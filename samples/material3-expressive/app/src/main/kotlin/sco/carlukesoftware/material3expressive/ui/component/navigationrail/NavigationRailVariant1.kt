package sco.carlukesoftware.material3expressive.ui.component.navigationrail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalWideNavigationRail
import androidx.compose.material3.Text
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import sco.carlukesoftware.material3expressive.ui.screens.ContentScreen
import sco.carlukesoftware.material3expressive.ui.theme.Material3ExpressiveTheme

@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun NavigationRailVariant1() {
    ContentScreen {
        var selectedItem by remember { mutableIntStateOf(0) }
        val items = listOf("Home", "Search", "Settings")
        val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Star)
        val unselectedIcons =
            listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder, Icons.Outlined.StarBorder)
        val state = rememberWideNavigationRailState()
        val scope = rememberCoroutineScope()

        Row(Modifier.fillMaxWidth()) {
            ModalWideNavigationRail(
                state = state,
                // Note: the value of expandedHeaderTopPadding depends on the layout of your screen in
                // order to achieve the best alignment.
                expandedHeaderTopPadding = 64.dp,
                header = {
                    IconButton(
                        modifier =
                            Modifier
                                .padding(start = 24.dp)
                                .semantics {
                                    // The button must announce the expanded or collapsed state of the rail
                                    // for accessibility.
                                    stateDescription =
                                        if (state.currentValue ==
                                            WideNavigationRailValue.Expanded
                                        ) {
                                            "Expanded"
                                        } else {
                                            "Collapsed"
                                        }
                                },
                        onClick = {
                            scope.launch {
                                if (state.targetValue == WideNavigationRailValue.Expanded) {
                                    state.collapse()
                                } else {
                                    state.expand()
                                }
                            }
                        },
                    ) {
                        if (state.targetValue == WideNavigationRailValue.Expanded) {
                            Icon(Icons.AutoMirrored.Filled.MenuOpen, "Collapse rail")
                        } else {
                            Icon(Icons.Filled.Menu, "Expand rail")
                        }
                    }
                },
            ) {
                items.forEachIndexed { index, item ->
                    WideNavigationRailItem(
                        railExpanded = state.targetValue == WideNavigationRailValue.Expanded,
                        icon = {
                            Icon(
                                if (selectedItem == index) {
                                    selectedIcons[index]
                                } else {
                                    unselectedIcons[index]
                                },
                                contentDescription = item,
                            )
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                    )
                }
            }

            val textString =
                if (state.currentValue == WideNavigationRailValue.Expanded) {
                    "Expanded"
                } else {
                    "Collapsed"
                }
            Column {
                Text(modifier = Modifier.padding(16.dp), text = "The rail is $textString.")
                Text(
                    modifier = Modifier.padding(16.dp),
                    text =
                        "Note: The orientation of this demo has been locked to portrait mode, because" +
                                " landscape mode may result in a compact height in certain devices. For" +
                                " any compact screen dimensions, use a Navigation Bar instead.",
                )
            }

            // Lock the orientation for this demo as the navigation rail may look cut off in landscape
            // in smaller screens.
            val context = LocalContext.current
            DisposableEffect(context) {
                (context as? Activity)?.requestedOrientation =
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                onDispose {
                    (context as? Activity)?.requestedOrientation =
                        ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NavigationRailVariant1Preview() {
    Material3ExpressiveTheme {
        NavigationRailVariant1()
    }
}
