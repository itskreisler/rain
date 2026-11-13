package com.kleilabs.piratetokencounter.model

/**
 * Una acción del jugador durante una partida.
 *
 * Se guardan en orden porque "deshacer" tiene que quitar lo último que se
 * registró, y ese último puede haber sido un cofre o un tesoro.
 */
sealed interface CounterEvent {
    val atMillis: Long

    /** Se ha abierto (o roto) un contenedor. */
    data class Opened(val kind: ChestKind, val destroyed: Boolean = false, override val atMillis: Long) : CounterEvent

    /** Se ha encontrado un tesoro. */
    data class Found(val kind: TreasureKind, override val atMillis: Long) : CounterEvent
}

/**
 * Una partida terminada.
 *
 * En el escritorio la partida se crea al leer una repetición; aquí la crea el
 * jugador al terminar, de modo que [startedAtMillis] y [endedAtMillis] los
 * fija el propio contador.
 */
data class MatchRecord(
    val id: String,
    /** Cuenta a la que pertenece la partida. */
    val profileKey: String,
    /** Modo de juego tal y como lo escribió el jugador ("Battle Royale"...). */
    val mode: String,
    val startedAtMillis: Long,
    val endedAtMillis: Long,
    val events: List<CounterEvent>,
) {
    /** Cofres que suman al total. */
    val chestsOpened: Int
        get() = events.count { it is CounterEvent.Opened && it.kind.counted }

    /** Cofres rotos en lugar de abiertos. */
    val chestsDestroyed: Int
        get() = events.count { it is CounterEvent.Opened && it.destroyed }

    /** Contenedores que no son cofres (cajas de munición, cañones...). */
    val otherInteractions: Int
        get() = events.count { it is CounterEvent.Opened && !it.kind.counted }

    /** Tesoros encontrados en la partida. */
    val treasures: Int
        get() = events.count { it is CounterEvent.Found }

    val durationMillis: Long get() = (endedAtMillis - startedAtMillis).coerceAtLeast(0L)
}

/**
 * Partida en curso: los eventos se acumulan aquí hasta que el jugador la
 * termina. Se persiste para que una muerte del proceso no pierda el recuento
 * de la partida que se está jugando.
 */
data class LiveMatch(val startedAtMillis: Long, val mode: String, val events: List<CounterEvent> = emptyList()) {
    val chestsOpened: Int
        get() = events.count { it is CounterEvent.Opened && it.kind.counted }

    val chestsDestroyed: Int
        get() = events.count { it is CounterEvent.Opened && it.destroyed }

    val otherInteractions: Int
        get() = events.count { it is CounterEvent.Opened && !it.kind.counted }

    val treasures: Int
        get() = events.count { it is CounterEvent.Found }

    /** El último evento registrado, para poder deshacerlo. */
    val lastEvent: CounterEvent? get() = events.lastOrNull()
}

/**
 * Una cuenta del jugador.
 *
 * El Contador de escritorio detecta las cuentas por el identificador de Epic
 * que aparece en la repetición. En el móvil no hay repeticiones, así que la
 * cuenta se crea a mano; el historial se separa igual por cuenta.
 */
data class Profile(val key: String, val name: String, val createdAtMillis: Long)

/**
 * Todos los datos que la app guarda en el dispositivo.
 *
 * Se escribe en `data.json`, dentro del almacenamiento privado de la app: no
 * sale del teléfono y no se sube a ningún sitio.
 */
data class TrackerData(
    val version: Int = 1,
    val createdAtMillis: Long = 0L,
    val matches: List<MatchRecord> = emptyList(),
    val profiles: List<Profile> = emptyList(),
    val live: LiveMatch? = null,
    /**
     * Momento en que empezó la sesión actual. La sesión son las partidas
     * terminadas desde entonces, con la misma regla que el escritorio.
     */
    val sessionStartMillis: Long = 0L,
) {
    companion object {
        /** Perfil por defecto para las partidas nuevas. */
        const val DEFAULT_PROFILE_KEY = "default"
        const val DEFAULT_PROFILE_NAME = "Mi cuenta"
    }
}
