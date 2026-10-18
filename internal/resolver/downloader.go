package resolver

import (
	"archive/zip"
	"fmt"
	"io"
	"net/http"
	"os"
	"path/filepath"
	"strings"
	"time"
)

var repositories = []string{
	"https://dl.google.com/dl/android/maven2",
	"https://repo1.maven.org/maven2",
}

func httpGetWithRetry(url string) (*http.Response, error) {
	client := &http.Client{Timeout: 30 * time.Second}
	var resp *http.Response
	var err error

	for attempt := 0; attempt < 5; attempt++ {
		if attempt > 0 {
			time.Sleep(time.Duration(attempt) * 500 * time.Millisecond)
		}

		req, reqErr := http.NewRequest("GET", url, nil)
		if reqErr != nil {
			return nil, reqErr
		}
		req.Header.Set("User-Agent", "Rain-Build-Tool/1.0 (https://github.com/lucassogarayda/rain)")

		resp, err = client.Do(req)
		if err == nil {
			if resp.StatusCode == http.StatusTooManyRequests || resp.StatusCode >= 500 {
				resp.Body.Close()
				continue
			}
			return resp, nil
		}
	}
	return resp, err
}

// DownloadAAR descarga un AAR de los repositorios y extrae classes.jar.
func DownloadAAR(coordinate string, destDir string) (string, error) {
	parts := strings.Split(coordinate, ":")
	if len(parts) != 3 {
		return "", fmt.Errorf("coordenada inválida: %s", coordinate)
	}
	group, artifact, version := parts[0], parts[1], parts[2]
	groupPath := strings.ReplaceAll(group, ".", "/")

	var lastErr error
	for _, repo := range repositories {
		url := fmt.Sprintf("%s/%s/%s/%s/%s-%s.aar",
			repo, groupPath, artifact, version, artifact, version)

		resp, err := httpGetWithRetry(url)
		if err != nil {
			lastErr = fmt.Errorf("error de red en %s: %w", repo, err)
			continue
		}
		if resp.StatusCode != http.StatusOK {
			resp.Body.Close()
			lastErr = fmt.Errorf("HTTP %d en %s", resp.StatusCode, url)
			continue
		}

		if err := os.MkdirAll(destDir, 0755); err != nil {
			resp.Body.Close()
			return "", err
		}
		tmpAAR := filepath.Join(destDir, artifact+"-"+version+".aar")
		f, err := os.Create(tmpAAR)
		if err != nil {
			resp.Body.Close()
			return "", err
		}
		_, copyErr := io.Copy(f, resp.Body)
		resp.Body.Close()
		f.Close()
		if copyErr != nil {
			lastErr = copyErr
			continue
		}

		// Extraer classes.jar
		zr, err := zip.OpenReader(tmpAAR)
		if err != nil {
			os.Remove(tmpAAR)
			lastErr = fmt.Errorf("AAR no es ZIP válido: %w", err)
			continue
		}

		classesJarPath := ""
		for _, zf := range zr.File {
			if zf.Name == "classes.jar" {
				classesJarPath = filepath.Join(destDir, artifact+"-"+version+"-classes.jar")
				out, err := os.Create(classesJarPath)
				if err != nil {
					zr.Close()
					return "", err
				}
				rc, err := zf.Open()
				if err != nil {
					out.Close()
					zr.Close()
					return "", err
				}
				io.Copy(out, rc)
				rc.Close()
				out.Close()
				break
			}
		}
		zr.Close()
		os.Remove(tmpAAR)

		if classesJarPath == "" {
			lastErr = fmt.Errorf("classes.jar no encontrado en %s", url)
			continue
		}
		return classesJarPath, nil
	}

	return "", fmt.Errorf("no se encontró AAR para %s (último error: %v)", coordinate, lastErr)
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
	for _, ext := range []string{"aar", "jar"} {
		var lastErr error
		for _, repo := range repositories {
			url := fmt.Sprintf("%s/%s/%s/%s/%s-%s.%s",
				repo, groupPath, artifact, version, artifact, version, ext)

			resp, err := httpGetWithRetry(url)
			if err != nil {
				lastErr = fmt.Errorf("error de red: %w", err)
				continue
			}
			if resp.StatusCode != http.StatusOK {
				resp.Body.Close()
				lastErr = fmt.Errorf("HTTP %d", resp.StatusCode)
				continue
			}

			tmpPath := filepath.Join(destDir, fmt.Sprintf("%s-%s.%s", artifact, version, ext))
			f, err := os.Create(tmpPath)
			if err != nil {
				resp.Body.Close()
				return "", err
			}
			_, copyErr := io.Copy(f, resp.Body)
			resp.Body.Close()
			f.Close()
			if copyErr != nil {
				os.Remove(tmpPath)
				lastErr = copyErr
				continue
			}

			if ext == "jar" {
				return tmpPath, nil
			}

			// Es AAR, extraer classes.jar
			zr, err := zip.OpenReader(tmpPath)
			if err != nil {
				os.Remove(tmpPath)
				lastErr = err
				continue
			}

			classesJarPath := filepath.Join(destDir, artifact+"-"+version+"-classes.jar")
			var found bool
			for _, zf := range zr.File {
				if zf.Name == "classes.jar" {
					out, err := os.Create(classesJarPath)
					if err != nil {
						zr.Close()
						return "", err
					}
					rc, err := zf.Open()
					if err != nil {
						out.Close()
						zr.Close()
						return "", err
					}
					io.Copy(out, rc)
					rc.Close()
					out.Close()
					found = true
					break
				}
			}
			zr.Close()
			os.Remove(tmpPath)

			if found {
				return classesJarPath, nil
			}
			lastErr = fmt.Errorf("classes.jar no encontrado en AAR")
		}
		if lastErr != nil {
			// Continuar con el siguiente ext
		}
	}

	return "", fmt.Errorf("no se encontró AAR ni JAR para %s", coordinate)
}
