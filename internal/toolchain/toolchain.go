package toolchain

import (
	"archive/zip"
	"context"
	"errors"
	"fmt"
	"io"
	"net/http"
	"os"
	"path/filepath"
	"runtime"
	"sort"
	"strings"
	"time"
)

const (
	BuildToolsURL = "https://dl.google.com/android/repository/build-tools_r36_linux.zip"
	PlatformURL   = "https://dl.google.com/android/repository/platform-36_r02.zip"

	// NDKVersion es el NDK que Rain descarga para los proyectos con [native].
	// r27c es la última versión estable con soporte para API 21+ y toolchain
	// LLVM única, que es la que esperan los scripts clang/make de los proyectos.
	NDKVersion = "r27c"
)

var ndkURLs = map[string]string{
	"linux":   "https://dl.google.com/android/repository/android-ndk-" + NDKVersion + "-linux.zip",
	"windows": "https://dl.google.com/android/repository/android-ndk-" + NDKVersion + "-windows.zip",
	"darwin":  "https://dl.google.com/android/repository/android-ndk-" + NDKVersion + "-darwin.zip",
}

func ndkURLFor(goos, goarch string) (string, error) {
	url, ok := ndkURLs[goos]
	if !ok {
		return "", fmt.Errorf("no hay NDK oficial de Rain para %s", goos)
	}
	// Google publica el ZIP de Linux solo para x86-64. En otras arquitecturas
	// se respeta un NDK del sistema, pero no se intenta ejecutar el de x86-64.
	if goos == "linux" && goarch != "amd64" {
		return "", fmt.Errorf("no hay ZIP de NDK para linux/%s; usa un NDK del sistema o ANDROID_NDK_HOME", goarch)
	}
	return url, nil
}

// buildToolsURLs son los artefactos oficiales de build-tools 36. Se usan para
// extraer apksigner.jar, aapt2 y zipalign sin depender del gestor de paquetes.
var buildToolsURLs = map[string]string{
	"linux":   BuildToolsURL,
	"windows": "https://dl.google.com/android/repository/build-tools_r36_windows.zip",
	"darwin":  "https://dl.google.com/android/repository/build-tools_r36_macosx.zip",
}

func buildToolsURL() string { return buildToolsURLFor(runtime.GOOS) }

func buildToolsURLFor(goos string) string {
	if url, ok := buildToolsURLs[goos]; ok {
		return url
	}
	return BuildToolsURL
}

// nativeBuildToolsAvailable indica si los ZIP oficiales de build-tools traen
// ejecutables para esta plataforma. Google solo publica Linux x86-64: en
// linux/arm64 Rain debe usar aapt2 y zipalign del sistema.
func nativeBuildToolsAvailable() bool {
	return nativeBuildToolsAvailableFor(runtime.GOOS, runtime.GOARCH)
}

func nativeBuildToolsAvailableFor(goos, goarch string) bool {
	return goos != "linux" || goarch == "amd64"
}

func nativeName(base string) string { return nativeNameFor(base, runtime.GOOS) }

func nativeNameFor(base, goos string) string {
	if goos == "windows" {
		return base + ".exe"
	}
	return base
}

// needsLibcxx indica si el ZIP de build-tools de ese sistema incluye libc++.so.
// Solo el wrapper de zipalign de Linux lo necesita; los ZIP de Windows y macOS
// no lo traen.
func needsLibcxx(goos string) bool { return goos == "linux" }

