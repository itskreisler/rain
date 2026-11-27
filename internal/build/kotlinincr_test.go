package build

import (
	"encoding/hex"
	"os"
	"path/filepath"
	"runtime"
	"testing"
)

// ktTestSetup crea un proyecto mínimo con las marcas que exige el planificador.
func ktTestSetup(t *testing.T) (Config, string, string) {
	t.Helper()
	dir := t.TempDir()
	app := filepath.Join(dir, "app")
	root := filepath.Join(app, "src", "main")
	build := filepath.Join(app, "build")
	writeHashFile(t, filepath.Join(root, "AndroidManifest.xml"), `<manifest package="com.x"/>`)
	writeHashFile(t, filepath.Join(build, javaHashFile), "hash-java")
	writeHashFile(t, filepath.Join(build, stampFile), "1")
	writeHashFile(t, filepath.Join(build, "obj", "com", "x", "AKt.class"), "clase previa")
	cfg := Config{AppDir: app, BuildDir: build, Package: "com.x"}
	return cfg, root, build
}

func ktTestIndex(abi, content string) *kotlinSourceIndex {
	return &kotlinSourceIndex{
		Version: kotlinIndexVersion,
		Env:     "entorno",
		Files: map[string]kotlinFileHashes{
			"A.kt": {Content: content, ABI: abi},
		},
	}
}

func TestIndiceKotlinLoadSave(t *testing.T) {
	dir := t.TempDir()
	path := kotlinIndexPath(dir)
	if _, err := loadKotlinIndex(path); err == nil {
		t.Error("cargar un índice ausente debe fallar")
	}

	idx := ktTestIndex("abi-1", "contenido-1")
	if err := saveKotlinIndex(dir, idx); err != nil {
		t.Fatalf("saveKotlinIndex: %v", err)
	}
	got, err := loadKotlinIndex(path)
	if err != nil {
		t.Fatalf("loadKotlinIndex: %v", err)
	}
	if got.Version != kotlinIndexVersion || got.Env != idx.Env {
		t.Fatalf("índice cargado = %+v", got)
	}
	if h := got.Files["A.kt"]; h.ABI != "abi-1" || h.Content != "contenido-1" {
		t.Fatalf("hashes cargados = %+v", h)
	}

	if err := os.WriteFile(path, []byte("{roto"), 0o644); err != nil {
		t.Fatal(err)
	}
	if _, err := loadKotlinIndex(path); err == nil {
		t.Error("un JSON corrupto debe rechazarse")
	}
	idx.Version = "otra-version"
	if err := saveKotlinIndex(dir, idx); err != nil {
		t.Fatal(err)
	}
	if _, err := loadKotlinIndex(path); err == nil {
		t.Error("una versión desconocida debe rechazarse")
	}
}

func TestIndiceKotlinCambioDeContenidoConABIIgual(t *testing.T) {
	cfg, root, _ := ktTestSetup(t)
	genDir := filepath.Join(cfg.BuildDir, "gen")
	src := filepath.Join(root, "A.kt")
	writeHashFile(t, src, `package com.x

class A {
    fun saludo(): String = "hola"
}
`)
	writeHashFile(t, filepath.Join(root, "B.kt"), `package com.x

class B {
    fun usar() = A().saludo()
}
`)

	prev, err := buildKotlinIndex(cfg, genDir)
	if err != nil {
		t.Fatalf("buildKotlinIndex: %v", err)
	}
	if err := saveKotlinIndex(cfg.BuildDir, prev); err != nil {
		t.Fatalf("saveKotlinIndex: %v", err)
	}

	// Cambiar solo un literal dentro del cuerpo no altera la ABI.
	writeHashFile(t, src, `package com.x

class A {
    fun saludo(): String = "adiós"
}
`)
	cur, err := buildKotlinIndex(cfg, genDir)
	if err != nil {
		t.Fatalf("buildKotlinIndex tras el cambio: %v", err)
	}
	loaded, err := loadKotlinIndex(kotlinIndexPath(cfg.BuildDir))
	if err != nil {
		t.Fatalf("loadKotlinIndex: %v", err)
	}
	if prev.Files["A.kt"].ABI != cur.Files["A.kt"].ABI {
		t.Fatal("el literal cambió la ABI: debería compilar todo")
	}
	if prev.Files["A.kt"].Content == cur.Files["A.kt"].Content {
		t.Fatal("el literal no cambió el hash de contenido")
	}
	plan := kotlinIncrementalPlan(loaded, cur, cfg)
	if plan.full || len(plan.files) != 1 || plan.files[0] != "A.kt" {
		t.Fatalf("plan = %+v, quiero el subconjunto {A.kt}", plan)
	}
}

