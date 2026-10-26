package com.kyant.backdrop.music

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.session.MediaSession
import android.media.session.PlaybackState
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MusicService : Service() {

    private val binder = MusicBinder()
    private var mediaSession: MediaSession? = null
    private var controller: PlayerController? = null

    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mediaSession = MediaSession(this, "MusicServiceSession").apply {
                setFlags(MediaSession.FLAG_HANDLES_MEDIA_BUTTONS or MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS)
                isActive = true
            }
        }
    }

    fun attachController(playerController: PlayerController) {
        controller = playerController
        playerController.onStateChanged = {
            updateNotification()
        }
        updateNotification()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_PLAY -> controller?.toggle()
            ACTION_PAUSE -> controller?.toggle()
            ACTION_NEXT -> controller?.next()
            ACTION_PREVIOUS -> controller?.previous()
        }
        updateNotification()
        return START_STICKY
    }

    fun updateNotification() {
        val current = controller?.currentTrack
        val isPlaying = controller?.isPlaying == true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val state = if (isPlaying) PlaybackState.STATE_PLAYING else PlaybackState.STATE_PAUSED
            mediaSession?.setPlaybackState(
                PlaybackState.Builder()
                    .setState(state, controller?.positionMs ?: 0L, 1.0f)
                    .setActions(
                        PlaybackState.ACTION_PLAY or PlaybackState.ACTION_PAUSE or
                            PlaybackState.ACTION_SKIP_TO_NEXT or PlaybackState.ACTION_SKIP_TO_PREVIOUS,
                    )
                    .build(),
            )
        }

        val playPauseIntent = PendingIntent.getService(
            this, 0,
            Intent(this, MusicService::class.java).apply { action = if (isPlaying) ACTION_PAUSE else ACTION_PLAY },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val nextIntent = PendingIntent.getService(
            this, 1,
            Intent(this, MusicService::class.java).apply { action = ACTION_NEXT },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val prevIntent = PendingIntent.getService(
            this, 2,
            Intent(this, MusicService::class.java).apply { action = ACTION_PREVIOUS },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

        val openAppIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

        val title = current?.title ?: "Backdrop Music"
        val subtitle = if (current != null) "${current.artist} · ${current.album}" else "Listo para reproducir"

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(subtitle)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentIntent(openAppIntent)
            .setOngoing(isPlaying)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .addAction(android.R.drawable.ic_media_previous, "Anterior", prevIntent)
            .addAction(
                if (isPlaying) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play,
                if (isPlaying) "Pausar" else "Reproducir",
                playPauseIntent,
            )
            .addAction(android.R.drawable.ic_media_next, "Siguiente", nextIntent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0, 1, 2),
            )
        }

        startForeground(NOTIFICATION_ID, builder.build())
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onDestroy() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mediaSession?.release()
        }
        super.onDestroy()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Backdrop Music Playback",
                NotificationManager.IMPORTANCE_LOW,
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "backdrop_music_channel"
        const val NOTIFICATION_ID = 1001
        const val ACTION_PLAY = "com.kyant.backdrop.music.PLAY"
        const val ACTION_PAUSE = "com.kyant.backdrop.music.PAUSE"
        const val ACTION_NEXT = "com.kyant.backdrop.music.NEXT"
        const val ACTION_PREVIOUS = "com.kyant.backdrop.music.PREVIOUS"
    }
}
