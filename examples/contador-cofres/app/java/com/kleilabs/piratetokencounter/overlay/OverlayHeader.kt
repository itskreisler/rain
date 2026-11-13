package com.kleilabs.piratetokencounter.overlay

import android.content.Context
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.graphics.toArgb
import com.kleilabs.piratetokencounter.Palette
import com.kleilabs.piratetokencounter.R
import kotlin.math.roundToInt

/**
 * La cabecera de la superposición.
 *
 * Lleva el asa de arrastre y los botones de control: tesoros, plegar,
 * deshacer, bloquear y cerrar. Vive aparte del panel porque son cosas
 * distintas: la cabecera es siempre visible, el panel cambia de forma.
 */
class OverlayHeader(private val context: Context, private val actions: OverlayActions) {

    private var dragOriginX = 0f
    private var dragOriginY = 0f

    private val title = TextView(context)
    private val treasureToggle = ImageView(context)
    private val compactToggle = ImageView(context)
    private val undoButton = ImageView(context)
    private val lockToggle = ImageView(context)
    private val closeButton = ImageView(context)

    private val root = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }

    /** La vista de la cabecera, la que se cuelga en el panel. */
    val view: View get() = root

    init {
        title.setTextColor(Palette.muted.toArgb())
        title.textSize = 11f
        title.setPadding(dp(6), dp(4), dp(6), dp(4))

        treasureToggle.setImageResource(R.drawable.ic_star)
        treasureToggle.contentDescription = "Elegir un tesoro"
        treasureToggle.setOnClickListener { actions.onToggleTreasures() }

        compactToggle.setOnClickListener { actions.onToggleCompact() }

        undoButton.setImageResource(R.drawable.ic_undo)
        undoButton.contentDescription = "Deshacer la última acción"
        undoButton.setOnClickListener { actions.onUndo() }

        lockToggle.setOnClickListener { actions.onToggleLock() }

        closeButton.setImageResource(R.drawable.ic_close)
        closeButton.contentDescription = "Cerrar la superposición"
        closeButton.setOnClickListener { actions.onClose() }

        listOf(treasureToggle, compactToggle, undoButton, lockToggle, closeButton).forEach { button ->
            button.setColorFilter(Palette.ink.toArgb())
            button.setPadding(dp(9), dp(9), dp(9), dp(9))
            button.layoutParams = LinearLayout.LayoutParams(dp(40), dp(40))
        }

        root.addView(title)
        root.addView(treasureToggle)
        root.addView(compactToggle)
        root.addView(undoButton)
        root.addView(lockToggle)
        root.addView(closeButton)

        // La cabecera es el asa de arrastre: el resto son botones y deben
        // seguir siendo pulsables.
        root.setOnTouchListener { _, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    dragOriginX = event.rawX
                    dragOriginY = event.rawY
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    actions.onDrag(
                        (event.rawX - dragOriginX).roundToInt(),
                        (event.rawY - dragOriginY).roundToInt(),
                    )
                    dragOriginX = event.rawX
                    dragOriginY = event.rawY
                    true
                }

                else -> false
            }
        }
    }

    /** Refleja el estado actual en los botones. */
    fun render(locked: Boolean, compact: Boolean) {
        lockToggle.setImageResource(if (locked) R.drawable.ic_lock else R.drawable.ic_lock_open)
        lockToggle.contentDescription =
            if (locked) "Desbloquear la superposición" else "Bloquear la superposición"
        compactToggle.setImageResource(
            if (compact) R.drawable.ic_expand_less else R.drawable.ic_expand_more,
        )
        compactToggle.contentDescription =
            if (compact) "Ampliar la superposición" else "Plegar la superposición"
    }

    /** Aplica el factor de tamaño elegido por el jugador. */
    fun scale(factor: Float) {
        title.textSize = 11f * factor
    }

    private fun dp(value: Int): Int = (value * context.resources.displayMetrics.density).roundToInt()
}
