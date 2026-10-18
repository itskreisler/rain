package sco.carlukesoftware.material3expressive.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import sco.carlukesoftware.material3expressive.ui.component.floatingtoolbar.FloatingToolBarVariant1
import sco.carlukesoftware.material3expressive.ui.component.floatingtoolbar.FloatingToolBarVariant2
import sco.carlukesoftware.material3expressive.ui.screens.FloatingToolBarListingScreen

fun EntryProviderBuilder<NavKey>.floatingToolBarNavGraph(backStack: NavBackStack) {
    entry<FloatingToolBarRoutes.ListingRoute> {
        FloatingToolBarListingScreen(
            onNavigateBack = { backStack.removeLastOrNull() },
            onNavigateToExample = { backStack.add(it) },
        )
    }

    entry<FloatingToolBarRoutes.Variant1Route> {
        FloatingToolBarVariant1()
    }

    entry<FloatingToolBarRoutes.Variant2Route> {
        FloatingToolBarVariant2()
    }
}
