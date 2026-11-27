package cli

import (
	"fmt"
	"io/fs"
	"os"
	"path/filepath"
	"regexp"
	"sort"
	"strings"

	"github.com/spf13/cobra"

	"github.com/itskreisler/rain/internal/config"
	"github.com/itskreisler/rain/internal/lint"
	"github.com/itskreisler/rain/internal/ui"
)

// newFmtCmd crea "rain fmt": formatea el código Kotlin y Java del proyecto.
//
// Es la parte de "rain lint" que se puede arreglar sola: ktlint y
// google-java-format reescriben los ficheros con el estilo del proyecto y
// dejan el código igual sin tocar cada coma a mano.
func newFmtCmd() *cobra.Command {
	devlog("newFmtCmd", "", "Crea el subcomando fmt para formatear el código del proyecto")
	var check bool
	cmd := &cobra.Command{
		Use:   "fmt [dir]",
		Short: "Formatea el código Kotlin y Java del proyecto",
		Long: "Formatea Kotlin con ktlint y Java con google-java-format.\n\n" +
			"Reescribe los ficheros en sitio con el estilo que marque el proyecto\n" +
			"(Kotlin lee .editorconfig; Java usa [format] en rain.toml) y avisa\n" +
			"de los ficheros que ha tocado. Lo que no saben corregir (nombres,\n" +
			"complejidad, \"code smells\") se sigue viendo con \"rain lint\".\n\n" +
			"Necesita las herramientas correspondientes: instálalas con\n" +
			"\"rain lint --install\".\n\n" +
			"Con --check no escribe nada: falla si algún fichero necesita formato.",
		Args: cobra.MaximumNArgs(1),
		RunE: func(cmd *cobra.Command, args []string) error {
			dir := "."
			if len(args) == 1 {
				dir = args[0]
			}
			return runFmt(cmd, dir, check)
		},
	}
	cmd.Flags().BoolVar(&check, "check", false, "no escribe: falla si algún fichero necesita formato")

	return cmd
}

// runFmt formatea el proyecto indicado.
func runFmt(cmd *cobra.Command, dir string, check bool) error {
	devlog("runFmt", fmt.Sprintf("cmd=%s, dir=%s, check=%t", cmd.Name(), dir, check), "Formatea el código Kotlin y Java de los módulos del proyecto")
	absDir, err := filepath.Abs(dir)
	if err != nil {
		return fmt.Errorf("resolviendo %s: %w", dir, err)
	}
	if st, err := os.Stat(absDir); err != nil || !st.IsDir() {
		return usageErr("%s no es un directorio válido", dir)
	}

	cfg := config.Config{}
	if _, err := os.Stat(filepath.Join(absDir, "rain.toml")); err == nil {
		if c, err := config.Read(filepath.Join(absDir, "rain.toml")); err == nil {
			cfg = c
		} else {
			ui.VerboseLog("rain.toml ilegible: se usan los valores por defecto")
		}
	}

	targets, err := lintTargets(absDir, cfg)
	if err != nil {
		return err
	}
	if len(targets) == 0 {
		ui.Warn("no se encontraron fuentes que formatear")
		return nil
	}

	skipImports := true
	if cfg.Format.JavaSkipImports != nil {
		skipImports = *cfg.Format.JavaSkipImports
	}
	javaFormat := lint.JavaFormatOptions{
		Style:       cfg.Format.JavaStyle,
		LineLength:  cfg.Format.JavaLineLength,
		SkipImports: skipImports,
	}

	// Los globs se evalúan desde la raíz del proyecto: recorrer las fuentes
	// es igual para todos los targets, así que se hace una sola vez.
	var allJava []string
	if javaFilesConGlobs(cfg.Format.JavaFiles, cfg.Format.JavaExclude) {
		allJava = collectJavaFiles(absDir)
	}

	var changed []string
	for _, target := range targets {
		files, err := lint.Format(cmd.Context(), lint.Options{
			ProjectDir:  target.projectDir,
			SourceRoot:  target.sourceRoot,
			KotlinFiles: target.kotlin,
			JavaFiles:   formatJavaFilesConCandidatos(absDir, cfg.Format.JavaFiles, cfg.Format.JavaExclude, target.java, allJava),
			JavaFormat:  javaFormat,
			Check:       check,
		})
		if err != nil {
			return err
		}
		for _, file := range files {
			changed = append(changed, relativeToProject(file, target.projectDir))
		}
	}

	if len(changed) == 0 {
		ui.Success("el código ya está formateado")
		return nil
	}
	for _, file := range changed {
		fmt.Fprintln(cmd.OutOrStdout(), file)
	}
	if check {
		return &exitError{code: 1, err: fmt.Errorf("%d ficheros necesitan formato", len(changed))}
	}
	ui.Success("%d ficheros formateados", len(changed))
	return nil
}

