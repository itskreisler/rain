package sco.carlukesoftware.material3expressive.model

import androidx.compose.ui.graphics.vector.ImageVector

data class HomeItemData(
    val title: String,
    val icon: ImageVector,
    val onClickAction: () -> Unit
)