const (
	R8Version = "9.5.28"
	R8URL     = "https://dl.google.com/dl/android/maven2/com/android/tools/r8/" + R8Version + "/r8-" + R8Version + ".jar"

	KotlinVersion = "2.4.21"
	KotlinURL     = "https://github.com/JetBrains/kotlin/releases/download/v" + KotlinVersion + "/kotlin-compiler-" + KotlinVersion + ".zip"

	// Analizadores estáticos opcionales para "rain lint". No hacen falta para
	// compilar: ktlint revisa el estilo de Kotlin y detekt los "code smells".
	KtlintVersion = "1.8.0"
	KtlintURL     = "https://repo1.maven.org/maven2/com/pinterest/ktlint/ktlint-cli/" + KtlintVersion + "/ktlint-cli-" + KtlintVersion + "-all.jar"

	DetektVersion = "1.23.8"
	DetektURL     = "https://github.com/detekt/detekt/releases/download/v" + DetektVersion + "/detekt-cli-" + DetektVersion + "-all.jar"

	// PMD analiza fuentes Java (y Kotlin). Rain gestiona su distribución porque
	// la CLI cambió de sintaxis en la versión 7 y así todos los proyectos usan
	// una versión conocida.
	PmdVersion = "7.29.0"
	PmdURL     = "https://github.com/pmd/pmd/releases/download/pmd_releases%2F" + PmdVersion + "/pmd-dist-" + PmdVersion + "-bin.zip"

	// google-java-format reescribe Java en "rain fmt". El jar "all-deps" evita
	// resolver dependencias Maven en el equipo del usuario.
	GoogleJavaFormatVersion = "2.0.0"
	GoogleJavaFormatURL     = "https://repo1.maven.org/maven2/com/google/googlejavaformat/google-java-format/" + GoogleJavaFormatVersion + "/google-java-format-" + GoogleJavaFormatVersion + "-all-deps.jar"
)

// Manager gestiona el toolchain propio de Rain en ~/.rain, sin root.
type Manager struct {
	Root   string
	Client *http.Client
	Out    io.Writer
}

func New() (*Manager, error) {
	home, err := os.UserHomeDir()
	if err != nil {
		return nil, fmt.Errorf("no se pudo determinar el HOME: %w", err)
	}
	return &Manager{
		Root:   filepath.Join(home, ".rain"),
		Client: &http.Client{Timeout: 20 * time.Minute},
		Out:    os.Stdout,
	}, nil
}

func (m *Manager) BinDir() string     { return filepath.Join(m.Root, "bin") }
func (m *Manager) ToolDir() string    { return filepath.Join(m.Root, "toolchain") }
func (m *Manager) AndroidJar() string { return filepath.Join(m.ToolDir(), "android.jar") }
func (m *Manager) R8Jar() string      { return filepath.Join(m.ToolDir(), "r8.jar") }
func (m *Manager) ApksignerJar() string {
	return filepath.Join(m.ToolDir(), "apksigner.jar")
}
func (m *Manager) Aapt2() string {
	return filepath.Join(m.ToolDir(), nativeName("aapt2"))
}
func (m *Manager) ZipAlign() string {
	return filepath.Join(m.ToolDir(), nativeName("zipalign"))
}
func (m *Manager) Kotlinc() string { return filepath.Join(m.ToolDir(), "kotlinc", "bin", "kotlinc") }
func (m *Manager) KtlintJar() string {
	return filepath.Join(m.ToolDir(), "ktlint-cli.jar")
}
func (m *Manager) DetektJar() string {
	return filepath.Join(m.ToolDir(), "detekt-cli.jar")
}
func (m *Manager) PmdDir() string {
	return filepath.Join(m.ToolDir(), "pmd-bin-"+PmdVersion)
}
func (m *Manager) PmdBin() string {
	name := "pmd"
	if runtime.GOOS == "windows" {
		name = "pmd.bat"
	}
	return filepath.Join(m.PmdDir(), "bin", name)
}
func (m *Manager) GoogleJavaFormatJar() string {
	return filepath.Join(m.ToolDir(), "google-java-format.jar")
}
func (m *Manager) ComposePlugin() string {
	return filepath.Join(m.ToolDir(), "kotlinc", "lib", "compose-compiler-plugin.jar")
}
func (m *Manager) NDKDir() string {
	return filepath.Join(m.ToolDir(), "android-ndk-"+NDKVersion)
}

// FindNDK devuelve el NDK a usar, sin descargar nada. Respeta las variables
// tradicionales, luego el gestionado por Rain y por último los NDK instalados
// por Android Studio en el SDK.
func FindNDK() string {
	for _, key := range []string{"ANDROID_NDK_HOME", "ANDROID_NDK_ROOT"} {
		if dir := strings.TrimSpace(os.Getenv(key)); dir != "" && ndkUsable(dir) {
			return dir
		}
	}

	home, err := os.UserHomeDir()
	if err == nil {
		managed := filepath.Join(home, ".rain", "toolchain", "android-ndk-"+NDKVersion)
		if ndkUsable(managed) {
			return managed
		}
		for _, sdk := range []string{
			os.Getenv("ANDROID_HOME"),
			os.Getenv("ANDROID_SDK_ROOT"),
			filepath.Join(home, "Android", "Sdk"),
		} {
			if sdk == "" {
				continue
			}
			matches, _ := filepath.Glob(filepath.Join(sdk, "ndk", "*"))
			sort.Sort(sort.Reverse(sort.StringSlice(matches)))
			for _, dir := range matches {
				if ndkUsable(dir) {
					return dir
				}
			}
		}
	}
	return ""
}

