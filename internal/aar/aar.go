// Package aar prepara dependencias de AndroidX/Compose (AAR y JAR) sin Gradle:
// descarga cada artefacto, extrae classes.jar, res/, R.txt y las bibliotecas
// nativas jni/<abi>/*.so, y fusiona los recursos como hace AGP (aapt2 no
// permite dos definiciones del mismo recurso).
package aar

import (
	"archive/zip"
	"fmt"
	"io"
	"net/http"
	"os"
	"path/filepath"
	"regexp"
	"sort"
	"strings"
	"time"

	"github.com/itskreisler/rain/internal/httpx"
	"github.com/itskreisler/rain/internal/mavenrepo"
)

// Repositorios Maven base en orden de prioridad. JitPack se añade por
// coordenada (solo para grupos com.github.*/io.github.*) vía mavenrepo.For,
// y los extras declarados en rain.toml se añaden al final.
var repositories = mavenrepo.Base

var manifestPackageRe = regexp.MustCompile(`package="([^"]+)"`)

// Library es el resultado de preparar una dependencia.
type Library struct {
	Coordinate    string // group:artifact:version
	ClassJar      string // classes.jar (AAR) o el .jar (JAR); vacío si el AAR no trae código
	ResDir        string // res/ del AAR, si tiene recursos
	Package       string // package del AndroidManifest.xml del AAR (para --extra-packages)
	NativeABIs    []string
	ProguardRules string // ruta a las reglas R8/ProGuard del AAR (proguard.txt), si las trae
}

// Set es el conjunto de dependencias preparadas para compilar.
type Set struct {
	Dir       string // directorio raíz (~/.rain/deps o libs/)
	Libraries []Library
}

// ClassJars devuelve las rutas de todos los jars de clases.
func (s *Set) ClassJars() []string {
	var out []string
	for _, lib := range s.Libraries {
		if lib.ClassJar != "" {
			out = append(out, lib.ClassJar)
		}
	}
	return out
}

// ResDirs devuelve los directorios res/ de las librerías, a fusionar.
func (s *Set) ResDirs() []string {
	var out []string
	for _, lib := range s.Libraries {
		if lib.ResDir != "" {
			out = append(out, lib.ResDir)
		}
	}
	return out
}

// ExtraPackages devuelve los packages de librerías con código (para
// aapt2 link --extra-packages), sin duplicados.
func (s *Set) ExtraPackages() []string {
	seen := make(map[string]bool)
	var out []string
	for _, lib := range s.Libraries {
		if lib.Package != "" && !seen[lib.Package] {
			seen[lib.Package] = true
			out = append(out, lib.Package)
		}
	}
	sort.Strings(out)
	return out
}

// NativeDir devuelve el directorio jni/ con lib/<abi>/*.so, o "" si no hay.
func (s *Set) NativeDir() string {
	dir := filepath.Join(s.Dir, "jni")
	if st, err := os.Stat(dir); err == nil && st.IsDir() {
		return dir
	}
	return ""
}

// ProguardFiles devuelve las reglas R8/ProGuard (consumer-rules) de las
// librerías que las traigan.
func (s *Set) ProguardFiles() []string {
	var out []string
	for _, lib := range s.Libraries {
		if lib.ProguardRules != "" {
			out = append(out, lib.ProguardRules)
		}
	}
	sort.Strings(out)
	return out
}

// Client permite inyectar un http.Client en tests.
type Client struct {
	HTTP   *http.Client
	client *httpx.Client
}

func NewClient() *Client {
	return &Client{HTTP: &http.Client{Timeout: 10 * time.Minute}}
}

// Prepare descarga y prepara cada coordenada en dir, devolviendo el Set.
func (c *Client) Prepare(coords []string, dir string) (*Set, error) {
	if err := os.MkdirAll(filepath.Join(dir, "jars"), 0o755); err != nil {
		return nil, err
	}

	set := &Set{Dir: dir}
	for _, coord := range coords {
		lib, err := c.prepareOne(coord, dir)
		if err != nil {
			return nil, err
		}
		set.Libraries = append(set.Libraries, *lib)
	}
	return set, nil
}

