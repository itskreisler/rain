package com.kyant.backdrop.catalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Estado del toggle play/pause. Dueño del estado lógico: el cambio de icono es
 * una consecuencia del final de la animación, no de una acción instantánea.
 */
class PlayPauseIconState internal constructor() {
    internal var toggleAction: (() -> Unit)? = null

    /** true = se muestra pause (contenido reproduciéndose); false = se muestra play. */
    var isPlaying: Boolean by mutableStateOf(false)
        private set

    /** Se ejecuta al terminar la animación, con [isPlaying] ya actualizado. */
    var onAnimationEnd: (() -> Unit)? = null

    /** Inicia la animación del icono visible. El icono cambia al terminar. */
    fun toggle() {
        toggleAction?.invoke()
    }

    internal fun finish(playing: Boolean) {
        isPlaying = playing
        onAnimationEnd?.invoke()
    }
}

@Composable
fun rememberPlayPauseIconState(): PlayPauseIconState = remember { PlayPauseIconState() }

/**
 * Alterna entre [PlayIcon] y [PauseIcon]. Al pulsar, el icono visible anima y,
 * solo cuando la animación termina sin cancelarse, se intercambia por el otro.
 *
 * El efecto de cambio de icono vive aquí. Si la app necesita además pausar o
 * reanudar audio, eso es una acción aparte: conectarla a [PlayPauseIconState.onAnimationEnd]
 * o al `onClick` del botón que envuelva este componente.
 */
@Composable
fun PlayPauseIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    color: Color = Color.Unspecified,
    duration: Float = 1f,
    state: PlayPauseIconState? = null,
) {
    val ownState = remember { PlayPauseIconState() }
    val effectiveState = state ?: ownState

    val playState = rememberPlayIconState()
    val pauseState = rememberPauseIconState()

    // El fin de la animación del icono visible voltea el estado y, con él, el icono.
    DisposableEffect(playState, pauseState, effectiveState) {
        playState.onAnimationEnd = {
            if (!effectiveState.isPlaying) effectiveState.finish(true)
        }
        pauseState.onAnimationEnd = {
            if (effectiveState.isPlaying) effectiveState.finish(false)
        }
        onDispose {
            playState.onAnimationEnd = null
            pauseState.onAnimationEnd = null
        }
    }

    val toggle: () -> Unit =
        remember(effectiveState) {
            {
                if (effectiveState.isPlaying) {
                    pauseState.startAnimation()
                } else {
                    playState.startAnimation()
                }
            }
        }

    DisposableEffect(effectiveState) {
        effectiveState.toggleAction = toggle
        onDispose {
            effectiveState.toggleAction = null
        }
    }

    Box(
        modifier =
            modifier
                .size(size)
                .clickable { effectiveState.toggle() },
        contentAlignment = Alignment.Center,
    ) {
        if (effectiveState.isPlaying) {
            PauseIcon(
                size = size,
                color = color,
                duration = duration,
                isAnimated = false,
                state = pauseState,
            )
        } else {
            PlayIcon(
                size = size,
                color = color,
                duration = duration,
                isAnimated = false,
                state = playState,
            )
        }
    }
}
