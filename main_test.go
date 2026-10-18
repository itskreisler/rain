package main_test

import (
	"os"
	"path/filepath"
	"testing"

	"rain/internal/config"
)

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
			name:        "Compose Application",
			dir:         filepath.Join("samples", "compose-app"),
			expectedPkg: "com.example.compose",
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
