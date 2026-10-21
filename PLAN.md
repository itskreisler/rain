# Rain — Plan para superar a android2apk

> Objetivo: mantener el build aislado en Alpine (sin root, sin Gradle, sin Android Studio),
> añadir un CLI manejable con Cobra, un `rain doctor` que diagnostique el entorno y llevar
> `rain` al nivel de `android2apk` (y más allá): Java, Kotlin, Compose, AndroidX, multidex,
> assets, JNI y APKs de release verificados.

---

## 0. Diagnóstico

### 0.1 Qué es cada proyecto

| | `android2apk` | `rain` |
|---|---|---|
| Forma | Scripts Bash + docs + ejemplos + skill de agente | Binario Go + `rain.toml` |
| Entorno | Alpine (proot-distro/Termux), requiere root | Termux/Alpine, sin root |
| Java | JDK 17, `--release 8` (simple) / `11` (Compose) | JDK 21, `-source 8 -target 8` |
| Kotlin | 2.2.21, `-jvm-target 8/11` | cualquiera instalado, `-jvm-target 1.8` |
| minSdk / targetSdk | 26 / 36 fijo | 21 / sin targetSdk |
| AndroidX/Compose | Sí (93 AAR, recursos fusionados) | No (solo `classes.jar`) |
| Multidex | Sí (`classes*.dex`) | No (solo `classes.dex`) |
| Assets/JNI | Sí | No |
| Incremental | No (recompila todo) | Sí (SHA-256) |
| Release/R8 | No | Sí (R8 del `d8.jar` del SDK) |
| Tests/CI | No | No |
| Madurez | Snapshot cuidado, mucha doc | 3 commits, 2 días |

### 0.2 Respuesta a “¿manejan la misma versión de Java?”

**No.** Pero el punto importante es que **el JDK no se empaqueta en el APK**: `d8` convierte
bytecode a DEX. Lo que importa es:

| | `android2apk` | `rain` |
|---|---|---|
| JDK que ejecuta las herramientas | 17 | 21 (README) |
| Bytecode que se genera | Java 8 (simple), Java 11 (Compose) | Java 8 siempre |
| `minSdk` que se pasa a `d8` | 26 | 21 |
| R8/D8 | build-tools 36 → **R8 8.10.9-dev** | el que haya en el sistema |

Consecuencias:

- `rain` no puede usar sintaxis moderna de Java/Kotlin de forma efectiva: `-source 8`
  bloquea records, sealed, pattern matching, etc.
- `rain` fija `--min-api 21`, aunque el proyecto pida 26.
- El `d8` de build-tools 36 (R8 8.10.9) **no cumple** el mínimo para Kotlin 2.4 (R8 9.1.29),
  y está por debajo del mínimo de Kotlin 2.3 (R8 8.13.19). Para Kotlin 2.2 pide 8.10.21.
- `android2apk` acierta al separar JDK de target, pero se queda en Java 8/11.

**Decisión propuesta:** ejecutar con el JDK más moderno disponible (Alpine 3.24 tiene
**OpenJDK 25**) pero compilar con un target configurable, por defecto **17**:

```toml
[build]
javaTarget = 17   # 8 | 11 | 17 | 21
```

Y bajar un **R8 propio** (Google Maven, `com.android.tools:r8`) en vez de depender del
`d8.jar` del SDK. Así Kotlin 2.4 + Compose + Java 17 funcionan sin Gradle.

> Matiz: “Java moderno” = **nivel de lenguaje**, no APIs del JDK 25. Android no trae
> `java.time`, streams, etc. según API level. Sin desugaring de librería, hay que limitar
> las APIs o subir `minSdk`. `rain` debería documentarlo y avisar en `doctor`.

### 0.3 Qué impide hoy compilar los ejemplos de `android2apk`

