package com.issart.talkingpets.ui.recorder.editAudio

import android.content.Context
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
import com.issart.talkingpets.ui.recorder.recorder.showToast
import com.issart.talkingpets.ui.theme.TextTitleColor

@Composable
fun EditAudio(editAudioViewModel: EditAudioViewModel = hiltViewModel()) {
    val speed = editAudioViewModel.speed.observeAsState(initial = 5f)
    val pitch = editAudioViewModel.pitch.observeAsState(initial = 7f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 24.dp,
                end = 16.dp
            )
    ) {
        Column {
            PlayerAndCancelButtons()
            Speed(speed.value, editAudioViewModel::setSpeed)
            Pitch(pitch.value, editAudioViewModel::setPitch)
        }
    }
}

@Composable
fun PlayerAndCancelButtons() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = 16.dp
            )
    ) {
        DeleteCheckedAudioButton(context = context)
        PlayerButton(context = context)
    }
}

@Composable
fun DeleteCheckedAudioButton(context: Context) = Box(
    modifier = Modifier,
    contentAlignment = Alignment.CenterStart
) {
    ImageButton(
        modifier = Modifier,
        size = 64.dp,
        onClick = { showToast(context, "record again") },
        imageId = R.drawable.ic_record_again
    )
}

@Composable
fun PlayerButton(context: Context) = Box(
    modifier = Modifier.fillMaxWidth(),
    contentAlignment = Alignment.Center
) {
    ImageButton(
        modifier = Modifier,
        size = 70.dp,
        onClick = { showToast(context, "play audio") },
        imageId = R.drawable.ic_play,
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