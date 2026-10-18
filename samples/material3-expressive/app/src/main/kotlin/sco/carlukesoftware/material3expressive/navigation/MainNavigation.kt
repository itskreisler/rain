package sco.carlukesoftware.material3expressive.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import sco.carlukesoftware.material3expressive.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MainNavigation() {
    val activity = LocalActivity.current
    val windowSizeClass = calculateWindowSizeClass(activity!!)

    val backStack = rememberNavBackStack(Routes.HomeRoute)

    NavDisplay(
        entryDecorators =
            listOf(
                // Add the default decorators for managing scenes and saving state
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),

                // Then add the view model store decorator
//
                //                rememberViewModelStoreNavEntryDecorator(),
           ),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { -it })
        },
        popTransitionSpec = {
            // Slide in from left when navigating back
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { it })
        },
        predictivePopTransitionSpec = {
            // Slide in from left when navigating back
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { it })
        },
        entryProvider =
            entryProvider {
                entry<Routes.HomeRoute> {
                    HomeScreen(
                        onNavigateToButtonGroup = {
                            backStack.add(
                                ButtonGroupRoutes.ListingRoute
                            )
                        },
                        onNavigateToProgressIndicator = {
                            backStack.add(
                                ProgressIndicatorRoutes.ListingRoute,
                            )
                        },
                        onNavigateToButtonRoute = {
                            backStack.add(ButtonRoutes.ButtonRoute)
                        },
                        onNavigateToBottomAppBarRoute = {
                            backStack.add(
                                BottomApBarRoutes.ListingRoute,
                            )
                        },
                        onNavigateToFabMenuRoute = {
                            backStack.add(FabMenuRoutes.FabMenuRoute)
                        },
                        onNavigateToFloatingToolBarRoute = {
                            backStack.add(
                                FloatingToolBarRoutes.ListingRoute,
                            )
                        },
                        onNavigateToLargeFabRoute = {
                            backStack.add(
                                LargeFabRoutes.ListingRoute,
                            )
                        },
                        onNavigateToNavigationRailRoute = {
                            backStack.add(
                                NavigationRailRoutes.ListingRoute,
                            )
                        },
                        onNavigateToSplitButtonRoute = {
                            backStack.add(
                                SplitButtonRoutes.ListingRoute,
                            )
                        },
                        onNavigateToVerticalFloatingToolbarRoute = {
                            backStack.add(
                                VerticalFloatingToolBarRoutes.ToolbarRoute,
                            )
                        },
                        onNavigateToWideNavigationRailRoute = {
                            backStack.add(
                                WideNavigationRailRoutes.ToolbarRoute,
                            )
                        },
                    )
                }

                groupButtonNavGraph(backStack)

                progressIndicatorNavGraph(backStack)

                buttonNavGraph(backStack)

                bottomAppBarNavGraph(backStack)

                fabMenuNavGraph(backStack)

                floatingToolBarNavGraph(backStack)

                largeFabNavGraph(backStack)

                navigationRailNavGraph(backStack)

                splitButtonNavGraph(backStack)

                verticalFloatingToolBarNavGraph()

                wideNavigationRailNavGraph()
            },
    )
}
