package sco.carlukesoftware.material3expressive.ui.component.buttongroup

import android.util.Log.i
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import sco.carlukesoftware.material3expressive.ui.theme.Material3ExpressiveTheme

@Composable
fun ButtonGroupComposable() {
    val numButtons = 10
    ButtonGroup(
        overflowIndicator = { menuState ->
            FilledIconButton(
                onClick = {
                    if (menuState.isExpanded) {
                        menuState.dismiss()
                    } else {
                        menuState.show()
                    }
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Localized description",
                )
            }
        },
    ) {
        for (index in 0 until numButtons) {
            clickableItem(
                onClick = { },
                label = "$index"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonGroupPreview() {
    Material3ExpressiveTheme {
        ButtonGroupComposable()
    }
}
