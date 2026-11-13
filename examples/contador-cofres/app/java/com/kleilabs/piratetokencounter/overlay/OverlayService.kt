package com.kleilabs.piratetokencounter.overlay

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import androidx.core.app.NotificationCompat
import com.kleilabs.piratetokencounter.AppState
import com.kleilabs.piratetokencounter.Changes
import com.kleilabs.piratetokencounter.Contador
import com.kleilabs.piratetokencounter.R
import kotlin.math.roundToInt

/**
 * Mantiene viva la superposición mientras se juega.
 *
 * Va en primer plano porque una superposición pertenece a la app que la
 * crea: en cuanto el proceso puede ser eliminado, la ventana se va con él.
 * El tipo `specialUse` es el previsto para casos que no encajan en otro tipo
 * de servicio en primer plano; el subtipo está declarado en el manifest.
 */
class OverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private var panel: OverlayPanel? = null
    private var params: WindowManager.LayoutParams? = null

    private val listener: (AppState) -> Unit = { state -> render(state) }

    private val actions = OverlayActions(
        onTap = { kind -> Contador.tap(kind) },
        onTreasure = { kind -> Contador.tapTreasure(kind) },
        onUndo = { Contador.undo() },
        onFinishMatch = { Contador.finishMatch() },
        onToggleTreasures = { panel?.toggleTreasures(Contador.state.value) },
        onToggleCompact = { Contador.updateConfig { it.copy(overlayCompact = !it.overlayCompact) } },
        onToggleLock = { Contador.updateConfig { it.copy(overlayLocked = !it.overlayLocked) } },
        onClose = { stopSelf() },
        onDrag = { dx, dy -> moveBy(dx, dy) },
    )

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        Contador.init(applicationContext)
        windowManager = getSystemService(WindowManager::class.java)
        createChannel()
        startForegroundCompat()
        show()
        Changes.subscribe(listener)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP -> stopSelf()
            ACTION_FINISH_MATCH -> Contador.finishMatch()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        Changes.unsubscribe(listener)
        hide()
        super.onDestroy()
    }

    /** Crea la ventana flotante y la cuelga sobre las demás apps. */
    private fun show() {
        if (panel != null) return
        if (!Settings.canDrawOverlays(this)) {
            Log.w(TAG, "sin permiso de superposición, el servicio se para")
            stopSelf()
            return
        }
        val layout = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            buildFlags(),
            PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.TOP or Gravity.END
            x = Contador.state.value.config.overlayX ?: dp(12)
            y = Contador.state.value.config.overlayY ?: dp(56)
        }
        val created = OverlayPanel(this, actions)
        runCatching { windowManager.addView(created.view, layout) }
            .onFailure { failure ->
                Log.w(TAG, "no se pudo añadir la superposición", failure)
                stopSelf()
                return
            }
        panel = created
        params = layout
        created.render(Contador.state.value)
    }

    /** Quita la ventana flotante. */
    private fun hide() {
        val view = panel?.view ?: return
        runCatching { windowManager.removeView(view) }
        panel = null
        params = null
    }

    /** Vuelve a pintar el panel con el estado actual. */
    private fun render(state: AppState) {
        panel?.render(state)
        val layout = params ?: return
        val wanted = buildFlags()
        if (layout.flags != wanted) {
            layout.flags = wanted
            runCatching { windowManager.updateViewLayout(panel?.view, layout) }
        }
    }

    /** Mueve la superposición y guarda la posición. */
    private fun moveBy(dx: Int, dy: Int) {
        val layout = params ?: return
        layout.x += dx
        layout.y += dy
        runCatching { windowManager.updateViewLayout(panel?.view, layout) }
        Contador.updateConfig { it.copy(overlayX = layout.x, overlayY = layout.y) }
    }

    /**
     * Banderas de la ventana.
     *
     * `NOT_FOCUSABLE` deja el foco al juego, así que los botones del overlay
     * funcionan sin que el teclado se quede atrapado en la superposición.
     * Bloqueada, la ventana no recibe toques y el ratón la atraviesa.
     */
    private fun buildFlags(): Int {
        val base = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
        return if (Contador.state.value.config.overlayLocked) {
            base or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        } else {
            base
        }
    }

    private fun startForegroundCompat() {
        val notification = buildNotification()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(
                NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE,
            )
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    private fun buildNotification(): Notification {
        val open = PendingIntent.getActivity(
            this,
            0,
            packageManager.getLaunchIntentForPackage(packageName),
            PendingIntent.FLAG_IMMUTABLE,
        )
        val finish = PendingIntent.getService(
            this,
            1,
            Intent(this, OverlayService::class.java).setAction(ACTION_FINISH_MATCH),
            PendingIntent.FLAG_IMMUTABLE,
        )
        val stop = PendingIntent.getService(
            this,
            2,
            Intent(this, OverlayService::class.java).setAction(ACTION_STOP),
            PendingIntent.FLAG_IMMUTABLE,
        )
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_contador)
            .setContentTitle("Contador de cofres")
            .setContentText("La superposición está activa")
            .setOngoing(true)
            .setSilent(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentIntent(open)
            .addAction(0, "Terminar partida", finish)
            .addAction(0, "Ocultar", stop)
            .build()
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Superposición del contador",
            NotificationManager.IMPORTANCE_LOW,
        ).apply {
            description = "Mantiene la superposición del contador de cofres activa"
            setShowBadge(false)
        }
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).roundToInt()

    companion object {
        private const val TAG = "Contador"
        private const val CHANNEL_ID = "contador_overlay"
        private const val NOTIFICATION_ID = 41
        const val ACTION_STOP = "com.kleilabs.piratetokencounter.STOP"
        const val ACTION_FINISH_MATCH = "com.kleilabs.piratetokencounter.FINISH_MATCH"

        /** Arranca el servicio y con él la superposición. */
        fun start(context: android.content.Context) {
            val intent = Intent(context, OverlayService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        /** Detiene el servicio y cierra la superposición. */
        fun stop(context: android.content.Context) {
            context.stopService(Intent(context, OverlayService::class.java))
        }
    }
}
