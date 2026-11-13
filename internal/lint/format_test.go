package lint

import (
	"context"
	"os"
	"path/filepath"
	"strings"
	"testing"
)

func TestFindDetektConfigBuscaEnElProyecto(t *testing.T) {
	dir := t.TempDir()
	path := filepath.Join(dir, "detekt.yml")
	if err := os.WriteFile(path, []byte("config:\n  validation: false\n"), 0o644); err != nil {
		t.Fatal(err)
	}

	if got := findDetektConfig(dir); got != path {
		t.Errorf("findDetektConfig = %q, quiero %q", got, path)
	}
}

func TestFindDetektConfigSinFicheroDevuelveCadenaVacia(t *testing.T) {
	dir := t.TempDir()
	if got := findDetektConfig(dir); got != "" {
		t.Errorf("findDetektConfig = %q, quiero cadena vacía", got)
	}
}

func TestFindDetektConfigAceptaLosNombresHabituales(t *testing.T) {
	for _, name := range []string{"detekt.yaml", ".detekt.yml"} {
		dir := t.TempDir()
		path := filepath.Join(dir, name)
		if err := os.WriteFile(path, []byte("config: {}\n"), 0o644); err != nil {
			t.Fatal(err)
		}
		if got := findDetektConfig(dir); got != path {
			t.Errorf("findDetektConfig = %q, quiero %q", got, path)
		}
	}
}

func TestFormatSinFuentesNoHaceNada(t *testing.T) {
	changed, err := Format(context.Background(), Options{ProjectDir: t.TempDir()})
	if err != nil {
		t.Fatalf("Format: %v", err)
	}
	if len(changed) != 0 {
		t.Errorf("ficheros cambiados = %d, quiero 0: %+v", len(changed), changed)
	}
}

func TestFileSumDetectaLosCambios(t *testing.T) {
	dir := t.TempDir()
	path := filepath.Join(dir, "Main.kt")
	if err := os.WriteFile(path, []byte("fun a() = 1\n"), 0o644); err != nil {
		t.Fatal(err)
	}

	before, err := fileSum(path)
	if err != nil {
		t.Fatalf("fileSum: %v", err)
	}
	after, err := fileSum(path)
	if err != nil {
		t.Fatalf("fileSum: %v", err)
	}
	if before != after {
		t.Errorf("el fichero no cambió pero la suma es distinta: %q != %q", before, after)
	}

	if err := os.WriteFile(path, []byte("fun a() = 2\n"), 0o644); err != nil {
		t.Fatal(err)
	}
	changed, err := fileSum(path)
	if err != nil {
		t.Fatalf("fileSum: %v", err)
	}
	if before == changed {
		t.Error("la suma no cambió tras escribir otro contenido")
	}
}

// TestFormatReescribeElCodigo comprueba que el formateador deja el código
// como lo deja ktlint. Solo se ejecuta si ktlint está instalado.
func TestFormatReescribeElCodigo(t *testing.T) {
	if _, ok := findKtlint(); !ok {
		t.Skip("ktlint no está instalado")
	}

	dir := t.TempDir()
	source := filepath.Join(dir, "app", "java")
	if err := os.MkdirAll(source, 0o755); err != nil {
		t.Fatal(err)
	}
	file := filepath.Join(source, "Main.kt")
	contenido := "package com.ejemplo\n\nfun main( ) {\n    val x = 1\n}\n"
	if err := os.WriteFile(file, []byte(contenido), 0o644); err != nil {
		t.Fatal(err)
	}

	opts := Options{
		ProjectDir:  dir,
		SourceRoot:  filepath.Join(dir, "app", "java"),
		KotlinFiles: []string{file},
	}
	changed, err := Format(context.Background(), opts)
	if err != nil {
		t.Fatalf("Format: %v", err)
	}
	if len(changed) != 1 {
		t.Fatalf("ficheros cambiados = %d, quiero 1: %+v", len(changed), changed)
	}

	data, err := os.ReadFile(file)
	if err != nil {
		t.Fatal(err)
	}
	if !strings.Contains(string(data), "fun main() {") {
		t.Errorf("ktlint no corrigió el código:\n%s", data)
	}
}
