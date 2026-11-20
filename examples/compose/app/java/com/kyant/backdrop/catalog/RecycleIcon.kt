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

/** Estado del icono RecycleIcon: expone el control imperativo usado por el catálogo. */
class RecycleIconState internal constructor() {
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
fun rememberRecycleIconState(): RecycleIconState = remember { RecycleIconState() }

private val RecycleIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 13.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.9f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.85f, 0f, LucideEase.InOut), "rotate" to lucideTrack(floatArrayOf(0f, 360.0f), floatArrayOf(0f, 1.0f), 0.85f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M7 19 H4.815 A1.83 1.83 0 0 1 3.245 18.119 A1.785 1.785 0 0 1 3.241 16.335 L7.196 9.5"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M11 19 H19.203 A1.83 1.83 0 0 0 20.759 18.11 A1.784 1.784 0 0 0 20.759 16.335 L19.533 14.215"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14 16 L11 19 L14 22"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M8.293 13.596 L7.196 9.5 L3.1 10.598"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M9.344 5.811 L10.437 3.919 A1.83 1.83 0 0 1 11.985 3 A1.784 1.784 0 0 1 13.531 3.888 L17.474 10.731"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M13.378 9.633 L17.474 10.731 L18.571 6.635"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val RecycleIconTotalSeconds: Float = 0.8500f

/** Conversión a Compose del icono animado `recycle` de animateicons/Lucide. */
@Composable
fun RecycleIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: RecycleIconState? = null,
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
                                    durationMillis = (850 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * RecycleIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(RecycleIconShapes, seconds, resolvedColor)
        }
    }
}
