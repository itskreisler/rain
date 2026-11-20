// Package lint ejecuta analizadores estáticos opcionales (ktlint, detekt y
// pmd) sobre las fuentes de un proyecto.
//
// Se diferencia de "rain check" en qué detecta: el check compila el código y
// por tanto encuentra los errores de tipos (referencias sin resolver, imports
// rotos), que ningún linter de estilo ve. Estas herramientas analizan el
// estilo, la complejidad y los "code smells", que son otra categoría de
// problemas.
//
// Ninguna herramienta se descarga sola: Rain usa las que ya estén instaladas
// (en el PATH o en ~/.rain/toolchain) y avisa de las que falten.
package lint

import (
	"bufio"
	"context"
	"crypto/sha256"
	"encoding/hex"
	"errors"
	"fmt"
	"os"
	"os/exec"
	"path/filepath"
	"regexp"
	"runtime"
	"sort"
	"strconv"
	"strings"

	"github.com/itskreisler/rain/internal/toolchain"
)

// Severity clasifica un hallazgo.
type Severity string

const (
	SeverityInfo    Severity = "info"
	SeverityWarning Severity = "warning"
	SeverityError   Severity = "error"
)

// Finding es un problema reportado por un analizador.
type Finding struct {
	Tool     string
	File     string
	Line     int
	Column   int
	Rule     string
	Severity Severity
	Message  string
}

// String devuelve el hallazgo en formato "archivo:línea:columna: mensaje".
func (f Finding) String() string {
	loc := f.File
	if f.Line > 0 {
		loc = fmt.Sprintf("%s:%d:%d", f.File, f.Line, f.Column)
	}
	return fmt.Sprintf("%s: %s", loc, f.Message)
}

// Options describe el proyecto que se va a analizar.
type Options struct {
	// ProjectDir es la raíz del proyecto: las rutas se muestran relativas.
	ProjectDir string
	// SourceRoot es el directorio con las fuentes (app/src/main/java).
	SourceRoot string
	// KotlinFiles y JavaFiles son las fuentes a analizar.
	KotlinFiles []string
	JavaFiles   []string
	// Classpath se usa para la resolución de tipos (detekt).
	Classpath []string
	// TypeResolution activa la resolución de tipos en detekt. Es más lento y
	// puede fallar con algunas combinaciones de versión, así que va apagada.
	TypeResolution bool
	// PmdRuleset es la regla de PMD que se usa en lugar del quickstart. La
	// resuelve el CLI a una ruta absoluta; vacío significa "la de por defecto".
	PmdRuleset string
	// JavaFormat ajusta google-java-format en "rain fmt".
	JavaFormat JavaFormatOptions
	// Check ejecuta los formateadores sin dejar los cambios: se usa en
	// "rain fmt --check" para validar sin tocar las fuentes.
	Check bool
}

// JavaFormatOptions son las opciones de estilo para fuentes Java.
type JavaFormatOptions struct {
	Style       string
	LineLength  int
	SkipImports bool
}

// Config es la sección [lint] de rain.toml.
type Config struct {
	// Tools lista los analizadores a ejecutar. Vacío o con "auto" detecta los
	// que estén instalados.
	Tools []string `toml:"tools"`
	// FailOn decide si el comando falla: "never", "warning" o "error".
	FailOn string `toml:"fail_on"`
	// TypeResolution pasa el classpath del proyecto a detekt.
	TypeResolution bool `toml:"type_resolution"`
	// PmdRuleset permite usar un ruleset propio en lugar del quickstart.
	// La ruta es relativa a la raíz del proyecto.
	PmdRuleset string `toml:"pmd_ruleset"`
	// PmdBaseline es un fichero con los avisos ya conocidos. Solo se aplica a
	// PMD: un aviso nuevo sigue fallando igual.
	PmdBaseline string `toml:"pmd_baseline"`
}

// FailOnLevel traduce el valor de configuración al nivel de severidad que
// hace fallar el comando.
func (c Config) FailOnLevel() Severity {
	switch strings.ToLower(strings.TrimSpace(c.FailOn)) {
	case "never", "none", "off":
		return ""
	case "error":
		return SeverityError
	default:
		return SeverityWarning
	}
}

