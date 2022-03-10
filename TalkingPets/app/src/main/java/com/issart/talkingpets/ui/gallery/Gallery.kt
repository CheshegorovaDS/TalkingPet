package com.issart.talkingpets.ui.gallery

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.theme.Blue
import com.issart.talkingpets.ui.theme.Purple
import com.issart.talkingpets.ui.theme.TextTitleColor

@Composable
fun Gallery() {
    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        GalleryTitleText()
        GalleryButtonsRow()
        AnimalGridLayout()
    }
}

@Composable
fun GalleryTitleText() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 36.dp, end = 36.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        TitleScreen(
            title = stringResource(id = R.string.create_first_animation),
        )
    }
}

@Composable
fun GalleryButtonsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        GalleryButton(
            color = Blue,
            imageId = R.drawable.ic_gallery,
            description = "open gallery"
        )

        GalleryButton(
            color = Purple,
            imageId = R.drawable.ic_camera,
            description = "open camera"
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun AnimalGridLayout() {
    val data = getAnimalIdList()
    val context = LocalContext.current

    LazyVerticalGrid(
        modifier = Modifier,
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(vertical = 32.dp, horizontal = 8.dp)
    ) {
        items(data) { animalPhotoId ->
            Card(
                modifier = Modifier.padding(4.dp),
                backgroundColor = Color.LightGray,
                onClick = { showToast(context, "animal photo") }
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(id = animalPhotoId),
                    contentDescription = "animal photo",
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
fun GalleryButton(
    color: Color,
    imageId: Int,
    description: String
) {
    val configuration = LocalConfiguration.current
    val widthBox = configuration.screenWidthDp / 2
    val width = configuration.screenWidthDp * 0.45
//    val height = configuration.screenHeightDp * 0.11

    val context = LocalContext.current

    Box(
        modifier = Modifier.width(width = widthBox.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier.width(width = width.dp),
            onClick = { showToast(context, description) },
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            shape = RoundedCornerShape(25),
            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
        ) {
            Image(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                painter = painterResource(id = imageId),
                contentDescription = description
            )
        }
    }
}

@Composable
fun TitleScreen(title: String) {
    Text(
        text = title,
        color = TextTitleColor,
        fontSize = 36.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight(600)
    )
}

private fun showToast(context: Context, text: String) =
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()


@Preview(showBackground = true, widthDp = 400, heightDp = 848)
@Composable
fun DefaultPreview() {
    Gallery()
}
