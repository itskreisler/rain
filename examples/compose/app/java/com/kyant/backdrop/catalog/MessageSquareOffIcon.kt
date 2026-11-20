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

/** Estado del icono MessageSquareOffIcon: expone el control imperativo usado por el catálogo. */
class MessageSquareOffIconState internal constructor() {
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
fun rememberMessageSquareOffIconState(): MessageSquareOffIconState = remember { MessageSquareOffIconState() }

private val MessageSquareOffIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.4f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(null, LucideMotion(4.0f, 20.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.7f, 1.05f, 1.0f), floatArrayOf(0f, 0.35f, 0.75f, 1.0f), 0.65f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M19 19 H6.828 A2 2 0 0 0 5.414 19.586 L3.212 21.788 A0.7 0.7 0 0 1 2 21.286 V5 A2 2 0 0 1 3.184 3.174"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M8.656 3 H20 A2 2 0 0 1 22 5 V16.344"), null, false, true, 1.0f, 2.0f, null, 0f))))),
        LucideShape(lucidePath("M2 2 L22 22"), LucideMotion(12.0f, 12.0f, mapOf("strokeDashoffset" to lucideTrack(floatArrayOf(31.0f, 0f), floatArrayOf(0f, 1.0f), 0.45f, 0.1f, LucideEase.InOut))), false, true, 1.0f, 2.0f, floatArrayOf(30.0f, 200.0f), 0f)
    )

private const val MessageSquareOffIconTotalSeconds: Float = 0.7000f

/** Conversión a Compose del icono animado `message-square-off` de animateicons/Lucide. */
@Composable
fun MessageSquareOffIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: MessageSquareOffIconState? = null,
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
        val seconds = phase.value * MessageSquareOffIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(MessageSquareOffIconShapes, seconds, resolvedColor)
        }
    }
}
