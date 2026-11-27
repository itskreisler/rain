package build

import (
	"os"
	"path/filepath"
	"testing"
)

// writeHashFile escribe un fichero creando los directorios necesarios.
func writeHashFile(t *testing.T, path, content string) {
	t.Helper()
	if err := os.MkdirAll(filepath.Dir(path), 0o755); err != nil {
		t.Fatalf("creando %s: %v", filepath.Dir(path), err)
	}
	if err := os.WriteFile(path, []byte(content), 0o644); err != nil {
		t.Fatalf("escribiendo %s: %v", path, err)
	}
}

func TestComputeConfigJavaHashIncluyeFlavor(t *testing.T) {
	dir := t.TempDir()
	app := filepath.Join(dir, "app")
	writeHashFile(t, filepath.Join(app, "src", "main", "AndroidManifest.xml"), "<manifest/>")
	flavorJava := filepath.Join(app, "src", "github", "java", "app", "buddy", "Flavor.java")
	writeHashFile(t, flavorJava, "class Flavor { static final boolean FDROID = false; }")

	cfg := Config{AppDir: app, LibsDir: filepath.Join(dir, "libs"), Flavor: "github"}
	gen := filepath.Join(app, "build", "gen")
	primero, err := computeConfigJavaHash(cfg, gen)
	if err != nil {
		t.Fatalf("computeConfigJavaHash: %v", err)
	}
	writeHashFile(t, flavorJava, "class Flavor { static final boolean FDROID = true; }")
	segundo, err := computeConfigJavaHash(cfg, gen)
	if err != nil {
		t.Fatalf("computeConfigJavaHash: %v", err)
	}
	if primero == segundo {
		t.Errorf("cambiar una fuente de la variante no invalidó el hash: %s", primero)
	}

	// Un nombre de variante con traversal no debe salir del módulo.
	if got := flavorSourceDir(app, "../fdroid"); got != "" {
		t.Errorf("flavorSourceDir con traversal = %q, quiero vacío", got)
	}
}

func TestComputeJavaHashIncluyeJniLibs(t *testing.T) {
	dir := t.TempDir()
	app := filepath.Join(dir, "app")
	so := filepath.Join(app, "src", "main", "jniLibs", "arm64-v8a", "libfoo.so")
	writeHashFile(t, filepath.Join(app, "src", "main", "AndroidManifest.xml"), "<manifest/>")
	writeHashFile(t, so, "primero")

	primero, err := computeJavaHash(app, filepath.Join(dir, "libs"), filepath.Join(app, "build", "gen"), false)
	if err != nil {
		t.Fatalf("computeJavaHash: %v", err)
	}
	writeHashFile(t, so, "segundo")
	segundo, err := computeJavaHash(app, filepath.Join(dir, "libs"), filepath.Join(app, "build", "gen"), false)
	if err != nil {
		t.Fatalf("computeJavaHash: %v", err)
	}
	if primero == segundo {
		t.Errorf("cambiar una .so no invalidó el hash: %s", primero)
	}
}

