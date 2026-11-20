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

/** Estado del icono PackageOpenIcon: expone el control imperativo usado por el catálogo. */
class PackageOpenIconState internal constructor() {
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
fun rememberPackageOpenIconState(): PackageOpenIconState = remember { PackageOpenIconState() }

private val PackageOpenIconShapes: List<LucideShape> =
    listOf(
        LucideShape(lucidePath("M12 22 V13"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M15.17 2.21 A1.67 1.67 0 0 1 16.8 2.21 L21 4.57 A1.93 1.93 0 0 1 21 7.93 L8.82 14.79 A1.655 1.655 0 0 1 7.18 14.79 L3 12.43 A1.93 1.93 0 0 1 3 9.07 Z"), LucideMotion(12.0f, 8.5f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -6.0f, 4.0f, -2.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M20 13 V16.87 A2.06 2.06 0 0 1 18.89 18.7 L12.89 21.78 A1.93 1.93 0 0 1 11.11 21.78 L5.11 18.7 A2.06 2.06 0 0 1 4 16.87 V13"), null, false, true, 1.0f, 2.0f, null, 0f),
        LucideShape(lucidePath("M21 12.43 A1.93 1.93 0 0 0 21 9.07 L8.83 2.2 A1.64 1.64 0 0 0 7.2 2.2 L3 4.57 A1.93 1.93 0 0 0 3 7.93 L15.18 14.79 A1.636 1.636 0 0 0 16.81 14.79 Z"), LucideMotion(12.0f, 8.5f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, 6.0f, -4.0f, 2.0f, 0f), floatArrayOf(0f, 0.25f, 0.5f, 0.75f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f)
    )

private const val PackageOpenIconTotalSeconds: Float = 0.7000f

/** Conversión a Compose del icono animado `package-open` de animateicons/Lucide. */
@Composable
fun PackageOpenIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: PackageOpenIconState? = null,
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
                                    durationMillis = (700 * duration.coerceAtLeast(0.1f)).toInt(),
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
        val seconds = phase.value * PackageOpenIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(PackageOpenIconShapes, seconds, resolvedColor)
        }
    }
}
