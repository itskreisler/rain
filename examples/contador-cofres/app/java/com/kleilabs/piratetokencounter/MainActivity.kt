package com.kleilabs.piratetokencounter

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.kleilabs.piratetokencounter.model.OverlayStyle
import com.kleilabs.piratetokencounter.overlay.OverlayService
import com.kleilabs.piratetokencounter.ui.AppTopBar
import com.kleilabs.piratetokencounter.ui.CounterActions
import com.kleilabs.piratetokencounter.ui.CounterScreen
import com.kleilabs.piratetokencounter.ui.DashboardScreen
import com.kleilabs.piratetokencounter.ui.HistoryScreen
import com.kleilabs.piratetokencounter.ui.SettingsActions
import com.kleilabs.piratetokencounter.ui.SettingsScreen
import com.kleilabs.piratetokencounter.ui.TAB_AJUSTES
import com.kleilabs.piratetokencounter.ui.TAB_CONTADOR
import com.kleilabs.piratetokencounter.ui.TAB_HISTORIAL
import com.kleilabs.piratetokencounter.ui.TAB_RESUMEN
import com.kleilabs.piratetokencounter.ui.tabSubtitle
import com.kleilabs.piratetokencounter.ui.tabTitle

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Contador.init(this)

        // La escena ocupa toda la pantalla; las barras del sistema quedan
        // transparentes y los iconos en claro sobre el fondo oscuro.
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.navigationBarColor = android.graphics.Color.TRANSPARENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }

        setContent {
            ContadorApp()
        }
    }
}

/** La paleta del Contador, tal y como la define el `DESIGN.md` del escritorio. */
private val ContadorColors = darkColorScheme(
    primary = Palette.gold,
    onPrimary = Palette.ground,
    primaryContainer = Palette.pirateRed,
    onPrimaryContainer = Palette.ink,
    secondary = Palette.team,
    onSecondary = Palette.ground,
    background = Palette.ground,
    onBackground = Palette.ink,
    surface = Palette.panel,
    onSurface = Palette.ink,
    surfaceVariant = Palette.helpSurface,
    onSurfaceVariant = Palette.muted,
    outline = Palette.line,
    outlineVariant = Palette.line,
    error = Palette.warn,
    onError = Palette.ground,
)

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
private fun ContadorApp() {
    val context = LocalContext.current
    val state by Contador.state
    var tab by rememberSaveable { mutableIntStateOf(TAB_RESUMEN) }
    var overlayGranted by remember { mutableStateOf(Settings.canDrawOverlays(context)) }

    // El permiso de superposición se concede desde los ajustes del sistema:
    // al volver hay que volver a mirarlo. De paso se comprueba que el
    // servicio siga en pie, porque algunos móviles matan las apps en segundo
    // plano.
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                overlayGranted = Settings.canDrawOverlays(context)
                if (state.config.overlayEnabled && overlayGranted) {
                    OverlayService.start(context)
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    // Sin permiso de notificaciones el servicio en primer plano sigue
    // funcionando, pero el jugador no ve que la superposición está activa.
    val notificationPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) { }

    MaterialTheme(colorScheme = ContadorColors) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Palette.ground,
            topBar = {
                AppTopBar(
                    title = tabTitle(tab),
                    subtitle = tabSubtitle(tab, state),
                )
            },
            bottomBar = {
                NavigationBar(containerColor = Palette.panel) {
                    NavigationItem(TAB_RESUMEN, "Resumen", R.drawable.ic_dashboard, tab) { tab = it }
                    NavigationItem(TAB_CONTADOR, "Contador", R.drawable.ic_inventory, tab) { tab = it }
                    NavigationItem(TAB_HISTORIAL, "Historial", R.drawable.ic_history, tab) { tab = it }
                    NavigationItem(TAB_AJUSTES, "Ajustes", R.drawable.ic_settings, tab) { tab = it }
                }
            },
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                AnimatedContent(
                    targetState = tab,
                    transitionSpec = {
                        fadeIn(tween(220)) togetherWith fadeOut(tween(160))
                    },
                    label = "páginas",
                ) { page ->
                    ContadorPage(
                        page = page,
                        state = state,
                        context = context,
                        overlayGranted = overlayGranted,
                        onRequestNotifications = { requestNotifications(context, notificationPermission) },
                    )
                }
            }
        }
    }
}

