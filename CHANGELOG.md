# Changelog

Todos los cambios notables de Rain se documentan aquí. El formato sigue
[Keep a Changelog](https://keepachangelog.com/es-ES/1.1.0/) y el proyecto usa
[Versionado Semántico](https://semver.org/lang/es/).

Las versiones se calculan automáticamente a partir de los mensajes de commit
(Conventional Commits): `feat:` sube la *minor*, `fix:`/`perf:`/`refactor:` la
*patch*, y `!`/`BREAKING CHANGE` la *major*.

## [No publicado]

### Añadido

- Repositorios Maven por coordenada: JitPack se consulta solo para grupos
  `com.github.*`/`io.github.*`, y `[repositories].extra` en `rain.toml` permite
  declarar repos de terceros sin afectar al resto de builds.

## [0.1.0] - 2026-10-21

Primera versión etiquetada. Núcleo funcional: compila, firma y verifica APKs
reales de Android desde Termux/Alpine, sin Gradle ni Android Studio.

### Añadido

- CLI basada en Cobra: `init`, `build`, `clean`, `doctor`, `version`.
- `rain doctor` con motor de recomendaciones y toolchain autoprovisionado en
  `~/.rain` (Kotlin 2.4.21, R8 9.5.28, android.jar, apksigner).
- Matriz de compatibilidad JDK/R8/Kotlin/SDK (`docs/compatibilidad.md`).
- Scaffolding real de proyectos: plantilla `simple` (Java) y `completo`
  (Kotlin + Compose con AndroidX).
- Resolución de dependencias leyendo POMs de Maven (transitivas, "highest wins",
  properties y `dependencyManagement`).
- Soporte AAR completo: `classes.jar`, `res/`, `jni/<abi>/*.so`, manifest,
  `R.txt` y reglas ProGuard; fusión de recursos tipo AGP.
- Multidex y build de release con R8 real (reglas base + de AAR, `mapping.txt`,
  `apksigner verify`).
- Modo release con firma PKCS12 y soporte de keystore; APK nombrado
  `<nombre>-<versionName>-<abi>.apk` al estilo android2apk.
- Configuración completa en `rain.toml` (`[build]` con `min_sdk`, `target_sdk`,
  `version_code`, `version_name`, `application_id`, `java_target`), con
  `app.properties` como fallback.
- `BuildConfig.java` real (`DEBUG`, `APPLICATION_ID`, `BUILD_TYPE`,
  `VERSION_CODE`, `VERSION_NAME`).
- Mensaje final del build con tamaño del APK y tiempo empleado.

### Cambiado

- Plugin de Compose aplicado solo si el proyecto importa `androidx.compose`.
- Orden de compilación: Java generado (R.java/BuildConfig.java) → Kotlin → Java
  de la app, con `objDir` en el classpath de `kotlinc`.
- Dedupe global de dependencias por `group:artifact` (una sola versión, la más
  alta) para evitar clases contradictorias en el classpath.
- Si un JAR es un stub de Kotlin Multiplatform (sin `.class`), se descarga la
  variante `-jvm`.

[No publicado]: https://github.com/itskreisler/rain/compare/v0.1.0...HEAD
[0.1.0]: https://github.com/itskreisler/rain/releases/tag/v0.1.0
