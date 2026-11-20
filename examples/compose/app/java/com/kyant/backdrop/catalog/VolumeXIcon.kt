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

/** Estado del icono VolumeXIcon: expone el control imperativo usado por el catálogo. */
class VolumeXIconState internal constructor() {
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
fun rememberVolumeXIconState(): VolumeXIconState = remember { VolumeXIconState() }

private val VolumeXIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M11 4.702 A0.705 0.705 0 0 0 9.797 4.204 L6.413 7.587 A1.4 1.4 0 0 1 5.416 8 H3 A1 1 0 0 0 2 9 V15 A1 1 0 0 0 3 16 H5.416 A1.4 1.4 0 0 1 6.413 16.413 L9.796 19.797 A0.705 0.705 0 0 0 11 19.298 Z"), LucideMotion(12.0f, 12.0f, mapOf("opacity" to lucideTrack(floatArrayOf(1.0f, 0.4f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M22 9L16 15"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(0.85f, 1.1f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.5f, 0.12f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0.12f, LucideEase.InOut), "strokeDashoffset" to lucideTrack(floatArrayOf(10.0f, 0f), floatArrayOf(0f, 1.0f), 0.45f, 0.12f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(10.0f), 0f),
        LucideShape(lucidePath("M16 9L22 15"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(0.85f, 1.1f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.5f, 0.22f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(0f, 1.0f), floatArrayOf(0f, 1.0f), 0.25f, 0.22f, LucideEase.InOut), "strokeDashoffset" to lucideTrack(floatArrayOf(10.0f, 0f), floatArrayOf(0f, 1.0f), 0.45f, 0.22f, LucideEase.Out))), false, true, 1.0f, 2.0f, floatArrayOf(10.0f), 0f)
    )

private const val VolumeXIconTotalSeconds: Float = 0.7200f

/** Conversión a Compose del icono animado `volume-x` de animateicons/Lucide. */
@Composable
fun VolumeXIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: VolumeXIconState? = null,
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
                                    durationMillis = (720 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * VolumeXIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(VolumeXIconShapes, seconds, resolvedColor)
        }
    }
}
