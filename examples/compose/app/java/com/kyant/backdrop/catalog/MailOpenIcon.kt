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

/** Estado del icono MailOpenIcon: expone el control imperativo usado por el catálogo. */
class MailOpenIconState internal constructor() {
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
fun rememberMailOpenIconState(): MailOpenIconState = remember { MailOpenIconState() }

private val MailOpenIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, null, false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("y" to lucideTrack(floatArrayOf(-9.0f, 0f, 14.0f), floatArrayOf(0f, 0.4f, 1.0f), 0.8f, 0f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(0f, 1.0f, 1.0f), floatArrayOf(0f, 0.25f, 1.0f), 0.8f, 0f, LucideEase.Out))), false, true, 1.0f, 1.5f, null, 0f, listOf(LucideShape(lucidePath("M8 4H16A1 1 0 0 1 17 5V12.5A1 1 0 0 1 16 13.5H8A1 1 0 0 1 7 12.5V5A1 1 0 0 1 8 4Z"), null, false, true, 1.0f, 1.5f, null, 0f), LucideShape(lucidePath("M9.5 7.3 H14.5"), null, false, true, 1.0f, 1.5f, null, 0f), LucideShape(lucidePath("M9.5 10.3 H12.5"), null, false, true, 1.0f, 1.5f, null, 0f))))),
        LucideShape(lucidePath("M21.2 8.4 C21.7 8.78 22 9.37 22 10 V20 A2 2 0 0 1 20 22 H4 A2 2 0 0 1 2 20 V10 A2 2 0 0 1 2.8 8.4"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M22 10 L13.03 15.7 A1.94 1.94 0 0 1 10.97 15.7 L2 10"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M2.8 8.4 L10.8 2.4 A2 2 0 0 1 13.2 2.4 L21.2 8.4"), null, false, true, 1.0f, 2.0f, null, 0f)
    )

private const val MailOpenIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `mail-open` de animateicons/Lucide. */
@Composable
fun MailOpenIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: MailOpenIconState? = null,
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
        val seconds = phase.value * MailOpenIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(MailOpenIconShapes, seconds, resolvedColor)
        }
    }
}
