package com.kyant.backdrop.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FireIconBasicExample() {
    FireIcon(
        size = 32.dp,
        color = Color(0xFFFF5722),
    )
}

@Composable
fun FireIconButtonExample() {
    val fireState = rememberFireIconState()

    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        IconButton(
            onClick = { fireState.startAnimation() },
        ) {
            FireIcon(
                size = 32.dp,
                color = Color(0xFFFF5722),
                state = fireState,
            )
        }

        Text("Toca el icono o utiliza hover")
    }
}

@Composable
fun FireIconControlsExample() {
    val fireState = rememberFireIconState()

    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        FireIcon(
            size = 48.dp,
            color = Color(0xFFFF5722),
            state = fireState,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Button(
                onClick = { fireState.startAnimation() },
            ) {
                Text("Iniciar")
            }

            Button(
                onClick = { fireState.stopAnimation() },
            ) {
                Text("Detener")
            }
        }
    }
}

@Composable
fun FireIconDemo() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Text("Prueba de FireIcon")

            FireIconBasicExample()

            FireIconButtonExample()

            FireIconControlsExample()
        }
    }
}
