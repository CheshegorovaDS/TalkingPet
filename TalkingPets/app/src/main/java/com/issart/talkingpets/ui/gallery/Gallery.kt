package com.issart.talkingpets.ui.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
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
    Column {
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
            .padding(top = 48.dp, start = 36.dp, end = 36.dp),
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimalGridLayout() {
    val data: List<String> = listOf("1", " 2", "3", "4", "5", "6")

    LazyVerticalGrid(
        modifier = Modifier,
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(vertical = 48.dp, horizontal = 8.dp)
    ) {
        items(data) { item ->
            Card(
                modifier = Modifier.padding(4.dp),
                backgroundColor = Color.LightGray
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.ic_cat),
                    contentDescription = "animal photo"
                )
            }
        }
    }
}

@Composable
fun GalleryButton(color: Color, imageId: Int, description: String) {
    val configuration = LocalConfiguration.current
    val widthBox = configuration.screenWidthDp / 2
    val width = configuration.screenWidthDp * 0.45

    Box(
        modifier = Modifier.width(width = widthBox.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier.width(width = width.dp),
            onClick = { },
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            shape = RoundedCornerShape(25),
            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
        ) {
            Image(
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),
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

@Preview(showBackground = true, widthDp = 400, heightDp = 848)
@Composable
fun DefaultPreview() {
    Gallery()
}
