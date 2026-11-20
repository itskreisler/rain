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

/** Estado del icono DogIcon: expone el control imperativo usado por el catálogo. */
class DogIconState internal constructor() {
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
fun rememberDogIconState(): DogIconState = remember { DogIconState() }

private val DogIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M11.25 16.25 H12.75 L12 17 Z"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M16 14 V14.5"), LucideMotion(16.0f, 14.25f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 0.1f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.35f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M4.42 11.247 A13.152 13.152 0 0 0 4 14.556 C4 18.728 7.582 21 12 21 S20 18.728 20 14.556 A11.702 11.702 0 0 0 19.507 11.247"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M8 14 V14.5"), LucideMotion(8.0f, 14.25f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 0.1f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.35f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M8.5 8.5 C8.116 9.55 7.417 10.528 6.156 11 C4.225 11.722 2.58 10.703 2.5 10 C2.387 9.006 3.677 3.47 6.5 3 C8.423 2.679 10.151 3.845 10.151 5.235"), LucideMotion(10.151f, 5.235f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, 18.0f, -6.0f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M10.151 5.235 A7.497 7.497 0 0 1 14 5.277"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M14 5.277 C14 3.887 15.844 2.679 17.767 3 C20.59 3.47 21.88 9.006 21.767 10 C21.687 10.703 20.042 11.722 18.111 11 C16.85 10.528 16.256 9.55 15.872 8.5"), LucideMotion(14.0f, 5.277f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -18.0f, 6.0f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0.1f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val DogIconTotalSeconds: Float = 0.7000f

/** Conversión a Compose del icono animado `dog` de animateicons/Lucide. */
@Composable
fun DogIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: DogIconState? = null,
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
        val seconds = phase.value * DogIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(DogIconShapes, seconds, resolvedColor)
        }
    }
}
