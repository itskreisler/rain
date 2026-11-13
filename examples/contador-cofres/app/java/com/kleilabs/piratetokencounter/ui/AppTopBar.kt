package com.kleilabs.piratetokencounter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kleilabs.piratetokencounter.AppState
import com.kleilabs.piratetokencounter.Palette

/**
 * La barra superior: el nombre de la pantalla y su subtitles.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(title: String, subtitle: String, modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        title = {
            Column {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Palette.ink,
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Palette.muted,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Palette.ink,
        ),
    )
}

/** El título de cada pantalla. */
fun tabTitle(tab: Int): String = when (tab) {
    TAB_RESUMEN -> "Resumen"
    TAB_CONTADOR -> "Contador"
    TAB_HISTORIAL -> "Historial"
    else -> "Ajustes"
}

/** El subtítulo de cada pantalla: dice en qué se está. */
fun tabSubtitle(tab: Int, state: AppState): String = when (tab) {
    TAB_RESUMEN -> "Tu botín, en cifras"
    TAB_CONTADOR -> state.data.live?.mode ?: "Registra lo que abras"
    TAB_HISTORIAL -> "Cada partida, en detalle"
    else -> "Superposición, cuenta y datos"
}

/** Las cuatro pantallas de la app. */
const val TAB_RESUMEN = 0
const val TAB_CONTADOR = 1
const val TAB_HISTORIAL = 2
const val TAB_AJUSTES = 3
