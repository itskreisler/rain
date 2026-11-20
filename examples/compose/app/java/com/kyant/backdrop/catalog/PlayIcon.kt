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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/** Manejador equivalente al `PlayIconHandle` del componente React. */
class PlayIconState internal constructor() {
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
fun rememberPlayIconState(): PlayIconState = remember { PlayIconState() }

private val PlayPath: Path =
    PathParser()
        .parsePathString(
            "M5 5a2 2 0 0 1 3.008-1.728l11.997 6.998a2 2 0 0 1 .003 3.458l-12 7A2 2 0 0 1 5 19z",
        ).toPath()

// x: [0, 3, -0.6, 0] con times [0, 0.4, 0.75, 1]
private val PlayOffsetFrames =
    listOf(
        IconKeyframe(0f, 0f),
        IconKeyframe(0.4f, 3f),
        IconKeyframe(0.75f, -0.6f),
        IconKeyframe(1f, 0f),
    )

// scale: [1, 1.1, 1, 1] con los mismos times
private val PlayScaleFrames =
    listOf(
        IconKeyframe(0f, 1f),
        IconKeyframe(0.4f, 1.1f),
        IconKeyframe(0.75f, 1f),
        IconKeyframe(1f, 1f),
    )

/** Conversión del `PlayIcon` de React (motion/react) a Compose. */
@Composable
fun PlayIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: PlayIconState? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    // Fase normalizada 0..1. El valor real se deriva de los keyframes al dibujar,
    // para reproducir los `times` de Motion sin depender de Animatable por propiedad.
    val phase = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val motionScale = scope.coroutineContext[MotionDurationScale]?.scaleFactor ?: 1f

    var animationJob by remember { mutableStateOf<Job?>(null) }

    val start: () -> Unit =
        remember(motionScale, duration) {
            {
                // useReducedMotion(): en React arranca directamente "normal".
                // Se notifica el final para que un toggle pueda cambiar de icono igualmente.
                if (motionScale <= 0f) {
                    animationJob?.cancel()
                    animationJob =
                        scope.launch {
                            phase.snapTo(0f)
                            state?.endAction?.invoke()
                        }
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
                                    durationMillis = (600 * duration.coerceAtLeast(0.1f)).toInt(),
                                    easing = LinearEasing,
                                ),
                        )
                        phase.snapTo(0f)
                        // animateTo solo vuelve aquí si no se canceló: es el fin de la animación.
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
                        phase.snapTo(0f)
                    }
            }
        }

    // Equivale a useImperativeHandle: con state externo el componente está controlado.
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

    // handleEnter / handleLeave: solo animan en hover cuando no hay control externo.
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
        val p = phase.value
        val sx = this.size.width / 24f
        val sy = this.size.height / 24f
        val dx = sampleKeyframes(PlayOffsetFrames, p) * sx
        val s = sampleKeyframes(PlayScaleFrames, p)

        translate(dx, 0f) {
            scale(sx, sy, pivot = Offset.Zero) {
                scale(s, s, pivot = Offset(12f, 12f)) {
                    drawPath(
                        path = PlayPath,
                        color = resolvedColor,
                        style =
                            Stroke(
                                width = 2f,
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round,
                            ),
                    )
                }
            }
        }
    }
}
