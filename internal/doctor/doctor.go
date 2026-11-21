package doctor

import (
	"archive/zip"
	"context"
	"errors"
	"fmt"
	"os"
	"os/exec"
	"path/filepath"
	"regexp"
	"runtime"
	"strconv"
	"strings"
	"time"

	"github.com/itskreisler/rain/internal/compat"
	"github.com/itskreisler/rain/internal/memenv"
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
	Ubuntu  Platform = "ubuntu"
	Windows Platform = "windows"
	MacOS   Platform = "macos"
	Generic Platform = "generic"
)

// Status es el contrato de presentación de cada herramienta. Es independiente
// de que la comprobación sea obligatoria u opcional: eso lo dice Required.
type Status string

const (
	StatusOK      Status = "ok"      // instalada y utilizable: se muestra su versión
	StatusMissing Status = "missing" // no instalada: no está en PATH ni en rutas gestionadas
	StatusBroken  Status = "broken"  // hay una ruta, pero no funciona
	StatusWarning Status = "warning" // presente pero con una advertencia (memoria, opcionales)
)

// Reason es la causa tipada detrás de un Status. La UI usa Detail para el
// texto libre y Reason para decidir el icono y el comando de instalación.
type Reason string

const (
	ReasonNone          Reason = ""
	ReasonNotInstalled  Reason = "not-installed"
	ReasonUnavailable   Reason = "unavailable"
	ReasonIncompatible  Reason = "incompatible"
	ReasonAdvisory      Reason = "advisory"
	ReasonManagedRepair Reason = "managed-repair"
)

type Check struct {
	Name     string `json:"name"`
	Required bool   `json:"required"`
	OK       bool   `json:"ok"` // derivado de Status, se conserva por compatibilidad
	Status   Status `json:"status"`
	Reason   Reason `json:"reason,omitempty"`
	Version  string `json:"version,omitempty"`
	Path     string `json:"path,omitempty"`
	Detail   string `json:"detail,omitempty"`
	Install  string `json:"install,omitempty"`
}

// SchemaVersion cambia porque el contrato de Check incorpora status/reason/
// install. Los consumidores deben comprobarla antes de parsear el resto.
const SchemaVersion = "2"

type Report struct {
	SchemaVersion string         `json:"schemaVersion"`
	Profile       Profile        `json:"profile"`
	Platform      Platform       `json:"platform"`
	OS            string         `json:"os"`
	Arch          string         `json:"arch"`
	Ready         bool           `json:"ready"`
	Checks        []Check        `json:"checks"`
	Compat        []compat.Issue `json:"compat,omitempty"`
	Missing       []string       `json:"missing,omitempty"`
	Commands      []string       `json:"commands,omitempty"`
}

// Diagnose mantiene la firma histórica. DiagnoseContext es la versión
// cancelable que usan los comandos que pueden instalar herramientas.
func Diagnose(profile Profile) Report {
	return DiagnoseContext(context.Background(), profile)
}

