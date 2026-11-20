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

/** Estado del icono GuitarIcon: expone el control imperativo usado por el catálogo. */
class GuitarIconState internal constructor() {
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
fun rememberGuitarIconState(): GuitarIconState = remember { GuitarIconState() }

private val GuitarIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M11.9 12.1 L16.414 7.586"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, 0.5f, -0.4f, 0.25f, 0f), floatArrayOf(0f, 0.2f, 0.48f, 0.74f, 1.0f), 1.0f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -0.5f, 0.4f, -0.25f, 0f), floatArrayOf(0f, 0.2f, 0.48f, 0.74f, 1.0f), 1.0f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M20.1 2.3 A1 1 0 0 0 18.7 2.3 L17.586 3.414 A2 2 0 0 0 17 4.828 V6.172 A2 2 0 0 1 16.414 7.586 A2 2 0 0 1 17.828 7 H19.172 A2 2 0 0 0 20.586 6.414 L21.7 5.3 A1 1 0 0 0 21.7 3.9 Z"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M6 16 L8 18"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, 0.5f, -0.4f, 0.25f, 0f), floatArrayOf(0f, 0.2f, 0.48f, 0.74f, 1.0f), 1.0f, 0.08f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -0.5f, 0.4f, -0.25f, 0f), floatArrayOf(0f, 0.2f, 0.48f, 0.74f, 1.0f), 1.0f, 0.08f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M8.23 9.85 A3 3 0 0 1 11 8 A5 5 0 0 1 16 13 A3 3 0 0 1 14.15 15.77 L13.23 16.15 A2 2 0 0 0 12 18 A4 4 0 0 1 8 22 A6 6 0 0 1 2 16 A4 4 0 0 1 6 12 A2 2 0 0 0 7.85 10.77 Z"), null, false, true, 1.0f, 2.0f, null, 0f)
    )

private const val GuitarIconTotalSeconds: Float = 1.0800f

/** Conversión a Compose del icono animado `guitar` de animateicons/Lucide. */
@Composable
fun GuitarIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: GuitarIconState? = null,
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
                                    durationMillis = (1080 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * GuitarIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(GuitarIconShapes, seconds, resolvedColor)
        }
    }
}
