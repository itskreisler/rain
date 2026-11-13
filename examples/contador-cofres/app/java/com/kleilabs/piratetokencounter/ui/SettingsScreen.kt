package com.kleilabs.piratetokencounter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kleilabs.piratetokencounter.AppState
import com.kleilabs.piratetokencounter.Palette
import com.kleilabs.piratetokencounter.Profiles
import com.kleilabs.piratetokencounter.Spacing
import com.kleilabs.piratetokencounter.model.AppConfig
import com.kleilabs.piratetokencounter.model.OverlayStyle

/**
 * Ajustes: superposición, cuenta y datos.
 *
 * Es la traducción móvil de la pantalla "Ajustes" del escritorio. Cada
 * bloque es un composable aparte: la pantalla es una lista de secciones.
 */
@Composable
fun SettingsScreen(
    state: AppState,
    overlayPermissionGranted: Boolean,
    actions: SettingsActions,
    modifier: Modifier = Modifier,
) {
    var newProfile by remember { mutableStateOf(false) }
    var confirmClear by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            start = Spacing.lg,
            end = Spacing.lg,
            top = Spacing.md,
            bottom = Spacing.xxl,
        ),
        verticalArrangement = Arrangement.spacedBy(Spacing.lg),
    ) {
        item(key = "overlay") {
            OverlaySection(
                state = state,
                permissionGranted = overlayPermissionGranted,
                actions = actions,
            )
        }
        item(key = "profiles") { ProfileSection(state = state, actions = actions, onAdd = { newProfile = true }) }
        item(key = "data") { DataSection(actions = actions, onClear = { confirmClear = true }) }
        item(key = "about") { AboutSection() }
    }

    if (newProfile) {
        AddProfileDialog(
            onConfirm = { name ->
                newProfile = false
                actions.onAddProfile(name)
            },
            onDismiss = { newProfile = false },
        )
    }

    if (confirmClear) {
        ClearHistoryDialog(
            onConfirm = {
                confirmClear = false
                actions.onClearHistory()
            },
            onDismiss = { confirmClear = false },
        )
    }
}

/** El interruptor de la superposición y el permiso del sistema. */
@Composable
private fun OverlaySection(state: AppState, permissionGranted: Boolean, actions: SettingsActions) {
    val config = state.config
    SectionCard(
        title = "Superposición",
        subtitle = "El contador por encima del juego",
    ) {
        LabeledSwitch(
            title = "Mostrar la superposición",
            subtitle = "Mantiene las cifras encima de lo que estés haciendo",
            checked = config.overlayEnabled,
            onCheckedChange = actions.onToggleOverlay,
        )

        if (!config.overlayEnabled) return@SectionCard

        if (!permissionGranted) {
            Spacer(Modifier.height(Spacing.sm))
            Text(
                text = "Falta el permiso del sistema para mostrar la superposición.",
                fontSize = 13.sp,
                color = Palette.warn,
            )
            Spacer(Modifier.height(Spacing.sm))
            Button(
                onClick = actions.onOpenOverlaySettings,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Palette.gold,
                    contentColor = Palette.ground,
                ),
            ) {
                Text("Conceder permiso")
            }
        }

        Spacer(Modifier.height(Spacing.md))
        OverlayAppearanceSection(config = config, actions = actions)
    }
}