// ndkUsable comprueba el binario que realmente necesita el script de build de los
// proyectos: el clang del toolchain LLVM. Un directorio vacío no cuenta.
func ndkUsable(dir string) bool {
	if dir == "" || !dirExists(dir) {
		return false
	}
	matches, _ := filepath.Glob(filepath.Join(dir, "toolchains", "llvm", "prebuilt", "*", "bin", nativeName("clang")))
	return len(matches) > 0
}

// EnsureNDK devuelve un NDK usable, descargando el gestionado solo si no hay
// ninguno en el equipo. Es deliberadamente independiente de Ensure: el NDK
// pesa cientos de MB y solo hace falta en proyectos con código nativo.
func (m *Manager) EnsureNDK(ctx context.Context) (string, error) {
	if ndk := FindNDK(); ndk != "" {
		return ndk, nil
	}
	url, err := ndkURLFor(runtime.GOOS, runtime.GOARCH)
	if err != nil {
		return "", err
	}
	tmp, err := m.downloadTemp(ctx, url, "Android NDK "+NDKVersion)
	if err != nil {
		return "", err
	}
	defer func() { _ = os.Remove(tmp) }()

	staging := filepath.Join(m.ToolDir(), ".android-ndk-"+NDKVersion+".part")
	if err := os.RemoveAll(staging); err != nil {
		return "", err
	}
	if err := extractPrefix(tmp, "android-ndk-"+NDKVersion+"/", staging); err != nil {
		return "", err
	}
	if !ndkUsable(staging) {
		return "", fmt.Errorf("el ZIP del NDK no contiene toolchains/llvm/prebuilt/*/bin/clang")
	}
	if err := os.RemoveAll(m.NDKDir()); err != nil {
		return "", err
	}
	if err := os.Rename(staging, m.NDKDir()); err != nil {
		return "", err
	}
	return m.NDKDir(), nil
}

// Ensure descarga e instala en ~/.rain solo lo que falte.
func (m *Manager) Ensure(ctx context.Context, complex bool) error {
	if err := os.MkdirAll(m.BinDir(), 0o755); err != nil {
		return err
	}
	if err := os.MkdirAll(m.ToolDir(), 0o755); err != nil {
		return err
	}

	steps := []struct {
		name string
		fn   func(context.Context) error
	}{
		{"R8 " + R8Version, m.ensureR8},
		{"build-tools 36 (apksigner, aapt2, zipalign)", m.ensureBuildTools},
		{"android.jar (API 36)", m.ensureAndroidJar},
	}
	if complex {
		steps = append(steps, struct {
			name string
			fn   func(context.Context) error
		}{"Kotlin " + KotlinVersion, m.ensureKotlin})
	}

	for _, step := range steps {
		if err := step.fn(ctx); err != nil {
			return fmt.Errorf("%s: %w", step.name, err)
		}
	}

	return m.writeWrappers(complex)
}

// EnsureLint descarga las herramientas opcionales de "rain lint" y "rain fmt"
// (ktlint, detekt, pmd y google-java-format) en el toolchain de Rain. No son
// necesarias para compilar.
func (m *Manager) EnsureLint(ctx context.Context) error {
	if err := os.MkdirAll(m.ToolDir(), 0o755); err != nil {
		return err
	}
	if err := m.ensureKtlint(ctx); err != nil {
		return fmt.Errorf("ktlint: %w", err)
	}
	if err := m.ensureDetekt(ctx); err != nil {
		return fmt.Errorf("detekt: %w", err)
	}
	if err := m.ensurePmd(ctx); err != nil {
		return fmt.Errorf("pmd: %w", err)
	}
	if err := m.ensureGoogleJavaFormat(ctx); err != nil {
		return fmt.Errorf("google-java-format: %w", err)
	}
	return nil
}