// Tool es un analizador estático disponible en el entorno.
type Tool struct {
	// Name es el identificador del analizador ("ktlint", "detekt", "pmd").
	Name string
	// Path es la ruta al ejecutable o al jar.
	Path string
	// Run ejecuta el analizador sobre las fuentes.
	Run func(ctx context.Context, path string, opts Options) ([]Finding, error)
}

// toolNames son los analizadores que Rain sabe ejecutar, en el orden en que
// se ejecutan.
var toolNames = []string{"ktlint", "detekt", "pmd"}

// Available devuelve los analizadores instalados en el entorno, en el orden
// canónico.
func Available() []Tool {
	var out []Tool
	for _, name := range toolNames {
		if t, ok := newTool(name); ok {
			out = append(out, t)
		}
	}
	return out
}

// Select devuelve los analizadorespedidos en cfg. Con "auto" o sin lista
// devuelve los que estén instalados. Si se pide uno que no está instalado,
// devuelve un error con la instrucción de instalarlo.
func Select(cfg Config) ([]Tool, error) {
	available := Available()

	requested := cfg.Tools
	if len(requested) == 0 {
		requested = []string{"auto"}
	}

	var out []Tool
	for _, name := range requested {
		name = strings.ToLower(strings.TrimSpace(name))
		if name == "" {
			continue
		}
		if name == "auto" {
			out = append(out, available...)
			continue
		}
		found := false
		for _, t := range available {
			if t.Name == name {
				out = append(out, t)
				found = true
				break
			}
		}
		if !found {
			return nil, fmt.Errorf("el analizador %q no está instalado (%s)", name, installHint(name))
		}
	}
	return out, nil
}

func installHint(name string) string {
	switch name {
	case "ktlint", "detekt":
		return "instálalo con: rain lint --install"
	case "pmd":
		return "instálalo con: rain lint --install"
	default:
		return " Rain solo conoce ktlint, detekt y pmd"
	}
}

// newTool construye el analizador indicado si está instalado.
func newTool(name string) (Tool, bool) {
	switch name {
	case "ktlint":
		if path, ok := findKtlint(); ok {
			return Tool{Name: "ktlint", Path: path, Run: runKtlint}, true
		}
	case "detekt":
		if path, ok := findDetekt(); ok {
			return Tool{Name: "detekt", Path: path, Run: runDetekt}, true
		}
	case "pmd":
		if path, ok := findPmd(); ok {
			return Tool{Name: "pmd", Path: path, Run: runPMD}, true
		}
	}
	return Tool{}, false
}

// toolchainDir es el directorio del toolchain de Rain (~/.rain/toolchain).
func toolchainDir() string {
	home, err := os.UserHomeDir()
	if err != nil {
		return ""
	}
	return filepath.Join(home, ".rain", "toolchain")
}

func findKtlint() (string, bool) {
	if dir := toolchainDir(); dir != "" {
		jar := filepath.Join(dir, "ktlint-cli.jar")
		if fileExists(jar) {
			return jar, true
		}
	}
	if path, err := exec.LookPath("ktlint"); err == nil {
		return path, true
	}
	return "", false
}

func findDetekt() (string, bool) {
	if dir := toolchainDir(); dir != "" {
		jar := filepath.Join(dir, "detekt-cli.jar")
		if fileExists(jar) {
			return jar, true
		}
	}
	if path, err := exec.LookPath("detekt"); err == nil {
		return path, true
	}
	return "", false
}

// findPmd prefiere la distribución gestionada por Rain para que la sintaxis
// de la CLI coincida con la versión que Rain sabe invocar.
func findPmd() (string, bool) {
	if dir := toolchainDir(); dir != "" {
		name := "pmd"
		if runtime.GOOS == "windows" {
			name = "pmd.bat"
		}
		path := filepath.Join(dir, "pmd-bin-"+toolchain.PmdVersion, "bin", name)
		if fileExists(path) {
			return path, true
		}
	}
	if path, err := exec.LookPath("pmd"); err == nil {
		return path, true
	}
	return "", false
}

