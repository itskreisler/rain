package build

import (
	"archive/zip"
	"io"
	"os"
	"path/filepath"
	"strings"

	"github.com/itskreisler/rain/internal/aar"
	"github.com/itskreisler/rain/internal/ui"
)

// libraryJars devuelve los jars de clases: los classes.jar de los AAR y los
// JAR sueltos preparados en DepsDir, más los .jar de LibsDir. Cuando hay un set
// de dependencias (Libs) se usa su lista explícita, que incluye tanto los
// classes.jar (en jars/) como los .jar que van directamente en aars/.
func (c Config) libraryJars() []string {
	jars := make(map[string]bool)
	var out []string
	add := func(path string) {
		if path != "" && !jars[path] {
			jars[path] = true
			out = append(out, path)
		}
	}

	if c.Libs != nil {
		for _, jar := range c.Libs.ClassJars() {
			add(jar)
		}
	}

	if c.LibsDir != "" {
		if entries, err := os.ReadDir(c.LibsDir); err == nil {
			for _, e := range entries {
				if !e.IsDir() && strings.HasSuffix(e.Name(), ".jar") {
					add(filepath.Join(c.LibsDir, e.Name()))
				}
			}
		}
	}

	if c.DepsDir != "" {
		jarDir := filepath.Join(c.DepsDir, "jars")
		if entries, err := os.ReadDir(jarDir); err == nil {
			for _, e := range entries {
				if !e.IsDir() && strings.HasSuffix(e.Name(), ".jar") {
					add(filepath.Join(jarDir, e.Name()))
				}
			}
		}
	}

	return out
}

// prepareLibraryResources fusiona los res/ de las librerías (si hay) y devuelve
// el directorio fusionado y los packages para --extra-packages.
func (c Config) prepareLibraryResources(libs *aar.Set) (string, []string, error) {
	if libs == nil {
		return "", nil, nil
	}
	resDirs := libs.ResDirs()
	if len(resDirs) == 0 {
		return "", libs.ExtraPackages(), nil
	}
	merged := filepath.Join(c.BuildDir, "libres-merged")
	if err := aar.MergeResources(resDirs, merged); err != nil {
		return "", nil, err
	}
	return merged, libs.ExtraPackages(), nil
}

// nativeDir devuelve el directorio con lib/<abi>/*.so de los AAR, o "".
func (c Config) nativeDir(libs *aar.Set) string {
	if libs == nil {
		return ""
	}
	return libs.NativeDir()
}

// dedupeJarsByClass resuelve definiciones duplicadas de clases entre jars. Los
// primeros jars de la lista tienen prioridad. Cuando un jar posterior comparte
// solo *algunas* clases con los anteriores (habitual en el grafo de AndroidX:
// variantes -android/-ktx, versiones solapadas), se reempaqueta sin esas clases
// en scratchDir y se usa el jar filtrado. Si todas sus clases están duplicadas
// se descarta por completo.
//
// d8/R8 no resuelven dependencias transitivas y fallan con "Type X is defined
// multiple times"; filtrar a nivel de clase evita repackaging manual del grafo.
func dedupeJarsByClass(jars []string, scratchDir string) []string {
	seen := make(map[string]bool)
	var out []string

	for _, jar := range jars {
		classes, err := jarClassNames(jar)
		if err != nil || len(classes) == 0 {
			// Si no se pueden leer las clases, se conserva el jar tal cual.
			out = append(out, jar)
			continue
		}
		unique := make([]string, 0, len(classes))
		for _, class := range classes {
			if !seen[class] {
				unique = append(unique, class)
			}
		}
		if len(unique) == len(classes) {
			// Sin solapes: se conserva el jar original.
			for _, class := range classes {
				seen[class] = true
			}
			out = append(out, jar)
			continue
		}
		if len(unique) == 0 {
			// Todas sus clases ya estaban: se descarta por completo.
			ui.VerboseLog("dedupe: %s descartado (clases duplicadas)", filepath.Base(jar))
			continue
		}
		// Solape parcial: reempaquetar conservando solo las clases únicas y el
		// resto de entradas (recursos, etc.).
		stripped, err := stripJarClasses(jar, scratchDir, seen)
		if err != nil {
			ui.Warn("dedupe: no se pudo filtrar %s: %v", filepath.Base(jar), err)
			out = append(out, jar)
			for _, class := range classes {
				seen[class] = true
			}
			continue
		}
		ui.VerboseLog("dedupe: %s filtrado (%d clases solapadas)", filepath.Base(jar), len(classes)-len(unique))
		for _, class := range classes {
			seen[class] = true
		}
		out = append(out, stripped)
	}
	return out
}

// jarClassNames devuelve los nombres de clase (con /) definidos en un jar.
func jarClassNames(jar string) ([]string, error) {
	zr, err := zip.OpenReader(jar)
	if err != nil {
		return nil, err
	}
	defer func() { _ = zr.Close() }()

	var classes []string
	for _, f := range zr.File {
		if strings.HasSuffix(f.Name, ".class") {
			classes = append(classes, strings.TrimSuffix(f.Name, ".class"))
		}
	}
	return classes, nil
}

// stripJarClasses escribe en scratchDir un jar que conserva todas las entradas
// no-.class (recursos embebidos, etc.) y solo las clases cuyo nombre no esté ya
// en seen. El nombre deriva del original para que sea estable entre builds.
func stripJarClasses(jar, scratchDir string, seen map[string]bool) (string, error) {
	zr, err := zip.OpenReader(jar)
	if err != nil {
		return "", err
	}
	defer func() { _ = zr.Close() }()

	if err := os.MkdirAll(scratchDir, 0o755); err != nil {
		return "", err
	}
	dest := filepath.Join(scratchDir, "dedupe-"+filepath.Base(jar))
	tmp := dest + ".tmp"

	f, err := os.Create(tmp)
	if err != nil {
		return "", err
	}
	zw := zip.NewWriter(f)
	for _, entry := range zr.File {
		if strings.HasSuffix(entry.Name, ".class") {
			name := strings.TrimSuffix(entry.Name, ".class")
			if seen[name] {
				continue // clase duplicada: se omite
			}
		}
		w, err := zw.Create(entry.Name)
		if err != nil {
			_ = zw.Close()
			_ = f.Close()
			return "", err
		}
		rc, err := entry.Open()
		if err != nil {
			_ = zw.Close()
			_ = f.Close()
			return "", err
		}
		_, err = io.Copy(w, rc)
		_ = rc.Close()
		if err != nil {
			_ = zw.Close()
			_ = f.Close()
			return "", err
		}
	}
	if err := zw.Close(); err != nil {
		_ = f.Close()
		return "", err
	}
	if err := f.Close(); err != nil {
		return "", err
	}
	if err := os.Rename(tmp, dest); err != nil {
		return "", err
	}
	return dest, nil
}
