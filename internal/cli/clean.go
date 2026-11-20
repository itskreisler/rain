package cli

import (
	"fmt"
	"os"
	"path/filepath"

	"github.com/spf13/cobra"

	"github.com/itskreisler/rain/internal/config"
	"github.com/itskreisler/rain/internal/ui"
)

func newCleanCmd() *cobra.Command {
	devlog("newCleanCmd", "", "Crea el subcomando clean para eliminar artefactos y cachés de compilación")
	return &cobra.Command{
		Use:   "clean",
		Short: "Borra artefactos de build, dependencias y caché",
		Long: "Borra los artefactos generados por los builds: libs/, cache/ y el\n" +
			"build/ de cada módulo del rain.toml (app/build por defecto).\n\n" +
			"No toca .rain-deps/: las dependencias ya preparadas se reutilizan\n" +
			"en el siguiente build. En un layout plano tampoco borra el build/\n" +
			"de la raíz.",
		Args: cobra.NoArgs,
		RunE: func(cmd *cobra.Command, args []string) error {
			modules := []config.Module{{Name: "app", Path: "app"}}
			if cfg, err := config.Read("rain.toml"); err == nil && len(cfg.Modules) > 0 {
				modules = cfg.Modules
			}

			dirs := []string{"libs", "cache"}
			for _, mod := range modules {
				dirs = append(dirs, filepath.Join(mod.Path, "build"))
			}

			ui.Info("Limpiando artefactos...")
			for _, dir := range dirs {
				if err := os.RemoveAll(dir); err != nil {
					return fmt.Errorf("borrando %s: %w", dir, err)
				}
			}
			ui.Success("Limpieza completada")
			return nil
		},
	}
}
