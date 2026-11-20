package cli

import (
	"context"
	"encoding/json"
	"fmt"
	"strings"

	"github.com/spf13/cobra"

	"github.com/itskreisler/rain/internal/compat"
	"github.com/itskreisler/rain/internal/doctor"
	"github.com/itskreisler/rain/internal/toolchain"
	"github.com/itskreisler/rain/internal/ui"
)

func newDoctorCmd() *cobra.Command {
	devlog("newDoctorCmd", "", "Crea el subcomando doctor para diagnosticar el entorno de desarrollo")
	var (
		profile      string
		jsonOut      bool
		printInstall bool
		fix          bool
		lintTools    bool
		install      bool
		assumeYes    bool
		dryRun       bool
	)

	cmd := &cobra.Command{
		Use:   "doctor",
		Short: "Diagnostica el entorno de compilación Android",
		Long: "Comprueba Java, aapt2, d8, apksigner, android.jar y, en el perfil\n" +
			"complejo, Kotlin/Compose. Muestra los comandos necesarios para\n" +
			"completar la instalación.\n\n" +
			"Perfiles:\n" +
			"  simple   Java + recursos, sin Kotlin ni AndroidX\n" +
			"  complex  Kotlin + Compose + AndroidX (R8 y Kotlin modernos)\n\n" +
			"--install descarga primero el toolchain gestionado y después intenta\n" +
			"instalar las dependencias del sistema con el gestor detectado (apt,\n" +
			"apk, pkg, winget o choco). Pide confirmación salvo con --yes.\n\n" +
			"ktlint y detekt aparecen como opcionales: no hacen falta para\n" +
			"compilar, pero " + "\"rain lint\"" + " los usa si están instalados.",
		Args: cobra.NoArgs,
		RunE: func(cmd *cobra.Command, args []string) error {
			p, err := doctor.ParseProfile(profile)
			if err != nil {
				return usageErr("%v", err)
			}
			devlog("doctor.RunE", fmt.Sprintf("profile=%s, fix=%t, lint=%t, install=%t, yes=%t, dryRun=%t, json=%t",
				p, fix, lintTools, install, assumeYes, dryRun, jsonOut), "Ejecuta el diagnóstico del entorno")

			if install {
				if err := runDoctorInstall(cmd.Context(), p, cmd, dryRun, assumeYes); err != nil {
					return err
				}
			} else {
				if fix {
					if err := runDoctorFix(cmd, p); err != nil {
						return err
					}
				}
				if lintTools {
					if err := runDoctorFixLint(cmd); err != nil {
						return err
					}
				}
			}

			report := doctor.Diagnose(p)

			if jsonOut {
				enc := json.NewEncoder(cmd.OutOrStdout())
				enc.SetIndent("", "  ")
				if err := enc.Encode(report); err != nil {
					return fmt.Errorf("codificando JSON: %w", err)
				}
			} else {
				printDoctorReport(cmd, report, printInstall)
			}

			if !report.Ready {
				return &exitError{
					code: 3,
					err:  fmt.Errorf("faltan dependencias para el perfil %s: %s", p, strings.Join(report.Missing, ", ")),
				}
			}
			return nil
		},
	}

	cmd.Flags().StringVar(&profile, "profile", "simple", "perfil a comprobar: simple|complex")
	cmd.Flags().BoolVar(&jsonOut, "json", false, "salida en JSON")
	cmd.Flags().BoolVar(&printInstall, "print-install", false, "mostrar solo los comandos de instalación")
	cmd.Flags().BoolVar(&fix, "fix", false, "descargar el toolchain propio en ~/.rain (sin root)")
	cmd.Flags().BoolVar(&lintTools, "lint", false, "con --fix, descargar también los analizadores estáticos (ktlint, detekt)")
	cmd.Flags().BoolVar(&install, "install", false, "instalar automáticamente lo que falte (toolchain + paquetes del sistema)")
	cmd.Flags().BoolVar(&assumeYes, "yes", false, "con --install, no pedir confirmación")
	cmd.Flags().BoolVar(&dryRun, "dry-run", false, "con --install, mostrar qué haría sin ejecutarlo")

	return cmd
}

