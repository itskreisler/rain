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

/** Estado del icono SmartphoneNfcIcon: expone el control imperativo usado por el catálogo. */
class SmartphoneNfcIconState internal constructor() {
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
fun rememberSmartphoneNfcIconState(): SmartphoneNfcIconState = remember { SmartphoneNfcIconState() }

private val SmartphoneNfcIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M3 6H8A1 1 0 0 1 9 7V17A1 1 0 0 1 8 18H3A1 1 0 0 1 2 17V7A1 1 0 0 1 3 6Z"), LucideMotion(5.5f, 18.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, 8.0f, -2.0f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M13 8.32 A7.43 7.43 0 0 1 13 15.68"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, 1.6f, -0.3f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M16.46 6.21 A11.76 11.76 0 0 1 16.46 17.79"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, 1.6f, -0.3f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0.09f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M19.91 4.1 A15.91 15.91 0 0 1 19.92 19.9"), LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, 1.6f, -0.3f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0.18f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val SmartphoneNfcIconTotalSeconds: Float = 0.7800f

/** Conversión a Compose del icono animado `smartphone-nfc` de animateicons/Lucide. */
@Composable
fun SmartphoneNfcIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: SmartphoneNfcIconState? = null,
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
                                    durationMillis = (780 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * SmartphoneNfcIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(SmartphoneNfcIconShapes, seconds, resolvedColor)
        }
    }
}
