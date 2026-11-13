package com.kleilabs.piratetokencounter.overlay

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.graphics.toArgb
import com.kleilabs.piratetokencounter.Palette
import com.kleilabs.piratetokencounter.model.ChestKind
import com.kleilabs.piratetokencounter.model.TreasureKind
import kotlin.math.roundToInt

/**
 * La rejilla de botones de la superposición.
 *
 * Es la misma rejilla de la app (cofres y contenedores que no son cofres),
 * pero con los tamaños de la superposición. También puede mostrar la
 * rejilla de tesoros, para elegir cuál se ha encontrado.
 */
class OverlayGrid(private val context: Context, private val actions: OverlayActions) {

    private val root = GridLayout(context)

    /** La vista de la rejilla. */
    val view: View get() = root

    init {
        root.columnCount = COLUMNS
        root.useDefaultMargins = false
    }

    /**
     * Vuelve a construir los botones.
     *
     * @param treasures si es `true`, muestra los tesoros en vez de los
     *   contenedores.
     * @param scale factor de tamaño del panel.
     */
    fun build(treasures: Boolean, scale: Int) {
        root.removeAllViews()
        val factor = scale / 100f
        entries(treasures).forEach { entry ->
            val cell = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                setPadding(dp(6), dp(4), dp(6), dp(4))
                isClickable = true
                isFocusable = true
                background = cellBackground()
                layoutParams = GridLayout.LayoutParams().apply {
                    width = (dp(84) * factor).roundToInt()
                    height = (dp(56) * factor).roundToInt()
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    setMargins(dp(3), dp(3), dp(3), dp(3))
                }
            }
            val label = TextView(context).apply {
                text = entry.label
                textSize = 10f * factor
                setTextColor(Palette.muted.toArgb())
                gravity = Gravity.CENTER
                maxLines = 1
            }
            val count = TextView(context).apply {
                text = "0"
                textSize = 17f * factor
                setTextColor(entry.color)
                gravity = Gravity.CENTER
                setTypeface(typeface, Typeface.BOLD)
            }
            cell.addView(label)
            cell.addView(count)
            cell.setOnClickListener { entry.onClick() }
            root.addView(cell)
        }
    }

    /** Actualiza los números de los botones sin recrearlos. */
    fun updateCounts(treasures: Boolean, counts: Map<String, Int>) {
        val list = entries(treasures)
        for (index in 0 until minOf(root.childCount, list.size)) {
            val cell = root.getChildAt(index) as? LinearLayout ?: continue
            val count = counts[list[index].key] ?: 0
            (cell.getChildAt(1) as? TextView)?.text = count.toString()
            cell.contentDescription = "${list[index].label}, $count registrados"
        }
    }

    private fun entries(treasures: Boolean): List<GridEntry> = if (treasures) {
        TreasureKind.entries.map { kind ->
            GridEntry(kind.shortLabel, kind.name, kind.color) { actions.onTreasure(kind) }
        }
    } else {
        ChestKind.entries.map { kind ->
            GridEntry(kind.shortLabel, kind.name, kind.color) { actions.onTap(kind) }
        }
    }

    private fun cellBackground(): GradientDrawable = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = dp(8).toFloat()
        setColor(Palette.helpSurface.toArgb())
        setStroke(dp(1), Palette.line.toArgb())
    }

    private fun dp(value: Int): Int = (value * context.resources.displayMetrics.density).roundToInt()

    /** Una celda de la rejilla: etiqueta, clave, color y acción. */
    private data class GridEntry(val label: String, val key: String, val color: Int, val onClick: () -> Unit)

    private companion object {
        const val COLUMNS = 3
    }
}
