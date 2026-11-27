# Checklist de rendimiento y complejidad

Esta checklist verifica que las correcciones de los algoritmos más costosos
estén implementadas **y** comprobadas. Se usa en la rama
`perf/optimizaciones-complejidad`.

Convenciones: `n` es el tamaño de la entrada; `V` nodos del grafo de
dependencias; `D` raíces declaradas; `P` proveedores de servicio; `F` ficheros;
`L` bytes de texto; `Z` entradas de un ZIP. El estado de implementación se
marca con `[x]`; el de verificación solo se marca cuando el comando indicado se
ha ejecutado sin errores en esta rama.

## 1. Algoritmos más costosos (prioridad alta)

- [x] **A. Sustitución de propiedades de POM** — `internal/pom/resolve.go`
  - Antes: `O(10·p·L)` por campo y `O(V·d·p·L)` en el grafo.
  - Después: `O(p·|clave| + 10·L)` por campo, con un `strings.Replacer`
    construido una sola vez por POM y reutilizado en las cuatro llamadas de
    `ResolveDependencies`.
  - Comprobar: `go test ./internal/pom -run 'TestResolveProperties|TestResolveDependencies'`.

- [x] **B. Cierre transitivo multi-raíz** — `internal/cli/build.go`,
  `internal/pom/transitive.go`
  - Antes: `O(D·V)`; cada raíz repetía el DFS y volvía a descargar/parsear el
    subgrafo compartido.
  - Después: `O(V + D)` con `ResolveTransitiveMany`, `visited`, `resolved`,
    `skipped` y cadenas de parents compartidas. `ResolveTransitive` queda como
    envoltorio público.
  - Comprobar: `go test ./internal/pom ./internal/cli` y
    `rg 'ResolveTransitiveMany' internal/cli/build.go internal/pom/transitive.go`.

- [x] **C. Chequeo incremental** — `internal/build/incremental.go`
  - Antes: `O(a·c + a·m·L)`; `containsPath` por fuente y una búsqueda por cada
    declaración en todo el texto.
  - Después: set de modificados + tokenización única (`mentionsAnyIdentifier`)
    ⇒ `O(a + ΣL)`. `hasClassFiles` usa `filepath.SkipAll`.
  - Comprobar: `go test ./internal/build -run 'TestChangedSources|TestMentions'` y
    `rg 'SkipAll|mentionsAnyIdentifier' internal/build/incremental.go`.

- [x] **D. Deduplicación de proveedores de servicio** — `internal/aar/aar.go`
  - Antes: `O(P²)`; `addServiceImpl` recorría la lista en cada inserción.
  - Después: `serviceIndex` con `map[serviceKey]bool` ⇒ `O(P)` amortizado,
    conservando el orden de inserción.
  - Comprobar: `go test ./internal/aar -run 'TestService|TestSet'` y
    `rg 'serviceIndex' internal/aar/aar.go`.

- [x] **E. Globs de `rain fmt`** — `internal/cli/fmt.go`
  - Antes: `O(T·N + K·(P+Q)·p·ℓ)`; `regexp.MatchString` compilaba la expresión
    por fichero y patrón, y `collectJavaFiles` se repetía por target.
  - Después: patrones precompilados (`O(P·p)`) y candidatos compartidos entre
    targets (`O(N)`).
  - Comprobar: `go test ./internal/cli -run 'Test.*Fmt|Test.*Glob'` y
    `rg 'MatchString|compileGlobs|collectJavaFiles' internal/cli/fmt.go`.

- [x] **F. Cadena de parents** — `internal/pom/parent.go`
  - Antes: `O(V·P·L)`; cada hijo volvía a parsear parents compartidos, y un
    ciclo `A → B → A` no terminaba.
  - Después: caché `parentCache` compartida (`O(P·L)` total) y detección de
    ciclos.
  - Comprobar: `go test ./internal/pom -run TestResolveParentChain` y
    `rg 'parentCache|ciclo de parents' internal/pom/parent.go`.

- [x] **G. Parsing de Gradle** — `internal/gradle/parse.go`
  - Antes: `O(L²)` con llaves o paréntesis sin cerrar; `matchBrace` se
    reescaneaba desde cada candidato.
  - Después: pila de delimitadores en una pasada ⇒ `O(L)` garantizado.
  - Comprobar: `go test ./internal/gradle` y
    `rg 'braceEnds|parenEnds' internal/gradle/parse.go`.

