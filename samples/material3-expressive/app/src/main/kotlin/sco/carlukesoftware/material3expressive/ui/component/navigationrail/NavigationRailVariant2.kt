package sco.carlukesoftware.material3expressive.ui.component.navigationrail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalWideNavigationRail
import androidx.compose.material3.Text
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import sco.carlukesoftware.material3expressive.ui.screens.ContentScreen
import sco.carlukesoftware.material3expressive.ui.theme.Material3ExpressiveTheme

@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun NavigationRailVariant2() {
    ContentScreen {
        var selectedItem by remember { mutableIntStateOf(0) }
        val items = listOf("Home", "Search", "Settings")
        val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Star)
        val unselectedIcons =
            listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder, Icons.Outlined.StarBorder)
        val state = rememberWideNavigationRailState()
        val scope = rememberCoroutineScope()

        Row(Modifier.fillMaxSize()) {
            ModalWideNavigationRail(state = state, hideOnCollapse = true) {
                items.forEachIndexed { index, item ->
                    WideNavigationRailItem(
                        railExpanded = true,
                        icon = {
                            Icon(
                                if (selectedItem == index) {
                                    selectedIcons[index]
                                } else {
                                    unselectedIcons[index]
                                },
                                contentDescription = null,
                            )
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            scope.launch { state.collapse() }
                        },
                    )
                }
            }

            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                val currentPage = items.get(selectedItem)
                Button(onClick = { scope.launch { state.expand() } }, Modifier.padding(32.dp)) {
                    Text(text = "$currentPage Page\nOpen modal rail", textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NavigationRailVariant2Preview() {
    Material3ExpressiveTheme {
        NavigationRailVariant2()
    }
}
