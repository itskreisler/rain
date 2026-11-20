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

/** Estado del icono CatIcon: expone el control imperativo usado por el catálogo. */
class CatIconState internal constructor() {
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
fun rememberCatIconState(): CatIconState = remember { CatIconState() }

private val CatIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 20.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -10.0f, 8.0f, -3.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M12 5 C12.67 5 13.35 5.09 14 5.26 C15.78 3.26 19.03 2.42 20.42 3 C21.82 3.58 20 10 20 10 C20.57 11.07 21 12.24 21 13.44 C21 17.9 16.97 21 12 21 S3 18 3 13.44 C3 12.19 3.5 11.04 4 10 C4 10 2.11 3.58 3.5 3 C4.89 2.42 8.22 3.23 10 5.23 A9.04 9.04 0 0 1 12 5 Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M8 14 V14.5"), LucideMotion(8.0f, 14.25f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 0.1f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.3f, 0.25f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M16 14 V14.5"), LucideMotion(16.0f, 14.25f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 0.1f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.3f, 0.25f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M11.25 16.25 H12.75 L12 17 L11.25 16.25 Z"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val CatIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `cat` de animateicons/Lucide. */
@Composable
fun CatIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: CatIconState? = null,
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
        val seconds = phase.value * CatIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(CatIconShapes, seconds, resolvedColor)
        }
    }
}
