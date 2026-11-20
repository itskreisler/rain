package com.kyant.backdrop.catalog

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.MotionDurationScale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Dos formas de hacer la transición play <-> pause sin el salto brusco del
 * catálogo actual. Ambas comparten [PlayPauseMorphState], por lo que el
 * estado activo y [PlayPauseMorphState.onAnimationEnd] se conservan igual.
 *
 * - [CrossfadePlayPauseIcon] (A): dibuja los dos iconos a la vez con alfa y
 *   escala contrarios. Mantiene los keyframes originales de cada icono.
 * - [MorphPlayPauseIcon] (B): un único conjunto de trazos redondeados que
 *   interpola sus puntas desde la forma de play hasta las dos barras de pause.
 *   No hay crossfade: el icono activo se transforma continuamente.
 *
 * La acción instantánea (pausar/reproducir audio) sigue siendo un callback
 * aparte: este archivo solo controla el efecto visual del cambio de icono.
 */

class PlayPauseMorphState internal constructor() {
    internal var toggleAction: (() -> Unit)? = null

    /** true = se muestra pause; false = se muestra play. */
    var isPlaying: Boolean by mutableStateOf(false)
        private set

    /** Se ejecuta al terminar la transición, con [isPlaying] ya actualizado. */
    var onAnimationEnd: (() -> Unit)? = null

    fun toggle() {
        toggleAction?.invoke()
    }

    internal fun finish(playing: Boolean) {
        isPlaying = playing
        onAnimationEnd?.invoke()
    }
}

@Composable
fun rememberPlayPauseMorphState(): PlayPauseMorphState = remember { PlayPauseMorphState() }

// ---------------------------------------------------------------------------
// Helper de animación compartido
// ---------------------------------------------------------------------------

private val MorphEase = CubicBezierEasing(0.42f, 0f, 0.58f, 1f)

private data class MorphKeyframe(val at: Float, val value: Float)

private fun morphSample(frames: List<MorphKeyframe>, phase: Float): Float {
    val p = phase.coerceIn(0f, 1f)
    if (p <= frames.first().at) return frames.first().value
    for (i in 1 until frames.size) {
        val from = frames[i - 1]
        val to = frames[i]
        if (p <= to.at) {
            val raw = (p - from.at) / (to.at - from.at)
            return from.value + (to.value - from.value) * MorphEase.transform(raw)
        }
    }
    return frames.last().value
}

private fun lerpPoint(a: Offset, b: Offset, k: Float): Offset =
    Offset(a.x + (b.x - a.x) * k, a.y + (b.y - a.y) * k)

/** x: [0, 3, -0.6, 0] con times [0, 0.4, 0.75, 1] del PlayIcon original. */
private val MorphPlayShift = listOf(
    MorphKeyframe(0f, 0f),
    MorphKeyframe(0.4f, 3f),
    MorphKeyframe(0.75f, -0.6f),
    MorphKeyframe(1f, 0f),
)

/** scale: [1, 1.1, 1, 1] del PlayIcon original. */
private val MorphPlayPulse = listOf(
    MorphKeyframe(0f, 1f),
    MorphKeyframe(0.4f, 1.1f),
    MorphKeyframe(0.75f, 1f),
    MorphKeyframe(1f, 1f),
)

/** scaleY: [1, 0.55, 1] del PauseIcon original. */
private val MorphPauseBar = listOf(
    MorphKeyframe(0f, 1f),
    MorphKeyframe(0.5f, 0.55f),
    MorphKeyframe(1f, 1f),
)

private val MorphPlayTriangle: Path =
    PathParser()
        .parsePathString(
            "M5 5a2 2 0 0 1 3.008-1.728l11.997 6.998a2 2 0 0 1 .003 3.458l-12 7A2 2 0 0 1 5 19z",
        ).toPath()

/**
 * Devuelve un lector del progreso 0..1 (0 = play, 1 = pause) y registra el
 * toggle en el State. `start`/`toggle` se usan como claves para que un cambio
 * de `duration` vuelva a registrar la acción (si no, se usa la duración vieja).
 */
@Composable
private fun rememberMorphProgress(duration: Float, state: PlayPauseMorphState): () -> Float {
    val progress = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val motionScale = scope.coroutineContext[MotionDurationScale]?.scaleFactor ?: 1f
    var animationJob by remember { mutableStateOf<Job?>(null) }

    val toggle = remember(state, motionScale, duration) {
        {
            val targetPlaying = !state.isPlaying
            val target = if (targetPlaying) 1f else 0f
            animationJob?.cancel()
            if (motionScale <= 0f) {
                animationJob =
                    scope.launch {
                        progress.snapTo(target)
                        state.finish(targetPlaying)
                    }
            } else {
                animationJob =
                    scope.launch {
                        progress.animateTo(
                            targetValue = target,
                            animationSpec =
                                tween(
                                    durationMillis = (700 * duration.coerceAtLeast(0.1f)).toInt(),
                                    easing = LinearEasing,
                                ),
                        )
                        // Solo se llega aquí si no se canceló: es el fin real.
                        state.finish(targetPlaying)
                    }
            }
        }
    }

    DisposableEffect(state, toggle) {
        state.toggleAction = toggle
        onDispose {
            state.toggleAction = null
            animationJob?.cancel()
            animationJob = null
        }
    }

    return { progress.value }
}

