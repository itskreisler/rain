package main_test

import (
	"os"
	"path/filepath"
	"sort"
	"testing"

	"rain/internal/build"
	"rain/internal/config"
)

func findAndroidJar() string {
	if envJar := os.Getenv("ANDROID_JAR"); envJar != "" {
		if _, err := os.Stat(envJar); err == nil {
			return envJar
		}
	}

	sdkHomes := []string{
		os.Getenv("ANDROID_HOME"),
		os.Getenv("ANDROID_SDK_ROOT"),
		"/opt/android-sdk",
		"/usr/local/lib/android/sdk",
	}

	var candidates []string
	for _, sdkHome := range sdkHomes {
		if sdkHome == "" {
			continue
		}
		platformsDir := filepath.Join(sdkHome, "platforms")
		entries, err := os.ReadDir(platformsDir)
		if err == nil {
			for _, e := range entries {
				jarPath := filepath.Join(platformsDir, e.Name(), "android.jar")
				if _, err := os.Stat(jarPath); err == nil {
					candidates = append(candidates, jarPath)
				}
			}
		}
	}

	if len(candidates) > 0 {
		sort.Strings(candidates)
		return candidates[len(candidates)-1]
	}
	return ""
}

func TestSampleProjects(t *testing.T) {
	samples := []struct {
		name        string
		dir         string
		expectedPkg string
		mode        string
	}{
		{
			name:        "Simple Application",
			dir:         filepath.Join("samples", "simple-app"),
			expectedPkg: "com.example.simple",
			mode:        "simple",
		},
		{
			name:        "Liquid Glass Application",
			dir:         filepath.Join("samples", "liquid-glass"),
			expectedPkg: "com.example.liquidglass.demo",
			mode:        "completo",
		},
		{
			name:        "Material 3 Expressive Application",
			dir:         filepath.Join("samples", "material3-expressive"),
			expectedPkg: "sco.carlukesoftware.material3expressive",
			mode:        "completo",
		},
	}

	for _, sample := range samples {
		t.Run(sample.name, func(t *testing.T) {
			cfgPath := filepath.Join(sample.dir, "rain.toml")
			if _, err := os.Stat(cfgPath); err != nil {
				t.Fatalf("rain.toml not found in %s: %v", sample.dir, err)
			}

			cfg, err := config.Read(cfgPath)
			if err != nil {
				t.Fatalf("failed to read config in %s: %v", sample.dir, err)
			}

			if cfg.Mode != sample.mode {
				t.Errorf("expected mode %q, got %q", sample.mode, cfg.Mode)
			}

			if cfg.Project.Package != sample.expectedPkg {
				t.Errorf("expected package %q, got %q", sample.expectedPkg, cfg.Project.Package)
			}

			manifestPath := filepath.Join(sample.dir, "app", "src", "main", "AndroidManifest.xml")
			if _, err := os.Stat(manifestPath); err != nil {
				t.Errorf("AndroidManifest.xml not found in %s: %v", sample.dir, err)
			}
		})
	}
}

func TestBuildSimpleAppLocally(t *testing.T) {
	androidJar := findAndroidJar()
	if androidJar == "" {
		t.Skip("android.jar not found, skipping local build test")
	}

	sampleDir := filepath.Join("samples", "simple-app")
	cfgPath := filepath.Join(sampleDir, "rain.toml")
	cfg, err := config.Read(cfgPath)
	if err != nil {
		t.Fatalf("failed to read config: %v", err)
	}

	appDir := filepath.Join(sampleDir, "app")
	buildDir := filepath.Join(appDir, "build")

	// Test Debug Build
	bcfgDebug := build.Config{
		AppDir:     appDir,
		BuildDir:   buildDir,
		LibsDir:    filepath.Join(sampleDir, "libs"),
		AndroidJar: androidJar,
		Package:    cfg.Project.Package,
		Release:    false,
	}
	if err := build.Run(bcfgDebug); err != nil {
		t.Fatalf("debug build failed: %v", err)
	}

	// Test Release Build
	bcfgRelease := build.Config{
		AppDir:     appDir,
		BuildDir:   buildDir,
		LibsDir:    filepath.Join(sampleDir, "libs"),
		AndroidJar: androidJar,
		Package:    cfg.Project.Package,
		Release:    true,
	}
	if err := build.Run(bcfgRelease); err != nil {
		t.Fatalf("release build failed: %v", err)
	}

	// Clean up build dir
	os.RemoveAll(buildDir)
}
