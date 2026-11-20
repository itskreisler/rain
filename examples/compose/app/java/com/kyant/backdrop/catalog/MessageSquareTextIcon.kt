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

/** Estado del icono MessageSquareTextIcon: expone el control imperativo usado por el catálogo. */
class MessageSquareTextIconState internal constructor() {
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
fun rememberMessageSquareTextIconState(): MessageSquareTextIconState = remember { MessageSquareTextIconState() }

private val MessageSquareTextIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(4.0f, 20.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.7f, 1.05f, 1.0f), floatArrayOf(0f, 0.35f, 0.75f, 1.0f), 0.65f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M22 17 A2 2 0 0 1 20 19 H6.828 A2 2 0 0 0 5.414 19.586 L3.212 21.788 A0.71 0.71 0 0 1 2 21.286 V5 A2 2 0 0 1 4 3 H20 A2 2 0 0 1 22 5 Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M7 7 H15"), LucideMotion(7.0f, 7.0f, mapOf("scaleX" to lucideTrack(floatArrayOf(1.0f, 0.5f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.45f, 0.25f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M7 11 H17"), LucideMotion(7.0f, 11.0f, mapOf("scaleX" to lucideTrack(floatArrayOf(1.0f, 0.5f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.45f, 0.32f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M7 15 H13"), LucideMotion(7.0f, 15.0f, mapOf("scaleX" to lucideTrack(floatArrayOf(1.0f, 0.5f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.45f, 0.39f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val MessageSquareTextIconTotalSeconds: Float = 0.8400f

/** Conversión a Compose del icono animado `message-square-text` de animateicons/Lucide. */
@Composable
fun MessageSquareTextIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: MessageSquareTextIconState? = null,
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
                                    durationMillis = (840 * duration.coerceAtLeast(0.1f)).toInt(),
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
            if (color == Color.Unspecified) Color(0xFF00BCD4) else color
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
        val seconds = phase.value * MessageSquareTextIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(MessageSquareTextIconShapes, seconds, resolvedColor)
        }
    }
}
