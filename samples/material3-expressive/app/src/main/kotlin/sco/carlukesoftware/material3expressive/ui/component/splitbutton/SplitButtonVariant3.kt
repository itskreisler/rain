package sco.carlukesoftware.material3expressive.ui.component.splitbutton

import android.R.attr.checked
import android.R.attr.contentDescription
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sco.carlukesoftware.material3expressive.ui.screens.ContentScreen
import sco.carlukesoftware.material3expressive.ui.theme.Material3ExpressiveTheme

@Composable
fun SplitButtonVariant3() {
    ContentScreen {
        var checked by remember { mutableStateOf(false) }
        val size = SplitButtonDefaults.MediumContainerHeight

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            SplitButtonLayout(
                leadingButton = {
                    SplitButtonDefaults.LeadingButton(
                        onClick = { /* Do Nothing */ },
                    ) {
                        Icon(
                            Icons.Filled.Edit,
                            modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                            contentDescription = "Localized description",
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("My Button")
                    }
                },
                trailingButton = {
                    SplitButtonDefaults.TrailingButton(
                        onClick = { /* Do Nothing */ },
                    ) {
                        Icon(
                            Icons.Filled.KeyboardArrowDown,
                            modifier = Modifier.size(SplitButtonDefaults.TrailingIconSize),
                            contentDescription = "Localized description",
                        )
                    }
                },
            )

            SplitButtonLayout(
                leadingButton = {
                    SplitButtonDefaults.OutlinedLeadingButton(
                        onClick = { /* Do Nothing */ },
                    ) {
                        Icon(
                            Icons.Filled.Edit,
                            modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                            contentDescription = "Localized description",
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("My Button")
                    }
                },
                trailingButton = {
                    SplitButtonDefaults.OutlinedTrailingButton(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        modifier =
                            Modifier.semantics {
                                stateDescription = if (checked) "Expanded" else "Collapsed"
                            },
                    ) {
                        val rotation: Float by
                        animateFloatAsState(
                            targetValue = if (checked) 180f else 0f,
                            label = "Trailing Icon Rotation",
                        )
                        Icon(
                            Icons.Filled.KeyboardArrowDown,
                            modifier =
                                Modifier
                                    .size(SplitButtonDefaults.TrailingIconSize)
                                    .graphicsLayer {
                                        this.rotationZ = rotation
                                    },
                            contentDescription = "Localized description",
                        )
                    }
                },
            )

            SplitButtonLayout(
                leadingButton = {
                    SplitButtonDefaults.LeadingButton(
                        onClick = { /* Do Nothing */ },
                    ) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Localized description",
                            Modifier.size(SplitButtonDefaults.LeadingIconSize),
                        )
                    }
                },
                trailingButton = {
                    SplitButtonDefaults.TrailingButton(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        modifier =
                            Modifier.semantics {
                                stateDescription = if (checked) "Expanded" else "Collapsed"
                            },
                    ) {
                        val rotation: Float by
                        animateFloatAsState(
                            targetValue = if (checked) 180f else 0f,
                            label = "Trailing Icon Rotation",
                        )
                        Icon(
                            Icons.Filled.KeyboardArrowDown,
                            modifier =
                                Modifier
                                    .size(SplitButtonDefaults.TrailingIconSize)
                                    .graphicsLayer {
                                        this.rotationZ = rotation
                                    },
                            contentDescription = "Localized description",
                        )
                    }
                },
            )

            SplitButtonLayout(
                leadingButton = {
                    SplitButtonDefaults.LeadingButton(
                        onClick = { /* Do Nothing */ },
                        modifier = Modifier.heightIn(size),
                        shapes = SplitButtonDefaults.leadingButtonShapesFor(size),
                        contentPadding = SplitButtonDefaults.leadingButtonContentPaddingFor(size),
                    ) {
                        Icon(
                            Icons.Filled.Edit,
                            modifier = Modifier.size(
                                SplitButtonDefaults.leadingButtonIconSizeFor(size),
                            ),
                            contentDescription = "Localized description",
                        )
                        Spacer(Modifier.size(ButtonDefaults.iconSpacingFor(size)))
                        Text("My Button", style = ButtonDefaults.textStyleFor(size))
                    }
                },
                trailingButton = {
                    SplitButtonDefaults.TrailingButton(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        modifier =
                            Modifier
                                .heightIn(size)
                                .semantics {
                                    stateDescription = if (checked) "Expanded" else "Collapsed"
                                },
                        shapes = SplitButtonDefaults.trailingButtonShapesFor(size),
                        contentPadding = SplitButtonDefaults.trailingButtonContentPaddingFor(size),
                    ) {
                        val rotation: Float by
                        animateFloatAsState(
                            targetValue = if (checked) 180f else 0f,
                            label = "Trailing Icon Rotation",
                        )
                        Icon(
                            Icons.Filled.KeyboardArrowDown,
                            modifier =
                                Modifier
                                    .size(SplitButtonDefaults.trailingButtonIconSizeFor(size))
                                    .graphicsLayer { this.rotationZ = rotation },
                            contentDescription = "Localized description",
                        )
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplitButtonVariant3Preview() {
    Material3ExpressiveTheme {
        SplitButtonVariant3()
    }
}
