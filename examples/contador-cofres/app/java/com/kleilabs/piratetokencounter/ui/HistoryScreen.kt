package com.kleilabs.piratetokencounter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.kleilabs.piratetokencounter.AppState
import com.kleilabs.piratetokencounter.Palette
import com.kleilabs.piratetokencounter.Profiles
import com.kleilabs.piratetokencounter.Radii
import com.kleilabs.piratetokencounter.Spacing
import com.kleilabs.piratetokencounter.core.Breakdown
import com.kleilabs.piratetokencounter.model.CounterEvent
import com.kleilabs.piratetokencounter.model.MatchRecord
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Historial: todas las partidas guardadas, con buscador y detalle.
 *
 * Es la traducción móvil de la pantalla "Historial" del escritorio, sin la
 * tabla ordenable: en un teléfono manda la lista y el detalle al tocar.
 */
@Composable
fun HistoryScreen(state: AppState, onDelete: (String) -> Unit, modifier: Modifier = Modifier) {
    val matches = Profiles.matches(state.data, state.config)
        .sortedByDescending { it.endedAtMillis }
    var query by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf<MatchRecord?>(null) }

    val filtered = remember(matches, query) {
        if (query.isBlank()) {
            matches
        } else {
            matches.filter {
                val date = DATE_FORMAT.format(Date(it.endedAtMillis))
                date.contains(query, ignoreCase = true) || it.mode.contains(query, ignoreCase = true)
            }
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.lg, vertical = Spacing.md),
            label = { Text("Buscar por fecha o modo") },
            singleLine = true,
        )

        if (filtered.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Spacing.lg, vertical = Spacing.xxl),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = if (matches.isEmpty()) {
                        "Todavía no hay partidas."
                    } else {
                        "Ninguna partida coincide con la búsqueda."
                    },
                    fontSize = 15.sp,
                    color = Palette.ink,
                )
                Spacer(Modifier.height(Spacing.sm))
                Text(
                    text = "Empieza una partida en la pestaña Contador para empezar a contar.",
                    fontSize = 13.sp,
                    color = Palette.muted,
                )
            }
            return@Column
        }

        LazyColumn(
            contentPadding = PaddingValues(
                start = Spacing.lg,
                end = Spacing.lg,
                bottom = Spacing.xxl,
            ),
            verticalArrangement = Arrangement.spacedBy(Spacing.sm),
        ) {
            items(filtered, key = { it.id }) { match ->
                MatchRowItem(match = match, onClick = { selected = match })
            }
        }
    }

    val detail = selected
    if (detail != null) {
        MatchDetailDialog(
            match = detail,
            onDelete = {
                onDelete(detail.id)
                selected = null
            },
            onDismiss = { selected = null },
        )
    }
}

/** Una partida en la lista. */
@Composable
private fun MatchRowItem(match: MatchRecord, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Radii.control))
            .background(Palette.panel)
            .clickable(onClick = onClick)
            .padding(Spacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = match.mode,
                fontSize = 15.sp,
                color = Palette.ink,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = DATE_FORMAT.format(Date(match.endedAtMillis)) +
                    " · ${match.durationMillis / 60000L} min",
                fontSize = 11.sp,
                color = Palette.muted,
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "${match.chestsOpened}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Palette.gold,
            )
            Text(
                text = "★ ${match.treasures}",
                fontSize = 11.sp,
                color = Palette.star,
            )
        }
    }
}

/** El detalle de una partida. */
@Composable
private fun MatchDetailDialog(match: MatchRecord, onDelete: () -> Unit, onDismiss: () -> Unit) {
    val byKind = Breakdown.byKind(listOf(match))
    val byContainer = Breakdown.byContainer(listOf(match))
    val treasures = match.events.filterIsInstance<CounterEvent.Found>()
    val max = byKind.values.maxOrNull() ?: 0

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(match.mode) },
        text = {
            Column {
                Text(
                    text = DATE_FORMAT.format(Date(match.endedAtMillis)),
                    fontSize = 12.sp,
                    color = Palette.muted,
                )
                Spacer(Modifier.height(Spacing.md))
                Row(horizontalArrangement = Arrangement.spacedBy(Spacing.lg)) {
                    StatTile(value = match.chestsOpened.toString(), label = "Cofres")
                    StatTile(value = "★ ${match.treasures}", label = "Tesoros", accent = Palette.star)
                    StatTile(value = match.otherInteractions.toString(), label = "Contenedores")
                }
                if (byKind.isNotEmpty()) {
                    Spacer(Modifier.height(Spacing.md))
                    byKind.forEach { (kind, count) ->
                        KindBar(
                            label = kind.label,
                            count = count,
                            max = max,
                            color = Color(kind.color),
                        )
                    }
                }
                if (byContainer.isNotEmpty()) {
                    Spacer(Modifier.height(Spacing.sm))
                    Text(
                        text = "FUERA DEL TOTAL",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Palette.muted,
                        letterSpacing = 1.sp,
                    )
                    byContainer.forEach { (kind, count) ->
                        KindBar(
                            label = kind.label,
                            count = count,
                            max = max,
                            color = Color(kind.color),
                        )
                    }
                }
                if (treasures.isNotEmpty()) {
                    Spacer(Modifier.height(Spacing.md))
                    Text(
                        text = "Tesoros: " + treasures.joinToString(", ") { it.kind.label },
                        fontSize = 12.sp,
                        color = Palette.star,
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDelete) {
                Text("Borrar", color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cerrar") }
        },
    )
}

private val DATE_FORMAT = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
