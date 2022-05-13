package com.issart.talkingpets.ui.recorder

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.buttons.DEFAULT_ELEVATION
import com.issart.talkingpets.ui.common.buttons.ImageButton
import com.issart.talkingpets.ui.common.texts.BodyMediumText
import com.issart.talkingpets.ui.common.texts.BodySecondaryText
import com.issart.talkingpets.ui.theme.Blue
import com.issart.talkingpets.ui.theme.White

@Composable
fun Recorder() {
    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        RecorderImage()
        RecordAudio()
    }
}

@Composable
fun RecorderImage() {
    val configuration = LocalConfiguration.current
    val heightImage = configuration.screenHeightDp * 0.56
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightImage.dp),
        bitmap = ImageBitmap.imageResource(id = R.drawable.cat_1),
        contentDescription = "animal photo",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun RecordAudio() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        RecorderRow()
        RecordButton()
        AudioListButton()
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

@Composable
fun AudioListButton() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        contentAlignment = Alignment.TopEnd,
    ) {
        Button(
            modifier = Modifier.size(80.dp, 80.dp),
            onClick = { showToast(context, "click audio list") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Blue),
            shape = RoundedCornerShape(BUTTON_CORNER, 0,0, BUTTON_CORNER),
            elevation = ButtonDefaults.elevation(DEFAULT_ELEVATION.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_recorder),
                    contentDescription = "audio list",
                    colorFilter = ColorFilter.tint(
                        color = White
                    )
                )
            }
        }
    }
}

private fun showToast(context: Context, text: String) =
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

const val BUTTON_CORNER = 20
