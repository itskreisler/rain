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

/** Estado del icono BellRingIcon: expone el control imperativo usado por el catálogo. */
class BellRingIconState internal constructor() {
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
fun rememberBellRingIconState(): BellRingIconState = remember { BellRingIconState() }

private val BellRingIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M10.268 21 A2 2 0 0 0 13.732 21"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, 1.7f, -1.4f, 0.8f, -0.4f, 0f), floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f), 1.1f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M22 8 C22 5.7 21.2 3.7 20 2"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.6f, 1.15f, 0.7f, 1.15f, 1.0f), floatArrayOf(0f, 0.18f, 0.385f, 0.59f, 0.795f, 1.0f), 1.1f, 0.07f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.5f, 1.0f, 0.25f, 1.0f, 1.0f), floatArrayOf(0f, 0.18f, 0.385f, 0.59f, 0.795f, 1.0f), 1.1f, 0.07f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M3.262 15.326 A1 1 0 0 0 4 17 H20 A1 1 0 0 0 20.74 15.327 C19.41 13.956 18 12.499 18 8 A6 6 0 0 0 6 8 C6 12.499 4.589 13.956 3.262 15.326"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M4 2 C2.8 3.7 2 5.7 2 8"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.6f, 1.15f, 0.7f, 1.15f, 1.0f), floatArrayOf(0f, 0.18f, 0.385f, 0.59f, 0.795f, 1.0f), 1.1f, 0.07f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.5f, 1.0f, 0.25f, 1.0f, 1.0f), floatArrayOf(0f, 0.18f, 0.385f, 0.59f, 0.795f, 1.0f), 1.1f, 0.07f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val BellRingIconTotalSeconds: Float = 1.1700f

/** Conversión a Compose del icono animado `bell-ring` de animateicons/Lucide. */
@Composable
fun BellRingIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: BellRingIconState? = null,
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
                                    durationMillis = (1170 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * BellRingIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(BellRingIconShapes, seconds, resolvedColor)
        }
    }
}
