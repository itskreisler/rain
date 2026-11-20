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

/** Estado del icono GamepadIcon: expone el control imperativo usado por el catálogo. */
class GamepadIconState internal constructor() {
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
fun rememberGamepadIconState(): GamepadIconState = remember { GamepadIconState() }

private val GamepadIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -4.0f, 4.0f, -3.0f, 2.0f, 0f), floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M17.32 5 H6.68 A4 4 0 0 0 2.702 8.59 C2.696 8.642 2.692 8.691 2.685 8.742 C2.604 9.416 2 14.456 2 16 A3 3 0 0 0 5 19 C6 19 6.5 18.5 7 18 L8.414 16.586 A2 2 0 0 1 9.828 16 H14.172 A2 2 0 0 1 15.586 16.586 L17 18 C17.5 18.5 18 19 19 19 A3 3 0 0 0 22 16 C22 14.455 21.396 9.416 21.315 8.742 C21.308 8.692 21.304 8.642 21.298 8.591 A4 4 0 0 0 17.32 5 Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(null, LucideMotion(8.0f, 11.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.7f, 1.08f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.5f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M6 11L10 11"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M8 9L8 13"), null, false, true, 1.0f, 2.0f, null, 0f))), LucideShape(lucidePath("M15 12L15.01 12"), LucideMotion(15.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 2.0f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.3f, 0.15f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M18 10L18.01 10"), LucideMotion(18.0f, 10.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 2.0f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.3f, 0.33f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val GamepadIconTotalSeconds: Float = 0.6300f

/** Conversión a Compose del icono animado `gamepad` de animateicons/Lucide. */
@Composable
fun GamepadIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: GamepadIconState? = null,
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
                                    durationMillis = (630 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * GamepadIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(GamepadIconShapes, seconds, resolvedColor)
        }
    }
}
