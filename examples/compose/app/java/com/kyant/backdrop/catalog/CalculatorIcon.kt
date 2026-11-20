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

/** Estado del icono CalculatorIcon: expone el control imperativo usado por el catálogo. */
class CalculatorIconState internal constructor() {
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
fun rememberCalculatorIconState(): CalculatorIconState = remember { CalculatorIconState() }

private val CalculatorIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M6 2H18A2 2 0 0 1 20 4V20A2 2 0 0 1 18 22H6A2 2 0 0 1 4 20V4A2 2 0 0 1 6 2Z"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M8 6L16 6"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0f, LucideEase.Out), "strokeDashoffset" to lucideTrack(floatArrayOf(9.0f, 0f), floatArrayOf(0f, 1.0f), 0.4f, 0f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(9.0f), 0f),
        LucideShape(lucidePath("M16 14L16 18"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M16 10 H16.01"), LucideMotion(16.0f, 10.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.8f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.15f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M12 10 H12.01"), LucideMotion(12.0f, 10.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.8f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.21f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M8 10 H8.01"), LucideMotion(8.0f, 10.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.8f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.27f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M12 14 H12.01"), LucideMotion(12.0f, 14.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.8f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.33f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M8 14 H8.01"), LucideMotion(8.0f, 14.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.8f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.39f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M12 18 H12.01"), LucideMotion(12.0f, 18.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.8f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.45f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M8 18 H8.01"), LucideMotion(8.0f, 18.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.8f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.51f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val CalculatorIconTotalSeconds: Float = 0.8600f

/** Conversión a Compose del icono animado `calculator` de animateicons/Lucide. */
@Composable
fun CalculatorIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: CalculatorIconState? = null,
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
                                    durationMillis = (860 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * CalculatorIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(CalculatorIconShapes, seconds, resolvedColor)
        }
    }
}