func TestIndiceKotlinCambioDeABIVaCompleto(t *testing.T) {
	cfg, root, _ := ktTestSetup(t)
	genDir := filepath.Join(cfg.BuildDir, "gen")
	src := filepath.Join(root, "A.kt")
	writeHashFile(t, src, `package com.x

class A {
    fun saludo(): String = "hola"
}
`)
	prev, err := buildKotlinIndex(cfg, genDir)
	if err != nil {
		t.Fatalf("buildKotlinIndex: %v", err)
	}

	// Cambiar la firma de un miembro afecta a quien lo llama.
	writeHashFile(t, src, `package com.x

class A {
    fun saludo(nombre: String): String = "hola $nombre"
}
`)
	cur, err := buildKotlinIndex(cfg, genDir)
	if err != nil {
		t.Fatalf("buildKotlinIndex tras el cambio: %v", err)
	}
	if prev.Files["A.kt"].ABI == cur.Files["A.kt"].ABI {
		t.Fatal("un cambio de firma debe alterar la ABI")
	}
	plan := kotlinIncrementalPlan(prev, cur, cfg)
	if !plan.full {
		t.Fatalf("plan = %+v, quiero compilación completa", plan)
	}
}

func TestIndiceKotlinCuerpoInlineYFuenteNoClasificable(t *testing.T) {
	dir := t.TempDir()
	src := filepath.Join(dir, "Inline.kt")
	writeHashFile(t, src, `package com.x

inline fun saludo(): String = "hola"
`)
	first, _, err := kotlinABISignature(src)
	if err != nil {
		t.Fatalf("kotlinABISignature: %v", err)
	}
	writeHashFile(t, src, `package com.x

inline fun saludo(): String = "adiós"
`)
	second, _, err := kotlinABISignature(src)
	if err != nil {
		t.Fatalf("kotlinABISignature tras el cambio: %v", err)
	}
	if first == second {
		t.Error("el cuerpo de una función inline debe entrar en la ABI")
	}

	// Los identificadores con backticks no se clasifican con confianza: la
	// ABI debe ser el hash del fichero completo.
	writeHashFile(t, src, "package com.x\nval `nombre raro` = 1\n")
	_, confident, err := kotlinABISignature(src)
	if err != nil {
		t.Fatalf("kotlinABISignature con backticks: %v", err)
	}
	if confident {
		t.Error("un identificador con backticks no debería considerarse clasificado")
	}
	abi, err := hashKotlinABI(src)
	if err != nil {
		t.Fatalf("hashKotlinABI: %v", err)
	}
	content, err := hashFile(src)
	if err != nil {
		t.Fatalf("hashFile: %v", err)
	}
	if abi != hex.EncodeToString(content) {
		t.Error("una fuente no clasificable debe hashear el fichero completo en la ABI")
	}
}

func TestIndiceKotlinCuerpoSealedYConstEntranEnABI(t *testing.T) {
	dir := t.TempDir()
	src := filepath.Join(dir, "Estado.kt")
	writeHashFile(t, src, `package com.x

sealed class Estado
{
    object Cargando : Estado()
}

const val SALUDO = "hola"
`)
	first, _, err := kotlinABISignature(src)
	if err != nil {
		t.Fatalf("kotlinABISignature: %v", err)
	}
	writeHashFile(t, src, `package com.x

sealed class Estado
{
    object Listo : Estado()
}

const val SALUDO = "hola"
`)
	second, _, err := kotlinABISignature(src)
	if err != nil {
		t.Fatalf("kotlinABISignature tras cambiar sealed: %v", err)
	}
	if first == second {
		t.Error("cambiar una subclase sealed debe alterar la ABI")
	}
	writeHashFile(t, src, `package com.x

sealed class Estado
{
    object Listo : Estado()
}

const val SALUDO = "adiós"
`)
	third, _, err := kotlinABISignature(src)
	if err != nil {
		t.Fatalf("kotlinABISignature tras cambiar const: %v", err)
	}
	if second == third {
		t.Error("cambiar el valor de un const debe alterar la ABI")
	}
}

