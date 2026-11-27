package gradle

import (
	"regexp"
	"strings"
	"sync"
)

// Este archivo tiene las utilidades para leer el Kotlin DSL de Gradle
// (build.gradle.kts) sin ejecutarlo.
//
// El Kotlin DSL es un lenguaje completo, así que un parser real sería un
// proyecto entero. Aquí se hace un parseo textual que cubre lo que se usa en
// la configuración de un módulo Android: bloques, asignaciones y llamadas
// simples. Es suficiente para leer namespace, sdk, versiones y dependencias.

// stripComments devuelve el código con los comentarios sustituidos por
// espacios (se conserva la longitud y los saltos de línea). Así los patrones
// de búsqueda no encuentran falsos positivos dentro de un comentario.
//
// Las cadenas de texto se dejan intactas: su contenido se necesita para leer
// valores.
func stripComments(src string) string {
	out := []byte(src)
	inString := false
	escaped := false

	for i := 0; i < len(src); i++ {
		c := src[i]
		if inString {
			if escaped {
				escaped = false
				continue
			}
			switch c {
			case '\\':
				escaped = true
			case '"':
				inString = false
			}
			continue
		}
		switch {
		case c == '"':
			inString = true
		case c == '/' && i+1 < len(src) && src[i+1] == '/':
			// Comentario de línea: hasta el final de la línea.
			for i < len(src) && src[i] != '\n' {
				out[i] = ' '
				i++
			}
			i-- // el bucle exterior incrementa i
		case c == '/' && i+1 < len(src) && src[i+1] == '*':
			out[i] = ' '
			i++
			for i < len(src) {
				if src[i] == '*' && i+1 < len(src) && src[i+1] == '/' {
					out[i] = ' '
					out[i+1] = ' '
					i++
					break
				}
				if src[i] != '\n' {
					out[i] = ' '
				}
				i++
			}
		}
	}
	return string(out)
}

// findBlock busca un bloque con el nombre dado ("android", "dependencies"...)
// y devuelve su contenido (sin las llaves exterior).
//
// Se buscan varias ocurrencias porque un mismo nombre puede aparecer en
// distintos sitios (p. ej. "kotlin" dentro de "android" y fuera).
func findBlocks(src, name string) []string {
	// Los comentarios se quitan una sola vez y los pares de llaves se
	// calculan también una sola vez: antes matchBrace volvía a escanear
	// desde cada candidato (O(L²) con llaves sin cerrar).
	clean := stripComments(src)
	ends := braceEnds(clean)
	var blocks []string

	for i := 0; i < len(clean); {
		body, _, end, ok := tryBlock(clean, ends, name, i, len(clean))
		if !ok {
			i++
			continue
		}
		blocks = append(blocks, body)
		i = end + 1
	}
	return blocks
}

// findBlock devuelve el primer bloque con el nombre dado.
func findBlock(src, name string) (string, bool) {
	blocks := findBlocks(src, name)
	if len(blocks) == 0 {
		return "", false
	}
	return blocks[0], true
}

// matchBrace devuelve la posición de la llave que cierra la de src[start]
// (que debe ser una "{"), o -1 si no está. Se conserva para búsquedas
// puntuales; findBlocks usa la tabla de braceEnds para no reescanear.
func matchBrace(src string, start int) int {
	depth := 0
	inString := false
	escaped := false

	for i := start; i < len(src); i++ {
		c := src[i]
		if inString {
			if escaped {
				escaped = false
				continue
			}
			switch c {
			case '\\':
				escaped = true
			case '"':
				inString = false
			}
			continue
		}
		switch c {
		case '"':
			inString = true
		case '{':
			depth++
		case '}':
			depth--
			if depth == 0 {
				return i
			}
		}
	}
	return -1
}

