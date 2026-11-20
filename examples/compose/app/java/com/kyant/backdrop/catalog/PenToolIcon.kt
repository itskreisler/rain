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

/** Estado del icono PenToolIcon: expone el control imperativo usado por el catálogo. */
class PenToolIconState internal constructor() {
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
fun rememberPenToolIconState(): PenToolIconState = remember { PenToolIconState() }

private val PenToolIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(11.0f, 11.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -7.0f, 6.0f, -3.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M15.707 21.293 A1 1 0 0 1 14.293 21.293 L12.707 19.707 A1 1 0 0 1 12.707 18.293 L18.293 12.707 A1 1 0 0 1 19.707 12.707 L21.293 14.293 A1 1 0 0 1 21.293 15.707 Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M18 13 L16.625 6.126 A1 1 0 0 0 15.879 5.35 L3.235 2.028 A1 1 0 0 0 2.028 3.235 L5.35 15.879 A1 1 0 0 0 6.126 16.625 L13 18"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M2.3 2.3 L9.586 9.586"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M9 11A2 2 0 1 0 13 11A2 2 0 1 0 9 11Z"), LucideMotion(11.0f, 11.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.5f, 0.9f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.45f, 0.2f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val PenToolIconTotalSeconds: Float = 0.7000f

/** Conversión a Compose del icono animado `pen-tool` de animateicons/Lucide. */
@Composable
fun PenToolIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: PenToolIconState? = null,
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
        val seconds = phase.value * PenToolIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(PenToolIconShapes, seconds, resolvedColor)
        }
    }
}
