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

/** Estado del icono PaletteIcon: expone el control imperativo usado por el catálogo. */
class PaletteIconState internal constructor() {
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
fun rememberPaletteIconState(): PaletteIconState = remember { PaletteIconState() }

private val PaletteIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -10.0f, 8.0f, -4.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M12 22 A1 1 0 0 1 12 2 A10 9 0 0 1 22 11 A5 5 0 0 1 17 16 H14.75 A1.75 1.75 0 0 0 13.35 18.8 L13.65 19.2 A1.75 1.75 0 0 1 12.25 22 Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M6 12.5A0.5 0.5 0 1 0 7 12.5A0.5 0.5 0 1 0 6 12.5Z"), LucideMotion(6.5f, 12.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0f, 1.8f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0f, LucideEase.Out))), true, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M8 7.5A0.5 0.5 0 1 0 9 7.5A0.5 0.5 0 1 0 8 7.5Z"), LucideMotion(8.5f, 7.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0f, 1.8f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0.08f, LucideEase.Out))), true, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M13 6.5A0.5 0.5 0 1 0 14 6.5A0.5 0.5 0 1 0 13 6.5Z"), LucideMotion(13.5f, 6.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0f, 1.8f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0.16f, LucideEase.Out))), true, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M17 10.5A0.5 0.5 0 1 0 18 10.5A0.5 0.5 0 1 0 17 10.5Z"), LucideMotion(17.5f, 10.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0f, 1.8f, 1.0f), floatArrayOf(0f, 0.2f, 0.6f, 1.0f), 0.4f, 0.24f, LucideEase.Out))), true, true, 1.0f, 2.0f, null, 0f)))
    )

private const val PaletteIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `palette` de animateicons/Lucide. */
@Composable
fun PaletteIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: PaletteIconState? = null,
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
        val seconds = phase.value * PaletteIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(PaletteIconShapes, seconds, resolvedColor)
        }
    }
}