// ktlintLine captura el formato del reporter "plain":
//
//	ruta/fichero.kt:12:5: mensaje (regla)
var ktlintLine = regexp.MustCompile(`^(.+?):(\d+):(\d+):\s+(.*?)\s*(?:\(([a-z0-9:.\-]+)\))?$`)

// Format reescribe las fuentes Kotlin (ktlint) y Java (google-java-format) del
// proyecto y devuelve la lista de ficheros que han cambiado.
//
// Cada herramienta corrige lo que sabe sin tocar la lógica: sangría, imports,
// saltos de línea. Lo que no se puede corregir solo sigue viéndose en
// "rain lint". Si un proyecto solo tiene Kotlin no se necesita el
// formateador de Java, y viceversa.
func Format(ctx context.Context, opts Options) ([]string, error) {
	// En modo check se formatea en sitio y se restauran los originales: así
	// ktlint y google-java-format deciden realmente qué cambiaría, sin que el
	// usuario tenga que revertir nada a mano.
	if opts.Check {
		snapshot := snapshotFiles(append(append([]string{}, opts.KotlinFiles...), opts.JavaFiles...))
		defer restoreFiles(snapshot)
	}

	kotlinChanged, err := formatKotlin(ctx, opts)
	if err != nil {
		return nil, err
	}
	javaChanged, err := formatJava(ctx, opts)
	if err != nil {
		return nil, err
	}
	changed := append(kotlinChanged, javaChanged...)
	sort.Strings(changed)
	return changed, nil
}

func formatKotlin(ctx context.Context, opts Options) ([]string, error) {
	if len(opts.KotlinFiles) == 0 {
		return nil, nil
	}
	path, ok := findKtlint()
	if !ok {
		return nil, fmt.Errorf("ktlint no está instalado (instálalo con: rain lint --install)")
	}

	before := sums(opts.KotlinFiles)
	args := []string{"--relative", "--format", "**/*.kt"}
	args = append(args, sourcePatterns(opts)...)
	cmd := toolCommand(ctx, path, args)
	cmd.Dir = opts.ProjectDir
	out, err := cmd.CombinedOutput()
	if err != nil {
		// ktlint sale con 1 cuando encuentra problemas: no es un error.
		if exitCode(err) != 1 {
			return nil, fmt.Errorf("ktlint falló: %w\n%s", err, strings.TrimSpace(string(out)))
		}
	}
	return changedFiles(before, opts.KotlinFiles), nil
}

// findGoogleJavaFormat prefiere el jar gestionado por Rain. La CLI del jar
// necesita --add-exports en JDK 16+, así que conviene usar siempre esa copia.
func findGoogleJavaFormat() (string, bool) {
	if dir := toolchainDir(); dir != "" {
		jar := filepath.Join(dir, "google-java-format.jar")
		if fileExists(jar) {
			return jar, true
		}
	}
	if path, err := exec.LookPath("google-java-format"); err == nil {
		return path, true
	}
	return "", false
}

// javaFormatExports son los módulos internos del JDK que google-java-format
// necesita abrir para analizar sintaxis Java.
var javaFormatExports = []string{
	"--add-exports", "jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED",
	"--add-exports", "jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED",
	"--add-exports", "jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED",
	"--add-exports", "jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED",
	"--add-exports", "jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED",
}

// googleJavaFormatArgs traduce la configuración de rain.toml a flags. El
// estilo por defecto es AOSP (4 espacios) y la columna máxima 120.
func googleJavaFormatArgs(opts Options) []string {
	args := []string{}
	if strings.ToLower(opts.JavaFormat.Style) != "google" {
		args = append(args, "--aosp")
	}
	lineLength := opts.JavaFormat.LineLength
	if lineLength <= 0 {
		lineLength = 120
	}
	args = append(args, "--max-line-length", strconv.Itoa(lineLength))
	if opts.JavaFormat.SkipImports {
		args = append(args, "--skip-sorting-imports", "--skip-removing-unused-imports")
	}
	return append(args, "-i")
}

