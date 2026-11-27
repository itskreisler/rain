package cli

import (
	"crypto/sha256"
	"encoding/hex"
	"encoding/json"
	"os"
	"path/filepath"

	"github.com/itskreisler/rain/internal/mavenrepo"
	"github.com/itskreisler/rain/internal/ui"
)

// resolvedCacheVersion invalida el caché cuando cambia la lógica del
// resolvedor (por ejemplo, un filtro nuevo de artefactos). Hay que subirlo a
// mano al tocar internal/pom/transitive.go.
const resolvedCacheVersion = 4

// resolvedCache guarda el cierre transitivo ya calculado. Sin esto, cada
// build vuelve a recorrer y parsear todos los POMs del grafo (cientos en un
// proyecto con Compose) aunque las dependencias declaradas no hayan cambiado.
type resolvedCache struct {
	Version     int      `json:"version"`
	Key         string   `json:"key"`
	Coordinates []string `json:"coordinates"`
}

// resolvedCachePath devuelve un fichero por espacio de nombres ("deps",
// "kapt"): así la resolución de kapt no pisa la de las dependencias normales.
func resolvedCachePath(namespace string) string {
	return filepath.Join("cache", ".rain-resolved-"+namespace+".json")
}

// resolveCacheKey resume las entradas que afectan al resultado. El orden de
// las dependencias importa porque determina el orden del classpath, así que no
// se ordena la lista.
func resolveCacheKey(namespace string, deps []string) string {
	h := sha256.New()
	h.Write([]byte("rain-resolved-v1\n"))
	h.Write([]byte(namespace))
	h.Write([]byte{'\n'})
	for _, dep := range deps {
		h.Write([]byte(dep))
		h.Write([]byte{'\n'})
	}
	h.Write([]byte("repos\n"))
	for _, repo := range mavenrepo.ExtraRepositories {
		h.Write([]byte(repo))
		h.Write([]byte{'\n'})
	}
	return hex.EncodeToString(h.Sum(nil))
}

func loadResolvedCache(namespace string, deps []string) ([]string, bool) {
	return loadResolvedCacheWithKey(namespace, resolveCacheKey(namespace, deps))
}

// loadResolvedCacheWithKey evita recalcular el hash cuando el llamador ya tiene
// la clave (resolveCached la necesita también para guardar).
func loadResolvedCacheWithKey(namespace, key string) ([]string, bool) {
	data, err := os.ReadFile(resolvedCachePath(namespace))
	if err != nil {
		return nil, false
	}
	var cached resolvedCache
	if err := json.Unmarshal(data, &cached); err != nil {
		return nil, false
	}
	if cached.Version != resolvedCacheVersion || cached.Key != key || len(cached.Coordinates) == 0 {
		return nil, false
	}
	return cached.Coordinates, true
}

func saveResolvedCache(namespace string, deps, coords []string) error {
	return saveResolvedCacheWithKey(namespace, resolveCacheKey(namespace, deps), coords)
}

// saveResolvedCacheWithKey es la contrapartida de loadResolvedCacheWithKey:
// recibe la clave ya calculada en lugar de derivarla de las dependencias.
func saveResolvedCacheWithKey(namespace, key string, coords []string) error {
	data, err := json.Marshal(resolvedCache{
		Version:     resolvedCacheVersion,
		Key:         key,
		Coordinates: coords,
	})
	if err != nil {
		return err
	}
	path := resolvedCachePath(namespace)
	if err := os.MkdirAll(filepath.Dir(path), 0o755); err != nil {
		return err
	}
	// Escritura atómica: un build interrumpido no debe dejar un JSON roto.
	tmp := path + ".tmp"
	if err := os.WriteFile(tmp, data, 0o644); err != nil {
		return err
	}
	return os.Rename(tmp, path)
}

func resolveCached(namespace string, deps []string, resolve func([]string) ([]string, error)) ([]string, error) {
	key := resolveCacheKey(namespace, deps)
	if coords, ok := loadResolvedCacheWithKey(namespace, key); ok {
		ui.VerboseLog("dependencias ya resueltas, reutilizando %d coordenadas", len(coords))
		return coords, nil
	}

	coords, err := resolve(deps)
	if err != nil {
		return nil, err
	}
	if err := saveResolvedCacheWithKey(namespace, key, coords); err != nil {
		ui.VerboseLog("no se pudo guardar la caché de resolución: %v", err)
	}
	return coords, nil
}

func resolveTransitiveCoordinatesCached(deps []string) ([]string, error) {
	return resolveCached("deps", deps, resolveTransitiveCoordinates)
}

func resolveCoordinatesCached(namespace string, deps []string) ([]string, error) {
	return resolveCached(namespace, deps, resolveCoordinates)
}
