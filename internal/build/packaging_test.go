package build

import (
	"archive/zip"
	"os"
	"path/filepath"
	"testing"
)

// zipEntries devuelve los nombres de las entradas de un ZIP, en orden.
func zipEntries(t *testing.T, path string) []string {
	t.Helper()
	r, err := zip.OpenReader(path)
	if err != nil {
		t.Fatalf("abriendo %s: %v", path, err)
	}
	defer func() { _ = r.Close() }()

	var names []string
	for _, f := range r.File {
		names = append(names, f.Name)
	}
	return names
}

func countEntry(names []string, want string) int {
	n := 0
	for _, name := range names {
		if name == want {
			n++
		}
	}
	return n
}

// TestAppendDexNoDuplicaLasEntradasDelApkBase cubre el caso en que el APK
// base se reutiliza de un build anterior: ya contiene classes.dex y las
// librerías nativas, y añadirlo otra vez produciría un APK con entradas
// duplicadas ("Multiple ZIP entries with the same name: classes.dex"), que
// apksigner rechaza.
func TestAppendDexNoDuplicaLasEntradasDelApkBase(t *testing.T) {
	dir := t.TempDir()

	baseApk := filepath.Join(dir, "base.apk")
	writeZip(t, baseApk, map[string]string{
		"AndroidManifest.xml": "manifest",
		"resources.arsc":      "resources",
		"classes.dex":         "dex antiguo",
		"lib/arm64-v8a/a.so":  "so antiguo",
	})

	dexDir := filepath.Join(dir, "dex")
	if err := os.MkdirAll(dexDir, 0o755); err != nil {
		t.Fatal(err)
	}
	dex := filepath.Join(dexDir, "classes.dex")
	if err := os.WriteFile(dex, []byte("dex nuevo"), 0o644); err != nil {
		t.Fatal(err)
	}

	soDir := filepath.Join(dir, "so")
	if err := os.MkdirAll(soDir, 0o755); err != nil {
		t.Fatal(err)
	}
	so := filepath.Join(soDir, "a.so")
	if err := os.WriteFile(so, []byte("so nuevo"), 0o644); err != nil {
		t.Fatal(err)
	}

	nativeLibs := map[string]string{"lib/arm64-v8a/a.so": so}
	if err := appendDex(baseApk, []string{dex}, nativeLibs); err != nil {
		t.Fatalf("appendDex: %v", err)
	}

	names := zipEntries(t, baseApk)
	if n := countEntry(names, "classes.dex"); n != 1 {
		t.Errorf("classes.dex aparece %d veces, quiero 1: %v", n, names)
	}
	if n := countEntry(names, "lib/arm64-v8a/a.so"); n != 1 {
		t.Errorf("a.so aparece %d veces, quiero 1: %v", n, names)
	}
	for _, want := range []string{"AndroidManifest.xml", "resources.arsc"} {
		if countEntry(names, want) != 1 {
			t.Errorf("%s debería estar en el APK: %v", want, names)
		}
	}
}

// TestAppendDexAñadeElDexNuevoComprueba que, aun limpiando el APK base, el
// DEX que se añade es el nuevo (no el que quedó de un build anterior).
func TestAppendDexAñadeElDexNuevo(t *testing.T) {
	dir := t.TempDir()

	baseApk := filepath.Join(dir, "base.apk")
	writeZip(t, baseApk, map[string]string{
		"AndroidManifest.xml": "manifest",
		"classes.dex":         "dex antiguo",
	})

	dex := filepath.Join(dir, "classes.dex")
	if err := os.WriteFile(dex, []byte("dex nuevo"), 0o644); err != nil {
		t.Fatal(err)
	}

	if err := appendDex(baseApk, []string{dex}, nil); err != nil {
		t.Fatalf("appendDex: %v", err)
	}

	r, err := zip.OpenReader(baseApk)
	if err != nil {
		t.Fatalf("abriendo %s: %v", baseApk, err)
	}
	defer func() { _ = r.Close() }()

	for _, f := range r.File {
		if f.Name != "classes.dex" {
			continue
		}
		rc, err := f.Open()
		if err != nil {
			t.Fatal(err)
		}
		buf := make([]byte, len("dex nuevo"))
		n, _ := rc.Read(buf)
		_ = rc.Close()
		if string(buf[:n]) != "dex nuevo" {
			t.Errorf("classes.dex = %q, quiero el nuevo", string(buf[:n]))
		}
		return
	}
	t.Fatal("classes.dex no está en el APK")
}
