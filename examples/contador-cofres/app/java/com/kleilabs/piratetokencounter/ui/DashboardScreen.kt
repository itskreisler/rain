package com.kleilabs.piratetokencounter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kleilabs.piratetokencounter.AppState
import com.kleilabs.piratetokencounter.Palette
import com.kleilabs.piratetokencounter.Profiles
import com.kleilabs.piratetokencounter.Spacing
import com.kleilabs.piratetokencounter.core.Breakdown
import com.kleilabs.piratetokencounter.core.DayCount
import com.kleilabs.piratetokencounter.core.Stats
import com.kleilabs.piratetokencounter.model.MatchRecord
import com.kleilabs.piratetokencounter.model.TreasureKind
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

/**
 * Resumen: el total, las medias y los desgloses.
 *
 * Es la traducción móvil de la pantalla "Resumen" del Contador de escritorio.
 * Cada bloque es un composable aparte para que la pantalla se lea como una
 * lista de secciones y no como un bloque único.
 */
@Composable
fun DashboardScreen(state: AppState, modifier: Modifier = Modifier) {
    val matches = Profiles.matches(state.data, state.config)

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
        item(key = "hero") { HeroSection(state) }
        item(key = "treasures") { TreasuresSection(matches) }
        item(key = "by-kind") { ByKindSection(matches) }
        item(key = "by-day") { ByDaySection(matches) }
        item(key = "recent") { RecentSection(matches) }
    }
}

/** El total y las medias de la cuenta seleccionada. */
@Composable
private fun HeroSection(state: AppState) {
    val matches = Profiles.matches(state.data, state.config)
    val session = Stats.sessionChests(matches, state.data.sessionStartMillis)
    val live = state.data.live

    SectionCard(title = "Tu botín") {
        HeroTotal(
            value = (Stats.chests(matches) + (live?.chestsOpened ?: 0)).toString(),
            caption = "$session cofres desde que abriste la app",
        )
        Spacer(Modifier.height(Spacing.lg))
        Row(horizontalArrangement = Arrangement.spacedBy(Spacing.lg)) {
            StatTile(
                value = matches.size.toString(),
                label = "Partidas",
                modifier = Modifier.weight(1f),
            )
            StatTile(
                value = "%.1f".format(Locale.US, Stats.average(matches)),
                label = "Media por partida",
                modifier = Modifier.weight(1f),
            )
            StatTile(
                value = Stats.best(matches).toString(),
                label = "Récord",
                accent = Palette.gold,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

/** Los tesoros encontrados y lo raro que es sacarlos. */
@Composable
private fun TreasuresSection(matches: List<MatchRecord>) {
    val treasures = Stats.treasures(matches)
    SectionCard(
        title = "Tesoros",
        subtitle = "Los que salen de los cofres durante el Cazatesoros",
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(Spacing.lg)) {
            StatTile(
                value = "★ $treasures",
                label = "Tesoros encontrados",
                accent = Palette.star,
                modifier = Modifier.weight(1f),
            )
            StatTile(
                value = Stats.chestsPerTreasure(matches).toString(),
                label = "Cofres por tesoro",
                modifier = Modifier.weight(1f),
            )
        }
        Spacer(Modifier.height(Spacing.md))
        Text(
            text = treasureChanceText(Stats.chests(matches)),
            fontSize = 12.sp,
            color = Palette.muted,
        )
    }
}

/** El reparto por tipo de cofre y por tipo de contenedor. */
@Composable
private fun ByKindSection(matches: List<MatchRecord>) {
    val byKind = Breakdown.byKind(matches)
    val byContainer = Breakdown.byContainer(matches)

    SectionCard(title = "Por tipo de cofre") {
        if (byKind.isEmpty() && byContainer.isEmpty()) {
            Text(
                text = "Todavía no hay contenedores registrados.",
                fontSize = 13.sp,
                color = Palette.muted,
            )
            return@SectionCard
        }
        val max = (byKind.values.maxOrNull() ?: 0)
            .coerceAtLeast(byContainer.values.maxOrNull() ?: 0)
        byKind.forEach { (kind, count) ->
            KindBar(
                label = kind.label,
                count = count,
                max = max,
                color = Color(kind.color),
            )
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
        val destroyed = Breakdown.destroyed(matches)
        if (destroyed > 0) {
            Spacer(Modifier.height(Spacing.sm))
            Text(
                text = "De ellos, $destroyed destruidos (rotos en lugar de abiertos)",
                fontSize = 12.sp,
                color = Palette.muted,
            )
        }
    }
}

/** El gráfico de barras de los últimos días. */
@Composable
private fun ByDaySection(matches: List<MatchRecord>) {
    SectionCard(
        title = "Cofres por día",
        subtitle = "Últimos ${Breakdown.CHART_DAYS} días",
    ) {
        DayChart(Breakdown.byDay(matches))
    }
}

/** Las últimas partidas. */
@Composable
private fun RecentSection(matches: List<MatchRecord>) {
    SectionCard(
        title = "Partidas recientes",
        subtitle = if (matches.isEmpty()) null else "Las ${minOf(4, matches.size)} últimas",
    ) {
        val recent = matches.sortedByDescending { it.endedAtMillis }.take(4)
        if (recent.isEmpty()) {
            Text(
                text = "No hay partidas todavía. Empieza una en la pestaña Contador.",
                fontSize = 13.sp,
                color = Palette.muted,
            )
        } else {
            recent.forEach { match ->
                RecentRow(match)
                Spacer(Modifier.height(Spacing.sm))
            }
        }
    }
}

/**
 * Probabilidad de haber encontrado un artilugio.
 *
 * Las probabilidades no cambian durante el día, salvo en las Horas del
 * tesoro, donde se multiplican.
 */
private fun treasureChanceText(chests: Int): String {
    if (chests <= 0) return "Cada cofre tiene opciones de sacar un artilugio."
    val chance = TreasureKind.gizmoChance(chests)
    val percent = (chance * 100).roundToInt()
    return "Con $chests cofres, la probabilidad de haber sacado al menos un artilugio " +
        "es del $percent % (1 de cada 127 cofres)."
}

/** Una fila con la última partida. */
@Composable
private fun RecentRow(match: MatchRecord) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = match.mode,
                fontSize = 14.sp,
                color = Palette.ink,
            )
            Text(
                text = DATE_FORMAT.format(Date(match.endedAtMillis)),
                fontSize = 11.sp,
                color = Palette.muted,
            )
        }
        Text(
            text = "${match.chestsOpened}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Palette.gold,
        )
        Spacer(Modifier.padding(horizontal = Spacing.sm))
        Text(
            text = "★ ${match.treasures}",
            fontSize = 13.sp,
            color = Palette.star,
        )
    }
}

/** El gráfico de barras de los últimos días. */
@Composable
private fun DayChart(days: List<DayCount>, modifier: Modifier = Modifier) {
    val max = days.maxOfOrNull { it.chests } ?: 0
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(84.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        days.forEach { day ->
            val fraction = if (max <= 0) 0f else day.chests.toFloat() / max
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((68f * fraction).coerceAtLeast(2f).dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(if (day.chests > 0) Palette.gold else Palette.track),
                )
            }
        }
    }
}

private val DATE_FORMAT = SimpleDateFormat("dd/MM HH:mm", Locale.getDefault())
