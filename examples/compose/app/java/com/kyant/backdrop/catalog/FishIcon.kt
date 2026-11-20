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

/** Estado del icono FishIcon: expone el control imperativo usado por el catálogo. */
class FishIconState internal constructor() {
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
fun rememberFishIconState(): FishIconState = remember { FishIconState() }

private val FishIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("x" to lucideTrack(floatArrayOf(0f, 1.0f, 0.2f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.7f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, 0f, 0f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M6.5 12 C7.44 8.54 11.44 6 15 6 C18.56 6 21.06 8.54 22 12 C21.06 15.47 18.56 18 15 18 S7.44 15.47 6.5 12 Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M18 12 V12.5"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M16 17.93 A9.77 9.77 0 0 1 16 6.07"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M10.46 7.26 C10.2 5.88 9.17 4.24 8 3 H13.8 A2 2 0 0 1 15.78 4.67 L16.01 6.07"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M16.01 17.93 L15.78 19.33 A2 2 0 0 1 13.8 21 H9.5 A5.96 5.96 0 0 0 10.99 17.02"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M7 10.67 C7 8 5.58 5.97 2.73 5.5 C1.73 7 1.73 10.5 2.96 12 C1.72 13.5 1.72 17 2.73 18.5 C5.58 18.03 7 16 7 13.33"), LucideMotion(7.0f, 12.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -14.0f, 10.0f, -5.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val FishIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `fish` de animateicons/Lucide. */
@Composable
fun FishIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: FishIconState? = null,
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
        val seconds = phase.value * FishIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(FishIconShapes, seconds, resolvedColor)
        }
    }
}
