package pom

import (
	"fmt"
	"strings"
)

// newPropertyReplacer construye un sustitutor de una sola pasada.
// Antes ResolveProperties recorría todas las propiedades por cada campo y
// llamaba a ReplaceAll dentro de ese bucle; con un Replacer la búsqueda la
// hace un trie interno y el coste deja de depender de |props| por campo.
func newPropertyReplacer(props map[string]string) *strings.Replacer {
	pairs := make([]string, 0, 2*len(props))
	for key, val := range props {
		pairs = append(pairs, "${"+key+"}", val)
	}
	return strings.NewReplacer(pairs...)
}

// resolvePropertiesWith resuelve referencias anidadas reutilizando un
// sustitutor ya construido. Se mantiene el límite histórico de 10 niveles.
func resolvePropertiesWith(replacer *strings.Replacer, value string) string {
	if value == "" {
		return ""
	}
	for i := 0; i < 10; i++ {
		next := replacer.Replace(value)
		if next == value {
			return value
		}
		value = next
	}
	return value
}

// ResolveProperties reemplaza las referencias ${...} en una cadena
// usando el mapa de propiedades fusionadas.
func ResolveProperties(value string, props map[string]string) string {
	return resolvePropertiesWith(newPropertyReplacer(props), value)
}

// ResolveDependencies devuelve la lista de dependencias con versiones resueltas.
func ResolveDependencies(p *Project, mgmt map[string]string, props map[string]string) ([]Dependency, error) {
	var resolved []Dependency

	// El sustitutor se construye una sola vez por POM: reconstruirlo para cada
	// grupo, artefacto y versión costaría O(|props|) por campo.
	replacer := newPropertyReplacer(props)
	for _, dep := range p.Dependencies {
		groupID := resolvePropertiesWith(replacer, dep.GroupID)
		artifactID := resolvePropertiesWith(replacer, dep.ArtifactID)
		version := resolvePropertiesWith(replacer, dep.Version)

		if version == "" {
			key := groupID + ":" + artifactID
			if managed, ok := mgmt[key]; ok {
				version = resolvePropertiesWith(replacer, managed)
			} else {
				return nil, fmt.Errorf("sin versión para %s:%s y no está en dependencyManagement", groupID, artifactID)
			}
		}

		// Colapsar rangos de versión ([2.1.0] -> 2.1.0)
		version = NormalizeVersion(version)
		if version == "" {
			return nil, fmt.Errorf("rango de versión no soportado para %s:%s", groupID, artifactID)
		}

		resolved = append(resolved, Dependency{
			GroupID:    groupID,
			ArtifactID: artifactID,
			Version:    version,
			Scope:      dep.Scope,
			Optional:   dep.Optional,
		})
	}

	return resolved, nil
}
