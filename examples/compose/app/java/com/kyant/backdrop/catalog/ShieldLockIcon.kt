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

/** Estado del icono ShieldLockIcon: expone el control imperativo usado por el catálogo. */
class ShieldLockIconState internal constructor() {
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
fun rememberShieldLockIconState(): ShieldLockIconState = remember { ShieldLockIconState() }

private val ShieldLockIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 22.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -7.0f, 4.0f, -1.5f, 0f), floatArrayOf(0f, 0.2f, 0.5f, 0.78f, 1.0f), 0.7f, 0f, LucideEase.InOut), "x" to lucideTrack(floatArrayOf(0f, -0.6f, 0.25f, -0.1f, 0f), floatArrayOf(0f, 0.2f, 0.5f, 0.78f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M20 9.807 V6 A1 1 0 0 0 19 5 C17 5 14.51 3.81 12.76 2.28 A1.17 1.17 0 0 0 11.24 2.28 C9.5 3.8 7 5 5 5 A1 1 0 0 0 4 6 V13 C4 16.88 6.107 19.254 9 20.796"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M19 17 V15 A2 2 0 0 0 15 15 V17"), LucideMotion(12.0f, 12.0f, mapOf("y" to lucideTrack(floatArrayOf(0f, -2.0f, 0.5f, 0f), floatArrayOf(0f, 0.45f, 0.72f, 1.0f), 0.7f, 0.1f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M14 17H20A1 1 0 0 1 21 18V21A1 1 0 0 1 20 22H14A1 1 0 0 1 13 21V18A1 1 0 0 1 14 17Z"), LucideMotion(17.0f, 22.0f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 1.0f, 0.88f, 1.0f), floatArrayOf(0f, 0.45f, 0.72f, 1.0f), 0.7f, 0.1f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val ShieldLockIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `shield-lock` de animateicons/Lucide. */
@Composable
fun ShieldLockIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: ShieldLockIconState? = null,
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
        val seconds = phase.value * ShieldLockIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(ShieldLockIconShapes, seconds, resolvedColor)
        }
    }
}
