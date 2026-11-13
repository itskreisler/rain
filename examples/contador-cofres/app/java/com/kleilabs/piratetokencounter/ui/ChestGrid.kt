package com.kleilabs.piratetokencounter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kleilabs.piratetokencounter.Palette
import com.kleilabs.piratetokencounter.Radii
import com.kleilabs.piratetokencounter.Spacing
import com.kleilabs.piratetokencounter.model.ChestKind

/**
 * La rejilla de contenedores.
 *
 * Es la misma rejilla que la superposición, pero con la tipografía y los
 * tamaños de la app: los cofres suman al total y el resto de contenedores se
 * registra aparte, marcado como "(fuera del total)".
 */
@Composable
fun ChestGrid(counts: Map<ChestKind, Int>, onTap: (ChestKind) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(Spacing.sm)) {
        ChestKind.chests.chunked(COLUMNS).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                row.forEach { kind ->
                    ChestButton(
                        kind = kind,
                        count = counts[kind] ?: 0,
                        onTap = { onTap(kind) },
                        modifier = Modifier.weight(1f),
                    )
                }
                repeat(COLUMNS - row.size) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
        Spacer(Modifier.height(Spacing.xs))
        Text(
            text = "OTROS CONTENEDORES · FUERA DEL TOTAL",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Palette.muted,
            letterSpacing = 1.sp,
        )
        ChestKind.containers.chunked(COLUMNS).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                row.forEach { kind ->
                    ChestButton(
                        kind = kind,
                        count = counts[kind] ?: 0,
                        onTap = { onTap(kind) },
                        modifier = Modifier.weight(1f),
                    )
                }
                repeat(COLUMNS - row.size) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

/** Un botón de la rejilla: color del tipo, nombre y cuántas veces se ha usado. */
@Composable
private fun ChestButton(kind: ChestKind, count: Int, onTap: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .heightIn(min = 64.dp)
            .clip(RoundedCornerShape(Radii.control))
            .background(Palette.panel)
            .clickable(onClick = onTap)
            .padding(horizontal = Spacing.md, vertical = Spacing.sm)
            .semantics { contentDescription = "${kind.label}, $count registrados" },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ColorDot(Color(kind.color))
            Spacer(Modifier.width(Spacing.sm))
            Text(
                text = kind.shortLabel,
                fontSize = 13.sp,
                color = Palette.ink,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(Modifier.height(Spacing.xs))
        Text(
            text = count.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = if (count > 0) Color(kind.color) else Palette.muted,
        )
    }
}

/** Columnas de la rejilla. */
private const val COLUMNS = 2
