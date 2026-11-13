package com.kleilabs.piratetokencounter

import java.util.concurrent.CopyOnWriteArrayList

/**
 * Avisos de cambio de estado para las superficies que no son Compose.
 *
 * La superposición se construye con Views clásicas para que el proceso, que
 * está vivo durante toda la partida, no cargue el runtime de Compose. Como no
 * hay recomposición, se avisa a mano con este bus.
 */
object Changes {

    private val listeners = CopyOnWriteArrayList<(AppState) -> Unit>()

    /** Se suscribe a los cambios de estado. */
    fun subscribe(listener: (AppState) -> Unit) {
        listeners.addIfAbsent(listener)
    }

    /** Cancela la suscripción. */
    fun unsubscribe(listener: (AppState) -> Unit) {
        listeners.remove(listener)
    }

    /** Avisa a todos los suscriptores del estado actual. */
    fun notifyState(state: AppState) {
        listeners.forEach { it(state) }
    }
}
