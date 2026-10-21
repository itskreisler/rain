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
	visited := make(map[string]bool)         // key: group:artifact:version

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
		na, aok := numericPrefix(pa[i])
		nb, bok := numericPrefix(pb[i])
		if aok && bok {
			if na != nb {
				return na < nb
			}
			// Si ambos son puramente numéricos, sigue; si uno tiene
			// cualificador (1.0.0-beta) el otro (1.0.0) gana, y entre
			// cualificadores decide el orden textual.
			if pa[i] != pb[i] {
				return qualifierLess(pa[i], pb[i])
			}
			continue
		}
		return pa[i] < pb[i]
	}
	// Mismas partes numéricas comunes: decide por lo que queda
	// ("1.0" < "1.0.1").
	return len(pa) < len(pb)
}

// numericPrefix extrae el número inicial de un segmento ("0-beta" -> 0, true).
func numericPrefix(segment string) (int, bool) {
	i := 0
	for i < len(segment) && segment[i] >= '0' && segment[i] <= '9' {
		i++
	}
	if i == 0 {
		return 0, false
	}
	return atoi(segment[:i]), true
}

// atoi convierte un prefijo numérico ya validado.
func atoi(s string) int {
	n := 0
	for i := 0; i < len(s); i++ {
		n = n*10 + int(s[i]-'0')
	}
	return n
}

// qualifierLess compara dos segmentos con cualificador textual.
// Un segmento sin cualificador ("0") es mayor que uno con él ("0-beta").
func qualifierLess(a, b string) bool {
	qa := qualifierOf(a)
	qb := qualifierOf(b)
	switch {
	case qa == "" && qb == "":
		return false
	case qa == "":
		return false // 0 > 0-beta
	case qb == "":
		return true // 0-beta < 0
	default:
		return qa < qb
	}
}

// qualifierOf devuelve la parte textual de un segmento ("0-beta" -> "-beta").
func qualifierOf(segment string) string {
	i := 0
	for i < len(segment) && segment[i] >= '0' && segment[i] <= '9' {
		i++
	}
	return segment[i:]
}
