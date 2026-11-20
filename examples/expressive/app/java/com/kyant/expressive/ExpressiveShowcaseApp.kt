@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.kyant.expressive

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.toPath
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.MotionDurationScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.Morph

/**
 * Ejemplo único que cubre las cinco patas de Material 3 Expressive:
 *
 * 1. Atención, personalidad y hero moments sin perder accesibilidad.
 * 2. MotionScheme con spatial springs y effects springs.
 * 3. Estilos enfatizados, peso variable de fuente y jerarquía editorial.
 * 4. Las 35 MaterialShapes y morphing real cuadrado -> círculo.
 * 5. Componentes nuevos/actualizados: ButtonGroup, SplitButton, FloatingToolbar,
 *    LoadingIndicator, WavyProgressIndicator, Slider y botones.
 *
 * Todo vive en `androidx.compose.material3`; no hay dependencia adicional.
 */
@Composable
fun ExpressiveShowcaseApp() {
    MaterialExpressiveTheme(
        motionScheme = MotionScheme.expressive(),
        // Marca en toda la escala tipográfica: una sola fuente para baseline y emphasized.
        typography = Typography(fontFamily = FontFamily.SansSerif),
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
            ExpressiveShowcase()
        }
    }
}

@Composable
private fun ExpressiveShowcase() {
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        HeroMoment()
        SectionTitle("Física de movimiento", "Spatial springs y effects springs del MotionScheme")
        MotionPhysicsDemo()
        SectionTitle("Tipografía", "Estilos enfatizados y peso variable")
        TypographyDemo()
        SectionTitle("35 formas", "Biblioteca MaterialShapes + morph integrado")
        ShapesDemo()
        SectionTitle("Componentes", "ButtonGroup, SplitButton, Toolbar, Loading y Wavy")
        ComponentsDemo()
        SectionTitle("Accesibilidad", "Objetos táctiles grandes, semantics y movimiento reducido")
        AccessibilityDemo()
    }
}

@Composable
private fun SectionTitle(title: String, subtitle: String) {
    Column(
        modifier = Modifier.semantics { heading() },
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(text = title, style = MaterialTheme.typography.titleLargeEmphasized)
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

/** 1. Hero moment: scala + color se animan con los sprigs del tema. */
@Composable
private fun HeroMoment() {
    var highlighted by remember { mutableStateOf(true) }
    val spatial = MaterialTheme.motionScheme.defaultSpatialSpec<Float>()
    val effects = MaterialTheme.motionScheme.defaultEffectsSpec<Color>()

    val scale by animateFloatAsState(
        targetValue = if (highlighted) 1.03f else 1f,
        animationSpec = spatial,
        label = "hero-scale",
    )
    val container by animateColorAsState(
        targetValue = if (highlighted) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceContainerHigh
        },
        animationSpec = effects,
        label = "hero-color",
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable { highlighted = !highlighted }
            .semantics { contentDescription = "Tarjeta destacada. Tócala para cambiar el énfasis." },
        shape = MaterialTheme.shapes.extraLargeIncreased,
        colors = CardDefaults.cardColors(containerColor = container),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = "EXPRESIVE",
                style = MaterialTheme.typography.labelLargeEmphasized,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "La atención\nes intencional",
                style = MaterialTheme.typography.displaySmallEmphasized.copy(
                    fontWeight = FontWeight(650),
                    fontSize = 40.sp,
                    letterSpacing = 1.sp,
                ),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Text(
                text = "Forma, color, tipografía y movimiento trabajan juntos.",
                style = MaterialTheme.typography.bodyLargeEmphasized,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}

/** 2. Un slider y su barra muestran los dos tipos de spec del MotionScheme. */
@Composable
private fun MotionPhysicsDemo() {
    var target by remember { mutableFloatStateOf(0.2f) }
    val value by animateFloatAsState(
        targetValue = target,
        animationSpec = MaterialTheme.motionScheme.defaultSpatialSpec(),
        label = "spatial-progress",
    )
    val trackColor by animateColorAsState(
        targetValue = if (target > 0.6f) {
            MaterialTheme.colorScheme.tertiary
        } else {
            MaterialTheme.colorScheme.primary
        },
        animationSpec = MaterialTheme.motionScheme.defaultEffectsSpec(),
        label = "effects-color",
    )

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Slider(
            value = target,
            onValueChange = { target = it },
            modifier = Modifier.fillMaxWidth(),
        )
        LinearProgressIndicator(
            progress = { value },
            modifier = Modifier.fillMaxWidth().height(10.dp),
            color = trackColor,
        )
        Button(
            onClick = { target = if (target > 0.6f) 0.2f else 1f },
            modifier = Modifier.heightIn(min = 56.dp),
        ) {
            Text("Alternar spatial + effects")
        }
    }
}

/** 3. Emphasized, peso variable 650 y escala editorial. */
@Composable
private fun TypographyDemo() {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = "Editorial",
            style = MaterialTheme.typography.headlineLargeEmphasized.copy(
                fontWeight = FontWeight(650),
                fontSize = 52.sp,
                letterSpacing = (-1).sp,
            ),
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = "El peso 650 usa el eje variable de la fuente; el estilo emphasized"
                + " reordena la jerarquía sin añadir otro recurso.",
            style = MaterialTheme.typography.bodyLargeEmphasized,
        )
        Text(
            text = "ETIQUETA EMPHASIZED",
            style = MaterialTheme.typography.labelSmallEmphasized,
            color = MaterialTheme.colorScheme.tertiary,
        )
    }
}

