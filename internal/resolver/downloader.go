package resolver

import (
	"archive/zip"
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strings"

	"github.com/itskreisler/rain/internal/httpx"
	"github.com/itskreisler/rain/internal/mavenrepo"
)

// httpClient es el cliente con reintentos para los repositorios Maven.
var httpClient = httpx.NewClient()

// Repositorios Maven base en orden de prioridad. JitPack se añade por
// coordenada (solo para grupos com.github.*/io.github.*) vía mavenrepo.For,
// y los extras declarados en rain.toml se añaden al final.
var repositories = mavenrepo.Base

// DownloadAAR descarga un AAR de los repositorios y extrae classes.jar.
func DownloadAAR(coordinate string, destDir string) (string, error) {
	parts := strings.Split(coordinate, ":")
	if len(parts) != 3 {
		return "", fmt.Errorf("coordenada inválida: %s", coordinate)
	}
	group, artifact, version := parts[0], parts[1], parts[2]
	groupPath := strings.ReplaceAll(group, ".", "/")

	var lastErr error
	for _, repo := range mavenrepo.For(repositories, group) {
		url := fmt.Sprintf("%s/%s/%s/%s/%s-%s.aar",
			repo, groupPath, artifact, version, artifact, version)

		resp, err := httpClient.Get(url)
		if err != nil {
			lastErr = fmt.Errorf("%w en %s", err, url)
			continue
		}

		if err := os.MkdirAll(destDir, 0755); err != nil {
			_ = resp.Body.Close()
			return "", err
		}
		tmpAAR := filepath.Join(destDir, artifact+"-"+version+".aar")
		f, err := os.Create(tmpAAR)
		if err != nil {
			_ = resp.Body.Close()
			return "", err
		}
		_, copyErr := io.Copy(f, resp.Body)
		_ = resp.Body.Close()
		_ = f.Close()
		if copyErr != nil {
			lastErr = copyErr
			continue
		}

		// Extraer classes.jar
		zr, err := zip.OpenReader(tmpAAR)
		if err != nil {
			_ = os.Remove(tmpAAR)
			lastErr = fmt.Errorf("AAR no es ZIP válido: %w", err)
			continue
		}

		classesJarPath := ""
		for _, zf := range zr.File {
			if zf.Name == "classes.jar" {
				classesJarPath = filepath.Join(destDir, artifact+"-"+version+"-classes.jar")
				out, err := os.Create(classesJarPath)
				if err != nil {
					_ = zr.Close()
					return "", err
				}
				rc, err := zf.Open()
				if err != nil {
					_ = out.Close()
					_ = zr.Close()
					return "", err
				}
				if _, err := io.Copy(out, rc); err != nil {
					_ = rc.Close()
					_ = out.Close()
					_ = zr.Close()
					_ = os.Remove(classesJarPath)
					return "", fmt.Errorf("extrayendo classes.jar: %w", err)
				}
				_ = rc.Close()
				_ = out.Close()
				break
			}
		}
		_ = zr.Close()
		_ = os.Remove(tmpAAR)

		if classesJarPath == "" {
			lastErr = fmt.Errorf("classes.jar no encontrado en %s", url)
			continue
		}
		return classesJarPath, nil
	}

	if lastErr != nil {
		return "", fmt.Errorf("no se encontró AAR para %s: %w", coordinate, lastErr)
	}
	return "", fmt.Errorf("no se encontró AAR para %s", coordinate)
}

// DownloadArtifact descarga un artefacto (AAR o JAR) y devuelve la ruta a usar en el classpath.
// Si es AAR, extrae classes.jar; si es JAR, lo guarda directamente.
func DownloadArtifact(coordinate string, destDir string) (string, error) {
	parts := strings.Split(coordinate, ":")
	if len(parts) != 3 {
		return "", fmt.Errorf("coordenada inválida: %s", coordinate)
	}
	group, artifact, version := parts[0], parts[1], parts[2]
	groupPath := strings.ReplaceAll(group, ".", "/")

	if err := os.MkdirAll(destDir, 0755); err != nil {
		return "", err
	}

	// Probar primero AAR, luego JAR
	var lastErr error
	for _, ext := range []string{"aar", "jar"} {
		for _, repo := range mavenrepo.For(repositories, group) {
			url := fmt.Sprintf("%s/%s/%s/%s/%s-%s.%s",
				repo, groupPath, artifact, version, artifact, version, ext)

			resp, err := httpClient.Get(url)
			if err != nil {
				lastErr = err
				continue
			}

			tmpPath := filepath.Join(destDir, fmt.Sprintf("%s-%s.%s", artifact, version, ext))
			f, err := os.Create(tmpPath)
			if err != nil {
				_ = resp.Body.Close()
				return "", err
			}
			_, copyErr := io.Copy(f, resp.Body)
			_ = resp.Body.Close()
			_ = f.Close()
			if copyErr != nil {
				_ = os.Remove(tmpPath)
				lastErr = copyErr
				continue
			}

			if ext == "jar" {
				return tmpPath, nil
			}

			// Es AAR, extraer classes.jar
			zr, err := zip.OpenReader(tmpPath)
			if err != nil {
				_ = os.Remove(tmpPath)
				lastErr = err
				continue
			}

			classesJarPath := filepath.Join(destDir, artifact+"-"+version+"-classes.jar")
			var found bool
			for _, zf := range zr.File {
				if zf.Name == "classes.jar" {
					out, err := os.Create(classesJarPath)
					if err != nil {
						_ = zr.Close()
						return "", err
					}
					rc, err := zf.Open()
					if err != nil {
						_ = out.Close()
						_ = zr.Close()
						return "", err
					}
					if _, err := io.Copy(out, rc); err != nil {
						_ = rc.Close()
						_ = out.Close()
						_ = zr.Close()
						_ = os.Remove(classesJarPath)
						return "", fmt.Errorf("extrayendo classes.jar: %w", err)
					}
					_ = rc.Close()
					_ = out.Close()
					found = true
					break
				}
			}
			_ = zr.Close()
			_ = os.Remove(tmpPath)

			if found {
				return classesJarPath, nil
			}
			lastErr = fmt.Errorf("classes.jar no encontrado en AAR")
		}
	}

	if lastErr != nil {
		return "", fmt.Errorf("no se encontró AAR ni JAR para %s: %w", coordinate, lastErr)
	}
	return "", fmt.Errorf("no se encontró AAR ni JAR para %s", coordinate)
}
