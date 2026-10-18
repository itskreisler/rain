package pom

import (
	"encoding/xml"
	"fmt"
	"net/http"
	"os"
	"strings"
	"time"
)

// Repositorios en orden de prioridad
var repositories = []string{
	"https://dl.google.com/dl/android/maven2",
	"https://repo.maven.apache.org/maven2",
	"https://repo1.maven.org/maven2",
}

const (
	UserAgentHeader = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
)

// Project representa la estructura relevante de un POM de Maven.
type Project struct {
	XMLName              xml.Name     `xml:"project"`
	GroupID              string       `xml:"groupId"`
	ArtifactID           string       `xml:"artifactId"`
	Version              string       `xml:"version"`
	Parent               *Parent      `xml:"parent"`
	Properties           []Property   `xml:"properties>entry"`
	DependencyManagement *DepMgmt     `xml:"dependencyManagement"`
	Dependencies         []Dependency `xml:"dependencies>dependency"`
}

type Parent struct {
	GroupID    string `xml:"groupId"`
	ArtifactID string `xml:"artifactId"`
	Version    string `xml:"version"`
}

type Property struct {
	XMLName xml.Name
	Value   string `xml:",chardata"`
}

type DepMgmt struct {
	Dependencies []Dependency `xml:"dependencies>dependency"`
}

type Dependency struct {
	GroupID    string `xml:"groupId"`
	ArtifactID string `xml:"artifactId"`
	Version    string `xml:"version"`
	Scope      string `xml:"scope"`
	Optional   string `xml:"optional"`
}

func httpGetWithRetry(url string) (*http.Response, error) {
	client := &http.Client{Timeout: 30 * time.Second}
	var resp *http.Response
	var err error

	for attempt := 0; attempt < 7; attempt++ {
		if attempt > 0 {
			backoff := time.Duration(1<<uint(attempt-1)) * time.Second
			time.Sleep(backoff)
		}

		req, reqErr := http.NewRequest("GET", url, nil)
		if reqErr != nil {
			return nil, reqErr
		}
		req.Header.Set("User-Agent", UserAgentHeader)

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

// DownloadPOM intenta descargar el POM desde cada repositorio hasta encontrarlo.
func DownloadPOM(coordinate string, destDir string) (string, error) {
	parts := strings.Split(coordinate, ":")
	if len(parts) != 3 {
		return "", fmt.Errorf("coordenada inválida: %s", coordinate)
	}
	group, artifact, version := parts[0], parts[1], parts[2]
	groupPath := strings.ReplaceAll(group, ".", "/")

	// Verificar caché primero
	path := fmt.Sprintf("%s/%s-%s.pom", destDir, artifact, version)
	if _, err := os.Stat(path); err == nil {
		return path, nil
	}

	if err := os.MkdirAll(destDir, 0755); err != nil {
		return "", err
	}

	var lastErr error
	for _, repo := range repositories {
		url := fmt.Sprintf("%s/%s/%s/%s/%s-%s.pom",
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

		f, err := os.Create(path)
		if err != nil {
			resp.Body.Close()
			return "", err
		}
		_, copyErr := f.ReadFrom(resp.Body)
		resp.Body.Close()
		f.Close()

		if copyErr != nil {
			os.Remove(path)
			lastErr = copyErr
			continue
		}

		return path, nil
	}

	return "", fmt.Errorf("no se encontró POM para %s en ningún repositorio (último error: %v)", coordinate, lastErr)
}

// ParsePOM lee y parsea un archivo POM.
func ParsePOM(path string) (*Project, error) {
	data, err := os.ReadFile(path)
	if err != nil {
		return nil, err
	}
	var p Project
	if err := xml.Unmarshal(data, &p); err != nil {
		return nil, fmt.Errorf("parseando POM: %w", err)
	}
	return &p, nil
}

// EffectiveProperties devuelve el mapa de propiedades fusionadas (hijo sobrescribe padre).
func (p *Project) EffectiveProperties(parent *Project) map[string]string {
	props := make(map[string]string)
	if parent != nil {
		for _, prop := range parent.Properties {
			props[prop.XMLName.Local] = strings.TrimSpace(prop.Value)
		}
	}
	for _, prop := range p.Properties {
		props[prop.XMLName.Local] = strings.TrimSpace(prop.Value)
	}
	return props
}