func TestComputeDexDepsHashInvalidaConMinSdkYJars(t *testing.T) {
	dir := t.TempDir()
	androidJar := filepath.Join(dir, "android.jar")
	dep := filepath.Join(dir, "dep.jar")
	writeHashFile(t, androidJar, "android")
	writeHashFile(t, dep, "dep")

	first, err := computeDexDepsHash(androidJar, 21, []string{dep})
	if err != nil {
		t.Fatalf("computeDexDepsHash: %v", err)
	}

	// Sin cambios: la clave debe ser estable (si no, la caché nunca acierta).
	again, err := computeDexDepsHash(androidJar, 21, []string{dep})
	if err != nil {
		t.Fatalf("computeDexDepsHash: %v", err)
	}
	if first != again {
		t.Errorf("hash inestable: %s != %s", first, again)
	}

	// Cambia el min-api.
	otherApi, err := computeDexDepsHash(androidJar, 26, []string{dep})
	if err != nil {
		t.Fatalf("computeDexDepsHash: %v", err)
	}
	if otherApi == first {
		t.Error("el hash no cambió al cambiar min-api")
	}

	// Cambia una dependencia.
	writeHashFile(t, dep, "dep-v2")
	otherDep, err := computeDexDepsHash(androidJar, 21, []string{dep})
	if err != nil {
		t.Fatalf("computeDexDepsHash: %v", err)
	}
	if otherDep == first {
		t.Error("el hash no cambió al cambiar una dependencia")
	}

	// Cambia android.jar.
	writeHashFile(t, androidJar, "android-v2")
	otherAndroid, err := computeDexDepsHash(androidJar, 21, []string{dep})
	if err != nil {
		t.Fatalf("computeDexDepsHash: %v", err)
	}
	if otherAndroid == otherDep {
		t.Error("el hash no cambió al cambiar android.jar")
	}
}

func TestComputeResourceHashCambiaConElContenido(t *testing.T) {
	dir := t.TempDir()
	app := filepath.Join(dir, "app")

	writeHashFile(t, filepath.Join(app, "src", "main", "AndroidManifest.xml"),
		`<manifest package="com.x"/>`)
	writeHashFile(t, filepath.Join(app, "src", "main", "res", "values", "strings.xml"),
		`<resources><string name="a">1</string></resources>`)

	first, err := computeResourceHash(app, "")
	if err != nil {
		t.Fatalf("computeResourceHash: %v", err)
	}
	if first == "" {
		t.Fatal("computeResourceHash devolvió un hash vacío")
	}

	// Sin cambios: el hash debe ser estable.
	second, err := computeResourceHash(app, "")
	if err != nil {
		t.Fatalf("computeResourceHash: %v", err)
	}
	if first != second {
		t.Errorf("hash inestable: %s != %s", first, second)
	}

	// Cambia un recurso.
	writeHashFile(t, filepath.Join(app, "src", "main", "res", "values", "strings.xml"),
		`<resources><string name="a">2</string></resources>`)
	changed, err := computeResourceHash(app, "")
	if err != nil {
		t.Fatalf("computeResourceHash: %v", err)
	}
	if changed == first {
		t.Error("el hash no cambió al modificar un recurso")
	}

	// Cambia el manifest.
	writeHashFile(t, filepath.Join(app, "src", "main", "AndroidManifest.xml"),
		`<manifest package="com.y"/>`)
	changedManifest, err := computeResourceHash(app, "")
	if err != nil {
		t.Fatalf("computeResourceHash: %v", err)
	}
	if changedManifest == changed {
		t.Error("el hash no cambió al modificar el manifest")
	}
}

func TestComputeResourceHashIncluyeAssets(t *testing.T) {
	dir := t.TempDir()
	app := filepath.Join(dir, "app")
	writeHashFile(t, filepath.Join(app, "src", "main", "AndroidManifest.xml"), `<manifest package="com.x"/>`)
	assets := filepath.Join(app, "src", "main", "assets")
	runTS := filepath.Join(assets, "node", "run.ts")
	writeHashFile(t, runTS, "console.log(1)")

	primero, err := computeResourceHash(app, "")
	if err != nil {
		t.Fatalf("computeResourceHash: %v", err)
	}
	writeHashFile(t, runTS, "console.log(2)")
	segundo, err := computeResourceHash(app, "")
	if err != nil {
		t.Fatalf("computeResourceHash: %v", err)
	}
	if primero == segundo {
		t.Error("cambiar un asset no invalidó el hash de recursos")
	}
}

