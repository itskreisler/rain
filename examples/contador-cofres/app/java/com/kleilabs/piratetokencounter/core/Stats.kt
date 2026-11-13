package com.kleilabs.piratetokencounter.core

import com.kleilabs.piratetokencounter.model.MatchRecord
import kotlin.math.roundToInt

/**
 * Cifras agregadas del contador.
 *
 * Es la parte pura del dominio: no toca la pantalla ni el disco. Replica la
 * lógica del Contador de escritorio (`OverlayModel`, `Dashboard` y el informe
 * HTML) para que la superposición, el resumen y el historial muestren
 * exactamente los mismos números.
 */
object Stats {

    /**
     * Margen de dos minutos de la regla de sesión del escritorio: una partida
     * que terminó justo antes de abrir la app sigue contando como de esta
     * sesión.
     */
    const val SESSION_TOLERANCE_MILLIS = 2 * 60 * 1000L

    /** Cofres abiertos en total. */
    fun chests(matches: List<MatchRecord>): Int = matches.sumOf { it.chestsOpened }

    /** Tesoros encontrados en total. */
    fun treasures(matches: List<MatchRecord>): Int = matches.sumOf { it.treasures }

    /** La partida más reciente, por fecha de fin. */
    fun lastMatch(matches: List<MatchRecord>): MatchRecord? = matches.maxByOrNull { it.endedAtMillis }

    /**
     * Una partida es de esta sesión si terminó después de que arrancó la
     * sesión (con dos minutos de margen).
     */
    fun isInSession(match: MatchRecord, sessionStartMillis: Long): Boolean =
        match.endedAtMillis >= sessionStartMillis - SESSION_TOLERANCE_MILLIS

    /** Partidas de esta sesión. */
    fun sessionMatches(matches: List<MatchRecord>, sessionStartMillis: Long): List<MatchRecord> =
        matches.filter { isInSession(it, sessionStartMillis) }

    /** Cofres de esta sesión. */
    fun sessionChests(matches: List<MatchRecord>, sessionStartMillis: Long): Int =
        sessionMatches(matches, sessionStartMillis).sumOf { it.chestsOpened }

    /** Media de cofres por partida. */
    fun average(matches: List<MatchRecord>): Double =
        if (matches.isEmpty()) 0.0 else chests(matches).toDouble() / matches.size

    /** Mejor partida: la que más cofres tuvo. */
    fun best(matches: List<MatchRecord>): Int = matches.maxOfOrNull { it.chestsOpened } ?: 0

    /** Un tesoro por cada N cofres (0 si no hay tesoros). */
    fun chestsPerTreasure(matches: List<MatchRecord>): Int {
        val total = chests(matches)
        val found = treasures(matches)
        return if (found > 0) (total.toDouble() / found).roundToInt() else 0
    }
}
