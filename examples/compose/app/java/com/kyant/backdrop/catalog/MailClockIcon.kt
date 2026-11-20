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

/** Estado del icono MailClockIcon: expone el control imperativo usado por el catálogo. */
class MailClockIconState internal constructor() {
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
fun rememberMailClockIconState(): MailClockIconState = remember { MailClockIconState() }

private val MailClockIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M22 7 L21.241 7.484"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M7.605 10.567 L2 7"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M6.835 20 H4 A2 2 0 0 1 2 18 V6 A2 2 0 0 1 4 4 H20 A2 2 0 0 1 22 6 V8"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M10 16A6 6 0 1 0 22 16A6 6 0 1 0 10 16Z"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M16 14 V16.2"), LucideMotion(16.0f, 16.2f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, 98.0f, 90.0f, 90.0f, 188.0f, 180.0f, 180.0f, 278.0f, 270.0f, 270.0f, 368.0f, 360.0f, 360.0f), floatArrayOf(0f, 0.12f, 0.17f, 0.25f, 0.37f, 0.42f, 0.5f, 0.62f, 0.67f, 0.75f, 0.87f, 0.92f, 1.0f), 0.9f, 0f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M16 16.2 L17.6 17.2"), null, false, true, 1.0f, 2.0f, null, 0f)
    )

private const val MailClockIconTotalSeconds: Float = 0.9000f

/** Conversión a Compose del icono animado `mail-clock` de animateicons/Lucide. */
@Composable
fun MailClockIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: MailClockIconState? = null,
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
            if (color == Color.Unspecified) Color(0xFF00BCD4) else color
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
        val seconds = phase.value * MailClockIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(MailClockIconShapes, seconds, resolvedColor)
        }
    }
}
