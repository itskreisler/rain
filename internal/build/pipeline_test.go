package build

import (
	"archive/zip"
	"bytes"
	"os"
	"path/filepath"
	"testing"
)

func writeFile(t *testing.T, path, content string) {
	t.Helper()
	if err := os.MkdirAll(filepath.Dir(path), 0o755); err != nil {
		t.Fatal(err)
	}
	if err := os.WriteFile(path, []byte(content), 0o644); err != nil {
		t.Fatal(err)
	}
}

func TestSourceRootPrefersGradleLayout(t *testing.T) {
	dir := t.TempDir()
	writeFile(t, filepath.Join(dir, "src", "main", "AndroidManifest.xml"), "<manifest/>")

	if got := sourceRoot(dir); got != filepath.Join(dir, "src", "main") {
		t.Errorf("sourceRoot = %q, quiero el layout Gradle", got)
	}
	if got := manifestPath(dir); got != filepath.Join(dir, "src", "main", "AndroidManifest.xml") {
		t.Errorf("manifestPath = %q", got)
	}
}

func TestSourceRootFallsBackToFlatLayout(t *testing.T) {
	dir := t.TempDir()
	writeFile(t, filepath.Join(dir, "AndroidManifest.xml"), "<manifest/>")

	if got := sourceRoot(dir); got != dir {
		t.Errorf("sourceRoot = %q, quiero %q (layout plano)", got, dir)
	}
	if got := resPath(dir); got != filepath.Join(dir, "res") {
		t.Errorf("resPath = %q", got)
	}
}

func TestPackageFromManifest(t *testing.T) {
	tests := []struct {
		name     string
		manifest string
		want     string
	}{
		{
			name:     "package simple",
			manifest: `<manifest xmlns:android="http://schemas.android.com/apk/res/android" package="com.klei.hello">`,
			want:     "com.klei.hello",
		},
		{
			name:     "package con espacios",
			manifest: `<manifest package = "com.demo.app" >`,
			want:     "",
		},
		{
			name:     "sin package",
			manifest: `<manifest xmlns:android="http://schemas.android.com/apk/res/android">`,
			want:     "",
		},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			dir := t.TempDir()
			writeFile(t, filepath.Join(dir, "AndroidManifest.xml"), tc.manifest)
			if got := PackageFromManifest(dir); got != tc.want {
				t.Errorf("PackageFromManifest = %q, quiero %q", got, tc.want)
			}
		})
	}
}

func TestPackageFromManifestMissingFile(t *testing.T) {
	if got := PackageFromManifest(t.TempDir()); got != "" {
		t.Errorf("PackageFromManifest sin manifest = %q, quiero vacío", got)
	}
}

func TestSanitizeTag(t *testing.T) {
	tests := []struct {
		in   string
		want string
	}{
		{"Mi App", "Mi-App"},
		{"Notas", "Notas"},
		{"app/compose", "app-compose"},
		{"  v1.2.3  ", "v1.2.3"},
		{"", ""},
		{"---", ""},
		{"ñandú", "and"},
	}
	for _, tc := range tests {
		if got := sanitizeTag(tc.in); got != tc.want {
			t.Errorf("sanitizeTag(%q) = %q, quiero %q", tc.in, got, tc.want)
		}
	}
}

func TestDetectABI(t *testing.T) {
	t.Run("sin lib es universal", func(t *testing.T) {
		if got := detectABI(t.TempDir()); got != "universal" {
			t.Errorf("detectABI = %q, quiero universal", got)
		}
	})

	t.Run("una abi", func(t *testing.T) {
		dir := t.TempDir()
		writeFile(t, filepath.Join(dir, "lib", "arm64-v8a", "libfoo.so"), "x")
		if got := detectABI(dir); got != "arm64-v8a" {
			t.Errorf("detectABI = %q, quiero arm64-v8a", got)
		}
	})

	t.Run("varias abis ordenadas", func(t *testing.T) {
		dir := t.TempDir()
		writeFile(t, filepath.Join(dir, "lib", "x86_64", "libfoo.so"), "x")
		writeFile(t, filepath.Join(dir, "lib", "arm64-v8a", "libfoo.so"), "x")
		if got := detectABI(dir); got != "arm64-v8a_x86_64" {
			t.Errorf("detectABI = %q, quiero arm64-v8a_x86_64", got)
		}
	})

	t.Run("las cuatro abis es universal", func(t *testing.T) {
		dir := t.TempDir()
		for _, abi := range []string{"arm64-v8a", "armeabi-v7a", "x86", "x86_64"} {
			writeFile(t, filepath.Join(dir, "lib", abi, "libfoo.so"), "x")
		}
		if got := detectABI(dir); got != "universal" {
			t.Errorf("detectABI = %q, quiero universal", got)
		}
	})

	t.Run("directorio abi sin .so se ignora", func(t *testing.T) {
		dir := t.TempDir()
		if err := os.MkdirAll(filepath.Join(dir, "lib", "arm64-v8a"), 0o755); err != nil {
			t.Fatal(err)
		}
		if got := detectABI(dir); got != "universal" {
			t.Errorf("detectABI = %q, quiero universal", got)
		}
	})
}