func googleJavaFormatCommand(ctx context.Context, path string, args []string) *exec.Cmd {
	if strings.HasSuffix(path, ".jar") {
		full := append([]string{}, javaFormatExports...)
		full = append(full, "-jar", path)
		full = append(full, args...)
		return exec.CommandContext(ctx, "java", full...)
	}
	return exec.CommandContext(ctx, path, args...)
}

func formatJava(ctx context.Context, opts Options) ([]string, error) {
	if len(opts.JavaFiles) == 0 {
		return nil, nil
	}
	path, ok := findGoogleJavaFormat()
	if !ok {
		return nil, fmt.Errorf("google-java-format no está instalado (instálalo con: rain lint --install)")
	}

	before := sums(opts.JavaFiles)
	args := googleJavaFormatArgs(opts)
	args = append(args, opts.JavaFiles...)
	cmd := googleJavaFormatCommand(ctx, path, args)
	cmd.Dir = opts.ProjectDir
	out, err := cmd.CombinedOutput()
	if err != nil {
		return nil, fmt.Errorf("google-java-format falló: %w\n%s", err, strings.TrimSpace(string(out)))
	}
	return changedFiles(before, opts.JavaFiles), nil
}

// snapshotFiles guarda el contenido original de los ficheros para --check.
func snapshotFiles(files []string) map[string][]byte {
	snapshot := make(map[string][]byte, len(files))
	for _, file := range files {
		if data, err := os.ReadFile(file); err == nil {
			snapshot[file] = data
		}
	}
	return snapshot
}

// restoreFiles devuelve el contenido original tras un --check.
func restoreFiles(snapshot map[string][]byte) {
	for file, data := range snapshot {
		_ = os.WriteFile(file, data, 0o644)
	}
}

// sums guarda la suma previa de cada fichero para detectar cambios después de
// ejecutar el formateador.
func sums(files []string) map[string]string {
	before := make(map[string]string, len(files))
	for _, file := range files {
		if sum, err := fileSum(file); err == nil {
			before[file] = sum
		}
	}
	return before
}

// changedFiles devuelve los ficheros cuya suma cambió, en orden estable.
func changedFiles(before map[string]string, files []string) []string {
	var changed []string
	for _, file := range files {
		sum, err := fileSum(file)
		if err != nil {
			continue
		}
		if previous, ok := before[file]; !ok || previous != sum {
			changed = append(changed, file)
		}
	}
	sort.Strings(changed)
	return changed
}

// findDetektConfig busca la configuración de detekt del proyecto.
//
// detekt no la busca solo: sin --config usa siempre su configuración por
// defecto, así que un proyecto que quiere ajustar reglas tiene que pasarle
// el fichero.
func findDetektConfig(projectDir string) string {
	if projectDir == "" {
		return ""
	}
	for _, name := range []string{"detekt.yml", "detekt.yaml", ".detekt.yml"} {
		path := filepath.Join(projectDir, name)
		if fileExists(path) {
			return path
		}
	}
	return ""
}

// fileSum devuelve la suma SHA-256 de un fichero, para detectar cambios.
func fileSum(path string) (string, error) {
	data, err := os.ReadFile(path)
	if err != nil {
		return "", err
	}
	sum := sha256.Sum256(data)
	return hex.EncodeToString(sum[:]), nil
}

// runKtlint ejecuta ktlint sobre las fuentes Kotlin.
func runKtlint(ctx context.Context, path string, opts Options) ([]Finding, error) {
	if len(opts.KotlinFiles) == 0 {
		return nil, nil
	}

	toolArgs := []string{"--relative", "**/*.kt"}

	// ktlint acepta patrones como argumentos posicionales; se le pasan las
	// fuentes una a una agrupadas por directorio para no agotar la línea de
	// comandos en proyectos grandes.
	patterns := sourcePatterns(opts)
	toolArgs = append(toolArgs, patterns...)

	cmd := toolCommand(ctx, path, toolArgs)
	cmd.Dir = opts.ProjectDir
	out, err := cmd.CombinedOutput()
	if err != nil {
		// ktlint sale con 1 cuando encuentra problemas: no es un error.
		if exitCode(err) != 1 {
			return nil, fmt.Errorf("ktlint falló: %w\n%s", err, strings.TrimSpace(string(out)))
		}
	}

	return parseKtlint(string(out), opts), nil
}

