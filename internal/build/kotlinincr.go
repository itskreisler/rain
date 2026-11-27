package build

import (
	"bufio"
	"crypto/sha256"
	"encoding/hex"
	"encoding/json"
	"fmt"
	"io"
	"os"
	"path/filepath"
	"regexp"
	"sort"
	"strings"
	"unicode"
)

// Índice de fuentes Kotlin para compilar solo lo que cambia. Se guarda en el
// directorio de build y NO sustituye a las marcas .rain-java-hash ni a
// .rain-dex-hash: solo decide cuántos ficheros se pasan a kotlinc.
const (
	kotlinIndexVersion = "rain-kt-v1"
	kotlinIndexFile    = ".rain-kt-hashes.json"
)

type kotlinFileHashes struct {
	// Content detecta cualquier cambio del fichero.
	Content string `json:"content"`
	// ABI resume lo que puede afectar al bytecode de otros ficheros.
	ABI string `json:"abi"`
}

type kotlinSourceIndex struct {
	Version string                      `json:"version"`
	Env     string                      `json:"env"`
	Files   map[string]kotlinFileHashes `json:"files"`
}

// kotlinPlan indica si hay que compilar todo o solo algunos ficheros. Las
// rutas del subconjunto son relativas a la raíz de fuentes y ya ordenadas.
type kotlinPlan struct {
	full  bool
	files []string
}

// Modos del extractor de ABI. ktBody incluye el cuerpo completo (inline,
// const, sealed...), ktInitializer incluye la expresión hasta la siguiente
// declaración de primer nivel.
const (
	ktNormal = iota
	ktSignature
	ktBody
	ktInitializer
)

var (
	reKotlinPackage = regexp.MustCompile(`(?m)^\s*package\s+([A-Za-z_][A-Za-z0-9_.]*)`)
	reJvmName       = regexp.MustCompile(`(?m)^\s*@file:JvmName\(\s*"([^"]+)"\s*\)`)
	reDeclLike      = regexp.MustCompile(`^\s*(?:@[A-Za-z_]\w*(?:\([^)]*\))?\s+)*(?:[A-Za-z_]\w*\s+)*(?:fun|class|object|interface|typealias|enum|annotation|val|var)\b`)
)

// kotlinIndexPath devuelve la ruta del índice dentro del directorio de build.
func kotlinIndexPath(buildDir string) string {
	return filepath.Join(buildDir, kotlinIndexFile)
}

// loadKotlinIndex lee el índice anterior. Un fichero ausente o corrupto se
// trata como "sin estado previo" (compilación completa).
func loadKotlinIndex(path string) (*kotlinSourceIndex, error) {
	data, err := os.ReadFile(path)
	if err != nil {
		return nil, err
	}
	var idx kotlinSourceIndex
	if err := json.Unmarshal(data, &idx); err != nil {
		return nil, fmt.Errorf("parseando el índice Kotlin: %w", err)
	}
	if idx.Version != kotlinIndexVersion || idx.Files == nil {
		return nil, fmt.Errorf("índice Kotlin con versión o contenido no válido")
	}
	return &idx, nil
}

// saveKotlinIndex escribe el índice tras una compilación correcta.
func saveKotlinIndex(buildDir string, idx *kotlinSourceIndex) error {
	if err := os.MkdirAll(buildDir, 0o755); err != nil {
		return err
	}
	data, err := json.MarshalIndent(idx, "", "  ")
	if err != nil {
		return err
	}
	data = append(data, '\n')
	return os.WriteFile(kotlinIndexPath(buildDir), data, 0o644)
}

// buildKotlinIndex calcula los hashes de todas las fuentes Kotlin del proyecto.
// Cualquier problema al clasificar una fuente se resuelve metiendo el fichero
// completo en el hash de ABI, de modo que esa fuente fuerza una compilación
// completa si cambia.
func buildKotlinIndex(cfg Config, genDir string) (*kotlinSourceIndex, error) {
	env, err := kotlinEnvHash(cfg, genDir)
	if err != nil {
		return nil, err
	}
	root := sourceRoot(cfg.AppDir)
	sources, err := collectKotlinFiles(root, cfg.BuildDir)
	if err != nil {
		return nil, err
	}

	idx := &kotlinSourceIndex{
		Version: kotlinIndexVersion,
		Env:     env,
		Files:   make(map[string]kotlinFileHashes, len(sources)),
	}
	for _, src := range sources {
		rel, err := filepath.Rel(root, src)
		if err != nil {
			return nil, err
		}
		content, err := hashFile(src)
		if err != nil {
			return nil, err
		}
		abi, err := hashKotlinABI(src)
		if err != nil {
			return nil, err
		}
		idx.Files[filepath.ToSlash(rel)] = kotlinFileHashes{
			Content: hex.EncodeToString(content),
			ABI:     abi,
		}
	}
	return idx, nil
}

