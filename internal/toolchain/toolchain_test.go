package toolchain

import (
	"archive/zip"
	"context"
	"net/http"
	"net/http/httptest"
	"os"
	"path/filepath"
	"runtime"
	"strings"
	"testing"
)

func wrapperName(name string) string {
	if runtime.GOOS == "windows" {
		return name + ".cmd"
	}
	return name
}

func TestNewUsesHome(t *testing.T) {
	home := t.TempDir()
	t.Setenv("HOME", home)
	if runtime.GOOS == "windows" {
		t.Setenv("USERPROFILE", home)
	}

	manager, err := New()
	if err != nil {
		t.Fatalf("New: %v", err)
	}
	if want := filepath.Join(home, ".rain"); manager.Root != want {
		t.Errorf("Root = %q, quiero %q", manager.Root, want)
	}
	if manager.BinDir() != filepath.Join(home, ".rain", "bin") {
		t.Errorf("BinDir = %q", manager.BinDir())
	}
	if manager.AndroidJar() != filepath.Join(home, ".rain", "toolchain", "android.jar") {
		t.Errorf("AndroidJar = %q", manager.AndroidJar())
	}
}

func TestEnsureIsIdempotentWithCachedFiles(t *testing.T) {
	home := t.TempDir()
	t.Setenv("HOME", home)
	if runtime.GOOS == "windows" {
		t.Setenv("USERPROFILE", home)
	}

	manager, err := New()
	if err != nil {
		t.Fatal(err)
	}
	// Servidor que falla: si los archivos ya existen, no debe llamarse.
	server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		t.Errorf("no debería descargar %s: los archivos ya están en caché", r.URL.Path)
		http.Error(w, "no", http.StatusInternalServerError)
	}))
	defer server.Close()
	manager.Client = server.Client()

	if err := os.MkdirAll(manager.ToolDir(), 0o755); err != nil {
		t.Fatal(err)
	}
	for _, path := range []string{
		manager.R8Jar(), manager.ApksignerJar(), manager.AndroidJar(), manager.Aapt2(), manager.ZipAlign(),
		filepath.Join(manager.ToolDir(), "lib64", "libc++.so"),
		filepath.Join(manager.ToolDir(), "lib64", "libc++.so.1"),
	} {
		if err := os.MkdirAll(filepath.Dir(path), 0o755); err != nil {
			t.Fatal(err)
		}
		if err := os.WriteFile(path, []byte("fake"), 0o644); err != nil {
			t.Fatal(err)
		}
	}

	if err := manager.Ensure(context.Background(), false); err != nil {
		t.Fatalf("Ensure con caché: %v", err)
	}

	for _, name := range []string{wrapperName("d8"), wrapperName("apksigner")} {
		info, err := os.Stat(filepath.Join(manager.BinDir(), name))
		if err != nil {
			t.Fatalf("wrapper %s no creado: %v", name, err)
		}
		// Windows no expone el bit de ejecución: Go informa 0666 para
		// cualquier fichero que no tenga el atributo de solo lectura.
		if runtime.GOOS != "windows" && info.Mode().Perm() != 0o755 {
			t.Errorf("wrapper %s permisos = %v, quiero 0755", name, info.Mode().Perm())
		}
	}
}

func TestPublishNativeBinariesUsaWrapperParaZipalign(t *testing.T) {
	manager, err := New()
	if err != nil {
		t.Fatal(err)
	}
	manager.Root = filepath.Join(t.TempDir(), ".rain")
	if err := os.MkdirAll(manager.ToolDir(), 0o755); err != nil {
		t.Fatal(err)
	}
	for _, name := range []string{nativeNameFor("aapt2", runtime.GOOS), nativeNameFor("zipalign", runtime.GOOS)} {
		if err := os.WriteFile(filepath.Join(manager.ToolDir(), name), []byte("BINARIO-DESCARTADO"), 0o755); err != nil {
			t.Fatal(err)
		}
	}
	if err := manager.publishNativeBinariesFor(true); err != nil {
		t.Fatal(err)
	}
	out, err := os.ReadFile(filepath.Join(manager.BinDir(), nativeNameFor("zipalign", runtime.GOOS)))
	if err != nil {
		t.Fatal(err)
	}
	if runtime.GOOS == "windows" {
		return
	}
	// El contenido del binario se descarta por completo: solo se publica el
	// wrapper, sin leer el ZIP ni copiar el ejecutable original.
	if string(out) != zipalignWrapper {
		t.Errorf("wrapper de zipalign inesperado:\n%s", out)
	}
	if strings.Contains(string(out), "BINARIO-DESCARTADO") {
		t.Error("el wrapper de zipalign conservó contenido del binario original")
	}
}

