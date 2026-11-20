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

/** Estado del icono ServerOffIcon: expone el control imperativo usado por el catálogo. */
class ServerOffIconState internal constructor() {
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
fun rememberServerOffIconState(): ServerOffIconState = remember { ServerOffIconState() }

private val ServerOffIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.4f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(null, LucideMotion(12.0f, 6.0f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 0.92f, 1.02f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.5f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M7 2 H20 A2 2 0 0 1 22 4 V8 A2 2 0 0 1 20 10 H15"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M10 10 L2.5 2.5 C2 2 2 2.5 2 5 V8 A2 2 0 0 0 4 10 H10 Z"), null, false, true, 1.0f, 2.0f, null, 0f))), LucideShape(null, LucideMotion(12.0f, 18.0f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 0.92f, 1.02f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.5f, 0.15f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M22 17 V16 A2 2 0 0 0 20 14 H19"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M4 14 A2 2 0 0 0 2 16 V20 A2 2 0 0 0 4 22 H20.5 L21.5 21.5 L22 22 L14 14 H4 Z"), null, false, true, 1.0f, 2.0f, null, 0f))), LucideShape(lucidePath("M6 18 H6.01"), LucideMotion(6.0f, 18.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.6f, 1.2f, 1.0f), floatArrayOf(0f, 0.3333f, 0.6667f, 1.0f), 0.45f, 0.25f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.15f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.45f, 0.25f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f))),
        LucideShape(lucidePath("M2 2 L22 22"), LucideMotion(12.0f, 12.0f, mapOf("strokeDashoffset" to lucideTrack(floatArrayOf(31.0f, 0f), floatArrayOf(0f, 1.0f), 0.45f, 0.1f, LucideEase.InOut))), false, true, 1.0f, 2.0f, floatArrayOf(30.0f, 200.0f), 0f)
    )

private const val ServerOffIconTotalSeconds: Float = 0.7000f

/** Conversión a Compose del icono animado `server-off` de animateicons/Lucide. */
@Composable
fun ServerOffIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: ServerOffIconState? = null,
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
        val seconds = phase.value * ServerOffIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(ServerOffIconShapes, seconds, resolvedColor)
        }
    }
}