// kotlinEnvHash resume las entradas que no son fuentes Kotlin y que pueden
// cambiar el bytecode: versión, target, dependencias, reglas y Java generado.
// Si cambia alguna, el subconjunto no sería suficiente y hay que compilar todo.
func kotlinEnvHash(cfg Config, genDir string) (string, error) {
	h := sha256.New()
	fmt.Fprintf(h, "rain-kt-env-v1\nrelease=%t\njvm=%d\npackage=%s\n", cfg.Release, cfg.jvmTarget(), cfg.Package)
	plugin := cfg.ComposePlugin
	if plugin == "" {
		plugin = cfg.composePlugin()
	}
	fmt.Fprintf(h, "compose=%s\n", plugin)

	paths := append([]string{cfg.AndroidJar}, cfg.libraryJars()...)
	paths = append(paths, cfg.proguardFiles()...)
	sort.Strings(paths)
	for _, path := range paths {
		if path == "" {
			continue
		}
		st, err := os.Stat(path)
		if err != nil {
			continue
		}
		fmt.Fprintf(h, "entrada\x00%s\x00%d\x00%d\x00", path, st.Size(), st.ModTime().UnixNano())
	}

	generated, err := collectJavaSources(genDir)
	if err != nil && !os.IsNotExist(err) {
		return "", err
	}
	sort.Strings(generated)
	for _, src := range generated {
		sum, err := hashFile(src)
		if err != nil {
			return "", err
		}
		h.Write([]byte(filepath.Base(src)))
		h.Write(sum)
	}
	return hex.EncodeToString(h.Sum(nil)), nil
}

// kotlinIncrementalPlan decide entre compilación completa y subconjunto. Ante
// cualquier duda (sin índice, versión distinta, marcas ausentes, kapt, Compose,
// entradas de build distintas, ficheros añadidos/borrados o ABI cambiada)
// devuelve completo. Solo cambia el contenido con la ABI intacta permite el
// subconjunto.
func kotlinIncrementalPlan(prev, cur *kotlinSourceIndex, cfg Config) kotlinPlan {
	full := kotlinPlan{full: true}
	if prev == nil || cur == nil || prev.Version != kotlinIndexVersion || cur.Version != kotlinIndexVersion {
		return full
	}
	if prev.Env == "" || prev.Env != cur.Env {
		return full
	}
	if len(cur.Files) == 0 || len(prev.Files) != len(cur.Files) {
		return full
	}
	if cfg.Kapt != nil && len(cfg.Kapt.ProcessorJars) > 0 {
		return full
	}
	if cfg.appliesComposePlugin() {
		return full
	}
	objDir := filepath.Join(cfg.BuildDir, "obj")
	if !fileExists(filepath.Join(cfg.BuildDir, javaHashFile)) ||
		!fileExists(filepath.Join(cfg.BuildDir, stampFile)) ||
		!hasClassFiles(objDir) {
		return full
	}

	var changed []string
	for rel, now := range cur.Files {
		before, ok := prev.Files[rel]
		if !ok || before.ABI == "" || now.ABI == "" || before.ABI != now.ABI {
			return full
		}
		if before.Content != now.Content {
			changed = append(changed, rel)
		}
	}
	if len(changed) == 0 {
		return full
	}
	sort.Strings(changed)
	return kotlinPlan{files: changed}
}