func TestIndiceKotlinAnadirOBorrarFuerzaCompleto(t *testing.T) {
	cfg, _, _ := ktTestSetup(t)
	base := ktTestIndex("abi", "contenido")

	added := ktTestIndex("abi", "contenido")
	added.Files["B.kt"] = kotlinFileHashes{ABI: "abi-b", Content: "contenido-b"}
	if plan := kotlinIncrementalPlan(base, added, cfg); !plan.full {
		t.Errorf("añadir un fichero dio %+v, quiero completo", plan)
	}

	deleted := ktTestIndex("abi", "contenido")
	deleted.Files = map[string]kotlinFileHashes{}
	if plan := kotlinIncrementalPlan(base, deleted, cfg); !plan.full {
		t.Errorf("borrar un fichero dio %+v, quiero completo", plan)
	}

	same := ktTestIndex("abi", "contenido")
	if plan := kotlinIncrementalPlan(base, same, cfg); !plan.full {
		t.Errorf("sin cambios dio %+v, quiero completo", plan)
	}

	otroEntorno := ktTestIndex("abi", "contenido-distinto")
	otroEntorno.Env = "otro-entorno"
	if plan := kotlinIncrementalPlan(base, otroEntorno, cfg); !plan.full {
		t.Errorf("con entradas de build distintas dio %+v, quiero completo", plan)
	}
}

func TestIndiceKotlinKaptYComposeFuerzanCompleto(t *testing.T) {
	cfg, root, _ := ktTestSetup(t)
	base := ktTestIndex("abi", "contenido")
	cur := ktTestIndex("abi", "contenido-distinto")

	withKapt := cfg
	withKapt.Kapt = &KaptConfig{ProcessorJars: []string{"processor.jar"}}
	if plan := kotlinIncrementalPlan(base, cur, withKapt); !plan.full {
		t.Errorf("con kapt dio %+v, quiero completo", plan)
	}

	plugin := filepath.Join(root, "compose-compiler-plugin.jar")
	writeHashFile(t, plugin, "plugin")
	writeHashFile(t, filepath.Join(root, "B.kt"), "package com.x\nimport androidx.compose.runtime.Composable\n")
	withCompose := cfg
	withCompose.ComposePlugin = plugin
	if !withCompose.appliesComposePlugin() {
		t.Fatal("el test no ha activado el plugin de Compose")
	}
	if plan := kotlinIncrementalPlan(base, cur, withCompose); !plan.full {
		t.Errorf("con Compose dio %+v, quiero completo", plan)
	}
}

func TestIndiceKotlinSinMarcasFuerzaCompleto(t *testing.T) {
	cfg, _, _ := ktTestSetup(t)
	base := ktTestIndex("abi", "contenido")
	cur := ktTestIndex("abi", "contenido-distinto")

	for _, name := range []string{javaHashFile, stampFile} {
		t.Run(name, func(t *testing.T) {
			cfgCopy := cfg
			cfgCopy.BuildDir = t.TempDir()
			writeHashFile(t, filepath.Join(cfgCopy.BuildDir, javaHashFile), "hash")
			writeHashFile(t, filepath.Join(cfgCopy.BuildDir, stampFile), "1")
			writeHashFile(t, filepath.Join(cfgCopy.BuildDir, "obj", "A.class"), "clase")
			if err := os.Remove(filepath.Join(cfgCopy.BuildDir, name)); err != nil {
				t.Fatal(err)
			}
			if plan := kotlinIncrementalPlan(base, cur, cfgCopy); !plan.full {
				t.Errorf("sin %s dio %+v, quiero completo", name, plan)
			}
		})
	}

	// Sin clases en objDir tampoco hay subconjunto seguro.
	cfgCopy := cfg
	cfgCopy.BuildDir = t.TempDir()
	writeHashFile(t, filepath.Join(cfgCopy.BuildDir, javaHashFile), "hash")
	writeHashFile(t, filepath.Join(cfgCopy.BuildDir, stampFile), "1")
	if plan := kotlinIncrementalPlan(base, cur, cfgCopy); !plan.full {
		t.Errorf("sin clases dio %+v, quiero completo", plan)
	}
}

