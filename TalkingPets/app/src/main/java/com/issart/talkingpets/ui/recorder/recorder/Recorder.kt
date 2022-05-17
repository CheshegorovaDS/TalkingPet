package com.issart.talkingpets.ui.recorder.recorder

import android.graphics.Bitmap
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.buttons.DEFAULT_ELEVATION
import com.issart.talkingpets.ui.common.images.MainImage
import com.issart.talkingpets.ui.editor.EditorViewModel
import com.issart.talkingpets.ui.recorder.editAudio.EditAudio
import com.issart.talkingpets.ui.recorder.audioList.AudioItem
import com.issart.talkingpets.ui.recorder.audioList.AudioListViewModel
import com.issart.talkingpets.ui.recorder.audioList.getAudioList
import com.issart.talkingpets.ui.theme.Blue
import com.issart.talkingpets.ui.theme.White

@Composable
fun Recorder(
    viewModel: EditorViewModel = hiltViewModel(),
    audioListViewModel: AudioListViewModel = hiltViewModel()
) {
    val audioListVisibility = audioListViewModel.isAudioListVisible.observeAsState(initial = false)

    Box(modifier = Modifier.padding(bottom = 70.dp)) {
        Column {
            RecorderImage(viewModel.editedBitmap)
            AudioBox()
        }
        AudioListMenu(audioListVisibility.value) {
            audioListViewModel.setAudioListVisibility(!audioListVisibility.value)
        }
    }
}

@Composable
fun RecorderImage(bitmap: Bitmap?) {
    bitmap?.let { MainImage(bitmap = it) }
}

@Composable
fun AudioBox(
    recorderViewModel: RecorderViewModel = hiltViewModel()
) = Box(
    modifier = Modifier.fillMaxSize()
) {
    val checkedAudioId = recorderViewModel.checkedAudio.observeAsState(initial = null)
    if (checkedAudioId.value == null) {
        RecordAudio()
    } else {
        EditAudio()
    }
}

@Composable
fun AudioListMenu(isVisible: Boolean, onClickAudioListButton: () -> Unit) = Box(
    modifier = Modifier
        .fillMaxSize()
        .padding(
            start = 16.dp,
            bottom = 8.dp,
            top = 8.dp
        ),
    contentAlignment = Alignment.CenterEnd
) {
    val configuration = LocalConfiguration.current
    val width = if (isVisible) { (configuration.screenWidthDp * 0.8).dp
    } else {
        0.dp
    }
    val iconAudioListButton = if (isVisible) {
        R.drawable.ic_close
    } else {
        R.drawable.ic_recorder
    }

    Row {
        AudioListButton(iconAudioListButton, onClickAudioListButton)
        AudioList(width)
    }
}

@Composable
fun AudioList(width: Dp) = LazyColumn(
    modifier = Modifier
        .fillMaxHeight()
        .width(width)
        .animateContentSize()
        .alpha(0.8f)
        .background(
            color = Blue,
            shape = RoundedCornerShape(BUTTON_CORNER, 0, 0, BUTTON_CORNER)
        )
) {
    items(getAudioList()) { audio ->
        AudioItem(audio)
    }
}

@Composable
fun AudioListButton(iconAudioListButton: Int, onClickAudioListButton: () -> Unit) {
    val configuration = LocalConfiguration.current
    val heightImage = configuration.screenWidthDp

    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = (heightImage + 16).dp),
        contentAlignment = Alignment.CenterEnd,
    ) {
        Button(
            modifier = Modifier.size(80.dp),
            onClick = { onClickAudioListButton() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Blue),
            shape = RoundedCornerShape(BUTTON_CORNER, 0,0, BUTTON_CORNER),
            elevation = ButtonDefaults.elevation(DEFAULT_ELEVATION.dp)
        ) {
           Image(
               painter = painterResource(id = iconAudioListButton),
               contentDescription = "audio list",
               colorFilter = ColorFilter.tint(
                   color = White
               )
           )
        }
    }
}

const val BUTTON_CORNER = 12
