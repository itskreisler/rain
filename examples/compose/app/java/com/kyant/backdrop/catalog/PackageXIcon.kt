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

/** Estado del icono PackageXIcon: expone el control imperativo usado por el catálogo. */
class PackageXIconState internal constructor() {
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
fun rememberPackageXIconState(): PackageXIconState = remember { PackageXIconState() }

private val PackageXIconShapes: List<LucideShape> =
    listOf(
        LucideShape(null, LucideMotion(12.0f, 12.0f, mapOf("rotate" to lucideTrack(floatArrayOf(0f, -6.0f, 4.0f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.7f, 0f, LucideEase.InOut), "y" to lucideTrack(floatArrayOf(0f, -1.0f, 0.3f, 0f), floatArrayOf(0f, 0.35f, 0.7f, 1.0f), 0.7f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M12 22 V12"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M21 10.5 V8 A2 2 0 0 0 20 6.27 L13 2.27 A2 2 0 0 0 11 2.27 L4 6.27 A2 2 0 0 0 3 8 V16 A2 2 0 0 0 4 17.729 L11 21.729 A2 2 0 0 0 13 21.73 L13.13 21.656"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M3.29 7 L12 12 L20.71 7"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M7.5 4.27 L16.497 9.418"), null, false, true, 1.0f, 2.0f, null, 0f))),
        LucideShape(null, LucideMotion(19.0f, 17.0f, mapOf("scale" to lucideTrack(floatArrayOf(1.0f, 1.2f, 1.0f), floatArrayOf(0f, 0.5f, 1.0f), 0.5f, 0f, LucideEase.InOut), "rotate" to lucideTrack(floatArrayOf(0f, 90.0f), floatArrayOf(0f, 1.0f), 0.5f, 0f, LucideEase.InOut))), false, true, 1.0f, 2.0f, null, 0f, listOf(LucideShape(lucidePath("M16.5 14.5 L21.5 19.5"), null, false, true, 1.0f, 2.0f, null, 0f), LucideShape(lucidePath("M16.5 19.5 L21.5 14.5"), null, false, true, 1.0f, 2.0f, null, 0f)))
    )

private const val PackageXIconTotalSeconds: Float = 0.7000f

/** Conversión a Compose del icono animado `package-x` de animateicons/Lucide. */
@Composable
fun PackageXIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    isAnimated: Boolean = true,
    duration: Float = 1f,
    state: PackageXIconState? = null,
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
            if (color == Color.Unspecified) Color(0xFF8BC34A) else color
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
        val seconds = phase.value * PackageXIconTotalSeconds * duration.coerceAtLeast(0.1f)
        scale(this.size.width / 24f, this.size.height / 24f, pivot = Offset.Zero) {
            drawLucideNodes(PackageXIconShapes, seconds, resolvedColor)
        }
    }
}
