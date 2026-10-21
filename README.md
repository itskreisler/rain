# Rain 🌧️

Un build system ligero para Android, escrito en Go, diseñado para correr en Termux sin la pesadez de Gradle.

## ¿Qué es esto?

Rain es una alternativa minimalista a Gradle para compilar APKs directamente en tu teléfono. Hace el pipeline esencial (`aapt2` → `javac`/`kotlinc` → `d8`/`R8` → `zipalign` → `apksigner`) pero sin JVM persistente, sin daemon y sin la resolución de dependencias que se come la RAM.

## Características

- **Ligero**: Binario de Go, arranque instantáneo.
- **Resolución de dependencias**: Lee POMs de Maven, maneja herencia de parents y `dependencyManagement` (modo completo).
- **Soporte mixto**: Compila Java y Kotlin en el mismo proyecto.
- **Incremental**: Hashea fuentes y recursos para no recompilar si nada cambió.
- **Chequeo rápido**: `rain check` valida los tipos sin generar APK; `rain lint` añade ktlint y detekt.
- **Release**: Genera DEX minificado con R8 y firma con tu propia keystore.
- **Multi-módulo**: Soporta varios módulos definidos en `rain.toml`.

## Requisitos

- Termux o Linux con glibc/musl (probado también en Alpine dentro de un contenedor).
- JDK 17 o superior (`pkg install openjdk-17`). Rain acepta JDK ≥ 17; OpenJDK 21
  funciona en Termux pero un kernel PRoot puede romperlo, por eso el mínimo es 17.
- `aapt2` del sistema (`pkg install aapt2`).
- El resto de la toolchain (`d8`/R8, `apksigner`, `zipalign`, `android.jar`, Kotlin
  y el plugin de Compose) **la autoprovisiona Rain** en `~/.rain`, así que no hace
  falta instalar el SDK de Android a mano. Para prepararla:

```
rain doctor --fix
```

Si ya tienes un `android.jar` propio puedes pasarlo con `--android-jar <ruta>`.

Migrar desde android2apk

Los proyectos que hoy compilan con `android2apk` (layout plano, `app.properties`,
scripts de setup) compilan con Rain sin modificarlos:

```
rain build /ruta/a/mi-app
```

La guía completa —equivalencias entre `app.properties` y `rain.toml`,
dependencias de Compose, toolchain y firmado— está en
[docs/migracion-android2apk.md](docs/migracion-android2apk.md).

Documentación

| Documento | Contenido |
|---|---|
| [docs/doctor.md](docs/doctor.md) | Qué comprueba `rain doctor` y cómo usar sus flags. |
| [docs/toolchain.md](docs/toolchain.md) | Qué se instala en `~/.rain` y por qué. |
| [docs/compatibilidad.md](docs/compatibilidad.md) | Matriz JDK / Kotlin / R8 / compileSdk. |
| [docs/memoria.md](docs/memoria.md) | Memoria en contenedores: `GOMEMLIMIT`, `GOGC`, heap de la JVM. |
| [docs/migracion-android2apk.md](docs/migracion-android2apk.md) | Cómo portar un proyecto de android2apk. |

Instalación

```
git clone https://github.com/itskreisler/rain.git
cd rain
go build -o rain .
# Opcional: mover a $PREFIX/bin (o ~/.local/bin) para uso global
cp rain $PREFIX/bin/
```

Estructura de un proyecto

Rain espera una estructura de directorios similar a la de Gradle, pero simplificada:

```
mi-app/
├── rain.toml              # Configuración del build
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── AndroidManifest.xml
│   │       ├── java/      # Código fuente Java (.java)
│   │       ├── kotlin/    # Código fuente Kotlin (.kt) (opcional)
│   │       └── res/       # Recursos (layouts, strings, etc.)
│   └── build/             # Generado por Rain (APKs, DEX, etc.)
├── libs/                  # Dependencias AAR/JAR descargadas (generado)
└── cache/                 # Caché de POMs (generado)
```

Flujo de trabajo paso a paso

1. Inicializa el proyecto

```
./rain init
```

Rain te preguntará el modo de resolución de dependencias:

· simple: Declaras las dependencias explícitamente, sin transitivas. Ideal para proyectos pequeños o si quieres control total.
· completo: Rain resuelve transitivas leyendo los POMs de Maven (como Gradle). Necesario para usar AndroidX.

Esto generará un archivo rain.toml básico.

2. Configura rain.toml

Edita el archivo con tus datos. Un ejemplo completo:

```toml
mode = "completo"  # o "simple"
dependencies = [
    "androidx.core:core:1.12.0",
    "androidx.appcompat:appcompat:1.6.1"
]

[project]
name = "MiApp"
package = "com.ejemplo.miapp"
version = "1.0"

[build]
debug = true

# Opcional: firma de release
[signing]
store_file = "mi-release.keystore"
store_password = "tu_password"
key_alias = "mi_alias"
key_password = "tu_key_password"   # opcional: si se omite, se usa store_password

# Opcional: múltiples módulos
[[modules]]
name = "app"
path = "app"

# Opcional: repositorios Maven adicionales (Google Maven y Maven Central
# siempre se consultan, y JitPack se añade solo para dependencias
# com.github.* / io.github.*). Útil para un Nexus propio o repos de terceros.
[repositories]
extra = ["https://mi-nexus.local/repository/maven"]
```

3. Compila tu APK

Modo debug:

```
./rain build --android-jar ./ruta/a/android.jar
```

