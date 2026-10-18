package sco.carlukesoftware.material3expressive.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import sco.carlukesoftware.material3expressive.ui.component.navigationrail.NavigationRailVariant1
import sco.carlukesoftware.material3expressive.ui.component.navigationrail.NavigationRailVariant2
import sco.carlukesoftware.material3expressive.ui.screens.NavigationRailListingScreen

fun EntryProviderBuilder<NavKey>.navigationRailNavGraph(backStack: NavBackStack) {
    entry<NavigationRailRoutes.ListingRoute> {
        NavigationRailListingScreen(
            onNavigateBack = { backStack.removeLastOrNull() },
            onNavigateToExample = { backStack.add(it) },
        )
    }

    entry<NavigationRailRoutes.Variant1Route> {
        NavigationRailVariant1()
    }

    entry<NavigationRailRoutes.Variant2Route> {
        NavigationRailVariant2()
    }
}