// formatJavaFiles permite acotar "rain fmt" con rutas o patrones glob
// relativos a la raíz del proyecto. Un patrón admite * (sin cruzar /), ?
// y ** (cruza directorios). Los patrones que empiezan por ! excluyen.
func formatJavaFiles(absDir string, configured, excluded, found []string) []string {
	devlog("formatJavaFiles", fmt.Sprintf("absDir=%s, configured=%v, excluded=%v", absDir, configured, excluded), "Selecciona los ficheros Java que debe tocar rain fmt")
	return formatJavaFilesConCandidatos(absDir, configured, excluded, found)
}

// javaFilesConGlobs indica si la selección necesita recorrer el proyecto. Las
// exclusiones escritas dentro de configured no activan el paseo: solo lo hacen
// los patrones de inclusión y los de JavaExclude.
func javaFilesConGlobs(configured, excluded []string) bool {
	devlog("javaFilesConGlobs", fmt.Sprintf("configured=%v, excluded=%v", configured, excluded), "Indica si hace falta recorrer el proyecto para seleccionar Java")
	if len(configured) == 0 {
		return len(excluded) > 0 // se usa el patrón implícito **/*.java
	}
	for _, pattern := range configured {
		if strings.HasPrefix(pattern, "!") {
			continue
		}
		if strings.ContainsAny(pattern, "*?") {
			return true
		}
	}
	for _, pattern := range excluded {
		if strings.ContainsAny(pattern, "*?") {
			return true
		}
	}
	return false
}

// formatJavaFilesConCandidatos aplica la misma selección que formatJavaFiles,
// reutilizando los candidatos ya recogidos cuando se le pasan.
func formatJavaFilesConCandidatos(absDir string, configured, excluded, found []string, candidatos ...[]string) []string {
	devlog("formatJavaFilesConCandidatos", fmt.Sprintf("absDir=%s, configured=%v, excluded=%v", absDir, configured, excluded), "Selecciona los ficheros Java reutilizando el paseo del proyecto")
	if len(configured) == 0 && len(excluded) == 0 {
		return found
	}

	var include []string
	exclude := append([]string{}, excluded...)
	if len(configured) == 0 {
		include = []string{"**/*.java"}
	}
	for _, pattern := range configured {
		if strings.HasPrefix(pattern, "!") {
			exclude = append(exclude, strings.TrimPrefix(pattern, "!"))
			continue
		}
		include = append(include, pattern)
	}

	candidates := found
	if javaFilesConGlobs(configured, excluded) {
		if len(candidatos) > 0 {
			candidates = candidatos[0]
		} else {
			candidates = collectJavaFiles(absDir)
		}
	}
	// Las rutas exactas pueden estar fuera del source root (por ejemplo, un
	// test en test/java), así que se añaden aunque no estuvieran en found.
	for _, pattern := range include {
		if strings.ContainsAny(pattern, "*?") {
			continue
		}
		path := pattern
		if !filepath.IsAbs(path) {
			path = filepath.Join(absDir, filepath.FromSlash(pattern))
		}
		if st, err := os.Stat(path); err == nil && !st.IsDir() {
			candidates = append(candidates, path)
		}
	}

	// Las expresiones se compilan una sola vez por selección, no por fichero.
	includeGlobs := compileGlobs(include)
	excludeGlobs := compileGlobs(exclude)

	out := make([]string, 0, len(candidates))
	seen := map[string]bool{}
	for _, file := range candidates {
		clean := filepath.Clean(file)
		if seen[clean] {
			continue
		}
		seen[clean] = true
		rel, err := filepath.Rel(absDir, file)
		if err != nil {
			continue
		}
		rel = filepath.ToSlash(rel)
		if !matchesAnyCompiled(rel, includeGlobs) || matchesAnyCompiled(rel, excludeGlobs) {
			continue
		}
		out = append(out, file)
	}
	sort.Strings(out)
	return out
}

