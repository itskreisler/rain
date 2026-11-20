package com.kyant.backdrop.catalog

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser

internal enum class LucideEase {
    InOut,
    Out,
    Linear,
}

/** Un track de Motion: valores, tiempos y ventana temporal dentro de la animación. */
internal class LucideTrack(
    val frames: List<IconKeyframe>,
    val duration: Float,
    val delay: Float,
    val easing: Easing,
) {
    fun sample(seconds: Float): Float {
        val local = ((seconds - delay) / duration).coerceIn(0f, 1f)
        return sampleKeyframes(frames, local, easing)
    }
}

internal fun lucideTrack(
    values: FloatArray,
    times: FloatArray,
    duration: Float,
    delay: Float,
    ease: LucideEase,
): LucideTrack {
    val frames = values.indices.map { i -> IconKeyframe(times[i], values[i]) }
    val easing =
        when (ease) {
            LucideEase.InOut -> IconEaseInOut
            LucideEase.Out -> IconEaseOut
            LucideEase.Linear -> LinearEasing
        }
    return LucideTrack(frames, duration.coerceAtLeast(0.001f), delay, easing)
}

internal class LucideMotion(
    val originX: Float,
    val originY: Float,
    val tracks: Map<String, LucideTrack>,
) {
    fun value(key: String, seconds: Float, default: Float = 0f): Float =
        tracks[key]?.sample(seconds) ?: default
}

internal class LucideShape(
    val path: Path?,
    val motion: LucideMotion?,
    val filled: Boolean = false,
    val stroked: Boolean = true,
    val alpha: Float = 1f,
    val strokeWidth: Float = 2f,
    val dash: FloatArray? = null,
    val dashOffset: Float = 0f,
    val children: List<LucideShape> = emptyList(),
)

internal fun lucidePath(pathData: String): Path = PathParser().parsePathString(pathData).toPath()

/** Dibuja el árbol de formas aplicando las transformaciones de cada grupo. */
internal fun DrawScope.drawLucideNodes(
    nodes: List<LucideShape>,
    seconds: Float,
    color: Color,
) {
    for (node in nodes) {
        node.path?.let { path ->
            val motion = node.motion
            val alpha = node.alpha * (motion?.value("opacity", seconds, 1f) ?: 1f)
            val pathLength = motion?.value("pathLength", seconds, 1f) ?: 1f
            val effectAlpha = (alpha * pathLength.coerceIn(0f, 1f)).coerceIn(0f, 1f)
            val offset = node.dashOffset + (motion?.value("strokeDashoffset", seconds) ?: 0f)
            val effect = node.dash?.let { PathEffect.dashPathEffect(it, offset) }
            if (node.filled) {
                drawPath(path = path, color = color, alpha = effectAlpha)
            }
            if (node.stroked) {
                drawPath(
                    path = path,
                    color = color,
                    alpha = effectAlpha,
                    style =
                        androidx.compose.ui.graphics.drawscope.Stroke(
                            width = node.strokeWidth,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round,
                            pathEffect = effect,
                        ),
                )
            }
        }
        val motion = node.motion
        if (motion == null) {
            drawLucideNodes(node.children, seconds, color)
        } else {
            val pivot = Offset(motion.originX, motion.originY)
            val scale = motion.value("scale", seconds, 1f)
            val scaleX = scale * motion.value("scaleX", seconds, 1f)
            val scaleY = scale * motion.value("scaleY", seconds, 1f)
            val dx = motion.value("x", seconds)
            val dy = motion.value("y", seconds)
            val rotation = motion.value("rotate", seconds)
            translate(dx, dy) {
                rotate(rotation, pivot = pivot) {
                    scale(scaleX, scaleY, pivot = pivot) {
                        drawLucideNodes(node.children, seconds, color)
                    }
                }
            }
        }
    }
}
