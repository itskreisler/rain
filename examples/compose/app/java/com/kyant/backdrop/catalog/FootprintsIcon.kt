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

/** Estado del icono FootprintsIcon: expone el control imperativo usado por el catálogo. */
class FootprintsIconState internal constructor() {
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
fun rememberFootprintsIconState(): FootprintsIconState = remember { FootprintsIconState() }

private val FootprintsIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M4 16 V13.62 C4 11.5 2.97 10.5 3 8 C3.03 5.28 4.49 2 7.5 2 C9.37 2 10 3.8 10 5.5 C10 8.61 8 11.16 8 14.18 V16 A2 2 0 1 1 4 16 Z"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0f, LucideEase.Out), "strokeDashoffset" to lucideTrack(floatArrayOf(40.0f, 0f), floatArrayOf(0f, 1.0f), 0.5f, 0f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(40.0f), 0f),
        LucideShape(lucidePath("M4 13 H8"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0.3f, LucideEase.Out), "strokeDashoffset" to lucideTrack(floatArrayOf(5.0f, 0f), floatArrayOf(0f, 1.0f), 0.25f, 0.3f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(5.0f), 0f),
        LucideShape(lucidePath("M20 20 V17.62 C20 15.5 21.03 14.5 21 12 C20.97 9.28 19.51 6 16.5 6 C14.63 6 14 7.8 14 9.5 C14 12.61 16 15.16 16 18.18 V20 A2 2 0 1 0 20 20 Z"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0.3f, LucideEase.Out), "strokeDashoffset" to lucideTrack(floatArrayOf(40.0f, 0f), floatArrayOf(0f, 1.0f), 0.5f, 0.3f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(40.0f), 0f),
        LucideShape(lucidePath("M16 17 H20"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0.6f, LucideEase.Out), "strokeDashoffset" to lucideTrack(floatArrayOf(5.0f, 0f), floatArrayOf(0f, 1.0f), 0.25f, 0.6f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(5.0f), 0f)
    )

private const val FootprintsIconTotalSeconds: Float = 0.8500f

/** Conversión a Compose del icono animado `footprints` de animateicons/Lucide. */
@Composable
fun FootprintsIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: FootprintsIconState? = null,
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
                                    durationMillis = (850 * duration.coerceAtLeast(0.1f)).toInt(),
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
            if (color == Color.Unspecified) Color(0xFFFF9800) else color
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
        val seconds = phase.value * FootprintsIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(FootprintsIconShapes, seconds, resolvedColor)
        }
    }
}
