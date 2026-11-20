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

/** Estado del icono PaintBucketIcon: expone el control imperativo usado por el catálogo. */
class PaintBucketIconState internal constructor() {
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
fun rememberPaintBucketIconState(): PaintBucketIconState = remember { PaintBucketIconState() }

private val PaintBucketIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(11.0f, 11.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, 10.0f, -3.0f, 0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M11 7 L6 2"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M18.992 12 H2.041"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M8.5 4.5 L10.648 2.352 A1.205 1.205 0 0 1 12.352 2.352 L19.648 9.648 A1.205 1.205 0 0 1 19.648 11.352 L12.056 18.944 A3.615 3.615 0 0 1 6.944 18.944 L3.056 15.056 A3.615 3.615 0 0 1 3.056 9.944 L5.67 7.33"), null, false, true, 1.0f, 2.0f, null, 0f))),
        LucideShape(lucidePath("M21.145 18.38 A3.34 3.34 0 0 1 20 16.5 A3.3 3.3 0 0 1 18.855 18.38 C18.28 18.84 18 19.4 18 19.975 A2 2 0 0 0 20 22 A2 2 0 0 0 22 19.975 C22 19.395 21.715 18.845 21.145 18.38"), LucideMotion(20.0f, 19.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.2f, 0.92f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.5f, 0.2f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val PaintBucketIconTotalSeconds: Float = 0.7000f

/** Conversión a Compose del icono animado `paint-bucket` de animateicons/Lucide. */
@Composable
fun PaintBucketIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: PaintBucketIconState? = null,
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
        val seconds = phase.value * PaintBucketIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(PaintBucketIconShapes, seconds, resolvedColor)
        }
    }
}