// sourcePatterns agrupa las fuentes Kotlin por directorio y devuelve los
// patrones que ktlint entiende.
func sourcePatterns(opts Options) []string {
	dirs := map[string]bool{}
	for _, f := range opts.KotlinFiles {
		dirs[filepath.Dir(f)] = true
	}
	names := make([]string, 0, len(dirs))
	for d := range dirs {
		names = append(names, d)
	}
	sort.Strings(names)

	out := make([]string, 0, len(names))
	for _, d := range names {
		out = append(out, filepath.Join(d, "**", "*.kt"))
	}
	return out
}

// parseKtlint convierte la salida de ktlint en hallazgos.
func parseKtlint(output string, opts Options) []Finding {
	var findings []Finding
	for _, line := range strings.Split(output, "\n") {
		line = strings.TrimRight(line, "\r")
		if line == "" {
			continue
		}
		m := ktlintLine.FindStringSubmatch(line)
		if m == nil {
			continue
		}
		findings = append(findings, Finding{
			Tool:     "ktlint",
			File:     relativePath(m[1], opts.ProjectDir),
			Line:     atoiSafe(m[2]),
			Column:   atoiSafe(m[3]),
			Message:  strings.TrimSpace(m[4]),
			Rule:     m[5],
			Severity: SeverityWarning,
		})
	}
	return findings
}

// detektLine captura el formato de la salida por consola de detekt:
//
//	ruta/fichero.kt:12:5: mensaje [Regla]
var detektLine = regexp.MustCompile(`^(.+?):(\d+):(\d+):\s+(.*?)\s*\[([A-Za-z0-9]+)\]$`)

// runDetekt ejecuta detekt sobre las fuentes Kotlin.
func runDetekt(ctx context.Context, path string, opts Options) ([]Finding, error) {
	if len(opts.KotlinFiles) == 0 {
		return nil, nil
	}

	toolArgs := []string{"--input", opts.SourceRoot}

	// detekt solo usa su configuración por defecto si no se le pasa otra. Si
	// el proyecto tiene un detekt.yml, se respeta: es donde se ajustan
	// umbrales y reglas al proyecto (y donde se desactivan las que no
	// encajan con un framework como Compose).
	if config := findDetektConfig(opts.ProjectDir); config != "" {
		toolArgs = append(toolArgs, "--config", config)
	}

	if opts.TypeResolution && len(opts.Classpath) > 0 {
		toolArgs = append(toolArgs, "--classpath", strings.Join(opts.Classpath, string(os.PathListSeparator)))
	}

	cmd := toolCommand(ctx, path, toolArgs)
	cmd.Dir = opts.ProjectDir
	out, err := cmd.CombinedOutput()
	if err != nil {
		// detekt sale con 2 cuando encuentra problemas y con 1 si el
		// análisis falla de verdad.
		if exitCode(err) != 2 {
			return nil, fmt.Errorf("detekt falló: %w\n%s", err, strings.TrimSpace(string(out)))
		}
	}

	return parseDetekt(string(out), opts), nil
}

// parseDetekt convierte la salida de detekt en hallazgos.
func parseDetekt(output string, opts Options) []Finding {
	var findings []Finding
	for _, line := range strings.Split(output, "\n") {
		line = strings.TrimRight(line, "\r")
		if line == "" {
			continue
		}
		m := detektLine.FindStringSubmatch(line)
		if m == nil {
			continue
		}
		findings = append(findings, Finding{
			Tool:     "detekt",
			File:     relativePath(m[1], opts.ProjectDir),
			Line:     atoiSafe(m[2]),
			Column:   atoiSafe(m[3]),
			Message:  strings.TrimSpace(m[4]),
			Rule:     m[5],
			Severity: SeverityWarning,
		})
	}
	return findings
}