| Ejemplo | Lenguaje | `res/` | AndroidX/Compose | Estado con `rain` hoy |
|---|---|---|---|---|
| `hello` | Java | No | No | Falla: layout distinto + pánico si no hay `res/` |
| `khello` | Kotlin | No | No | Falla: layout distinto |
| `pulse` | Java | Sí | No | Falla: layout distinto |
| `notes` | Java | Sí | No | Falla: layout distinto |
| `todo` | Java | No | No | Falla: layout + pánico sin `res/` |
| `compose` | Kotlin | Sí | Compose | Fase 5: falta todo el soporte AAR/Compose |
| `neko` | Kotlin | Sí | Compose | Fase 5 |
| `expressive` | — | — | — | No es una app (snippets) |

`android2apk` usa layout plano (`AndroidManifest.xml`, `java/`, `res/`, `assets/`,
`app.properties`); `rain` solo entiende `app/src/main/...`.

**Conclusión:** los 5 ejemplos simples son alcanzables en la Fase 3 con cambios moderados.
Los 2 de Compose requieren la Fase 4 (AAR completo) + Fase 5 (Compose).

### 0.4 Lo que ya es superior en `rain`

- Binario único, arranque instantáneo, sin daemon.
- Incremental real por hash.
- Resolución Maven con parents/`dependencyManagement`/transitivas.
- `rain.toml` declarativo y `clean`.
- R8 + firma configurables.
- Código pequeño y auditable.

Lo que hay que arreglar para que esa superioridad sea real: **corrección del pipeline**,
**paridad funcional** y **red de seguridad (tests/CI)**.

---

## 1. Decisiones de arquitectura

### 1.1 CLI con Cobra (solo Go, independiente de Java)

Hoy `main.go` hace `switch os.Args[1]` y crea un `flag.NewFlagSet` por comando. Eso no
escala: cada comando repite parseo, ayuda y validación, y no hay completions. Cobra
centraliza todo eso.

```
rain
├── init                    # crea rain.toml (interactivo o --non-interactive)
├── build [dir]             # compila; autodetecta layout
│   ├── --android-jar PATH
│   ├── --release
│   ├── --module NAME
│   ├── --dry-run
│   └── --offline
├── doctor                  # diagnóstico del entorno
│   ├── --profile simple|complex
│   ├── --json
│   ├── --print-install
│   └── --fix
├── deps tree               # árbol de dependencias resueltas
├── clean                   # respeta módulos
├── version                 # versión, commit, Go, plataforma
└── completion bash|zsh|fish
```

Esqueleto de migración:

```go
var (
    verbose bool
    quiet   bool
    noColor bool
)

var rootCmd = &cobra.Command{
    Use:           "rain",
    Short:         "Build system ligero para Android",
    SilenceUsage:  true, // no imprimir ayuda ante errores de ejecución
    SilenceErrors: true,
    PersistentPreRunE: func(cmd *cobra.Command, args []string) error {
        ui.Verbose = verbose
        ui.Quiet = quiet
        ui.NoColor = noColor
        return nil
    },
}

var buildCmd = &cobra.Command{
    Use:   "build [dir]",
    Short: "Compila un APK",
    Args:  cobra.MaximumNArgs(1),
    RunE: func(cmd *cobra.Command, args []string) error {
        dir := "."
        if len(args) == 1 {
            dir = args[0]
        }
        return runBuild(cmd.Context(), dir, buildOpts)
    },
}

func main() {
    if err := rootCmd.Execute(); err != nil {
        ui.Error("%v", err)
        os.Exit(1)
    }
}
```

Puntos clave:

- **Flags persistentes** (`--verbose`, `--quiet`, `--no-color`, `--json`) una sola vez en
  el root; no se repiten por comando.
- **`RunE` + `SilenceUsage`/`SilenceErrors`**: los errores de negocio salen limpios y con
  exit code, sin imprimir el usage entero.
