# Ejemplo: Neko Notes

Una app de notas completa en Compose: navegación, calendario, editor, base de
datos con `rememberSaveable`, animaciones, iconos extendidos de Material y
tema claro/oscuro. Son 20 ficheros Kotlin y unos 1.500 lines: sirve para
comprobar que Rain aguanta un proyecto de Compose de tamaño real.

## Compilar

```bash
cd examples/neko
rain build
```

El APK sale en `examples/neko/app/build/app-debug.apk`.

## Qué ejercita

- `androidx.compose.foundation.lazy` (listas y rejillas diferidas),
- `androidx.compose.animation` (transiciones y animaciones compartidas),
- `androidx.compose.material3` (diálogos, menús, chips, snackbars, top bar),
- `androidx.compose.material:material-icons-extended` (los iconos de Material),
- `rememberSaveable` (el estado sobrevive a la rotación),
- iconos adaptativos (`res/mipmap-anydpi-v26`).

## Notas

- El tema se define en `app/java/com/klei/neko/ui/theme/`.
- `min_sdk = 26` en `rain.toml`: por debajo, `material-icons-extended` no
  compila.

## Procedencia

Portado de [android2apk](https://github.com/lucassogarayDA/android2apk)
(Apache License 2.0). Ver [NOTICE](../../NOTICE).
