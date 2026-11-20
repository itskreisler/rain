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

/** Estado del icono ShuffleIcon: expone el control imperativo usado por el catálogo. */
class ShuffleIconState internal constructor() {
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
fun rememberShuffleIconState(): ShuffleIconState = remember { ShuffleIconState() }

private val ShuffleIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M18 14 L22 18 L18 22"), LucideMotion(22.0f, 18.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.15f, 0.97f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0.12f, LucideEase.InOut), "x" to lucideTrack(floatArrayOf(0f, 1.0f, -0.2f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0.12f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M18 2 L22 6 L18 10"), LucideMotion(22.0f, 6.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.15f, 0.97f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0f, LucideEase.InOut), "x" to lucideTrack(floatArrayOf(0f, 1.0f, -0.2f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M2 18 H3.973 A4 4 0 0 0 7.273 16.3 L12.727 7.7 A4 4 0 0 1 16.027 6 H22"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M2 6 H3.972 A4 4 0 0 1 7.572 8.2"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M22 18 H15.959 A4 4 0 0 1 12.659 16.2 L12.3 15.75"), null, false, true, 1.0f, 2.0f, null, 0f)
    )

private const val ShuffleIconTotalSeconds: Float = 0.7200f

/** Conversión a Compose del icono animado `shuffle` de animateicons/Lucide. */
@Composable
fun ShuffleIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: ShuffleIconState? = null,
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
                                    durationMillis = (720 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * ShuffleIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(ShuffleIconShapes, seconds, resolvedColor)
        }
    }
}
