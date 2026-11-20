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

/** Estado del icono HazeIcon: expone el control imperativo usado por el catálogo. */
class HazeIconState internal constructor() {
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
fun rememberHazeIconState(): HazeIconState = remember { HazeIconState() }

private val HazeIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 13.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.1f, 0.98f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M5.2 6.2 L6.6 7.6"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M2 13 H4"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M20 13 H22"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M17.4 7.6 L18.8 6.2"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M16 13 A4 4 0 0 0 8 13"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M12 5 V2.5"), null, false, true, 1.0f, 2.0f, null, 0f))),
        LucideShape(lucidePath("M22 17 H2"), LucideMotion(22.0f, 17.0f, mapOf("scaleX" to lucideTrack(floatArrayOf(1.0f, 0.55f, 1.03f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.6f, 0.08f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M22 21 H2"), LucideMotion(2.0f, 21.0f, mapOf("scaleX" to lucideTrack(floatArrayOf(1.0f, 0.55f, 1.03f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.6f, 0.2f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val HazeIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `haze` de animateicons/Lucide. */
@Composable
fun HazeIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: HazeIconState? = null,
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
            if (color == Color.Unspecified) Color(0xFFFF5722) else color
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
        val seconds = phase.value * HazeIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(HazeIconShapes, seconds, resolvedColor)
        }
    }
}
