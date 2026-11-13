package com.kleilabs.piratetokencounter.core

import com.kleilabs.piratetokencounter.model.ChestKind
import com.kleilabs.piratetokencounter.model.CounterEvent
import com.kleilabs.piratetokencounter.model.MatchRecord
import com.kleilabs.piratetokencounter.model.TreasureKind
import java.util.Calendar

/**
 * Desgloses del historial.
 *
 * Son los gráficos del informe HTML del Contador de escritorio, adaptados a
 * lo que cabe en la pantalla de un teléfono.
 */
object Breakdown {

    /** Días que muestra el gráfico de barras. */
    const val CHART_DAYS = 30

    private const val DAY_MILLIS = 24 * 60 * 60 * 1000L

    /**
     * Reparto por tipo de cofre, del más frecuente al menos frecuente.
     *
     * Los contenedores que no son cofres se devuelven aparte para poder
     * mostrarlos "(fuera del total)", como hace el informe del escritorio.
     */
    fun byKind(matches: List<MatchRecord>): Map<ChestKind, Int> = countBy(opened(matches).filter { it.kind.counted })

    /** Contenedores que no son cofres, del más frecuente al menos frecuente. */
    fun byContainer(matches: List<MatchRecord>): Map<ChestKind, Int> =
        countBy(opened(matches).filter { !it.kind.counted })

    /** Cofres destruidos en vez de abiertos. */
    fun destroyed(matches: List<MatchRecord>): Int = matches.sumOf { it.chestsDestroyed }

    /** Reparto por tipo de tesoro. */
    fun byTreasure(matches: List<MatchRecord>): Map<TreasureKind, Int> {
        val counts = LinkedHashMap<TreasureKind, Int>()
        matches.asSequence()
            .flatMap { it.events.asSequence() }
            .filterIsInstance<CounterEvent.Found>()
            .groupingBy { it.kind }
            .eachCount()
            .entries
            .sortedByDescending { it.value }
            .forEach { (kind, count) -> counts[kind] = count }
        return counts
    }

    /**
     * Cofres por día para los últimos [days] días, terminando hoy.
     *
     * Es el gráfico de barras del informe del escritorio, recortado a lo que
     * cabe en un teléfono.
     */
    fun byDay(
        matches: List<MatchRecord>,
        days: Int = CHART_DAYS,
        now: Long = System.currentTimeMillis(),
    ): List<DayCount> {
        val firstDay = startOfDay(now) - (days - 1).toLong() * DAY_MILLIS
        val sums = LongArray(days)
        matches.forEach { match ->
            val index = ((startOfDay(match.endedAtMillis) - firstDay) / DAY_MILLIS).toInt()
            if (index in 0 until days) sums[index] += match.chestsOpened
        }
        return (0 until days).map { DayCount(firstDay + it * DAY_MILLIS, sums[it].toInt()) }
    }

    private fun opened(matches: List<MatchRecord>): List<CounterEvent.Opened> =
        matches.flatMap { it.events }.filterIsInstance<CounterEvent.Opened>()

    private fun countBy(events: List<CounterEvent.Opened>): Map<ChestKind, Int> {
        val counts = LinkedHashMap<ChestKind, Int>()
        events.groupingBy { it.kind }
            .eachCount()
            .entries
            .sortedByDescending { it.value }
            .forEach { (kind, count) -> counts[kind] = count }
        return counts
    }

    private fun startOfDay(millis: Long): Long = Calendar.getInstance().apply {
        timeInMillis = millis
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis
}

/** Cofres contados en un día. */
data class DayCount(val dayStartMillis: Long, val chests: Int)
