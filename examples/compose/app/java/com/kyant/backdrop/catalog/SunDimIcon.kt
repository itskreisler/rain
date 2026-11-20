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

/** Estado del icono SunDimIcon: expone el control imperativo usado por el catálogo. */
class SunDimIconState internal constructor() {
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
fun rememberSunDimIconState(): SunDimIconState = remember { SunDimIconState() }

private val SunDimIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M8 12A4 4 0 1 0 16 12A4 4 0 1 0 8 12Z"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.86f, 1.08f, 1.0f), floatArrayOf(0f, 0.3f, 0.7f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M12 4 H12.01"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M20 12 H20.01"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.08f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M12 20 H12.01"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.16f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M4 12 H4.01"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.24f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M17.657 6.343 H17.667"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.04f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M17.657 17.657 H17.667"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.12f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M6.343 17.657 H6.353"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.2f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M6.343 6.343 H6.353"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.28f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val SunDimIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `sun-dim` de animateicons/Lucide. */
@Composable
fun SunDimIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: SunDimIconState? = null,
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
        val seconds = phase.value * SunDimIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(SunDimIconShapes, seconds, resolvedColor)
        }
    }
}
