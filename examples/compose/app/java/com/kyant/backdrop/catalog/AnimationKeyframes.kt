package com.kyant.backdrop.catalog

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing

/**
 * Curva "easeInOut" equivalente a la de Framer Motion. Se aplica por tramos,
 * igual que hacía `transition.ease` en los componentes de React de origen.
 */
internal val IconEaseInOut = CubicBezierEasing(0.42f, 0f, 0.58f, 1f)

/** Curva "easeOut" de Motion, usada por los tracks de los iconos de Lucide. */
internal val IconEaseOut = CubicBezierEasing(0f, 0f, 0.58f, 1f)

/** Un punto de la animación: `at` es la posición normalizada (0..1) y `value` el valor. */
internal data class IconKeyframe(val at: Float, val value: Float)

/**
 * Interpola los keyframes en la fase actual. Cada tramo usa la misma curva
 * easeInOut, reproduciendo el comportamiento de `times` + `ease` de Motion.
 */
internal fun sampleKeyframes(
    frames: List<IconKeyframe>,
    phase: Float,
    easing: Easing = IconEaseInOut,
): Float {
    if (phase <= frames.first().at) return frames.first().value
    for (i in 1 until frames.size) {
        val from = frames[i - 1]
        val to = frames[i]
        if (phase <= to.at) {
            val raw = (phase - from.at) / (to.at - from.at)
            return from.value + (to.value - from.value) * easing.transform(raw)
        }
    }
    return frames.last().value
}
