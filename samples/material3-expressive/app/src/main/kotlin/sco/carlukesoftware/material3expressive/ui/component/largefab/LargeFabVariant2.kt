package sco.carlukesoftware.material3expressive.ui.component.largefab

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sco.carlukesoftware.material3expressive.ui.theme.Material3ExpressiveTheme

@Composable
fun LargeFabVariant2() {
    val listState = rememberLazyListState()
// The FAB is initially expanded. Once the first visible item is past the first item we
// collapse the FAB. We use a remembered derived state to minimize unnecessary compositions.
    val expandedFab by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
    Scaffold(
        floatingActionButton = {
            LargeExtendedFloatingActionButton(
                onClick = { /* do something */ },
                expanded = expandedFab,
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        "Localized Description",
                        modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize),
                    )
                },
                text = { Text(text = "Large Extended FAB") },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            for (index in 0 until 100) {
                item { Text(text = "List item - $index", modifier = Modifier.padding(24.dp)) }
            }
        }
    }
}

@Preview
@Composable
private fun LargeFabVariant2Preview() {
    Material3ExpressiveTheme {
        LargeFabVariant2()
    }
}
