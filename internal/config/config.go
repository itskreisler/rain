package config

import (
	"bytes"
	"fmt"
	"os"

	"github.com/pelletier/go-toml/v2"
)

type Config struct {
	Project      Project      `toml:"project"`
	Build        Build        `toml:"build"`
	Mode         string       `toml:"mode"`
	Dependencies []string     `toml:"dependencies"`
	Kapt         Kapt         `toml:"kapt"`
	Signing      *Signing     `toml:"signing"`
	Modules      []Module     `toml:"modules"`
	Repositories Repositories `toml:"repositories"`
	Lint         Lint         `toml:"lint"`
	Format       Format       `toml:"format"`
}

// Kapt configura los annotation processors que se ejecutan antes de compilar
// (el mecanismo kapt de Kotlin).
//
// Sirve para processors que generan código a partir del código Kotlin:
// Dagger/Hilt, Room, AutoValue...
type Kapt struct {
	// Processors lista los artefactos de los annotation processors, en
	// formato "grupo:artifacto:version" (p. ej.
	// "com.google.dagger:hilt-compiler:2.60.1"). Se resuelven igual que las
	// dependencias normales, con su cierre transitivo.
	Processors []string `toml:"processors"`
	// Options son opciones para los processors, en formato "clave=valor"
	// (se pasan al processor como -Aclave=valor).
	Options []string `toml:"options"`
}

// Lint configura "rain lint", el comando que ejecuta los analizadores
// estáticos opcionales (ktlint, detekt, pmd).
type Lint struct {
	// Tools lista los analizadores a ejecutar. Vacío o con "auto" usa los que
	// estén instalados en el entorno.
	Tools []string `toml:"tools"`
	// FailOn decide si "rain lint" devuelve error: "never", "warning" (por
	// defecto) o "error".
	FailOn string `toml:"fail_on"`
	// TypeResolution pasa el classpath del proyecto a detekt. Detecta más
	// cosas, pero es más lento y puede fallar con algunas versiones.
	TypeResolution bool `toml:"type_resolution"`
	// PmdRuleset permite usar un ruleset propio en lugar del quickstart de
	// PMD. La ruta es relativa a la raíz del proyecto.
	PmdRuleset string `toml:"pmd_ruleset"`
	// PmdBaseline lista los avisos de PMD ya aceptados, en formato
	// "fichero|regla". La ruta es relativa a la raíz del proyecto.
	PmdBaseline string `toml:"pmd_baseline"`
}

// Format configura "rain fmt". Solo tiene opciones para Java: Kotlin ya toma
// su estilo de .editorconfig a través de ktlint.
type Format struct {
	// JavaStyle es "google" (2 espacios) o "aosp" (4 espacios). Por defecto,
	// "aosp", que es lo habitual en proyectos Android.
	JavaStyle string `toml:"java_style"`
	// JavaLineLength es la columna máxima. 0 usa 120.
	JavaLineLength int `toml:"java_line_length"`
	// JavaSkipImports conserva el orden de los imports y no elimina los que
	// parecen sin usar. Por defecto se hace así para no reescribir código
	// heredado. Ponlo a false para dejar que google-java-format los ordene.
	JavaSkipImports *bool `toml:"java_skip_imports"`
	// JavaFiles limita el formateo a estas rutas o globs (relativos a la raíz
	// del proyecto). Vacío significa todos los .java del módulo.
	JavaFiles []string `toml:"java_files"`
	// JavaExclude quita del formateo los ficheros que encajen con estos
	// patrones. Tiene prioridad sobre JavaFiles.
	JavaExclude []string `toml:"java_exclude"`
}

// Repositories permite declarar repositorios Maven adicionales. Google Maven y
// Maven Central siempre están presentes, y JitPack se añade automáticamente
// para grupos com.github.* / io.github.*; aquí solo hace falta lo demás (p. ej.
// un Nexus propio o un repositorio de terceros).
type Repositories struct {
	Extra []string `toml:"extra"`
}

type Project struct {
	Name    string `toml:"name"`
	Package string `toml:"package"`
	Version string `toml:"version"`
}

type Build struct {
	Debug bool `toml:"debug"`
	// MinSdk y TargetSdk son enteros de API. 0 = valor por defecto de Rain.
	MinSdk int `toml:"min_sdk"`
	// TargetSdk 0 = valor por defecto de Rain.
	TargetSdk int `toml:"target_sdk"`
	// VersionCode 0 = 1. VersionName vacío = Project.Version o "1.0".
	VersionCode int    `toml:"version_code"`
	VersionName string `toml:"version_name"`
	// ApplicationID permite separar el id de la app del namespace del código.
	ApplicationID string `toml:"application_id"`
	// JavaTarget es el nivel de bytecode (8, 11, 17, 21). 0 = automático.
	// JavaTarget es el nivel de bytecode del código Java/Kotlin. 0 = automático.
	JavaTarget int `toml:"java_target"`
	// ProguardFiles son archivos con reglas de R8/ProGuard adicionales a las
	// que rain encuentra por su cuenta (proguard-rules.pro en el módulo).
	// Las rutas son relativas a la raíz del proyecto.
	ProguardFiles []string `toml:"proguard_files"`
	// DebugDependencies son las dependencias que solo van en la variante
	// debug (en Gradle, debugImplementation). En un release se excluyen.
	DebugDependencies []string `toml:"debug_dependencies"`
	// ReleaseDependencies son las que solo van en la variante release
	// (en Gradle, releaseImplementation).
	ReleaseDependencies []string `toml:"release_dependencies"`
}

type Module struct {
	Name string `toml:"name"`
	Path string `toml:"path"`
}

type Signing struct {
	StoreFile     string `toml:"store_file"`
	StorePassword string `toml:"store_password"`
	KeyAlias      string `toml:"key_alias"`
	KeyPassword   string `toml:"key_password"`
}

func Write(path string, cfg Config) error {
	data, err := toml.Marshal(cfg)
	if err != nil {
		return err
	}
	return os.WriteFile(path, data, 0644)
}

// Read carga el rain.toml. El parseo es estricto: una clave desconocida o mal
// ubicada devuelve error en lugar de ignorarse en silencio.
//
// El error más común es colocar claves raíz (mode, dependencies, modules) DESPUÉS
// de una cabecera de tabla: en TOML todo lo que sigue a [build] pertenece a esa
// tabla, así que "dependencies = [...]" acababa dentro de [build] y se perdía.
// El build continuaba sin dependencias y fallaba más tarde con errores de
// compilación confusos ("unresolved reference"), por eso ahora se falla ya.
func Read(path string) (Config, error) {
	var cfg Config
	data, err := os.ReadFile(path)
	if err != nil {
		return cfg, err
	}
	dec := toml.NewDecoder(bytes.NewReader(data))
	dec.DisallowUnknownFields()
	if err := dec.Decode(&cfg); err != nil {
		return cfg, fmt.Errorf("%s: %w (las claves raíz mode/dependencies/modules deben ir ANTES de cualquier cabecera [tabla])", path, err)
	}
	return cfg, nil
}
