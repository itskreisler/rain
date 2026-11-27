package hilt

import (
	"os"
	"os/exec"
	"path/filepath"
	"testing"
)

// fakeAnnotation es una copia de la anotación de Hilt (retención CLASS) para
// poder compilar el fixture sin depender del artefacto real.
const fakeAnnotation = `package dagger.hilt.android;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface AndroidEntryPoint {
    Class<?> value() default void.class;
}
`

const fakeHiltApp = `package dagger.hilt.android;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface HiltAndroidApp {
    Class<?> value() default void.class;
}
`

const fakeBase = `package com.example;

public class BaseActivity {
    protected void onCreate() {
    }
}
`

const fakeEntryPoint = `package com.example;

@dagger.hilt.android.AndroidEntryPoint
public class MainActivity extends BaseActivity {
    public MainActivity() {
        super();
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }
}
`

const fakeApp = `package com.example;

@dagger.hilt.android.HiltAndroidApp
public class MyApp extends BaseActivity {
}
`

// compileFixture compila los fuentes Java dados y devuelve el directorio de
// clases.
func compileFixture(t *testing.T, sources map[string]string) string {
	t.Helper()
	if _, err := exec.LookPath("javac"); err != nil {
		t.Skip("javac no disponible")
	}
	dir := t.TempDir()
	srcDir := filepath.Join(dir, "src")
	outDir := filepath.Join(dir, "classes")
	if err := os.MkdirAll(outDir, 0o755); err != nil {
		t.Fatal(err)
	}
	var files []string
	for name, content := range sources {
		path := filepath.Join(srcDir, name)
		if err := os.MkdirAll(filepath.Dir(path), 0o755); err != nil {
			t.Fatal(err)
		}
		if err := os.WriteFile(path, []byte(content), 0o644); err != nil {
			t.Fatal(err)
		}
		files = append(files, path)
	}
	cmd := exec.Command("javac", append([]string{"-d", outDir}, files...)...)
	if output, err := cmd.CombinedOutput(); err != nil {
		t.Fatalf("javac falló: %v\n%s", err, output)
	}
	return outDir
}

func TestTransformClassRewritesSuperclass(t *testing.T) {
	classes := compileFixture(t, map[string]string{
		"dagger/hilt/android/AndroidEntryPoint.java": fakeAnnotation,
		"dagger/hilt/android/HiltAndroidApp.java":    fakeHiltApp,
		"com/example/BaseActivity.java":              fakeBase,
		"com/example/MainActivity.java":              fakeEntryPoint,
		"com/example/MyApp.java":                     fakeApp,
	})

	count, err := TransformDir(classes)
	if err != nil {
		t.Fatalf("TransformDir: %v", err)
	}
	if count != 2 {
		t.Fatalf("se esperaba transformar 2 clases, se transformaron %d", count)
	}

	// La clase anotada debe heredar ahora de Hilt_MainActivity.
	data, err := os.ReadFile(filepath.Join(classes, "com", "example", "MainActivity.class"))
	if err != nil {
		t.Fatal(err)
	}
	cf, err := parseClassFile(data)
	if err != nil {
		t.Fatal(err)
	}
	if got := cf.className(cf.superClass); got != "com/example/Hilt_MainActivity" {
		t.Fatalf("superclase incorrecta: %q", got)
	}

	// Y las llamadas a super (invokespecial) deben apuntar a la nueva
	// superclase, o la JVM rechazaría la clase.
	oldIndex := uint16(0)
	for i := 1; i < len(cf.pool); i++ {
		entry := cf.pool[i]
		if entry.tag == tagClass && cf.utf8(entry.refIndex) == "com/example/BaseActivity" {
			oldIndex = uint16(i)
		}
	}
	if oldIndex == 0 {
		t.Fatal("no se encontró la entrada de la superclase antigua")
	}
	methodRefs := 0
	for i := 1; i < len(cf.pool); i++ {
		entry := cf.pool[i]
		if entry.tag != tagMethodref {
			continue
		}
		methodRefs++
		if entry.refIndex == oldIndex {
			t.Fatalf("quedó un methodref apuntando a la superclase antigua (índice %d)", entry.refIndex)
		}
	}
	if methodRefs == 0 {
		t.Fatal("no se encontró ningún methodref: el fixture no compila como se espera")
	}
}

func TestTransformClassIgnoresPlainClasses(t *testing.T) {
	classes := compileFixture(t, map[string]string{
		"com/example/BaseActivity.java": fakeBase,
	})
	count, err := TransformDir(classes)
	if err != nil {
		t.Fatalf("TransformDir: %v", err)
	}
	if count != 0 {
		t.Fatalf("una clase sin anotaciones no debe transformarse (se transformaron %d)", count)
	}
}

func TestHiltBaseName(t *testing.T) {
	cases := map[string]string{
		"com/example/MainActivity":   "com/example/Hilt_MainActivity",
		"com/example/MyApp":          "com/example/Hilt_MyApp",
		"com/example/Outer$Inner":    "com/example/Hilt_Outer_Inner",
		"MainActivity":               "Hilt_MainActivity",
		"com/example/nested/Thing$1": "com/example/nested/Hilt_Thing_1",
		"com$externo/Outer$Inner$1":  "com$externo/Hilt_Outer_Inner_1",
	}
	for input, want := range cases {
		if got := HiltBaseName(input); got != want {
			t.Errorf("HiltBaseName(%q) = %q, se esperaba %q", input, got, want)
		}
	}
}

// TestPoolIndexReutilizaPrimerIndice comprueba que el índice del pool devuelve
// el mismo resultado que los bucles lineales originales, incluidos duplicados.
func TestPoolIndexReutilizaPrimerIndice(t *testing.T) {
	utf8Entry := func(value string) poolEntry {
		data := append([]byte{tagUtf8}, byte(len(value)>>8), byte(len(value)))
		data = append(data, value...)
		return poolEntry{tag: tagUtf8, data: data}
	}
	classEntry := func(nameIndex uint16) poolEntry {
		return poolEntry{
			tag:      tagClass,
			data:     []byte{tagClass, byte(nameIndex >> 8), byte(nameIndex)},
			refIndex: nameIndex,
		}
	}
	cf := &classFile{
		pool: []poolEntry{
			{}, // el índice 0 no existe
			utf8Entry("com/example/A"),
			utf8Entry("com/example/B"),
			utf8Entry("com/example/A"), // duplicado posterior
			classEntry(1),
			classEntry(3), // misma clase apuntando a otro Utf8 igual
		},
		poolCount: 6,
	}
	if got := cf.addClassEntry("com/example/A"); got != 4 {
		t.Fatalf("addClassEntry devolvió %d, se esperaba el primer índice 4", got)
	}
	if got := cf.addUtf8("com/example/A"); got != 1 {
		t.Fatalf("addUtf8 devolvió %d, se esperaba el primer índice 1", got)
	}
	// Una clase nueva añade primero su Utf8 y después su Class.
	size := len(cf.pool)
	if got := cf.addClassEntry("com/example/C"); got != uint16(size+1) {
		t.Fatalf("addClassEntry nuevo devolvió %d, se esperaba %d", got, size+1)
	}
	if got := cf.addClassEntry("com/example/C"); got != uint16(size+1) {
		t.Fatalf("addClassEntry repetido devolvió %d, se esperaba %d", got, size+1)
	}
	if len(cf.pool) != size+2 {
		t.Fatalf("el pool creció a %d entradas, se esperaban %d", len(cf.pool), size+2)
	}
}
