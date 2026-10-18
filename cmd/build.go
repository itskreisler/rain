package cmd

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"

	"rain/internal/build"
	"rain/internal/config"
	"rain/internal/pom"
	"rain/internal/resolver"
	"rain/internal/ui"

	"github.com/spf13/cobra"
	"github.com/spf13/pflag"
)

var (
	flagAndroidJar string
	flagRelease    bool
	flagVerbose    bool
	flagQuiet      bool
)

var buildCmd = &cobra.Command{
	Use:   "build",
	Short: "Compila el proyecto y genera el APK",
	Run: func(cmd *cobra.Command, args []string) {
		runBuild()
	},
}

func init() {
	buildCmd.Flags().StringVar(&flagAndroidJar, "android-jar", "", "ruta a android.jar (Requerido)")
	buildCmd.Flags().BoolVarP(&flagRelease, "release", "r", false, "compilar en modo release (R8 y firma)")
	buildCmd.Flags().BoolVar(&flagVerbose, "verbose", false, "mostrar detalles de ejecución")
	buildCmd.Flags().BoolVar(&flagQuiet, "quiet", false, "solo mostrar errores")

	buildCmd.Flags().SetNormalizeFunc(func(f *pflag.FlagSet, name string) pflag.NormalizedName {
		switch name {
		case "release":
			return pflag.NormalizedName("release")
		case "android-jar":
			return pflag.NormalizedName("android-jar")
		case "verbose":
			return pflag.NormalizedName("verbose")
		case "quiet":
			return pflag.NormalizedName("quiet")
		}
		return pflag.NormalizedName(name)
	})
}

func runBuild() {
	ui.Verbose = flagVerbose
	ui.Quiet = flagQuiet

	if flagAndroidJar == "" {
		ui.Error("Falta --android-jar")
		fmt.Println("\nSugerencia:")
		fmt.Println("  Usa --android-jar <ruta> para indicar el archivo android.jar del SDK.")
		fmt.Println("  Ejemplo: rain build --android-jar ./sdk/android.jar")
		os.Exit(1)
	}

	cfg, err := config.Read(DefaultConfigFileName)
	if err != nil {
		ui.Warn("No se pudo leer %s (%v). Usando modo manual.", DefaultConfigFileName, err)
	}

	needFetch := checkNeedFetch(cfg)
	if needFetch {
		ui.Info("Resolviendo y descargando dependencias...")
		if err := resolveAndFetchWithProgress(cfg, DefaultLibsDir); err != nil {
			ui.Error("Resolviendo dependencias: %v", err)
			os.Exit(1)
		}
	}

	modules := cfg.Modules
	if len(modules) == 0 {
		modules = []config.Module{{Name: "app", Path: DefaultAppDir}}
	}

	for _, mod := range modules {
		ui.Info("=== Compilando módulo: %s ===", mod.Name)
		bcfg := build.Config{
			AppDir:     mod.Path,
			BuildDir:   filepath.Join(mod.Path, "build"),
			LibsDir:    DefaultLibsDir,
			AndroidJar: flagAndroidJar,
			Package:    cfg.Project.Package,
			Release:    flagRelease,
			Signing:    cfg.Signing,
		}
		if err := build.Run(bcfg); err != nil {
			ui.Error("Módulo %s: %v", mod.Name, err)
			os.Exit(1)
		}
	}
}

func checkNeedFetch(cfg config.Config) bool {
	if len(cfg.Dependencies) == 0 {
		return false
	}
	for _, dep := range cfg.Dependencies {
		parts := strings.Split(dep, ":")
		if len(parts) != 3 {
			continue
		}
		artifact := parts[1]
		found := false
		entries, _ := os.ReadDir(DefaultLibsDir)
		for _, e := range entries {
			if strings.Contains(e.Name(), artifact) && strings.HasSuffix(e.Name(), ".jar") {
				found = true
				break
			}
		}
		if !found {
			return true
		}
	}
	return false
}

func resolveAndFetchWithProgress(cfg config.Config, libsDir string) error {
	var allCoords []string
	seen := make(map[string]bool)

	for _, dep := range cfg.Dependencies {
		if cfg.Mode == ModeCompleto {
			deps, err := pom.ResolveTransitive(dep, DefaultCacheDir)
			if err != nil {
				return fmt.Errorf("resolviendo %s: %w", dep, err)
			}
			for _, d := range deps {
				coord := d.Coordinate()
				if !seen[coord] {
					seen[coord] = true
					allCoords = append(allCoords, coord)
				}
			}
		}
		if !seen[dep] {
			seen[dep] = true
			allCoords = append(allCoords, dep)
		}
	}

	if len(allCoords) == 0 {
		return nil
	}

	pb := ui.NewProgressBar(len(allCoords), "Descargando ")
	for i, coord := range allCoords {
		pb.Current = i + 1
		pb.Render()
		if _, err := resolver.DownloadArtifact(coord, libsDir); err != nil {
			ui.Warn("No se pudo descargar %s: %v", coord, err)
		}
	}
	pb.Finish()
	return nil
}
