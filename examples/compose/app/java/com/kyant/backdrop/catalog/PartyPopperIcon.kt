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

/** Estado del icono PartyPopperIcon: expone el control imperativo usado por el catálogo. */
class PartyPopperIconState internal constructor() {
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
fun rememberPartyPopperIconState(): PartyPopperIconState = remember { PartyPopperIconState() }

private val PartyPopperIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(2.0f, 22.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -7.0f, 3.0f, 0f), floatArrayOf(0f, 0.3f, 0.65f, 1.0f), 0.6f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M5.8 11.3 L2 22 L12.7 18.21"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M11 13 C12.93 14.93 13.83 17.17 13 18 C12.17 18.83 9.93 17.93 8 16 C6.07 14.07 5.17 11.83 6 11 C6.83 10.17 9.07 11.07 11 13 Z"), null, false, true, 1.0f, 2.0f, null, 0f))),
        LucideShape(lucidePath("M22 2 L19.76 2.75 A2.9 2.9 0 0 0 17.8 5.87 C17.9 6.73 17.23 7.5 16.35 7.5 H15.97 C15.11 7.5 14.37 8.1 14.21 8.94 L14 10"), LucideMotion(10.0f, 15.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.88f, 1.06f, 1.0f), floatArrayOf(0f, 0.3f, 0.7f, 1.0f), 0.55f, 0.1f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M22 13 L21.18 12.67 C20.32 12.33 19.36 12.87 19.2 13.78 C19.09 14.48 18.48 15 17.77 15 H17"), LucideMotion(10.0f, 15.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.88f, 1.06f, 1.0f), floatArrayOf(0f, 0.3f, 0.7f, 1.0f), 0.55f, 0.16f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M11 2 L11.33 2.82 C11.67 3.68 11.13 4.64 10.22 4.8 C9.52 4.9 9 5.52 9 6.23 V7"), LucideMotion(10.0f, 15.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 0.88f, 1.06f, 1.0f), floatArrayOf(0f, 0.3f, 0.7f, 1.0f), 0.55f, 0.13f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M4 3 H4.01"), LucideMotion(4.0f, 3.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.7f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.25f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M15 2 H15.01"), LucideMotion(15.0f, 2.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.7f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.3f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M22 8 H22.01"), LucideMotion(22.0f, 8.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.7f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.35f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M22 20 H22.01"), LucideMotion(22.0f, 20.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.7f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.4f, 0.4f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val PartyPopperIconTotalSeconds: Float = 0.8000f

/** Conversión a Compose del icono animado `party-popper` de animateicons/Lucide. */
@Composable
fun PartyPopperIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: PartyPopperIconState? = null,
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
        val seconds = phase.value * PartyPopperIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(PartyPopperIconShapes, seconds, resolvedColor)
        }
    }
}
