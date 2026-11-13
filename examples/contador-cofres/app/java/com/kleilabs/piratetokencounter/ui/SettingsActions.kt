package com.kleilabs.piratetokencounter.ui

import com.kleilabs.piratetokencounter.model.OverlayStyle

/** Acciones de los ajustes, agrupadas en un solo objeto. */
data class SettingsActions(
    val onToggleOverlay: (Boolean) -> Unit = {},
    val onOpenOverlaySettings: () -> Unit = {},
    val onOpenBatterySettings: () -> Unit = {},
    val onStyle: (OverlayStyle) -> Unit = {},
    val onScale: (Int) -> Unit = {},
    val onOpacity: (Int) -> Unit = {},
    val onCompact: (Boolean) -> Unit = {},
    val onLocked: (Boolean) -> Unit = {},
    val onSelectProfile: (String) -> Unit = {},
    val onAddProfile: (String) -> Unit = {},
    val onDeleteProfile: (String) -> Unit = {},
    val onExportCsv: () -> Unit = {},
    val onClearHistory: () -> Unit = {},
)