- **Flags POSIX**: `--release`, `--android-jar`, `--profile`. Ojo: la CLI actual usa
  `-android-jar` (un guion). pflag espera `--android-jar`; hay que decidir si se acepta el
  cambio (pre-1.0) o se añade una normalización temporal de args para no romper scripts.
- **Completions** bash/zsh/fish y `RegisterFlagCompletionFunc` para `--profile`, `--module`.
- **Testeable**: `cmd.SetArgs([]string{...})`, `cmd.SetOut(buf)`, `cmd.SetErr(buf)`.
- **Exit codes**: `0` OK; `1` error de build; `2` uso incorrecto; `3` doctor incompleto.
  (Se implementa con un tipo de error que lleve el código.)
- Dependencias: `github.com/spf13/cobra v1.10.2` (+ `pflag`, que ya viene con Cobra).
  Requiere Go 1.21+; Alpine trae 1.26 y Termux, una versión reciente.
- No romper `rain build --android-jar ...`; mantener `init`, `build` y `clean` como
  comandos de primer nivel.

### 1.2 Toolchain propio en `~/.rain` (sin root)

En vez de escribir en `/usr/local/bin` o `/etc/apk/repositories` como `android2apk`:

```
~/.rain/
├── bin/            # symlinks o wrappers
├── toolchain/
│   ├── r8.jar
│   ├── apksigner.jar
│   ├── android.jar
│   ├── kotlinc/
│   └── aapt2
└── cache/          # AAR/POM + checksums
```

- `rain doctor` detecta; `rain doctor --fix` descarga **solo lo que falta** en `~/.rain`.
- Si ya hay herramientas del sistema válidas, se usan (configurable: `system-first` vs `managed-first`).
- Nada de root, nada de tocar `/etc`, nada de contaminar el contenedor Alpine.
- Descargas atómicas + SHA-256 + `.part` + reintentos + timeout.

### 1.3 `rain doctor` por perfiles

- `rain doctor` → perfil **simple**: ¿puedo compilar un APK Java sin AndroidX?
- `rain doctor --profile complex` → Kotlin + Compose + AndroidX + R8 moderno.
- `rain doctor --json` → para agentes/CI.
- `rain doctor --print-install` → solo comandos copiables.
- `rain doctor --fix` → instala lo gestionable sin root; para paquetes del sistema imprime el comando (`apk add ...` / `pkg install ...`).

Chequeos:

| Check | Simple | Complex |
|---|---|---|
| `java`/`javac`/`keytool` ≥ 17 | ✅ | ✅ |
| `aapt2` | ✅ | ✅ |
| `zipalign` | ✅ | ✅ |
| `apksigner` | ✅ | ✅ |
| `d8` o `r8.jar` | ✅ | ✅ |
| `android.jar` (API 36) | ✅ | ✅ |
| `zip` | ✅ | ✅ |
| `kotlinc` | — | ✅ |
| Compose compiler plugin | — | ✅ |
| `libjansi` (solo Termux) | — | ✅ |
| Espacio en disco | ✅ | ✅ |
| Matriz JDK/Kotlin/R8/SDK | ✅ | ✅ |

Salida humana (ejemplo):

```
Rain doctor · perfil simple · Alpine 3.24
✔ java 25.0.4        ✔ javac 25.0.4      ✔ keytool
✔ aapt2 16.0         ✔ zipalign          ✔ zip
✔ r8 9.5.28          ✔ apksigner         ✔ android.jar API 36
Resultado: LISTO para APK simple (Java).

Para apps complejas faltan:
  ✘ kotlinc 2.4.21
  ✘ Compose (plugin incluido en Kotlin)
Comandos recomendados:
  apk add openjdk25-jdk zip
  apk add android-build-tools@edge/testing
  rain doctor --fix          # baja kotlinc/r8/android.jar a ~/.rain
```

### 1.4 Compatibilidad de layouts

`rain build [dir]` autodetecta:

1. **Gradle-like:** `<dir>/app/src/main/{AndroidManifest.xml,java,kotlin,res,assets}`.
2. **Plano (android2apk):** `<dir>/{AndroidManifest.xml,java,res,assets,app.properties}`.
3. **Overrides en `rain.toml`:** `manifest`, `sourceDirs`, `resDir`, `assetsDir`.

Con esto, `rain build /root/projects/android2apk/examples/hello` funciona sin copiar nada.

### 1.5 AAR completo (la diferencia clave con `android2apk`)

Para superar a `android2apk`, `rain` debe procesar el AAR entero:

- `classes.jar`
- `res/` (fusionar + `aapt2 compile` + `--extra-packages`)
- `assets/`
- `jni/<abi>/*.so`
- `libs/*.jar`
- `AndroidManifest.xml` (fusión parcial: permisos, `uses-sdk`, `application`, componentes)
- `R.txt` (para generar `R` de cada librería)
- `consumer-rules.pro` / `proguard.txt`

Y empaquetar **todos** los `classes*.dex`, no solo `classes.dex`.

---

## 2. Roadmap por fases

> Prioridad: P0 (bloqueante), P1 (alto), P2 (medio).
> Cada tarea debe terminar con test o verificación reproducible.

### Fase 0 — Red de seguridad y deuda técnica (P0)

- [x] **T0.1** Pasar `gofmt`/`go vet`; arreglar imports de `main.go` y estilo.
- [ ] **T0.2** Tests unitarios: `pom` (parser, parents, versiones, transitivas), `config`, hashes, `needFetch`.
- [x] **T0.3** CI en GitHub Actions: `go test ./...`, `go vet`, `golangci-lint`, build para linux/amd64 y linux/arm64.
  > Incluye `.golangci.yml` (v2) y `Makefile` con `fmt`, `lint`, `test`, `build`, `tools`.
  > Equivalente a Ruff para Go: **`golangci-lint`** como meta-linter + `gofmt`/`goimports` como formatters.
- [x] **T0.4** Eliminar pánicos y errores ignorados: `res/` ausente, `filepath.Walk`, `MkdirAll`, `WriteFile`, `keytool`, `RemoveAll`.
- [ ] **T0.5** Cambiar `module rain` → `github.com/lucassogarayDA/rain`; añadir `--version` con `ldflags`.
- [x] **T0.6** Borrar código muerto (`DownloadAAR`, `EffectiveProperties`, `VerboseLog`) y el `DEBUG` de `main.go`; unificar repositorios Maven.

**Criterio de salida:** `go test ./...` verde en CI y ningún pánico con proyectos sin `res/`.

### Fase 1 — CLI Cobra + `rain doctor` simple (P0)

- [x] **T1.1** Integrar Cobra v1.10.2; root command + flags persistentes + exit codes.
- [x] **T1.2** Migrar `init`, `build`, `clean` a Cobra sin romper flags actuales.
- [x] **T1.3** `rain doctor` (perfil simple): detectar SO (Alpine/Termux/otro), comandos, versiones, `android.jar`, espacio.
- [x] **T1.4** Motor de recomendaciones: comandos `apk`/`pkg` por perfil; `--print-install`; `--fix` sin root para `~/.rain`.
- [x] **T1.5** Salida `--json` estable (schema versionado) + `rain completion` + tests de CLI con `cobra.Command.SetArgs`.

**Criterio de salida:** en un Alpine limpio, `rain doctor` dice correctamente qué falta para un APK simple y imprime los comandos exactos.

### Fase 2 — Toolchain autoprovisionado + doctor complejo (P1)

- [x] **T2.1** Estructura `~/.rain` + descargas atómicas + checksums + reintentos/timeout.
- [x] **T2.2** Resolver de toolchain: `system-first`/`managed-first`, overrides por env, `rain doctor --fix`.
- [x] **T2.3** Matriz de compatibilidad JDK/R8/Kotlin/SDK en código; `doctor` avisa (p. ej. Kotlin 2.4 + R8 8.10.9 = incompatible).
  > `internal/compat`: `CheckMatrix(Versions)` con severidades `advice`/`warning`/`error`; `doctor --json` incluye `compat` y la salida humana muestra la sección "Compatibilidad del toolchain".