func TestComputeResourceHashConSymlink(t *testing.T) {
	dir := t.TempDir()
	app := filepath.Join(dir, "app")
	writeHashFile(t, filepath.Join(app, "src", "main", "AndroidManifest.xml"), `<manifest package="com.x"/>`)
	assets := filepath.Join(app, "src", "main", "assets")
	writeHashFile(t, filepath.Join(assets, "linux", "real.txt"), "x")
	link := filepath.Join(assets, "plataforma")
	if err := os.Symlink(filepath.Join(assets, "linux"), link); err != nil {
		t.Skipf("el sistema no permite symlinks: %v", err)
	}

	primero, err := computeResourceHash(app, "")
	if err != nil {
		t.Fatalf("computeResourceHash con symlink: %v", err)
	}
	if err := os.Remove(link); err != nil {
		t.Fatal(err)
	}
	if err := os.Symlink(filepath.Join(assets, "otra"), link); err != nil {
		t.Fatal(err)
	}
	segundo, err := computeResourceHash(app, "")
	if err != nil {
		t.Fatalf("computeResourceHash tras cambiar el symlink: %v", err)
	}
	if primero == segundo {
		t.Error("cambiar el destino de un symlink no invalidó el hash")
	}
}

func TestComputeConfigJavaHashIncluyeNativeLibsDir(t *testing.T) {
	dir := t.TempDir()
	app := filepath.Join(dir, "app")
	writeHashFile(t, filepath.Join(app, "src", "main", "AndroidManifest.xml"), "<manifest/>")
	extra := filepath.Join(dir, "build", "jni", "arm64-v8a")
	so := filepath.Join(extra, "libnode.so")
	writeHashFile(t, so, "vieja")

	cfg := Config{AppDir: app, ProjectDir: dir, LibsDir: filepath.Join(dir, "libs"), NativeLibsDir: "build/jni"}
	gen := filepath.Join(app, "build", "gen")
	primero, err := computeConfigJavaHash(cfg, gen)
	if err != nil {
		t.Fatalf("computeConfigJavaHash: %v", err)
	}
	writeHashFile(t, so, "nueva")
	segundo, err := computeConfigJavaHash(cfg, gen)
	if err != nil {
		t.Fatalf("computeConfigJavaHash: %v", err)
	}
	if primero == segundo {
		t.Error("cambiar una .so externa no invalidó el hash")
	}
}

func TestComputeResourceHashSinRecursos(t *testing.T) {
	dir := t.TempDir()
	app := filepath.Join(dir, "app")
	writeHashFile(t, filepath.Join(app, "src", "main", "AndroidManifest.xml"),
		`<manifest package="com.x"/>`)

	// Un proyecto sin res/ es válido: no debe fallar.
	hash, err := computeResourceHash(app, "")
	if err != nil {
		t.Fatalf("computeResourceHash sin res/: %v", err)
	}
	if hash == "" {
		t.Error("hash vacío sin recursos")
	}
}

func TestComputeJavaHashCambiaConElContenido(t *testing.T) {
	dir := t.TempDir()
	app := filepath.Join(dir, "app")
	libs := filepath.Join(dir, "libs")
	gen := filepath.Join(dir, "gen")

	writeHashFile(t, filepath.Join(app, "java", "com", "x", "Main.kt"), "package com.x\n")
	writeHashFile(t, filepath.Join(gen, "com", "x", "BuildConfig.java"), "package com.x;\n")
	writeHashFile(t, filepath.Join(libs, "dep.jar"), "contenido")

	first, err := computeJavaHash(app, libs, gen, false)
	if err != nil {
		t.Fatalf("computeJavaHash: %v", err)
	}

	second, err := computeJavaHash(app, libs, gen, false)
	if err != nil {
		t.Fatalf("computeJavaHash: %v", err)
	}
	if first != second {
		t.Errorf("hash inestable: %s != %s", first, second)
	}

	// Cambia una fuente Kotlin.
	writeHashFile(t, filepath.Join(app, "java", "com", "x", "Main.kt"), "package com.x\n// cambio\n")
	changed, err := computeJavaHash(app, libs, gen, false)
	if err != nil {
		t.Fatalf("computeJavaHash: %v", err)
	}
	if changed == first {
		t.Error("el hash no cambió al modificar una fuente Kotlin")
	}

	// Cambia una dependencia.
	writeHashFile(t, filepath.Join(libs, "dep.jar"), "otro contenido")
	changedLib, err := computeJavaHash(app, libs, gen, false)
	if err != nil {
		t.Fatalf("computeJavaHash: %v", err)
	}
	if changedLib == changed {
		t.Error("el hash no cambió al modificar una dependencia")
	}
}

