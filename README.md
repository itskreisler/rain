# Rain 🌧️

Un build system ligero para Android, escrito en Go, diseñado para correr en Termux sin la pesadez de Gradle.

## ¿Qué es esto?

Rain es una alternativa minimalista a Gradle para compilar APKs directamente en tu teléfono. Hace el pipeline esencial (`aapt2` → `javac`/`kotlinc` → `d8`/`R8` → `zipalign` → `apksigner`) pero sin JVM persistente, sin daemon y sin la resolución de dependencias que se come la RAM.

## Características

- **Ligero**: Binario de Go, arranque instantáneo.
- **Resolución de dependencias**: Lee POMs de Maven, maneja herencia de parents y `dependencyManagement` (modo completo).
- **Soporte mixto**: Compila Java y Kotlin en el mismo proyecto.
- **Incremental**: Hashea fuentes y recursos para no recompilar si nada cambió.
- **Release**: Genera DEX minificado con R8 y firma con tu propia keystore.
- **Multi-módulo**: Soporta varios módulos definidos en `rain.toml`.

## Requisitos

- Termux (probado en Android 12, arquitectura ARMv7).
- Paquetes de Termux: `openjdk-21`, `aapt2`, `d8`, `apksigner`, `zipalign`, `kotlin`, `libjansi`.
- Un `android.jar` del SDK (API 33 o superior recomendado). Puedes descargarlo de los repositorios de AOSP o usar el que tengas del SDK.

Instala las dependencias con:
```
pkg install openjdk-21 aapt2 d8 apksigner zipalign kotlin libjansi
```

Instalación

```
git clone https://github.com/TU_USUARIO/rain.git
cd rain
go build -o rain .
# Opcional: mover a $PREFIX/bin para uso global
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
key_password = "tu_key_password"

# Opcional: múltiples módulos
[[modules]]
name = "app"
path = "app"
```

3. Compila tu APK

Modo debug:

```
./rain build --android-jar ./ruta/a/android.jar
```

Modo release (con R8 y firma):

```
./rain build --android-jar ./ruta/a/android.jar -release
```

Rain mostrará una barra de progreso mientras descarga dependencias, y luego el progreso de cada etapa de compilación. Al final, el APK estará en app/build/app-debug.apk o app/build/app-release.apk.

4. Limpia el build (opcional)

```
./rain clean
```

Borra los directorios app/build, libs y cache, dejando el proyecto como nuevo.

Comandos disponibles

Comando Descripción
rain init Inicializa un rain.toml interactivamente.
rain build Compila el APK. Flags: --android-jar <ruta>, -release, --verbose, --quiet.
rain clean Borra artefactos de build, dependencias y caché.

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