// runDoctorInstall prepara el toolchain gestionado y, si aún faltan
// dependencias del sistema, ejecuta los comandos concretos de cada herramienta.
func runDoctorInstall(ctx context.Context, profile doctor.Profile, cmd *cobra.Command, dryRun, yes bool) error {
	devlog("runDoctorInstall", fmt.Sprintf("profile=%s, dryRun=%t, yes=%t", profile, dryRun, yes), "Instala el toolchain gestionado y las dependencias del sistema que falten")

	if dryRun {
		preview := doctor.Diagnose(profile)
		_, err := doctor.InstallMissing(ctx, preview, doctor.InstallOptions{
			DryRun: true, Out: cmd.OutOrStdout(),
		})
		return err
	}

	if err := runDoctorFix(cmd, profile); err != nil {
		return err
	}
	if err := runDoctorFixLint(cmd); err != nil {
		return fmt.Errorf("descargando analizadores estáticos: %w", err)
	}

	remaining := doctor.DiagnoseContext(ctx, profile)
	_, err := doctor.InstallMissing(ctx, remaining, doctor.InstallOptions{
		AssumeYes: yes,
		Confirm: func(command string) bool {
			_, _ = fmt.Fprintf(cmd.OutOrStdout(), "¿Ejecutar %q? [s/N] ", command)
			var answer string
			if _, scanErr := fmt.Fscanln(cmd.InOrStdin(), &answer); scanErr != nil {
				return false
			}
			answer = strings.ToLower(strings.TrimSpace(answer))
			return answer == "s" || answer == "si" || answer == "y" || answer == "yes"
		},
		Out: cmd.OutOrStdout(),
	})
	return err
}

func runDoctorFixLint(cmd *cobra.Command) error {
	devlog("runDoctorFixLint", fmt.Sprintf("cmd=%s", cmd.Name()), "Descarga los analizadores estáticos opcionales para doctor")
	manager, err := toolchain.New()
	if err != nil {
		return err
	}
	manager.Out = cmd.OutOrStdout()

	ui.Info("Descargando analizadores estáticos (ktlint, detekt)...")
	if err := manager.EnsureLint(cmd.Context()); err != nil {
		return fmt.Errorf("descargando analizadores estáticos: %w", err)
	}
	ui.Success("Analizadores estáticos listos")
	return nil
}

func runDoctorFix(cmd *cobra.Command, profile doctor.Profile) error {
	devlog("runDoctorFix", fmt.Sprintf("cmd=%s, profile=%s", cmd.Name(), profile), "Prepara y descarga el toolchain en ~/.rain")
	manager, err := toolchain.New()
	if err != nil {
		return err
	}
	manager.Out = cmd.OutOrStdout()

	ui.Info("Preparando toolchain en %s...", manager.Root)
	if err := manager.Ensure(cmd.Context(), profile == doctor.Complex); err != nil {
		return fmt.Errorf("preparando toolchain: %w", err)
	}
	ui.Success("Toolchain descargado")
	return nil
}

