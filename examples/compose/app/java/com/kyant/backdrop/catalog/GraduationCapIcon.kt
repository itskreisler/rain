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

/** Estado del icono GraduationCapIcon: expone el control imperativo usado por el catálogo. */
class GraduationCapIconState internal constructor() {
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
fun rememberGraduationCapIconState(): GraduationCapIconState = remember { GraduationCapIconState() }

private val GraduationCapIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, 6.0f, -2.0f, 0f, 0f), floatArrayOf(0f, 0.35f, 0.65f, 0.82f, 1.0f), 0.8f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -4.0f, 0f, -1.0f, 0f), floatArrayOf(0f, 0.35f, 0.65f, 0.82f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M21.42 10.922 A1 1 0 0 0 21.401 9.084 L12.83 5.18 A2 2 0 0 0 11.17 5.18 L2.6 9.08 A1 1 0 0 0 2.6 10.912 L11.17 14.82 A2 2 0 0 0 12.83 14.82 Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M22 10 V16"), LucideMotion(22.0f, 10.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, 18.0f, -5.0f, 10.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.9f, 0.1f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M6 12.5 V16 A6 3 0 0 0 18 16 V12.5"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val GraduationCapIconTotalSeconds: Float = 1.0000f

/** Conversión a Compose del icono animado `graduation-cap` de animateicons/Lucide. */
@Composable
fun GraduationCapIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: GraduationCapIconState? = null,
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
                                    durationMillis = (1000 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * GraduationCapIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(GraduationCapIconShapes, seconds, resolvedColor)
        }
    }
}
