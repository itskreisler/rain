package sco.carlukesoftware.material3expressive.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import sco.carlukesoftware.material3expressive.ui.component.fabmenu.FabMenuComposable

fun EntryProviderBuilder<NavKey>.fabMenuNavGraph(backStack: NavBackStack) {
    entry<FabMenuRoutes.FabMenuRoute> {
        FabMenuComposable {
            backStack.removeLastOrNull()
        }
    }
}
