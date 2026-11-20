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

/** Estado del icono AtomIcon: expone el control imperativo usado por el catálogo. */
class AtomIconState internal constructor() {
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
fun rememberAtomIconState(): AtomIconState = remember { AtomIconState() }

private val AtomIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M11 12A1 1 0 1 0 13 12A1 1 0 1 0 11 12Z"), LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.7f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.5f, 0.2f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.9f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.9f, 0f, LucideEase.InOut), "rotate" to lucideTrack(floatArrayOf(0f, 180.0f), floatArrayOf(0f, 1.0f), 0.9f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M20.2 20.2 C22.24 18.17 20.22 12.84 15.7 8.3 C11.16 3.78 5.83 1.76 3.8 3.8 C1.76 5.83 3.78 11.16 8.3 15.7 C12.84 20.22 18.17 22.24 20.2 20.2 Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M15.7 15.7 C20.22 11.16 22.24 5.83 20.2 3.8 C18.17 1.76 12.84 3.78 8.3 8.3 C3.78 12.84 1.76 18.17 3.8 20.2 C5.83 22.24 11.16 20.22 15.7 15.7 Z"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val AtomIconTotalSeconds: Float = 0.9000f

/** Conversión a Compose del icono animado `atom` de animateicons/Lucide. */
@Composable
fun AtomIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: AtomIconState? = null,
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
            if (color == Color.Unspecified) Color(0xFF2196F3) else color
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
        val seconds = phase.value * AtomIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(AtomIconShapes, seconds, resolvedColor)
        }
    }
}