// collectJavaFiles recorre el proyecto buscando fuentes Java, sin entrar en
// directorios generados por Rain ni en herramientas descargadas.
func collectJavaFiles(absDir string) []string {
	devlog("collectJavaFiles", fmt.Sprintf("absDir=%s", absDir), "Busca fuentes Java para aplicar patrones glob")
	skipped := map[string]bool{
		".git": true, ".rain-deps": true, "build": true, "cache": true,
		"libs": true, "tools": true,
	}
	var out []string
	_ = filepath.WalkDir(absDir, func(path string, entry fs.DirEntry, err error) error {
		if err != nil {
			// Un fichero ilegible no debe abortar la búsqueda de fuentes.
			return nil //nolint:nilerr // se ignora a propósito: el resultado del paseo es mejor esfuerzo
		}
		if entry.IsDir() {
			if path != absDir && skipped[entry.Name()] {
				return filepath.SkipDir
			}
			return nil
		}
		if strings.EqualFold(filepath.Ext(path), ".java") {
			out = append(out, path)
		}
		return nil
	})
	return out
}

// compileGlobs compila los patrones una sola vez para reutilizarlos en todos
// los ficheros. Un patrón inválido se descarta, como hacía globMatch.
func compileGlobs(patterns []string) []*regexp.Regexp {
	devlog("compileGlobs", fmt.Sprintf("patterns=%v", patterns), "Compila los patrones glob una sola vez por selección")
	compiled := make([]*regexp.Regexp, 0, len(patterns))
	for _, pattern := range patterns {
		re, err := regexp.Compile(globExpr(filepath.ToSlash(pattern)))
		if err != nil {
			continue
		}
		compiled = append(compiled, re)
	}
	return compiled
}

// matchesAnyCompiled comprueba la ruta contra patrones ya compilados.
func matchesAnyCompiled(rel string, patterns []*regexp.Regexp) bool {
	devlog("matchesAnyCompiled", fmt.Sprintf("rel=%s, patrones=%d", rel, len(patterns)), "Comprueba si una ruta casa con algún patrón compilado")
	for _, re := range patterns {
		if re.MatchString(rel) {
			return true
		}
	}
	return false
}

// globExpr convierte un patrón sencillo en expresión regular. A diferencia de
// filepath.Glob, ** sí cruza barras, así que funciona con app/java/**/*.java.
func globExpr(pattern string) string {
	devlog("globExpr", fmt.Sprintf("pattern=%s", pattern), "Convierte un patrón glob en su expresión regular")
	var expr strings.Builder
	expr.WriteString("^")
	for i := 0; i < len(pattern); i++ {
		switch pattern[i] {
		case '*':
			if i+1 < len(pattern) && pattern[i+1] == '*' {
				// "**/" también debe casar con cero directorios: así
				// app/java/**/*.java incluye app/java/Main.java.
				if i+2 < len(pattern) && pattern[i+2] == '/' {
					expr.WriteString("(?:.*/)?")
					i += 2
				} else {
					expr.WriteString(".*")
					i++
				}
			} else {
				expr.WriteString("[^/]*")
			}
		case '?':
			expr.WriteString("[^/]")
		case '.', '+', '(', ')', '|', '^', '$', '{', '}', '[', ']', '\\':
			expr.WriteString("\\")
			expr.WriteByte(pattern[i])
		default:
			expr.WriteByte(pattern[i])
		}
	}
	expr.WriteString("$")
	return expr.String()
}

// globMatch es el envoltorio delgado que usan los tests de patrones.
func globMatch(pattern, name string) bool {
	devlog("globMatch", fmt.Sprintf("pattern=%s, name=%s", pattern, name), "Convierte un glob sencillo en expresión regular")
	re, err := regexp.Compile(globExpr(pattern))
	return err == nil && re.MatchString(name)
}

// relativeToProject muestra la ruta de un fichero relativa a la raíz del
// proyecto.
func relativeToProject(path, projectDir string) string {
	if projectDir == "" {
		return path
	}
	if rel, err := filepath.Rel(projectDir, path); err == nil {
		return rel
	}
	return path
}
