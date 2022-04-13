package com.issart.talkingpets.ui.share

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.theme.Blue
import com.issart.talkingpets.ui.theme.Green
import com.issart.talkingpets.ui.theme.Purple
import com.issart.talkingpets.ui.theme.White

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
            .padding(top = 32.dp, start = 24.dp, end = 24.dp)
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

    Box {
       ShareButtonWithoutIcon(
           text = stringResource(id = R.string.create_new_animation_button),
           background = Purple
       )
    }
}

@Composable
fun ShareButton(icon: Painter, text: String, background: Color, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val widthButton = configuration.screenWidthDp * 0.45
    val heightButton = configuration.screenHeightDp * 0.085
    val sizeIcon = widthButton * 0.15

    Button(
        modifier = modifier.size(
            width = widthButton.dp,
            height = heightButton.dp
        ),
        onClick = { showToast(context, text) },
        colors = ButtonDefaults.buttonColors(backgroundColor = background),
        shape = RoundedCornerShape(25),
        elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(sizeIcon.dp),
                painter = icon,
                contentDescription = text
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = text,
//                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                color = White
            )
        }
    }

}

@Composable
fun ShareButtonWithoutIcon(text: String, background: Color) {
    val context = LocalContext.current

    Button(
        onClick = { showToast(context, text) },
        colors = ButtonDefaults.buttonColors(backgroundColor = background),
        shape = RoundedCornerShape(25),
        elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight(600),
            color = White
        )
    }
}

private fun showToast(context: Context, text: String) =
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
