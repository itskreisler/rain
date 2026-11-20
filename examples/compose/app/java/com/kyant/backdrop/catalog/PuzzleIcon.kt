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

/** Estado del icono PuzzleIcon: expone el control imperativo usado por el catálogo. */
class PuzzleIconState internal constructor() {
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
fun rememberPuzzleIconState(): PuzzleIconState = remember { PuzzleIconState() }

private val PuzzleIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M15.39 4.39 A1 1 0 0 0 17.07 3.916 A2.5 2.5 0 1 1 20.084 6.931 A1 1 0 0 0 19.61 8.611 L21.293 10.293 A2.414 2.414 0 0 1 21.293 13.707 L19.61 15.39 A1 1 0 0 1 17.93 14.916 A2.5 2.5 0 1 0 14.916 17.931 A1 1 0 0 1 15.39 19.611 L13.707 21.293 A2.414 2.414 0 0 1 10.293 21.293 L8.61 19.61 A1 1 0 0 0 6.93 20.084 A2.5 2.5 0 1 1 3.916 17.069 A1 1 0 0 0 4.39 15.389 L2.707 13.707 A2.414 2.414 0 0 1 2.707 10.293 L4.39 8.61 A1 1 0 0 1 6.07 9.084 A2.5 2.5 0 1 0 9.084 6.069 A1 1 0 0 1 8.61 4.389 L10.293 2.707 A2.414 2.414 0 0 1 13.707 2.707 Z"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.94f, 1.06f, 1.0f), floatArrayOf(0f, 0.4f, 0.7f, 1.0f), 0.7f, 0f, LucideEase.InOut), "rotate" to lucideTrack(floatArrayOf(0f, -10.0f, 2.0f, 0f), floatArrayOf(0f, 0.4f, 0.7f, 1.0f), 0.7f, 0f, LucideEase.InOut), "x" to lucideTrack(floatArrayOf(0f, -1.5f, 0f, 0f), floatArrayOf(0f, 0.4f, 0.7f, 1.0f), 0.7f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -1.5f, 0f, 0f), floatArrayOf(0f, 0.4f, 0.7f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val PuzzleIconTotalSeconds: Float = 0.7000f

/** Conversión a Compose del icono animado `puzzle` de animateicons/Lucide. */
@Composable
fun PuzzleIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: PuzzleIconState? = null,
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
        val seconds = phase.value * PuzzleIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(PuzzleIconShapes, seconds, resolvedColor)
        }
    }
}
