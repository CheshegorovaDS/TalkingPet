package com.issart.talkingpets.ui.detector

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.Title

@Composable
fun Detector() {
    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        DetectorImage()
        DetectorTitle()
        DetectorEarsSwitch()
    }
}

@Composable//EditorImage
fun DetectorImage() {
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
fun DetectorTitle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 36.dp, end = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Title(
            title = stringResource(id = R.string.choose_points)
        )
    }
}

@Composable
fun DetectorEarsSwitch() {
    val checkedState = remember { mutableStateOf(true) }

    Row(modifier = Modifier
        .padding(start = 32.dp, end = 32.dp, top = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    )  {
        Box(modifier = Modifier
            .padding(end = 32.dp)
        ) {
            Title(
                title = stringResource(id = R.string.pointy_ears)
            )
        }

        Switch(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )
    }
}