func (m *Manager) ensureKtlint(ctx context.Context) error {
	if fileExists(m.KtlintJar()) {
		return nil
	}
	return m.download(ctx, KtlintURL, m.KtlintJar(), "ktlint "+KtlintVersion)
}

func (m *Manager) ensureDetekt(ctx context.Context) error {
	if fileExists(m.DetektJar()) {
		return nil
	}
	return m.download(ctx, DetektURL, m.DetektJar(), "detekt "+DetektVersion)
}

// ensurePmd extrae la distribución oficial en ~/.rain/toolchain/pmd-bin-X.
// Rain la detecta desde ahí antes de mirar el PATH.
func (m *Manager) ensurePmd(ctx context.Context) error {
	if fileExists(m.PmdBin()) {
		return nil
	}
	tmp, err := m.downloadTemp(ctx, PmdURL, "pmd "+PmdVersion)
	if err != nil {
		return err
	}
	defer func() { _ = os.Remove(tmp) }()
	return extractPrefix(tmp, "pmd-bin-"+PmdVersion+"/", m.ToolDir())
}

func (m *Manager) ensureGoogleJavaFormat(ctx context.Context) error {
	if fileExists(m.GoogleJavaFormatJar()) {
		return nil
	}
	return m.download(ctx, GoogleJavaFormatURL, m.GoogleJavaFormatJar(), "google-java-format "+GoogleJavaFormatVersion)
}

func (m *Manager) ensureR8(ctx context.Context) error {
	if fileExists(m.R8Jar()) {
		return nil
	}
	return m.download(ctx, R8URL, m.R8Jar(), "R8 "+R8Version)
}

// ensureBuildTools extrae de un único ZIP las herramientas nativas/JAR que
// Rain necesita. Se descarga solo si falta alguna de ellas. En plataformas sin
// binarios oficiales (linux/arm64) solo saca apksigner.jar y usa los ejecutables
// del sistema. libc++.so solo existe en el ZIP de Linux y solo lo usa su
// wrapper de zipalign.
func (m *Manager) ensureBuildTools(ctx context.Context) error {
	native := nativeBuildToolsAvailable()
	libcxx := needsLibcxx(runtime.GOOS) && native
	libcxxReady := !libcxx || (fileExists(m.libcxxPath()) && fileExists(m.libcxxPath()+".1"))
	nativesReady := !native || (fileExists(m.Aapt2()) && fileExists(m.ZipAlign()))
	if fileExists(m.ApksignerJar()) && nativesReady && libcxxReady {
		return m.publishNativeBinaries()
	}

	tmp, err := m.downloadTemp(ctx, buildToolsURL(), "build-tools 36")
	if err != nil {
		return err
	}
	defer func() { _ = os.Remove(tmp) }()

	tools := []buildTool{
		{
			name: "apksigner.jar", dest: m.ApksignerJar(), mode: 0o644,
			match: func(n string) bool { return strings.HasSuffix(n, "/lib/apksigner.jar") },
		},
		{
			name: nativeName("aapt2"), dest: m.Aapt2(), mode: 0o755, native: true,
			match: func(n string) bool { return filepath.Base(n) == nativeName("aapt2") },
		},
		{
			name: nativeName("zipalign"), dest: m.ZipAlign(), mode: 0o755, native: true,
			match: func(n string) bool { return filepath.Base(n) == nativeName("zipalign") },
		},
		{
			name: "libc++.so", dest: m.libcxxPath(), mode: 0o644, native: true, linuxOnly: true,
			match: func(n string) bool { return filepath.Base(n) == "libc++.so" },
		},
		{
			name: "libc++.so.1", dest: m.libcxxPath() + ".1", mode: 0o644, native: true, linuxOnly: true,
			match: func(n string) bool { return filepath.Base(n) == "libc++.so.1" },
		},
	}

	pending := make([]buildTool, 0, len(tools))
	for _, tool := range tools {
		if (tool.native && !native) || (tool.linuxOnly && !libcxx) || fileExists(tool.dest) {
			continue
		}
		pending = append(pending, tool)
	}
	if len(pending) == 0 {
		return m.publishNativeBinaries()
	}
	if err := extractBuildTools(tmp, pending); err != nil {
		return err
	}
	return m.publishNativeBinaries()
}

