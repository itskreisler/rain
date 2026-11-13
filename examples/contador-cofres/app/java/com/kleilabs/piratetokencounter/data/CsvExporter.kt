package com.kleilabs.piratetokencounter.data

import com.kleilabs.piratetokencounter.model.ChestKind
import com.kleilabs.piratetokencounter.model.CounterEvent
import com.kleilabs.piratetokencounter.model.MatchRecord
import com.kleilabs.piratetokencounter.model.Profile
import com.kleilabs.piratetokencounter.model.TrackerData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Exporta el historial a CSV.
 *
 * El Contador de escritorio exporta un CSV con punto y coma, que es lo que
 * Excel espera en español. Aquí se mantiene el separador y se añade el
 * desglose por tipo de cofre, que en el móvil es la información principal.
 */
object CsvExporter {

    /** Milisegundos en un minuto, para la columna de duración. */
    private const val MILLIS_PER_MINUTE = 60_000L

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)

    /** Cabecera y una fila por partida, de la más antigua a la más reciente. */
    fun build(data: TrackerData, profiles: List<Profile>): String {
        val names = profiles.associate { it.key to it.name }
        val builder = StringBuilder()
        builder.append(header()).append('\n')
        data.matches.sortedBy { it.endedAtMillis }.forEach { match ->
            builder.append(row(match, names[match.profileKey] ?: match.profileKey)).append('\n')
        }
        return builder.toString()
    }

    private fun header(): String = listOf(
        "fecha",
        "modo",
        "cuenta",
        "cofres",
        "tesoros",
        "contenedores",
        "destruidos",
        "duracion_min",
    ).plus(ChestKind.entries.map { columnName(it) }).joinToString(";")

    private fun row(match: MatchRecord, profileName: String): String {
        val counts = match.events
            .filterIsInstance<CounterEvent.Opened>()
            .groupingBy { it.kind }
            .eachCount()
        return listOf(
            dateFormat.format(Date(match.endedAtMillis)),
            match.mode,
            profileName,
            match.chestsOpened.toString(),
            match.treasures.toString(),
            match.otherInteractions.toString(),
            match.chestsDestroyed.toString(),
            (match.durationMillis / MILLIS_PER_MINUTE).toString(),
        ).plus(ChestKind.entries.map { (counts[it] ?: 0).toString() })
            .joinToString(";") { escape(it) }
    }

    private fun columnName(kind: ChestKind): String = when (kind) {
        ChestKind.NORMAL -> "cofre"
        ChestKind.RARE -> "cofre_raro"
        ChestKind.BURIED -> "cofre_enterrado"
        ChestKind.SPIRIT -> "cofre_espiritu"
        ChestKind.POSTER -> "carteles"
        ChestKind.OTHER_CHEST -> "otro_cofre"
        ChestKind.LOOT_CANNON -> "canon"
        ChestKind.AMMO_BOX -> "municion"
        ChestKind.FOOD_BOX -> "comida"
        ChestKind.OTHER_CONTAINER -> "otro_contenedor"
    }

    /** Escapa los campos que podrían romper la separación por punto y coma. */
    private fun escape(value: String): String =
        if (value.contains(';') || value.contains('"') || value.contains('\n')) {
            "\"" + value.replace("\"", "\"\"") + "\""
        } else {
            value
        }
}
