package com.issart.talkingpets.ui.mainNavigation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.issart.talkingpets.MainViewModel
import com.issart.talkingpets.R
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.ui.theme.Blue
import com.issart.talkingpets.ui.theme.Green
import com.issart.talkingpets.ui.utils.ClickNavigation

@Composable
fun MainNavigation(clickNavigation: ClickNavigation, viewModel: MainViewModel, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        EditorButton(viewModel = viewModel, clickNavigation = clickNavigation)
        GalleryButton(viewModel = viewModel, clickNavigation = clickNavigation)
    }
}

@Composable
fun GalleryButton(clickNavigation: ClickNavigation, viewModel: MainViewModel) {
    val configuration = LocalConfiguration.current
    val width = (configuration.screenWidthDp / 5).dp

    val height = if (viewModel.screen.value == TalkingPetsScreen.GALLERY) {
        80.dp
    } else {
        72.dp
    }

    Button(
        modifier = Modifier
            .width(width)
            .height(height)
            .animateContentSize(),
        onClick = { clickNavigation(TalkingPetsScreen.GALLERY) },
        colors = ButtonDefaults.buttonColors(backgroundColor = Green),
        shape = RoundedCornerShape(0,40,0,0),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_gallery),
            contentDescription = "gallery"
        )

    }
}

@Composable
fun EditorButton(clickNavigation: ClickNavigation, viewModel: MainViewModel) {
    val configuration = LocalConfiguration.current
    val width = (2 * configuration.screenWidthDp / 5).dp

    val height = if (viewModel.screen.value == TalkingPetsScreen.EDITOR) {
        80.dp
    } else {
        72.dp
    }

    Button(
        modifier = Modifier
            .width(width)
            .height(height)
            .animateContentSize(),
        onClick = { clickNavigation(TalkingPetsScreen.EDITOR) },
        colors = ButtonDefaults.buttonColors(backgroundColor = Blue),
        shape = RoundedCornerShape(0,40,0,0)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_editor),
                contentDescription = "editor"
            )
        }
    }
}
