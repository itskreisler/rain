package sco.carlukesoftware.material3expressive.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import sco.carlukesoftware.material3expressive.ui.component.buttongroup.ButtonGroupComposable
import sco.carlukesoftware.material3expressive.ui.component.buttongroup.ConnectedButtonGroupComposable
import sco.carlukesoftware.material3expressive.ui.screens.ButtonGroupListingScreen
import sco.carlukesoftware.material3expressive.ui.screens.ContentScreen

/**
 * Defines the navigation graph for the Button Group feature.
 */
fun EntryProviderBuilder<NavKey>.groupButtonNavGraph(backStack: NavBackStack) {
    entry<ButtonGroupRoutes.ListingRoute> {
        ButtonGroupListingScreen(
            onNavigateToExample = { route ->
                backStack.add(route)
            },
            onNavigateBack = { backStack.removeLastOrNull() },
        )
    }

    entry<ButtonGroupRoutes.ButtonGroupRoute> {
        ContentScreen {
            ButtonGroupComposable()
        }
    }

    entry<ButtonGroupRoutes.ConnectedButtonGroupRoute> {
        ContentScreen {
            ConnectedButtonGroupComposable()
        }
    }
}
