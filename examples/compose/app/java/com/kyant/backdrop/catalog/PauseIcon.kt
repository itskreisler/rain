package com.kyant.backdrop.catalog

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** Manejador equivalente al `PauseIconHandle` del componente React. */
class PauseIconState internal constructor() {
    internal var startAction: (() -> Unit)? = null
    internal var stopAction: (() -> Unit)? = null
    internal var endAction: (() -> Unit)? = null

    fun startAnimation() {
        startAction?.invoke()
    }

    fun stopAnimation() {
        stopAction?.invoke()
    }

    /** Función a ejecutar cuando la animación termina sin cancelarse. */
    var onAnimationEnd: (() -> Unit)? = null
        set(value) {
            endAction = value
        }
}

@Composable
fun rememberPauseIconState(): PauseIconState = remember { PauseIconState() }

// scaleY: [1, 0.55, 1] con easeInOut, igual que barVariants() en React.
private val PauseBarFrames =
    listOf(
        IconKeyframe(0f, 1f),
        IconKeyframe(0.5f, 0.55f),
        IconKeyframe(1f, 1f),
    )

/** Conversión del `PauseIcon` de React (motion/react) a Compose. */
@Composable
fun PauseIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: PauseIconState? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    // Una fase por barra: la derecha arranca con 0.15 * duration de retraso.
    val nearPhase = remember { Animatable(0f) }
    val farPhase = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val motionScale = scope.coroutineContext[MotionDurationScale]?.scaleFactor ?: 1f

    var animationJob by remember { mutableStateOf<Job?>(null) }

    val start: () -> Unit =
        remember(motionScale, duration) {
            {
                if (motionScale <= 0f) {
                    animationJob?.cancel()
                    animationJob =
                        scope.launch {
                            nearPhase.snapTo(0f)
                            farPhase.snapTo(0f)
                            state?.endAction?.invoke()
                        }
                    return@remember
                }
                animationJob?.cancel()
                animationJob =
                    scope.launch {
                        nearPhase.snapTo(0f)
                        farPhase.snapTo(0f)
                        val millis = (500 * duration.coerceAtLeast(0.1f)).toInt()
                        coroutineScope {
                            launch {
                                nearPhase.animateTo(
                                    targetValue = 1f,
                                    animationSpec = tween(durationMillis = millis, easing = LinearEasing),
                                )
                            }
                            launch {
                                delay((150 * duration.coerceAtLeast(0.1f)).toLong())
                                farPhase.animateTo(
                                    targetValue = 1f,
                                    animationSpec = tween(durationMillis = millis, easing = LinearEasing),
                                )
                            }
                        }
                        nearPhase.snapTo(0f)
                        farPhase.snapTo(0f)
                        state?.endAction?.invoke()
                    }
            }
        }

    val stop: () -> Unit =
        remember {
            {
                animationJob?.cancel()
                animationJob =
                    scope.launch {
                        nearPhase.snapTo(0f)
                        farPhase.snapTo(0f)
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
            }
            animationJob?.cancel()
            animationJob = null
        }
    }

    LaunchedEffect(isHovered, isAnimated) {
        if (state != null) return@LaunchedEffect
        if (isHovered && isAnimated && motionScale > 0f) {
            start()
        } else if (!isHovered) {
            stop()
        }
    }

    val resolvedColor =
        remember(color) {
            if (color == Color.Unspecified) Color(0xFF2196F3) else color
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
        val sx = this.size.width / 24f
        val sy = this.size.height / 24f
        val near = sampleKeyframes(PauseBarFrames, nearPhase.value)
        val far = sampleKeyframes(PauseBarFrames, farPhase.value)

        scale(sx, sy, pivot = Offset.Zero) {
            scale(1f, near, pivot = Offset(7.5f, 12f)) {
                drawRoundRect(
                    color = resolvedColor,
                    topLeft = Offset(5f, 3f),
                    size = Size(5f, 18f),
                    cornerRadius = CornerRadius(1f),
                    style = Stroke(width = 2f),
                )
            }
            scale(1f, far, pivot = Offset(16.5f, 12f)) {
                drawRoundRect(
                    color = resolvedColor,
                    topLeft = Offset(14f, 3f),
                    size = Size(5f, 18f),
                    cornerRadius = CornerRadius(1f),
                    style = Stroke(width = 2f),
                )
            }
        }
    }
}
