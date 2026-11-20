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

/** Estado del icono MessageCircleDashedCheckIcon: expone el control imperativo usado por el catálogo. */
class MessageCircleDashedCheckIconState internal constructor() {
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
fun rememberMessageCircleDashedCheckIconState(): MessageCircleDashedCheckIconState = remember { MessageCircleDashedCheckIconState() }

private val MessageCircleDashedCheckIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M10.1 2.182 A10 10 0 0 1 13.9 2.182"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, 0f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -1.0f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M17.609 3.72 A10 10 0 0 1 20.299 6.42"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, 0.7f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -0.7f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.05f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M21.818 10.1 A10 10 0 0 1 21.818 13.9"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, 1.0f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, 0f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.1f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M20.28 17.61 A10 10 0 0 1 17.58 20.3"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, 0.7f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, 0.7f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.15f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M13.9 21.818 A10 10 0 0 1 10.1 21.818"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, 0f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, 1.0f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.2f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M6.163 21.117 L3.257 21.967 A1 1 0 0 1 2.021 20.798 L2.986 17.818"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, -0.7f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, 0.7f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.25f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M2.182 13.9 A10 10 0 0 1 2.182 10.1"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, -1.0f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, 0f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.3f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M3.721 6.391 A10 10 0 0 1 6.421 3.701"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, -0.7f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -0.7f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.35f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M16 9 L10.5 14.5 L8 12"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(0.8f, 1.12f, 1.0f), floatArrayOf(0f, 0.6f, 1.0f), 0.45f, 0.19f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0.15f, LucideEase.InOut), "strokeDashoffset" to lucideTrack(floatArrayOf(12.0f, 0f), floatArrayOf(0f, 1.0f), 0.45f, 0.15f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(12.0f), 0f)
    )

private const val MessageCircleDashedCheckIconTotalSeconds: Float = 0.7500f

/** Conversión a Compose del icono animado `message-circle-dashed-check` de animateicons/Lucide. */
@Composable
fun MessageCircleDashedCheckIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: MessageCircleDashedCheckIconState? = null,
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
                                    durationMillis = (750 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * MessageCircleDashedCheckIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(MessageCircleDashedCheckIconShapes, seconds, resolvedColor)
        }
    }
}
