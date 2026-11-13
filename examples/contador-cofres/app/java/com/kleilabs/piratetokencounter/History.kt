package com.kleilabs.piratetokencounter

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.kleilabs.piratetokencounter.data.CsvExporter
import com.kleilabs.piratetokencounter.data.Store
import java.io.File

/**
 * Gestión del historial: borrar partidas, vaciarlo y exportarlo.
 */
object History {

    /** Borra una partida. */
    fun delete(id: String) {
        Contador.updateData { data ->
            data.copy(matches = data.matches.filterNot { it.id == id })
        }
    }

    /** Borra todas las partidas de todas las cuentas. */
    fun clear() {
        Contador.updateData { data -> data.copy(matches = emptyList()) }
    }

    /**
     * Prepara el CSV del historial y devuelve el intent para compartirlo.
     *
     * El fichero se deja en la carpeta que expone el `FileProvider`, así que
     * se puede mandar a cualquier app sin permisos ni rutas extrañas.
     */
    fun shareCsv(context: Context): Intent? {
        val state = Contador.state.value
        val file = exportFile(context, state) ?: return null
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file,
        )
        return Intent(Intent.ACTION_SEND).apply {
            type = "text/csv"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_SUBJECT, "Historial de cofres")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    }

    /** Escribe el CSV y devuelve el fichero, o `null` si no se pudo. */
    private fun exportFile(context: Context, state: AppState): File? = runCatching {
        val store = Store(context.applicationContext)
        val csv = CsvExporter.build(state.data, state.data.profiles)
        store.exportFile("cofres.csv", csv)
    }.getOrNull()
}
