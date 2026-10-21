package cli

import (
	"fmt"
	"os"
	"path/filepath"
	"strconv"
	"strings"

	"github.com/spf13/cobra"

	"github.com/itskreisler/rain/internal/aar"
	"github.com/itskreisler/rain/internal/build"
	"github.com/itskreisler/rain/internal/config"
	"github.com/itskreisler/rain/internal/doctor"
	"github.com/itskreisler/rain/internal/pom"
	"github.com/itskreisler/rain/internal/ui"
)

func newBuildCmd() *cobra.Command {
	var (
		androidJar        string
		release           bool
		allowDebugSigning bool
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
				return runBuildDir(args[0], absJar, release, allowDebugSigning)
			}
			return runBuild(absJar, release, allowDebugSigning)
		},
	}

	cmd.Flags().StringVar(&androidJar, "android-jar", "", "ruta a android.jar (por defecto se autodetecta)")
	cmd.Flags().BoolVarP(&release, "release", "r", false, "compilar en modo release (R8)")
	cmd.Flags().BoolVar(&allowDebugSigning, "allow-debug-signing", false, "permitir firmar un release con la keystore de debug (con aviso)")

	return cmd
}

func runBuild(androidJar string, release, allowDebugSigning bool) error {
	cfg, err := config.Read("rain.toml")
	if err != nil {
		return fmt.Errorf("no se pudo leer rain.toml: %w (usa rain init o rain build <dir>)", err)
	}

	var libs *aar.Set
	if len(cfg.Dependencies) > 0 {
		ui.Info("Resolviendo y descargando dependencias...")
		coords := cfg.Dependencies
		if cfg.Mode == "completo" {
			coords, err = resolveTransitiveCoordinates(cfg)
			if err != nil {
				return fmt.Errorf("resolviendo dependencias: %w", err)
			}
		}
		libs, err = prepareAARSet(coords, ".rain-deps")
		if err != nil {
			return fmt.Errorf("preparando dependencias: %w", err)
		}
	}

	modules := cfg.Modules
	if len(modules) == 0 {
		modules = []config.Module{{Name: "app", Path: "app"}}
	}

	for _, mod := range modules {
		ui.Info("=== Compilando módulo: %s ===", mod.Name)
		bcfg := build.Config{
			AppDir:            mod.Path,
			BuildDir:          filepath.Join(mod.Path, "build"),
			LibsDir:           "libs",
			AndroidJar:        androidJar,
			Package:           cfg.Project.Package,
			Release:           release,
			Signing:           cfg.Signing,
			MinSdk:            21,
			TargetSdk:         36,
			VersionCode:       1,
			VersionName:       cfg.Project.Version,
			AppName:           cfg.Project.Name,
			Libs:              libs,
			AllowDebugSigning: allowDebugSigning,
		}
		if libs != nil {
			bcfg.DepsDir = libs.Dir
		}
		if err := build.Run(bcfg); err != nil {
			return fmt.Errorf("módulo %s: %w", mod.Name, err)
		}
	}
	return nil
}

// resolveTransitiveCoordinates resuelve el cierre transitivo de las
// dependencias declaradas (modo completo).
func resolveTransitiveCoordinates(cfg config.Config) ([]string, error) {
	var coords []string
	seen := make(map[string]bool)

	for _, dep := range cfg.Dependencies {
		deps, err := pom.ResolveTransitive(dep, "cache")
		if err != nil {
			return nil, fmt.Errorf("resolviendo %s: %w", dep, err)
		}
		for _, d := range deps {
			coord := d.Coordinate()
			if !seen[coord] {
				seen[coord] = true
				coords = append(coords, coord)
			}
		}
		if !seen[dep] {
			seen[dep] = true
			coords = append(coords, dep)
		}
	}
	return coords, nil
}

// prepareAARSet descarga y prepara cada coordenada (AAR o JAR) en dir.
func prepareAARSet(coords []string, dir string) (*aar.Set, error) {
	pb := ui.NewProgressBar(len(coords), "Preparando ")
	set := &aar.Set{Dir: dir}
	client := aar.NewClient()
	failures := 0
	for i, coord := range coords {
		pb.Current = i + 1
		pb.Render()
		s, err := client.Prepare([]string{coord}, dir)
		if err != nil {
			failures++
			ui.Warn("No se pudo preparar %s: %v", coord, err)
			continue
		}
		set.Libraries = append(set.Libraries, s.Libraries...)
	}
	pb.Finish()
	if len(set.Libraries) == 0 {
		return nil, fmt.Errorf("no se pudo preparar ninguna dependencia")
	}
	if failures > 0 {
		ui.Warn("%d dependencias no se pudieron preparar", failures)
	}
	return set, nil
}

// runBuildDir compila un directorio sin rain.toml usando el layout plano de
// android2apk, o entra en el directorio si allí hay un rain.toml.
func runBuildDir(dir, androidJar string, release, allowDebugSigning bool) error {
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
		return runBuild(androidJar, release, allowDebugSigning)
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
		AppDir:            absDir,
		BuildDir:          filepath.Join(absDir, "build"),
		LibsDir:           filepath.Join(absDir, "libs"),
		AndroidJar:        androidJar,
		Package:           build.PackageFromManifest(absDir),
		Release:           release,
		MinSdk:            minSdk,
		TargetSdk:         36,
		VersionCode:       versionCode,
		VersionName:       versionName,
		AppName:           name,
		AllowDebugSigning: allowDebugSigning,
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