func DiagnoseContext(ctx context.Context, profile Profile) Report {
	report := Report{
		SchemaVersion: SchemaVersion,
		Profile:       profile,
		Platform:      DetectPlatform(),
		OS:            runtime.GOOS,
		Arch:          runtime.GOARCH,
	}

	add := func(check Check) {
		if check.Install == "" {
			check.Install = InstallCommand(report.Platform, check.Name, profile)
		}
		if check.Detail == "" {
			check.Detail = reasonText(check.Reason)
		}
		report.Checks = append(report.Checks, check)
	}

	probes := []toolProbe{
		{
			name: "java", command: "java", args: []string{"-version"}, required: true,
			parse: parseJavaVersion, valid: func(v string) bool { return majorOf(v) >= 17 },
			invalidDetail: "se requiere Java 17 o superior",
		},
		{
			name: "javac", command: "javac", args: []string{"-version"}, required: true,
			parse: firstVersion, valid: func(v string) bool { return majorOf(v) >= 17 },
			invalidDetail: "se requiere Java 17 o superior",
		},
		{name: "keytool", command: "keytool", args: []string{"-help"}, required: true},
		{name: "aapt2", command: "aapt2", args: []string{"version"}, required: true, parse: firstVersion},
		// zipalign no tiene --version: sin argumentos devuelve error de uso.
		// Se ejecuta de todos modos para detectar wrappers que no arrancan.
		{
			name: "zipalign", command: "zipalign", required: true, execOnly: true,
			parse:         zipalignProbeOutput,
			valid:         func(v string) bool { return v == "ok" },
			invalidDetail: "no se puede ejecutar: zipalign no responde",
		},
		{name: "zip", command: "zip", args: []string{"-v"}, required: true, parse: firstVersion},
	}
	for _, p := range probes {
		add(p.check(ctx, report.Platform, profile))
	}

	d8 := toolProbe{name: "d8", command: "d8", args: []string{"--version"}, required: true, parse: firstVersion}.
		check(ctx, report.Platform, profile)
	if !d8.OK {
		if jar := managedTool("r8.jar"); jar != "" {
			d8 = managedCheck(d8, jar, toolchain.R8Version)
		}
	}
	add(d8)

	apksigner := toolProbe{name: "apksigner", command: "apksigner", args: []string{"version"}, required: true, parse: firstVersion}.
		check(ctx, report.Platform, profile)
	if !apksigner.OK {
		if jar := managedTool("apksigner.jar"); jar != "" {
			apksigner = managedCheck(apksigner, jar, "build-tools 36")
		}
	}
	add(apksigner)

	jarPath, jarOK := FindAndroidJar()
	switch {
	case jarPath == "":
		add(makeCheck("android.jar", true, StatusMissing, ReasonNotInstalled, "", "", "", ""))
	case !jarOK:
		add(makeCheck("android.jar", true, StatusBroken, ReasonIncompatible, "", jarPath,
			"archivo inválido: no parece un android.jar", ""))
	default:
		add(makeCheck("android.jar", true, StatusOK, ReasonNone, "API "+apiLevelOf(jarPath), jarPath, "", ""))
	}

	if profile == Complex {
		kotlinc := toolProbe{name: "kotlinc", command: "kotlinc", args: []string{"-version"}, required: true, parse: firstVersion}.
			check(ctx, report.Platform, profile)
		if !kotlinc.OK {
			if path := managedTool(filepath.Join("kotlinc", "bin", "kotlinc")); path != "" {
				kotlinc = managedCheck(kotlinc, path, toolchain.KotlinVersion)
			}
		}
		add(kotlinc)

		plugin := findComposePlugin(kotlinc.Path)
		if plugin == "" {
			add(makeCheck("compose-plugin", true, StatusMissing, ReasonNotInstalled, "", "", "", ""))
		} else {
			add(makeCheck("compose-plugin", true, StatusOK, ReasonNone, "", plugin, "", ""))
		}

		if report.Platform == Termux {
			jansi := termuxJansi()
			if jansi == "" {
				add(makeCheck("libjansi", false, StatusWarning, ReasonAdvisory, "", "",
					"recomendado para kotlinc en Termux", ""))
			} else {
				add(makeCheck("libjansi", false, StatusOK, ReasonNone, "", jansi, "", ""))
			}
		}
	}

	// Los analizadores opcionales no bloquean: "rain lint" los usa si están.
	for _, lt := range []struct{ name, version string }{
		{"ktlint", toolchain.KtlintVersion},
		{"detekt", toolchain.DetektVersion},
	} {
		path, version := findLintTool(lt.name)
		if path == "" {
			add(makeCheck(lt.name, false, StatusWarning, ReasonAdvisory, "", "",
				"opcional: no instalado (rain doctor --fix --lint)", ""))
			continue
		}
		if version == "" {
			version = lt.version
		}
		add(makeCheck(lt.name, false, StatusOK, ReasonNone, version, path,
			"opcional: disponible para rain lint", ""))
	}

	mem := memenv.Detect()
	memDetail := memenv.Describe()
	if hint := memenv.Hint(); hint != "" {
		memDetail += " → " + hint
	}
	memStatus, memReason := StatusOK, ReasonNone
	if mem.Tight() {
		memStatus, memReason = StatusWarning, ReasonAdvisory
	}
	add(makeCheck("memoria", false, memStatus, memReason, "", "", memDetail, ""))

	report.Compat = compat.CheckMatrix(versionsFromChecks(report.Checks))

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

// toolProbe describe una comprobación de una herramienta de línea de comandos.
type toolProbe struct {
	name          string
	command       string
	args          []string
	required      bool
	execOnly      bool
	parse         func(string) string
	valid         func(string) bool
	invalidDetail string
}

func (p toolProbe) check(ctx context.Context, platform Platform, profile Profile) Check {
	path, out, err := runVersion(ctx, p.command, p.args...)
	version := ""
	if p.parse != nil {
		version = p.parse(out)
	}

	status, reason, detail := StatusOK, ReasonNone, ""
	switch {
	case err != nil && path == "":
		status, reason = StatusMissing, ReasonNotInstalled
	case err != nil && (!p.execOnly || !processStarted(err)):
		status, reason = StatusBroken, ReasonUnavailable
		detail = "no se puede ejecutar: " + err.Error()
	case p.valid != nil && !p.valid(version):
		status, reason, detail = StatusBroken, ReasonIncompatible, p.invalidDetail
	}
	return makeCheck(p.name, p.required, status, reason, version, path, detail,
		InstallCommand(platform, p.name, profile))
}

// processStarted distingue "comando encontrado pero con código de salida"
// de "no se pudo arrancar". zipalign termina con código 2 al mostrar el uso.
func processStarted(err error) bool {
	var exitErr *exec.ExitError
	return errors.As(err, &exitErr)
}

func makeCheck(name string, required bool, status Status, reason Reason, version, path, detail, install string) Check {
	if detail == "" {
		detail = reasonText(reason)
	}
	return Check{
		Name: name, Required: required, Status: status, OK: status == StatusOK,
		Reason: reason, Version: version, Path: path, Detail: detail, Install: install,
	}
}

func managedCheck(base Check, path, version string) Check {
	base.Status, base.Reason, base.OK = StatusOK, ReasonManagedRepair, true
	base.Path = path
	if base.Version == "" {
		base.Version = version
	}
	base.Detail = "gestionado por Rain"
	return base
}

func reasonText(reason Reason) string {
	switch reason {
	case ReasonNotInstalled:
		return "no instalado"
	case ReasonUnavailable:
		return "no se encuentra"
	case ReasonIncompatible:
		return "no funciona"
	case ReasonManagedRepair:
		return "gestionado por Rain"
	default:
		return ""
	}
}

func versionsFromChecks(checks []Check) compat.Versions {
	versions := compat.Versions{CompileSDK: 36}
	for _, check := range checks {
		switch check.Name {
		case "java":
			versions.JDK = check.Version
		case "d8":
			versions.R8 = check.Version
		case "kotlinc":
			versions.Kotlin = check.Version
		case "build-tools":
			versions.BuildTools = check.Version
		}
	}
	return versions
}

// DetectPlatform prioriza señales explícitas del entorno (Termux, GOOS) antes
// que los gestores de paquetes disponibles en PATH.
func DetectPlatform() Platform {
	if runtime.GOOS == "windows" {
		return Windows
	}
	if runtime.GOOS == "darwin" {
		return MacOS
	}
	if strings.Contains(os.Getenv("PREFIX"), "com.termux") {
		return Termux
	}
	if runtime.GOOS == "android" {
		return Termux
	}

	if id, idLike := readOSRelease(); id != "" {
		if p := platformFromRelease(runtime.GOOS, id, idLike); p != Generic {
			return p
		}
	}
	if _, err := exec.LookPath("apk"); err == nil {
		return Alpine
	}
	if _, err := exec.LookPath("apt-get"); err == nil {
		return Ubuntu
	}
	if _, err := exec.LookPath("apt"); err == nil {
		return Ubuntu
	}
	return Generic
}

// platformFromRelease es pura para poder probar la detección sin depender del
// /etc/os-release de la máquina que ejecuta los tests.
func platformFromRelease(goos, id, idLike string) Platform {
	switch {
	case goos == "windows":
		return Windows
	case goos == "darwin":
		return MacOS
	case id == "alpine" || hasToken(idLike, "alpine"):
		return Alpine
	case id == "termux":
		return Termux
	case id == "ubuntu" || id == "debian" || hasToken(idLike, "debian") || hasToken(idLike, "ubuntu"):
		return Ubuntu
	default:
		return Generic
	}
}

func readOSRelease() (id, idLike string) {
	data, err := os.ReadFile("/etc/os-release")
	if err != nil {
		return "", ""
	}
	for _, line := range strings.Split(string(data), "\n") {
		key, value, found := strings.Cut(line, "=")
		if !found {
			continue
		}
		value = strings.Trim(strings.ToLower(strings.TrimSpace(value)), `"'`)
		switch strings.TrimSpace(key) {
		case "ID":
			id = value
		case "ID_LIKE":
			idLike = value
		}
	}
	return id, idLike
}

func hasToken(list, token string) bool {
	for _, part := range strings.Fields(strings.NewReplacer("-", " ", "_", " ").Replace(list)) {
		if part == token {
			return true
		}
	}
	return false
}

// InstallCommand devuelve el comando concreto para una herramienta fallida.
// Las herramientas que Rain puede descargar por su cuenta se arreglan con
// --fix; el resto depende del gestor de paquetes de la plataforma.
func InstallCommand(platform Platform, tool string, profile Profile) string {
	fix := "rain doctor --fix --profile " + string(profile)
	switch tool {
	case "d8", "apksigner", "android.jar", "aapt2", "zipalign", "kotlinc", "compose-plugin":
		return fix
	case "ktlint", "detekt":
		return fix + " --lint"
	}

	switch platform {
	case Ubuntu:
		switch tool {
		case "java", "javac", "keytool":
			return "sudo apt install -y openjdk-17-jdk-headless"
		case "zip":
			return "sudo apt install -y zip"
		}
	case Alpine:
		switch tool {
		case "java", "javac", "keytool":
			return "apk add openjdk17-jdk"
		case "zip":
			return "apk add zip"
		}
	case Termux:
		switch tool {
		case "java", "javac", "keytool":
			return "pkg install openjdk-17"
		case "zip":
			return "pkg install zip"
		}
	case Windows:
		switch tool {
		case "java", "javac", "keytool":
			return "winget install --id EclipseAdoptium.Temurin.17.JDK -e"
		case "zip":
			return "choco install zip -y"
		}
	case MacOS:
		switch tool {
		case "java", "javac", "keytool":
			return "brew install openjdk@17"
		case "zip":
			return "brew install zip"
		}
	}
	return fix
}

func Recommendations(platform Platform, profile Profile) []string {
	fix := "rain doctor --fix --profile " + string(profile)
	switch platform {
	case Termux:
		commands := []string{"pkg install openjdk-17 zip"}
		if profile == Complex {
			commands = append(commands, "pkg install kotlin libjansi")
		}
		return append(commands, fix)
	case Alpine:
		return []string{
			"apk add openjdk17-jdk zip",
			"apk add --repository https://dl-cdn.alpinelinux.org/alpine/edge/main --repository https://dl-cdn.alpinelinux.org/alpine/edge/community --repository https://dl-cdn.alpinelinux.org/alpine/edge/testing android-build-tools",
			fix,
		}
	case Ubuntu:
		return []string{"sudo apt install -y openjdk-17-jdk-headless zip", fix}
	case Windows:
		return []string{
			"winget install --id EclipseAdoptium.Temurin.17.JDK -e",
			"choco install zip -y",
			fix,
		}
	case MacOS:
		return []string{"brew install openjdk@17 zip", fix}
	default:
		return []string{
			"# Termux: pkg install openjdk-17 zip",
			"# Ubuntu/Debian: sudo apt install -y openjdk-17-jdk-headless zip",
			"# Alpine: apk add openjdk17-jdk zip",
			fix,
		}
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

var apiLevelRe = regexp.MustCompile(`android-(\d+)`)

func apiLevelOf(path string) string {
	if match := apiLevelRe.FindStringSubmatch(filepath.ToSlash(path)); len(match) == 2 {
		return match[1]
	}
	return "36"
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

func findLintTool(name string) (string, string) {
	switch name {
	case "ktlint":
		if jar := managedTool("ktlint-cli.jar"); jar != "" {
			return jar, toolchain.KtlintVersion
		}
		if path, err := exec.LookPath("ktlint"); err == nil {
			return path, ""
		}
	case "detekt":
		if jar := managedTool("detekt-cli.jar"); jar != "" {
			return jar, toolchain.DetektVersion
		}
		if path, err := exec.LookPath("detekt"); err == nil {
			return path, ""
		}
	}
	return "", ""
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

// runVersion localiza un comando y lo ejecuta para obtener su versión.
//
// A diferencia de la versión anterior, conserva la ruta cuando el comando
// falla: así doctor puede distinguir "no instalado" (sin ruta) de
// "encontrado pero no funciona" (con ruta y error).
func runVersion(ctx context.Context, name string, args ...string) (string, string, error) {
	path, err := exec.LookPath(name)
	if err != nil {
		return "", "", fmt.Errorf("no encontrado en PATH: %w", err)
	}
	runCtx, cancel := context.WithTimeout(ctx, 8*time.Second)
	defer cancel()

	out, err := exec.CommandContext(runCtx, path, args...).CombinedOutput()
	return path, strings.TrimSpace(string(out)), err
}

var (
	javaVersionRe = regexp.MustCompile(`version "([^"]+)"`)
	numberRe      = regexp.MustCompile(`\d+(?:\.\d+)+`)
	warningLineRe = regexp.MustCompile(`^\[\d+(?:\.\d+)?s\]`)
)

func parseJavaVersion(output string) string {
	if match := javaVersionRe.FindStringSubmatch(output); len(match) == 2 {
		return match[1]
	}
	return firstVersion(output)
}

// zipalignProbeOutput detecta el texto de uso que zipalign imprime aunque
// termine con código 2. Un lanzador roto no llega a imprimirlo.
func zipalignProbeOutput(output string) string {
	if strings.Contains(output, "Zip alignment utility") && strings.Contains(output, "Usage: zipalign") {
		return "ok"
	}
	return ""
}

// firstVersion ignora las líneas de avisos de la JVM (p. ej.
// "[0.001s][warning][os,container] ..."), que antes se confundían con la
// versión: "javac 0.001" en vez de "javac 21.0.12.1".
func firstVersion(output string) string {
	for _, line := range strings.Split(output, "\n") {
		line = strings.TrimSpace(line)
		if line == "" || warningLineRe.MatchString(line) {
			continue
		}
		if version := numberRe.FindString(line); version != "" {
			return version
		}
	}
	return ""
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
