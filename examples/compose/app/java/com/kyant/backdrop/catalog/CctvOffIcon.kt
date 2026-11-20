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

/** Estado del icono CctvOffIcon: expone el control imperativo usado por el catálogo. */
class CctvOffIconState internal constructor() {
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
fun rememberCctvOffIconState(): CctvOffIconState = remember { CctvOffIconState() }

private val CctvOffIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.4f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(null, LucideMotion(9.0f, 15.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -8.0f, 6.0f, -3.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M12.309 6.652 L17.106 9.053 A1 1 0 0 1 17.553 10.394 L17.052 11.395 L17.657 12 H20.382 A1 1 0 0 1 21.276 13.447 L20.552 14.895"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M15.166 15.166 L14.447 16.605 A1 1 0 0 1 13.105 17.052 L3.61 12.3 A2.92 2.92 0 0 1 2.31 8.39 L3.69 5.6 A2.9 2.9 0 0 1 4.563 4.563"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M7 9 H7.01"), null, false, true, 1.0f, 2.0f, null, 0f))), LucideShape(lucidePath("M2 19 H5.76 A2 2 0 0 0 7.56 17.9 L9.001 14.998"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M2 21 V17"), null, false, true, 1.0f, 2.0f, null, 0f))),
        LucideShape(lucidePath("M2 2 L22 22"), LucideMotion(12.0f, 12.0f, mapOf("strokeDashoffset" to lucideTrack(floatArrayOf(29.0f, 0f), floatArrayOf(0f, 1.0f), 0.45f, 0.1f, LucideEase.InOut))), false, true, 1.0f, 2.0f, floatArrayOf(29.0f), 0f)
    )

private const val CctvOffIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `cctv-off` de animateicons/Lucide. */
@Composable
fun CctvOffIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: CctvOffIconState? = null,
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
        val seconds = phase.value * CctvOffIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(CctvOffIconShapes, seconds, resolvedColor)
        }
    }
}
