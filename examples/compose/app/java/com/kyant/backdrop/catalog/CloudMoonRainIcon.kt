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

/** Estado del icono CloudMoonRainIcon: expone el control imperativo usado por el catálogo. */
class CloudMoonRainIconState internal constructor() {
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
fun rememberCloudMoonRainIconState(): CloudMoonRainIconState = remember { CloudMoonRainIconState() }

private val CloudMoonRainIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M11 20 V22"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0.1f, LucideEase.Out), "strokeDashoffset" to lucideTrack(floatArrayOf(3.0f, 0f), floatArrayOf(0f, 1.0f), 0.4f, 0.1f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(3.0f), 0f),
        LucideShape(lucidePath("M18.376 14.512 A6 6 0 0 0 21.837 10.385 C21.985 9.76 21.178 9.415 20.589 9.671 A4 4 0 0 1 15.33 4.411 C15.585 3.822 15.24 3.016 14.614 3.163 A6 6 0 0 0 10.02 8.523"), LucideMotion(16.0f, 9.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -12.0f, 10.0f, -4.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M3 20 A5 5 0 1 1 11.9 16 H13 A3 3 0 0 1 15 21.24"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M7 19 V21"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0f, LucideEase.Out), "strokeDashoffset" to lucideTrack(floatArrayOf(3.0f, 0f), floatArrayOf(0f, 1.0f), 0.4f, 0f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(3.0f), 0f)
    )

private const val CloudMoonRainIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `cloud-moon-rain` de animateicons/Lucide. */
@Composable
fun CloudMoonRainIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: CloudMoonRainIconState? = null,
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
                                    durationMillis = (800 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * CloudMoonRainIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(CloudMoonRainIconShapes, seconds, resolvedColor)
        }
    }
}