// braceEnds devuelve, para cada "{" de src, la posición de la "}" que lo
// cierra con las mismas reglas que matchBrace: se ignoran las llaves que
// están dentro de cadenas entre comillas, respetando los escapes.
//
// Se calcula en una sola pasada, de derecha a izquierda. nextOut y nextIn
// apuntan a la siguiente llave que cuenta empezando fuera o dentro de una
// cadena; closeAfter guarda el cierre que equilibra una llave abierta
// pendiente. Con eso findBlocks es O(L) aunque haya llaves sin cerrar.
func braceEnds(src string) map[int]int {
	n := len(src)
	nextOut := make([]int, n+1)
	nextIn := make([]int, n+1)
	closeAfter := make([]int, n+1)
	nextOut[n], nextIn[n], closeAfter[n] = -1, -1, -1

	ends := make(map[int]int)
	for i := n - 1; i >= 0; i-- {
		c := src[i]
		switch c {
		case '"':
			// Empezar fuera abre la cadena; empezar dentro la cierra.
			nextOut[i] = nextIn[i+1]
			nextIn[i] = nextOut[i+1]
		case '\\':
			// Fuera de la cadena la barra no hace nada; dentro salta el byte
			// siguiente, igual que hace matchBrace con escaped.
			nextOut[i] = nextOut[i+1]
			if i+2 <= n {
				nextIn[i] = nextIn[i+2]
			} else {
				nextIn[i] = -1
			}
		case '{', '}':
			nextOut[i] = i
			nextIn[i] = nextIn[i+1]
		default:
			nextOut[i] = nextOut[i+1]
			nextIn[i] = nextIn[i+1]
		}

		if c == '{' {
			if end := closeAfter[i+1]; end >= 0 {
				ends[i] = end
			}
		}

		// closeAfter empieza en i con un "{" pendiente: si hay otro "{" se
		// trata como anidado y si hay un "}" cierra el pendiente. El "{"
		// propio ya se gestionó arriba con closeAfter[i+1].
		j := nextOut[i]
		switch {
		case j < 0:
			closeAfter[i] = -1
		case src[j] == '}':
			closeAfter[i] = j
		default:
			// Otra "{": primero se cierra esa y después la pendiente.
			nested := ends[j]
			if nested < 0 {
				closeAfter[i] = -1
			} else {
				closeAfter[i] = closeAfter[nested+1]
			}
		}
	}
	return ends
}

// tryBlock comprueba si en i empieza un bloque name cuyo cierre cae antes de
// hi, usando la tabla de pares de llaves ya calculada. Es el mismo criterio
// de identificador y de espacios que usaba findBlocks.
func tryBlock(src string, ends map[int]int, name string, i, hi int) (string, int, int, bool) {
	if src[i] != name[0] {
		return "", 0, 0, false
	}
	// El nombre tiene que ser un identificador completo.
	if i > 0 && isIdentChar(src[i-1]) {
		return "", 0, 0, false
	}
	if i+len(name) < hi && isIdentChar(src[i+len(name)]) {
		return "", 0, 0, false
	}
	// Debe ir seguido de "{".
	j := i + len(name)
	for j < hi && isBlockSpace(src[j]) {
		j++
	}
	if j >= hi || src[j] != '{' {
		return "", 0, 0, false
	}
	end, ok := ends[j]
	if !ok || end >= hi {
		return "", 0, 0, false
	}
	return src[j+1 : end], j, end, true
}

// isBlockSpace son los espacios que se permiten entre el nombre y la llave.
func isBlockSpace(c byte) bool {
	return c == ' ' || c == '\t' || c == '\n' || c == '\r'
}

