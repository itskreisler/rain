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

/** Estado del icono DropletsIcon: expone el control imperativo usado por el catálogo. */
class DropletsIconState internal constructor() {
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
fun rememberDropletsIconState(): DropletsIconState = remember { DropletsIconState() }

private val DropletsIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M12.56 6.6 A10.97 10.97 0 0 0 14 3.02 C14.5 5.52 16 7.92 18 9.52 S21 13.02 21 15.02 A6.98 6.98 0 0 1 9.09 19.99"), LucideMotion(16.0f, 13.0f, mapOf("scale" to lucideTrack(floatArrayOf(0.92f, 1.06f, 1.0f), floatArrayOf(0f, 0.7f, 1.0f), 0.55f, 0.2f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0.2f, LucideEase.Out), "strokeDashoffset" to lucideTrack(floatArrayOf(36.0f, 0f), floatArrayOf(0f, 1.0f), 0.55f, 0.2f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(36.0f), 0f),
        LucideShape(lucidePath("M7 16.3 C9.2 16.3 11 14.47 11 12.25 C11 11.09 10.43 9.99 9.29 9.06 S7.29 6.75 7 5.3 C6.71 6.75 5.86 8.14 4.71 9.06 S3 11.1 3 12.25 C3 14.47 4.8 16.3 7 16.3 Z"), LucideMotion(7.0f, 11.0f, mapOf("scale" to lucideTrack(floatArrayOf(0.92f, 1.06f, 1.0f), floatArrayOf(0f, 0.7f, 1.0f), 0.55f, 0f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0f, LucideEase.Out), "strokeDashoffset" to lucideTrack(floatArrayOf(31.0f, 0f), floatArrayOf(0f, 1.0f), 0.55f, 0f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(31.0f), 0f)
    )

private const val DropletsIconTotalSeconds: Float = 0.7500f

/** Conversión a Compose del icono animado `droplets` de animateicons/Lucide. */
@Composable
fun DropletsIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: DropletsIconState? = null,
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
                                    durationMillis = (750 * duration.coerceAtLeast(0.1f)).toInt(),
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
            if (color == Color.Unspecified) Color(0xFF9C27B0) else color
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
        val seconds = phase.value * DropletsIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(DropletsIconShapes, seconds, resolvedColor)
        }
    }
}
