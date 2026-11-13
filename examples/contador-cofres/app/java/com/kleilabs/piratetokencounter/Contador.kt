package com.kleilabs.piratetokencounter

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.kleilabs.piratetokencounter.data.Store
import com.kleilabs.piratetokencounter.model.AppConfig
import com.kleilabs.piratetokencounter.model.ChestKind
import com.kleilabs.piratetokencounter.model.CounterEvent
import com.kleilabs.piratetokencounter.model.GameModes
import com.kleilabs.piratetokencounter.model.LiveMatch
import com.kleilabs.piratetokencounter.model.MatchRecord
import com.kleilabs.piratetokencounter.model.TrackerData
import com.kleilabs.piratetokencounter.model.TreasureKind
import java.util.UUID

/** Todo lo que la app sabe en un momento dado. */
data class AppState(
    val data: TrackerData = TrackerData(),
    val config: AppConfig = AppConfig(),
    val loaded: Boolean = false,
)

/**
 * Estado del contador y acciones sobre la partida en curso.
 *
 * Es el puente entre la interfaz (Compose) y la superposición (Views
 * clásicas): las dos leen el mismo estado y disparan las mismas acciones, así
 * que un cofre que se cuenta en el overlay aparece al instante en el
 * resumen.
 */
object Contador {

    private val _state = mutableStateOf(AppState())
    val state: State<AppState> get() = _state

    private var store: Store? = null

    /**
     * Carga el historial y las preferencias.
     *
     * La sesión se reinicia al abrir la app, como en el escritorio, pero solo
     * si no hay una partida en curso: si el sistema mata el proceso a mitad
     * de partida, al volver no se pierde ni el recuento ni las cifras de la
     * sesión.
     */
    fun init(context: Context) {
        if (store != null) return
        val local = Store(context.applicationContext)
        store = local
        val loaded = local.loadData()
        val config = local.loadConfig()
        val data = if (loaded.live == null) {
            loaded.copy(sessionStartMillis = System.currentTimeMillis())
        } else {
            loaded
        }
        _state.value = AppState(data = data, config = config, loaded = true)
        local.saveData(data)
    }

    /** Registra un contenedor abierto. */
    fun tap(kind: ChestKind) {
        val now = System.currentTimeMillis()
        updateData { data ->
            val live = data.live ?: LiveMatch(now, GameModes.normalize(state.value.config.lastMode))
            data.copy(live = live.copy(events = live.events + CounterEvent.Opened(kind, false, now)))
        }
    }

    /** Registra un tesoro encontrado. */
    fun tapTreasure(kind: TreasureKind) {
        val now = System.currentTimeMillis()
        updateData { data ->
            val live = data.live ?: LiveMatch(now, GameModes.normalize(state.value.config.lastMode))
            data.copy(live = live.copy(events = live.events + CounterEvent.Found(kind, now)))
        }
    }

    /** Quita lo último que se registró. */
    fun undo() {
        updateData { data ->
            val live = data.live
            if (live == null) data else data.copy(live = live.copy(events = live.events.dropLast(1)))
        }
    }

    /** Empieza una partida en el modo indicado, o cambia el modo de la actual. */
    fun startMatch(mode: String) {
        val normalized = GameModes.normalize(mode)
        val now = System.currentTimeMillis()
        updateData { data ->
            val live = data.live ?: LiveMatch(now, normalized)
            data.copy(live = live.copy(mode = normalized))
        }
        updateConfig { it.copy(lastMode = normalized) }
    }

    /** Termina la partida en curso y la guarda en el historial. */
    fun finishMatch() {
        val now = System.currentTimeMillis()
        updateData { data ->
            val live = data.live ?: return@updateData data
            val record = MatchRecord(
                id = UUID.randomUUID().toString(),
                profileKey = Profiles.currentKey(data, state.value.config),
                mode = live.mode,
                startedAtMillis = live.startedAtMillis,
                endedAtMillis = now,
                events = live.events,
            )
            data.copy(matches = data.matches + record, live = null)
        }
    }

    /** Descarta la partida en curso sin guardarla. */
    fun cancelMatch() {
        updateData { data ->
            if (data.live == null) data else data.copy(live = null)
        }
    }

    /** Cambia el historial y lo guarda. */
    fun updateData(transform: (TrackerData) -> TrackerData) {
        val local = store ?: return
        val updated = transform(_state.value.data)
        _state.value = _state.value.copy(data = updated)
        local.saveData(updated)
        Changes.notifyState(_state.value)
    }

    /** Cambia las preferencias y las guarda. */
    fun updateConfig(transform: (AppConfig) -> AppConfig) {
        val local = store ?: return
        val updated = transform(_state.value.config)
        _state.value = _state.value.copy(config = updated)
        local.saveConfig(updated)
        Changes.notifyState(_state.value)
    }
}