## 2. Costes cuadráticos y repetidos (prioridad media)

- [x] **H. `ParseModule`** — `internal/gradle/gradle.go`: una sola pasada de
  comentarios/bloques en vez de cuatro `findBlock` sobre el archivo completo;
  regex de `stringValue`/`intValue` cacheadas. Verificar con
  `go test ./internal/gradle`.
- [x] **I. Memoria del proceso** — `internal/memenv/memenv.go`: `Detect` con
  `sync.Once`, de hasta 7 lecturas de `/proc` a 1. Verificar con
  `go test ./internal/memenv`.
- [x] **J. ZIP de build-tools** — `internal/toolchain/toolchain.go`: una sola
  apertura/recorrido para las 5 herramientas (`O(5·Z)` → `O(Z)`) y sin leer el
  binario de `zipalign` que se descartaba. Verificar con
  `go test ./internal/toolchain`.
- [x] **K. Hash de archivos** — `internal/lint/lint.go`: `io.Copy` sobre
  `sha256` (RAM `O(1)` en vez de `O(B)`) y reutilización de la instantánea en
  modo `--check`. Verificar con `go test ./internal/lint`.
- [x] **L. Constant pool de Hilt** — `internal/hilt/classfile.go`: índice
  nombre→índice construido en una pasada; búsquedas `O(1)` amortizado en vez de
  dos recorridos por clase. Verificar con `go test ./internal/hilt`.
- [x] **M. `TransformDir`** — `internal/hilt/transform.go`: pool de
  `runtime.NumCPU()` workers; errores y avisos publicados en orden de `Walk`.
  Verificar con `go test ./internal/hilt`.
- [x] **N. Descriptores en `copyTree`** — `internal/build/gencache.go`:
  `Close()` dentro del callback de `Walk`; pico `O(N)` → `O(1)`. Verificar con
  `go test ./internal/build`.
- [x] **O. Árbol de fuentes** — `internal/build/pipeline.go`: `skipDirs` como
  mapa, `detectABI` con un solo `Walk`, y una sola pasada compartida entre
  `usesCompose` y `findKotlinSources`. Verificar con `go test ./internal/build`.
- [x] **P. Servicios paralelos de lint** — `internal/cli/lint.go`: analizadores
  de un target en paralelo con resultados escritos en `slots[i]` para conservar
  el orden. Verificar con `go test ./internal/cli`.
- [x] **Q. Pool de preparación de AAR** — `internal/cli/build.go`: `w` workers
  fijos en vez de una goroutine por coordenada (`O(C)` → `O(w)` en memoria).
  Verificar con `go test ./internal/cli`.

## 3. Correcciones pequeñas y de reproducibilidad

- [x] ABIs y repositorios con set (`internal/aar/aar.go`,
  `internal/mavenrepo/mavenrepo.go`) y `mavenrepo.For` calculado una sola vez
  en `download`.
- [x] `groupPathPart` calculado fuera del bucle de repositorios
  (`internal/pom/module.go`).
- [x] Clave de caché de resolución calculada una sola vez y
  `resolvedCacheVersion` subido a 4 (`internal/cli/resolvecache.go`).
- [x] Atributos de recursos escritos en orden determinista
  (`internal/aar/merge.go`).
- [x] `traducirPlantillaDeUso` con `strings.NewReplacer` (`internal/cli/root.go`)
  y `Select` con mapa (`internal/lint/lint.go`).
- [x] `fastCheck` comprueba todas las fuentes si falta la marca; guardia para
  nombre vacío en `mentionsIdentifier`; `atoi` satura en vez de desbordar.
- [x] Resolución `local:` (`internal/cli/build.go`, `internal/aar/aar.go`): las
  coordenadas locales se copian desde `native/` sin resolver POMs ni consultar
  repositorios. Antes el proyecto tenía que copiar el AAR a `.rain-deps/aars`
  desde un `build.sh`; ahora el coste es `O(1)` por artefacto y la caché de
  dependencias se desactiva para no reutilizar un AAR local cambiado.
