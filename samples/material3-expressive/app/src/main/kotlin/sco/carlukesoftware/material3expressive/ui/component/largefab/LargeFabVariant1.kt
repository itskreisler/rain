package sco.carlukesoftware.material3expressive.ui.component.largefab

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import sco.carlukesoftware.material3expressive.ui.screens.ContentScreen
import sco.carlukesoftware.material3expressive.ui.theme.Material3ExpressiveTheme

@Composable
fun LargeFabVariant1() {
    ContentScreen {
        LargeExtendedFloatingActionButton(
            onClick = { /* do something */ },
            icon = {
                Icon(
                    Icons.Filled.Add,
                    "Localized description",
                    modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize),
                )
            },
            text = { Text(text = "Large Extended FAB") },
        )
    }
}

@Preview
@Composable
private fun LargeFabVariant1Preview() {
    Material3ExpressiveTheme {
        LargeFabVariant1()
    }
}
