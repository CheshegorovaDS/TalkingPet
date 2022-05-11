package com.issart.talkingpets.ui.common.images

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun MainImage(
    modifier: Modifier = Modifier,
    bitmap: Bitmap,
    contentDescription: String = DEFAULT_IMAGE_CONTENT_DESCRIPTION
) {
    val configuration = LocalConfiguration.current
    val heightImage = configuration.screenWidthDp
    Image(
        bitmap = bitmap.asImageBitmap(),
        modifier = modifier
            .fillMaxWidth()
            .height(heightImage.dp),
        contentScale = ContentScale.Crop,
        contentDescription = contentDescription,
    )
}

const val DEFAULT_IMAGE_CONTENT_DESCRIPTION = "Image."