- [x] **T2.4** Perfil complejo: `kotlinc`, plugin Compose, caché AAR, `libjansi` solo Termux, disco.
- [x] **T2.5** Tests con `httptest` y FS temporal (sin red real). Cubre `httpx` (reintentos/backoff), `aar` (extracción, `ExtraPackages`, `NativeDir`, fusión de recursos) y `pom`/`build` (dedupe de clases).

**Criterio de salida:** `rain doctor --profile complex --fix` deja un Alpine limpio listo para Kotlin/Compose sin root.

### Fase 3 — Paridad con los ejemplos simples de android2apk (P0)

- [x] **T3.1** Autodetección de layout (Gradle-like / plano) + overrides en `rain.toml`. `sourceRoot()` detecta `app/src/main` (Gradle) o la raíz (plano android2apk). Pendiente: overrides explícitos en `rain.toml`.
- [x] **T3.2** Config completa: `minSdk`, `targetSdk`, `versionCode`, `versionName`, `javaTarget`, `applicationId`/`namespace`; leer `app.properties` como fallback. `config.Build` expone todas las claves y `resolveModuleBuildConfig()` aplica la precedencia `rain.toml` > `app.properties` del módulo > defaults (min_sdk 21, target_sdk 36, version 1.0). Pendiente: `namespace` separado del `package`.
- [x] **T3.3** `BuildConfig` real: `VERSION_CODE`, `VERSION_NAME`, `DEBUG`, `APPLICATION_ID`, `BUILD_TYPE`. Se genera `BuildConfig.java` en `gen/<package>/` y se compila antes que Kotlin.
- [x] **T3.4** Pasar `minSdk`/`targetSdk` a `aapt2` y `d8` (quitar 21/36 hardcodeados). Ya no se hardcodea: llegan desde `resolveModuleBuildConfig()` al `build.Config`.
- [x] **T3.5** Soportar proyectos sin `res/` (arreglar el pánico) y sin `assets/`. Los `Walk`/lecturas ignoran `os.IsNotExist` y `aapt2 compile` solo corre `if dirExists(resDir)`.
- [x] **T3.6** Empaquetar `assets/`; Kotlin ↔ Java en el mismo módulo (orden/classpath correcto); `kotlin-stdlib` automática. `aapt2 link -A assets` y `findKotlinStdlib()` añaden el stdlib cuando hay fuentes Kotlin.
- [x] **T3.7** Compilar y verificar `hello`, `khello`, `pulse`, `notes`, `todo` **sin modificar los ejemplos**. Verificado: los 5 generan APK firmado (`apksigner verify` OK) con package y `launchable-activity` correctos, incluidos los recursos (`res/layout`, `res/drawable`, `resources.arsc`) de `pulse`/`notes`. Bug corregido: un proyecto Kotlin puro (khello) recibía el plugin Compose y fallaba con `noRuntimeOnClasspathError`; ahora el plugin se aplica solo si hay imports de `androidx.compose`.
- [x] **T3.8** `rain clean` respeta módulos; validar `manifest.package` vs config. `clean` borra `build/` de cada `[[modules]]`; el pipeline usa el `package` de `rain.toml` y cae a `manifest.package`. Pendiente: aviso si difieren.

**Criterio de salida:** `rain build <cada ejemplo simple>` genera un APK firmado, `apksigner verify` OK y `aapt2 dump badging` muestra el paquete/versión correctos.

### Fase 4 — AAR completo, multidex y release (P0/P1)

