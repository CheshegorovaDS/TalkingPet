package com.issart.talkingpets.ui.recorder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.issart.talkingpets.R
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.ui.gallery.TitleScreen

@Composable
fun Recorder() {
    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        RecorderImage()
        RecordAudio()
    }
}

@Composable
fun RecorderImage() {//Editor, Detector
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

}

@Composable
fun RefactoringAudio() {

}