func TestNativeBuildToolsSoloEnPlataformasConSoporte(t *testing.T) {
	tests := []struct {
		goos, goarch string
		want         bool
	}{
		{"linux", "amd64", true},
		{"linux", "arm64", false},
		{"linux", "386", false},
		{"windows", "amd64", true},
		{"windows", "arm64", true},
		{"darwin", "arm64", true},
	}
	for _, tc := range tests {
		if got := nativeBuildToolsAvailableFor(tc.goos, tc.goarch); got != tc.want {
			t.Errorf("nativeBuildToolsAvailableFor(%q,%q) = %t, quiero %t", tc.goos, tc.goarch, got, tc.want)
		}
	}
}

func TestPublishNativeBinariesLimpiaCopiasAjenas(t *testing.T) {
	manager, err := New()
	if err != nil {
		t.Fatal(err)
	}
	manager.Root = filepath.Join(t.TempDir(), ".rain")
	if err := os.MkdirAll(manager.BinDir(), 0o755); err != nil {
		t.Fatal(err)
	}
	if err := os.MkdirAll(manager.ToolDir(), 0o755); err != nil {
		t.Fatal(err)
	}
	for _, dir := range []string{manager.BinDir(), manager.ToolDir()} {
		for _, name := range []string{"aapt2", "zipalign"} {
			if err := os.WriteFile(filepath.Join(dir, name), []byte("x86-64"), 0o755); err != nil {
				t.Fatal(err)
			}
		}
	}

	if err := manager.publishNativeBinariesFor(false); err != nil {
		t.Fatal(err)
	}
	for _, dir := range []string{manager.BinDir(), manager.ToolDir()} {
		for _, name := range []string{"aapt2", "zipalign"} {
			path := filepath.Join(dir, name)
			if _, err := os.Stat(path); !os.IsNotExist(err) {
				t.Errorf("%s sigue presente: %v", path, err)
			}
		}
	}
}

func TestWindowsToolchainPathsAndWrappers(t *testing.T) {
	if got := nativeNameFor("aapt2", "windows"); got != "aapt2.exe" {
		t.Errorf("nativeNameFor(aapt2, windows) = %q, quiero aapt2.exe", got)
	}
	if got := nativeNameFor("zipalign", "linux"); got != "zipalign" {
		t.Errorf("nativeNameFor(zipalign, linux) = %q, quiero zipalign", got)
	}
	if got := buildToolsURLFor("windows"); !strings.HasSuffix(got, "build-tools_r36_windows.zip") {
		t.Errorf("buildToolsURLFor(windows) = %q", got)
	}
	if got := buildToolsURLFor("darwin"); !strings.HasSuffix(got, "build-tools_r36_macosx.zip") {
		t.Errorf("buildToolsURLFor(darwin) = %q", got)
	}

	manager, err := New()
	if err != nil {
		t.Fatal(err)
	}
	manager.Root = filepath.Join(t.TempDir(), ".rain")
	if err := manager.writeWindowsWrappers(true); err != nil {
		t.Fatal(err)
	}
	for _, name := range []string{"d8.cmd", "apksigner.cmd", "kotlinc.cmd"} {
		out, err := os.ReadFile(filepath.Join(manager.BinDir(), name))
		if err != nil {
			t.Fatalf("wrapper %s no creado: %v", name, err)
		}
		if !strings.Contains(string(out), "@echo off") {
			t.Errorf("el wrapper %s no es un .cmd válido:\n%s", name, out)
		}
	}
}

func TestLibcxxSoloEnLinux(t *testing.T) {
	for goos, want := range map[string]bool{
		"linux":   true,
		"windows": false,
		"darwin":  false,
	} {
		if got := needsLibcxx(goos); got != want {
			t.Errorf("needsLibcxx(%q) = %t, quiero %t", goos, got, want)
		}
	}
}

