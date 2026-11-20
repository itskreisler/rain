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

/** Estado del icono BrainIcon: expone el control imperativo usado por el catálogo. */
class BrainIconState internal constructor() {
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
fun rememberBrainIconState(): BrainIconState = remember { BrainIconState() }

private val BrainIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.06f, 0.97f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M12 18 V5"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M15 13 A4.17 4.17 0 0 1 12 9 A4.17 4.17 0 0 1 9 13"), LucideMotion(12.0f, 11.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.4f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.5f, 0.15f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M17.598 6.5 A3 3 0 1 0 12 5 A3 3 0 1 0 6.402 6.5"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M17.997 5.125 A4 4 0 0 1 20.523 10.895"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M18 18 A4 4 0 0 0 20 10.536"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M19.967 17.483 A4 4 0 1 1 12 18 A4 4 0 1 1 4.033 17.483"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M6 18 A4 4 0 0 1 4 10.536"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M6.003 5.125 A4 4 0 0 0 3.477 10.895"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val BrainIconTotalSeconds: Float = 0.7000f

/** Conversión a Compose del icono animado `brain` de animateicons/Lucide. */
@Composable
fun BrainIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: BrainIconState? = null,
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
                                    durationMillis = (700 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * BrainIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(BrainIconShapes, seconds, resolvedColor)
        }
    }
}
