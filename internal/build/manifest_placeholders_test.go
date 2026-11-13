package build

import (
	"os"
	"path/filepath"
	"strings"
	"testing"
)

// manifestConMarcadores es el manifest que declaran las librerías de
// AndroidX: usan "${applicationId}" porque no conocen el id final de la app.
const manifestConMarcadores = `<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.ejemplo.lib">
    <uses-permission android:name="${applicationId}.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION" />
    <application>
        <provider
            android:name="androidx.startup.InitializationProvider"
            android:authorities="${applicationId}.androidx-startup"
            android:exported="false" />
    </application>
</manifest>
`

func testConfig() Config {
	return Config{
		Package:     "com.ejemplo.app",
		VersionName: "1.0",
		VersionCode: 1,
		MinSdk:      26,
		TargetSdk:   36,
	}
}

// TestSubstituteManifestPlaceholdersResuelveElIdDeLaApp comprueba que los
// marcadores de las librerías se sustituyen: sin esto, el APK declara un
// permiso con un nombre literal y el provider de AndroidX Startup no
// encuentra su paquete.
func TestSubstituteManifestPlaceholdersResuelveElIdDeLaApp(t *testing.T) {
	dir := t.TempDir()
	src := filepath.Join(dir, "manifest.xml")
	writeFile(t, src, manifestConMarcadores)

	dst := filepath.Join(dir, "resuelto.xml")
	got, err := substituteManifestPlaceholders(src, dst, testConfig())
	if err != nil {
		t.Fatalf("substituteManifestPlaceholders: %v", err)
	}
	if got != dst {
		t.Fatalf("no se escribió el fichero resuelto: %q", got)
	}

	data, err := os.ReadFile(dst)
	if err != nil {
		t.Fatal(err)
	}
	content := string(data)
	if strings.Contains(content, "${") {
		t.Errorf("quedan marcadores sin sustituir:\n%s", content)
	}
	if !strings.Contains(content, "com.ejemplo.app.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION") {
		t.Errorf("el permiso no lleva el id de la app:\n%s", content)
	}
	if !strings.Contains(content, "com.ejemplo.app.androidx-startup") {
		t.Errorf("la autoridad del provider no lleva el id de la app:\n%s", content)
	}
}

// TestSubstituteManifestPlaceholdersAadeElPackageSiFalta cubre el manifest
// con el estilo de AGP, que ya no lleva el atributo "package".
func TestSubstituteManifestPlaceholdersAadeElPackageSiFalta(t *testing.T) {
	dir := t.TempDir()
	src := filepath.Join(dir, "manifest.xml")
	writeFile(t, src, `<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <uses-permission android:name="${applicationId}.PERM" />
</manifest>
`)

	dst := filepath.Join(dir, "resuelto.xml")
	if _, err := substituteManifestPlaceholders(src, dst, testConfig()); err != nil {
		t.Fatalf("substituteManifestPlaceholders: %v", err)
	}

	data, err := os.ReadFile(dst)
	if err != nil {
		t.Fatal(err)
	}
	content := string(data)
	if !strings.Contains(content, `package="com.ejemplo.app"`) {
		t.Errorf("no se añadió el atributo package:\n%s", content)
	}
}

// TestSubstituteManifestPlaceholdersNoTocaElFicheroOriginal comprueba que el
// manifest del proyecto nunca se modifica en sitio.
func TestSubstituteManifestPlaceholdersNoTocaElFicheroOriginal(t *testing.T) {
	dir := t.TempDir()
	src := filepath.Join(dir, "manifest.xml")
	writeFile(t, src, manifestConMarcadores)

	dst := filepath.Join(dir, "resuelto.xml")
	if _, err := substituteManifestPlaceholders(src, dst, testConfig()); err != nil {
		t.Fatalf("substituteManifestPlaceholders: %v", err)
	}

	data, err := os.ReadFile(src)
	if err != nil {
		t.Fatal(err)
	}
	if !strings.Contains(string(data), "${applicationId}") {
		t.Errorf("el manifest original se modificó:\n%s", data)
	}
}

// TestSubstituteManifestPlaceholdersSinMarcadoresDevuelveElOriginal
// comprueba que un manifest que no necesita cambios no genera un fichero
// nuevo: así el pipeline no toca el manifest de la app si no hay nada que
// sustituir.
func TestSubstituteManifestPlaceholdersSinMarcadoresDevuelveElOriginal(t *testing.T) {
	dir := t.TempDir()
	src := filepath.Join(dir, "manifest.xml")
	writeFile(t, src, `<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.ejemplo.app">
</manifest>
`)

	got, err := substituteManifestPlaceholders(src, filepath.Join(dir, "resuelto.xml"), testConfig())
	if err != nil {
		t.Fatalf("substituteManifestPlaceholders: %v", err)
	}
	if got != src {
		t.Errorf("se debería devolver el manifest original, se devolvió %q", got)
	}
}
