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

/** Estado del icono CalendarSyncIcon: expone el control imperativo usado por el catálogo. */
class CalendarSyncIconState internal constructor() {
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
fun rememberCalendarSyncIconState(): CalendarSyncIconState = remember { CalendarSyncIconState() }

private val CalendarSyncIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M8 2 V5"), LucideMotion(12.0f, 12.0f, mapOf("y" to lucideTrack(floatArrayOf(0f, 1.6f, -0.3f, 0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M16 2 V5"), LucideMotion(12.0f, 12.0f, mapOf("y" to lucideTrack(floatArrayOf(0f, 1.6f, -0.3f, 0f), floatArrayOf(0f, 0.4f, 0.75f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M21 8.517 V5 A2 2 0 0 0 19 3 H5 A2 2 0 0 0 3 5 V19 A2 2 0 0 0 5 21 H8.517"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M3 9 H7"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(null, LucideMotion(16.0f, 16.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.8f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.9f, 0f, LucideEase.InOut), "rotate" to lucideTrack(floatArrayOf(0f, 180.0f), floatArrayOf(0f, 1.0f), 0.9f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M11 10 V14 H15"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M11 14 L12.535 12.395 A5 5 0 0 1 20.535 13.895"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M21 18 L19.465 19.605 A5 5 0 0 1 11.465 18.105"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M21 22 V18 H17"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val CalendarSyncIconTotalSeconds: Float = 0.9000f

/** Conversión a Compose del icono animado `calendar-sync` de animateicons/Lucide. */
@Composable
fun CalendarSyncIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: CalendarSyncIconState? = null,
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
        val seconds = phase.value * CalendarSyncIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(CalendarSyncIconShapes, seconds, resolvedColor)
        }
    }
}
