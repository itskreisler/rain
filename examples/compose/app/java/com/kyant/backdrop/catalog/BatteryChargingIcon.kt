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

/** Estado del icono BatteryChargingIcon: expone el control imperativo usado por el catálogo. */
class BatteryChargingIconState internal constructor() {
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
fun rememberBatteryChargingIconState(): BatteryChargingIconState = remember { BatteryChargingIconState() }

private val BatteryChargingIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(2.0f, 12.0f, mapOf("scaleX" to lucideTrack(floatArrayOf(1.0f, 0.92f, 1.02f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M11 7 L8 12 H12 L9 17"), LucideMotion(10.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.18f, 0.96f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.45f, 1.0f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14.856 6 H16 A2 2 0 0 1 18 8 V16 A2 2 0 0 1 16 18 H13.065"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M5.14 18 H4 A2 2 0 0 1 2 16 V8 A2 2 0 0 1 4 6 H6.936"), null, false, true, 1.0f, 2.0f, null, 0f))),
        LucideShape(lucidePath("M22 14 V10"), LucideMotion(22.0f, 12.0f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 1.5f, 1.0f, 1.0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0f, LucideEase.InOut), "x" to lucideTrack(floatArrayOf(0f, -1.3f, 0.3f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val BatteryChargingIconTotalSeconds: Float = 0.6000f

/** Conversión a Compose del icono animado `battery-charging` de animateicons/Lucide. */
@Composable
fun BatteryChargingIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: BatteryChargingIconState? = null,
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
        val seconds = phase.value * BatteryChargingIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(BatteryChargingIconShapes, seconds, resolvedColor)
        }
    }
}
