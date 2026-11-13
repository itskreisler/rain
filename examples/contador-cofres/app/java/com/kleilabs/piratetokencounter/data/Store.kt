package com.kleilabs.piratetokencounter.data

import android.content.Context
import android.util.Log
import com.kleilabs.piratetokencounter.model.AppConfig
import com.kleilabs.piratetokencounter.model.TrackerData
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

/**
 * Dónde se guardan el historial y las preferencias.
 *
 * replica el `Store` del Contador de escritorio: dos ficheros de texto en la
 * carpeta privada de la app, escritos de forma atómica (se escribe a un
 * temporal y se renombra) para que un cierre inesperado nunca deje un
 * historial a medias.
 *
 * Todo se queda en el dispositivo: no hay cuentas, ni contraseñas, ni red.
 */
class Store(context: Context) {

    private val filesDir = context.filesDir
    private val dataFile = File(filesDir, "data.json")
    private val configFile = File(filesDir, "config.json")
    private val exportDir = File(filesDir, "exports")

    /** Lee el historial. Si el fichero está dañado se aparta y se empieza de cero. */
    fun loadData(): TrackerData {
        val text = readText(dataFile) ?: return emptyData()
        return runCatching { TrackerJson.decodeData(text) }
            .getOrElse { failure ->
                keepBrokenCopy(dataFile)
                Log.w(TAG, "data.json ilegible, se empieza de nuevo", failure)
                emptyData()
            }
    }

    /** Guarda el historial de forma atómica. */
    fun saveData(data: TrackerData) {
        writeAtomic(dataFile, TrackerJson.encodeData(data))
    }

    /** Lee las preferencias; si no hay o están dañadas, vuelve a los valores por defecto. */
    fun loadConfig(): AppConfig {
        val text = readText(configFile) ?: return AppConfig()
        return runCatching { TrackerJson.decodeConfig(text) }
            .getOrElse { failure ->
                keepBrokenCopy(configFile)
                Log.w(TAG, "config.json ilegible, se usan los valores por defecto", failure)
                AppConfig()
            }
    }

    /** Guarda las preferencias de forma atómica. */
    fun saveConfig(config: AppConfig) {
        writeAtomic(configFile, TrackerJson.encodeConfig(config))
    }

    /**
     * Prepara un fichero dentro de la carpeta que comparte el `FileProvider`,
     * para poder enviarlo desde el menú de compartir.
     */
    fun exportFile(name: String, content: String): File {
        if (!exportDir.exists()) exportDir.mkdirs()
        val file = File(exportDir, name)
        file.writeText(content, Charsets.UTF_8)
        return file
    }

    /** Historial vacío con la cuenta por defecto ya creada. */
    private fun emptyData(): TrackerData {
        val now = System.currentTimeMillis()
        return TrackerData(
            createdAtMillis = now,
            profiles = listOf(
                com.kleilabs.piratetokencounter.model.Profile(
                    key = TrackerData.DEFAULT_PROFILE_KEY,
                    name = TrackerData.DEFAULT_PROFILE_NAME,
                    createdAtMillis = now,
                ),
            ),
        )
    }

    private fun readText(file: File): String? =
        if (file.exists()) runCatching { file.readText(Charsets.UTF_8) }.getOrNull() else null

    private fun writeAtomic(file: File, content: String) {
        val temporary = File(file.parentFile, "${file.name}.tmp")
        temporary.writeText(content, Charsets.UTF_8)
        runCatching {
            Files.move(
                temporary.toPath(),
                file.toPath(),
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.ATOMIC_MOVE,
            )
        }.getOrElse {
            // Sin movimiento atómico (algunos sistemas de ficheros no lo
            // soportan): se borra el destino y se renombra a mano.
            if (file.exists()) file.delete()
            temporary.renameTo(file)
        }
    }

    /** Aparta una copia del fichero ilegible en lugar de perderlo sin más. */
    private fun keepBrokenCopy(file: File) {
        runCatching {
            val stamp = java.text.SimpleDateFormat("yyyyMMdd-HHmmss", java.util.Locale.US)
                .format(java.util.Date())
            file.copyTo(File(file.parentFile, "${file.name}.roto-$stamp"), overwrite = true)
        }
    }

    private companion object {
        const val TAG = "Contador"
    }
}
