package config

import (
	"os"

	"github.com/pelletier/go-toml/v2"
)

type Config struct {
	Project      Project      `toml:"project"`
	Build        Build        `toml:"build"`
	Mode         string       `toml:"mode"`
	Dependencies []string     `toml:"dependencies"`
	Signing      *Signing     `toml:"signing"`
	Modules      []Module     `toml:"modules"`
	Repositories Repositories `toml:"repositories"`
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
	JavaTarget int `toml:"java_target"`
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

func Read(path string) (Config, error) {
	var cfg Config
	data, err := os.ReadFile(path)
	if err != nil {
		return cfg, err
	}
	err = toml.Unmarshal(data, &cfg)
	return cfg, err
}
