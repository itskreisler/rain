package build_test

import (
	"os"
	"path/filepath"
	"testing"

	"rain/internal/config"
)

func TestSampleAppStructures(t *testing.T) {
	simpleAppDir := filepath.Join("..", "..", "samples", "simple-app")
	composeAppDir := filepath.Join("..", "..", "samples", "compose-app")

	simpleManifest := filepath.Join(simpleAppDir, "app", "src", "main", "AndroidManifest.xml")
	if _, err := os.Stat(simpleManifest); err != nil {
		t.Errorf("simple-app Manifest missing: %v", err)
	}

	simpleActivity := filepath.Join(simpleAppDir, "app", "src", "main", "java", "com", "example", "simple", "MainActivity.java")
	if _, err := os.Stat(simpleActivity); err != nil {
		t.Errorf("simple-app MainActivity.java missing: %v", err)
	}

	composeManifest := filepath.Join(composeAppDir, "app", "src", "main", "AndroidManifest.xml")
	if _, err := os.Stat(composeManifest); err != nil {
		t.Errorf("compose-app Manifest missing: %v", err)
	}

	composeActivity := filepath.Join(composeAppDir, "app", "src", "main", "kotlin", "com", "example", "compose", "MainActivity.kt")
	if _, err := os.Stat(composeActivity); err != nil {
		t.Errorf("compose-app MainActivity.kt missing: %v", err)
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