func TestExtractBuildToolsUnSoloRecorrido(t *testing.T) {
	dir := t.TempDir()
	zipPath := filepath.Join(dir, "build-tools.zip")
	writeZipFile(t, zipPath, map[string]string{
		// Un apksigner fuera de lib/ no debe tapar al que sí sirve.
		"otro/apksigner.jar":                   "no sirve",
		"android-16/lib/apksigner.jar":         "apksigner",
		"android-16/" + nativeName("aapt2"):    "aapt2",
		"android-16/" + nativeName("zipalign"): "zipalign",
		"android-16/lib64/libc++.so":           "libcxx",
		"android-16/lib64/libc++.so.1":         "libcxx1",
		"android-16/lib/otro.jar":              "ruido",
	})

	dest := func(name string) string { return filepath.Join(dir, "toolchain", name) }
	tools := []buildTool{
		{name: "apksigner.jar", dest: dest("apksigner.jar"), mode: 0o644,
			match: func(n string) bool { return strings.HasSuffix(n, "/lib/apksigner.jar") }},
		{name: nativeName("aapt2"), dest: dest(nativeName("aapt2")), mode: 0o755,
			match: func(n string) bool { return filepath.Base(n) == nativeName("aapt2") }},
		{name: nativeName("zipalign"), dest: dest(nativeName("zipalign")), mode: 0o755,
			match: func(n string) bool { return filepath.Base(n) == nativeName("zipalign") }},
		{name: "libc++.so", dest: dest("lib64/libc++.so"), mode: 0o644,
			match: func(n string) bool { return filepath.Base(n) == "libc++.so" }},
		{name: "libc++.so.1", dest: dest("lib64/libc++.so.1"), mode: 0o644,
			match: func(n string) bool { return filepath.Base(n) == "libc++.so.1" }},
	}
	if err := extractBuildTools(zipPath, tools); err != nil {
		t.Fatalf("extractBuildTools: %v", err)
	}

	for _, tc := range []struct{ path, contenido string }{
		{dest("apksigner.jar"), "apksigner"},
		{dest(nativeName("aapt2")), "aapt2"},
		{dest(nativeName("zipalign")), "zipalign"},
		{dest("lib64/libc++.so"), "libcxx"},
		{dest("lib64/libc++.so.1"), "libcxx1"},
	} {
		data, err := os.ReadFile(tc.path)
		if err != nil {
			t.Fatalf("lectura de %s: %v", tc.path, err)
		}
		if string(data) != tc.contenido {
			t.Errorf("%s = %q, quiero %q", tc.path, data, tc.contenido)
		}
	}
	info, err := os.Stat(dest(nativeName("aapt2")))
	if err != nil {
		t.Fatal(err)
	}
	if runtime.GOOS != "windows" && info.Mode().Perm() != 0o755 {
		t.Errorf("permisos de aapt2 = %v, quiero 0755", info.Mode().Perm())
	}

	// El mensaje de error de una herramienta ausente se conserva.
	missing := buildTool{
		name: "missing.bin", dest: dest("missing.bin"), mode: 0o644,
		match: func(n string) bool { return filepath.Base(n) == "missing.bin" },
	}
	err = extractBuildTools(zipPath, []buildTool{missing})
	want := "missing.bin: no se encontró " + dest("missing.bin") + " dentro del ZIP"
	if err == nil || err.Error() != want {
		t.Errorf("error con herramienta ausente = %v, quiero %q", err, want)
	}
}

func TestExtractOne(t *testing.T) {
	dir := t.TempDir()
	zipPath := filepath.Join(dir, "build-tools.zip")
	writeZipFile(t, zipPath, map[string]string{
		"android-16/lib/d8.jar":        "d8",
		"android-16/lib/apksigner.jar": "apksigner",
		"android-16/aapt2":             "binario",
	})

	dest := filepath.Join(dir, "out", "apksigner.jar")
	err := extractOne(zipPath, func(name string) bool {
		return filepath.Base(name) == "apksigner.jar"
	}, dest)
	if err != nil {
		t.Fatalf("extractOne: %v", err)
	}

	data, err := os.ReadFile(dest)
	if err != nil {
		t.Fatal(err)
	}
	if string(data) != "apksigner" {
		t.Errorf("contenido = %q, quiero apksigner", data)
	}
}

func TestExtractOneNotFound(t *testing.T) {
	dir := t.TempDir()
	zipPath := filepath.Join(dir, "x.zip")
	writeZipFile(t, zipPath, map[string]string{"a.txt": "a"})

	err := extractOne(zipPath, func(name string) bool { return name == "noexiste" }, filepath.Join(dir, "out"))
	if err == nil {
		t.Fatal("extractOne debería fallar si no encuentra la entrada")
	}
}

