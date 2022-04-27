package com.issart.talkingpets.ui.detector

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.Title
import com.issart.talkingpets.ui.theme.Blue
import com.issart.talkingpets.ui.theme.SwitchUncheckedTrackColor
import com.issart.talkingpets.ui.theme.White

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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightImage.dp)
    ) {
        DetectorPhoto()
        DetectorCanvas()
    }
}

@Composable
fun DetectorPhoto() {
    Image(
        modifier = Modifier.fillMaxSize(),
        bitmap = ImageBitmap.imageResource(id = R.drawable.cat_1),
        contentDescription = "animal photo",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun DetectorCanvas() {
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += offsetChange
    }

//    Canvas(modifier = Modifier.fillMaxSize() ) {
//        val canvasWidth = size.width
//        val canvasHeight = size.height
//
//        drawOval(
//            color = Blue,
//            size = Size(canvasWidth * 0.5f, canvasWidth * 0.4f),
//            topLeft = Offset(canvasWidth * 0.25f, canvasHeight / 2)
//        )
//    }
//    Box(
//        Modifier
//            .graphicsLayer(
//                scaleX = scale,
//                scaleY = scale,
//                rotationZ = rotation,
//                translationX = offset.x,
//                translationY = offset.y,
//                shape = RoundedCornerShape(50)
//            )
//            .transformable(state = state)
//            .background(Blue)
//            .fillMaxSize()
//    )
    Image(
        painter = painterResource(
            id = R.drawable.ic_rotate_right),
        contentDescription = "",
        modifier = Modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = rotation,
                translationX = offset.x,
                translationY = offset.y
            )
            .transformable(state = state)
            .background(Color.Transparent)
            .fillMaxSize()
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