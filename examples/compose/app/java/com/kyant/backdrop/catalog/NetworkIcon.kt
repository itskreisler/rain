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

/** Estado del icono NetworkIcon: expone el control imperativo usado por el catálogo. */
class NetworkIconState internal constructor() {
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
fun rememberNetworkIconState(): NetworkIconState = remember { NetworkIconState() }

private val NetworkIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M17 16H21A1 1 0 0 1 22 17V21A1 1 0 0 1 21 22H17A1 1 0 0 1 16 21V17A1 1 0 0 1 17 16Z"), LucideMotion(19.0f, 19.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.2f, 0.96f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.4f, 0.55f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M3 16H7A1 1 0 0 1 8 17V21A1 1 0 0 1 7 22H3A1 1 0 0 1 2 21V17A1 1 0 0 1 3 16Z"), LucideMotion(5.0f, 19.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.2f, 0.96f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.4f, 0.55f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M10 2H14A1 1 0 0 1 15 3V7A1 1 0 0 1 14 8H10A1 1 0 0 1 9 7V3A1 1 0 0 1 10 2Z"), LucideMotion(12.0f, 5.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.2f, 0.96f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.4f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M5 16 V13 A1 1 0 0 1 6 12 H18 A1 1 0 0 1 19 13 V16"), LucideMotion(12.0f, 12.0f, mapOf("scaleX" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.3f, 0.3f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0.3f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M12 12 V8"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0.1f, LucideEase.InOut), "strokeDashoffset" to lucideTrack(floatArrayOf(-5.0f, 0f), floatArrayOf(0f, 1.0f), 0.25f, 0.1f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(5.0f), 0f)
    )

private const val NetworkIconTotalSeconds: Float = 0.9500f

/** Conversión a Compose del icono animado `network` de animateicons/Lucide. */
@Composable
fun NetworkIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: NetworkIconState? = null,
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
                                    durationMillis = (950 * duration.coerceAtLeast(0.1f)).toInt(),
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
            if (color == Color.Unspecified) Color(0xFFFF5722) else color
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
        val seconds = phase.value * NetworkIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(NetworkIconShapes, seconds, resolvedColor)
        }
    }
}
