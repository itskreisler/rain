package com.kleilabs.piratetokencounter.overlay

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.graphics.toArgb
import com.kleilabs.piratetokencounter.AppState
import com.kleilabs.piratetokencounter.Palette
import com.kleilabs.piratetokencounter.Profiles
import com.kleilabs.piratetokencounter.R
import com.kleilabs.piratetokencounter.core.Breakdown
import com.kleilabs.piratetokencounter.core.Stats
import com.kleilabs.piratetokencounter.model.ChestKind
import com.kleilabs.piratetokencounter.model.OverlayStyle
import kotlin.math.roundToInt

/**
 * La superposición que flota encima del juego.
 *
 * Se construye con Views clásicas a propósito: el proceso está vivo durante
 * toda la partida y no tiene sentido cargarle el runtime de Compose. Solo
 * necesita etiquetas y botones.
 *
 * El panel tiene dos estilos:
 *  - **HUD**: cifras grandes sobre fondo casi transparente, al estilo del
 *    overlay del Contador de escritorio.
 *  - **Clásica**: una tarjeta oscura con borde, más legible fuera del juego.
 *
 * Y dos tamaños: **completo**, con la rejilla de contenedores, y **compacto**,
 * con un solo botón grande de cofre.
 */
class OverlayPanel(private val context: Context, private val actions: OverlayActions) {

    private var scale = 100
    private var compact = false
    private var style = OverlayStyle.HUD
    private var treasuresMode = false

    private val header = OverlayHeader(context, actions)
    private val grid = OverlayGrid(context, actions)

