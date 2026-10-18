package pom

import (
"fmt"
"os"
"path/filepath"
"strings"
)

// ResolvedDep representa una dependencia final resuelta.
type ResolvedDep struct {
GroupID    string
ArtifactID string
Version    string
}

func (d ResolvedDep) Coordinate() string {
return fmt.Sprintf("%s:%s:%s", d.GroupID, d.ArtifactID, d.Version)
}

// ResolveTransitive resuelve todas las dependencias transitivas de una coordenada raíz.
// Usa "highest wins" para conflictos de versión.
func ResolveTransitive(rootCoord string, cacheDir string) ([]ResolvedDep, error) {
resolved := make(map[string]ResolvedDep) // key: group:artifact
visited := make(map[string]bool)          // key: group:artifact:version

var walk func(coord string) error
walk = func(coord string) error {
if visited[coord] {
return nil
}
visited[coord] = true

parts := strings.Split(coord, ":")
if len(parts) != 3 {
return fmt.Errorf("coordenada inválida: %s", coord)
}
group, artifact, version := parts[0], parts[1], parts[2]

// Descargar POM (o usar caché)
groupPath := filepath.Join(cacheDir, group)
pomPath := filepath.Join(groupPath, artifact+"-"+version+".pom")

var p *Project
var err error
if _, statErr := os.Stat(pomPath); statErr == nil {
p, err = ParsePOM(pomPath)
} else {
path, dlErr := DownloadPOM(coord, groupPath)
if dlErr != nil {
return fmt.Errorf("descargando POM %s: %w", coord, dlErr)
}
p, err = ParsePOM(path)
}
if err != nil {
return fmt.Errorf("parseando POM %s: %w", coord, err)
}

// Resolver cadena de parents
chain, err := ResolveParentChain(p, cacheDir)
if err != nil {
return fmt.Errorf("resolviendo parents de %s: %w", coord, err)
}

// Fusionar propiedades y management
props := MergeProperties(chain)
mgmt := MergeDependencyManagement(chain)

// Resolver dependencias directas de este POM
deps, err := ResolveDependencies(p, mgmt, props)
if err != nil {
return fmt.Errorf("resolviendo dependencias de %s: %w", coord, err)
}

for _, dep := range deps {
// Filtrar scopes no necesarios para compilación
if dep.Scope == "test" || dep.Scope == "provided" || dep.Scope == "system" {
continue
}
if dep.Optional == "true" {
continue
}

key := dep.GroupID + ":" + dep.ArtifactID
newCoord := dep.GroupID + ":" + dep.ArtifactID + ":" + dep.Version

// Highest wins: si ya existe, comparar versiones
if existing, ok := resolved[key]; ok {
if versionLess(existing.Version, dep.Version) {
resolved[key] = ResolvedDep{dep.GroupID, dep.ArtifactID, dep.Version}
}
} else {
resolved[key] = ResolvedDep{dep.GroupID, dep.ArtifactID, dep.Version}
}

// Recursión
if err := walk(newCoord); err != nil {
return err
}
}

// Añadir este artefacto al resultado (si no es la raíz)
if coord != rootCoord {
key := group + ":" + artifact
if existing, ok := resolved[key]; !ok || versionLess(existing.Version, version) {
resolved[key] = ResolvedDep{group, artifact, version}
}
}

return nil
}

if err := walk(rootCoord); err != nil {
return nil, err
}

// Convertir mapa a slice
var out []ResolvedDep
for _, d := range resolved {
out = append(out, d)
}
return out, nil
}

// versionLess compara dos versiones de forma simple (asume formato semver-like).
func versionLess(a, b string) bool {
// Implementación básica: comparar numéricamente las partes separadas por "."
// Si no son numéricas, comparar como strings.
if a == b {
return false
}
pa := strings.Split(a, ".")
pb := strings.Split(b, ".")
for i := 0; i < len(pa) && i < len(pb); i++ {
var na, nb int
if _, err := fmt.Sscanf(pa[i], "%d", &na); err != nil {
return pa[i] < pb[i]
}
if _, err := fmt.Sscanf(pb[i], "%d", &nb); err != nil {
return pa[i] < pb[i]
}
if na != nb {
return na < nb
}
}
return len(pa) < len(pb)
}
