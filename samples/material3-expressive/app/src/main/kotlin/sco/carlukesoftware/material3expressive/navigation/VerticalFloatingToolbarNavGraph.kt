package sco.carlukesoftware.material3expressive.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import sco.carlukesoftware.material3expressive.ui.screens.VerticalFloatingToolBarScreen

fun EntryProviderBuilder<NavKey>.verticalFloatingToolBarNavGraph() {
    entry<VerticalFloatingToolBarRoutes.ToolbarRoute> {
        VerticalFloatingToolBarScreen()
    }
}