func TestComputeJavaHashCambiaConLasDependenciasDelSet(t *testing.T) {
	dir := t.TempDir()
	app := filepath.Join(dir, "app")
	libs := filepath.Join(dir, "libs")
	gen := filepath.Join(dir, "gen")
	writeHashFile(t, filepath.Join(app, "java", "com", "x", "Main.kt"), "package com.x\n")

	dep1 := filepath.Join(dir, ".rain-deps", "jars", "core-1.0.jar")
	dep2 := filepath.Join(dir, ".rain-deps", "jars", "core-2.0.jar")
	writeHashFile(t, dep1, "clases de la 1.0")
	writeHashFile(t, dep2, "clases de la 2.0")

	withDep1, err := computeJavaHash(app, libs, gen, false, dep1)
	if err != nil {
		t.Fatalf("computeJavaHash: %v", err)
	}
	withDep2, err := computeJavaHash(app, libs, gen, false, dep2)
	if err != nil {
		t.Fatalf("computeJavaHash: %v", err)
	}
	if withDep1 == withDep2 {
		t.Error("cambiar la versión de una dependencia debe invalidar el DEX")
	}

	// Mismo nombre de fichero y misma ruta, pero contenido distinto (por
	// ejemplo, un jar regenerado): el tamaño cambia y el hash debe cambiar.
	writeHashFile(t, dep1, "clases de la 1.0 con un arreglo")
	withPatched, err := computeJavaHash(app, libs, gen, false, dep1)
	if err != nil {
		t.Fatalf("computeJavaHash: %v", err)
	}
	if withPatched == withDep1 {
		t.Error("un cambio de contenido en el jar debe invalidar el DEX")
	}
}

func TestComputeJavaHashDistingueReleaseYDebug(t *testing.T) {
	dir := t.TempDir()
	app := filepath.Join(dir, "app")
	writeHashFile(t, filepath.Join(app, "java", "com", "x", "Main.kt"), "package com.x\n")

	debug, err := computeJavaHash(app, filepath.Join(dir, "libs"), filepath.Join(dir, "gen"), false)
	if err != nil {
		t.Fatalf("computeJavaHash: %v", err)
	}
	release, err := computeJavaHash(app, filepath.Join(dir, "libs"), filepath.Join(dir, "gen"), true)
	if err != nil {
		t.Fatalf("computeJavaHash: %v", err)
	}
	if debug == release {
		t.Error("debug y release producen el mismo hash: no se regeneraría el DEX")
	}
}

func TestComputeJavaHashIgnoraFuentesNoJava(t *testing.T) {
	dir := t.TempDir()
	app := filepath.Join(dir, "app")
	writeHashFile(t, filepath.Join(app, "java", "com", "x", "Main.kt"), "package com.x\n")

	first, err := computeJavaHash(app, filepath.Join(dir, "libs"), filepath.Join(dir, "gen"), false)
	if err != nil {
		t.Fatalf("computeJavaHash: %v", err)
	}

	// Un .txt no debe alterar el hash de compilación.
	writeHashFile(t, filepath.Join(app, "java", "com", "x", "notas.txt"), "sin efecto\n")
	second, err := computeJavaHash(app, filepath.Join(dir, "libs"), filepath.Join(dir, "gen"), false)
	if err != nil {
		t.Fatalf("computeJavaHash: %v", err)
	}
	if first != second {
		t.Error("un fichero que no es fuente alteró el hash")
	}
}
