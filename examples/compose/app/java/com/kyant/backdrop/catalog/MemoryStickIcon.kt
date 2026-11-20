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

/** Estado del icono MemoryStickIcon: expone el control imperativo usado por el catálogo. */
class MemoryStickIconState internal constructor() {
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
fun rememberMemoryStickIconState(): MemoryStickIconState = remember { MemoryStickIconState() }

private val MemoryStickIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M12 12 V10"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.14f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M12 18 V16"), LucideMotion(12.0f, 16.0f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 1.8f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.45f, 0.14f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M16 12 V10"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.28f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M16 18 V16"), LucideMotion(16.0f, 16.0f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 1.8f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.45f, 0.21f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M2 11 H3.5"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M20 18 V16"), LucideMotion(20.0f, 16.0f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 1.8f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.45f, 0.28f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M20.5 11 H22"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M4 18 V16"), LucideMotion(4.0f, 16.0f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 1.8f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.45f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M8 12 V10"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M8 18 V16"), LucideMotion(8.0f, 16.0f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 1.8f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.45f, 0.07f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M4 6H20A2 2 0 0 1 22 8V14A2 2 0 0 1 20 16H4A2 2 0 0 1 2 14V8A2 2 0 0 1 4 6Z"), null, false, true, 1.0f, 2.0f, null, 0f)
    )

private const val MemoryStickIconTotalSeconds: Float = 0.7300f

/** Conversión a Compose del icono animado `memory-stick` de animateicons/Lucide. */
@Composable
fun MemoryStickIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: MemoryStickIconState? = null,
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
                                    durationMillis = (730 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * MemoryStickIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(MemoryStickIconShapes, seconds, resolvedColor)
        }
    }
}
