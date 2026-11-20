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

/** Estado del icono BellElectricIcon: expone el control imperativo usado por el catálogo. */
class BellElectricIconState internal constructor() {
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
fun rememberBellElectricIconState(): BellElectricIconState = remember { BellElectricIconState() }

private val BellElectricIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M18.518 17.347 A7 7 0 0 1 14 19"), LucideMotion(14.0f, 18.0f, mapOf("scale" to lucideTrack(floatArrayOf(0.85f, 1.0f, 0.9f, 1.0f), floatArrayOf(0f, 0.3f, 0.6f, 1.0f), 0.9f, 0f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(0f, 1.0f, 0.4f, 1.0f), floatArrayOf(0f, 0.3f, 0.6f, 1.0f), 0.9f, 0f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M18.8 4 A11 11 0 0 1 20 9"), LucideMotion(19.0f, 6.0f, mapOf("scale" to lucideTrack(floatArrayOf(0.85f, 1.0f, 0.9f, 1.0f), floatArrayOf(0f, 0.3f, 0.6f, 1.0f), 0.9f, 0.1f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(0f, 1.0f, 0.4f, 1.0f), floatArrayOf(0f, 0.3f, 0.6f, 1.0f), 0.9f, 0.1f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M18 16A2 2 0 1 0 22 16A2 2 0 1 0 18 16Z"), LucideMotion(20.0f, 16.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.75f, 1.0f, 0.85f, 1.0f), floatArrayOf(0f, 0.15f, 0.4f, 0.6f, 1.0f), 0.9f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, -1.2f, 1.2f, -0.8f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.5f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M9 9 H9.01"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M2 9A7 7 0 1 0 16 9A7 7 0 1 0 2 9Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M6 16H12A2 2 0 0 1 14 18V20A2 2 0 0 1 12 22H6A2 2 0 0 1 4 20V18A2 2 0 0 1 6 16Z"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val BellElectricIconTotalSeconds: Float = 1.0000f

/** Conversión a Compose del icono animado `bell-electric` de animateicons/Lucide. */
@Composable
fun BellElectricIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: BellElectricIconState? = null,
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
                                    durationMillis = (1000 * duration.coerceAtLeast(0.1f)).toInt(),
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
            if (color == Color.Unspecified) Color(0xFFE91E63) else color
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
        val seconds = phase.value * BellElectricIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(BellElectricIconShapes, seconds, resolvedColor)
        }
    }
}
