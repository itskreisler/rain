package com.kleilabs.piratetokencounter.data

import com.kleilabs.piratetokencounter.model.AppConfig
import com.kleilabs.piratetokencounter.model.ChestKind
import com.kleilabs.piratetokencounter.model.CounterEvent
import com.kleilabs.piratetokencounter.model.LiveMatch
import com.kleilabs.piratetokencounter.model.MatchRecord
import com.kleilabs.piratetokencounter.model.OverlayStyle
import com.kleilabs.piratetokencounter.model.Profile
import com.kleilabs.piratetokencounter.model.TrackerData
import com.kleilabs.piratetokencounter.model.TreasureKind
import org.json.JSONArray
import org.json.JSONObject

/**
 * Serialización del historial y de las preferencias.
 *
 * Se usa `org.json`, que viene con Android, porque Rain no trae el plugin de
 * kotlinx-serialization: no hace falta ningún procesador de anotaciones para
 * leer y escribir estos ficheros.
 */
internal object TrackerJson {

    fun encodeData(data: TrackerData): String {
        val root = JSONObject()
        root.put("version", data.version)
        root.put("createdAt", data.createdAtMillis)
        root.put("sessionStart", data.sessionStartMillis)

        val profiles = JSONArray()
        data.profiles.forEach { profile ->
            profiles.put(
                JSONObject()
                    .put("key", profile.key)
                    .put("name", profile.name)
                    .put("createdAt", profile.createdAtMillis),
            )
        }
        root.put("profiles", profiles)

        val matches = JSONArray()
        data.matches.forEach { match ->
            matches.put(
                JSONObject()
                    .put("id", match.id)
                    .put("profile", match.profileKey)
                    .put("mode", match.mode)
                    .put("startedAt", match.startedAtMillis)
                    .put("endedAt", match.endedAtMillis)
                    .put("events", encodeEvents(match.events)),
            )
        }
        root.put("matches", matches)

        root.put("live", data.live?.let { encodeLive(it) } ?: JSONObject.NULL)
        return root.toString()
    }

    fun decodeData(text: String): TrackerData {
        val root = JSONObject(text)
        val profiles = ArrayList<Profile>()
        root.optJSONArray("profiles")?.let { array ->
            for (index in 0 until array.length()) {
                val item = array.optJSONObject(index) ?: continue
                val key = item.optString("key")
                if (key.isEmpty()) continue
                profiles.add(
                    Profile(
                        key = key,
                        name = item.optString("name", key),
                        createdAtMillis = item.optLong("createdAt", 0L),
                    ),
                )
            }
        }
        val matches = ArrayList<MatchRecord>()
        root.optJSONArray("matches")?.let { array ->
            for (index in 0 until array.length()) {
                val item = array.optJSONObject(index) ?: continue
                matches.add(
                    MatchRecord(
                        id = item.optString("id"),
                        profileKey = item.optString("profile", TrackerData.DEFAULT_PROFILE_KEY),
                        mode = item.optString("mode", "Battle Royale"),
                        startedAtMillis = item.optLong("startedAt", 0L),
                        endedAtMillis = item.optLong("endedAt", 0L),
                        events = decodeEvents(item.optJSONArray("events")),
                    ),
                )
            }
        }
        return TrackerData(
            version = root.optInt("version", 1),
            createdAtMillis = root.optLong("createdAt", 0L),
            matches = matches,
            profiles = profiles,
            live = root.optJSONObject("live")?.let { decodeLive(it) },
            sessionStartMillis = root.optLong("sessionStart", 0L),
        )
    }

    fun encodeConfig(config: AppConfig): String {
        val root = JSONObject()
        root.put("profileKey", config.profileKey ?: JSONObject.NULL)
        root.put("overlayEnabled", config.overlayEnabled)
        root.put("overlayStyle", config.overlayStyle.name)
        root.put("overlayScale", config.overlayScale)
        root.put("overlayOpacity", config.overlayOpacity)
        root.put("overlayLocked", config.overlayLocked)
        root.put("overlayCompact", config.overlayCompact)
        root.put("overlayX", config.overlayX ?: JSONObject.NULL)
        root.put("overlayY", config.overlayY ?: JSONObject.NULL)
        return root.toString()
    }

    fun decodeConfig(text: String): AppConfig {
        val root = JSONObject(text)
        return AppConfig(
            profileKey = if (root.isNull("profileKey")) null else root.optString("profileKey"),
            overlayEnabled = root.optBoolean("overlayEnabled", false),
            overlayStyle = runCatching {
                OverlayStyle.valueOf(root.optString("overlayStyle"))
            }.getOrDefault(OverlayStyle.HUD),
            overlayScale = AppConfig.clampScale(root.optInt("overlayScale", AppConfig.DEFAULT_SCALE)),
            overlayOpacity = AppConfig.clampOpacity(
                root.optInt("overlayOpacity", AppConfig.DEFAULT_OPACITY),
            ),
            overlayLocked = root.optBoolean("overlayLocked", false),
            overlayCompact = root.optBoolean("overlayCompact", false),
            overlayX = if (root.isNull("overlayX")) null else root.optInt("overlayX"),
            overlayY = if (root.isNull("overlayY")) null else root.optInt("overlayY"),
        )
    }

    private fun encodeEvents(events: List<CounterEvent>): JSONArray {
        val array = JSONArray()
        events.forEach { event ->
            val item = JSONObject().put("at", event.atMillis)
            when (event) {
                is CounterEvent.Opened -> {
                    item.put("type", "opened")
                    item.put("kind", event.kind.name)
                    item.put("destroyed", event.destroyed)
                }

                is CounterEvent.Found -> {
                    item.put("type", "found")
                    item.put("kind", event.kind.name)
                }
            }
            array.put(item)
        }
        return array
    }

    private fun decodeEvents(array: JSONArray?): List<CounterEvent> {
        if (array == null) return emptyList()
        val events = ArrayList<CounterEvent>(array.length())
        for (index in 0 until array.length()) {
            val item = array.optJSONObject(index) ?: continue
            val at = item.optLong("at", 0L)
            events.add(
                when (item.optString("type")) {
                    "found" -> CounterEvent.Found(
                        kind = TreasureKind.fromName(item.optString("kind")) ?: TreasureKind.GOLDEN_FIREWORK,
                        atMillis = at,
                    )

                    else -> CounterEvent.Opened(
                        kind = ChestKind.fromName(item.optString("kind")),
                        destroyed = item.optBoolean("destroyed", false),
                        atMillis = at,
                    )
                },
            )
        }
        return events
    }

    private fun encodeLive(live: LiveMatch): JSONObject = JSONObject()
        .put("startedAt", live.startedAtMillis)
        .put("mode", live.mode)
        .put("events", encodeEvents(live.events))

    private fun decodeLive(item: JSONObject): LiveMatch = LiveMatch(
        startedAtMillis = item.optLong("startedAt", 0L),
        mode = item.optString("mode", "Battle Royale"),
        events = decodeEvents(item.optJSONArray("events")),
    )
}
