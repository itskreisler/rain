package sco.carlukesoftware.material3expressive.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import sco.carlukesoftware.material3expressive.ui.screens.ContentScreen
import sco.carlukesoftware.material3expressive.ui.screens.bottomappbar.BottomAppBarListingScreen
import sco.carlukesoftware.material3expressive.ui.screens.bottomappbar.BottomAppBarVariant1
import sco.carlukesoftware.material3expressive.ui.screens.bottomappbar.BottomAppBarVariant2
import sco.carlukesoftware.material3expressive.ui.screens.bottomappbar.BottomAppBarVariant3
import sco.carlukesoftware.material3expressive.ui.screens.bottomappbar.BottomAppBarVariant4

fun EntryProviderBuilder<NavKey>.bottomAppBarNavGraph(backStack: NavBackStack) {
    entry<BottomApBarRoutes.ListingRoute> {
        BottomAppBarListingScreen(
            onNavigateToExample = { backStack.add(it) },
            onNavigateBack = { backStack.removeLastOrNull() },
        )
    }

    entry<BottomApBarRoutes.Variant1Route> {
        ContentScreen {
            BottomAppBarVariant1()
        }
    }

    entry<BottomApBarRoutes.Variant2Route> {
        BottomAppBarVariant2()
    }

    entry<BottomApBarRoutes.Variant3Route> {
        BottomAppBarVariant3()
    }

    entry<BottomApBarRoutes.Variant4Route> {
        BottomAppBarVariant4()
    }
}
