package com.issart.talkingpets.ui.detector

import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.images.MainImage
import com.issart.talkingpets.ui.common.texts.BodyMediumText
import com.issart.talkingpets.ui.common.texts.BodyText
import com.issart.talkingpets.ui.editor.EditorViewModel
import com.issart.talkingpets.ui.theme.Blue
import com.issart.talkingpets.ui.theme.SwitchUncheckedTrackColor
import com.issart.talkingpets.ui.theme.White

@Composable
fun Detector(viewModel: EditorViewModel = hiltViewModel()) {
    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        DetectorImage(viewModel.bitmap.value)
        DetectorTitle()
        DetectorEarsSwitch()
    }
}

@Composable
fun DetectorImage(bitmap: Bitmap?) = Box {
    bitmap?.let { MainImage(bitmap = it) }
    DetectorCanvas()
}

@Composable
fun DetectorTitle() = BodyText(
    title = stringResource(id = R.string.choose_points)
)

@Composable
fun DetectorEarsSwitch() {
    val checkedState = remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .padding(
                top = 16.dp,
                start = 32.dp,
                end = 32.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    )  {
        Box(
            modifier = Modifier.padding(end = 32.dp)
        ) {
            BodyMediumText(title = stringResource(id = R.string.pointy_ears))
        }

        Switch(
            modifier = Modifier.height(32.dp),
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = White,
                checkedTrackColor = Blue,
                uncheckedThumbColor = Blue,
                uncheckedTrackColor = SwitchUncheckedTrackColor
            )
        )
    }
}