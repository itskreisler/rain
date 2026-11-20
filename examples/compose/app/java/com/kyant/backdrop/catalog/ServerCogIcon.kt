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

/** Estado del icono ServerCogIcon: expone el control imperativo usado por el catálogo. */
class ServerCogIconState internal constructor() {
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
fun rememberServerCogIconState(): ServerCogIconState = remember { ServerCogIconState() }

private val ServerCogIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M4.5 10 H4 A2 2 0 0 1 2 8 V4 A2 2 0 0 1 4 2 H20 A2 2 0 0 1 22 4 V8 A2 2 0 0 1 20 10 H19.5"), LucideMotion(12.0f, 6.0f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 0.92f, 1.02f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.5f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M4.5 14 H4 A2 2 0 0 0 2 16 V20 A2 2 0 0 0 4 22 H20 A2 2 0 0 0 22 20 V16 A2 2 0 0 0 20 14 H19.5"), LucideMotion(12.0f, 18.0f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 0.92f, 1.02f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.5f, 0.15f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M6 6 H6.01"), LucideMotion(6.0f, 6.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.6f, 1.2f, 1.0f), floatArrayOf(0f, 0.3333f, 0.6667f, 1.0f), 0.45f, 0.1f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.15f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.45f, 0.1f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M6 18 H6.01"), LucideMotion(6.0f, 18.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.6f, 1.2f, 1.0f), floatArrayOf(0f, 0.3333f, 0.6667f, 1.0f), 0.45f, 0.25f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.15f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.45f, 0.25f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, 360.0f), floatArrayOf(0f, 1.0f), 0.9f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M10.852 14.772 L10.469 15.695"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M13.148 14.772 A3 3 0 1 0 10.852 9.228 L10.469 8.305"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M13.148 9.228 L13.531 8.305"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M13.53 15.696 L13.148 14.772 A3 3 0 1 1 10.852 9.228"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14.772 10.852 L15.695 10.469"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14.772 13.148 L15.695 13.531"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M9.228 10.852 L8.305 10.469"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M9.228 13.148 L8.305 13.531"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val ServerCogIconTotalSeconds: Float = 0.9000f

/** Conversión a Compose del icono animado `server-cog` de animateicons/Lucide. */
@Composable
fun ServerCogIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: ServerCogIconState? = null,
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
                                    durationMillis = (900 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * ServerCogIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(ServerCogIconShapes, seconds, resolvedColor)
        }
    }
}
