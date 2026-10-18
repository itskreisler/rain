package pom_test

import (
	"encoding/xml"
	"testing"

	"rain/internal/pom"
)

func TestResolveProperties(t *testing.T) {
	props := map[string]string{
		"compose.version": "1.6.0",
		"kotlin.version":  "1.9.20",
	}

	val := pom.ResolveProperties("${compose.version}", props)
	if val != "1.6.0" {
		t.Errorf("expected '1.6.0', got %q", val)
	}

	nestedProps := map[string]string{
		"base.ver": "1.0",
		"full.ver": "${base.ver}.1",
	}
	valNested := pom.ResolveProperties("v${full.ver}", nestedProps)
	if valNested != "v1.0.1" {
		t.Errorf("expected 'v1.0.1', got %q", valNested)
	}
}

func TestNormalizeVersion(t *testing.T) {
	tests := []struct {
		input    string
		expected string
	}{
		{"1.2.3", "1.2.3"},
		{"[1.2.3]", "1.2.3"},
		{"[1.0,2.0]", "1.0"},
		{"(1.0,2.0]", ""},
	}

	for _, tt := range tests {
		got := pom.NormalizeVersion(tt.input)
		if got != tt.expected {
			t.Errorf("NormalizeVersion(%q) = %q; want %q", tt.input, got, tt.expected)
		}
	}
}

func TestParsePOM(t *testing.T) {
	xmlData := `<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <groupId>com.example</groupId>
    <artifactId>sample-lib</artifactId>
    <version>1.0.0</version>
    <dependencies>
        <dependency>
            <groupId>org.jetbrains.kotlin</groupId>
            <artifactId>kotlin-stdlib</artifactId>
            <version>1.9.20</version>
        </dependency>
    </dependencies>
</project>`

	var p pom.Project
	if err := xml.Unmarshal([]byte(xmlData), &p); err != nil {
		t.Fatalf("failed to unmarshal POM xml: %v", err)
	}

	if p.GroupID != "com.example" || p.ArtifactID != "sample-lib" || p.Version != "1.0.0" {
		t.Errorf("unexpected POM header: %s:%s:%s", p.GroupID, p.ArtifactID, p.Version)
	}

	if len(p.Dependencies) != 1 {
		t.Fatalf("expected 1 dependency, got %d", len(p.Dependencies))
	}
	if p.Dependencies[0].ArtifactID != "kotlin-stdlib" {
		t.Errorf("expected artifact 'kotlin-stdlib', got %q", p.Dependencies[0].ArtifactID)
	}
}