// pmdLine captura el formato "text" de PMD:
//
//	ruta/fichero.java:12:	Regla: mensaje
var pmdLine = regexp.MustCompile(`^(.+?):(\d+):\s*(.+?):\s*(.*)$`)

// pmdVersionLine captura el número mayor de `pmd --version`. PMD 7 imprime un
// banner antes, por eso se busca la línea que empieza por "PMD ".
var pmdVersionLine = regexp.MustCompile(`(?m)^PMD (\d+)`)

// parsePmdMajorVersion devuelve 0 si la salida no contiene una versión.
func parsePmdMajorVersion(output string) int {
	m := pmdVersionLine.FindStringSubmatch(output)
	if m == nil {
		return 0
	}
	return atoiSafe(m[1])
}

// pmdMajorVersion detecta la sintaxis de la CLI instalada. Si no se puede
// averiguar se asume PMD 7, que es la versión gestionada por Rain.
func pmdMajorVersion(ctx context.Context, path string) int {
	out, err := exec.CommandContext(ctx, path, "--version").CombinedOutput()
	if err != nil {
		return 7
	}
	if major := parsePmdMajorVersion(string(out)); major > 0 {
		return major
	}
	return 7
}

// pmdArgs construye los argumentos según la versión: PMD 7 usa el subcomando
// "check" y la flag "--aux-classpath"; PMD 6 no los entiende.
func pmdArgs(major int, opts Options) []string {
	ruleset := opts.PmdRuleset
	if ruleset == "" {
		ruleset = "rulesets/java/quickstart.xml"
		if len(opts.JavaFiles) == 0 {
			ruleset = "rulesets/kotlin/quickstart.xml"
		}
	}

	// Se pasan los ficheros recogidos por CollectSources, no el directorio:
	// así PMD no analiza app/build/gen ni otros artefactos generados.
	target := opts.SourceRoot
	if sources := append(append([]string{}, opts.JavaFiles...), opts.KotlinFiles...); len(sources) > 0 {
		target = strings.Join(sources, ",")
	}

	args := []string{}
	if major >= 7 {
		args = append(args, "check")
	}
	args = append(args, "-d", target, "-R", ruleset, "-f", "text", "--no-cache")
	if len(opts.Classpath) > 0 {
		auxFlag := "--auxclasspath"
		if major >= 7 {
			auxFlag = "--aux-classpath"
		}
		args = append(args, auxFlag, strings.Join(opts.Classpath, string(os.PathListSeparator)))
	}
	return args
}

// runPMD ejecuta PMD sobre las fuentes Java y Kotlin del proyecto.
func runPMD(ctx context.Context, path string, opts Options) ([]Finding, error) {
	if len(opts.JavaFiles) == 0 && len(opts.KotlinFiles) == 0 {
		return nil, nil
	}

	args := pmdArgs(pmdMajorVersion(ctx, path), opts)
	cmd := exec.CommandContext(ctx, path, args...)
	cmd.Dir = opts.ProjectDir

	out, err := cmd.CombinedOutput()
	if err != nil {
		if exitCode(err) != 4 {
			return nil, fmt.Errorf("pmd falló: %w\n%s", err, strings.TrimSpace(string(out)))
		}
	}

	return parsePMD(string(out), opts), nil
}

// parsePMD convierte la salida de PMD en hallazgos.
func parsePMD(output string, opts Options) []Finding {
	var findings []Finding
	scanner := bufio.NewScanner(strings.NewReader(output))
	scanner.Buffer(make([]byte, 0, 64*1024), 1024*1024)
	for scanner.Scan() {
		line := strings.TrimRight(scanner.Text(), "\r")
		if line == "" {
			continue
		}
		m := pmdLine.FindStringSubmatch(line)
		if m == nil {
			continue
		}
		findings = append(findings, Finding{
			Tool:     "pmd",
			File:     relativePath(m[1], opts.ProjectDir),
			Line:     atoiSafe(m[2]),
			Rule:     strings.TrimSpace(m[3]),
			Message:  strings.TrimSpace(m[4]),
			Severity: SeverityWarning,
		})
	}
	return findings
}