func TestLimpiezaClasesKotlinObsoletas(t *testing.T) {
	dir := t.TempDir()
	obj := filepath.Join(dir, "obj")
	src := filepath.Join(dir, "A.kt")
	writeHashFile(t, src, `package com.x

class A {
    class B
}

fun saludo() = "hola"
`)
	for _, name := range []string{
		"AKt.class", "A.class", "A$B.class", "B.class",
		"R.class", "BuildConfig.class", "Otra.class",
	} {
		writeHashFile(t, filepath.Join(obj, "com", "x", name), "clase")
	}

	if err := removeStaleKotlinClasses(obj, []string{src}); err != nil {
		t.Fatalf("removeStaleKotlinClasses: %v", err)
	}
	for _, name := range []string{"AKt.class", "A.class", "A$B.class", "B.class"} {
		if _, err := os.Stat(filepath.Join(obj, "com", "x", name)); !os.IsNotExist(err) {
			t.Errorf("quedó la clase obsoleta %s", name)
		}
	}
	for _, name := range []string{"R.class", "BuildConfig.class", "Otra.class"} {
		if _, err := os.Stat(filepath.Join(obj, "com", "x", name)); err != nil {
			t.Errorf("se borró la clase %s que no debía tocarse: %v", name, err)
		}
	}
}

// TestCompileKotlinSubsetConKotlincFalso comprueba que una sola invocación
// recibe únicamente el fichero cambiado y que el resto de objDir se conserva.
func TestCompileKotlinSubsetConKotlincFalso(t *testing.T) {
	if runtime.GOOS == "windows" {
		t.Skip("el compilador falso es un script sh")
	}
	cfg, root, build := ktTestSetup(t)
	genDir := filepath.Join(build, "gen")
	obj := filepath.Join(build, "obj")
	writeHashFile(t, filepath.Join(root, "A.kt"), "package com.x\nfun a() = 1\n")
	writeHashFile(t, filepath.Join(root, "B.kt"), "package com.x\nfun b() = 2\n")
	writeHashFile(t, filepath.Join(obj, "com", "x", "AKt.class"), "vieja")
	writeHashFile(t, filepath.Join(obj, "mantener.txt"), "de otra compilación")

	binDir := filepath.Join(t.TempDir(), "bin")
	writeHashFile(t, filepath.Join(binDir, "kotlinc"), `#!/bin/sh
mkdir -p "$RAIN_TEST_OBJ"
for arg in "$@"; do
  case "$arg" in
    *.kt) : > "$RAIN_TEST_OBJ/$(basename "$arg").compilado" ;;
  esac
done
`)
	if err := os.Chmod(filepath.Join(binDir, "kotlinc"), 0o755); err != nil {
		t.Fatal(err)
	}
	t.Setenv("PATH", binDir+string(os.PathListSeparator)+os.Getenv("PATH"))
	t.Setenv("RAIN_TEST_OBJ", obj)

	kotlinFiles, err := compileKotlinSubset(cfg, genDir, obj, []string{"A.kt"})
	if err != nil {
		t.Fatalf("compileKotlinSubset: %v", err)
	}
	if len(kotlinFiles) != 1 || filepath.Base(kotlinFiles[0]) != "A.kt" {
		t.Fatalf("fuentes devueltas = %v, quiero solo A.kt", kotlinFiles)
	}
	if _, err := os.Stat(filepath.Join(obj, "A.kt.compilado")); err != nil {
		t.Errorf("el compilador falso no recibió A.kt: %v", err)
	}
	if _, err := os.Stat(filepath.Join(obj, "B.kt.compilado")); !os.IsNotExist(err) {
		t.Error("el compilador falso recibió B.kt, que no cambió")
	}
	if _, err := os.Stat(filepath.Join(obj, "mantener.txt")); err != nil {
		t.Errorf("se borró el contenido previo de objDir: %v", err)
	}
	if _, err := os.Stat(filepath.Join(obj, "com", "x", "AKt.class")); !os.IsNotExist(err) {
		t.Error("no se borró la clase obsoleta del fichero cambiado")
	}
}
