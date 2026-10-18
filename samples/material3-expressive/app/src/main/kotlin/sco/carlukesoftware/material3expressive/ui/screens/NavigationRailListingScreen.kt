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
import androidx.compose.material.icons.filled.Navigation
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
import sco.carlukesoftware.material3expressive.navigation.NavigationRailRoutes
import sco.carlukesoftware.material3expressive.ui.component.ListTile
import sco.carlukesoftware.material3expressive.ui.theme.Material3ExpressiveTheme

@Composable
fun NavigationRailListingScreen(
    onNavigateBack: () -> Unit,
    onNavigateToExample: (NavigationRailRoutes) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Navigation Rails") },
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
                imageVector = Icons.Filled.Navigation,
                contentDescription = "Navigation Rail",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.primary,
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Navigation Rails",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Navigation rails provide access to primary destinations in your " +
                        "app on larger screens. They are typically used for tablet and desktop layouts.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.height(24.dp))

            Text(text = "Examples", style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(8.dp))

            ListTile(
                onClick = {
                    onNavigateToExample(NavigationRailRoutes.Variant1Route)
                },
                title = "Navigation Rail - Variant 1",
                description = "Example of a standard Navigation Rail.",
            )

            ListTile(
                onClick = {
                    onNavigateToExample(NavigationRailRoutes.Variant2Route)
                },
                title = "Navigation Rail - Variant 2",
                description = "Another example of Navigation Rail, possibly with " +
                        "different configurations.",
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun NavigationRailListingScreenPreview() {
    Material3ExpressiveTheme {
        NavigationRailListingScreen(
            onNavigateBack = {},
            onNavigateToExample = {},
        )
    }
}
