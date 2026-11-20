// Package depstore gestiona el caché global de dependencias en ~/.rain/store.
//
// Rain guarda los bytes originales de cada AAR/JAR una sola vez, por
// coordenada y repositorio. Cada proyecto enlaza ese fichero desde su propio
// .rain-deps/aars/: dos proyectos que usan la misma versión de material3
// comparten la descarga, pero cada uno mantiene su propio classpath y su
// extracción (el orden de las coordenadas decide qué jar gana).
package depstore

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"io"
	"os"
	"path/filepath"
	"time"
)

// StoreVersion cambia cuando cambia el formato del store. Se incluye en la
// ruta para poder descartar entradas viejas sin mezclar formatos.
const StoreVersion = "v1"

// Store es el caché global de dependencias.
type Store struct {
	Root string
}

// New devuelve el store bajo ~/.rain/store. Devuelve nil si no se puede
// determinar el directorio home (el build sigue funcionando sin store).
func New() *Store {
	home, err := os.UserHomeDir()
	if err != nil || home == "" {
		return nil
	}
	return &Store{Root: filepath.Join(home, ".rain", "store", StoreVersion)}
}

// artifactKey identifica de forma inequívoca un artefacto. El repositorio
// entra en la clave porque la misma coordenada puede apuntar a bytes
// distintos en Google Maven, Maven Central o un repo del usuario.
func artifactKey(coord, repo string) string {
	h := sha256.New()
	fmt.Fprintf(h, "%s\x00%s", repo, coord)
	return hex.EncodeToString(h.Sum(nil))
}

// OriginalPath devuelve la ruta compartida del AAR/JAR original.
func (s *Store) OriginalPath(coord, repo, ext string) string {
	return filepath.Join(s.Root, "maven", artifactKey(coord, repo), "original."+ext)
}

// HasOriginal indica si el store ya tiene los bytes originales.
func (s *Store) HasOriginal(coord, repo, ext string) bool {
	st, err := os.Stat(s.OriginalPath(coord, repo, ext))
	return err == nil && st.Mode().IsRegular() && st.Size() > 0
}

// PublishOriginal mueve un fichero recién descargado al store. Escritura
// atómica: un build interrumpido no deja un fichero a medias.
func (s *Store) PublishOriginal(coord, repo, ext, downloaded string) error {
	dest := s.OriginalPath(coord, repo, ext)
	if err := os.MkdirAll(filepath.Dir(dest), 0o755); err != nil {
		return err
	}
	if err := os.Rename(downloaded, dest); err != nil {
		// En Windows el rename entre unidades puede fallar; copiar y borrar.
		if copyErr := copyFile(downloaded, dest); copyErr != nil {
			return err
		}
		return os.Remove(downloaded)
	}
	s.touch(coord, repo)
	return nil
}

func (s *Store) touch(coord, repo string) {
	dir := filepath.Join(s.Root, "maven", artifactKey(coord, repo))
	_ = os.WriteFile(filepath.Join(dir, ".lastused"), []byte(time.Now().UTC().Format(time.RFC3339)), 0o644)
}

// LinkOriginal enlaza el original compartido en la ruta que espera el
// pipeline (<proyecto>/.rain-deps/aars/<artifact>-<version>.ext). El
// directorio compartido se monta en modo solo lectura lógico: rain no escribe
// a través de este enlace, solo extrae de él.
func LinkOriginal(linkPath, target string) error {
	abs, err := filepath.Abs(target)
	if err != nil {
		return err
	}
	if current, err := os.Readlink(linkPath); err == nil {
		if current == abs {
			return nil
		}
		return fmt.Errorf("%s ya enlaza a %s (esperado %s)", linkPath, current, abs)
	}
	if st, err := os.Lstat(linkPath); err == nil {
		if st.Mode().IsRegular() {
			// Fichero del proyecto anterior al store: se conserva.
			return nil
		}
		return fmt.Errorf("%s existe y no es un symlink", linkPath)
	}
	if err := os.MkdirAll(filepath.Dir(linkPath), 0o755); err != nil {
		return err
	}
	tmp := linkPath + ".tmp"
	_ = os.Remove(tmp)
	if err := os.Symlink(abs, tmp); err != nil {
		// Windows sin Developer Mode: copiar en vez de enlazar.
		return copyFile(target, linkPath)
	}
	return os.Rename(tmp, linkPath)
}

// SaveResolution guarda el cierre transitivo calculado en el store global.
func (s *Store) SaveResolution(key string, data []byte) error {
	dir := filepath.Join(s.Root, "resolved")
	if err := os.MkdirAll(dir, 0o755); err != nil {
		return err
	}
	tmp := filepath.Join(dir, key+".tmp")
	if err := os.WriteFile(tmp, data, 0o644); err != nil {
		return err
	}
	return os.Rename(tmp, filepath.Join(dir, key+".json"))
}

// LoadResolution lee el cierre transitivo del store global.
func (s *Store) LoadResolution(key string) ([]byte, bool) {
	data, err := os.ReadFile(filepath.Join(s.Root, "resolved", key+".json"))
	return data, err == nil
}

func copyFile(src, dst string) error {
	in, err := os.Open(src)
	if err != nil {
		return err
	}
	defer func() { _ = in.Close() }()
	out, err := os.Create(dst)
	if err != nil {
		return err
	}
	_, copyErr := io.Copy(out, in)
	closeErr := out.Close()
	if copyErr != nil {
		return copyErr
	}
	return closeErr
}
