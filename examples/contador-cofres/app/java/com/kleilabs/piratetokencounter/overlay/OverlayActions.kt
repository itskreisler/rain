package com.kleilabs.piratetokencounter.overlay

import com.kleilabs.piratetokencounter.model.ChestKind
import com.kleilabs.piratetokencounter.model.TreasureKind

/**
 * Acciones que puede disparar la superposición.
 *
 * Se agrupan en un solo objeto para no pasar diez parámetros a la vista.
 */
data class OverlayActions(
    val onTap: (ChestKind) -> Unit = {},
    val onTreasure: (TreasureKind) -> Unit = {},
    val onUndo: () -> Unit = {},
    val onFinishMatch: () -> Unit = {},
    val onToggleTreasures: () -> Unit = {},
    val onToggleCompact: () -> Unit = {},
    val onToggleLock: () -> Unit = {},
    val onClose: () -> Unit = {},
    /** Desplazamiento en píxeles aplicado al arrastrar la cabecera. */
    val onDrag: (dx: Int, dy: Int) -> Unit = { _, _ -> },
)
