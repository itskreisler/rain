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

/** Estado del icono FunnelXIcon: expone el control imperativo usado por el catálogo. */
class FunnelXIconState internal constructor() {
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
fun rememberFunnelXIconState(): FunnelXIconState = remember { FunnelXIconState() }

private val FunnelXIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M12.531 3 H3 A1 1 0 0 0 2.258 4.67 L9.483 12.659 A2 2 0 0 1 10 14 V20 A1 1 0 0 0 10.553 20.895 L12.553 21.895 A1 1 0 0 0 14 21 V14 A2 2 0 0 1 14.517 12.659 L14.944 12.186"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, -0.7f, 0.7f, -0.4f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.5f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(null, LucideMotion(19.0f, 6.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.5f, 0f, LucideEase.InOut), "rotate" to lucideTrack(floatArrayOf(0f, 90.0f), floatArrayOf(0f, 1.0f), 0.5f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M16.5 3.5 L21.5 8.5"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M21.5 3.5 L16.5 8.5"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val FunnelXIconTotalSeconds: Float = 0.5000f

/** Conversión a Compose del icono animado `funnel-x` de animateicons/Lucide. */
@Composable
fun FunnelXIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: FunnelXIconState? = null,
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
                                    durationMillis = (500 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * FunnelXIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(FunnelXIconShapes, seconds, resolvedColor)
        }
    }
}