func isIdentChar(c byte) bool {
	return c == '_' || c == '.' ||
		(c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')
}

// parseProguardFiles extrae las rutas de archivos de reglas que aparecen en
// las llamadas proguardFiles(...) del archivo de build.
//
// Se ignoran las que no son rutas (las que trae getDefaultProguardFile,
// que apuntan al SDK de Android): rain tiene sus propias reglas base.
func parseProguardFiles(src string) []string {
	var out []string
	for _, args := range findCalls(src, "proguardFiles") {
		for _, arg := range splitStrings(args) {
			if !strings.HasSuffix(arg, ".pro") && !strings.HasSuffix(arg, ".txt") {
				continue
			}
			out = append(out, arg)
		}
	}
	return out
}

// findCalls devuelve el texto de los argumentos de cada llamada a name(...) que
// aparece en src.
//
// A diferencia de un regex, tiene en cuenta los paréntesis anidados: una
// llamada puede ser proguardFiles(getDefaultProguardFile("x.txt"), "y.pro") y
// el regex se pararía en el primer ")".
func findCalls(src, name string) []string {
	// La pila de paréntesis se calcula una sola vez. Se cuentan todos los
	// paréntesis, incluso los de cadenas o comentarios, como en el escaneo
	// original; así se conserva qué se considera una llamada.
	ends := parenEnds(src)
	var out []string
	for i := 0; i < len(src); {
		j := strings.Index(src[i:], name)
		if j < 0 {
			break
		}
		pos := i + j
		i = pos + len(name)

		// El nombre debe ir solo: no vale "misproguardFiles(".
		if pos > 0 && isIdentChar(src[pos-1]) {
			continue
		}
		// Debe seguir un "(".
		k := i
		for k < len(src) && isBlockSpace(src[k]) {
			k++
		}
		if k >= len(src) || src[k] != '(' {
			continue
		}
		end, ok := ends[k]
		if !ok {
			continue
		}
		out = append(out, src[k+1:end])
		i = end + 1
	}
	return out
}

// parenEnds calcula en una pasada la posición de la ")" que cierra cada "("
// de src con una pila. Los ")" sin abrir se ignoran, igual que hacía el
// escaneo desde cada candidato de findCalls.
func parenEnds(src string) map[int]int {
	ends := make(map[int]int)
	var stack []int
	for i := 0; i < len(src); i++ {
		switch src[i] {
		case '(':
			stack = append(stack, i)
		case ')':
			if len(stack) > 0 {
				open := stack[len(stack)-1]
				stack = stack[:len(stack)-1]
				ends[open] = i
			}
		}
	}
	return ends
}

// regexCache guarda las expresiones ya compiladas. Las claves (namespace,
// compileSdk, ...) se repiten en cada módulo, así que compilarlas una sola
// vez evita el coste de MustCompile en cada llamada.
var regexCache sync.Map

// cachedRegex devuelve la expresión compilada para pattern, reutilizando la
// compilación anterior si ya está en la caché.
func cachedRegex(pattern string) *regexp.Regexp {
	if re, ok := regexCache.Load(pattern); ok {
		return re.(*regexp.Regexp)
	}
	re := regexp.MustCompile(pattern)
	actual, _ := regexCache.LoadOrStore(pattern, re)
	return actual.(*regexp.Regexp)
}

// stringValue busca la primera asignación de cadena de una clave.
func stringValue(src, key string) string {
	re := cachedRegex(`(?m)` + regexp.QuoteMeta(key) + `\s*=\s*"([^"]*)"`)
	if m := re.FindStringSubmatch(src); m != nil {
		return m[1]
	}
	// También se acepta la forma "key("valor")" (p. ej. jvmTarget("17")).
	re2 := cachedRegex(regexp.QuoteMeta(key) + `\s*\(\s*"([^"]*)"\s*\)`)
	if m := re2.FindStringSubmatch(src); m != nil {
		return m[1]
	}
	return ""
}

// intValue busca `key = 36` (o `key(36)`) y devuelve el número.
func intValue(src, key string) int {
	re := cachedRegex(`(?m)` + regexp.QuoteMeta(key) + `\s*=\s*(-?\d+)`)
	if m := re.FindStringSubmatch(src); m != nil {
		return atoiSafe(m[1])
	}
	re2 := cachedRegex(regexp.QuoteMeta(key) + `\s*\(\s*(-?\d+)\s*\)`)
	if m := re2.FindStringSubmatch(src); m != nil {
		return atoiSafe(m[1])
	}
	return 0
}

func atoiSafe(s string) int {
	n := 0
	neg := false
	for i := 0; i < len(s); i++ {
		if i == 0 && s[i] == '-' {
			neg = true
			continue
		}
		if s[i] < '0' || s[i] > '9' {
			return 0
		}
		n = n*10 + int(s[i]-'0')
	}
	if neg {
		return -n
	}
	return n
}

// reJavaVersion busca "JavaVersion.VERSION_17" o "JvmTarget.JVM_17".
var reJavaVersion = regexp.MustCompile(`(?:JavaVersion|JvmTarget)\.(?:VERSION_|JVM_)(\d+)`)

// javaVersionExtrae el nivel de Java de una expresión como
// "JavaVersion.VERSION_17" o "org.jetbrains...JvmTarget.JVM_17".
func javaVersion(src string) int {
	if m := reJavaVersion.FindStringSubmatch(src); m != nil {
		return atoiSafe(m[1])
	}
	return 0
}

// splitStatements divide un bloque en sentencias, respetando paréntesis,
// llaves y cadenas.
func splitStatements(src string) []string {
	var out []string
	depth := 0
	inString := false
	escaped := false
	start := 0

	for i := 0; i < len(src); i++ {
		c := src[i]
		if inString {
			if escaped {
				escaped = false
				continue
			}
			switch c {
			case '\\':
				escaped = true
			case '"':
				inString = false
			}
			continue
		}
		switch c {
		case '"':
			inString = true
		case '(', '{', '[':
			depth++
		case ')', '}', ']':
			depth--
		case '\n':
			if depth == 0 {
				stmt := strings.TrimSpace(src[start:i])
				if stmt != "" {
					out = append(out, stmt)
				}
				start = i + 1
			}
		}
	}
	if stmt := strings.TrimSpace(src[start:]); stmt != "" {
		out = append(out, stmt)
	}
	return out
}
