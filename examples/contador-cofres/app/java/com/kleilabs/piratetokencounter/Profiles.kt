package com.kleilabs.piratetokencounter

import com.kleilabs.piratetokencounter.model.AppConfig
import com.kleilabs.piratetokencounter.model.MatchRecord
import com.kleilabs.piratetokencounter.model.Profile
import com.kleilabs.piratetokencounter.model.TrackerData
import java.util.UUID

/**
 * Cuentas del jugador.
 *
 * En el escritorio las cuentas se detectan solas por el identificador de Epic
 * que trae la repetición. En el móvil no hay repeticiones, así que la cuenta
 * se crea a mano, pero el historial se separa igual por cuenta: el resumen,
 * el overlay y el CSV solo teach lo de la cuenta elegida.
 */
object Profiles {

    /** Todas las cuentas, de la más reciente a la más antigua. */
    fun list(data: TrackerData): List<Profile> = data.profiles.sortedByDescending { it.createdAtMillis }

    /** La cuenta seleccionada, o la más reciente si la elegida ya no existe. */
    fun current(data: TrackerData, config: AppConfig): Profile? {
        val all = list(data)
        return all.firstOrNull { it.key == config.profileKey } ?: all.firstOrNull()
    }

    /** Clave de la cuenta activa. */
    fun currentKey(data: TrackerData, config: AppConfig): String =
        current(data, config)?.key ?: TrackerData.DEFAULT_PROFILE_KEY

    /** Partidas de la cuenta activa. */
    fun matches(data: TrackerData, config: AppConfig): List<MatchRecord> {
        val key = currentKey(data, config)
        return data.matches.filter { it.profileKey == key }
    }

    /** Crea una cuenta nueva. */
    fun add(name: String) {
        val clean = name.trim()
        if (clean.isEmpty()) return
        Contador.updateData { data ->
            val profile = Profile(UUID.randomUUID().toString(), clean, System.currentTimeMillis())
            data.copy(profiles = data.profiles + profile)
        }
    }

    /** Cambia el nombre de una cuenta. */
    fun rename(key: String, name: String) {
        val clean = name.trim()
        if (clean.isEmpty()) return
        Contador.updateData { data ->
            data.copy(
                profiles = data.profiles.map { if (it.key == key) it.copy(name = clean) else it },
            )
        }
    }

    /** Cambia la cuenta activa. */
    fun select(key: String) {
        Contador.updateConfig { it.copy(profileKey = key) }
    }

    /**
     * Borra una cuenta y sus partidas.
     *
     * No se puede borrar la última: siempre queda una con la que contar.
     */
    fun remove(key: String) {
        Contador.updateData { data ->
            if (data.profiles.size <= 1) return@updateData data
            data.copy(
                profiles = data.profiles.filterNot { it.key == key },
                matches = data.matches.filter { it.profileKey != key },
            )
        }
        Contador.updateConfig { config ->
            if (config.profileKey == key) config.copy(profileKey = null) else config
        }
    }
}
