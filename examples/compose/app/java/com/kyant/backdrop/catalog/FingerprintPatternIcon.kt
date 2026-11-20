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

/** Estado del icono FingerprintPatternIcon: expone el control imperativo usado por el catálogo. */
class FingerprintPatternIconState internal constructor() {
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
fun rememberFingerprintPatternIconState(): FingerprintPatternIconState = remember { FingerprintPatternIconState() }

private val FingerprintPatternIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M12 10 A2 2 0 0 0 10 12 C10 13.02 9.9 14.51 9.74 16"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.1f, 0.98f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.45f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M14 13.12 C14 15.5 14 19.5 13 22"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.1f, 0.98f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.45f, 0.05f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M17.29 21.02 C17.41 20.42 17.72 18.72 17.79 18"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.06f, 0.98f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.45f, 0.15f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M2 12 A10 10 0 0 1 20 6"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.05f, 0.98f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.45f, 0.22f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M2 16 H2.01"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.05f, 0.98f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.45f, 0.25f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M21.8 16 C22 14 21.931 10.646 21.8 10"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.05f, 0.98f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.45f, 0.2f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M5 19.5 C5.5 18 6 15 6 12 A6 6 0 0 1 6.34 10"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.08f, 0.98f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.45f, 0.12f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M8.65 22 C8.86 21.34 9.1 20.68 9.22 20"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.06f, 0.98f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.45f, 0.15f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M9 6.8 A6 6 0 0 1 18 12 V14"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.09f, 0.98f, 1.0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.45f, 0.08f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val FingerprintPatternIconTotalSeconds: Float = 0.7000f

/** Conversión a Compose del icono animado `fingerprint-pattern` de animateicons/Lucide. */
@Composable
fun FingerprintPatternIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: FingerprintPatternIconState? = null,
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
        val seconds = phase.value * FingerprintPatternIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(FingerprintPatternIconShapes, seconds, resolvedColor)
        }
    }
}
