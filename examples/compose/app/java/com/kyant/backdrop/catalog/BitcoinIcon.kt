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

/** Estado del icono BitcoinIcon: expone el control imperativo usado por el catálogo. */
class BitcoinIconState internal constructor() {
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
fun rememberBitcoinIconState(): BitcoinIconState = remember { BitcoinIconState() }

private val BitcoinIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.055f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.9f, 0f, LucideEase.InOut), "rotate" to lucideTrack(floatArrayOf(0f, -2.0f, 2.0f, 0f), floatArrayOf(0f, 0.3333f, 0.6667f, 1.0f), 0.9f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -1.0f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.9f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(null, null, false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M11.767 19.089 C16.691 19.957 17.907 13.064 12.983 12.195"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M10.551 19.089 L5.86 18.047"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M10.551 19.089 L10.204 21.059"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M12.114 12.195 C17.038 13.064 18.254 6.17 13.329 5.302"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M12.114 12.195 L8.174 11.501"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M13.329 5.301 L8.29 4.26"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14.198 5.302 L14.546 3.332"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M7.48 20.364 L10.606 2.637"), null, false, true, 1.0f, 2.0f, null, 0f))), LucideShape(lucidePath("M11.767 19.089 C16.691 19.957 17.907 13.064 12.983 12.195"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 1.0f), floatArrayOf(0f, 1.0f), 0.55f, 0.06f, LucideEase.InOut), "pathLength" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.55f, 0.06f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M10.551 19.089 L5.86 18.047"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 1.0f), floatArrayOf(0f, 1.0f), 0.45f, 0.12f, LucideEase.InOut), "pathLength" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.45f, 0.12f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M10.551 19.089 L10.204 21.059"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 1.0f), floatArrayOf(0f, 1.0f), 0.4f, 0.18f, LucideEase.InOut), "pathLength" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.4f, 0.18f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M12.114 12.195 C17.038 13.064 18.254 6.17 13.329 5.302"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 1.0f), floatArrayOf(0f, 1.0f), 0.6f, 0.26f, LucideEase.InOut), "pathLength" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.6f, 0.26f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M12.114 12.195 L8.174 11.501"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 1.0f), floatArrayOf(0f, 1.0f), 0.45f, 0.32f, LucideEase.InOut), "pathLength" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.45f, 0.32f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M13.329 5.301 L8.29 4.26"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 1.0f), floatArrayOf(0f, 1.0f), 0.45f, 0.4f, LucideEase.InOut), "pathLength" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.45f, 0.4f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14.198 5.302 L14.546 3.332"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 1.0f), floatArrayOf(0f, 1.0f), 0.4f, 0.46f, LucideEase.InOut), "pathLength" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.4f, 0.46f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M7.48 20.364 L10.606 2.637"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 1.0f), floatArrayOf(0f, 1.0f), 0.75f, 0.54f, LucideEase.InOut), "pathLength" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.75f, 0.54f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M11.3 12.2A0.9 0.9 0 1 0 13.1 12.2A0.9 0.9 0 1 0 11.3 12.2Z"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(0.6f, 1.25f, 0.6f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.28f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(0f, 0.9f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.28f, LucideEase.Out))), true, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M9.8 19.1A0.8 0.8 0 1 0 11.4 19.1A0.8 0.8 0 1 0 9.8 19.1Z"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(0.6f, 1.25f, 0.6f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.16f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(0f, 0.9f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.16f, LucideEase.Out))), true, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M13.6 5.3A0.7 0.7 0 1 0 15 5.3A0.7 0.7 0 1 0 13.6 5.3Z"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(0.6f, 1.25f, 0.6f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.48f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(0f, 0.9f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.48f, LucideEase.Out))), true, true, 1.0f, 2.0f, null, 0f)))
    )

private const val BitcoinIconTotalSeconds: Float = 1.2900f

/** Conversión a Compose del icono animado `bitcoin` de animateicons/Lucide. */
@Composable
fun BitcoinIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: BitcoinIconState? = null,
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
                                    durationMillis = (1290 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * BitcoinIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(BitcoinIconShapes, seconds, resolvedColor)
        }
    }
}
