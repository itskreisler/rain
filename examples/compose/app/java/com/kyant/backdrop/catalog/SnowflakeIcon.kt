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

/** Estado del icono SnowflakeIcon: expone el control imperativo usado por el catálogo. */
class SnowflakeIconState internal constructor() {
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
fun rememberSnowflakeIconState(): SnowflakeIconState = remember { SnowflakeIconState() }

private val SnowflakeIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.92f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.8f, 0f, LucideEase.InOut), "rotate" to lucideTrack(floatArrayOf(0f, 180.0f), floatArrayOf(0f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M10 20 L8.75 17.5 L6 18"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M10 4 L8.75 6.5 L6 6"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14 20 L15.25 17.5 L18 18"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14 4 L15.25 6.5 L18 6"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M17 21 L14 15 H10"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M17 3 L14 9 L15.5 12"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M2 12 H8.5 L10 9"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M20 10 L18.5 12 L20 14"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M22 12 H15.5 L14 15"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M4 10 L5.5 12 L4 14"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M7 21 L10 15 L8.5 12"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M7 3 L10 9 H14"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val SnowflakeIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `snowflake` de animateicons/Lucide. */
@Composable
fun SnowflakeIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: SnowflakeIconState? = null,
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
        val seconds = phase.value * SnowflakeIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(SnowflakeIconShapes, seconds, resolvedColor)
        }
    }
}
