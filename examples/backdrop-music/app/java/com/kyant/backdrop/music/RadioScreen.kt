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
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Radio
import androidx.compose.material.icons.rounded.SignalCellularAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.stringResource
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

val VerifiedRadioStations = listOf(
    RadioStation("1", "Radio Anime Stream", "Anime", "https://radioanime.radioca.st/stream"),
    RadioStation("2", "AnimeRadio.de (OGG 96)", "J-Music", "https://stream.animeradio.de/animeradio96.ogg"),
    RadioStation("3", "AnimeRadio.de (OGG)", "J-Music", "https://stream.animeradio.de/animeradio.ogg"),
    RadioStation("4", "AnimeRadio.de (AAC)", "J-Music", "https://stream.animeradio.de/animeradio.aac"),
    RadioStation("5", "AnimeRadio.de (MP3)", "J-Music", "https://stream.animeradio.de/animeradio.mp3"),
    RadioStation("6", "J-Pop Powerplay Kawaii", "J-Pop", "https://kathy.torontocast.com:3060/;?type=http&nocache=24667"),
    RadioStation("7", "Vocaloid Radio", "Vocaloid", "http://curiosity.shoutca.st:8019/stream"),
    RadioStation("8", "Otaku Music Radio", "Otaku", "https://kathy.torontocast.com:2880/1.mp3"),
    RadioStation("9", "Radio Free Otaku", "Otaku", "https://streaming.radio.co/s8267e5edc/listen"),
    RadioStation("10", "Hentai-Radio", "Anime", "https://hentairadio.stream.laut.fm/hentai_radio"),
    RadioStation("11", "stereoanime.net", "Anime", "https://radio.stereoanime.net/listen/stereoanime/128"),
    RadioStation("12", "Retro PC Game Music Radio", "Retro Gaming", "http://gyusyabu.ddo.jp:8000/;"),
    RadioStation("13", "Kawaii Music", "Kawaii", "https://kawaii-music.stream.laut.fm/kawaii-music"),
    RadioStation("14", "Asian Music", "Asian / J-Pop", "http://91.232.4.33:7028/stream/;"),
    RadioStation("15", "Yggdrasil Radio", "Anime / Game", "http://yggdrasilradio.net:8000/;stream.nsv"),
    RadioStation("16", "Big B Radio #JPOP", "J-Pop", "https://antares.dribbcast.com/proxy/jpop?mp=/s"),
    RadioStation("17", "Radio J-Hero", "J-Rock / J-Pop", "https://stm1.radiojhero.com:18008/;"),
    RadioStation("18", "Iloveanimemanga", "Anime", "https://iloveanimemanga.stream.laut.fm/iloveanimemanga"),
    RadioStation("19", "RadioAnimati AAC+", "Anime / Cartoons", "https://stream.radioanimati.it/ramobile"),
    RadioStation("20", "Anime FM (Laut)", "Anime", "https://animefm.stream.laut.fm/animefm"),
    RadioStation("21", "Ronin Radio", "J-Pop / Gaming", "https://s3.radio.co/sff133d65b/listen"),
    RadioStation("22", "Anime FM", "Anime", "https://stream.laut.fm/animefm"),
    RadioStation("23", "BOX : Weeb Anime Radio", "Anime / Weeb", "https://ss-edge.joeycast.com/anime.mp3"),
    RadioStation("24", "Final Fantasy Radio", "Game OST", "http://finalfantasystation.com:8000/stream"),
    RadioStation("25", "Otaku no Radio!", "Otaku", "http://radio.otakunopodcast.com:8000/otakunoradio"),
    RadioStation("26", "Web Radio", "Anime", "https://player.voxtreaming.com.br/proxy/6728"),
    RadioStation("27", "Fluffylandrpg", "RPG / Game", "https://fluffylandrpg.stream.laut.fm/fluffylandrpg"),
    RadioStation("28", "SUPERLOUSTIC", "Anime / Cartoons", "https://www.superloustic.com/listen"),
    RadioStation("29", "Kodama Station", "Anime OST", "https://cast.kodamastation.com/radio/8000/radio.mp3"),
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
    var customGenre by remember { mutableStateOf("Personalizada") }
    var stations by remember { mutableStateOf(VerifiedRadioStations) }
    var showAddDialog by remember { mutableStateOf(false) }
    var editingStationId by remember { mutableStateOf<String?>(null) }

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
                            text = stringResource(R.string.radio_online),
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = stringResource(R.string.radio_count_sub, stations.size),
                            color = Color.White.copy(alpha = 0.65f),
                            fontSize = 12.sp,
                        )
                    }
                    GlassIconButton(
                        backdrop = backdrop,
                        icon = Icons.Rounded.Add,
                        contentDescription = stringResource(R.string.add_station),
                        onClick = {
                            editingStationId = null
                            customName = ""
                            customUrl = ""
                            customGenre = "Personalizada"
                            showAddDialog = true
                        },
                        buttonSize = 42.dp,
                        iconSize = 22.dp,
                    )
                }

                if (showAddDialog) {
                    Spacer(Modifier.height(14.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            if (editingStationId == null) stringResource(R.string.new_station) else stringResource(R.string.edit_station),
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(stringResource(R.string.station_name), color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
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
                        Text(stringResource(R.string.station_genre), color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
                        BasicTextField(
                            value = customGenre,
                            onValueChange = { customGenre = it },
                            singleLine = true,
                            textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                            cursorBrush = SolidColor(accent),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(10.dp))
                                .padding(8.dp),
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(stringResource(R.string.station_url), color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
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
                        Row {
                            TextButton(
                                onClick = { showAddDialog = false },
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(stringResource(R.string.cancel), color = Color.White.copy(alpha = 0.7f))
                            }
                            Spacer(Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    if (customName.isNotBlank() && customUrl.isNotBlank()) {
                                        val editId = editingStationId
                                        if (editId != null) {
                                            stations = stations.map {
                                                if (it.id == editId) {
                                                    it.copy(name = customName, genre = customGenre, url = customUrl)
                                                } else it
                                            }
                                        } else {
                                            stations = stations + RadioStation(
                                                id = System.currentTimeMillis().toString(),
                                                name = customName,
                                                genre = customGenre,
                                                url = customUrl,
                                            )
                                        }
                                        showAddDialog = false
                                    }
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = accent),
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(stringResource(R.string.save), color = Color.White, fontWeight = FontWeight.Bold)
                            }
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

                    GlassIconButton(
                        backdrop = backdrop,
                        icon = Icons.Rounded.Edit,
                        contentDescription = stringResource(R.string.edit_station),
                        onClick = {
                            editingStationId = station.id
                            customName = station.name
                            customGenre = station.genre
                            customUrl = station.url
                            showAddDialog = true
                        },
                        buttonSize = 36.dp,
                        iconSize = 18.dp,
                    )

                    Spacer(Modifier.width(4.dp))

                    GlassIconButton(
                        backdrop = backdrop,
                        icon = Icons.Rounded.Delete,
                        contentDescription = stringResource(R.string.cancel),
                        onClick = {
                            stations = stations.filter { it.id != station.id }
                        },
                        buttonSize = 36.dp,
                        iconSize = 18.dp,
                    )

                    Spacer(Modifier.width(6.dp))

                    Button(
                        onClick = {
                            controller.playRadioStation(station.name, station.url)
                            onOpenPlayer()
                        },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = accent.copy(alpha = 0.5f)),
                    ) {
                        Text(stringResource(R.string.tune), fontSize = 12.sp, color = Color.White)
                    }
                }
            }
        }
    }
}