- [x] `.so` del módulo (`internal/build/deps.go`, `pipeline.go`): un `Walk` de
  `jniLibs/<abi>/` indexa las librerías en un mapa `O(n)` y las añade al hash
  incremental. Las locales ganan a las del AAR en caso de colisión.
- [x] Variantes de producto (`[build] flavor`): las fuentes de
  `src/<flavor>/{java,kotlin}` se recogen en una pasada `O(n)` adicional y se
  incluyen en el hash del DEX; los recursos de la variante se compilan aparte
  y sobrescriben los del main. `rain gradle` elige el primer flavor declarado.
- [x] `CHANGELOG.md` documenta los cambios visibles.

## 4. Verificación (marcar solo tras ejecutar)

Entorno usado: Go 1.27.4 (estable), golangci-lint 2.15.0 y goimports 0.52.0.

- [x] `make fmt` (o `gofmt`/`goimports`) sin cambios pendientes.
- [x] `go vet ./...` sin errores.
- [x] `go test ./...` en verde (todos los paquetes).
- [x] `make lint` (golangci-lint v2.15.0) en verde: 0 issues.
- [x] `make build` genera `./rain` con versión, commit y fecha.
- [x] `./rain doctor --fix --profile complex` prepara `~/.rain`: toolchain
  completo (R8 9.5.28, build-tools 36, Android 36, Kotlin 2.4.21, Compose).
- [x] `./rain --devlog build examples/compose` genera el APK del ejemplo con
  más de mil iconos: `FireIconCatalog-1.0-universal.apk` (14,7 MiB, 1431 s el
  primer build; un `kotlinc` huérfano del build interrumpido duplicaba CPU/RAM
  y se eliminó).
- [x] `java -jar ~/.rain/toolchain/apksigner.jar verify --verbose <apk>` valida
  el APK resultante: v1, v2 y v3 correctos, 1 firmante.
- [x] Segundo build sin cambios: 7 s, imprime `java sin cambios, reutilizando
  classes.dex` y no invoca a `kotlinc`. Confirma que el coste repetido es
  `O(fuentes)` de hash, no `O(fuentes)` de compilación.
- [x] Build limpio de `TelegramBotX` (propiedad del usuario) con
  `/content/rain/rain --devlog build .` tras borrar `.rain-deps`, `.rain-deps.json`
  y `app/build`: sin ejecutar su `build.sh`, 25 s, ``empaquetando 5 bibliotecas
  nativas`` y `TelegramBotX-1.0-universal.apk` (581,6 KiB).
- [x] `apksigner verify --verbose` sobre ese APK: v1, v2 y v3 correctos, 1
  firmante; `unzip -l` confirma `lib/arm64-v8a/` con `libproot.so`,
  `libproot-loader.so`, `libtalloc.so`, `libandroid-shmem.so` y
  `libbuddypty.so`.
- [x] Build limpio de `buddy-android` en clon sin cambios manuales:
  `rain gradle .` detecta `Flavors:[github fdroid]`, escribe
  `java_target = 8` y `flavor = 'github'`; `rain build .` compila en 61 s sin
  copiar `Flavor.java` a `src/main`. `apksigner verify --verbose`: `Verifies`
  con v3 y 1 firmante.
- [x] Cambiar `flavor` a `fdroid` en el `rain.toml` fuerza la recompilación
  Java (el hash incluye la variante) y genera un APK igualmente válido en 66 s.

## 5. Complejidades que NO requirieron cambio

- `O(1)`: búsquedas en mapas (`pom/transitive.go`, `aar/manifest.go`,
  `build/deps.go`), `os.Stat` de cachés, `detectarLayout`.
- `O(log₂ n)`: no aparece como algoritmo puro en el repo. Las ordenaciones son
  `O(n log n)` y son necesarias para la reproducibilidad del APK.
- `O(n)` óptimos: DFS con `visited` (`pom/transitive.go`),
  `dedupeJarsByClass` (`build/deps.go`), `MergeResources`/`scanManifest`,
  parsers de salida de lint y `CollectSources`.
- `O(nⁿ)` / exponencial: no existe. La resolución transitiva ya usa `visited`
  y las regex de Go son RE2 (sin backtracking exponencial). El único riesgo
  parecido era el ciclo de parents, corregido en el punto F.
