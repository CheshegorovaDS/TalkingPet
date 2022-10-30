package com.issart.talkingpets.ui.recorder.recorder

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.buttons.ActionImageButton
import com.issart.talkingpets.ui.common.texts.BodyMediumText
import com.issart.talkingpets.ui.common.texts.BodySecondaryText

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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RecordButton(recorderViewModel: RecorderViewModel = hiltViewModel()) {
    val context = LocalContext.current

    val recordAudioPermission = rememberPermissionState(
        permission = android.Manifest.permission.RECORD_AUDIO
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActionImageButton(
                modifier = Modifier,
                size = 80.dp,
                imageId = R.drawable.ic_record,
                onActionDownEvent = {
                    if (recordAudioPermission.hasPermission) {
//                        recorderViewModel.start()
                        showToast(context, "start record")
                    } else {
                        recordAudioPermission.launchPermissionRequest()
                    }
                },
                onActionUpEvent = {
//                    recorderViewModel.stop()
                    recorderViewModel.setAudioFile("hjhjh")
                    showToast(context, "end record")
                }
            )

            BodySecondaryText(title = stringResource(id = R.string.hilt_recording))
        }
    }
}

fun showToast(context: Context, text: String) =
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