// compileKotlinSubset compila solo los ficheros indicados conservando las
// clases del resto. Elimina primero las clases que esos ficheros generaron en
// la compilación anterior para que no queden restos en el DEX.
func compileKotlinSubset(cfg Config, genDir, objDir string, relativeFiles []string) ([]string, error) {
	if err := os.MkdirAll(objDir, 0o755); err != nil {
		return nil, fmt.Errorf("creando el directorio de objetos: %w", err)
	}
	root := sourceRoot(cfg.AppDir)
	files := make([]string, 0, len(relativeFiles))
	for _, rel := range relativeFiles {
		files = append(files, filepath.Join(root, filepath.FromSlash(rel)))
	}
	if err := removeStaleKotlinClasses(objDir, files); err != nil {
		return nil, err
	}
	javaFiles, err := collectJavaSources(sourceRoot(cfg.AppDir), cfg.BuildDir)
	if err != nil && !os.IsNotExist(err) {
		return nil, fmt.Errorf("buscando fuentes Java: %w", err)
	}
	return compileSourceSet(cfg, genDir, objDir, files, javaFiles)
}

// removeStaleKotlinClasses borra la fachada y las clases declaradas en los
// ficheros Kotlin que se van a recompilar. Nunca toca R.class ni
// BuildConfig.class, que vienen del Java generado.
func removeStaleKotlinClasses(objDir string, sources []string) error {
	for _, src := range sources {
		pkg := kotlinPackage(src)
		dir := objDir
		if pkg != "" {
			dir = filepath.Join(objDir, filepath.FromSlash(strings.ReplaceAll(pkg, ".", string(filepath.Separator))))
		}
		base := strings.TrimSuffix(filepath.Base(src), filepath.Ext(src))
		facade := base + "Kt"
		if jvmName := kotlinJvmName(src); jvmName != "" {
			facade = jvmName
		}
		names := append([]string{facade}, kotlinDeclaredTypes(src)...)
		seen := make(map[string]bool, len(names))
		for _, name := range names {
			if name == "" || name == "R" || name == "BuildConfig" || seen[name] {
				continue
			}
			seen[name] = true
			paths := []string{filepath.Join(dir, name+".class")}
			matches, err := filepath.Glob(filepath.Join(dir, name+"$*.class"))
			if err != nil {
				return fmt.Errorf("buscando clases de %s: %w", name, err)
			}
			paths = append(paths, matches...)
			for _, path := range paths {
				if err := os.Remove(path); err != nil && !os.IsNotExist(err) {
					return fmt.Errorf("borrando la clase obsoleta %s: %w", path, err)
				}
			}
		}
	}
	return nil
}

// kotlinPackage lee el paquete declarado en un fichero Kotlin.
func kotlinPackage(path string) string {
	data, err := os.ReadFile(path)
	if err != nil {
		return ""
	}
	if m := reKotlinPackage.FindSubmatch(data); len(m) == 2 {
		return string(m[1])
	}
	return ""
}

// kotlinJvmName lee el nombre de fachada impuesto con @file:JvmName.
func kotlinJvmName(path string) string {
	data, err := os.ReadFile(path)
	if err != nil {
		return ""
	}
	if m := reJvmName.FindSubmatch(data); len(m) == 2 {
		return string(m[1])
	}
	return ""
}

// kotlinDeclaredTypes devuelve los tipos de primer nivel o miembro declarados
// en el fichero. Se usan para borrar sus clases antes de recompilarlo.
func kotlinDeclaredTypes(path string) []string {
	data, err := os.ReadFile(path)
	if err != nil {
		return nil
	}
	var out []string
	seen := make(map[string]bool)
	inBlock := false
	for _, line := range strings.Split(string(data), "\n") {
		code, ok := stripKotlinLine(line, &inBlock)
		if !ok {
			continue
		}
		name, keyword, ok := matchKotlinDecl(code)
		if !ok || !isTypeKeyword(keyword) || seen[name] {
			continue
		}
		seen[name] = true
		out = append(out, name)
	}
	return out
}

// hashKotlinABI calcula el hash de lo que afecta a los llamadores. Las fuentes
// que no se pueden clasificar con confianza se hashean completas: cualquier
// cambio en ellas fuerza la compilación completa.
func hashKotlinABI(path string) (string, error) {
	signature, confident, err := kotlinABISignature(path)
	if err != nil {
		return "", err
	}
	if !confident {
		sum, err := hashFile(path)
		if err != nil {
			return "", err
		}
		return hex.EncodeToString(sum), nil
	}

	// hashFile lee de un fichero; el resumen de ABI se escribe en uno temporal
	// para reutilizarlo y no cargar el fichero completo en memoria.
	tmp, err := os.CreateTemp("", "rain-kt-abi-")
	if err != nil {
		return "", err
	}
	defer func() { _ = os.Remove(tmp.Name()) }()
	if _, err := tmp.WriteString(signature); err != nil {
		_ = tmp.Close()
		return "", err
	}
	if err := tmp.Close(); err != nil {
		return "", err
	}
	sum, err := hashFile(tmp.Name())
	if err != nil {
		return "", err
	}
	return hex.EncodeToString(sum), nil
}

