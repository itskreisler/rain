package com.kleilabs.piratetokencounter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kleilabs.piratetokencounter.Palette
import com.kleilabs.piratetokencounter.Radii
import com.kleilabs.piratetokencounter.Spacing

/**
 * Una cifra con su etiqueta.
 *
 * El valor manda: es lo que el jugador consulta de un vistazo.
 */
@Composable
fun StatTile(value: String, label: String, modifier: Modifier = Modifier, accent: Color = Palette.ink) {
    Column(modifier = modifier) {
        Text(
            text = value,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = accent,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = label,
            fontSize = 11.sp,
            color = Palette.muted,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/** El total: la cifra más grande de la app. */
@Composable
fun HeroTotal(value: String, caption: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "COFRES EN TOTAL",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Palette.muted,
            letterSpacing = 1.4.sp,
        )
        Spacer(Modifier.height(Spacing.xs))
        Text(
            text = value,
            fontSize = 56.sp,
            fontWeight = FontWeight.Bold,
            color = Palette.gold,
            maxLines = 1,
        )
        Text(
            text = caption,
            fontSize = 12.sp,
            color = Palette.muted,
        )
    }
}

/** Una tarjeta con título, para agrupar contenido. */
@Composable
fun SectionCard(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    trailing: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Radii.panel))
            .background(Palette.panel)
            .padding(Spacing.lg),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Palette.ink,
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        fontSize = 12.sp,
                        color = Palette.muted,
                    )
                }
            }
            trailing?.invoke()
        }
        Spacer(Modifier.height(Spacing.md))
        content()
    }
}

/**
 * Una barra de reparto: etiqueta, barra y cantidad.
 *
 * Es el gráfico por tipo de cofre del informe del escritorio, en una línea.
 */
@Composable
fun KindBar(label: String, count: Int, max: Int, color: Color, modifier: Modifier = Modifier, suffix: String? = null) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            color = Palette.ink,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(112.dp),
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Palette.track),
        ) {
            val fraction = if (max <= 0) 0f else count.toFloat() / max
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction.coerceIn(0f, 1f))
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color),
            )
        }
        Spacer(Modifier.width(Spacing.sm))
        Text(
            text = suffix ?: count.toString(),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Palette.ink,
        )
    }
}

/** Un interruptor con su etiqueta y su explicación. */
@Composable
fun LabeledSwitch(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    enabled: Boolean = true,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.md),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 15.sp,
                color = if (enabled) Palette.ink else Palette.muted,
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Palette.muted,
                )
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
        )
    }
}

/** Un punto de color junto a su etiqueta: el color nunca va solo. */
@Composable
fun ColorDot(color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(10.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(color),
    )
}

/** Separador vertical fino entre bloques. */
@Composable
fun ThinDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Palette.line),
    )
}
