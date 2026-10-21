package resolver

import (
	"archive/zip"
	"net/http"
	"net/http/httptest"
	"os"
	"path/filepath"
	"testing"
)

func TestDownloadAARInvalidCoordinate(t *testing.T) {
	if _, err := DownloadAAR("solo:dos", t.TempDir()); err == nil {
		t.Fatal("DownloadAAR con coordenada inválida debería fallar")
	}
}

func TestDownloadArtifactInvalidCoordinate(t *testing.T) {
	if _, err := DownloadArtifact("solo:dos", t.TempDir()); err == nil {
		t.Fatal("DownloadArtifact con coordenada inválida debería fallar")
	}
}

func TestDownloadAARExtractsClassesJar(t *testing.T) {
	aar := buildZip(t, map[string]string{
		"AndroidManifest.xml":   "<manifest/>",
		"classes.jar":           "contenido-jar",
		"res/values/values.xml": "<resources/>",
	})

	server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		if r.URL.Path != "/androidx/core/core/1.12.0/core-1.12.0.aar" {
			http.NotFound(w, r)
			return
		}
		_, _ = w.Write(aar)
	}))
	defer server.Close()
	swapRepositories(t, server.URL)

	destDir := t.TempDir()
	path, err := DownloadAAR("androidx.core:core:1.12.0", destDir)
	if err != nil {
		t.Fatalf("DownloadAAR: %v", err)
	}
	if want := filepath.Join(destDir, "core-1.12.0-classes.jar"); path != want {
		t.Errorf("ruta = %q, quiero %q", path, want)
	}
	data, err := os.ReadFile(path)
	if err != nil {
		t.Fatalf("leyendo classes.jar: %v", err)
	}
	if string(data) != "contenido-jar" {
		t.Errorf("classes.jar = %q, quiero contenido-jar", data)
	}
	// El AAR temporal debe eliminarse.
	if _, err := os.Stat(filepath.Join(destDir, "core-1.12.0.aar")); err == nil {
		t.Error("el AAR temporal no debería quedar en disco")
	}
}

func TestDownloadAARWithoutClassesJar(t *testing.T) {
	aar := buildZip(t, map[string]string{"AndroidManifest.xml": "<manifest/>"})

	server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		_, _ = w.Write(aar)
	}))
	defer server.Close()
	swapRepositories(t, server.URL)

	if _, err := DownloadAAR("androidx.core:core:1.12.0", t.TempDir()); err == nil {
		t.Fatal("DownloadAAR debería fallar si el AAR no trae classes.jar")
	}
}

func TestDownloadAARNotFound(t *testing.T) {
	server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		http.NotFound(w, r)
	}))
	defer server.Close()
	swapRepositories(t, server.URL)

	if _, err := DownloadAAR("androidx.core:core:1.12.0", t.TempDir()); err == nil {
		t.Fatal("DownloadAAR debería fallar con 404")
	}
}

func TestDownloadArtifactJAR(t *testing.T) {
	jar := buildZip(t, map[string]string{"com/x/Y.class": "bytecode"})

	server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		if r.URL.Path != "/org/jetbrains/kotlin/kotlin-stdlib/2.4.21/kotlin-stdlib-2.4.21.jar" {
			http.NotFound(w, r)
			return
		}
		_, _ = w.Write(jar)
	}))
	defer server.Close()
	swapRepositories(t, server.URL)

	destDir := t.TempDir()
	path, err := DownloadArtifact("org.jetbrains.kotlin:kotlin-stdlib:2.4.21", destDir)
	if err != nil {
		t.Fatalf("DownloadArtifact: %v", err)
	}
	if want := filepath.Join(destDir, "kotlin-stdlib-2.4.21.jar"); path != want {
		t.Errorf("ruta = %q, quiero %q", path, want)
	}
	if _, err := os.Stat(path); err != nil {
		t.Errorf("el JAR descargado no existe: %v", err)
	}
}

func TestDownloadArtifactPrefersAAR(t *testing.T) {
	aar := buildZip(t, map[string]string{
		"classes.jar":            "aar-jar",
		"AndroidManifest.xml":    "<manifest/>",
		"res/values/strings.xml": "<resources/>",
	})

	var jarRequested bool
	server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		if filepath.Ext(r.URL.Path) == ".jar" {
			jarRequested = true
			_, _ = w.Write(buildZip(t, map[string]string{"a.class": "jar"}))
			return
		}
		_, _ = w.Write(aar)
	}))
	defer server.Close()
	swapRepositories(t, server.URL)

	destDir := t.TempDir()
	path, err := DownloadArtifact("androidx.core:core:1.12.0", destDir)
	if err != nil {
		t.Fatalf("DownloadArtifact: %v", err)
	}
	if want := filepath.Join(destDir, "core-1.12.0-classes.jar"); path != want {
		t.Errorf("ruta = %q, quiero classes.jar del AAR", path)
	}
	data, err := os.ReadFile(path)
	if err != nil {
		t.Fatal(err)
	}
	if string(data) != "aar-jar" {
		t.Errorf("contenido = %q, quiero aar-jar", data)
	}
	if jarRequested {
		t.Error("no debería pedir el .jar si el .aar existe")
	}
}

func TestDownloadArtifactFallsBackToJAR(t *testing.T) {
	jar := buildZip(t, map[string]string{"a.class": "jar"})

	server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		if filepath.Ext(r.URL.Path) != ".jar" {
			http.NotFound(w, r)
			return
		}
		_, _ = w.Write(jar)
	}))
	defer server.Close()
	swapRepositories(t, server.URL)

	destDir := t.TempDir()
	path, err := DownloadArtifact("com.demo:lib:1.0", destDir)
	if err != nil {
		t.Fatalf("DownloadArtifact debería caer al JAR: %v", err)
	}
	if want := filepath.Join(destDir, "lib-1.0.jar"); path != want {
		t.Errorf("ruta = %q, quiero %q", path, want)
	}
}

func TestDownloadArtifactNotFound(t *testing.T) {
	server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		http.NotFound(w, r)
	}))
	defer server.Close()
	swapRepositories(t, server.URL)

	if _, err := DownloadArtifact("com.demo:lib:1.0", t.TempDir()); err == nil {
		t.Fatal("DownloadArtifact debería fallar si no hay AAR ni JAR")
	}
}

func TestDownloadArtifactSecondRepository(t *testing.T) {
	jar := buildZip(t, map[string]string{"a.class": "jar"})

	first := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		http.NotFound(w, r)
	}))
	defer first.Close()

	second := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		_, _ = w.Write(jar)
	}))
	defer second.Close()

	swapRepositories(t, first.URL, second.URL)

	path, err := DownloadArtifact("com.demo:lib:1.0", t.TempDir())
	if err != nil {
		t.Fatalf("DownloadArtifact debería usar el segundo repositorio: %v", err)
	}
	if filepath.Base(path) != "lib-1.0.jar" {
		t.Errorf("ruta = %q", path)
	}
}

func buildZip(t *testing.T, entries map[string]string) []byte {
	t.Helper()
	path := filepath.Join(t.TempDir(), "build.zip")
	f, err := os.Create(path)
	if err != nil {
		t.Fatal(err)
	}
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
	if err := f.Close(); err != nil {
		t.Fatal(err)
	}
	data, err := os.ReadFile(path)
	if err != nil {
		t.Fatal(err)
	}
	return data
}

// swapRepositories sustituye temporalmente la lista de repositorios por URLs de test.
func swapRepositories(t *testing.T, urls ...string) {
	t.Helper()
	original := repositories
	repositories = urls
	t.Cleanup(func() { repositories = original })
}
