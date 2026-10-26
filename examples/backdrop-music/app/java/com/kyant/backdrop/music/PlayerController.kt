package com.kyant.backdrop.music

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.audiofx.Equalizer
import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.random.Random

enum class RepeatMode(val label: String) {
    Off("Sin repetición"),
    All("Repetir todo"),
    One("Repetir una pista"),
}

data class EqBand(
    val index: Int,
    val label: String,
    val minMillibels: Short,
    val maxMillibels: Short,
    val millibels: Short,
) {
    val levelDb: Float get() = millibels / 100f
    val minDb: Float get() = minMillibels / 100f
    val maxDb: Float get() = maxMillibels / 100f
}

class PlayerController(context: Context) : ViewModel() {

    private val appContext = context.applicationContext
    private val audioManager =
        appContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private val mainHandler = Handler(Looper.getMainLooper())
    private val attributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_MEDIA)
        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
        .build()

    private var pausedForFocusLoss = false
    private var pauseOnFocusLoss = true

    private var equalizer: Equalizer? = null
    private var savedEqualizerLevels: Map<Int, Short> = emptyMap()

    private val focusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
        .setAudioAttributes(attributes)
        .setAcceptsDelayedFocusGain(false)
        .setOnAudioFocusChangeListener { change ->
            when (change) {
                AudioManager.AUDIOFOCUS_LOSS -> {
                    pausedForFocusLoss = false
                    pausePlayback(abandonFocus = false)
                }

                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> if (pauseOnFocusLoss) {
                    pausedForFocusLoss = isPlaying
                    pausePlayback(abandonFocus = false)
                }

                AudioManager.AUDIOFOCUS_GAIN -> if (pausedForFocusLoss) {
                    pausedForFocusLoss = false
                    resumeFromFocusGain()
                }
            }
        }
        .build()

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var player: MediaPlayer? = null

    var queue by mutableStateOf<List<Track>>(emptyList())
        private set
    var currentIndex by mutableIntStateOf(-1)
        private set

    var onStateChanged: (() -> Unit)? = null

    var isPlaying by mutableStateOf(false)
        private set
    var isPreparing by mutableStateOf(false)
        private set
    var positionMs by mutableLongStateOf(0L)
        private set
    var durationMs by mutableLongStateOf(0L)
        private set
    var error by mutableStateOf<String?>(null)
        private set

    var shuffle by mutableStateOf(false)
        private set

    var repeatMode by mutableStateOf(RepeatMode.Off)
        private set

    var crossfadeSeconds by mutableIntStateOf(0)
        private set

    fun updateCrossfadeSeconds(seconds: Int) {
        crossfadeSeconds = seconds
    }

    var eqBands by mutableStateOf<List<EqBand>>(emptyList())
        private set
    var eqError by mutableStateOf<String?>(null)
        private set

    var sleepTimerRemainingMs by mutableLongStateOf(0L)
        private set

    var stopAtEndOfTrack by mutableStateOf(false)
        private set

    private var sleepTimerJob: kotlinx.coroutines.Job? = null

    fun setSleepTimer(minutes: Int, atEndOfTrack: Boolean = false) {
        sleepTimerJob?.cancel()
        stopAtEndOfTrack = atEndOfTrack
        if (minutes <= 0) {
            sleepTimerRemainingMs = 0L
            return
        }
        sleepTimerRemainingMs = minutes * 60_000L
        sleepTimerJob = scope.launch {
            while (sleepTimerRemainingMs > 0L && isActive) {
                delay(1000L)
                sleepTimerRemainingMs = (sleepTimerRemainingMs - 1000L).coerceAtLeast(0L)
            }
            if (sleepTimerRemainingMs == 0L && !stopAtEndOfTrack) {
                pausePlayback(abandonFocus = true)
            }
        }
    }

    fun cancelSleepTimer() {
        sleepTimerJob?.cancel()
        sleepTimerRemainingMs = 0L
        stopAtEndOfTrack = false
    }

    val hasEqualizer: Boolean get() = eqBands.isNotEmpty()

    val currentTrack: Track? get() = queue.getOrNull(currentIndex)

    val progress: Float
        get() = if (durationMs <= 0L) 0f
        else (positionMs.toFloat() / durationMs).coerceIn(0f, 1f)

    init {
        scope.launch {
            while (isActive) {
                val mediaPlayer = player
                if (mediaPlayer != null && isPlaying) {
                    runCatching { positionMs = mediaPlayer.currentPosition.toLong() }
                }
                delay(250)
            }
        }
    }

    fun updateQueue(tracks: List<Track>, savedIds: List<Long> = emptyList()) {
        val currentId = currentTrack?.id
        val finalQueue = if (savedIds.isNotEmpty() && currentId == null) {
            val trackMap = tracks.associateBy { it.id }
            val ordered = savedIds.mapNotNull { trackMap[it] }
            val missing = tracks.filter { it.id !in savedIds }
            ordered + missing
        } else {
            tracks
        }

        queue = finalQueue

        val preservedIndex = finalQueue.indexOfFirst { it.id == currentId }
        if (preservedIndex >= 0) {
            currentIndex = preservedIndex
            if (currentTrack != null && durationMs <= 0L) {
                durationMs = currentTrack?.durationMs ?: 0L
            }
            onStateChanged?.invoke()
            return
        }

        releasePlayer()
        currentIndex = if (finalQueue.isNotEmpty()) 0 else -1
        positionMs = 0L
        durationMs = finalQueue.firstOrNull()?.durationMs ?: 0L
        error = null
        onStateChanged?.invoke()
    }

    fun updateTrackMetadata(id: Long, title: String, artist: String, album: String, genre: String) {
        queue = queue.map { track ->
            if (track.id == id) {
                track.copy(title = title, artist = artist, album = album, genre = genre)
            } else track
        }
        onStateChanged?.invoke()
    }

    fun moveQueueItem(fromIndex: Int, toIndex: Int): List<Long> {
        if (fromIndex !in queue.indices || toIndex !in queue.indices || fromIndex == toIndex) {
            return queue.map { it.id }
        }
        val mutable = queue.toMutableList()
        val item = mutable.removeAt(fromIndex)
        mutable.add(toIndex, item)

        val current = currentTrack
        queue = mutable
        if (current != null) {
            currentIndex = queue.indexOfFirst { it.id == current.id }
        }
        onStateChanged?.invoke()
        return queue.map { it.id }
    }

    fun removeQueueItem(index: Int): List<Long> {
        if (index !in queue.indices) return queue.map { it.id }
        val current = currentTrack
        val isCurrent = (index == currentIndex)
        val mutable = queue.toMutableList()
        mutable.removeAt(index)
        queue = mutable

        if (queue.isEmpty()) {
            releasePlayer()
            currentIndex = -1
            positionMs = 0L
            durationMs = 0L
        } else if (isCurrent) {
            playAt(index.coerceAtMost(queue.lastIndex), autoPlay = isPlaying)
        } else if (current != null) {
            currentIndex = queue.indexOfFirst { it.id == current.id }
        }
        onStateChanged?.invoke()
        return queue.map { it.id }
    }

    fun playRadioStation(name: String, streamUrl: String) {
        val radioTrack = Track(
            id = System.currentTimeMillis(),
            uri = android.net.Uri.parse(streamUrl),
            title = name,
            artist = "Radio por Internet",
            album = "Stream En Vivo",
            albumId = 0L,
            genre = "Radio",
            folder = "Online",
            durationMs = 0L,
            sizeBytes = 0L,
            dateAdded = System.currentTimeMillis() / 1000L,
        )
        queue = listOf(radioTrack)
        playAt(0)
    }

    fun playAt(index: Int, autoPlay: Boolean = true) {
        val track = queue.getOrNull(index) ?: return
        releasePlayer()

        currentIndex = index
        positionMs = 0L
        durationMs = track.durationMs
        error = null
        isPreparing = true
        onStateChanged?.invoke()

        val mediaPlayer = MediaPlayer()
        player = mediaPlayer
        try {
            mediaPlayer.setAudioAttributes(attributes)
            mediaPlayer.setDataSource(appContext, track.uri)
            mediaPlayer.setOnPreparedListener { prepared ->
                isPreparing = false
                val reportedDuration = prepared.duration.toLong()
                if (reportedDuration > 0L) durationMs = reportedDuration
                attachEqualizer(prepared.audioSessionId)
                if (autoPlay) {
                    if (requestFocus()) {
                        runCatching { prepared.start() }
                            .onSuccess {
                                isPlaying = true
                                onStateChanged?.invoke()
                            }
                            .onFailure {
                                isPlaying = false
                                error = "No se pudo iniciar «${track.title}»."
                                onStateChanged?.invoke()
                            }
                    } else {
                        isPlaying = false
                        error = "Otra aplicación está usando el audio en este momento."
                        onStateChanged?.invoke()
                    }
                } else {
                    isPlaying = false
                    onStateChanged?.invoke()
                }
            }
            mediaPlayer.setOnCompletionListener {
                isPlaying = false
                onStateChanged?.invoke()
                mainHandler.post { advanceFromCompletion() }
            }
            mediaPlayer.setOnErrorListener { _, what, extra ->
                isPreparing = false
                isPlaying = false
                error = "No se pudo reproducir «${track.title}» (código $what/$extra)."
                onStateChanged?.invoke()
                true
            }
            mediaPlayer.prepareAsync()
        } catch (exception: Exception) {
            isPreparing = false
            isPlaying = false
            error = "No se pudo abrir «${track.title}»: " +
                (exception.message ?: "archivo no disponible")
            onStateChanged?.invoke()
            releasePlayer()
        }
    }

    fun play(track: Track) {
        val index = queue.indexOfFirst { it.id == track.id }
        if (index >= 0) playAt(index)
    }

    fun toggle() {
        if (queue.isEmpty() || isPreparing) return
        val mediaPlayer = player
        if (mediaPlayer == null) {
            playAt(currentIndex.coerceAtLeast(0))
            return
        }
        if (isPlaying) {
            pausePlayback(abandonFocus = true)
            return
        }
        if (!requestFocus()) {
            error = "Otra aplicación está usando el audio en este momento."
            onStateChanged?.invoke()
            return
        }
        runCatching { mediaPlayer.start() }
            .onSuccess {
                isPlaying = true
                onStateChanged?.invoke()
            }
            .onFailure {
                error = "No se pudo reanudar la reproducción."
                onStateChanged?.invoke()
            }
    }

    fun next(autoPlay: Boolean = isPlaying) {
        if (queue.isEmpty()) return
        val nextIndex = nextIndexOrNull(wrap = true) ?: return
        playAt(nextIndex, autoPlay = autoPlay)
    }

    fun previous() {
        if (queue.isEmpty()) return
        if (positionMs > 3_000L) {
            seekTo(0L)
            return
        }
        val previousIndex = if (currentIndex <= 0) queue.lastIndex else currentIndex - 1
        playAt(previousIndex, autoPlay = isPlaying)
    }

    fun seekTo(milliseconds: Long) {
        val mediaPlayer = player
        if (mediaPlayer == null) return
        val clamped = milliseconds.coerceIn(0L, durationMs.coerceAtLeast(0L))
        runCatching { mediaPlayer.seekTo(clamped.toInt()) }
        positionMs = clamped
    }

    fun toggleShuffle() {
        shuffle = !shuffle
    }

    fun updateShuffle(value: Boolean) {
        shuffle = value
    }

    fun cycleRepeatMode() {
        repeatMode = when (repeatMode) {
            RepeatMode.Off -> RepeatMode.All
            RepeatMode.All -> RepeatMode.One
            RepeatMode.One -> RepeatMode.Off
        }
    }

    fun updateRepeatMode(value: RepeatMode) {
        repeatMode = value
    }

    fun updatePauseOnFocusLoss(value: Boolean) {
        pauseOnFocusLoss = value
    }

    fun playIfIdle(index: Int = currentIndex.coerceAtLeast(0)) {
        if (queue.isEmpty() || player != null || isPreparing) return
        playAt(index)
    }

    fun setBandLevel(index: Int, millibels: Float) {
        val effect = equalizer ?: return
        val band = eqBands.getOrNull(index) ?: return
        val clamped = millibels.roundToInt().toShort()
            .coerceIn(band.minMillibels, band.maxMillibels)
        runCatching { effect.setBandLevel(index.toShort(), clamped) }
        savedEqualizerLevels = savedEqualizerLevels + (index to clamped)
        eqBands = eqBands.toMutableList().also { bands ->
            bands[index] = band.copy(millibels = clamped)
        }
    }

    var currentPresetName by mutableStateOf("Flat")
        private set

    fun applyPreset(presetName: String, customLevels: Map<Int, Short> = emptyMap()): Map<Int, Short> {
        currentPresetName = presetName
        if (equalizer == null || eqBands.isEmpty()) return savedEqualizerLevels

        val newLevels = mutableMapOf<Int, Short>()
        eqBands.indices.forEach { index ->
            val band = eqBands[index]
            val millibels: Short = when (presetName) {
                "Rock" -> when (index) {
                    0 -> 400; 1 -> 200; 2 -> -100; 3 -> 300; else -> 500
                }
                "Jazz" -> when (index) {
                    0 -> 300; 1 -> 200; 2 -> 100; 3 -> 200; else -> 300
                }
                "Pop" -> when (index) {
                    0 -> -100; 1 -> 200; 2 -> 400; 3 -> 200; else -> -100
                }
                "Classical" -> when (index) {
                    0 -> 500; 1 -> 300; 2 -> 0; 3 -> 200; else -> 400
                }
                "Bass Boost" -> when (index) {
                    0 -> 600; 1 -> 400; 2 -> 100; else -> 0
                }
                "Personalizado" -> customLevels[index] ?: 0
                else -> 0
            }.toShort().coerceIn(band.minMillibels, band.maxMillibels)

            setBandLevel(index, millibels.toFloat())
            newLevels[index] = millibels
        }
        return newLevels
    }

    fun resetEqualizer() {
        if (equalizer == null) return
        applyPreset("Flat")
    }

    fun clearError() {
        error = null
        onStateChanged?.invoke()
    }

    override fun onCleared() {
        scope.cancel()
        mainHandler.removeCallbacksAndMessages(null)
        releasePlayer()
        abandonFocus()
    }

    private fun advanceFromCompletion() {
        if (stopAtEndOfTrack) {
            cancelSleepTimer()
            isPlaying = false
            onStateChanged?.invoke()
            return
        }

        if (repeatMode == RepeatMode.One) {
            playAt(currentIndex, autoPlay = true)
            return
        }

        val nextIndex = nextIndexOrNull(wrap = repeatMode == RepeatMode.All)
        if (nextIndex == null) {
            positionMs = durationMs
            onStateChanged?.invoke()
            return
        }
        playAt(nextIndex, autoPlay = true)
    }

    private fun nextIndexOrNull(wrap: Boolean): Int? {
        if (queue.isEmpty()) return null
        if (queue.size == 1) return if (wrap) currentIndex else null

        if (shuffle) {
            var candidate: Int
            do {
                candidate = Random.nextInt(queue.size)
            } while (candidate == currentIndex)
            return candidate
        }

        val next = currentIndex + 1
        return when {
            next < queue.size -> next
            wrap -> 0
            else -> null
        }
    }

    private fun pausePlayback(abandonFocus: Boolean) {
        val mediaPlayer = player ?: return
        runCatching {
            if (mediaPlayer.isPlaying) mediaPlayer.pause()
            positionMs = mediaPlayer.currentPosition.toLong()
        }
        isPlaying = false
        onStateChanged?.invoke()
        if (abandonFocus) {
            pausedForFocusLoss = false
            abandonFocus()
        }
    }

    private fun resumeFromFocusGain() {
        val mediaPlayer = player ?: return
        runCatching { mediaPlayer.start() }
            .onSuccess {
                isPlaying = true
                onStateChanged?.invoke()
            }
            .onFailure {
                error = "No se pudo reanudar la reproducción."
                onStateChanged?.invoke()
            }
    }

    private fun releasePlayer() {
        releaseEqualizer()
        player?.let { mediaPlayer ->
            runCatching { mediaPlayer.reset() }
            runCatching { mediaPlayer.release() }
        }
        player = null
        isPlaying = false
        isPreparing = false
        onStateChanged?.invoke()
    }

    private fun attachEqualizer(sessionId: Int) {
        releaseEqualizer()
        eqError = null
        if (sessionId == 0) {
            eqBands = emptyList()
            return
        }
        runCatching { Equalizer(0, sessionId) }
            .onSuccess { effect ->
                equalizer = effect
                runCatching {
                    val range = effect.bandLevelRange
                    val count = effect.numberOfBands.toInt()
                    eqBands = (0 until count).map { index ->
                        val band = index.toShort()
                        savedEqualizerLevels[index]?.let { saved ->
                            runCatching { effect.setBandLevel(band, saved) }
                        }
                        EqBand(
                            index = index,
                            label = formatFrequency(effect.getCenterFreq(band)),
                            minMillibels = range[0],
                            maxMillibels = range[1],
                            millibels = runCatching { effect.getBandLevel(band) }
                                .getOrDefault(0),
                        )
                    }
                }.onFailure { failure ->
                    eqError = failure.message ?: "El ecualizador no respondió."
                    releaseEqualizer()
                }
            }
            .onFailure {
                eqError = "Este dispositivo no permite ecualizar el audio."
                eqBands = emptyList()
            }
    }

    private fun releaseEqualizer() {
        runCatching { equalizer?.release() }
        equalizer = null
        eqBands = emptyList()
    }

    private fun formatFrequency(milliHertz: Int): String {
        val hertz = (milliHertz / 1000).coerceAtLeast(0)
        return if (hertz >= 1000) {
            val whole = hertz / 1000
            val tenths = (hertz % 1000) / 100
            if (tenths == 0) "$whole kHz" else "$whole,$tenths kHz"
        } else {
            "$hertz Hz"
        }
    }

    private fun requestFocus(): Boolean {
        return audioManager.requestAudioFocus(focusRequest) ==
            AudioManager.AUDIOFOCUS_REQUEST_GRANTED
    }

    private fun abandonFocus() {
        runCatching { audioManager.abandonAudioFocusRequest(focusRequest) }
    }
}
