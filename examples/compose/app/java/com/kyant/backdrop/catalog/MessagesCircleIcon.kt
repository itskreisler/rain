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

/** Estado del icono MessagesCircleIcon: expone el control imperativo usado por el catálogo. */
class MessagesCircleIconState internal constructor() {
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
fun rememberMessagesCircleIconState(): MessagesCircleIconState = remember { MessagesCircleIconState() }

private val MessagesCircleIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M2.696 12.708 A1 1 0 0 0 2.638 11.923 A7 7 0 1 1 6.156 15.396 A1 1 0 0 0 5.412 15.335 L3.257 15.965 A1 1 0 0 1 2.021 14.798 Z"), LucideMotion(9.0f, 9.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.85f, 1.08f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -1.0f, 0.3f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M19.95 10.05 A7 7 0 0 1 21.362 17.922 A1 1 0 0 0 21.304 18.709 L21.979 20.798 A1 1 0 0 1 20.743 21.966 L18.588 21.335 A1 1 0 0 0 17.843 21.395 A7 7 0 0 1 10.05 19.95"), LucideMotion(15.0f, 15.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.85f, 1.08f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0.25f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -1.0f, 0.3f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.55f, 0.25f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val MessagesCircleIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `messages-circle` de animateicons/Lucide. */
@Composable
fun MessagesCircleIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: MessagesCircleIconState? = null,
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
            if (color == Color.Unspecified) Color(0xFF8BC34A) else color
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
        val seconds = phase.value * MessagesCircleIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(MessagesCircleIconShapes, seconds, resolvedColor)
        }
    }
}
