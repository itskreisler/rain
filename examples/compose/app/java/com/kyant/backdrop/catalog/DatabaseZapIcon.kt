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

/** Estado del icono DatabaseZapIcon: expone el control imperativo usado por el catálogo. */
class DatabaseZapIconState internal constructor() {
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
fun rememberDatabaseZapIconState(): DatabaseZapIconState = remember { DatabaseZapIconState() }

private val DatabaseZapIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M8 5A4 1.3 0 1 0 16 5A4 1.3 0 1 0 8 5Z"), LucideMotion(12.0f, 12.0f, mapOf("y" to lucideTrack(floatArrayOf(-11.0f, 0f, 0.6f, 0f), floatArrayOf(0f, 0.4f, 0.5f, 1.0f), 0.8f, 0f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(0f, 1.0f, 1.0f, 0f, 0f), floatArrayOf(0f, 0.1f, 0.4f, 0.5f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M3 5A9 3 0 1 0 21 5A9 3 0 1 0 3 5Z"), LucideMotion(12.0f, 12.0f, mapOf("y" to lucideTrack(floatArrayOf(0f, 0f, 1.0f, -0.3f, 0f), floatArrayOf(0f, 0.4f, 0.58f, 0.8f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(null, LucideMotion(12.0f, 22.0f, mapOf("scaleY" to lucideTrack(floatArrayOf(1.0f, 1.0f, 0.96f, 1.01f, 1.0f), floatArrayOf(0f, 0.4f, 0.6f, 0.82f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M3 5 V19 A9 3 0 0 0 15 21.84"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M21 5 V8"), null, false, true, 1.0f, 2.0f, null, 0f))),
        LucideShape(lucidePath("M3 12 A9 3 0 0 0 14.59 14.87"), LucideMotion(12.0f, 12.0f, mapOf("y" to lucideTrack(floatArrayOf(0f, 0f, 1.4f, -0.35f, 0f), floatArrayOf(0f, 0.45f, 0.63f, 0.84f, 1.0f), 0.8f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M21 12 L18 17 H22 L19 22"), LucideMotion(20.0f, 17.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.2f, 1.0f, 1.1f, 1.0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.5f, 0.15f, LucideEase.InOut), "opacity" to lucideTrack(floatArrayOf(1.0f, 0.25f, 1.0f, 0.5f, 1.0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.5f, 0.15f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val DatabaseZapIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `database-zap` de animateicons/Lucide. */
@Composable
fun DatabaseZapIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: DatabaseZapIconState? = null,
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
            if (color == Color.Unspecified) Color(0xFFFF9800) else color
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
        val seconds = phase.value * DatabaseZapIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(DatabaseZapIconShapes, seconds, resolvedColor)
        }
    }
}
