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

/** Estado del icono ChartScatterIcon: expone el control imperativo usado por el catálogo. */
class ChartScatterIconState internal constructor() {
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
fun rememberChartScatterIconState(): ChartScatterIconState = remember { ChartScatterIconState() }

private val ChartScatterIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M7 7.5A0.5 0.5 0 1 0 8 7.5A0.5 0.5 0 1 0 7 7.5Z"), LucideMotion(7.5f, 7.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.5f, 0.95f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -1.5f, 0.4f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0f, LucideEase.InOut))), true, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M18 5.5A0.5 0.5 0 1 0 19 5.5A0.5 0.5 0 1 0 18 5.5Z"), LucideMotion(18.5f, 5.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.5f, 0.95f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0.08f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -1.5f, 0.4f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0.08f, LucideEase.InOut))), true, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M11 11.5A0.5 0.5 0 1 0 12 11.5A0.5 0.5 0 1 0 11 11.5Z"), LucideMotion(11.5f, 11.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.5f, 0.95f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0.16f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -1.5f, 0.4f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0.16f, LucideEase.InOut))), true, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M7 16.5A0.5 0.5 0 1 0 8 16.5A0.5 0.5 0 1 0 7 16.5Z"), LucideMotion(7.5f, 16.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.5f, 0.95f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0.24f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -1.5f, 0.4f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0.24f, LucideEase.InOut))), true, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M17 14.5A0.5 0.5 0 1 0 18 14.5A0.5 0.5 0 1 0 17 14.5Z"), LucideMotion(17.5f, 14.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.5f, 0.95f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0.32f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -1.5f, 0.4f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0.32f, LucideEase.InOut))), true, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M3 3 V19 A2 2 0 0 0 5 21 H21"), null, false, true, 1.0f, 2.0f, null, 0f)
    )

private const val ChartScatterIconTotalSeconds: Float = 0.8700f

/** Conversión a Compose del icono animado `chart-scatter` de animateicons/Lucide. */
@Composable
fun ChartScatterIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: ChartScatterIconState? = null,
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
                                    durationMillis = (870 * duration.coerceAtLeast(0.1f)).toInt(),
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
            if (color == Color.Unspecified) Color(0xFF4CAF50) else color
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
        val seconds = phase.value * ChartScatterIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(ChartScatterIconShapes, seconds, resolvedColor)
        }
    }
}
