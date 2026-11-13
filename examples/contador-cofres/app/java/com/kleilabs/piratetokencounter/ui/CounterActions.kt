package com.kleilabs.piratetokencounter.ui

import com.kleilabs.piratetokencounter.model.ChestKind
import com.kleilabs.piratetokencounter.model.TreasureKind

/** Acciones del contador, agrupadas para no pasar muchos parámetros. */
data class CounterActions(
    val onTap: (ChestKind) -> Unit = {},
    val onTreasure: (TreasureKind) -> Unit = {},
    val onStartMatch: (String) -> Unit = {},
    val onFinishMatch: () -> Unit = {},
    val onUndo: () -> Unit = {},
    val onCancelMatch: () -> Unit = {},
)
