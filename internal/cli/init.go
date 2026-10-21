package cli

import (
	"bufio"
	"fmt"
	"os"
	"strings"

	"github.com/spf13/cobra"

	"github.com/itskreisler/rain/internal/config"
	"github.com/itskreisler/rain/internal/ui"
)

func newInitCmd() *cobra.Command {
	var (
		mode           string
		name           string
		pkg            string
		version        string
		nonInteractive bool
		force          bool
	)

	cmd := &cobra.Command{
		Use:   "init",
		Short: "Crea un rain.toml en el directorio actual",
		Args:  cobra.NoArgs,
		RunE: func(cmd *cobra.Command, args []string) error {
			if _, err := os.Stat("rain.toml"); err == nil && !force {
				ui.Warn("rain.toml ya existe. No se sobrescribe (usa --force para reemplazarlo).")
				return nil
			}

			if mode == "" {
				if nonInteractive {
					mode = "simple"
				} else {
					mode = promptMode(cmd)
				}
			}
			if mode != "simple" && mode != "completo" {
				return usageErr("modo inválido %q: usa simple o completo", mode)
			}

			if name == "" {
				name = "MiApp"
			}
			if pkg == "" {
				pkg = "com.rain.miapp"
			}
			if version == "" {
				version = "1.0"
			}

			cfg := config.Config{
				Project:      config.Project{Name: name, Package: pkg, Version: version},
				Build:        config.Build{Debug: true},
				Mode:         mode,
				Dependencies: []string{},
			}
			if err := config.Write("rain.toml", cfg); err != nil {
				return fmt.Errorf("escribiendo rain.toml: %w", err)
			}

			ui.Success("rain.toml creado (modo: %s, paquete: %s)", mode, pkg)
			return nil
		},
	}

	cmd.Flags().StringVar(&mode, "mode", "", "modo de dependencias: simple|completo")
	cmd.Flags().StringVar(&name, "name", "", "nombre del proyecto (por defecto MiApp)")
	cmd.Flags().StringVar(&pkg, "package", "", "package id (por defecto com.rain.miapp)")
	cmd.Flags().StringVar(&version, "version", "", "versión del proyecto (por defecto 1.0)")
	cmd.Flags().BoolVar(&nonInteractive, "non-interactive", false, "no preguntar; usar valores por defecto")
	cmd.Flags().BoolVar(&force, "force", false, "sobrescribir rain.toml si existe")

	return cmd
}

func promptMode(cmd *cobra.Command) string {
	out := cmd.OutOrStdout()
	fmt.Fprintln(out, "Rain init — ¿modo de dependencias?")
	fmt.Fprintln(out, "  1) simple   — AAR/JAR explícitos, sin transitivas")
	fmt.Fprintln(out, "  2) completo — resuelve POMs, parents, dependencyManagement")
	fmt.Fprint(out, "Elige [1/2]: ")

	reader := bufio.NewReader(cmd.InOrStdin())
	line, _ := reader.ReadString('\n')
	choice := strings.TrimSpace(line)
	if choice == "2" {
		return "completo"
	}
	return "simple"
}
