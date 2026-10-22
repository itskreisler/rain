package gradle

import (
	"regexp"
	"strings"
)

// reLocalVal busca una declaración de val local dentro del bloque
// dependencies: "val composeBom = platform("androidx.compose:compose-bom:...")".
var reLocalVal = regexp.MustCompile(`^val\s+([A-Za-z_]\w*)\s*=\s*(.+)$`)

// reDependencyLine busca una llamada de configuración:
// implementation("g:a:v"), "baselineProfile"(project(":x")), kapt("g:a:v")...
var reDependencyLine = regexp.MustCompile(`^(?:"([^"]+)"|([A-Za-z_]\w*))\s*\((.*)\)$`)

// reQuotedString extrae la primera cadena entre comillas del texto.
var reQuotedString = regexp.MustCompile(`"([^"]*)"`)

// parseDependencies parsea el bloque "dependencies" de un módulo.
//
// Devuelve las dependencias de compilación/runtime, los annotation processors
// (kapt) y las referencias a otros módulos del proyecto.
//
// Se ignoran las configuraciones de test (testImplementation,
// androidTestImplementation...): no forman parte de la app.
func parseDependencies(body string) (deps []Dependency, kapt []Dependency, local []string) {
	// Primero los val locales del propio bloque: las dependencias sin versión
	// suelen referenciar un BOM declarado así.
	vals := map[string]Dependency{}
	for _, stmt := range splitStatements(body) {
		m := reLocalVal.FindStringSubmatch(stmt)
		if m == nil {
			continue
		}
		if dep, ok := parseDependencyValue(m[2]); ok {
			vals[m[1]] = dep
		}
	}

	for _, stmt := range splitStatements(body) {
		// Los val locales ya se han resuelto.
		if reLocalVal.MatchString(stmt) {
			continue
		}
		m := reDependencyLine.FindStringSubmatch(stmt)
		if m == nil {
			continue
		}
		scope := m[1]
		if scope == "" {
			scope = m[2]
		}
		argument := strings.TrimSpace(m[3])

		dep, ok := parseDependencyValue(argument)
		if !ok {
			// Puede ser una referencia a un val local.
			if v, found := vals[argument]; found {
				dep = v
			} else {
				continue
			}
		}
		dep.Scope = scope

		switch {
		case dep.Local:
			local = append(local, dep.Coordinate)
		case scope == "kapt":
			kapt = append(kapt, dep)
		case scope == "ksp":
			// rain no soporta KSP: los processors de KSP no se ejecutan.
		case strings.HasPrefix(scope, "test") || strings.HasPrefix(scope, "androidTest"):
			// Configuraciones de test: no van en la app.
		default:
			deps = append(deps, dep)
		}
	}
	return deps, kapt, local
}

// parseDependencyValue interpreta el argumento de una dependencia.
//
// Acepta una coordenada ("g:a:v"), un platform(...) (un BOM), un project(...)
// (otro módulo del proyecto) o un val local ya resuelto.
func parseDependencyValue(value string) (Dependency, bool) {
	value = strings.TrimSpace(value)
	if value == "" {
		return Dependency{}, false
	}

	// platform("g:a:v") → BOM.
	if strings.HasPrefix(value, "platform(") {
		inner := strings.TrimSuffix(strings.TrimPrefix(value, "platform("), ")")
		coord := firstQuoted(inner)
		if coord == "" {
			return Dependency{}, false
		}
		return Dependency{Coordinate: coord, Platform: true}, true
	}

	// project(":otro") → otro módulo del proyecto.
	if strings.HasPrefix(value, "project(") {
		inner := strings.TrimSuffix(strings.TrimPrefix(value, "project("), ")")
		path := firstQuoted(inner)
		if path == "" {
			return Dependency{}, false
		}
		return Dependency{Coordinate: path, Local: true}, true
	}

	// Una cadena suelta: la coordenada.
	if strings.HasPrefix(value, `"`) {
		coord := firstQuoted(value)
		if coord == "" {
			return Dependency{}, false
		}
		return Dependency{Coordinate: coord}, true
	}

	// Un identificador: un val local que no se ha podido resolver, o un
	// catálogo de versiones de Gradle (libs.foo).
	return Dependency{}, false
}

// firstQuoted devuelve la primera cadena entre comillas del texto.
func firstQuoted(src string) string {
	if m := reQuotedString.FindStringSubmatch(src); m != nil {
		return m[1]
	}
	return ""
}
