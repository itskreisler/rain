package hilt

import (
	"fmt"
	"os"
	"path/filepath"
	"runtime"
	"strings"
	"sync"
	"sync/atomic"
)

// Anotaciones que convierten una clase en "entry point" de Hilt. Son las
// mismas que mira el plugin de Gradle (AndroidEntryPointClassVisitor).
var entryPointAnnotations = []string{
	"Ldagger/hilt/android/AndroidEntryPoint;",
	"Ldagger/hilt/android/HiltAndroidApp;",
}

// HiltBaseName devuelve el nombre de la clase base que Hilt genera para una
// clase: Hilt_MainActivity, Hilt_MyApp... Las clases anidadas usan "_" en
// lugar de "$" (Hilt_Foo_Bar), igual que hace el plugin de Gradle.
func HiltBaseName(internalName string) string {
	// Antes se buscaba dos veces el último '/' y se creaba una cadena intermedia
	// con ReplaceAll. Ahora el separador se localiza una sola vez y el nombre se
	// copia sustituyendo '$' sobre la marcha. Se copia byte a byte para no
	// alterar secuencias UTF-8 inválidas, igual que hacía ReplaceAll.
	sep := strings.LastIndexByte(internalName, '/')
	simpleStart := 0
	if sep >= 0 {
		simpleStart = sep + 1
	}
	var out strings.Builder
	out.Grow(len(internalName) + len("Hilt_"))
	out.WriteString(internalName[:simpleStart])
	out.WriteString("Hilt_")
	for i := simpleStart; i < len(internalName); i++ {
		c := internalName[i]
		if c == '$' {
			c = '_'
		}
		out.WriteByte(c)
	}
	return out.String()
}

// IsEntryPoint indica si el class file corresponde a una clase anotada con
// @AndroidEntryPoint o @HiltAndroidApp.
func IsEntryPoint(data []byte) (bool, error) {
	cf, err := parseClassFile(data)
	if err != nil {
		return false, err
	}
	for _, descriptor := range entryPointAnnotations {
		if cf.hasClassAnnotation(descriptor) {
			return true, nil
		}
	}
	return false, nil
}

// TransformClass reescribe la superclase de una clase anotada con
// @AndroidEntryPoint o @HiltAndroidApp para que herede de la clase base que
// genera Hilt.
//
// Devuelve el class file reescrito y true si hubo cambios.
//
// Cambia dos cosas, igual que el visitor de ASM del plugin de Gradle:
//
//   - super_class apunta a Hilt_<Clase>.
//   - Los methodrefs que llamaban a la superclase antigua (el invokespecial
//     del constructor y las llamadas a super) apuntan a la nueva. Sin esto la
//     JVM rechaza la clase: el invokespecial <init> de un constructor tiene
//     que apuntar a la superclase directa.
func TransformClass(data []byte) ([]byte, bool, error) {
	cf, err := parseClassFile(data)
	if err != nil {
		return nil, false, err
	}

	entryPoint := false
	for _, descriptor := range entryPointAnnotations {
		if cf.hasClassAnnotation(descriptor) {
			entryPoint = true
			break
		}
	}
	if !entryPoint {
		return data, false, nil
	}

	if cf.superClass == 0 {
		// Object: no hay superclase que cambiar.
		return data, false, nil
	}

	oldSuper := cf.className(cf.superClass)
	if oldSuper == "" {
		return nil, false, fmt.Errorf("no se pudo leer la superclase de la clase")
	}

	ownName := cf.className(cf.thisClass)
	newSuper := HiltBaseName(ownName)
	if newSuper == oldSuper {
		// Ya transformada (o una clase que ya hereda de Hilt_X).
		return data, false, nil
	}

	newIndex := cf.addClassEntry(newSuper)

	// Los methodrefs que apuntaban a la superclase antigua pasan a apuntar a
	// la nueva. Se reescribe el class_index de la entrada, no se crea una
	// entrada nueva: el effecto es el mismo (el invokespecial resuelve por
	// el índice de la clase) y es más simple.
	oldIndex := cf.superClass
	for i := range cf.pool {
		entry := &cf.pool[i]
		if entry.tag != tagMethodref && entry.tag != tagInterfaceMethodref {
			continue
		}
		if entry.refIndex == oldIndex {
			entry.data[1] = byte(newIndex >> 8)
			entry.data[2] = byte(newIndex)
			entry.refIndex = newIndex
		}
	}

	cf.superClass = newIndex
	return cf.serialize(), true, nil
}

// TransformDir reescribe en el sitio todas las clases anotadas de un
// directorio de clases compiladas. Devuelve cuántas se han tocado.
func TransformDir(dir string) (int, error) {
	type classPath struct {
		path string
		mode os.FileMode
	}
	// Walk recorre en orden: recogemos las rutas y su modo para repartirlas
	// después entre workers sin perder ese orden al informar de resultados.
	var paths []classPath
	walkErr := filepath.Walk(dir, func(path string, info os.FileInfo, err error) error {
		if err != nil {
			return err
		}
		if info.IsDir() || !strings.HasSuffix(path, ".class") {
			return nil
		}
		paths = append(paths, classPath{path: path, mode: info.Mode().Perm()})
		return nil
	})
	if len(paths) == 0 {
		return 0, walkErr
	}

	type jobResult struct {
		warning string
		err     error
	}
	results := make([]jobResult, len(paths))
	// Pool fijo de workers: cada uno toma el siguiente índice de forma atómica
	// y escribe un fichero distinto, así que la salida en disco es la misma.
	var next atomic.Int64
	var changed atomic.Int64
	workers := runtime.NumCPU()
	var wg sync.WaitGroup
	wg.Add(workers)
	for w := 0; w < workers; w++ {
		go func() {
			defer wg.Done()
			for {
				i := int(next.Add(1) - 1)
				if i >= len(paths) {
					return
				}
				job := paths[i]
				data, err := os.ReadFile(job.path)
				if err != nil {
					results[i].err = err
					continue
				}
				out, modified, err := TransformClass(data)
				if err != nil {
					// Una clase que no se puede parsear no es culpa de Hilt: se
					// avisa y se sigue, igual que hace el plugin de Gradle (que
					// ignora las clases que no puede instrumentar). El aviso se
					// guarda para imprimirlo después en orden del Walk.
					results[i].warning = fmt.Sprintf("aviso: no se pudo transformar %s: %v\n", job.path, err)
					continue
				}
				if !modified {
					continue
				}
				if err := os.WriteFile(job.path, out, job.mode); err != nil {
					results[i].err = err
					continue
				}
				changed.Add(1)
			}
		}()
	}
	wg.Wait()

	// Publicamos avisos y primer error en el orden del Walk, aunque el trabajo
	// se haya ejecutado en paralelo: los mensajes siguen siendo deterministas.
	var firstErr error
	for _, res := range results {
		if res.warning != "" {
			fmt.Fprint(os.Stderr, res.warning)
		}
		if res.err != nil && firstErr == nil {
			firstErr = res.err
		}
	}
	if walkErr != nil {
		return int(changed.Load()), walkErr
	}
	return int(changed.Load()), firstErr
}
