package cmd

import (
	"fmt"
	"os"

	"rain/internal/config"
	"rain/internal/ui"

	"github.com/spf13/cobra"
)

var initCmd = &cobra.Command{
	Use:   "init",
	Short: "Inicializa un archivo rain.toml interactivo en el proyecto actual",
	Run: func(cmd *cobra.Command, args []string) {
		runInit()
	},
}

func runInit() {
	if _, err := os.Stat(DefaultConfigFileName); err == nil {
		ui.Warn("%s ya existe. No se sobrescribe.", DefaultConfigFileName)
		return
	}

	fmt.Println("Rain init — ¿modo de dependencias?")
	fmt.Println("  1) simple   — AAR/JAR explícitos, sin transitivas")
	fmt.Println("  2) completo — resuelve POMs, parents, dependencyManagement")
	fmt.Print("Elige [1/2]: ")

	var choice string
	fmt.Scanln(&choice)

	mode := ModeSimple
	if choice == "2" {
		mode = ModeCompleto
	}

	cfg := config.Config{
		Project:      config.Project{Name: DefaultAppName, Package: DefaultPackage, Version: DefaultVersion},
		Build:        config.Build{Debug: true},
		Mode:         mode,
		Dependencies: []string{},
	}

	if err := config.Write(DefaultConfigFileName, cfg); err != nil {
		ui.Error("Error escribiendo %s: %v", DefaultConfigFileName, err)
		os.Exit(1)
	}
	ui.Success("%s creado (modo: %s)", DefaultConfigFileName, mode)
}