// buildTool describe una herramienta a extraer del ZIP de build-tools.
type buildTool struct {
	name      string
	dest      string
	mode      os.FileMode
	match     func(string) bool
	native    bool
	linuxOnly bool
}

// extractBuildTools abre el ZIP una sola vez y localiza todas las herramientas
// en un único recorrido. Antes se abría y se recorría una vez por herramienta
// (cinco veces), aunque siempre fuera el mismo archivo.
func extractBuildTools(zipPath string, tools []buildTool) error {
	zr, err := zip.OpenReader(zipPath)
	if err != nil {
		return err
	}
	defer func() { _ = zr.Close() }()

	// Índice por nombre de fichero. Si hay varias entradas con el mismo nombre
	// se conserva la primera que encaja, igual que hacía extractOneMode.
	porNombre := make(map[string]*zip.File, len(tools))
	for _, f := range zr.File {
		if f.FileInfo().IsDir() {
			continue
		}
		nombre := filepath.Base(f.Name)
		if _, yaVisto := porNombre[nombre]; yaVisto {
			continue
		}
		for _, tool := range tools {
			if filepath.Base(tool.name) == nombre && tool.match(f.Name) {
				porNombre[nombre] = f
				break
			}
		}
	}

	for _, tool := range tools {
		f := porNombre[filepath.Base(tool.name)]
		if f == nil {
			return fmt.Errorf("%s: no se encontró %s dentro del ZIP", tool.name, tool.dest)
		}
		if err := extractZipFile(f, tool.dest, tool.mode); err != nil {
			return fmt.Errorf("%s: %w", tool.name, err)
		}
	}
	return nil
}

func (m *Manager) libcxxPath() string {
	return filepath.Join(m.ToolDir(), "lib64", "libc++.so")
}

// publishNativeBinaries deja en ~/.rain/bin (que el CLI antepone al PATH)
// los ejecutables que el pipeline invoca por su nombre. zipalign necesita un
// wrapper: el binario de Google enlaza libc++.so, que no está en Ubuntu.
// En plataformas sin binarios oficiales retira las copias gestionadas para
// que no tapen las del sistema.
func (m *Manager) publishNativeBinaries() error {
	return m.publishNativeBinariesFor(nativeBuildToolsAvailable())
}

// zipalignWrapper sustituye al binario de Google, que enlaza libc++ de
// build-tools y no arranca en las distribuciones que no lo traen.
const zipalignWrapper = "#!/bin/sh\n" +
	"# Lanzador de Rain para zipalign (necesita libc++ de build-tools).\n" +
	"LD_LIBRARY_PATH=\"$HOME/.rain/toolchain/lib64${LD_LIBRARY_PATH:+:$LD_LIBRARY_PATH}\" " +
	"exec \"$HOME/.rain/toolchain/zipalign\" \"$@\"\n"

func (m *Manager) publishNativeBinariesFor(native bool) error {
	if err := os.MkdirAll(m.BinDir(), 0o755); err != nil {
		return err
	}
	if !native {
		return m.removeNativeBinaries()
	}
	for _, name := range []string{nativeName("aapt2"), nativeName("zipalign")} {
		src := filepath.Join(m.ToolDir(), name)
		if !fileExists(src) {
			continue
		}
		dst := filepath.Join(m.BinDir(), name)

		// El contenido de zipalign se descarta: no tiene sentido leer un
		// binario que va a sustituirse por el wrapper. En Windows se mantiene
		// el comportamiento histórico: el nombre termina en .exe y no se sustituye.
		var content []byte
		if name == "zipalign" {
			content = []byte(zipalignWrapper)
		} else {
			var err error
			content, err = os.ReadFile(src)
			if err != nil {
				return err
			}
		}
		if existing, err := os.ReadFile(dst); err == nil && string(content) == string(existing) {
			continue
		}
		if err := os.WriteFile(dst, content, 0o755); err != nil {
			return err
		}
	}
	return nil
}