/** El aspecto de la superposición: estilo, tamaño, opacidad y bloqueo. */
@Composable
private fun OverlayAppearanceSection(config: AppConfig, actions: SettingsActions) {
    Text(text = "Estilo", fontSize = 13.sp, color = Palette.muted)
    LazyRow(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
        items(OverlayStyle.entries.toList()) { style ->
            FilterChip(
                selected = config.overlayStyle == style,
                onClick = { actions.onStyle(style) },
                label = {
                    Text(
                        text = if (style == OverlayStyle.HUD) "HUD" else "Clásica",
                        fontSize = 13.sp,
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Palette.gold,
                    selectedLabelColor = Palette.ground,
                ),
            )
        }
    }

    Spacer(Modifier.height(Spacing.md))
    Text(text = "Tamaño · ${config.overlayScale} %", fontSize = 13.sp, color = Palette.muted)
    Slider(
        value = AppConfig.SCALES.indexOf(config.overlayScale).toFloat(),
        onValueChange = { index ->
            actions.onScale(AppConfig.SCALES[index.toInt().coerceIn(AppConfig.SCALES.indices)])
        },
        valueRange = 0f..(AppConfig.SCALES.size - 1).toFloat(),
        steps = AppConfig.SCALES.size - 2,
    )

    Text(text = "Opacidad · ${config.overlayOpacity} %", fontSize = 13.sp, color = Palette.muted)
    Slider(
        value = AppConfig.OPACITIES.indexOf(config.overlayOpacity).toFloat(),
        onValueChange = { index ->
            actions.onOpacity(AppConfig.OPACITIES[index.toInt().coerceIn(AppConfig.OPACITIES.indices)])
        },
        valueRange = 0f..(AppConfig.OPACITIES.size - 1).toFloat(),
        steps = AppConfig.OPACITIES.size - 2,
    )

    LabeledSwitch(
        title = "Modo compacto",
        subtitle = "Solo las cifras y un botón grande de cofre",
        checked = config.overlayCompact,
        onCheckedChange = actions.onCompact,
    )
    LabeledSwitch(
        title = "Bloquear",
        subtitle = "La superposición no recibe toques y no se puede mover",
        checked = config.overlayLocked,
        onCheckedChange = actions.onLocked,
    )

    Spacer(Modifier.height(Spacing.sm))
    Text(
        text = "Algunos móviles (Xiaomi, Oppo, Samsung) cierran las apps en segundo " +
            "plano. Si la superposición desaparece a mitad de partida, desactiva la " +
            "optimización de batería para esta app.",
        fontSize = 12.sp,
        color = Palette.muted,
    )
    Spacer(Modifier.height(Spacing.sm))
    OutlinedButton(onClick = actions.onOpenBatterySettings) {
        Text("Abrir los ajustes de batería")
    }
}

/** Las cuentas del jugador. */
@Composable
private fun ProfileSection(state: AppState, actions: SettingsActions, onAdd: () -> Unit) {
    val profiles = Profiles.list(state.data)
    val current = Profiles.current(state.data, state.config)
    SectionCard(
        title = "Cuenta",
        subtitle = "El historial se guarda separado por cuenta",
    ) {
        profiles.forEach { profile ->
            ProfileRow(
                name = profile.name,
                matches = state.data.matches.count { it.profileKey == profile.key },
                selected = profile.key == current?.key,
                canDelete = profiles.size > 1,
                onSelect = { actions.onSelectProfile(profile.key) },
                onDelete = { actions.onDeleteProfile(profile.key) },
            )
        }
        Spacer(Modifier.height(Spacing.sm))
        OutlinedButton(onClick = onAdd, modifier = Modifier.fillMaxWidth()) {
            Text("Añadir una cuenta")
        }
    }
}

/** Una cuenta en la lista. */
@Composable
private fun ProfileRow(
    name: String,
    matches: Int,
    selected: Boolean,
    canDelete: Boolean,
    onSelect: () -> Unit,
    onDelete: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                fontSize = 15.sp,
                color = if (selected) Palette.gold else Palette.ink,
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            )
            Text(text = "$matches partidas", fontSize = 11.sp, color = Palette.muted)
        }
        if (!selected) {
            TextButton(onClick = onSelect) { Text("Elegir") }
        }
        if (canDelete) {
            TextButton(onClick = onDelete) {
                Text("Borrar", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

/** La exportación y el borrado del historial. */
@Composable
private fun DataSection(actions: SettingsActions, onClear: () -> Unit) {
    SectionCard(
        title = "Tus datos",
        subtitle = "Todo se guarda en este teléfono y no sale de él",
    ) {
        OutlinedButton(
            onClick = actions.onExportCsv,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Exportar el historial en CSV")
        }
        Spacer(Modifier.height(Spacing.sm))
        OutlinedButton(
            onClick = onClear,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.error,
            ),
        ) {
            Text("Borrar todas las partidas")
        }
    }
}

/** Créditos y avisos legales. */
@Composable
private fun AboutSection() {
    SectionCard(title = "Acerca de") {
        Text(text = "Contador · versión 1.0", fontSize = 14.sp, color = Palette.ink)
        Spacer(Modifier.height(Spacing.sm))
        Text(
            text = "Port móvil del Contador de cofres para Windows (Axxon-g, licencia MIT). " +
                "Allí las cuentas se leen de las repeticiones del juego; en el móvil Fortnite " +
                "no guarda repeticiones, así que aquí las cuentas las pones tú y el recuento " +
                "lo haces a mano con la superposición.",
            fontSize = 12.sp,
            color = Palette.muted,
        )
        Spacer(Modifier.height(Spacing.sm))
        Text(
            text = "No es una aplicación oficial ni está aprobada por Epic Games. " +
                "Fortnite es una marca de Epic Games.",
            fontSize = 12.sp,
            color = Palette.muted,
        )
    }
}

/** El diálogo para crear una cuenta nueva. */
@Composable
private fun AddProfileDialog(onConfirm: (String) -> Unit, onDismiss: () -> Unit) {
    var name by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nueva cuenta") },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                singleLine = true,
            )
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(name) }, enabled = name.isNotBlank()) {
                Text("Crear")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        },
    )
}

/** La confirmación antes de borrar todo el historial. */
@Composable
private fun ClearHistoryDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("¿Borrar todas las partidas?") },
        text = { Text("Se borra el historial de todas las cuentas. No se puede deshacer.") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Borrar", color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        },
    )
}
