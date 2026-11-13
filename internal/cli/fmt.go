package cli

import (
	"fmt"
	"os"
	"path/filepath"

	"github.com/spf13/cobra"

	"github.com/itskreisler/rain/internal/config"
	"github.com/itskreisler/rain/internal/lint"
	"github.com/itskreisler/rain/internal/ui"
)

// newFmtCmd crea "rain fmt": formatea el código Kotlin del proyecto.
//
// Es la parte de "rain lint" que se puede arreglar sola: ktlint reescribe los
// ficheros con el estilo que marque el proyecto (el .editorconfig manda) y
// deja el código igual sin tocar cada coma a mano.
func newFmtCmd() *cobra.Command {
	devlog("newFmtCmd", "", "Crea el subcomando fmt para formatear el código del proyecto")
	cmd := &cobra.Command{
		Use:   "fmt [dir]",
		Short: "Formatea el código Kotlin del proyecto",
		Long: "Formatea el código Kotlin con ktlint.\n\n" +
			"Reescribe los ficheros en sitio con el estilo que marque el proyecto\n" +
			"(el .editorconfig manda) y avisa de los ficheros que ha tocado. Lo\n" +
			"que ktlint no sabe corregir (nombres, complejidad, \"code smells\")\n" +
			"se sigue viendo con \"rain lint\".\n\n" +
			"Necesita ktlint: instálalo con \"rain lint --install\".",
		Args: cobra.MaximumNArgs(1),
		RunE: func(cmd *cobra.Command, args []string) error {
			dir := "."
			if len(args) == 1 {
				dir = args[0]
			}
			return runFmt(cmd, dir)
		},
	}

	return cmd
}

// runFmt formatea el proyecto indicado.
func runFmt(cmd *cobra.Command, dir string) error {
	devlog("runFmt", fmt.Sprintf("cmd=%s, dir=%s", cmd.Name(), dir), "Formatea el código Kotlin de los módulos del proyecto")
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
		ui.Warn("no se encontraron fuentes Kotlin que formatear")
		return nil
	}

	var changed []string
	for _, target := range targets {
		files, err := lint.Format(cmd.Context(), lint.Options{
			ProjectDir:  target.projectDir,
			SourceRoot:  target.sourceRoot,
			KotlinFiles: target.kotlin,
			JavaFiles:   target.java,
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
	ui.Success("%d ficheros formateados", len(changed))
	return nil
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
