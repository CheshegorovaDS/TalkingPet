package com.issart.talkingpets.ui.recorder.audioList

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.texts.BodySecondaryText
import com.issart.talkingpets.ui.model.Audio
import com.issart.talkingpets.ui.theme.TextBodySecondaryAudioColor
import com.issart.talkingpets.ui.theme.White

@Composable
fun AudioItem(
    audio: Audio,
    audioListViewModel: AudioListViewModel = hiltViewModel()
) {
    val checkedAudioId = audioListViewModel.checkedAudio.observeAsState()
    val isChecked = checkedAudioId.value == audio.id

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable {
                if (isChecked) {
                    audioListViewModel.setCheckedAudio(null)
                } else {
                    audioListViewModel.setCheckedAudio(audio.id)
                }
            }
    ) {
       AudioItemColumn(isChecked, audio.title)
    }
}

@Composable
fun AudioItemColumn(isChecked: Boolean, title: String) = Column(
    modifier = Modifier
        .padding(
            top = 8.dp,
            start = 24.dp,
            end = 8.dp
        )
) {
    Row {
        AudioPlayerButton()
        AudioTitle(title)
        AudioCheckButton(isChecked = isChecked)
    }
    Divider(
        modifier = Modifier.padding(bottom = 0.dp),
        color = White,
        thickness = 1.dp
    )
}

@Composable
fun RowScope.AudioPlayerButton() = Box(
    modifier = Modifier
        .weight(1f)
) {
    Image(
        painter = painterResource(id = R.drawable.ic_play),
        contentDescription = "play audio image"
    )
}

@Composable
fun RowScope.AudioTitle(title: String) = Box(
    modifier = Modifier
        .fillMaxHeight()
        .weight(2f),
    contentAlignment = Alignment.Center
) {
    BodySecondaryText(
        title = title,
        color = TextBodySecondaryAudioColor
    )
}

@Composable
fun RowScope.AudioCheckButton(isChecked: Boolean) = Box(
    modifier = Modifier.weight(1f),
    contentAlignment = Alignment.CenterEnd
) {
    if(isChecked) {
        Image(
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = "play audio image"
        )
    }
}