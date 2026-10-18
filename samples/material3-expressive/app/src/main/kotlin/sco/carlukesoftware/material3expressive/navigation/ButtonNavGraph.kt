package sco.carlukesoftware.material3expressive.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import sco.carlukesoftware.material3expressive.ui.component.buttons.ButtonComposables

fun EntryProviderBuilder<NavKey>.buttonNavGraph(backStack: NavBackStack) {
    entry<ButtonRoutes.ButtonRoute> {
        ButtonComposables()
    }
}
