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

/** Estado del icono HouseCogIcon: expone el control imperativo usado por el catálogo. */
class HouseCogIconState internal constructor() {
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
fun rememberHouseCogIconState(): HouseCogIconState = remember { HouseCogIconState() }

private val HouseCogIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 21.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.8f, 1.06f, 1.0f), floatArrayOf(0f, 0.35f, 0.75f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M10.584 21 H5 A2 2 0 0 1 3 19 V10 A2 2 0 0 1 3.709 8.473 L10.709 2.473 A2 2 0 0 1 13.291 2.473 L20.291 8.473 A2 2 0 0 1 21 10.001 V10.584"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14 12 H10 A1 1 0 0 0 9 13 V21"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0.35f, LucideEase.InOut), "strokeDashoffset" to lucideTrack(floatArrayOf(15.0f, 0f), floatArrayOf(0f, 1.0f), 0.45f, 0.35f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(15.0f), 0f))),
        LucideShape(null, LucideMotion(18.0f, 18.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, 360.0f), floatArrayOf(0f, 1.0f), 0.9f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M15 18A3 3 0 1 0 21 18A3 3 0 1 0 15 18Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14.305 19.53 L15.228 19.148"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M15.229 16.852 L14.305 16.469"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M16.852 15.228 L16.469 14.305"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M16.852 20.773 L16.469 21.697"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M19.148 15.228 L19.531 14.305"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M19.53 21.697 L19.148 20.773"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M20.773 16.852 L21.695 16.469"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M20.773 19.148 L21.695 19.531"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val HouseCogIconTotalSeconds: Float = 0.9000f

/** Conversión a Compose del icono animado `house-cog` de animateicons/Lucide. */
@Composable
fun HouseCogIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: HouseCogIconState? = null,
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
        val seconds = phase.value * HouseCogIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(HouseCogIconShapes, seconds, resolvedColor)
        }
    }
}