func (c *Client) prepareOne(coord, dir string) (*Library, error) {
	parts := strings.Split(coord, ":")
	if len(parts) != 3 {
		return nil, fmt.Errorf("coordenada inválida: %s", coord)
	}
	_, artifact, version := parts[0], parts[1], parts[2]

	for _, ext := range []string{"aar", "jar"} {
		path, err := c.download(coord, dir, ext)
		if err != nil {
			continue
		}
		if ext == "jar" {
			return c.prepareJAR(coord, dir, path)
		}
		return c.extractAAR(coord, artifact, version, path, dir)
	}
	return nil, fmt.Errorf("no se encontró AAR ni JAR para %s", coord)
}

// prepareJAR prepara un JAR plano. Si el JAR es un stub de Kotlin Multiplatform
// (solo metadata, sin clases) — el caso de androidx.annotation:annotation, que
// publica las clases en annotation-jvm — descarga el artefacto -jvm hermano.
// Gradle resuelve esto vía Gradle Module Metadata; rain lo emula aquí para que
// funcione también en modo simple (sin transitivas).
func (c *Client) prepareJAR(coord, dir, path string) (*Library, error) {
	if jarHasClasses(path) {
		return &Library{Coordinate: coord, ClassJar: path}, nil
	}

	parts := strings.Split(coord, ":")
	if len(parts) == 3 && !strings.HasSuffix(parts[1], "-jvm") {
		group, artifact, version := parts[0], parts[1], parts[2]
		jvmCoord := fmt.Sprintf("%s:%s-jvm:%s", group, artifact, version)
		if jvmPath, err := c.download(jvmCoord, dir, "jar"); err == nil && jarHasClasses(jvmPath) {
			return &Library{Coordinate: jvmCoord, ClassJar: jvmPath}, nil
		}
	}

	// Sin alternativas, se devuelve el JAR tal cual (el usuario verá el error
	// de compilación si de verdad faltan clases).
	return &Library{Coordinate: coord, ClassJar: path}, nil
}

// jarHasClasses indica si un JAR contiene al menos un .class (y por tanto no es
// un stub de metadata de Kotlin Multiplatform).
func jarHasClasses(path string) bool {
	zr, err := zip.OpenReader(path)
	if err != nil {
		return false
	}
	defer func() { _ = zr.Close() }()
	for _, f := range zr.File {
		if strings.HasSuffix(f.Name, ".class") {
			return true
		}
	}
	return false
}

func (c *Client) download(coord, dir, ext string) (string, error) {
	parts := strings.Split(coord, ":")
	group, artifact, version := parts[0], parts[1], parts[2]
	groupPath := strings.ReplaceAll(group, ".", "/")
	dest := filepath.Join(dir, "aars", fmt.Sprintf("%s-%s.%s", artifact, version, ext))

	if fileExists(dest) {
		return dest, nil
	}
	if err := os.MkdirAll(filepath.Dir(dest), 0o755); err != nil {
		return "", err
	}

	var lastErr error
	for _, repo := range mavenrepo.For(repositories, group) {
		url := fmt.Sprintf("%s/%s/%s/%s/%s-%s.%s", repo, groupPath, artifact, version, artifact, version, ext)
		resp, err := c.httpClient().Get(url)
		if err != nil {
			lastErr = err
			continue
		}
		tmp := dest + ".tmp"
		f, err := os.Create(tmp)
		if err != nil {
			_ = resp.Body.Close()
			return "", err
		}
		_, copyErr := io.Copy(f, resp.Body)
		_ = resp.Body.Close()
		_ = f.Close()
		if copyErr != nil {
			_ = os.Remove(tmp)
			lastErr = copyErr
			continue
		}
		if err := os.Rename(tmp, dest); err != nil {
			return "", err
		}
		return dest, nil
	}
	return "", fmt.Errorf("descargando %s.%s: %w", coord, ext, lastErr)
}

