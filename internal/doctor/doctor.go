package doctor

import (
	"archive/zip"
	"context"
	"fmt"
	"os"
	"os/exec"
	"path/filepath"
	"regexp"
	"runtime"
	"strconv"
	"strings"
	"time"

	"github.com/itskreisler/rain/internal/toolchain"
)

type Profile string

const (
	Simple  Profile = "simple"
	Complex Profile = "complex"
)

func ParseProfile(value string) (Profile, error) {
	switch Profile(strings.ToLower(strings.TrimSpace(value))) {
	case Simple:
		return Simple, nil
	case Complex:
		return Complex, nil
	default:
		return "", fmt.Errorf("perfil inválido %q: usa simple o complex", value)
	}
}

type Platform string

const (
	Alpine  Platform = "alpine"
	Termux  Platform = "termux"
	Generic Platform = "generic"
)

type Check struct {
	Name     string `json:"name"`
	Required bool   `json:"required"`
	OK       bool   `json:"ok"`
	Version  string `json:"version,omitempty"`
	Path     string `json:"path,omitempty"`
	Detail   string `json:"detail,omitempty"`
}

// SchemaVersion identifica la versión del esquema JSON de doctor. Los
// consumidores (CI, scripts) deben comprobarla antes de parsear el resto.
const SchemaVersion = "1"

type Report struct {
	SchemaVersion string   `json:"schemaVersion"`
	Profile       Profile  `json:"profile"`
	Platform      Platform `json:"platform"`
	OS            string   `json:"os"`
	Arch          string   `json:"arch"`
	Ready         bool     `json:"ready"`
	Checks        []Check  `json:"checks"`
	Missing       []string `json:"missing,omitempty"`
	Commands      []string `json:"commands,omitempty"`
}

func Diagnose(profile Profile) Report {
	report := Report{
		SchemaVersion: SchemaVersion,
		Profile:       profile,
		Platform:      DetectPlatform(),
		OS:            runtime.GOOS,
		Arch:          runtime.GOARCH,
	}

	add := func(check Check) {
		report.Checks = append(report.Checks, check)
	}

	javaPath, javaOut := runVersion("java", "-version")
	javaVersion := parseJavaVersion(javaOut)
	javaOK := javaPath != "" && majorOf(javaVersion) >= 17
	add(Check{
		Name:     "java",
		Required: true,
		OK:       javaOK,
		Version:  javaVersion,
		Path:     javaPath,
		Detail:   javaDetail(javaPath, javaOK),
	})

	javacPath, javacOut := runVersion("javac", "-version")
	javacVersion := firstVersion(javacOut)
	javacOK := javacPath != "" && majorOf(javacVersion) >= 17
	add(Check{
		Name:     "javac",
		Required: true,
		OK:       javacOK,
		Version:  javacVersion,
		Path:     javacPath,
		Detail:   javaDetail(javacPath, javacOK),
	})

	keytoolPath, _ := runVersion("keytool", "-help")
	add(Check{
		Name:     "keytool",
		Required: true,
		OK:       keytoolPath != "",
		Path:     keytoolPath,
		Detail:   missingDetail(keytoolPath),
	})

	aapt2Path, aapt2Out := runVersion("aapt2", "version")
	add(Check{
		Name:     "aapt2",
		Required: true,
		OK:       aapt2Path != "",
		Version:  firstVersion(aapt2Out),
		Path:     aapt2Path,
		Detail:   missingDetail(aapt2Path),
	})

	zipalignPath, _ := findCommand("zipalign")
	add(Check{
		Name:     "zipalign",
		Required: true,
		OK:       zipalignPath != "",
		Path:     zipalignPath,
		Detail:   missingDetail(zipalignPath),
	})

	zipPath, zipOut := runVersion("zip", "-v")
	add(Check{
		Name:     "zip",
		Required: true,
		OK:       zipPath != "",
		Version:  firstVersion(zipOut),
		Path:     zipPath,
		Detail:   missingDetail(zipPath),
	})

	d8Path, d8Out := runVersion("d8", "--version")
	d8Version := firstVersion(d8Out)
	if d8Path == "" {
		if managed := managedTool("r8.jar"); managed != "" {
			d8Path = managed
			d8Version = toolchain.R8Version
		} else if managed := managedTool("d8.jar"); managed != "" {
			d8Path = managed
			d8Version = "build-tools 36"
		}
	}
	d8OK := d8Path != "" && javaOK
	add(Check{
		Name:     "d8",
		Required: true,
		OK:       d8OK,
		Version:  d8Version,
		Path:     d8Path,
		Detail:   javaToolDetail(d8Path, d8OK, javaOK),
	})

	apksignerPath, apksignerOut := runVersion("apksigner", "version")
	if apksignerPath == "" {
		if managed := managedTool("apksigner.jar"); managed != "" {
			apksignerPath = managed
			apksignerOut = "build-tools 36"
		}
	}
	apksignerOK := apksignerPath != "" && javaOK
	add(Check{
		Name:     "apksigner",
		Required: true,
		OK:       apksignerOK,
		Version:  firstVersion(apksignerOut),
		Path:     apksignerPath,
		Detail:   javaToolDetail(apksignerPath, apksignerOK, javaOK),
	})

	jarPath, jarOK := FindAndroidJar()
	jarDetail := ""
	switch {
	case jarPath == "":
		jarDetail = "no encontrado"
	case !jarOK:
		jarDetail = "archivo inválido: no parece un android.jar"
	}
	add(Check{
		Name:     "android.jar",
		Required: true,
		OK:       jarOK,
		Version:  "API 36",
		Path:     jarPath,
		Detail:   jarDetail,
	})

	if profile == Complex {
		kotlincPath, kotlincOut := runVersion("kotlinc", "-version")
		if kotlincPath == "" {
			kotlincPath = managedTool(filepath.Join("kotlinc", "bin", "kotlinc"))
		}
		add(Check{
			Name:     "kotlinc",
			Required: true,
			OK:       kotlincPath != "",
			Version:  firstVersion(kotlincOut),
			Path:     kotlincPath,
			Detail:   missingDetail(kotlincPath),
		})

		plugin := findComposePlugin(kotlincPath)
		add(Check{
			Name:     "compose-plugin",
			Required: true,
			OK:       plugin != "",
			Path:     plugin,
			Detail:   missingDetail(plugin),
		})

		if report.Platform == Termux {
			jansi := termuxJansi()
			add(Check{
				Name:     "libjansi",
				Required: false,
				OK:       jansi != "",
				Path:     jansi,
				Detail:   "recomendado para kotlinc en Termux",
			})
		}
	}

	report.Ready = true
	for _, check := range report.Checks {
		if check.Required && !check.OK {
			report.Ready = false
			report.Missing = append(report.Missing, check.Name)
		}
	}
	report.Commands = Recommendations(report.Platform, profile)
	return report
}