func printDoctorReport(cmd *cobra.Command, report doctor.Report, onlyInstall bool) {
	devlog("printDoctorReport", fmt.Sprintf("cmd=%s, checks=%d, perfil=%s, listo=%t", cmd.Name(), len(report.Checks), report.Profile, report.Ready), "Imprime en consola el reporte del diagnóstico de doctor")
	out := cmd.OutOrStdout()

	// Auxiliares de render: se definen aquí para no llenar la tabla de líneas
	// DEVLOG con una entrada por herramienta.
	statusText := func(check doctor.Check) string {
		switch check.Status {
		case doctor.StatusOK:
			return "instalado"
		case doctor.StatusBroken:
			return "no se encuentra"
		case doctor.StatusWarning:
			return "advertencia"
		case doctor.StatusMissing:
			if !check.Required {
				return "opcional"
			}
			return "no instalado"
		default:
			return "desconocido"
		}
	}
	paint := func(code, text string) string {
		if ui.NoColor {
			return text
		}
		return code + text + ui.Reset
	}
	widthOf := func(value func(doctor.Check) string) int {
		width := 0
		for _, check := range report.Checks {
			if n := len(value(check)); n > width {
				width = n
			}
		}
		return width
	}

	if !onlyInstall {
		fmt.Fprintf(out, "Rain doctor — perfil: %s\n", report.Profile)
		fmt.Fprintf(out, "Plataforma: %s (%s/%s)\n\n", report.Platform, report.OS, report.Arch)

		nameW := widthOf(func(c doctor.Check) string { return c.Name })
		statusW := widthOf(statusText)
		if nameW < len("HERRAMIENTA") {
			nameW = len("HERRAMIENTA")
		}
		if statusW < len("ESTADO") {
			statusW = len("ESTADO")
		}
		versionW := len("VERSIÓN")
		for _, check := range report.Checks {
			version := check.Version
			if version == "" {
				version = "—"
			}
			if len(version) > versionW {
				versionW = len(version)
			}
		}

		fmt.Fprintf(out, "%-*s  %-*s  %-*s  %s\n", nameW, "HERRAMIENTA", statusW, "ESTADO", versionW, "VERSIÓN", "DETALLE / INSTALACIÓN")
		fmt.Fprintf(out, "%s  %s  %s  %s\n", strings.Repeat("─", nameW), strings.Repeat("─", statusW), strings.Repeat("─", versionW), strings.Repeat("─", 20))

		for _, check := range report.Checks {
			version := check.Version
			if version == "" {
				version = "—"
			}
			detail := check.Detail
			if check.OK && check.Path != "" {
				detail = check.Path
			}
			if check.Path != "" && !check.OK && detail != "" {
				detail += " (" + check.Path + ")"
			}

			text := statusText(check)
			padded := fmt.Sprintf("%-*s", statusW, text)
			code := ui.Green
			if !check.OK {
				code = ui.Red
				if check.Status == doctor.StatusWarning {
					code = ui.Yellow
				}
			}
			padded = paint(code, text) + padded[len(text):]

			fmt.Fprintf(out, "%-*s  %s  %-*s  %s\n", nameW, check.Name, padded, versionW, version, detail)
			if !check.OK && check.Install != "" {
				fmt.Fprintf(out, "%s  → %s\n", strings.Repeat(" ", nameW+2+statusW+2+versionW+2), check.Install)
			}
		}

		fmt.Fprintln(out)
		if len(report.Compat) > 0 {
			fmt.Fprintln(out, "Compatibilidad del toolchain:")
			for _, issue := range report.Compat {
				symbol := "ℹ️ "
				switch issue.Severity {
				case compat.Error:
					symbol = "❌"
				case compat.Warning:
					symbol = "⚠️ "
				}
				fmt.Fprintf(out, "%s %s\n", symbol, issue.Message)
			}
			fmt.Fprintln(out)
		}
		if report.Ready {
			ui.Success("Todo listo para el perfil %s", report.Profile)
			if report.Profile == doctor.Simple {
				fmt.Fprintln(out, "\nPara apps complejas (Kotlin/Compose): rain doctor --profile complex")
			}
		} else {
			fmt.Fprintf(out, "Faltan: %s\n", strings.Join(report.Missing, ", "))
		}
	}

	if len(report.Commands) > 0 && (onlyInstall || !report.Ready) {
		fmt.Fprintln(out, "\nComandos recomendados:")
		for _, command := range report.Commands {
			fmt.Fprintf(out, "  %s\n", command)
		}
	}
}