// kotlinABISignature extrae, en orden, las líneas que pueden cambiar el
// bytecode de otros ficheros: package, imports, anotaciones, firmas de primer
// nivel y de miembros de tipos (con supertipos). Los cuerpos normales se
// omiten; los de inline/const/sealed/enum se incluyen porque se incrustan o
// pueden declarar nuevos tipos visibles.
func kotlinABISignature(path string) (string, bool, error) {
	f, err := os.Open(path)
	if err != nil {
		return "", false, err
	}
	defer func() { _ = f.Close() }()

	reader := bufio.NewReader(f)
	var out []string
	depth, startDepth, mode := 0, 0, ktNormal
	fullDeclaration := false
	currentKeyword := ""
	inBlock := false
	// Profundidades que corresponden al cuerpo de un tipo (clase, object,
	// interface...). Solo ahí las firmas de fun/val/var afectan a llamadores;
	// las variables locales dentro de funciones no.
	typeBodyDepths := make(map[int]bool)

	for {
		line, readErr := reader.ReadString('\n')
		if line != "" {
			code, ok := stripKotlinLine(line, &inBlock)
			if !ok {
				return "", false, nil
			}
			trimmed := strings.TrimSpace(code)
			// Se guarda la línea original: en inline/const el literal cuenta
			// como ABI, aunque el código sin literales se use para clasificar.
			raw := strings.TrimSpace(strings.TrimRight(line, "\r\n"))
			before := depth
			after := before + strings.Count(code, "{") - strings.Count(code, "}")
			if after < 0 {
				return "", false, nil
			}
			if after < before {
				delete(typeBodyDepths, before)
			}

			switch mode {
			case ktBody:
				out = append(out, raw)
				depth = after
				if after <= startDepth {
					mode = ktNormal
				}
				continue
			case ktInitializer:
				_, _, declaration := matchKotlinDecl(code)
				if before == startDepth && (declaration || strings.HasPrefix(trimmed, "@")) {
					mode = ktNormal
				} else {
					out = append(out, raw)
					depth = after
					continue
				}
			case ktSignature:
				if !fullDeclaration && strings.Contains(code, "=") && !strings.Contains(code, "{") {
					out = append(out, kotlinSignatureLine(code))
				} else {
					out = append(out, raw)
				}
				depth = after
				switch {
				case after > before:
					if fullDeclaration {
						startDepth = before
						mode = ktBody
					} else {
						mode = ktNormal
						if isTypeKeyword(currentKeyword) {
							typeBodyDepths[after] = true
						}
					}
				case strings.Contains(code, "=") && !strings.Contains(code, "{"):
					if fullDeclaration {
						mode = ktInitializer
					} else {
						mode = ktNormal
					}
				case strings.Contains(code, ";"):
					mode = ktNormal
				}
				continue
			}

			depth = after
			if trimmed == "" {
				continue
			}
			if strings.HasPrefix(trimmed, "@") ||
				(before == 0 && (strings.HasPrefix(trimmed, "package ") || strings.HasPrefix(trimmed, "import "))) {
				out = append(out, raw)
				continue
			}

			_, keyword, matched := matchKotlinDecl(code)
			memberSignature := typeBodyDepths[before] && (keyword == "fun" || keyword == "val" || keyword == "var")
			if matched && (before == 0 || isTypeKeyword(keyword) || memberSignature) {
				fullDeclaration = hasFullBody(code, keyword)
				currentKeyword = keyword
				if !fullDeclaration && strings.Contains(code, "=") && !strings.Contains(code, "{") {
					out = append(out, kotlinSignatureLine(code))
				} else {
					out = append(out, raw)
				}
				switch {
				case after > before:
					if fullDeclaration {
						startDepth = before
						mode = ktBody
					} else if isTypeKeyword(keyword) {
						typeBodyDepths[after] = true
					}
				case strings.Contains(code, "=") && !strings.Contains(code, "{"):
					if fullDeclaration {
						startDepth = before
						mode = ktInitializer
					}
				default:
					startDepth = before
					mode = ktSignature
				}
				continue
			}
			if before == 0 && reDeclLike.MatchString(trimmed) {
				// Una declaración que no sabemos nombrar (identificadores con
				// backticks, por ejemplo) se considera no clasificable.
				return "", false, nil
			}
		}
		if readErr == io.EOF {
			break
		}
		if readErr != nil {
			return "", false, readErr
		}
	}
	if inBlock || depth != 0 || mode == ktSignature {
		return "", false, nil
	}
	return strings.Join(out, "\n") + "\n", true, nil
}

