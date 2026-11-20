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

	var changed []string
	for _, target := range targets {
		files, err := lint.Format(cmd.Context(), lint.Options{
			ProjectDir:  target.projectDir,
			SourceRoot:  target.sourceRoot,
			KotlinFiles: target.kotlin,
			JavaFiles:   formatJavaFiles(absDir, cfg.Format.JavaFiles, cfg.Format.JavaExclude, target.java),
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
	if len(configured) == 0 && len(excluded) == 0 {
		return found
	}

	var include []string
	exclude := append([]string{}, excluded...)
	hasGlob := false
	if len(configured) == 0 {
		include = []string{"**/*.java"}
		hasGlob = true
	}
	for _, pattern := range configured {
		if strings.HasPrefix(pattern, "!") {
			exclude = append(exclude, strings.TrimPrefix(pattern, "!"))
			continue
		}
		include = append(include, pattern)
		if strings.ContainsAny(pattern, "*?") {
			hasGlob = true
		}
	}
	for _, pattern := range excluded {
		if strings.ContainsAny(pattern, "*?") {
			hasGlob = true
		}
	}

	candidates := found
	if hasGlob {
		candidates = collectJavaFiles(absDir)
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
		if !matchesAny(rel, include) || matchesAny(rel, exclude) {
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

func matchesAny(rel string, patterns []string) bool {
	devlog("matchesAny", fmt.Sprintf("rel=%s, patterns=%v", rel, patterns), "Comprueba si una ruta casa con alguno de los patrones")
	for _, pattern := range patterns {
		if globMatch(filepath.ToSlash(pattern), rel) {
			return true
		}
	}
	return false
}

// globMatch convierte un patrón sencillo en expresión regular. A diferencia de
// filepath.Glob, ** sí cruza barras, así que funciona con app/java/**/*.java.
func globMatch(pattern, name string) bool {
	devlog("globMatch", fmt.Sprintf("pattern=%s, name=%s", pattern, name), "Convierte un glob sencillo en expresión regular")
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
	ok, err := regexp.MatchString(expr.String(), name)
	return err == nil && ok
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