func TestExtractPrefix(t *testing.T) {
	dir := t.TempDir()
	zipPath := filepath.Join(dir, "kotlin.zip")
	writeZipFile(t, zipPath, map[string]string{
		"kotlinc/bin/kotlinc":           "#!/bin/sh\n",
		"kotlinc/lib/kotlin-stdlib.jar": "stdlib",
		"otro/archivo.txt":              "no copiar",
	})

	destDir := filepath.Join(dir, "toolchain", "kotlinc")
	if err := extractPrefix(zipPath, "kotlinc/", destDir); err != nil {
		t.Fatalf("extractPrefix: %v", err)
	}

	if _, err := os.Stat(filepath.Join(destDir, "bin", "kotlinc")); err != nil {
		t.Errorf("falta kotlinc: %v", err)
	}
	if _, err := os.Stat(filepath.Join(destDir, "lib", "kotlin-stdlib.jar")); err != nil {
		t.Errorf("falta kotlin-stdlib.jar: %v", err)
	}
	if _, err := os.Stat(filepath.Join(dir, "toolchain", "kotlinc", "otro")); err == nil {
		t.Error("extractPrefix no debería copiar entradas fuera del prefijo")
	}
}

func TestSafeJoinRejectsZipSlip(t *testing.T) {
	base := t.TempDir()

	if _, err := safeJoin(base, "../fuera.txt"); err == nil {
		t.Error("safeJoin debería rechazar ../fuera.txt")
	}
	if _, err := safeJoin(base, "../../etc/passwd"); err == nil {
		t.Error("safeJoin debería rechazar rutas con traversal")
	}

	got, err := safeJoin(base, "sub/archivo.txt")
	if err != nil {
		t.Fatalf("safeJoin con ruta válida falló: %v", err)
	}
	if want := filepath.Join(base, "sub", "archivo.txt"); got != want {
		t.Errorf("safeJoin = %q, quiero %q", got, want)
	}
}

func TestSafeJoinRejectsAbsoluteEscape(t *testing.T) {
	base := filepath.Join(t.TempDir(), "base")
	if err := os.MkdirAll(base, 0o755); err != nil {
		t.Fatal(err)
	}
	if _, err := safeJoin(base, "../base-evil/x"); err == nil {
		t.Error("safeJoin debería rechazar salir del prefijo base")
	}
}

func TestWrappersContent(t *testing.T) {
	home := t.TempDir()
	t.Setenv("HOME", home)

	manager, err := New()
	if err != nil {
		t.Fatal(err)
	}
	if err := os.MkdirAll(manager.BinDir(), 0o755); err != nil {
		t.Fatal(err)
	}
	if err := manager.writeWrappers(true); err != nil {
		t.Fatalf("writeWrappers: %v", err)
	}

	for _, name := range []string{"d8", "apksigner", "kotlinc"} {
		path := filepath.Join(manager.BinDir(), wrapperName(name))
		data, err := os.ReadFile(path)
		if err != nil {
			t.Fatalf("wrapper %s: %v", name, err)
		}
		if runtime.GOOS == "windows" {
			if len(data) == 0 || !strings.HasPrefix(string(data), "@echo off") {
				t.Errorf("wrapper %s no empieza con @echo off: %q", name, data)
			}
		} else if len(data) == 0 || data[0] != '#' {
			t.Errorf("wrapper %s no empieza con shebang: %q", name, data)
		}
	}

	// En perfil simple no debe crearse el wrapper de kotlinc.
	if err := os.RemoveAll(manager.BinDir()); err != nil {
		t.Fatal(err)
	}
	if err := os.MkdirAll(manager.BinDir(), 0o755); err != nil {
		t.Fatal(err)
	}
	if err := manager.writeWrappers(false); err != nil {
		t.Fatal(err)
	}
	if _, err := os.Stat(filepath.Join(manager.BinDir(), wrapperName("kotlinc"))); err == nil {
		t.Error("writeWrappers(false) no debería crear kotlinc")
	}
}

func writeZipFile(t *testing.T, path string, entries map[string]string) {
	t.Helper()
	f, err := os.Create(path)
	if err != nil {
		t.Fatal(err)
	}
	defer func() { _ = f.Close() }()

	w := zip.NewWriter(f)
	for name, content := range entries {
		header := &zip.FileHeader{Name: name, Method: zip.Deflate}
		if filepath.Base(name) == "kotlinc" {
			header.SetMode(0o755)
		}
		entry, err := w.CreateHeader(header)
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