// removeNativeBinaries borra las copias gestionadas de aapt2 y zipalign.
// Sin ellas, exec.LookPath encuentra los ejecutables nativos del sistema.
func (m *Manager) removeNativeBinaries() error {
	var errs []error
	for _, dir := range []string{m.BinDir(), m.ToolDir()} {
		for _, base := range []string{"aapt2", "zipalign"} {
			for _, name := range []string{base, nativeName(base)} {
				path := filepath.Join(dir, name)
				if err := os.Remove(path); err != nil && !os.IsNotExist(err) {
					errs = append(errs, err)
				}
			}
		}
	}
	return errors.Join(errs...)
}

// PruneUnsupportedNativeBinaries limpia binarios de build-tools descargados
// por versiones anteriores de Rain en arquitecturas sin soporte oficial.
// Se llama al iniciar el CLI, antes de anteponer ~/.rain/bin al PATH.
func PruneUnsupportedNativeBinaries() error {
	if nativeBuildToolsAvailable() {
		return nil
	}
	manager, err := New()
	if err != nil {
		return err
	}
	return manager.removeNativeBinaries()
}

func (m *Manager) ensureAndroidJar(ctx context.Context) error {
	if fileExists(m.AndroidJar()) {
		return nil
	}

	tmp, err := m.downloadTemp(ctx, PlatformURL, "Android platform 36")
	if err != nil {
		return err
	}
	defer func() { _ = os.Remove(tmp) }()

	return extractOne(tmp, func(name string) bool {
		return strings.HasSuffix(name, "/android.jar")
	}, m.AndroidJar())
}

func (m *Manager) ensureKotlin(ctx context.Context) error {
	if fileExists(m.Kotlinc()) {
		return nil
	}

	tmp, err := m.downloadTemp(ctx, KotlinURL, "Kotlin "+KotlinVersion)
	if err != nil {
		return err
	}
	defer func() { _ = os.Remove(tmp) }()

	dest := filepath.Join(m.ToolDir(), "kotlinc")
	if err := os.RemoveAll(dest); err != nil {
		return err
	}
	return extractPrefix(tmp, "kotlinc/", dest)
}

// writeWrappers crea los scripts lanzadores de las herramientas de Java.
//
// El heap de la JVM se acota con RAIN_JAVA_MAX_HEAP porque, por defecto, la
// JVM reserva 1/4 de la RAM física: en un contenedor con 2 GB libres y 12 GB de
// máquina eso significa 3 GB reservados y un OOM killer del kernel.
//
//	RAIN_JAVA_MAX_HEAP=1g   fija el heap de todas las herramientas
//	RAIN_JAVA_OPTS="..."    opciones extra (p. ej. -XX:+UseSerialGC)
//
// RefreshWrappers reescribe los lanzadores de ~/.rain/bin cuando su contenido
// ha cambiado. Se llama en cada ejecución de Rain para que una actualización
// aplique los nuevos ajustes (heap de la JVM, variables de entorno) sin
// obligar a ejecutar "rain doctor --fix".
func (m *Manager) RefreshWrappers() error {
	return m.writeWrappers(true)
}

func (m *Manager) writeWrappers(complex bool) error {
	if runtime.GOOS == "windows" {
		return m.writeWindowsWrappers(complex)
	}
	wrappers := map[string]string{
		"d8": "#!/bin/sh\n" +
			"# Lanzador de Rain para d8 (R8).\n" +
			"exec java -Xmx\"${RAIN_JAVA_MAX_HEAP:-1g}\" ${RAIN_JAVA_OPTS:-} " +
			"-cp \"$HOME/.rain/toolchain/r8.jar\" com.android.tools.r8.D8 \"$@\"\n",
		"apksigner": "#!/bin/sh\n" +
			"# Lanzador de Rain para apksigner.\n" +
			"exec java -Xmx\"${RAIN_JAVA_MAX_HEAP:-512m}\" ${RAIN_JAVA_OPTS:-} " +
			"-jar \"$HOME/.rain/toolchain/apksigner.jar\" \"$@\"\n",
	}
	if complex {
		// kotlinc ya respeta JAVA_OPTS y JAVA_TOOL_OPTIONS por su cuenta. Aquí
		// solo se traducen las opciones propias de Rain al formato -J que
		// espera el lanzador de Kotlin.
		wrappers["kotlinc"] = "#!/bin/sh\n" +
			"# Lanzador de Rain para kotlinc.\n" +
			"opts=\"\"\n" +
			"for opt in ${RAIN_JAVA_OPTS:-}; do\n" +
			"\topts=\"$opts -J$opt\"\n" +
			"done\n" +
			"if [ -n \"${RAIN_JAVA_MAX_HEAP:-}\" ]; then\n" +
			"\topts=\"$opts -J-Xmx$RAIN_JAVA_MAX_HEAP\"\n" +
			"fi\n" +
			"exec \"$HOME/.rain/toolchain/kotlinc/bin/kotlinc\" $opts \"$@\"\n"
	}

	if err := os.MkdirAll(m.BinDir(), 0o755); err != nil {
		return err
	}

	for name, content := range wrappers {
		path := filepath.Join(m.BinDir(), name)
		if current, err := os.ReadFile(path); err == nil && string(current) == content {
			continue
		}
		if err := os.WriteFile(path, []byte(content), 0o755); err != nil {
			return err
		}
	}
	return nil
}

