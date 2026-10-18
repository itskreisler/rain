package sco.carlukesoftware.material3expressive.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Subject
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sco.carlukesoftware.material3expressive.navigation.FloatingToolBarRoutes
import sco.carlukesoftware.material3expressive.ui.component.ListTile
import sco.carlukesoftware.material3expressive.ui.theme.Material3ExpressiveTheme

@Composable
fun FloatingToolBarListingScreen(
    onNavigateBack: () -> Unit,
    onNavigateToExample: (FloatingToolBarRoutes) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Floating ToolBar") },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            },
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(Modifier.height(16.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Subject,
                contentDescription = "Floating ToolBar",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.primary,
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Floating ToolBar",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Floating Toolbars offer contextual actions that appear dynamically," +
                        " often in response to a selection or interaction with specific content.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.height(24.dp))

            Text(text = "Examples", style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(8.dp))

            ListTile(
                onClick = {
                    onNavigateToExample(FloatingToolBarRoutes.Variant1Route)
                },
                title = "Floating Toolbar Variant 1",
                description = "Example of a standard floating toolbar",
            )

            ListTile(
                onClick = {
                    onNavigateToExample(FloatingToolBarRoutes.Variant2Route)
                },
                title = "Floating Toolbar Variant 2",
                description = "Example of a floating toolbar that gets dismissed when " +
                        "the user scrolls",
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun FloatingToolBarListingScreenPreview() {
    Material3ExpressiveTheme {
        FloatingToolBarListingScreen(
            onNavigateBack = {},
            onNavigateToExample = {},
        )
    }
}
