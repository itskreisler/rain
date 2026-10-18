package config

import (
"os"

"github.com/pelletier/go-toml/v2"
)

type Config struct {
Project      Project  `toml:"project"`
Build        Build    `toml:"build"`
Mode         string   `toml:"mode"`
Dependencies []string `toml:"dependencies"`
Signing      *Signing `toml:"signing"`
Modules      []Module `toml:"modules"`
}

type Project struct {
Name    string `toml:"name"`
Package string `toml:"package"`
Version string `toml:"version"`
}

type Build struct {
Debug bool `toml:"debug"`
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
