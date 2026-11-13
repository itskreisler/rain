package com.kleilabs.piratetokencounter.model

/**
 * Apariencia de la superposición.
 *
 * `HUD` imita el HUD del juego (cifras grandes sobre fondo transparente, al
 * estilo del overlay del Contador de escritorio); `CLASSIC` es una tarjeta
 * oscura con borde, más legible fuera del juego.
 */
enum class OverlayStyle {
    HUD,
    CLASSIC,
}

/**
 * Preferencias de la app.
 *
 * Se guardan en `config.json`, junto al historial y dentro del almacenamiento
 * privado de la app.
 */
data class AppConfig(
    /** Cuenta seleccionada. `null` = la más reciente con partidas. */
    val profileKey: String? = null,

    /** Si la superposición debe estar visible. */
    val overlayEnabled: Boolean = false,

    val overlayStyle: OverlayStyle = OverlayStyle.HUD,

    /** Tamaño del overlay en porcentaje (70-175). */
    val overlayScale: Int = 100,

    /** Opacidad del overlay en porcentaje (30-100). */
    val overlayOpacity: Int = 100,

    /** Bloqueado: el overlay no recibe toques y no se puede mover. */
    val overlayLocked: Boolean = false,

    /** Modo compacto: solo el HUD y un botón grande de cofre. */
    val overlayCompact: Boolean = false,

    /** Posición en píxeles, relativa a la esquina superior derecha. */
    val overlayX: Int? = null,
    val overlayY: Int? = null,

    /** Modo con el que se empieza la próxima partida. */
    val lastMode: String = GameModes.DEFAULT,
) {
    companion object {
        val SCALES = listOf(70, 80, 90, 100, 115, 130, 150, 175)
        val OPACITIES = listOf(100, 90, 80, 70, 60, 50, 40, 30)

        /** Tamaño por defecto de la superposición, en porcentaje. */
        const val DEFAULT_SCALE = 100

        /** Opacidad por defecto de la superposición, en porcentaje. */
        const val DEFAULT_OPACITY = 100

        /** Por debajo de 30 % las cifras ya no se leen. */
        fun clampScale(value: Int): Int = value.coerceIn(SCALES.first(), SCALES.last())

        fun clampOpacity(value: Int): Int = value.coerceIn(OPACITIES.last(), OPACITIES.first())
    }
}

/**
 * Modos de juego que el jugador puede elegir al empezar una partida.
 *
 * El Contador de escritorio deduce el modo del nombre de la lista de
 * reproducción de la repetición; aquí lo elige el jugador.
 */
object GameModes {
    val ALL = listOf(
        "Battle Royale",
        "Cero construcción",
        "Solitario",
        "Dúos",
        "Tríos",
        "Escuadrones",
        "Clasificatoria",
        "Creativo",
    )

    val DEFAULT = "Battle Royale"

    fun normalize(mode: String?): String = ALL.firstOrNull { it.equals(mode?.trim(), ignoreCase = true) }
        ?: mode?.trim()?.takeIf { it.isNotEmpty() }
        ?: DEFAULT
}