// stripKotlinLine reemplaza comentarios y literales por espacios para que el
// recuento de llaves no se confunda con texto. Devuelve false si la línea no
// se puede clasificar (cadena triple, sin cerrar o identificador con backticks).
func stripKotlinLine(line string, inBlock *bool) (string, bool) {
	b := []byte(line)
	out := make([]byte, len(b))
	for i := range out {
		out[i] = ' '
	}
	for i := 0; i < len(b); {
		if *inBlock {
			if i+1 < len(b) && b[i] == '*' && b[i+1] == '/' {
				*inBlock = false
				i += 2
				continue
			}
			i++
			continue
		}
		if i+1 < len(b) && b[i] == '/' && b[i+1] == '/' {
			break
		}
		if i+1 < len(b) && b[i] == '/' && b[i+1] == '*' {
			*inBlock = true
			i += 2
			continue
		}
		if i+2 < len(b) && b[i] == '"' && b[i+1] == '"' && b[i+2] == '"' {
			return "", false
		}
		if b[i] == '"' || b[i] == '\'' {
			quote := b[i]
			i++
			closed := false
			for i < len(b) {
				if b[i] == '\\' {
					i += 2
					continue
				}
				if b[i] == quote {
					i++
					closed = true
					break
				}
				i++
			}
			if !closed {
				return "", false
			}
			continue
		}
		if b[i] == '`' {
			return "", false
		}
		out[i] = b[i]
		i++
	}
	return string(out), true
}

// matchKotlinDecl reutiliza declPatterns para obtener el nombre declarado y
// distingue entre funciones/propiedades y tipos.
func matchKotlinDecl(code string) (string, string, bool) {
	for _, re := range declPatterns {
		m := re.FindStringSubmatch(code)
		if len(m) > 1 && m[1] != "" {
			return m[1], kotlinDeclKeyword(code, m[1]), true
		}
	}
	return "", "", false
}

// kotlinDeclKeyword busca la palabra clave que precede al nombre declarado.
func kotlinDeclKeyword(code, name string) string {
	idx := strings.Index(code, name)
	if idx < 0 {
		return ""
	}
	prefix := code[:idx]
	best, bestPos := "", -1
	for _, keyword := range []string{"fun", "class", "object", "interface", "typealias", "enum", "annotation", "val", "var"} {
		pos := strings.LastIndex(prefix, keyword)
		if pos > bestPos {
			best, bestPos = keyword, pos
		}
	}
	return best
}

func isTypeKeyword(keyword string) bool {
	return keyword != "" && keyword != "fun" && keyword != "val" && keyword != "var"
}

// hasFullBody indica si el cuerpo de una declaración puede afectar a los
// llamadores (inline, constantes, jerarquías sealed/enum o clases inline).
func hasFullBody(code, keyword string) bool {
	prefix := code
	if idx := strings.Index(code, keyword); idx >= 0 {
		prefix = code[:idx]
	}
	for _, modifier := range []string{"inline", "const", "sealed", "value", "enum", "expect", "actual"} {
		if hasKotlinWord(prefix, modifier) {
			return true
		}
	}
	return false
}

// kotlinSignatureLine descarta la expresión de una declaración con cuerpo
// breve (`fun f() = 1`). El cuerpo normal no afecta a los llamadores, así que
// un literal ahí no debe forzar la compilación de todos los ficheros.
func kotlinSignatureLine(code string) string {
	idx := strings.LastIndex(code, "=")
	if idx < 0 {
		return strings.TrimSpace(code)
	}
	return strings.TrimSpace(code[:idx])
}

func hasKotlinWord(text, word string) bool {
	tokens := strings.FieldsFunc(text, func(r rune) bool {
		return !unicode.IsLetter(r) && !unicode.IsDigit(r) && r != '_'
	})
	for _, token := range tokens {
		if token == word {
			return true
		}
	}
	return false
}