func TestApkNameFor(t *testing.T) {
	tests := []struct {
		name string
		cfg  Config
		want string
	}{
		{
			name: "nombre y versión",
			cfg:  Config{AppName: "Notas", VersionName: "1.0", AppDir: t.TempDir()},
			want: "Notas-1.0-universal.apk",
		},
		{
			name: "sin nombre usa el directorio",
			cfg:  Config{VersionName: "2.0", AppDir: filepath.Join(t.TempDir(), "MiProyecto")},
			want: "MiProyecto-2.0-universal.apk",
		},
		{
			name: "sin versión usa 1.0",
			cfg:  Config{AppName: "App", AppDir: t.TempDir()},
			want: "App-1.0-universal.apk",
		},
		{
			name: "saneado de espacios",
			cfg:  Config{AppName: "Mi App Grande", VersionName: "1.0 beta", AppDir: t.TempDir()},
			want: "Mi-App-Grande-1.0-beta-universal.apk",
		},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			if got := apkNameFor(tc.cfg); got != tc.want {
				t.Errorf("apkNameFor = %q, quiero %q", got, tc.want)
			}
		})
	}
}

func TestDexNumber(t *testing.T) {
	tests := []struct {
		path string
		want int
	}{
		{"/x/classes.dex", 1},
		{"/x/classes2.dex", 2},
		{"/x/classes10.dex", 10},
		{"/x/raro.dex", 1 << 30},
	}
	for _, tc := range tests {
		if got := dexNumber(tc.path); got != tc.want {
			t.Errorf("dexNumber(%q) = %d, quiero %d", tc.path, got, tc.want)
		}
	}
}

func TestAppendDexPreservesEntries(t *testing.T) {
	dir := t.TempDir()
	baseApk := filepath.Join(dir, "base.apk")

	// APK base con una entrada que NO debe perderse (resources.arsc).
	writeZip(t, baseApk, map[string]string{
		"AndroidManifest.xml": "manifest",
		"resources.arsc":      "recursos",
	})

	dex1 := filepath.Join(dir, "classes.dex")
	dex2 := filepath.Join(dir, "classes2.dex")
	writeFile(t, dex1, "dex-uno")
	writeFile(t, dex2, "dex-dos")

	if err := appendDex(baseApk, []string{dex1, dex2}); err != nil {
		t.Fatalf("appendDex: %v", err)
	}

	got := readZip(t, baseApk)
	if got["resources.arsc"] != "recursos" {
		t.Errorf("resources.arsc = %q, se perdió al empaquetar", got["resources.arsc"])
	}
	if got["AndroidManifest.xml"] != "manifest" {
		t.Errorf("AndroidManifest.xml = %q", got["AndroidManifest.xml"])
	}
	if got["classes.dex"] != "dex-uno" {
		t.Errorf("classes.dex = %q, quiero dex-uno", got["classes.dex"])
	}
	if got["classes2.dex"] != "dex-dos" {
		t.Errorf("classes2.dex = %q, quiero dex-dos", got["classes2.dex"])
	}
}

func writeZip(t *testing.T, path string, entries map[string]string) {
	t.Helper()
	f, err := os.Create(path)
	if err != nil {
		t.Fatal(err)
	}
	defer func() { _ = f.Close() }()

	w := zip.NewWriter(f)
	for name, content := range entries {
		entry, err := w.Create(name)
		if err != nil {
			t.Fatal(err)
		}
		if _, err := entry.Write([]byte(content)); err != nil {
			t.Fatal(err)
		}
	}
	if err := w.Close(); err != nil {
		t.Fatal(err)
	}
}

func readZip(t *testing.T, path string) map[string]string {
	t.Helper()
	zr, err := zip.OpenReader(path)
	if err != nil {
		t.Fatal(err)
	}
	defer func() { _ = zr.Close() }()

	out := make(map[string]string)
	for _, f := range zr.File {
		rc, err := f.Open()
		if err != nil {
			t.Fatal(err)
		}
		var buf bytes.Buffer
		if _, err := buf.ReadFrom(rc); err != nil {
			t.Fatal(err)
		}
		_ = rc.Close()
		out[f.Name] = buf.String()
	}
	return out
}
