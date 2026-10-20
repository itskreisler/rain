package build_test

import (
	"os"
	"path/filepath"
	"testing"

	"rain/internal/config"
)

func TestSampleAppStructures(t *testing.T) {
	simpleAppDir := filepath.Join("..", "..", "samples", "simple-app")
	lgAppDir := filepath.Join("..", "..", "samples", "liquid-glass")

	simpleManifest := filepath.Join(simpleAppDir, "app", "src", "main", "AndroidManifest.xml")
	if _, err := os.Stat(simpleManifest); err != nil {
		t.Errorf("simple-app Manifest missing: %v", err)
	}

	simpleActivity := filepath.Join(simpleAppDir, "app", "src", "main", "java", "com", "example", "simple", "MainActivity.java")
	if _, err := os.Stat(simpleActivity); err != nil {
		t.Errorf("simple-app MainActivity.java missing: %v", err)
	}

	lgManifest := filepath.Join(lgAppDir, "app", "src", "main", "AndroidManifest.xml")
	if _, err := os.Stat(lgManifest); err != nil {
		t.Errorf("liquid-glass Manifest missing: %v", err)
	}

	lgActivity := filepath.Join(lgAppDir, "app", "src", "main", "java", "com", "example", "liquidglass", "HeroShowcaseActivity.kt")
	if _, err := os.Stat(lgActivity); err != nil {
		t.Errorf("liquid-glass HeroShowcaseActivity.kt missing: %v", err)
	}
}

func TestBuildConfigGen(t *testing.T) {
	cfg := config.Config{
		Project: config.Project{
			Name:    "SampleApp",
			Package: "com.example.sample",
			Version: "1.0.0",
		},
		Mode: "simple",
	}

	if cfg.Project.Package != "com.example.sample" {
		t.Errorf("expected package 'com.example.sample', got %q", cfg.Project.Package)
	}
}