@Composable
private fun morphColor(color: Color): Color =
    if (color == Color.Unspecified) Color(0xFF2196F3) else color

// ---------------------------------------------------------------------------
// A) Crossfade de los dos iconos originales
// ---------------------------------------------------------------------------

@Composable
fun CrossfadePlayPauseIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    duration: Float = 1f,
    state: PlayPauseMorphState? = null,
) {
    val ownState = remember { PlayPauseMorphState() }
    val effective = state ?: ownState
    val readProgress = rememberMorphProgress(duration, effective)
    val resolved = morphColor(color)

    Box(
        modifier =
            modifier
                .size(size)
                .clickable { effective.toggle() },
        contentAlignment = Alignment.Center,
    ) {
        androidx.compose.foundation.Canvas(modifier = Modifier.size(size)) {
            val t = readProgress().coerceIn(0f, 1f)
            val playAlpha = 1f - t
            val pauseAlpha = t
            val sx = this.size.width / 24f
            val sy = this.size.height / 24f

            fun drawPlayShape() {
                val dx = morphSample(MorphPlayShift, t) * sx
                val pulse = morphSample(MorphPlayPulse, t)
                translate(dx, 0f) {
                    scale(pulse, pulse, pivot = Offset(12f, 12f)) {
                        drawPath(
                            path = MorphPlayTriangle,
                            color = resolved.copy(alpha = playAlpha),
                            style = Stroke(width = 2f, cap = StrokeCap.Round, join = StrokeJoin.Round),
                        )
                    }
                }
            }

            fun drawPauseShape() {
                val near = morphSample(MorphPauseBar, t)
                val farPhase = ((t - 0.15f) / 0.85f).coerceIn(0f, 1f)
                val far = morphSample(MorphPauseBar, farPhase)
                scale(1f, near, pivot = Offset(7.5f, 12f)) {
                    drawPauseBar(resolved.copy(alpha = pauseAlpha))
                }
                scale(1f, far, pivot = Offset(16.5f, 12f)) {
                    drawPauseBar(resolved.copy(alpha = pauseAlpha))
                }
            }

            scale(sx, sy, pivot = Offset.Zero) {
                // El icono más visible se dibuja encima: nunca queda un frame vacío.
                if (playAlpha <= pauseAlpha) {
                    drawPlayShape()
                    drawPauseShape()
                } else {
                    drawPauseShape()
                    drawPlayShape()
                }
            }
        }
    }
}

private fun DrawScope.drawPauseBar(color: Color) {
    drawRoundRect(
        color = color,
        topLeft = Offset(5f, 3f),
        size = Size(5f, 18f),
        cornerRadius = CornerRadius(1f),
        style = Stroke(width = 2f),
    )
}

// ---------------------------------------------------------------------------
// B) Morph geométrico: dos trazos que giran hasta ser las barras
// ---------------------------------------------------------------------------

@Composable
fun MorphPlayPauseIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    duration: Float = 1f,
    state: PlayPauseMorphState? = null,
) {
    val ownState = remember { PlayPauseMorphState() }
    val effective = state ?: ownState
    val readProgress = rememberMorphProgress(duration, effective)
    val resolved = morphColor(color)

    Box(
        modifier =
            modifier
                .size(size)
                .clickable { effective.toggle() },
        contentAlignment = Alignment.Center,
    ) {
        androidx.compose.foundation.Canvas(modifier = Modifier.size(size)) {
            val k = MorphEase.transform(readProgress().coerceIn(0f, 1f))
            val sx = this.size.width / 24f
            val sy = this.size.height / 24f

            scale(sx, sy, pivot = Offset.Zero) {
                // En play los dos trazos forman un ">" grueso; al interpolalos
                // se enderezan y se separan hasta convertirse en las dos barras.
                val upperStart = lerpPoint(Offset(6.5f, 5f), Offset(7.5f, 5f), k)
                val upperEnd = lerpPoint(Offset(18.5f, 12f), Offset(7.5f, 19f), k)
                val lowerStart = lerpPoint(Offset(18.5f, 12f), Offset(16.5f, 5f), k)
                val lowerEnd = lerpPoint(Offset(6.5f, 19f), Offset(16.5f, 19f), k)

                drawLine(
                    color = resolved,
                    start = upperStart,
                    end = upperEnd,
                    strokeWidth = 4f,
                    cap = StrokeCap.Round,
                )
                drawLine(
                    color = resolved,
                    start = lowerStart,
                    end = lowerEnd,
                    strokeWidth = 4f,
                    cap = StrokeCap.Round,
                )

                // Tercer trazo: cierra el triángulo en play y se colapsa al morph.
                drawLine(
                    color = resolved,
                    start = lerpPoint(Offset(6.5f, 19f), Offset(12f, 12f), k),
                    end = lerpPoint(Offset(6.5f, 5f), Offset(12f, 12f), k),
                    strokeWidth = 4f,
                    cap = StrokeCap.Round,
                    alpha = 1f - k,
                )
            }
        }
    }
}
