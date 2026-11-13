package com.kleilabs.piratetokencounter

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Sistema de diseño del Contador.
 *
 * Los tokens vienen del `DESIGN.md` del Contador de escritorio: azul marino
 * para el fondo, panel azul oscuro, texto marfil, azul claro para lo
 * secundario y dorado de tesoro para las cifras que importan. El rojo pirata
 * marca la navegación activa.
 *
 * El overlay se construye con Views clásicas, no con Compose, así que los
 * colores se guardan como [Color] de Compose y se convierten a ARGB con
 * [toArgb] cuando hacen falta. Una sola fuente de verdad para las dos
 * superficies.
 */
// Una paleta es una lista de constantes por definición: los códigos de color
// no esconden ninguna regla de negocio detrás de un número suelto.
@Suppress("MagicNumber")
object Palette {
    val ground = Color(0xFF101C2E)
    val panel = Color(0xFF182D48)
    val ink = Color(0xFFFFF4DF)
    val muted = Color(0xFFBDCEE3)
    val gold = Color(0xFFFFC857)
    val line = Color(0xFF4F7295)
    val pirateRed = Color(0xFF6E2636)
    val ready = Color(0xFFA9D5A5)
    val attention = Color(0xFFF1CA76)
    val rowAlternate = Color(0xFF14243A)
    val rowSelected = Color(0xFF365779)
    val helpSurface = Color(0xFF20364C)
    val helpInk = Color(0xFFE2D1AD)
    val selection = Color(0xFF695128)

    /** Fondo de las barras sin datos. */
    val track = Color(0xFF294360)

    /** Avisos y probabilidades bajas. */
    val warn = Color(0xFFFFB09A)
    val warnBackground = Color(0xFF412B38)

    /** Tesoros: morado, como en el informe del escritorio. */
    val star = Color(0xFFD7B6FF)

    /** Tesoros del equipo. */
    val team = Color(0xFF9EDDD6)
}

/**
 * Espaciado y radios.
 *
 * El escritorio usa una escala de 8/12/16/24/32; aquí los mismos valores en
 * dp, que en Android es la unidad equivalente.
 */
object Spacing {
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 24.dp
    val xxl = 32.dp
}

/** Radios de controles y paneles. */
object Radii {
    val control = 8.dp
    val panel = 10.dp
}
