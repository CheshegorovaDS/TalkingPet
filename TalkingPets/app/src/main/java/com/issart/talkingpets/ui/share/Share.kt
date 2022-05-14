package com.issart.talkingpets.ui.share

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.buttons.TextButton
import com.issart.talkingpets.ui.common.buttons.TextButtonsWithImage
import com.issart.talkingpets.ui.theme.Blue
import com.issart.talkingpets.ui.theme.Green
import com.issart.talkingpets.ui.theme.Purple

@Composable
fun Share() {
    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        TalkingPetVideo()
        ShareButtons()
    }
}

@Composable
fun TalkingPetVideo() {
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
fun ShareButtons() {
    Row(
        modifier = Modifier
            .padding(
                top = 32.dp,
                start = 24.dp,
                end = 24.dp
            )
    ) {
        ShareButton(
            icon = painterResource(id = R.drawable.ic_download),
            text = stringResource(id = R.string.download),
            background = Green
        )

        ShareButton(
            icon = painterResource(id = R.drawable.ic_share),
            text = stringResource(id = R.string.share),
            background = Blue,
            modifier = Modifier.padding(start = 16.dp)
        )
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
       ShareButtonWithoutIcon(
           modifier = Modifier.padding(top = 32.dp),
           text = stringResource(id = R.string.create_new_animation_button),
           background = Purple
       )
    }
}

@Composable
fun ShareButton(icon: Painter, text: String, background: Color, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val widthButton = configuration.screenWidthDp * PERCENT_WIDTH_BUTTON
    val heightButton = configuration.screenHeightDp * PERCENT_HEIGHT_BUTTON
    val sizeIcon = widthButton * PERCENT_SIZE_ICON_BUTTON

    TextButtonsWithImage(
        modifier = modifier.size(
            width = widthButton.dp,
            height = heightButton.dp
        ),
        icon = icon,
        text = text,
        backgroundColor = background,
        sizeIcon = sizeIcon.dp
    ) {
        showToast(context, text)
    }
}

@Composable
fun ShareButtonWithoutIcon(text: String, background: Color, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val heightButton = configuration.screenHeightDp * PERCENT_HEIGHT_BUTTON

    TextButton(
        modifier = modifier.height(heightButton.dp),
        text = text,
        backgroundColor = background
    ) {
        showToast(context, text)
    }

}

private fun showToast(context: Context, text: String) =
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

const val PERCENT_SIZE_ICON_BUTTON = 0.15
const val PERCENT_WIDTH_BUTTON = 0.45
const val PERCENT_HEIGHT_BUTTON = 0.085