/** La página que se está viendo. */
@Composable
private fun ContadorPage(
    page: Int,
    state: AppState,
    context: Context,
    overlayGranted: Boolean,
    onRequestNotifications: () -> Unit,
) {
    when (page) {
        TAB_RESUMEN -> DashboardScreen(state = state)

        TAB_CONTADOR -> CounterScreen(
            state = state,
            actions = CounterActions(
                onTap = Contador::tap,
                onTreasure = Contador::tapTreasure,
                onStartMatch = Contador::startMatch,
                onFinishMatch = Contador::finishMatch,
                onUndo = Contador::undo,
            ),
        )

        TAB_HISTORIAL -> HistoryScreen(
            state = state,
            onDelete = History::delete,
        )

        else -> SettingsScreen(
            state = state,
            overlayPermissionGranted = overlayGranted,
            actions = buildSettingsActions(context, onRequestNotifications),
        )
    }
}

/**
 * Una pestaña de la barra de navegación.
 *
 * `NavigationBarItem` es una extensión de `RowScope`, así que esta función
 * también lo tiene que ser para poder llamarla desde la barra.
 */
@Composable
private fun androidx.compose.foundation.layout.RowScope.NavigationItem(
    index: Int,
    label: String,
    iconRes: Int,
    selected: Int,
    onSelect: (Int) -> Unit,
) {
    NavigationBarItem(
        selected = selected == index,
        onClick = { onSelect(index) },
        icon = {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
            )
        },
        label = { Text(label, fontSize = 11.sp) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Palette.ground,
            selectedTextColor = Palette.gold,
            indicatorColor = Palette.gold,
            unselectedIconColor = Palette.muted,
            unselectedTextColor = Palette.muted,
        ),
    )
}

/** Las acciones de la pantalla de ajustes. */
private fun buildSettingsActions(context: Context, requestNotifications: () -> Unit): SettingsActions = SettingsActions(
    onToggleOverlay = { enabled ->
        if (enabled) {
            Contador.updateConfig { it.copy(overlayEnabled = true) }
            if (Settings.canDrawOverlays(context)) {
                OverlayService.start(context)
            }
            requestNotifications()
        } else {
            Contador.updateConfig { it.copy(overlayEnabled = false) }
            OverlayService.stop(context)
        }
    },
    onOpenOverlaySettings = {
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.fromParts("package", context.packageName, null),
        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        runCatching { context.startActivity(intent) }
    },
    onOpenBatterySettings = {
        val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        runCatching { context.startActivity(intent) }
    },
    onStyle = { style: OverlayStyle -> Contador.updateConfig { it.copy(overlayStyle = style) } },
    onScale = { scale -> Contador.updateConfig { it.copy(overlayScale = scale) } },
    onOpacity = { opacity -> Contador.updateConfig { it.copy(overlayOpacity = opacity) } },
    onCompact = { compact -> Contador.updateConfig { it.copy(overlayCompact = compact) } },
    onLocked = { locked -> Contador.updateConfig { it.copy(overlayLocked = locked) } },
    onSelectProfile = Profiles::select,
    onAddProfile = Profiles::add,
    onDeleteProfile = Profiles::remove,
    onExportCsv = {
        History.shareCsv(context)?.let { share ->
            val chooser = Intent.createChooser(share, "Exportar el historial")
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            runCatching { context.startActivity(chooser) }
        }
    },
    onClearHistory = History::clear,
)

/** Pide el permiso de notificaciones en Android 13 y siguientes. */
private fun requestNotifications(context: Context, launcher: androidx.activity.result.ActivityResultLauncher<String>) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return
    val granted = context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) ==
        PackageManager.PERMISSION_GRANTED
    if (!granted) launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
}
