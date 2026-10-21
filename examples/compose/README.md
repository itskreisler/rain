# Ejemplo: Compose

Compila una app mínima con **Jetpack Compose** (Material 3 Expressive) usando
Rain. Es el ejemplo más pequeño que ejercita todo el camino interesante:

- resolución de dependencias desde los POMs de Google Maven (con transitivas),
- fusión de los recursos de las AAR (`res/` + `resources.arsc`),
- fusión de los manifests de las librerías (permisos y componentes),
- el plugin del compilador de Compose sobre Kotlin,
- multidex (`classes.dex` + `classes2.dex`).

## Compilar

```bash
cd examples/compose
rain build
```

El APK sale en `examples/compose/app/build/app-debug.apk`.

## Dependencias

En `rain.toml` solo se declaran las dependencias **directas**; Rain resuelve
las transitivas (`activity-compose` arrastra `activity`, `core`, `lifecycle`...).

```toml
mode = "completo"

dependencies = [
    "androidx.activity:activity-compose:1.13.0",
    "androidx.compose.material3:material3:1.5.0-beta01",
    ...
]
```

`mode = "completo"` es lo que activa la resolución transitiva y la fusión de
recursos. Con `mode = "simple"` solo se usarían los JAR sueltos de `libs/`.

## Notas

- `material3:1.5.0-beta01` es la versión que trae `MaterialExpressiveTheme` y
  `MotionScheme.expressive()`, que usa el ejemplo.
- Los artefactos de Compose son multiplataforma (KMP); Rain redirige a la
  variante `-android` automáticamente al resolver el `.module`.

## Procedencia

Portado de [android2apk](https://github.com/lucassogarayDA/android2apk)
(Apache License 2.0). Ver [NOTICE](../../NOTICE).
