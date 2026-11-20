package depstore

import (
	"os"
	"path/filepath"
	"strings"
	"testing"
)

func TestPublishYLinkOriginal(t *testing.T) {
	store := &Store{Root: t.TempDir()}

	// Un fichero "descargado" cualquiera: el store solo mueve bytes.
	downloaded := filepath.Join(t.TempDir(), "material3-1.5.0.aar")
	content := []byte("bytes del AAR de material3")
	if err := os.WriteFile(downloaded, content, 0o644); err != nil {
		t.Fatal(err)
	}
	coord, repo := "androidx.compose.material3:material3-android:1.5.0", "https://dl.google.com/dl/android/maven2"
	if err := store.PublishOriginal(coord, repo, "aar", downloaded); err != nil {
		t.Fatalf("PublishOriginal: %v", err)
	}
	if !store.HasOriginal(coord, repo, "aar") {
		t.Fatal("el store debería tener el original publicado")
	}
	if _, err := os.Stat(downloaded); !os.IsNotExist(err) {
		t.Fatal("PublishOriginal debe mover (no copiar) el fichero descargado")
	}

	// Dos proyectos distintos enlazan el mismo original.
	projA := filepath.Join(t.TempDir(), "proyectoA", "aars", "material3-1.5.0.aar")
	projB := filepath.Join(t.TempDir(), "proyectoB", "aars", "material3-1.5.0.aar")
	target := store.OriginalPath(coord, repo, "aar")
	for _, p := range []string{projA, projB} {
		if err := LinkOriginal(p, target); err != nil {
			t.Fatalf("LinkOriginal(%s): %v", p, err)
		}
		got, err := os.ReadFile(p)
		if err != nil {
			t.Fatalf("leyendo %s: %v", p, err)
		}
		if string(got) != string(content) {
			t.Fatalf("contenido distinto en %s", p)
		}
	}
	linked, err := os.Readlink(projA)
	if err != nil || !strings.HasSuffix(linked, "original.aar") {
		t.Fatalf("projA debería ser symlink al store, es %q (%v)", linked, err)
	}

	// Otra versión de la misma librería tiene clave distinta.
	otraKey := artifactKey("androidx.compose.material3:material3-android:1.6.0", repo)
	if otraKey == artifactKey(coord, repo) {
		t.Fatal("versiones distintas no deben compartir clave de store")
	}
}

func TestLinkOriginalNoSobrescribeFicheroLocal(t *testing.T) {
	store := &Store{Root: t.TempDir()}
	content := []byte("original compartido")
	target := filepath.Join(store.Root, "shared.aar")
	if err := os.MkdirAll(filepath.Dir(target), 0o755); err != nil {
		t.Fatal(err)
	}
	if err := os.WriteFile(target, content, 0o644); err != nil {
		t.Fatal(err)
	}
	local := filepath.Join(t.TempDir(), "aars", "a-1.aar")
	if err := os.MkdirAll(filepath.Dir(local), 0o755); err != nil {
		t.Fatal(err)
	}
	originalLocal := []byte("fichero del usuario")
	if err := os.WriteFile(local, originalLocal, 0o644); err != nil {
		t.Fatal(err)
	}
	if err := LinkOriginal(local, target); err != nil {
		t.Fatalf("LinkOriginal sobre fichero local: %v", err)
	}
	got, _ := os.ReadFile(local)
	if string(got) != string(originalLocal) {
		t.Fatal("LinkOriginal no debe sobrescribir un fichero regular existente")
	}
}
