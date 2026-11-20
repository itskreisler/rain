# Material 3 Expressive Showcase

Ejemplo único que demuestra **todas las patas** de Material 3 Expressive sobre
`androidx.compose.material3:material3-android:1.5.0-beta01`. No añade
dependencias nuevas: las APIs Expressive ya viven en ese artefacto.

## Qué cubre

| Punto Expressive | Dónde está |
|---|---|
| Atención, personalidad y *hero moments* | `HeroMoment()`: tarjeta grande con escala y color animados, tipografía `displaySmallEmphasized` y peso variable 650. |
| Accesibilidad | `Modifier.semantics`, `contentDescription`, `heading()`, objetivos táctiles de 56 dp y respeto a `MotionDurationScale`. |
| Motion physics | `MotionPhysicsDemo()`: `MotionScheme.expressive()` con `defaultSpatialSpec()` para la barra y `defaultEffectsSpec()` para el color. |
| Tipografía enfatizada | `TypographyDemo()`: estilos `*Emphasized`, `FontWeight(650)` (eje variable) y `Typography(fontFamily = ...)` aplicada a toda la escala. |
| 35 formas + morph | `ShapesDemo()`: lista completa de `MaterialShapes` y morph real `Morph(Square, Circle).toPath(progress)`. |
| Componentes nuevos/actualizados | `ComponentsDemo()`: `ButtonGroup`, `SplitButtonLayout`, `HorizontalFloatingToolbar`, `LoadingIndicator`, `LinearWavyProgressIndicator`, `Slider` y `Button`. |
| Color vibrante y nuevos roles | `expressiveLightColorScheme()` por defecto en `MaterialExpressiveTheme`, `vibrantFloatingToolbarColors()` y roles `surfaceContainerHigh`, `tertiaryContainer`, `primaryContainer`. |

> `FAB menu` no se muestra porque en `1.5.0-beta01` solo aparece como token
> interno; el resto de componentes de la lista están representados por su
> versión nueva o actualizada.

## Compilar

```bash
cd examples/expressive
../../rain check
../../rain build
../../rain build --release --allow-debug-signing
```
