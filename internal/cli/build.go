package cli

import (
	"fmt"
	"os"
	"path/filepath"
	"strconv"
	"strings"

	"github.com/spf13/cobra"

	"github.com/itskreisler/rain/internal/build"
	"github.com/itskreisler/rain/internal/config"
	"github.com/itskreisler/rain/internal/doctor"
	"github.com/itskreisler/rain/internal/pom"
	"github.com/itskreisler/rain/internal/resolver"
	"github.com/itskreisler/rain/internal/ui"
)

func newBuildCmd() *cobra.Command {
	var (
		androidJar string
		release    bool
	)

	cmd := &cobra.Command{
		Use:   "build [dir]",
		Short: "Compila y firma un APK",
		Long: "Compila un APK. Sin argumentos usa rain.toml del directorio actual.\n" +
			"Con [dir] compila un proyecto completo o un layout plano estilo android2apk\n" +
			"(AndroidManifest.xml, java/ y res/ en la raíz).\n\n" +
			"Si se omite --android-jar, rain busca el android.jar instalado por\n" +
			"\"rain doctor --fix\" o en el SDK del sistema.",
		Args: cobra.MaximumNArgs(1),
		RunE: func(cmd *cobra.Command, args []string) error {
			jar := androidJar
			if jar == "" {
				if found, ok := doctor.FindAndroidJar(); ok {
					jar = found
					ui.VerboseLog("android.jar: %s", found)
				}
			}
			if jar == "" {
				fmt.Fprintln(cmd.ErrOrStderr(), "Sugerencia:")
				fmt.Fprintln(cmd.ErrOrStderr(), "  rain doctor --fix    # descarga d8, apksigner y android.jar en ~/.rain")
				fmt.Fprintln(cmd.ErrOrStderr(), "  o usa --android-jar <ruta>")
				return usageErr("falta android.jar")
			}

			absJar, err := filepath.Abs(jar)
			if err != nil {
				return fmt.Errorf("resolviendo android.jar: %w", err)
			}

			if len(args) == 1 {
				return runBuildDir(args[0], absJar, release)
			}
			return runBuild(absJar, release)
		},
	}

	cmd.Flags().StringVar(&androidJar, "android-jar", "", "ruta a android.jar (por defecto se autodetecta)")
	cmd.Flags().BoolVarP(&release, "release", "r", false, "compilar en modo release (R8)")

	return cmd
}

func runBuild(androidJar string, release bool) error {
	cfg, err := config.Read("rain.toml")
	if err != nil {
		return fmt.Errorf("no se pudo leer rain.toml: %w (usa rain init o rain build <dir>)", err)
	}

	if needFetch(cfg) {
		ui.Info("Resolviendo y descargando dependencias...")
		if err := resolveAndFetchWithProgress(cfg, "libs"); err != nil {
			return fmt.Errorf("resolviendo dependencias: %w", err)
		}
	}

	modules := cfg.Modules
	if len(modules) == 0 {
		modules = []config.Module{{Name: "app", Path: "app"}}
	}

	for _, mod := range modules {
		ui.Info("=== Compilando módulo: %s ===", mod.Name)
		bcfg := build.Config{
			AppDir:      mod.Path,
			BuildDir:    filepath.Join(mod.Path, "build"),
			LibsDir:     "libs",
			AndroidJar:  androidJar,
			Package:     cfg.Project.Package,
			Release:     release,
			Signing:     cfg.Signing,
			MinSdk:      21,
			TargetSdk:   36,
			VersionCode: 1,
			VersionName: cfg.Project.Version,
			AppName:     cfg.Project.Name,
		}
		if err := build.Run(bcfg); err != nil {
			return fmt.Errorf("módulo %s: %w", mod.Name, err)
		}
	}
	return nil
}

// runBuildDir compila un directorio sin rain.toml usando el layout plano de
// android2apk, o entra en el directorio si allí hay un rain.toml.
func runBuildDir(dir, androidJar string, release bool) error {
	absDir, err := filepath.Abs(dir)
	if err != nil {
		return fmt.Errorf("resolviendo %s: %w", dir, err)
	}
	st, err := os.Stat(absDir)
	if err != nil || !st.IsDir() {
		return usageErr("%s no es un directorio válido", dir)
	}

	if _, err := os.Stat(filepath.Join(absDir, "rain.toml")); err == nil {
		previous, err := os.Getwd()
		if err != nil {
			return err
		}
		if err := os.Chdir(absDir); err != nil {
			return fmt.Errorf("entrando en %s: %w", absDir, err)
		}
		defer func() { _ = os.Chdir(previous) }()
		return runBuild(androidJar, release)
	}

	if _, err := os.Stat(filepath.Join(absDir, "src/main/AndroidManifest.xml")); err != nil {
		if _, err := os.Stat(filepath.Join(absDir, "AndroidManifest.xml")); err != nil {
			return usageErr("%s no contiene rain.toml ni AndroidManifest.xml", dir)
		}
	}

	props := readProperties(filepath.Join(absDir, "app.properties"))
	versionCode := parseInt(props["versionCode"], 1)
	if versionCode <= 0 {
		versionCode = 1
	}
	versionName := props["versionName"]
	if versionName == "" {
		versionName = "1.0"
	}
	minSdk := parseInt(props["minSdk"], 26)
	if minSdk <= 0 {
		minSdk = 26
	}
	name := props["name"]
	if name == "" {
		name = filepath.Base(absDir)
	}

	ui.Info("=== Compilando %s (layout plano) ===", name)
	bcfg := build.Config{
		AppDir:      absDir,
		BuildDir:    filepath.Join(absDir, "build"),
		LibsDir:     filepath.Join(absDir, "libs"),
		AndroidJar:  androidJar,
		Package:     build.PackageFromManifest(absDir),
		Release:     release,
		MinSdk:      minSdk,
		TargetSdk:   36,
		VersionCode: versionCode,
		VersionName: versionName,
		AppName:     name,
	}
	if bcfg.Package == "" {
		return fmt.Errorf("no se pudo leer package=\"...\" de AndroidManifest.xml")
	}
	return build.Run(bcfg)
}

func readProperties(path string) map[string]string {
	props := make(map[string]string)
	data, err := os.ReadFile(path)
	if err != nil {
		return props
	}
	for _, line := range strings.Split(string(data), "\n") {
		line = strings.TrimSpace(line)
		if line == "" || strings.HasPrefix(line, "#") {
			continue
		}
		key, value, ok := strings.Cut(line, "=")
		if !ok {
			continue
		}
		props[strings.TrimSpace(key)] = strings.TrimSpace(value)
	}
	return props
}

func parseInt(value string, fallback int) int {
	number, err := strconv.Atoi(strings.TrimSpace(value))
	if err != nil {
		return fallback
	}
	return number
}

func needFetch(cfg config.Config) bool {
	if len(cfg.Dependencies) == 0 {
		return false
	}

	entries, _ := os.ReadDir("libs")
	for _, dep := range cfg.Dependencies {
		parts := strings.Split(dep, ":")
		if len(parts) != 3 {
			continue
		}
		artifact := parts[1]
		found := false
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
		if cfg.Mode == "completo" {
			deps, err := pom.ResolveTransitive(dep, "cache")
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
