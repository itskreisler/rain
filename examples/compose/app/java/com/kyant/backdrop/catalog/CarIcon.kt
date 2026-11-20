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

/** Estado del icono CarIcon: expone el control imperativo usado por el catálogo. */
class CarIconState internal constructor() {
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
fun rememberCarIconState(): CarIconState = remember { CarIconState() }

private val CarIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(7.0f, 19.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -6.0f, 2.0f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0f, LucideEase.InOut), "x" to lucideTrack(floatArrayOf(0f, 0.8f, -0.2f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M19 17 H21 C21.6 17 22 16.6 22 16 V13 C22 12.1 21.3 11.3 20.5 11.1 C18.7 10.6 16 10 16 10 S14.7 8.6 13.8 7.7 C13.3 7.3 12.7 7 12 7 H5 C4.4 7 3.9 7.4 3.6 7.9 L2.2 10.8 A3.7 3.7 0 0 0 2 12 V16 C2 16.6 2.4 17 3 17 H5"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M5 17A2 2 0 1 0 9 17A2 2 0 1 0 5 17Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M9 17 H15"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M15 17A2 2 0 1 0 19 17A2 2 0 1 0 15 17Z"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val CarIconTotalSeconds: Float = 0.6000f

/** Conversión a Compose del icono animado `car` de animateicons/Lucide. */
@Composable
fun CarIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: CarIconState? = null,
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
                                    durationMillis = (600 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * CarIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(CarIconShapes, seconds, resolvedColor)
        }
    }
}
