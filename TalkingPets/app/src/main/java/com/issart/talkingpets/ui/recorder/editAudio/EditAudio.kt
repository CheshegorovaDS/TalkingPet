package com.issart.talkingpets.ui.recorder.editAudio

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.buttons.ImageButton
import com.issart.talkingpets.ui.common.slider.SliderWithText
import com.issart.talkingpets.ui.recorder.audioList.AudioListViewModel
import com.issart.talkingpets.ui.recorder.player.PlayerViewModel
import com.issart.talkingpets.ui.theme.TextTitleColor

@Composable
fun EditAudio(
    audioListViewModel: AudioListViewModel = hiltViewModel(),
    editAudioViewModel: EditAudioViewModel = hiltViewModel(),
    playerViewModel: PlayerViewModel = hiltViewModel()
) {
    val isPlayed = playerViewModel.isPlay.observeAsState()
    val checkedAudio = audioListViewModel.checkedAudio.observeAsState()
    playerViewModel.setPlayedAudio(checkedAudio.value)

    val speed = editAudioViewModel.speed.observeAsState(initial = 5f)
    val pitch = editAudioViewModel.pitch.observeAsState(initial = 7f)
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 24.dp,
                end = 16.dp
            )
    ) {
        Column {
            PlayerAndCancelButtons(
                isPlayed.value ?: false,
                onClickCancelButton = { playerViewModel.clear() },
                onClickPlayerButton = {
                    audioListViewModel.checkedAudio.value?.let {
                        playerViewModel.clickPlayButton(it, context)
                    }
                }
            )
            Speed(speed.value, editAudioViewModel::setSpeed)
            Pitch(pitch.value, editAudioViewModel::setPitch)
        }
    }
}

@Composable
fun PlayerAndCancelButtons(
    isPlayed: Boolean,
    onClickPlayerButton: () -> Unit,
    onClickCancelButton: () -> Unit
) = Box(
    modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 16.dp)
) {
    DeleteCheckedAudioButton(onClickCancelButton)
    PlayerButton(isPlayed, onClickPlayerButton)
}


@Composable
fun DeleteCheckedAudioButton(onClick: () -> Unit) = Box(
    modifier = Modifier,
    contentAlignment = Alignment.CenterStart
) {
    ImageButton(
        modifier = Modifier,
        size = 64.dp,
        onClick = { onClick() },
        imageId = R.drawable.ic_record_again
    )
}

@Composable
fun PlayerButton(isPlayed: Boolean, onClick: () -> Unit) = Box(
    modifier = Modifier.fillMaxWidth(),
    contentAlignment = Alignment.Center
) {
    ImageButton(
        modifier = Modifier,
        size = 70.dp,
        onClick = { onClick() },
        imageId = if (isPlayed) {
            R.drawable.ic_pause
        } else {
            R.drawable.ic_play
               },
        colorFilter = ColorFilter.tint(color = TextTitleColor)
    )
}

@Composable
fun Speed(speed: Float, onChangedSlider: (Float) -> Unit) = SliderWithText(
    value = speed,
    onChangedSlider = onChangedSlider,
    text = stringResource(id = R.string.speed)
)

@Composable
fun Pitch(pitch: Float, onChangedSlider: (Float) -> Unit) = SliderWithText(
    value = pitch,
    onChangedSlider = onChangedSlider,
    text = stringResource(id = R.string.pitch)
)