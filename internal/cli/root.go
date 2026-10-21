package cli

import (
	"errors"
	"fmt"
	"os"
	"path/filepath"
	"strings"

	"github.com/spf13/cobra"

	"github.com/itskreisler/rain/internal/ui"
)

// BuildInfo contiene los datos de versión inyectados en tiempo de compilación.
type BuildInfo struct {
	Version string
	Commit  string
	Date    string
}

// exitError permite asociar un código de salida a un error.
type exitError struct {
	code int
	err  error
}

func (e *exitError) Error() string { return e.err.Error() }
func (e *exitError) Unwrap() error { return e.err }

// ExitCode devuelve el código de salida asociado a err (1 por defecto).
func ExitCode(err error) int {
	var e *exitError
	if errors.As(err, &e) {
		return e.code
	}
	return 1
}

func usageErr(format string, a ...any) error {
	return &exitError{code: 2, err: fmt.Errorf(format, a...)}
}

// Execute construye y ejecuta el CLI. Los errores se imprimen una sola vez y
// terminan el proceso con el código correspondiente.
func Execute(info BuildInfo) {
	root := NewRootCmd(info)
	root.SetArgs(normalizeLegacyArgs(os.Args[1:]))
	if err := root.Execute(); err != nil {
		ui.Error("%v", err)
		os.Exit(ExitCode(err))
	}
}

// NewRootCmd construye el árbol de comandos. Se exporta para poder testearlo
// con cmd.SetArgs(...) sin ejecutar os.Exit.
func NewRootCmd(info BuildInfo) *cobra.Command {
	opts := &globalOptions{}

	root := &cobra.Command{
		Use:   "rain",
		Short: "Build system ligero para Android",
		Long: "Rain compila APKs Android sin Gradle ni Android Studio.\n" +
			"Pensado para Termux y contenedores Alpine, sin daemon y sin JVM persistente.",
		SilenceUsage:  true,
		SilenceErrors: true,
		Version:       info.Version,
		PersistentPreRunE: func(cmd *cobra.Command, args []string) error {
			ui.Verbose = opts.verbose
			ui.Quiet = opts.quiet
			ui.NoColor = opts.noColor || os.Getenv("NO_COLOR") != ""
			ui.SetOutput(cmd.OutOrStdout(), cmd.ErrOrStderr())
			addManagedBinToPath()
			return nil
		},
	}
	root.SetVersionTemplate("rain {{.Version}}\n")

	root.PersistentFlags().BoolVarP(&opts.verbose, "verbose", "v", false, "mostrar detalles de ejecución")
	root.PersistentFlags().BoolVarP(&opts.quiet, "quiet", "q", false, "solo mostrar errores")
	root.PersistentFlags().BoolVar(&opts.noColor, "no-color", false, "desactivar colores ANSI")

	root.AddCommand(
		newInitCmd(),
		newBuildCmd(),
		newDoctorCmd(),
		newCleanCmd(),
		newDepsCmd(),
		newVersionCmd(info),
	)

	return root
}

type globalOptions struct {
	verbose bool
	quiet   bool
	noColor bool
}

// normalizeLegacyArgs acepta la forma antigua de un guion (-release,
// -android-jar) además de la forma POSIX (--release, --android-jar).
func normalizeLegacyArgs(args []string) []string {
	known := map[string]bool{
		"android-jar":     true,
		"release":         true,
		"verbose":         true,
		"quiet":           true,
		"no-color":        true,
		"mode":            true,
		"name":            true,
		"package":         true,
		"version":         true,
		"non-interactive": true,
		"force":           true,
		"profile":         true,
		"json":            true,
		"print-install":   true,
		"fix":             true,
		"module":          true,
		"dry-run":         true,
		"offline":         true,
	}

	out := make([]string, len(args))
	for i, arg := range args {
		if strings.HasPrefix(arg, "-") && !strings.HasPrefix(arg, "--") && len(arg) > 2 {
			name := strings.TrimPrefix(arg, "-")
			if eq := strings.IndexByte(name, '='); eq >= 0 {
				name = name[:eq]
			}
			if known[name] {
				arg = "-" + arg
			}
		}
		out[i] = arg
	}
	return out
}

// addManagedBinToPath antepone ~/.rain/bin al PATH para que los wrappers
// creados por "rain doctor --fix" estén disponibles sin tocar el shell.
func addManagedBinToPath() {
	home, err := os.UserHomeDir()
	if err != nil {
		return
	}
	bin := filepath.Join(home, ".rain", "bin")
	for _, part := range filepath.SplitList(os.Getenv("PATH")) {
		if part == bin {
			return
		}
	}
	_ = os.Setenv("PATH", bin+string(os.PathListSeparator)+os.Getenv("PATH"))
}
