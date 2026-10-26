package com.kyant.backdrop.music

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Radio
import androidx.compose.material.icons.rounded.SignalCellularAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kyant.backdrop.Backdrop

data class RadioStation(
    val id: String,
    val name: String,
    val genre: String,
    val url: String,
)

val DefaultRadioStations = listOf(
    RadioStation("1", "SomaFM Groove Salad", "Ambient / Chill", "https://ice1.somafm.com/groovesalad-128-mp3"),
    RadioStation("2", "SomaFM Secret Agent", "Lounge / Spy", "https://ice1.somafm.com/secretagent-128-mp3"),
    RadioStation("3", "SomaFM DEF CON Radio", "Hacker / Electronic", "https://ice1.somafm.com/defcon-128-mp3"),
    RadioStation("4", "KEXP Seattle", "Indie Rock", "https://kexp-mp3-128.streamguys1.com/kexp128.mp3"),
)

@Composable
fun RadioScreen(
    controller: PlayerController,
    backdrop: Backdrop,
    accent: Color,
    onOpenPlayer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var customName by remember { mutableStateOf("") }
    var customUrl by remember { mutableStateOf("") }
    var stations by remember { mutableStateOf(DefaultRadioStations) }
    var showAddDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 6.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item(key = "header") {
            GlassPanel(
                backdrop = backdrop,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(18.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.Radio,
                        contentDescription = null,
                        tint = accent,
                        modifier = Modifier.size(28.dp),
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Radio por Internet",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Emisoras online de alta calidad en tiempo real",
                            color = Color.White.copy(alpha = 0.65f),
                            fontSize = 12.sp,
                        )
                    }
                    GlassIconButton(
                        backdrop = backdrop,
                        icon = Icons.Rounded.Add,
                        contentDescription = "Añadir emisora",
                        onClick = { showAddDialog = !showAddDialog },
                        buttonSize = 42.dp,
                        iconSize = 22.dp,
                    )
                }

                if (showAddDialog) {
                    Spacer(Modifier.height(14.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("Nombre de la emisora", color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
                        BasicTextField(
                            value = customName,
                            onValueChange = { customName = it },
                            singleLine = true,
                            textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                            cursorBrush = SolidColor(accent),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(10.dp))
                                .padding(8.dp),
                        )
                        Spacer(Modifier.height(6.dp))
                        Text("URL del stream HTTP/HTTPS", color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
                        BasicTextField(
                            value = customUrl,
                            onValueChange = { customUrl = it },
                            singleLine = true,
                            textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                            cursorBrush = SolidColor(accent),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(10.dp))
                                .padding(8.dp),
                        )
                        Spacer(Modifier.height(10.dp))
                        Button(
                            onClick = {
                                if (customName.isNotBlank() && customUrl.isNotBlank()) {
                                    stations = stations + RadioStation(
                                        id = System.currentTimeMillis().toString(),
                                        name = customName,
                                        genre = "Personalizada",
                                        url = customUrl,
                                    )
                                    customName = ""
                                    customUrl = ""
                                    showAddDialog = false
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = accent),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text("Guardar Emisora", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        items(stations, key = { it.id }) { station ->
            GlassPanel(
                backdrop = backdrop,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(accent.copy(alpha = 0.7f), Color(0xFF161626)),
                                ),
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.SignalCellularAlt,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp),
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = station.name,
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = station.genre,
                            color = Color.White.copy(alpha = 0.6f),
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    Button(
                        onClick = {
                            controller.playRadioStation(station.name, station.url)
                            onOpenPlayer()
                        },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = accent.copy(alpha = 0.5f)),
                    ) {
                        Text("Sintonizar", fontSize = 12.sp, color = Color.White)
                    }
                }
            }
        }
    }
}
