package sco.carlukesoftware.material3expressive.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import sco.carlukesoftware.material3expressive.ui.component.progressindicators.ProgressIndicatorComposables
import sco.carlukesoftware.material3expressive.ui.component.progressindicators.RefreshIndicatorComposable
import sco.carlukesoftware.material3expressive.ui.screens.ProgressIndicatorListingScreen

fun EntryProviderBuilder<NavKey>.progressIndicatorNavGraph(backStack: NavBackStack) {
    entry<ProgressIndicatorRoutes.ListingRoute> {
        ProgressIndicatorListingScreen(
            onNavigateToExample = { route ->
                backStack.add(route)
            },
            onNavigateBack = { backStack.removeLastOrNull() },
        )
    }

    entry<ProgressIndicatorRoutes.ProgressIndicatorRoute> {
        ProgressIndicatorComposables()
    }

    entry<ProgressIndicatorRoutes.RefreshIndicatorRoute> {
        RefreshIndicatorComposable()
    }
}