// writeWindowsWrappers genera lanzadores .cmd: exec.LookPath en Windows
// reconoce PATHEXT, así que "d8" resuelve a "d8.cmd" sin tocar el PATH del
// usuario de forma permanente.
func (m *Manager) writeWindowsWrappers(complex bool) error {
	wrappers := map[string]string{
		"d8.cmd": "@echo off\r\n" +
			"set HEAP=%RAIN_JAVA_MAX_HEAP%\r\n" +
			"if \"%HEAP%\"==\"\" set HEAP=1g\r\n" +
			"java -Xmx%HEAP% %RAIN_JAVA_OPTS% -cp \"%USERPROFILE%\\.rain\\toolchain\\r8.jar\" com.android.tools.r8.D8 %*\r\n",
		"apksigner.cmd": "@echo off\r\n" +
			"set HEAP=%RAIN_JAVA_MAX_HEAP%\r\n" +
			"if \"%HEAP%\"==\"\" set HEAP=512m\r\n" +
			"java -Xmx%HEAP% %RAIN_JAVA_OPTS% -jar \"%USERPROFILE%\\.rain\\toolchain\\apksigner.jar\" %*\r\n",
	}
	if complex {
		wrappers["kotlinc.cmd"] = "@echo off\r\n" +
			"\"%USERPROFILE%\\.rain\\toolchain\\kotlinc\\bin\\kotlinc.bat\" %*\r\n"
	}

	if err := os.MkdirAll(m.BinDir(), 0o755); err != nil {
		return err
	}
	for name, content := range wrappers {
		path := filepath.Join(m.BinDir(), name)
		if current, err := os.ReadFile(path); err == nil && string(current) == content {
			continue
		}
		if err := os.WriteFile(path, []byte(content), 0o755); err != nil {
			return err
		}
	}
	return nil
}

func (m *Manager) download(ctx context.Context, url, dest, label string) error {
	tmp, err := m.downloadTemp(ctx, url, label)
	if err != nil {
		return err
	}
	defer func() { _ = os.Remove(tmp) }()

	if err := os.MkdirAll(filepath.Dir(dest), 0o755); err != nil {
		return err
	}
	return os.Rename(tmp, dest)
}

func (m *Manager) downloadTemp(ctx context.Context, url, label string) (string, error) {
	if m.Out != nil {
		fmt.Fprintf(m.Out, "  ↓ %s\n", label)
	}

	req, err := http.NewRequestWithContext(ctx, http.MethodGet, url, nil)
	if err != nil {
		return "", err
	}
	req.Header.Set("User-Agent", "rain/"+R8Version)

	resp, err := m.Client.Do(req)
	if err != nil {
		return "", err
	}
	defer func() { _ = resp.Body.Close() }()
	if resp.StatusCode != http.StatusOK {
		return "", fmt.Errorf("HTTP %d al descargar %s", resp.StatusCode, url)
	}

	f, err := os.CreateTemp("", "rain-download-*")
	if err != nil {
		return "", err
	}
	path := f.Name()

	if _, err := io.Copy(f, resp.Body); err != nil {
		_ = f.Close()
		_ = os.Remove(path)
		return "", err
	}
	if err := f.Close(); err != nil {
		_ = os.Remove(path)
		return "", err
	}
	return path, nil
}

