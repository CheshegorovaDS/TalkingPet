package com.issart.talkingpets.ui.editor

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.theme.TalkingPetsTheme
import com.issart.talkingpets.ui.theme.TextTitleColor

@Composable
fun Editor() {
    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        EditorImage()
        EditorTitle()
        RotateButtons()
    }
}

@Composable
fun EditorImage() {
    val configuration = LocalConfiguration.current
    val heightImage = configuration.screenHeightDp * 0.56
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightImage.dp),
//            .graphicsLayer(
//                scaleX = 1.35f,
//                scaleY = 1.35f,
//                rotationZ = 15f
//            ),
        bitmap = ImageBitmap.imageResource(id = R.drawable.cat_1),
        contentDescription = "animal photo",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun EditorTitle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 36.dp, end = 36.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Title(
            title = stringResource(id = R.string.change_photo_hilt)
        )
    }
}

@Composable
fun RotateButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        val configuration = LocalConfiguration.current
        val sizeRotationButton = configuration.screenWidthDp * 0.22
        val sizeZeroRotationButton = configuration.screenWidthDp * 0.15

        Image(
            modifier = Modifier
                .padding(end = 32.dp)
                .size(sizeRotationButton.dp),
            painter = painterResource(id = R.drawable.ic_rotate_left) ,
            contentDescription = "rotate left"
        )

        Image(
            modifier = Modifier
                .size(sizeZeroRotationButton.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.ic_rotate_zero_degrees) ,
            contentDescription = "rotate zero"
        )

        Image(
            modifier = Modifier
                .padding(start = 32.dp)
                .size(sizeRotationButton.dp),
            painter = painterResource(id = R.drawable.ic_rotate_right) ,
            contentDescription = "rotate right"
        )
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        color = TextTitleColor,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight(500)
    )
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun EditorPreview() {
    TalkingPetsTheme {
        Editor()
    }
}

