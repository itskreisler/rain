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

/** Estado del icono ReceiptIcon: expone el control imperativo usado por el catálogo. */
class ReceiptIconState internal constructor() {
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
fun rememberReceiptIconState(): ReceiptIconState = remember { ReceiptIconState() }

private val ReceiptIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -3.0f, 2.5f, -1.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M4 3 A1 1 0 0 1 5 2 A1.3 1.3 0 0 1 5.7 2.2 L6.633 2.8 A1.3 1.3 0 0 0 8.033 2.8 L8.967 2.2 A1.3 1.3 0 0 1 10.367 2.2 L11.3 2.8 A1.3 1.3 0 0 0 12.7 2.8 L13.633 2.2 A1.3 1.3 0 0 1 15.033 2.2 L15.967 2.8 A1.3 1.3 0 0 0 17.367 2.8 L18.3 2.2 A1.3 1.3 0 0 1 19 2 A1 1 0 0 1 20 3 V21 A1 1 0 0 1 19 22 A1.3 1.3 0 0 1 18.3 21.8 L17.367 21.2 A1.3 1.3 0 0 0 15.967 21.2 L15.033 21.8 A1.3 1.3 0 0 1 13.633 21.8 L12.7 21.2 A1.3 1.3 0 0 0 11.3 21.2 L10.367 21.8 A1.3 1.3 0 0 1 8.967 21.8 L8.033 21.2 A1.3 1.3 0 0 0 6.633 21.2 L5.7 21.8 A1.3 1.3 0 0 1 5 22 A1 1 0 0 1 4 21 Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.5f, 0.1f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M12 17 V7"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M16 8 H10 A2 2 0 0 0 10 12 H14 A2 2 0 0 1 14 16 H8"), null, false, true, 1.0f, 2.0f, null, 0f)))))
    )

private const val ReceiptIconTotalSeconds: Float = 0.7000f

/** Conversión a Compose del icono animado `receipt` de animateicons/Lucide. */
@Composable
fun ReceiptIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: ReceiptIconState? = null,
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
            if (color == Color.Unspecified) Color(0xFF4CAF50) else color
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
        val seconds = phase.value * ReceiptIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(ReceiptIconShapes, seconds, resolvedColor)
        }
    }
}
