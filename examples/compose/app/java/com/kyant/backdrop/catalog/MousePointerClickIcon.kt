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

/** Estado del icono MousePointerClickIcon: expone el control imperativo usado por el catálogo. */
class MousePointerClickIconState internal constructor() {
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
fun rememberMousePointerClickIconState(): MousePointerClickIconState = remember { MousePointerClickIconState() }

private val MousePointerClickIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M14 4.1 L12 6"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.6f, 1.2f, 1.0f), floatArrayOf(0f, 0.3333f, 0.6667f, 1.0f), 0.6f, 0f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.5f, 1.0f, 0f, 1.0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.6f, 0f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M5.1 8 L2.2 7.2"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.6f, 1.2f, 1.0f), floatArrayOf(0f, 0.3333f, 0.6667f, 1.0f), 0.6f, 0f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.5f, 1.0f, 0f, 1.0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.6f, 0f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M6 12 L4.1 14"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.6f, 1.2f, 1.0f), floatArrayOf(0f, 0.3333f, 0.6667f, 1.0f), 0.6f, 0f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.5f, 1.0f, 0f, 1.0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.6f, 0f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M7.2 2.2 L8 5.1"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.6f, 1.2f, 1.0f), floatArrayOf(0f, 0.3333f, 0.6667f, 1.0f), 0.6f, 0f, LucideEase.Out), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.5f, 1.0f, 0f, 1.0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.6f, 0f, LucideEase.Out))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M9.037 9.69 A0.498 0.498 0 0 1 9.69 9.037 L20.69 13.537 A0.5 0.5 0 0 1 20.616 14.486 L16.267 15.527 A1 1 0 0 0 15.527 16.266 L14.487 20.616 A0.5 0.5 0 0 1 13.537 20.69 Z"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.95f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.5f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, 1.0f, 0f), floatArrayOf(0f, 0.5f, 1.0f), 0.5f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val MousePointerClickIconTotalSeconds: Float = 0.6000f

/** Conversión a Compose del icono animado `mouse-pointer-click` de animateicons/Lucide. */
@Composable
fun MousePointerClickIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: MousePointerClickIconState? = null,
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
                                    durationMillis = (600 * duration.coerceAtLeast(0.1f)).toInt(),
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
            if (color == Color.Unspecified) Color(0xFFFF9800) else color
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
        val seconds = phase.value * MousePointerClickIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(MousePointerClickIconShapes, seconds, resolvedColor)
        }
    }
}