func DetectPlatform() Platform {
	if strings.Contains(os.Getenv("PREFIX"), "com.termux") {
		return Termux
	}
	if data, err := os.ReadFile("/etc/os-release"); err == nil {
		if strings.Contains(strings.ToLower(string(data)), "alpine") {
			return Alpine
		}
	}
	return Generic
}

func Recommendations(platform Platform, profile Profile) []string {
	switch platform {
	case Termux:
		commands := []string{
			"pkg install openjdk-17 aapt2 d8 apksigner zipalign zip",
		}
		if profile == Complex {
			commands = append(commands, "pkg install kotlin libjansi")
		}
		return append(commands, "rain doctor --fix --profile "+string(profile))

	case Alpine:
		commands := []string{
			"apk add openjdk17-jdk zip",
			"apk add --repository https://dl-cdn.alpinelinux.org/alpine/edge/main --repository https://dl-cdn.alpinelinux.org/alpine/edge/community --repository https://dl-cdn.alpinelinux.org/alpine/edge/testing android-build-tools",
		}
		return append(commands, "rain doctor --fix --profile "+string(profile))

	default:
		commands := []string{
			"# Debian/Ubuntu: sudo apt install openjdk-17-jdk zip",
			"# aapt2/zipalign: instala Android build-tools 36 o el paquete de tu distro",
			"rain doctor --fix --profile " + string(profile),
		}
		return commands
	}
}

func FindAndroidJar() (string, bool) {
	var invalid string
	for _, path := range androidJarCandidates() {
		st, err := os.Stat(path)
		if err != nil || st.IsDir() {
			continue
		}
		if validAndroidJar(path) {
			return path, true
		}
		if invalid == "" {
			invalid = path
		}
	}
	return invalid, false
}

