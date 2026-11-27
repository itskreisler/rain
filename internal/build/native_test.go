package build

import (
	"os"
	"path/filepath"
	"runtime"
	"strings"
	"testing"

	"github.com/itskreisler/rain/internal/config"
)

// fakeNDK crea un directorio que toolchain.FindNDK acepta, para que los tests no
// descarguen el NDK real.
func fakeNDK(t *testing.T, root string) string {
	t.Helper()
	ndk := filepath.Join(root, "ndk")
	writeHashFile(t, filepath.Join(ndk, "toolchains", "llvm", "prebuilt", "linux-x86_64", "bin", "clang"), "#!/bin/sh\n")
	t.Setenv("ANDROID_NDK_HOME", ndk)
	return ndk
}

func TestPrepareNativeCacheaPorEntradas(t *testing.T) {
	if runtime.GOOS == "windows" {
		t.Skip("el script de prueba usa sh")
	}
	root := t.TempDir()
	fakeNDK(t, root)
	app := filepath.Join(root, "app")
	buildDir := filepath.Join(app, "build")
	script := filepath.Join(root, "native", "build.sh")
	writeHashFile(t, script, `#!/bin/sh
echo run >> ejecuciones.txt
mkdir -p app/src/main/jniLibs/arm64-v8a
echo so > app/src/main/jniLibs/arm64-v8a/libtest.so
`)
	if err := os.Chmod(script, 0o755); err != nil {
		t.Fatal(err)
	}
	cfg := Config{
		ProjectDir: root,
		AppDir:     app,
		BuildDir:   buildDir,
		Native:     config.Native{Script: "native/build.sh"},
	}

	if err := cfg.prepareNative(); err != nil {
		t.Fatalf("primer prepareNative: %v", err)
	}
	if err := cfg.prepareNative(); err != nil {
		t.Fatalf("segundo prepareNative: %v", err)
	}
	runs, err := os.ReadFile(filepath.Join(root, "ejecuciones.txt"))
	if err != nil {
		t.Fatal(err)
	}
	if string(runs) != "run\n" {
		t.Errorf("el script se ejecutó %d veces, quiero 1", strings.Count(string(runs), "run"))
	}

	writeHashFile(t, filepath.Join(root, "native", "foo.c"), "int main(void) { return 0; }\n")
	if err := cfg.prepareNative(); err != nil {
		t.Fatalf("prepareNative tras cambiar una fuente: %v", err)
	}
	runs, err = os.ReadFile(filepath.Join(root, "ejecuciones.txt"))
	if err != nil {
		t.Fatal(err)
	}
	if strings.Count(string(runs), "run") != 2 {
		t.Errorf("cambiar una fuente no reejecutó el script: %q", runs)
	}
}

func TestPrepareNativeFallaSiNoHaySo(t *testing.T) {
	if runtime.GOOS == "windows" {
		t.Skip("el script de prueba usa sh")
	}
	root := t.TempDir()
	fakeNDK(t, root)
	app := filepath.Join(root, "app")
	script := filepath.Join(root, "native", "build.sh")
	writeHashFile(t, script, "#!/bin/sh\necho listo\n")

	cfg := Config{
		ProjectDir: root,
		AppDir:     app,
		BuildDir:   filepath.Join(app, "build"),
		Native:     config.Native{Script: "native/build.sh"},
	}
	err := cfg.prepareNative()
	if err == nil || !strings.Contains(err.Error(), "no generó .so") {
		t.Fatalf("prepareNative sin .so = %v, quiero el error de salidas", err)
	}
}

func TestPrepareNativeRespetaNoNativeLibs(t *testing.T) {
	if runtime.GOOS == "windows" {
		t.Skip("el script de prueba usa sh")
	}
	root := t.TempDir()
	fakeNDK(t, root)
	app := filepath.Join(root, "app")
	script := filepath.Join(root, "native", "build.sh")
	writeHashFile(t, script, "#!/bin/sh\necho no_debe_ejecutarse > marcador\n")

	cfg := Config{
		ProjectDir:   root,
		AppDir:       app,
		BuildDir:     filepath.Join(app, "build"),
		Native:       config.Native{Script: "native/build.sh"},
		NoNativeLibs: true,
	}
	if err := cfg.prepareNative(); err != nil {
		t.Fatalf("prepareNative con NoNativeLibs: %v", err)
	}
	if fileExists(filepath.Join(root, "marcador")) {
		t.Error("NoNativeLibs ejecutó el script nativo")
	}
}
