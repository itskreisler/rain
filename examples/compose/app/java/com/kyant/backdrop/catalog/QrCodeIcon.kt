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

/** Estado del icono QrCodeIcon: expone el control imperativo usado por el catálogo. */
class QrCodeIconState internal constructor() {
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
fun rememberQrCodeIconState(): QrCodeIconState = remember { QrCodeIconState() }

private val QrCodeIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M4 3H7A1 1 0 0 1 8 4V7A1 1 0 0 1 7 8H4A1 1 0 0 1 3 7V4A1 1 0 0 1 4 3Z"), LucideMotion(5.5f, 5.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.12f, 0.96f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.5f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M17 3H20A1 1 0 0 1 21 4V7A1 1 0 0 1 20 8H17A1 1 0 0 1 16 7V4A1 1 0 0 1 17 3Z"), LucideMotion(18.5f, 5.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.12f, 0.96f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.5f, 0.06f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M4 16H7A1 1 0 0 1 8 17V20A1 1 0 0 1 7 21H4A1 1 0 0 1 3 20V17A1 1 0 0 1 4 16Z"), LucideMotion(5.5f, 18.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.12f, 0.96f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.5f, 0.12f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M21 16 H18 A2 2 0 0 0 16 18 V21"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M12 7 V10 A2 2 0 0 1 10 12 H7"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M12 3 H12.01"), LucideMotion(12.0f, 3.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.6f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.1f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M3 12 H3.01"), LucideMotion(3.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.6f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.14f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M16 12 H17"), LucideMotion(16.5f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.6f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.18f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M21 12 V12.01"), LucideMotion(21.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.6f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.22f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M12 16 V16.01"), LucideMotion(12.0f, 16.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.6f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.26f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M12 21 V20"), LucideMotion(12.0f, 20.5f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.6f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.3f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M21 21 V21.01"), LucideMotion(21.0f, 21.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.6f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.35f, 0.34f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val QrCodeIconTotalSeconds: Float = 0.6900f

/** Conversión a Compose del icono animado `qr-code` de animateicons/Lucide. */
@Composable
fun QrCodeIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: QrCodeIconState? = null,
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
                                    durationMillis = (690 * duration.coerceAtLeast(0.1f)).toInt(),
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
            if (color == Color.Unspecified) Color(0xFFE91E63) else color
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
        val seconds = phase.value * QrCodeIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(QrCodeIconShapes, seconds, resolvedColor)
        }
    }
}