- [x] **T4.1** Extraer del AAR: `classes.jar`, `res/`, `jni/`, manifest y package de librería (para `--extra-packages`). Pendiente: `assets/`, `libs/*.jar`, `R.txt` explícito y reglas ProGuard.
- [x] **T4.2** Fusionar recursos de librerías (`--extra-packages`, duplicados/overlays tipo AGP). Pendiente: `R.txt` → `R.java`.
- [ ] **T4.3** Fusión de manifests (subconjunto): permisos, `uses-sdk`, `application`, `activity/service/receiver/provider`; documentar lo no soportado.
- [x] **T4.4** Multidex: se empaquetan `classes*.dex` en orden (verificado en Compose: `classes.dex` + `classes2.dex`).
- [x] **T4.5** JNI: se empaquetan `lib/<abi>/*.so` de los AAR (verificado en Compose: 4 ABIs). Pendiente: `zipalign -P 16`.
- [ ] **T4.6** Dependencias correctas: exclusiones, BOM/import, classifiers, properties built-in, parents locales, checksums, caché atómica. (Ya: timeouts/reintentos, moduleKey/highest-wins, dedupe de clases, omisión resiliente.)
- [x] **T4.7** Release: R8 con reglas del usuario + `consumer-rules.pro`, mapping, **nunca** caer a debug en silencio, `apksigner verify`, contraseñas por env/archivo. Implementado: reglas base en `build/r8-rain.pro` (nunca pisan las del usuario) + `proguard-rules.pro`/`proguard.pro` del proyecto + `proguard.txt` de cada AAR; `--pg-map-output` → `build/mapping.txt`; aviso explícito (o `--allow-debug-signing`) si falta keystore de release; `apksigner verify` tras firmar; `-dontwarn` para paquetes AndroidX opcionales/ausentes (emoji2, window, profileinstaller, tracing, startup) que R8 valida y D8 no.
- [ ] **T4.8** Test de integración con una app AndroidX/AppCompat real y verificación de recursos en runtime.

**Criterio de salida:** una app con AndroidX compila, arranca y no lanza `Resources$NotFoundException`.

### Fase 5 — Compose / Material 3 (P1)

- [x] **T5.1** Plugin Compose de Kotlin 2.4 + `-jvm-target` compatible (11 en Compose).
- [x] **T5.2** Cierre de dependencias Compose/Material3 (resolver transitivo estable). Pendiente: `rain.lock`.
- [ ] **T5.3** Compilar `examples/compose` y verificar arranque.
- [ ] **T5.4** Compilar `examples/neko` y verificar arranque + persistencia.
- [ ] **T5.5** Test de CI que compile Compose (sin emulador) y, opcional, smoke test en emulador.

**Estado verificado:** un proyecto Kotlin + Compose (AndroidX) generado con `rain init --template completo` compila a un APK firmado (multidex + `lib/<abi>/*.so` + recursos fusionados) sin Gradle.

**Criterio de salida:** `rain build examples/compose` y `examples/neko` producen APKs que abren sin crash.

### Fase 6 — Modernización y reproducibilidad (P2)

- [ ] **T6.1** JDK 25 soportado; default `--release 17`; `javaTarget` configurable (8/11/17/21).
- [x] **T6.2** Kotlin 2.4.21 + R8 9.5.x desde Google Maven (desacoplar del build-tools). Fijado en `internal/toolchain/toolchain.go` (`KotlinVersion = "2.4.21"`, `R8Version = "9.5.28"`).
- [ ] **T6.3** `rain.lock` con SHA-256 de dependencias y toolchain; `--offline`.
- [ ] **T6.4** APK reproducible: timestamps fijos, ZIP ordenado, R8 determinista, firma determinista.
- [ ] **T6.5** Paralelizar descargas y compilación por módulo; caché por archivo.
- [ ] **T6.6** Matriz CI Alpine 3.24 / Termux (o contenedor equivalente) / amd64 + arm64.

