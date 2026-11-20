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

/** Estado del icono SirenIcon: expone el control imperativo usado por el catálogo. */
class SirenIconState internal constructor() {
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
fun rememberSirenIconState(): SirenIconState = remember { SirenIconState() }

private val SirenIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 20.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -5.0f, 5.0f, -5.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M7 18 V12 A5 5 0 1 1 17 12 V18"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M5 21 A1 1 0 0 0 6 22 H18 A1 1 0 0 0 19 21 V20 A2 2 0 0 0 17 18 H7 A2 2 0 0 0 5 20 Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M12 12 V18"), null, false, true, 1.0f, 2.0f, null, 0f))),
        LucideShape(lucidePath("M21 12 H22"), LucideMotion(21.5f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0f, 1.8f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0.32f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0f, 1.0f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0.32f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M18.5 4.5 L18 5"), LucideMotion(18.25f, 4.75f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0f, 1.8f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0.24f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0f, 1.0f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0.24f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M2 12 H3"), LucideMotion(2.5f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0f, 1.8f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0f, 1.0f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M12 2 V3"), LucideMotion(12.0f, 2.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0f, 1.8f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0.16f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0f, 1.0f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0.16f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M4.929 4.929 L5.636 5.636"), LucideMotion(5.28f, 5.28f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0f, 1.8f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0.08f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0f, 1.0f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0.08f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val SirenIconTotalSeconds: Float = 0.7200f

/** Conversión a Compose del icono animado `siren` de animateicons/Lucide. */
@Composable
fun SirenIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: SirenIconState? = null,
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
                                    durationMillis = (720 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * SirenIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(SirenIconShapes, seconds, resolvedColor)
        }
    }
}