    private val root = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
    }

    private val totalValue = TextView(context)
    private val totalLabel = TextView(context)
    private val treasureValue = TextView(context)
    private val sessionValue = TextView(context)
    private val lastValue = TextView(context)

    private val compactRow = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }
    private val compactAdd = TextView(context)
    private val compactTreasure = ImageView(context)
    private val compactFinish = TextView(context)

    /** La vista raíz, la que se cuelga en el `WindowManager`. */
    val view: View get() = root

    init {
        buildHud()
        buildCompactRow()
        root.addView(header.view)
        root.addView(buildHudRow())
        root.addView(grid.view)
        root.addView(compactRow)
        applyStyle()
    }

    /** Vuelve a pintar el panel con el estado actual. */
    fun render(state: AppState) {
        val config = state.config
        val restyled = config.overlayScale != scale ||
            config.overlayCompact != compact ||
            config.overlayStyle != style
        scale = config.overlayScale
        compact = config.overlayCompact
        style = config.overlayStyle

        val data = state.data
        val matches = Profiles.matches(data, config)
        val live = data.live

        totalValue.text = (Stats.chests(matches) + (live?.chestsOpened ?: 0)).toString()
        treasureValue.text = "★ ${Stats.treasures(matches) + (live?.treasures ?: 0)}"
        sessionValue.text = "Sesión ${Stats.sessionChests(matches, data.sessionStartMillis)}"
        val last = Stats.lastMatch(matches)
        lastValue.text = last?.let { "Última ${it.chestsOpened}" } ?: "Sin partidas"

        header.render(locked = config.overlayLocked, compact = compact)

        if (restyled) {
            grid.build(treasures = treasuresMode, scale = scale)
            applyStyle()
        }
        grid.updateCounts(treasures = treasuresMode, counts = countsOf(matches))
        root.alpha = config.overlayOpacity / 100f
    }

    /** Cambia entre la rejilla de contenedores y la de tesoros. */
    fun toggleTreasures(state: AppState) {
        treasuresMode = !treasuresMode
        grid.build(treasures = treasuresMode, scale = scale)
        val matches = Profiles.matches(state.data, state.config)
        grid.updateCounts(treasures = treasuresMode, counts = countsOf(matches))
    }

    /** Los contadores de cada tipo, para la rejilla. */
    private fun countsOf(matches: List<com.kleilabs.piratetokencounter.model.MatchRecord>): Map<String, Int> {
        if (treasuresMode) {
            return Breakdown.byTreasure(matches).mapKeys { (key, _) -> key.name }
        }
        val kinds = Breakdown.byKind(matches) + Breakdown.byContainer(matches)
        return kinds.mapKeys { (key, _) -> key.name }
    }

    private fun buildHud() {
        totalValue.setTextColor(Palette.gold.toArgb())
        totalValue.setTypeface(totalValue.typeface, Typeface.BOLD)

        totalLabel.setTextColor(Palette.muted.toArgb())
        totalLabel.text = "COFRES"

        treasureValue.setTextColor(Palette.star.toArgb())

        sessionValue.setTextColor(Palette.muted.toArgb())
        lastValue.setTextColor(Palette.muted.toArgb())
    }

    private fun buildHudRow(): View {
        val left = LinearLayout(context).apply { orientation = LinearLayout.VERTICAL }
        left.addView(totalValue)
        left.addView(totalLabel)

        val right = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.END
        }
        right.addView(treasureValue)
        right.addView(sessionValue)
        right.addView(lastValue)

        return LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.BOTTOM
            setPadding(dp(8), dp(2), dp(8), dp(2))
            addView(left)
            addView(right)
        }
    }

    private fun buildCompactRow() {
        compactAdd.setTextColor(Palette.ink.toArgb())
        compactAdd.text = "+ Cofre"
        compactAdd.gravity = Gravity.CENTER
        compactAdd.setPadding(dp(14), dp(10), dp(14), dp(10))
        compactAdd.minHeight = dp(48)
        compactAdd.setOnClickListener { actions.onTap(ChestKind.NORMAL) }
        compactAdd.contentDescription = "Registrar un cofre"

        compactTreasure.setImageResource(R.drawable.ic_star)
        compactTreasure.setColorFilter(Palette.star.toArgb())
        compactTreasure.setPadding(dp(12), dp(12), dp(12), dp(12))
        compactTreasure.layoutParams = LinearLayout.LayoutParams(dp(48), dp(48))
        compactTreasure.setOnClickListener { actions.onToggleTreasures() }
        compactTreasure.contentDescription = "Elegir un tesoro"

        compactFinish.setTextColor(Palette.ink.toArgb())
        compactFinish.text = "Terminar"
        compactFinish.textSize = 13f
        compactFinish.gravity = Gravity.CENTER
        compactFinish.setPadding(dp(12), dp(10), dp(12), dp(10))
        compactFinish.minHeight = dp(48)
        compactFinish.setOnClickListener { actions.onFinishMatch() }
        compactFinish.contentDescription = "Terminar la partida"

        compactRow.addView(compactAdd)
        compactRow.addView(compactTreasure)
        compactRow.addView(compactFinish)
    }

    /** Aplica el estilo elegido: tarjeta oscura o HUD transparente. */
    private fun applyStyle() {
        val classic = style == OverlayStyle.CLASSIC
        root.background = if (classic) {
            GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = dp(10).toFloat()
                setColor(Palette.panel.toArgb())
                setStroke(dp(1), Palette.line.toArgb())
            }
        } else {
            GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = dp(10).toFloat()
                setColor(Palette.ground.toArgb() and SCRIM)
            }
        }
        applyShadow()
        compactRow.visibility = if (compact) View.VISIBLE else View.GONE
        grid.view.visibility = if (compact) View.GONE else View.VISIBLE
        scaleViews()
    }

    /**
     * Sombreado de las cifras.
     *
     * En modo HUD el panel va sobre el juego, así que el texto necesita una
     * sombra para que se lea sobre cualquier fondo.
     */
    private fun applyShadow() {
        val shadow = if (style == OverlayStyle.CLASSIC) 0f else dp(3).toFloat()
        val color = if (style == OverlayStyle.CLASSIC) 0 else 0xCC000000.toInt()
        listOf(totalValue, treasureValue, sessionValue, lastValue).forEach {
            it.setShadowLayer(shadow, 0f, dp(1).toFloat(), color)
        }
    }

    /** Aplica el factor de tamaño elegido por el jugador. */
    private fun scaleViews() {
        val factor = scale / 100f
        totalValue.textSize = 34f * factor
        totalLabel.textSize = 9f * factor
        treasureValue.textSize = 15f * factor
        sessionValue.textSize = 11f * factor
        lastValue.textSize = 11f * factor
        header.scale(factor)
    }

    private fun dp(value: Int): Int = (value * context.resources.displayMetrics.density).roundToInt()

    private companion object {
        const val SCRIM = 0x88000000.toInt()
    }
}