/** 4. Las 35 formas y un morph real cuadrado -> círculo con Morph.toPath. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ShapesDemo() {
    val allShapes = listOf(
        "Círculo" to androidx.compose.material3.MaterialShapes.Circle,
        "Cuadrado" to androidx.compose.material3.MaterialShapes.Square,
        "Slanted" to androidx.compose.material3.MaterialShapes.Slanted,
        "Arch" to androidx.compose.material3.MaterialShapes.Arch,
        "Fan" to androidx.compose.material3.MaterialShapes.Fan,
        "Flecha" to androidx.compose.material3.MaterialShapes.Arrow,
        "Semicírculo" to androidx.compose.material3.MaterialShapes.SemiCircle,
        "Óvalo" to androidx.compose.material3.MaterialShapes.Oval,
        "Píldora" to androidx.compose.material3.MaterialShapes.Pill,
        "Triángulo" to androidx.compose.material3.MaterialShapes.Triangle,
        "Diamante" to androidx.compose.material3.MaterialShapes.Diamond,
        "Concha" to androidx.compose.material3.MaterialShapes.ClamShell,
        "Pentágono" to androidx.compose.material3.MaterialShapes.Pentagon,
        "Gema" to androidx.compose.material3.MaterialShapes.Gem,
        "Sol" to androidx.compose.material3.MaterialShapes.Sunny,
        "Sol grande" to androidx.compose.material3.MaterialShapes.VerySunny,
        "Galleta 4" to androidx.compose.material3.MaterialShapes.Cookie4Sided,
        "Galleta 6" to androidx.compose.material3.MaterialShapes.Cookie6Sided,
        "Galleta 7" to androidx.compose.material3.MaterialShapes.Cookie7Sided,
        "Galleta 9" to androidx.compose.material3.MaterialShapes.Cookie9Sided,
        "Galleta 12" to androidx.compose.material3.MaterialShapes.Cookie12Sided,
        "Fantasma" to androidx.compose.material3.MaterialShapes.Ghostish,
        "Trébol 4" to androidx.compose.material3.MaterialShapes.Clover4Leaf,
        "Trébol 8" to androidx.compose.material3.MaterialShapes.Clover8Leaf,
        "Burst" to androidx.compose.material3.MaterialShapes.Burst,
        "SoftBurst" to androidx.compose.material3.MaterialShapes.SoftBurst,
        "Boom" to androidx.compose.material3.MaterialShapes.Boom,
        "SoftBoom" to androidx.compose.material3.MaterialShapes.SoftBoom,
        "Flor" to androidx.compose.material3.MaterialShapes.Flower,
        "Puffy" to androidx.compose.material3.MaterialShapes.Puffy,
        "PuffyDiamond" to androidx.compose.material3.MaterialShapes.PuffyDiamond,
        "Pixel círculo" to androidx.compose.material3.MaterialShapes.PixelCircle,
        "Pixel triángulo" to androidx.compose.material3.MaterialShapes.PixelTriangle,
        "Pan" to androidx.compose.material3.MaterialShapes.Bun,
        "Corazón" to androidx.compose.material3.MaterialShapes.Heart,
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        allShapes.forEach { (name, polygon) ->
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(polygon.toShape())
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .semantics { contentDescription = name },
            )
        }
    }

    var target by remember { mutableFloatStateOf(0f) }
    val reduceMotion = rememberCoroutineScope()
        .coroutineContext[MotionDurationScale]?.scaleFactor ?: 1f <= 0f
    val progress by animateFloatAsState(
        targetValue = target,
        animationSpec = MaterialTheme.motionScheme.defaultSpatialSpec(),
        label = "shape-morph",
    )
    val morph = remember { Morph(androidx.compose.material3.MaterialShapes.Square, androidx.compose.material3.MaterialShapes.Circle) }
    val shownProgress = if (reduceMotion) target else progress
    val morphColor = MaterialTheme.colorScheme.primary

    Column(
        modifier = Modifier.padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Canvas(
            modifier = Modifier
                .size(140.dp)
                .semantics { contentDescription = "Morph de cuadrado a círculo" },
        ) {
            val path = morph.toPath(shownProgress.coerceIn(0f, 1f))
            val factor = size.minDimension / 2.2f
            translate(size.width / 2f, size.height / 2f) {
                scale(factor, factor) {
                    drawPath(path, morphColor)
                }
            }
        }
        Button(
            onClick = { target = if (target < 0.5f) 1f else 0f },
            modifier = Modifier.heightIn(min = 56.dp),
        ) {
            Text(if (target < 0.5f) "Cuadrado → círculo" else "Círculo → cuadrado")
        }
    }
}

/** 5. Componentes nuevos y actualizados del catálogo Expressive. */
@Composable
private fun ComponentsDemo() {
    var emphasis by remember { mutableStateOf(true) }
    var wave by remember { mutableFloatStateOf(0.35f) }
    var toolbarExpanded by remember { mutableStateOf(true) }

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        ButtonGroup(
            modifier = Modifier.fillMaxWidth(),
            overflowIndicator = { Text("+") },
        ) {
            clickableItem(
                onClick = { emphasis = !emphasis },
                label = "Alternar",
            )
            toggleableItem(
                checked = emphasis,
                label = "Énfasis",
                onCheckedChange = { emphasis = it },
            )
            clickableItem(
                onClick = { wave = if (wave > 0.7f) 0.35f else 1f },
                label = "Ola",
            )
        }

        SplitButtonLayout(
            leadingButton = {
                Button(
                    onClick = { toolbarExpanded = !toolbarExpanded },
                    modifier = Modifier.height(56.dp),
                ) { Text("Guardar") }
            },
            trailingButton = {
                Button(
                    onClick = { emphasis = !emphasis },
                    modifier = Modifier.height(56.dp),
                ) { Text("⋯") }
            },
        )

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            HorizontalFloatingToolbar(
                expanded = toolbarExpanded,
                colors = FloatingToolbarDefaults.vibrantFloatingToolbarColors(),
                shape = MaterialTheme.shapes.extraLargeIncreased,
            ) {
                Text("Toolbar flotante")
            }
        }

        RowForIndicators(wave = wave)

        Button(
            onClick = { wave = if (wave > 0.7f) 0.35f else 1f },
            modifier = Modifier.heightIn(min = 56.dp),
        ) {
            Text(if (wave > 0.7f) "Reiniciar ola" else "Completar ola")
        }
    }
}

@Composable
private fun RowForIndicators(wave: Float) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LoadingIndicator(modifier = Modifier.size(52.dp))
        LinearWavyProgressIndicator(
            progress = { wave },
            modifier = Modifier
                .weight(1f)
                .height(14.dp),
        )
    }
}

/** 6. Accesibilidad explícita: targets amplios, semantics y respeto a reduced motion. */
@Composable
private fun AccessibilityDemo() {
    val scope = rememberCoroutineScope()
    val reduced = (scope.coroutineContext[MotionDurationScale]?.scaleFactor ?: 1f) <= 0f
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .semantics {
                contentDescription = if (reduced) {
                    "Movimiento reducido activado: las animaciones se saltan."
                } else {
                    "Movimiento completo. Los objetivos táctiles miden al menos 56 dpi."
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = if (reduced) "Movimiento reducido respetado" else "Objetivos táctiles de 56 dp",
            style = MaterialTheme.typography.labelLargeEmphasized,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
        )
    }
}
