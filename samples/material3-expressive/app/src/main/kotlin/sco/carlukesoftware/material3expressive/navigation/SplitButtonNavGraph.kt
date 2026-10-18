package sco.carlukesoftware.material3expressive.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import sco.carlukesoftware.material3expressive.ui.component.splitbutton.SplitButtonVariant1
import sco.carlukesoftware.material3expressive.ui.component.splitbutton.SplitButtonVariant2
import sco.carlukesoftware.material3expressive.ui.component.splitbutton.SplitButtonVariant3
import sco.carlukesoftware.material3expressive.ui.screens.SplitButtonListingScreen
import java.util.Map.entry

fun EntryProviderBuilder<NavKey>.splitButtonNavGraph(backStack: NavBackStack) {
    entry<SplitButtonRoutes.ListingRoute> {
        SplitButtonListingScreen(
            onNavigateToExample = { route ->
                backStack.add(route)
            },
            onNavigateBack = { backStack.removeLastOrNull() },
        )
    }

    entry<SplitButtonRoutes.Variant1Route> {
        SplitButtonVariant1()
    }

    entry<SplitButtonRoutes.Variant2Route> {
        SplitButtonVariant2()
    }

    entry<SplitButtonRoutes.Variant3Route> {
        SplitButtonVariant3()
    }
}
