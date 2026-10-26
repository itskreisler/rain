package com.kyant.backdrop.music

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kyant.backdrop.Backdrop

data class LrcLine(
    val timeMs: Long,
    val text: String,
)

object LrcParser {
    fun parse(lrcText: String): List<LrcLine> {
        val lines = mutableListOf<LrcLine>()
        val regex = Regex("""\[(\d{2}):(\d{2})\.(\d{2,3})\](.*)""")
        lrcText.lines().forEach { rawLine ->
            val match = regex.find(rawLine.trim())
            if (match != null) {
                val min = match.groupValues[1].toLongOrNull() ?: 0L
                val sec = match.groupValues[2].toLongOrNull() ?: 0L
                val msStr = match.groupValues[3]
                val ms = if (msStr.length == 2) msStr.toLong() * 10 else msStr.toLong()
                val totalMs = min * 60_000L + sec * 1000L + ms
                val text = match.groupValues[4].trim()
                if (text.isNotEmpty()) {
                    lines.add(LrcLine(totalMs, text))
                }
            }
        }
        return lines.sortedBy { it.timeMs }
    }
}

@Composable
fun LyricsView(
    positionMs: Long,
    lyrics: List<LrcLine>,
    accent: Color,
    modifier: Modifier = Modifier,
) {
    if (lyrics.isEmpty()) {
        Text(
            text = stringResource(R.string.lyrics_title),
            color = Color.White.copy(alpha = 0.55f),
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
        return
    }

    val currentLineIndex = remember(positionMs, lyrics) {
        val idx = lyrics.indexOfLast { it.timeMs <= positionMs }
        if (idx >= 0) idx else 0
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        lyrics.take(15).forEachIndexed { index, line ->
            val isCurrent = (index == currentLineIndex)
            Text(
                text = line.text,
                color = if (isCurrent) accent else Color.White.copy(alpha = 0.45f),
                fontSize = if (isCurrent) 16.sp else 13.sp,
                fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
            )
        }
    }
}

@Composable
fun TagEditorCard(
    track: Track,
    backdrop: Backdrop,
    accent: Color,
    onSave: (String, String, String, String) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var title by remember { mutableStateOf(track.title) }
    var artist by remember { mutableStateOf(track.artist) }
    var album by remember { mutableStateOf(track.album) }
    var genre by remember { mutableStateOf(track.genre) }

    GlassPanel(
        backdrop = backdrop,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(18.dp),
    ) {
        Text(
            text = stringResource(R.string.edit_tags),
            color = Color.White,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(12.dp))

        TagInputField(label = stringResource(R.string.title), value = title, onValueChange = { title = it }, accent = accent)
        Spacer(Modifier.height(8.dp))
        TagInputField(label = stringResource(R.string.artist), value = artist, onValueChange = { artist = it }, accent = accent)
        Spacer(Modifier.height(8.dp))
        TagInputField(label = stringResource(R.string.album), value = album, onValueChange = { album = it }, accent = accent)
        Spacer(Modifier.height(8.dp))
        TagInputField(label = stringResource(R.string.genre), value = genre, onValueChange = { genre = it }, accent = accent)

        Spacer(Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(onClick = onCancel, modifier = Modifier.weight(1f)) {
                Text(stringResource(R.string.cancel), color = Color.White.copy(alpha = 0.7f))
            }
            Spacer(Modifier.width(10.dp))
            Button(
                onClick = { onSave(title, artist, album, genre) },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accent),
                modifier = Modifier.weight(1f),
            ) {
                Text(stringResource(R.string.save), color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun TagInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    accent: Color,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, color = Color.White.copy(alpha = 0.6f), fontSize = 11.sp)
        Spacer(Modifier.height(2.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
            cursorBrush = SolidColor(accent),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp, vertical = 8.dp),
        )
    }
}
