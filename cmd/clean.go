package cmd

import (
	"os"

	"rain/internal/ui"

	"github.com/spf13/cobra"
)

var cleanCmd = &cobra.Command{
	Use:   "clean",
	Short: "Borra artefactos de build, dependencias y caché",
	Run: func(cmd *cobra.Command, args []string) {
		runClean()
	},
}

func runClean() {
	ui.Info("Limpiando build, libs y cache...")
	os.RemoveAll("app/build")
	os.RemoveAll(DefaultLibsDir)
	os.RemoveAll(DefaultCacheDir)
	ui.Success("Limpieza completada")
}