// BaselineKey identifica un aviso de PMD por fichero y regla, sin la línea:
// así un aviso que se desplaza al editar el archivo sigue considerado conocido.
func BaselineKey(f Finding) string {
	return filepath.ToSlash(f.File) + "|" + f.Rule
}

// LoadBaseline lee un fichero de avisos aceptados. Cada línea es
// "fichero|regla"; las líneas vacías y las que empiezan por # se ignoran.
func LoadBaseline(path string) (map[string]bool, error) {
	if path == "" {
		return nil, nil
	}
	f, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	defer func() { _ = f.Close() }()

	set := map[string]bool{}
	scanner := bufio.NewScanner(f)
	for scanner.Scan() {
		line := strings.TrimSpace(scanner.Text())
		if line == "" || strings.HasPrefix(line, "#") {
			continue
		}
		set[line] = true
	}
	return set, scanner.Err()
}

// ApplyBaseline descarta los avisos de PMD que ya estaban en la línea base.
// Los de ktlint y detekt nunca se silencian: su línea de base no existe.
func ApplyBaseline(findings []Finding, baseline map[string]bool) []Finding {
	if len(baseline) == 0 {
		return findings
	}
	out := make([]Finding, 0, len(findings))
	for _, f := range findings {
		if f.Tool == "pmd" && baseline[BaselineKey(f)] {
			continue
		}
		out = append(out, f)
	}
	return out
}

// CollectSources devuelve las fuentes Kotlin y Java que hay bajo root, sin
// entrar en directorios generados ni en herramientas descargadas.
func CollectSources(root string) (kotlin []string, java []string, err error) {
	if root == "" {
		return nil, nil, nil
	}
	skipped := map[string]bool{
		".git": true, ".rain-deps": true, "build": true, "cache": true,
		"libs": true, "tools": true,
	}
	err = filepath.Walk(root, func(path string, info os.FileInfo, err error) error {
		if err != nil {
			return err
		}
		if info.IsDir() {
			if path != root && skipped[info.Name()] {
				return filepath.SkipDir
			}
			return nil
		}
		switch filepath.Ext(path) {
		case ".kt":
			kotlin = append(kotlin, path)
		case ".java":
			java = append(java, path)
		}
		return nil
	})
	if err != nil && os.IsNotExist(err) {
		return nil, nil, nil
	}
	return kotlin, java, err
}

// toolCommand construye el comando de un analizador. Los jars se ejecutan con
// la JVM del sistema; los binarios nativos se ejecutan directamente.
func toolCommand(ctx context.Context, path string, args []string) *exec.Cmd {
	if strings.HasSuffix(path, ".jar") {
		return exec.CommandContext(ctx, "java", append([]string{"-jar", path}, args...)...)
	}
	return exec.CommandContext(ctx, path, args...)
}

// relativePath muestra la ruta relativa a la raíz del proyecto para que los
// informes sean legibles y reproducibles.
func relativePath(path, projectDir string) string {
	if projectDir == "" {
		return path
	}
	rel, err := filepath.Rel(projectDir, path)
	if err != nil {
		return path
	}
	// Una ruta relativa que sale del proyecto ("../otro/fichero.kt") es peor
	// que la ruta absoluta: se muestra esta última.
	if strings.HasPrefix(rel, "..") {
		return path
	}
	return rel
}

// exitCode devuelve el código de salida del comando, o -1 si el error no
// fue un fallo del proceso (por ejemplo, si no se pudo ejecutarlo).
func exitCode(err error) int {
	var exit *exec.ExitError
	if errors.As(err, &exit) {
		return exit.ExitCode()
	}
	return -1
}

func atoiSafe(s string) int {
	n := 0
	for _, r := range s {
		if r < '0' || r > '9' {
			return 0
		}
		n = n*10 + int(r-'0')
	}
	return n
}

func fileExists(path string) bool {
	st, err := os.Stat(path)
	return err == nil && !st.IsDir()
}
