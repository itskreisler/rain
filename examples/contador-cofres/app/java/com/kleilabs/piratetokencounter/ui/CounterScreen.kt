package com.kleilabs.piratetokencounter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kleilabs.piratetokencounter.AppState
import com.kleilabs.piratetokencounter.Palette
import com.kleilabs.piratetokencounter.Spacing
import com.kleilabs.piratetokencounter.model.ChestKind
import com.kleilabs.piratetokencounter.model.CounterEvent
import com.kleilabs.piratetokencounter.model.GameModes
import com.kleilabs.piratetokencounter.model.TreasureKind

/**
 * El contador: la partida en curso y la rejilla de contenedores.
 *
 * Es la misma rejilla que la superposición, para poder contar también sin
 * estar jugando.
 */
@Composable
fun CounterScreen(state: AppState, actions: CounterActions, modifier: Modifier = Modifier) {
    val haptics = LocalHapticFeedback.current
    val live = state.data.live
    var pickerOpen by remember { mutableStateOf(false) }

    val counts = remember(live) {
        live?.events.orEmpty()
            .filterIsInstance<CounterEvent.Opened>()
            .groupingBy { it.kind }
            .eachCount()
    }

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
        item(key = "live") {
            LiveMatchCard(state = state, actions = actions)
        }

        item(key = "modes") {
            SectionCard(
                title = "Modo de la partida",
                subtitle = "Elige el modo para saber de qué partida hablas",
            ) {
                ModeChips(
                    selected = live?.mode ?: state.config.lastMode,
                    onSelect = actions.onStartMatch,
                )
            }
        }

        item(key = "grid") {
            SectionCard(
                title = "Contenedores",
                subtitle = "Toca lo que abras. Los cofres suman al total.",
            ) {
                ChestGrid(
                    counts = counts,
                    onTap = { kind ->
                        haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        actions.onTap(kind)
                    },
                )
            }
        }

        item(key = "treasures") {
            TreasuresSection(
                live = live,
                onRegister = { pickerOpen = true },
            )
        }
    }

    if (pickerOpen) {
        TreasurePicker(
            onPick = { kind ->
                pickerOpen = false
                haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                actions.onTreasure(kind)
            },
            onDismiss = { pickerOpen = false },
        )
    }
}

/** Los tesoros de la partida en curso y el botón para registrar uno. */
@Composable
private fun TreasuresSection(live: com.kleilabs.piratetokencounter.model.LiveMatch?, onRegister: () -> Unit) {
    SectionCard(
        title = "Tesoros",
        subtitle = "Artilugios y recompensas del Cazatesoros",
    ) {
        val found = live?.events.orEmpty()
            .filterIsInstance<CounterEvent.Found>()
            .groupingBy { it.kind }
            .eachCount()
        if (found.isEmpty()) {
            Text(
                text = "Todavía no hay tesoros en esta partida.",
                fontSize = 13.sp,
                color = Palette.muted,
            )
        } else {
            found.forEach { (kind, count) ->
                KindBar(
                    label = kind.label,
                    count = count,
                    max = found.values.maxOrNull() ?: 1,
                    color = Color(kind.color),
                )
            }
            Spacer(Modifier.height(Spacing.sm))
        }
        Spacer(Modifier.height(Spacing.md))
        Button(
            onClick = onRegister,
            colors = ButtonDefaults.buttonColors(
                containerColor = Palette.panel,
                contentColor = Palette.star,
            ),
        ) {
            Text("Registrar un tesoro")
        }
    }
}

/** La cabecera de la partida en curso. */
@Composable
private fun LiveMatchCard(state: AppState, actions: CounterActions) {
    val live = state.data.live
    SectionCard(
        title = if (live == null) "Sin partida en curso" else "Partida en curso",
        subtitle = live?.mode ?: "Empieza una partida para que el recuento quede guardado",
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(Spacing.lg)) {
            StatTile(
                value = (live?.chestsOpened ?: 0).toString(),
                label = "Cofres",
                accent = Palette.gold,
                modifier = Modifier.weight(1f),
            )
            StatTile(
                value = "★ ${live?.treasures ?: 0}",
                label = "Tesoros",
                accent = Palette.star,
                modifier = Modifier.weight(1f),
            )
            StatTile(
                value = (live?.otherInteractions ?: 0).toString(),
                label = "Contenedores",
                modifier = Modifier.weight(1f),
            )
        }
        Spacer(Modifier.height(Spacing.md))
        if (live == null) {
            Button(
                onClick = { actions.onStartMatch(state.config.lastMode) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Palette.gold,
                    contentColor = Palette.ground,
                ),
            ) {
                Text("Empezar partida")
            }
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                Button(
                    onClick = actions.onFinishMatch,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Palette.gold,
                        contentColor = Palette.ground,
                    ),
                ) {
                    Text("Terminar partida")
                }
                TextButton(onClick = actions.onUndo) {
                    Text("Deshacer", color = Palette.muted)
                }
            }
        }
    }
}

/** Los modos de juego, como una fila de chips. */
@Composable
private fun ModeChips(selected: String, onSelect: (String) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
        items(GameModes.ALL) { mode ->
            FilterChip(
                selected = mode == selected,
                onClick = { onSelect(mode) },
                label = { Text(mode, fontSize = 13.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Palette.gold,
                    selectedLabelColor = Palette.ground,
                ),
            )
        }
    }
}

/** El diálogo para elegir qué tesoro se ha encontrado. */
@Composable
private fun TreasurePicker(onPick: (TreasureKind) -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("¿Qué tesoro has encontrado?") },
        text = {
            LazyColumn {
                items(TreasureKind.entries) { kind ->
                    TextButton(
                        onClick = { onPick(kind) },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = kind.label,
                                fontSize = 15.sp,
                                color = Palette.ink,
                            )
                            Text(
                                text = if (kind.gizmo) {
                                    "Artilugio · 1 de cada ${kind.odds} cofres"
                                } else {
                                    "1 de cada ${kind.odds} cofres"
                                },
                                fontSize = 11.sp,
                                color = Palette.muted,
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        },
    )
}
