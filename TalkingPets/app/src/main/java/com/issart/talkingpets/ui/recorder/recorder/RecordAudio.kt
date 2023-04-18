package com.issart.talkingpets.ui.recorder.recorder

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.buttons.ImageButton
import com.issart.talkingpets.ui.common.texts.BodyMediumText
import com.issart.talkingpets.ui.common.texts.BodySecondaryText
import com.issart.talkingpets.ui.common.toast.showToast

@Composable
fun RecordAudio() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        RecorderRow()
        RecordButton()
    }
}

@Composable
fun RecorderRow() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart
    ) {
        RecordTimer()
    }
}

@Composable
fun RecordTimer() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentWidth()
            .padding(
                start = 24.dp
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        BodyMediumText(
            title = "0:00",
            fontSize = 30.sp
        )
    }
}

@Composable
fun RecordButton() {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageButton(
                modifier = Modifier,
                size = 80.dp,
                onClick = { showToast(context, "start record") },
                imageId = R.drawable.ic_record
            )

            BodySecondaryText(title = stringResource(id = R.string.hilt_recording))
        }
    }
}
