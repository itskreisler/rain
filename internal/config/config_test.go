package config_test

import (
	"path/filepath"
	"testing"

	"rain/internal/config"
)

func TestConfigReadWrite(t *testing.T) {
	tmpDir := t.TempDir()
	cfgPath := filepath.Join(tmpDir, "rain.toml")

	original := config.Config{
		Mode:         "completo",
		Dependencies: []string{"androidx.core:core:1.12.0", "androidx.compose.ui:ui:1.6.0"},
		Project: config.Project{
			Name:    "TestApp",
			Package: "com.example.test",
			Version: "1.2.3",
		},
		Build: config.Build{
			Debug: true,
		},
		Signing: &config.Signing{
			StoreFile:     "my.keystore",
			StorePassword: "pass",
			KeyAlias:      "alias",
			KeyPassword:   "keypass",
		},
	}

	if err := config.Write(cfgPath, original); err != nil {
		t.Fatalf("config.Write failed: %v", err)
	}

	readCfg, err := config.Read(cfgPath)
	if err != nil {
		t.Fatalf("config.Read failed: %v", err)
	}

	if readCfg.Mode != original.Mode {
		t.Errorf("expected mode %q, got %q", original.Mode, readCfg.Mode)
	}
	if readCfg.Project.Name != original.Project.Name {
		t.Errorf("expected project name %q, got %q", original.Project.Name, readCfg.Project.Name)
	}
	if readCfg.Project.Package != original.Project.Package {
		t.Errorf("expected package %q, got %q", original.Project.Package, readCfg.Project.Package)
	}
	if len(readCfg.Dependencies) != len(original.Dependencies) {
		t.Errorf("expected %d dependencies, got %d", len(original.Dependencies), len(readCfg.Dependencies))
	}
}

func TestSampleConfigs(t *testing.T) {
	simpleCfgPath := filepath.Join("..", "..", "samples", "simple-app", "rain.toml")
	simpleCfg, err := config.Read(simpleCfgPath)
	if err != nil {
		t.Fatalf("failed to read simple-app config: %v", err)
	}
	if simpleCfg.Mode != "simple" {
		t.Errorf("simple-app mode expected 'simple', got %q", simpleCfg.Mode)
	}
	if simpleCfg.Project.Package != "com.example.simple" {
		t.Errorf("simple-app package expected 'com.example.simple', got %q", simpleCfg.Project.Package)
	}

	composeCfgPath := filepath.Join("..", "..", "samples", "compose-app", "rain.toml")
	composeCfg, err := config.Read(composeCfgPath)
	if err != nil {
		t.Fatalf("failed to read compose-app config: %v", err)
	}
	if composeCfg.Mode != "completo" {
		t.Errorf("compose-app mode expected 'completo', got %q", composeCfg.Mode)
	}
	if composeCfg.Project.Package != "com.example.compose" {
		t.Errorf("compose-app package expected 'com.example.compose', got %q", composeCfg.Project.Package)
	}
	if len(composeCfg.Dependencies) == 0 {
		t.Errorf("compose-app expected dependencies, got empty slice")
	}

	m3CfgPath := filepath.Join("..", "..", "samples", "material3-expressive", "rain.toml")
	m3Cfg, err := config.Read(m3CfgPath)
	if err != nil {
		t.Fatalf("failed to read material3-expressive config: %v", err)
	}
	if m3Cfg.Mode != "completo" {
		t.Errorf("material3-expressive mode expected 'completo', got %q", m3Cfg.Mode)
	}
	if m3Cfg.Project.Package != "sco.carlukesoftware.material3expressive" {
		t.Errorf("material3-expressive package expected 'sco.carlukesoftware.material3expressive', got %q", m3Cfg.Project.Package)
	}
	if len(m3Cfg.Dependencies) == 0 {
		t.Errorf("material3-expressive expected dependencies, got empty slice")
	}
}