func extractOne(zipPath string, match func(string) bool, dest string) error {
	return extractOneMode(zipPath, match, dest, 0o644)
}

func extractOneMode(zipPath string, match func(string) bool, dest string, mode os.FileMode) error {
	zr, err := zip.OpenReader(zipPath)
	if err != nil {
		return err
	}
	defer func() { _ = zr.Close() }()

	for _, f := range zr.File {
		if f.FileInfo().IsDir() || !match(f.Name) {
			continue
		}
		return extractZipFile(f, dest, mode)
	}
	return fmt.Errorf("no se encontró %s dentro del ZIP", dest)
}

// extractZipFile copia una entrada ya localizada a dest con el permiso pedido,
// usando un temporal para no dejar un fichero a medias si falla la copia.
func extractZipFile(f *zip.File, dest string, mode os.FileMode) error {
	if err := os.MkdirAll(filepath.Dir(dest), 0o755); err != nil {
		return err
	}
	tmp := dest + ".part"
	if err := copyZipFile(f, tmp, mode); err != nil {
		_ = os.Remove(tmp)
		return err
	}
	return os.Rename(tmp, dest)
}

func extractPrefix(zipPath, prefix, destDir string) error {
	zr, err := zip.OpenReader(zipPath)
	if err != nil {
		return err
	}
	defer func() { _ = zr.Close() }()

	for _, f := range zr.File {
		if !strings.HasPrefix(f.Name, prefix) {
			continue
		}
		rel := strings.TrimPrefix(f.Name, prefix)
		if rel == "" {
			continue
		}
		target, err := safeJoin(destDir, rel)
		if err != nil {
			return err
		}
		if f.FileInfo().IsDir() {
			if err := os.MkdirAll(target, 0o755); err != nil {
				return err
			}
			continue
		}
		if err := os.MkdirAll(filepath.Dir(target), 0o755); err != nil {
			return err
		}
		if f.Mode()&os.ModeSymlink != 0 {
			if err := extractZipSymlink(f, target); err != nil {
				return err
			}
			continue
		}
		mode := f.Mode()
		if mode == 0 {
			mode = 0o644
		}
		if err := copyZipFile(f, target, mode.Perm()); err != nil {
			return err
		}
	}
	return nil
}

// extractZipSymlink conserva los enlaces del NDK. Copiar su contenido como un
// fichero normal rompe las librerías con versiones aliasadas (libc++.so.1...).
func extractZipSymlink(f *zip.File, dest string) error {
	rc, err := f.Open()
	if err != nil {
		return err
	}
	target, err := io.ReadAll(rc)
	_ = rc.Close()
	if err != nil {
		return err
	}
	if err := os.Remove(dest); err != nil && !os.IsNotExist(err) {
		return err
	}
	if err := os.Symlink(string(target), dest); err != nil {
		// Windows sin permisos para symlinks: el contenido sigue siendo útil.
		return os.WriteFile(dest, target, 0o644)
	}
	return nil
}

func copyZipFile(f *zip.File, dest string, mode os.FileMode) error {
	rc, err := f.Open()
	if err != nil {
		return err
	}
	defer func() { _ = rc.Close() }()

	out, err := os.OpenFile(dest, os.O_CREATE|os.O_WRONLY|os.O_TRUNC, mode)
	if err != nil {
		return err
	}
	if _, err := io.Copy(out, rc); err != nil {
		_ = out.Close()
		return err
	}
	return out.Close()
}

func safeJoin(base, rel string) (string, error) {
	target := filepath.Join(base, filepath.FromSlash(rel))
	cleanBase := filepath.Clean(base)
	cleanTarget := filepath.Clean(target)
	if cleanTarget != cleanBase && !strings.HasPrefix(cleanTarget, cleanBase+string(os.PathSeparator)) {
		return "", fmt.Errorf("ruta insegura en ZIP: %s", rel)
	}
	return cleanTarget, nil
}

func fileExists(path string) bool {
	st, err := os.Stat(path)
	return err == nil && !st.IsDir()
}

func dirExists(path string) bool {
	st, err := os.Stat(path)
	return err == nil && st.IsDir()
}
