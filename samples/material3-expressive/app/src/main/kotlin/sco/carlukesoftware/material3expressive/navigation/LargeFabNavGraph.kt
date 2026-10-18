package sco.carlukesoftware.material3expressive.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import sco.carlukesoftware.material3expressive.ui.component.largefab.LargeFabVariant1
import sco.carlukesoftware.material3expressive.ui.component.largefab.LargeFabVariant2
import sco.carlukesoftware.material3expressive.ui.screens.LargeFabListingScreen

fun EntryProviderBuilder<NavKey>.largeFabNavGraph(backStack: NavBackStack) {
    entry<LargeFabRoutes.ListingRoute> {
        LargeFabListingScreen(
            onNavigateBack = { backStack.removeLastOrNull() },
            onNavigateToExample = { backStack.add(it) },
        )
    }

    entry<LargeFabRoutes.Variant1Route> {
        LargeFabVariant1()
    }

    entry<LargeFabRoutes.Variant2Route> {
        LargeFabVariant2()
    }
}