Modo release (con R8 y firma):

```
./rain build --android-jar ./ruta/a/android.jar --release
```

Rain mostrará una barra de progreso mientras descarga dependencias, y luego el progreso de cada etapa de compilación. Al final, el APK estará en `app/build/app-debug.apk` o `app/build/app-release.apk` (además de una copia descriptiva `<nombre>-<version>-<abi>.apk`). Si no defines `[signing]`, un build release se firma con una keystore de **debug** y Rain te lo avisa; confírmalo con `--allow-debug-signing` si es lo que quieres.

4. Limpia el build (opcional)

```
./rain clean
```

Borra los directorios app/build, libs y cache, dejando el proyecto como nuevo.

Comandos disponibles

| Comando | Descripción |
| --- | --- |
| `rain init` | Inicializa un proyecto (`--template simple` o `--template completo`). |
| `rain build [dir]` | Compila el APK. Flags: `--android-jar <ruta>`, `--release`, `--allow-debug-signing`, `--verbose`, `--quiet`. Con `[dir]` compila un proyecto o un layout plano estilo android2apk. |
| `rain check [dir]` | Comprueba los tipos **sin generar APK**: compila el código y se detiene. Detecta `unresolved reference 'x'`, imports rotos y tipos incompatibles mucho antes que un build completo. Flags: `--fast` (chequeo incremental), `--android-jar <ruta>`, `--release`. |
| `rain lint [dir]` | Ejecuta los analizadores estáticos opcionales (ktlint, detekt, pmd) y agrupa los avisos. Flags: `--install`, `--tools`, `--fail-on`, `--type-resolution`. |
| `rain doctor` | Diagnostica la toolchain (`--profile simple\|complex`, `--json`, `--fix`, `--lint`, `--print-install`). |
| `rain clean` | Borra artefactos de build, dependencias y caché. |

`rain check` y `rain lint` no son lo mismo, y no se solapan:

| | `rain check` | `rain lint` |
| --- | --- | --- |
| Qué hace | Compila el código (kotlinc/javac) y para ahí | Analiza el texto fuente con ktlint/detekt/pmd |
| Detecta | Errores de **tipos**: referencias sin resolver, imports rotos, firmas incompatibles | Estilo, complejidad y "code smells": formato, imports sin usar, magic numbers |
| Coste típico | ~3 s si no ha cambiado nada; ~30 s si hay que recompilar | ~20 s |

Un linter de estilo **no** pilla un `unresolved reference`: eso solo lo ve el
compilador. Por eso el check compila, y por eso el lint no puede sustituirlo.

Para instalar los analizadores que gestiona Rain (ktlint 1.8.0 y detekt 1.23.8,
en `~/.rain/toolchain`):

```
rain lint --install        # o: rain doctor --fix --lint
```

Se pueden configurar en `rain.toml`:

```toml
[lint]
tools = ["ktlint", "detekt"]   # o ["auto"] para usar los instalados
fail_on = "error"              # never | warning | error
type_resolution = false        # detekt con tipos: más avisos, más lento
```

¿Cómo funciona internamente?

Rain ejecuta el mismo pipeline que Gradle, pero sin capas de abstracción:

1. Resolución de dependencias (solo en modo completo): Lee los POMs de Google Maven y Maven Central, resuelve transitivas, y descarga los AAR/JAR a libs/.
2. aapt2 compile: Compila los recursos de res/ a archivos .flat.
3. aapt2 link: Genera el APK base y el R.java a partir del manifest y los recursos.
4. BuildConfig: Genera la clase BuildConfig.java con DEBUG, APPLICATION_ID, etc.
5. kotlinc (si hay archivos .kt): Compila Kotlin a .class en obj/.
6. javac: Compila Java (incluyendo R.java y BuildConfig.java) usando obj/ como classpath para ver las clases de Kotlin.
7. d8 / R8: Convierte los .class a classes.dex. En release, usa R8 con ProGuard para minificar.
8. zip: Empaqueta classes.dex dentro del APK base.
9. zipalign: Alinea el APK para que sea eficiente en memoria.
10. apksigner: Firma el APK con la keystore de debug o la de release.

La incrementalidad funciona con hashes SHA-256: si los fuentes y recursos no cambian, Rain reutiliza el DEX y el APK base de la compilación anterior.

Solución de problemas

· aapt2: command not found: Instala aapt2 con pkg install aapt2.
· javac falla con package X does not exist: Asegúrate de que las dependencias estén en libs/. Borra libs/ y vuelve a compilar para forzar la descarga.
· Error de jansi al compilar Kotlin: Instala libjansi con pkg install libjansi. Rain ya pasa el flag -J-Dlibrary.jansi.path=... automáticamente.
· La barra de progreso no aparece: Asegúrate de que libs/ no exista o esté vacío. Rain solo descarga si no encuentra los JARs.
· "fatal error: found pointer to free object" o la compilación muere: normalmente falta de RAM. La JVM reserva 1/4 de la RAM física por defecto; Rain acota el heap de kotlinc a la mitad de la memoria realmente disponible, pero en contenedores justos conviene exportar GOGC=15 y GOMEMLIMIT=1200MiB. Si el proceso corre bajo proot (PRoot-Distro, contenedores emulados), el crash no es de memoria: es proot estropeando el runtime de Go, y Rain ya lo detecta y aplica GC conservador por su cuenta. Ver [docs/memoria.md](docs/memoria.md).