func androidJarCandidates() []string {
	home, _ := os.UserHomeDir()
	candidates := []string{
		os.Getenv("RAIN_ANDROID_JAR"),
		os.Getenv("ANDROID_JAR"),
		filepath.Join(home, ".rain", "toolchain", "android.jar"),
		filepath.Join(home, "android-sdk", "android-36", "android.jar"),
		filepath.Join(home, "Android", "Sdk", "platforms", "android-36", "android.jar"),
	}

	for _, env := range []string{"ANDROID_HOME", "ANDROID_SDK_ROOT"} {
		if base := os.Getenv(env); base != "" {
			candidates = append(candidates, filepath.Join(base, "platforms", "android-36", "android.jar"))
		}
	}

	// Cualquier plataforma android-XX instalada, de la más nueva a la más vieja.
	if base := os.Getenv("ANDROID_HOME"); base != "" {
		if entries, err := os.ReadDir(filepath.Join(base, "platforms")); err == nil {
			for i := len(entries) - 1; i >= 0; i-- {
				candidates = append(candidates, filepath.Join(base, "platforms", entries[i].Name(), "android.jar"))
			}
		}
	}
	return candidates
}

func validAndroidJar(path string) bool {
	zr, err := zip.OpenReader(path)
	if err != nil {
		return false
	}
	defer func() { _ = zr.Close() }()

	for _, f := range zr.File {
		if f.Name == "android/Manifest.class" || f.Name == "android/R.class" {
			return true
		}
	}
	return false
}

func managedTool(name string) string {
	home, err := os.UserHomeDir()
	if err != nil {
		return ""
	}
	for _, path := range []string{
		filepath.Join(home, ".rain", "toolchain", name),
		filepath.Join(home, ".rain", "bin", name),
	} {
		if fileExists(path) {
			return path
		}
	}
	return ""
}

func findComposePlugin(kotlincPath string) string {
	var candidates []string
	if kotlincPath != "" {
		real, err := filepath.EvalSymlinks(kotlincPath)
		if err == nil {
			kotlincPath = real
		}
		candidates = append(candidates,
			filepath.Join(filepath.Dir(kotlincPath), "..", "lib", "compose-compiler-plugin.jar"),
		)
	}

	home, _ := os.UserHomeDir()
	candidates = append(candidates,
		filepath.Join(home, ".rain", "toolchain", "kotlinc", "lib", "compose-compiler-plugin.jar"),
		filepath.Join(home, "kotlin", "kotlinc", "lib", "compose-compiler-plugin.jar"),
	)

	for _, candidate := range candidates {
		if fileExists(candidate) {
			return candidate
		}
	}
	return ""
}

func termuxJansi() string {
	prefix := os.Getenv("PREFIX")
	if prefix == "" {
		return ""
	}
	for _, candidate := range []string{
		filepath.Join(prefix, "lib", "libjansi.so"),
		filepath.Join(prefix, "lib", "jansi"),
	} {
		if _, err := os.Stat(candidate); err == nil {
			return candidate
		}
	}
	return ""
}

func runVersion(name string, args ...string) (string, string) {
	path, err := exec.LookPath(name)
	if err != nil {
		return "", ""
	}
	ctx, cancel := context.WithTimeout(context.Background(), 8*time.Second)
	defer cancel()

	out, err := exec.CommandContext(ctx, path, args...).CombinedOutput()
	if err != nil && len(out) == 0 {
		return path, ""
	}
	return path, strings.TrimSpace(string(out))
}

func findCommand(name string) (string, string) {
	path, err := exec.LookPath(name)
	if err != nil {
		return "", ""
	}
	return path, ""
}

var (
	javaVersionRe = regexp.MustCompile(`version "([^"]+)"`)
	numberRe      = regexp.MustCompile(`\d+(?:\.\d+)+`)
)

func parseJavaVersion(output string) string {
	if match := javaVersionRe.FindStringSubmatch(output); len(match) == 2 {
		return match[1]
	}
	return firstVersion(output)
}

func firstVersion(output string) string {
	return numberRe.FindString(output)
}

func majorOf(version string) int {
	if version == "" {
		return 0
	}
	parts := strings.Split(version, ".")
	major, _ := strconv.Atoi(parts[0])
	if major == 1 && len(parts) > 1 {
		major, _ = strconv.Atoi(parts[1])
	}
	return major
}

func javaDetail(path string, ok bool) string {
	if path == "" {
		return "no encontrado"
	}
	if !ok {
		return "se requiere Java 17 o superior"
	}
	return ""
}

func missingDetail(path string) string {
	if path == "" {
		return "no encontrado"
	}
	return ""
}

func javaToolDetail(path string, ok, javaOK bool) string {
	if path == "" {
		return "no encontrado"
	}
	if !ok && !javaOK {
		return "requiere Java 17+"
	}
	return ""
}

func fileExists(path string) bool {
	st, err := os.Stat(path)
	return err == nil && !st.IsDir()
}
