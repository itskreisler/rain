package doctor

import (
	"context"
	"errors"
	"fmt"
	"io"
	"os"
	"os/exec"
	"runtime"
	"strings"
)

// InstallOptions controla la instalación de dependencias del sistema. Rain
// nunca instala nada por defecto: el llamador debe pedirlo explícitamente.
type InstallOptions struct {
	AssumeYes bool
	DryRun    bool
	Confirm   func(command string) bool
	Out       io.Writer
}

// InstallMissing ejecuta, una sola vez, los comandos de instalación asociados
// a las comprobaciones obligatorias que fallaron. Las herramientas gestionadas
// por Rain (que se arreglan con "rain doctor --fix") se ignoran: el comando
// doctor ya las instaló antes de llegar aquí.
func InstallMissing(ctx context.Context, report Report, opts InstallOptions) ([]string, error) {
	var (
		ran    []string
		failed []error
		seen   = make(map[string]bool)
		out    = opts.Out
	)
	if out == nil {
		out = io.Discard
	}

	for _, check := range report.Checks {
		if check.OK || !check.Required || check.Install == "" {
			continue
		}
		if strings.HasPrefix(check.Install, "rain doctor") {
			continue
		}
		command := prepareCommand(check.Install)
		if seen[command] {
			continue
		}
		seen[command] = true

		if opts.DryRun {
			fmt.Fprintf(out, "[simulación] %s\n", command)
			ran = append(ran, command)
			continue
		}
		if !opts.AssumeYes {
			if opts.Confirm == nil || !opts.Confirm(command) {
				fmt.Fprintf(out, "omitido: %s\n", command)
				continue
			}
		}
		fmt.Fprintf(out, "→ %s\n", command)
		if err := runShell(ctx, command, out); err != nil {
			failed = append(failed, fmt.Errorf("%s: %w", command, err))
			continue
		}
		ran = append(ran, command)
	}
	return ran, errors.Join(failed...)
}

// prepareCommand evita un sudo innecesario cuando ya somos root. Es habitual
// en contenedores, donde sudo no está instalado.
func prepareCommand(command string) string {
	if runtime.GOOS == "windows" || os.Geteuid() != 0 {
		return command
	}
	return strings.TrimPrefix(command, "sudo ")
}

func runShell(ctx context.Context, command string, out io.Writer) error {
	var cmd *exec.Cmd
	if runtime.GOOS == "windows" {
		cmd = exec.CommandContext(ctx, "cmd", "/C", command)
	} else {
		cmd = exec.CommandContext(ctx, "sh", "-c", command)
	}
	cmd.Stdout = out
	cmd.Stderr = out
	return cmd.Run()
}
