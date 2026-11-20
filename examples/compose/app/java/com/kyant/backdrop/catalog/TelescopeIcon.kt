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

/** Estado del icono TelescopeIcon: expone el control imperativo usado por el catálogo. */
class TelescopeIconState internal constructor() {
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
fun rememberTelescopeIconState(): TelescopeIconState = remember { TelescopeIconState() }

private val TelescopeIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 13.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -9.0f, 5.0f, -2.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M10.065 12.493 L3.885 13.811 A0.934 0.934 0 0 1 2.777 13.109 L2.24 10.959 A1.07 1.07 0 0 1 2.931 9.694 L16.435 5.254"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M13.56 11.747 L17.892 10.823"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M16.485 5.94 A2 2 0 0 1 17.94 3.515 L19.03 3.243 A1 1 0 0 1 20.242 3.97 L21.757 10.03 A1 1 0 0 1 21.03 11.243 L19.94 11.515 A2 2 0 0 1 17.515 10.06 Z"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M6.158 8.633 L7.272 13.089"), null, false, true, 1.0f, 2.0f, null, 0f))),
        LucideShape(lucidePath("M16 21 L12.895 14.79"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M8 21 L11.105 14.79"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M10 13A2 2 0 1 0 14 13A2 2 0 1 0 10 13Z"), null, false, true, 1.0f, 2.0f, null, 0f)
    )

private const val TelescopeIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `telescope` de animateicons/Lucide. */
@Composable
fun TelescopeIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: TelescopeIconState? = null,
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
            if (color == Color.Unspecified) Color(0xFF9C27B0) else color
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
        val seconds = phase.value * TelescopeIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(TelescopeIconShapes, seconds, resolvedColor)
        }
    }
}
