package com.kyant.backdrop.catalog

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.MotionDurationScale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/** Estado del icono WandSparklesIcon: expone el control imperativo usado por el catálogo. */
class WandSparklesIconState internal constructor() {
    internal var startAction: (() -> Unit)? = null
    internal var stopAction: (() -> Unit)? = null

    /** Se ejecuta cuando la animación termina sin haberse cancelado. */
    var onAnimationEnd: (() -> Unit)? = null

    fun startAnimation() {
        startAction?.invoke()
    }

    fun stopAnimation() {
        stopAction?.invoke()
    }
}

@Composable
fun rememberWandSparklesIconState(): WandSparklesIconState = remember { WandSparklesIconState() }

private val WandSparklesIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(3.0f, 21.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -8.0f, 5.0f, -2.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M21.64 3.64 L20.36 2.36 A1.21 1.21 0 0 0 18.64 2.36 L2.36 18.64 A1.21 1.21 0 0 0 2.36 20.36 L3.64 21.64 A1.2 1.2 0 0 0 5.36 21.64 L21.64 5.36 A1.2 1.2 0 0 0 21.64 3.64"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14 7 L17 10"), null, false, true, 1.0f, 2.0f, null, 0f))),
        LucideShape(null, LucideMotion(5.0f, 8.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0f, 1.3f, 1.0f), floatArrayOf(0f, 0.25f, 0.65f, 1.0f), 0.5f, 0.15f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0f, 1.0f, 1.0f), floatArrayOf(0f, 0.25f, 0.65f, 1.0f), 0.5f, 0.15f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M5 6 V10"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M7 8 H3"), null, false, true, 1.0f, 2.0f, null, 0f))),
        LucideShape(null, LucideMotion(10.0f, 3.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0f, 1.3f, 1.0f), floatArrayOf(0f, 0.25f, 0.65f, 1.0f), 0.5f, 0.27f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0f, 1.0f, 1.0f), floatArrayOf(0f, 0.25f, 0.65f, 1.0f), 0.5f, 0.27f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M10 2 V4"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M11 3 H9"), null, false, true, 1.0f, 2.0f, null, 0f))),
        LucideShape(null, LucideMotion(19.0f, 16.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0f, 1.3f, 1.0f), floatArrayOf(0f, 0.25f, 0.65f, 1.0f), 0.5f, 0.39f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0f, 1.0f, 1.0f), floatArrayOf(0f, 0.25f, 0.65f, 1.0f), 0.5f, 0.39f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M19 14 V18"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M21 16 H17"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val WandSparklesIconTotalSeconds: Float = 0.8900f

/** Conversión a Compose del icono animado `wand-sparkles` de animateicons/Lucide. */
@Composable
fun WandSparklesIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: WandSparklesIconState? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val phase = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val motionScale = scope.coroutineContext[MotionDurationScale]?.scaleFactor ?: 1f

    var animationJob by remember { mutableStateOf<Job?>(null) }

    val start: () -> Unit =
        remember(motionScale, duration) {
            {
                if (motionScale <= 0f) {
                    state?.onAnimationEnd?.invoke()
                    return@remember
                }
                animationJob?.cancel()
                animationJob =
                    scope.launch {
                        phase.snapTo(0f)
                        phase.animateTo(
                            targetValue = 1f,
                            animationSpec =
                                tween(
                                    durationMillis = (890 * duration.coerceAtLeast(0.1f)).toInt(),
                                ),
                        )
                        phase.snapTo(0f)
                        // animateTo vuelve aquí solo si no se canceló: fin de la animación.
                        state?.onAnimationEnd?.invoke()
                    }
            }
        }

    val stop: () -> Unit =
        remember {
            {
                animationJob?.cancel()
                animationJob =
                    scope.launch {
                        phase.snapTo(0f)
                    }
            }
        }

    // start/stop como claves: al cambiar `duration` se recalculan y hay que
    // re-registrarlos en el State, o el action seguiría usando la duración vieja.
    DisposableEffect(state, start, stop) {
        if (state != null) {
            state.startAction = start
            state.stopAction = stop
        }
        onDispose {
            if (state != null) {
                state.startAction = null
                state.stopAction = null
                state.onAnimationEnd = null
            }
            animationJob?.cancel()
            animationJob = null
        }
    }

    LaunchedEffect(isHovered, isAnimated) {
        if (isAnimated && isHovered) {
            start()
        } else {
            stop()
        }
    }

    val resolvedColor =
        remember(color) {
            if (color == Color.Unspecified) Color(0xFFFF9800) else color
        }

    Canvas(
        modifier =
            modifier
                .size(size)
                .hoverable(
                    interactionSource = interactionSource,
                    enabled = isAnimated,
                ),
    ) {
        val seconds = phase.value * WandSparklesIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(WandSparklesIconShapes, seconds, resolvedColor)
        }
    }
}