**Criterio de salida:** dos builds del mismo commit producen el mismo APK (o diferencias justificadas y documentadas).

### Fase 7 — Producto y documentación (P2)

- [ ] **T7.1** README nuevo + `docs/doctor.md`, `docs/toolchain.md`, `docs/compatibilidad.md`, guía de migración desde android2apk.
- [ ] **T7.2** `CHANGELOG`, versionado semver, releases con binarios linux/amd64 y linux/arm64.
- [x] **T7.3** `rain init` con scaffolding real (manifest, `MainActivity`, `res/`, plantillas Java/Kotlin/Compose).
- [ ] **T7.4** `rain deps tree`, `rain doctor --json`, salida amigable para agentes.
- [ ] **T7.5** `SECURITY.md`, NOTICE de terceros, cabeceras de licencia.

---

## 3. Matriz de pruebas objetivo

| Escenario | Java | Kotlin | Compose | AAR | Assets/JNI |
|---|---|---|---|---|---|
| `hello` | ✅ | — | — | — | — |
| `khello` | — | ✅ | — | — | — |
| `pulse` | ✅ | — | — | — | — |
| `notes` | ✅ | — | — | — | — |
| `todo` | ✅ | — | — | — | — |
| app AndroidX mínima | ✅ | — | — | ✅ | — |
| `compose` | — | ✅ | ✅ | ✅ | — |
| `neko` | — | ✅ | ✅ | ✅ | — |
| app con `.so` | ✅ | — | — | ✅ | ✅ |

Verificación por APK:

1. `apksigner verify --verbose`.
2. `aapt2 dump badging` (paquete, versión, minSdk, targetSdk).
3. `unzip -l` (dex, resources, assets, `.so`).
4. Instalación/arranque en emulador o dispositivo (smoke test manual o CI con emulador).

---

## 4. Riesgos y mitigaciones

| Riesgo | Impacto | Mitigación |
|---|---|---|
| Fusión de manifests de AAR | Alto | Empezar por subconjunto; documentar; tests con AndroidX |
| Fusión de recursos duplicados | Alto | Reutilizar estrategia de `android2apk/setup-compose.sh`; tests |
| Matriz Kotlin/R8/SDK | Alto | Tabla en código + `doctor`; descargar R8 propio |
| `--release 17` vs APIs Android | Medio | `javaTarget` configurable; avisar de APIs no disponibles |
| Termux vs Alpine | Medio | Detección de plataforma; tests en ambos |
| Descargas sin checksum | Medio | `rain.lock` + SHA-256 + `.part` atómico |
| Complejidad de Compose | Alto | Fase 5 separada; no bloquear paridad simple |
| Pérdida de datos en ejemplos (JSON) | Bajo | No es bug de rain; documentar |

---

## 5. Orden recomendado de ejecución

1. **Fase 0** (1–2 días): tests + CI + arreglos de robustez.
2. **Fase 1** (2–3 días): Cobra + `doctor` simple.
3. **Fase 3** (3–5 días): layout + config + ejemplos simples. *Aquí ya se puede demostrar paridad.*
4. **Fase 2** (2–3 días): toolchain `~/.rain` + `doctor` complejo.
5. **Fase 4** (1–2 semanas): AAR completo, multidex, release.
6. **Fase 5** (1 semana): Compose.
7. **Fase 6–7**: modernización, reproducibilidad, docs y releases.

**Hito demostrable temprano:** `rain doctor` + `rain build examples/hello` (y los otros 4 simples)
en un Alpine limpio, sin root, con un solo binario. Eso ya es superior a `android2apk` en
experiencia; la superioridad funcional total llega con la Fase 4/5.

---

## 6. Notas de licencia

`android2apk` es Apache-2.0 y tiene `NOTICE` por código de `buddy-android`. Si se porta
lógica de `setup-compose.sh` o `build-apk.sh` a Go, conservar atribución y añadir un
`NOTICE` actualizado en `rain`.
