package cli

import (
	"os"
	"path/filepath"
	"reflect"
	"testing"
)

func TestFormatJavaFilesSeleccionaSoloLoConfigurado(t *testing.T) {
	dir := t.TempDir()
	first := filepath.Join(dir, "app", "java", "First.java")
	second := filepath.Join(dir, "test", "java", "Second.java")
	for _, path := range []string{first, second} {
		if err := os.MkdirAll(filepath.Dir(path), 0o755); err != nil {
			t.Fatal(err)
		}
		if err := os.WriteFile(path, []byte("class Demo {}\n"), 0o644); err != nil {
			t.Fatal(err)
		}
	}

	configured := []string{"app/java/First.java", "test/java/Second.java", "no/existe.java"}
	want := []string{first, second}
	if got := formatJavaFiles(dir, configured, nil, []string{filepath.Join(dir, "otro.java")}); !reflect.DeepEqual(got, want) {
		t.Errorf("formatJavaFiles = %v, quiero %v", got, want)
	}
}

func TestFormatJavaFilesConGlob(t *testing.T) {
	dir := t.TempDir()
	files := map[string]string{
		"app/java/app/tl/bot/New.java":      "",
		"app/java/app/tl/bot/Other.java":    "",
		"test/java/app/tl/bot/NewTest.java": "",
		"app/build/gen/R.java":              "",
	}
	for rel := range files {
		path := filepath.Join(dir, filepath.FromSlash(rel))
		if err := os.MkdirAll(filepath.Dir(path), 0o755); err != nil {
			t.Fatal(err)
		}
		if err := os.WriteFile(path, []byte("class Demo {}\n"), 0o644); err != nil {
			t.Fatal(err)
		}
	}

	configured := []string{"app/java/**/*.java", "test/java/**/*.java", "!**/Other.java"}
	want := []string{
		filepath.Join(dir, "app", "java", "app", "tl", "bot", "New.java"),
		filepath.Join(dir, "test", "java", "app", "tl", "bot", "NewTest.java"),
	}
	if got := formatJavaFiles(dir, configured, nil, nil); !reflect.DeepEqual(got, want) {
		t.Errorf("formatJavaFiles = %v, quiero %v", got, want)
	}
}

func TestGlobMatchDobleEstrellaCasaConCeroDirectorios(t *testing.T) {
	for _, name := range []string{"app/java/Main.java", "app/java/sub/Main.java"} {
		if !globMatch("app/java/**/*.java", name) {
			t.Errorf("globMatch(app/java/**/*.java, %q) = false", name)
		}
	}
	if globMatch("app/java/**/*.java", "test/java/Main.java") {
		t.Error("el glob no debería salir de app/java")
	}
}

func TestFormatJavaFilesConJavaExclude(t *testing.T) {
	dir := t.TempDir()
	keep := filepath.Join(dir, "app", "java", "New.java")
	skip := filepath.Join(dir, "app", "java", "Ui.java")
	for _, path := range []string{keep, skip} {
		if err := os.MkdirAll(filepath.Dir(path), 0o755); err != nil {
			t.Fatal(err)
		}
		if err := os.WriteFile(path, []byte("class Demo {}\n"), 0o644); err != nil {
			t.Fatal(err)
		}
	}

	if got := formatJavaFiles(dir, nil, []string{"**/Ui.java"}, nil); !reflect.DeepEqual(got, []string{keep}) {
		t.Errorf("formatJavaFiles = %v, quiero solo %v", got, keep)
	}
}

func TestFormatJavaFilesSinConfiguracionUsaLoEncontrado(t *testing.T) {
	found := []string{filepath.Join(t.TempDir(), "Main.java")}
	if got := formatJavaFiles(t.TempDir(), nil, nil, found); !reflect.DeepEqual(got, found) {
		t.Errorf("formatJavaFiles = %v, quiero %v", got, found)
	}
}
