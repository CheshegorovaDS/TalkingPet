package com.issart.talkingpets.ui.mainNavigation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.issart.talkingpets.MainViewModel
import com.issart.talkingpets.R
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.ui.theme.*
import com.issart.talkingpets.ui.utils.ClickNavigation

@Composable
fun MainNavigation(clickNavigation: ClickNavigation, viewModel: MainViewModel, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        val configuration = LocalConfiguration.current
        val width = (configuration.screenWidthDp / 5).dp
        NavigationButton(
            width = width * 6,
            height = getHeight(TalkingPetsScreen.PREVIEW, viewModel.screen.value!!),
            typeScreen = TalkingPetsScreen.PREVIEW,
            background = Orange,
            iconId = R.drawable.ic_preview,
            onClick = clickNavigation
        )
        NavigationButton(
            width = width * 4,
            height = getHeight(TalkingPetsScreen.RECORDER, viewModel.screen.value!!),
            typeScreen = TalkingPetsScreen.RECORDER,
            background = Red,
            iconId = R.drawable.ic_recorder,
            onClick = clickNavigation
        )
        DetectorButton(clickNavigation = clickNavigation, viewModel = viewModel)
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
        elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
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
        shape = RoundedCornerShape(0,40,0,0),
        elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
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

@Composable
fun DetectorButton(clickNavigation: ClickNavigation, viewModel: MainViewModel) {
    val configuration = LocalConfiguration.current
    val width = (3 * configuration.screenWidthDp / 5).dp

    val height = if (viewModel.screen.value == TalkingPetsScreen.DETECTOR) {
        80.dp
    } else {
        72.dp
    }

    Button(
        modifier = Modifier
            .width(width)
            .height(height)
            .animateContentSize(),
        onClick = { clickNavigation(TalkingPetsScreen.DETECTOR) },
        colors = ButtonDefaults.buttonColors(backgroundColor = Purple),
        shape = RoundedCornerShape(0,40,0,0),
        elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_detector),
                contentDescription = "detector"
            )
        }
    }
}

@Composable
fun NavigationButton(
    width: Dp,
    height: Dp,
    typeScreen: TalkingPetsScreen,
    background: Color,
    iconId: Int,
    onClick: ClickNavigation
) {
    Button(
        modifier = Modifier
            .width(width)
            .height(height)
            .animateContentSize(),
        onClick = { onClick(typeScreen) },
        colors = ButtonDefaults.buttonColors(backgroundColor = background),
        shape = RoundedCornerShape(0,40,0,0),
        elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = typeScreen.name
            )
        }
    }
}

private fun getHeight(
    currentButton: TalkingPetsScreen,
    clickedScreen: TalkingPetsScreen
) = if (currentButton == clickedScreen) {
    80.dp
} else {
    72.dp
}
