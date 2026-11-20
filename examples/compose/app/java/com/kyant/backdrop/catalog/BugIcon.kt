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

/** Estado del icono BugIcon: expone el control imperativo usado por el catálogo. */
class BugIconState internal constructor() {
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
fun rememberBugIconState(): BugIconState = remember { BugIconState() }

private val BugIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 13.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -6.0f, 6.0f, -4.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.8f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -0.8f, 0f, -0.8f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M12 20 V11"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14 7 A4 4 0 0 1 18 11 V14 A6 6 0 0 1 6 14 V11 A4 4 0 0 1 10 7 Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M9 7.13 V6 A3 3 0 1 1 15 6 V7.13"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14.12 3.88 L16 2"), LucideMotion(14.12f, 3.88f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -16.0f, 12.0f, -10.0f, 6.0f, 0f), floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M8 2 L9.88 3.88"), LucideMotion(9.88f, 3.88f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, 16.0f, -12.0f, 10.0f, -6.0f, 0f), floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M3 5 A4 4 0 0 0 6.55 8.97"), LucideMotion(6.55f, 8.97f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, 16.0f, -12.0f, 10.0f, -6.0f, 0f), floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M6 13 H2"), LucideMotion(6.0f, 13.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -16.0f, 12.0f, -10.0f, 6.0f, 0f), floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M3 21 A4 4 0 0 1 6.81 17"), LucideMotion(6.81f, 17.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, 16.0f, -12.0f, 10.0f, -6.0f, 0f), floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M21 5 A4 4 0 0 1 17.45 8.97"), LucideMotion(17.45f, 8.97f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -16.0f, 12.0f, -10.0f, 6.0f, 0f), floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M22 13 H18"), LucideMotion(18.0f, 13.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, 16.0f, -12.0f, 10.0f, -6.0f, 0f), floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M21 21 A4 4 0 0 0 17.19 17"), LucideMotion(17.19f, 17.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -16.0f, 12.0f, -10.0f, 6.0f, 0f), floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val BugIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `bug` de animateicons/Lucide. */
@Composable
fun BugIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: BugIconState? = null,
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
        val seconds = phase.value * BugIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(BugIconShapes, seconds, resolvedColor)
        }
    }
}
