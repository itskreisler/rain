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

/** Estado del icono AudioLinesXIcon: expone el control imperativo usado por el catálogo. */
class AudioLinesXIconState internal constructor() {
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
fun rememberAudioLinesXIconState(): AudioLinesXIconState = remember { AudioLinesXIconState() }

private val AudioLinesXIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M2 10 V13"), LucideMotion(2.0f, 11.5f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 2.4f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M6 6 V17"), LucideMotion(6.0f, 11.5f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 0.55f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0.05f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M10 3 V21"), LucideMotion(10.0f, 12.0f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 0.55f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0.1f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M14 8 V14.35"), LucideMotion(14.0f, 11.5f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 1.7f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0.15f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M18 5 V13.1"), LucideMotion(18.0f, 11.5f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 0.6f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0.2f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M22 10 V13"), LucideMotion(22.0f, 11.5f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 2.4f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.6f, 0.25f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(null, LucideMotion(19.5f, 19.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.5f, 0f, LucideEase.InOut), "rotate" to lucideTrack(floatArrayOf(0f, 90.0f), floatArrayOf(0f, 1.0f), 0.5f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M17 17 L22 22"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M22 17 L17 22"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val AudioLinesXIconTotalSeconds: Float = 0.8500f

/** Conversión a Compose del icono animado `audio-lines-x` de animateicons/Lucide. */
@Composable
fun AudioLinesXIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: AudioLinesXIconState? = null,
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
            if (color == Color.Unspecified) Color(0xFF4CAF50) else color
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
        val seconds = phase.value * AudioLinesXIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(AudioLinesXIconShapes, seconds, resolvedColor)
        }
    }
}