// httpClient devuelve el cliente con reintentos, creándolo si falta.
func (c *Client) httpClient() *httpx.Client {
	if c.client == nil {
		if c.HTTP != nil {
			c.client = &httpx.Client{HTTP: c.HTTP}
		} else {
			c.client = httpx.NewClient()
		}
	}
	return c.client
}

func (c *Client) extractAAR(coord, artifact, version, aarPath, dir string) (*Library, error) {
	zr, err := zip.OpenReader(aarPath)
	if err != nil {
		return nil, fmt.Errorf("abriendo AAR %s: %w", coord, err)
	}
	defer func() { _ = zr.Close() }()

	lib := &Library{Coordinate: coord}
	jarDir := filepath.Join(dir, "jars")
	resBase := filepath.Join(dir, "res", artifact+"-"+version)

	for _, f := range zr.File {
		switch {
		case f.Name == "classes.jar":
			dest := filepath.Join(jarDir, artifact+"-"+version+".jar")
			if err := extractFile(f, dest); err != nil {
				return nil, err
			}
			lib.ClassJar = dest
		case strings.HasPrefix(f.Name, "res/"):
			rel := strings.TrimPrefix(f.Name, "res/")
			if rel == "" || strings.HasSuffix(f.Name, "/") {
				continue
			}
			if err := extractFile(f, filepath.Join(resBase, rel)); err != nil {
				return nil, err
			}
			lib.ResDir = resBase
		case f.Name == "AndroidManifest.xml":
			data, err := readZipFile(f)
			if err != nil {
				return nil, err
			}
			if match := manifestPackageRe.FindSubmatch(data); len(match) == 2 {
				// Solo importa si el AAR trae código: su bytecode referencia su R.
				lib.Package = string(match[1])
			}
		case strings.HasPrefix(f.Name, "jni/") && strings.HasSuffix(f.Name, ".so"):
			rel := strings.TrimPrefix(f.Name, "jni/")
			dest := filepath.Join(dir, "jni", rel)
			if err := extractFile(f, dest); err != nil {
				return nil, err
			}
			if abi := strings.Split(rel, "/")[0]; !contains(lib.NativeABIs, abi) {
				lib.NativeABIs = append(lib.NativeABIs, abi)
			}
		case f.Name == "proguard.txt":
			dest := filepath.Join(dir, "proguard", artifact+"-"+version+".pro")
			if err := extractFile(f, dest); err != nil {
				return nil, err
			}
			lib.ProguardRules = dest
		}
	}

	// Un AAR con recursos pero sin código no debe aportar --extra-packages.
	if lib.ClassJar == "" {
		lib.Package = ""
	}
	sort.Strings(lib.NativeABIs)
	return lib, nil
}

func extractFile(f *zip.File, dest string) error {
	if err := os.MkdirAll(filepath.Dir(dest), 0o755); err != nil {
		return err
	}
	rc, err := f.Open()
	if err != nil {
		return err
	}
	defer func() { _ = rc.Close() }()

	out, err := os.Create(dest)
	if err != nil {
		return err
	}
	if _, err := io.Copy(out, rc); err != nil {
		_ = out.Close()
		return err
	}
	return out.Close()
}

func readZipFile(f *zip.File) ([]byte, error) {
	rc, err := f.Open()
	if err != nil {
		return nil, err
	}
	defer func() { _ = rc.Close() }()
	return io.ReadAll(rc)
}

func fileExists(path string) bool {
	st, err := os.Stat(path)
	return err == nil && !st.IsDir()
}

func contains(values []string, value string) bool {
	for _, v := range values {
		if v == value {
			return true
		}
	}
	return false
}
