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
import com.issart.talkingpets.R
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.ui.theme.*
import com.issart.talkingpets.ui.utils.ClickNavigation

@Composable
fun MainNavigation(
    currentScreen: TalkingPetsScreen?,
    clickNavigation: ClickNavigation
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        val configuration = LocalConfiguration.current
        val width = (configuration.screenWidthDp / 5).dp
        NavigationButton(
            width = width * 6,
            height = getHeight(TalkingPetsScreen.PREVIEW, currentScreen),
            typeScreen = TalkingPetsScreen.PREVIEW,
            background = Orange,
            iconId = R.drawable.ic_preview,
            onClick = clickNavigation
        )
        NavigationButton(
            width = width * 4,
            height = getHeight(TalkingPetsScreen.RECORDER, currentScreen),
            typeScreen = TalkingPetsScreen.RECORDER,
            background = Red,
            iconId = R.drawable.ic_recorder,
            onClick = clickNavigation
        )
        DetectorButton(clickNavigation = clickNavigation, curScreen = currentScreen)
        EditorButton(curScreen = currentScreen, clickNavigation = clickNavigation)
        GalleryButton(curScreen = currentScreen, clickNavigation = clickNavigation)
    }
}

@Composable
fun GalleryButton(clickNavigation: ClickNavigation, curScreen: TalkingPetsScreen?) {
    val configuration = LocalConfiguration.current
    val width = (configuration.screenWidthDp / 5).dp
    val height = getHeight(TalkingPetsScreen.GALLERY, curScreen)

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
fun EditorButton(clickNavigation: ClickNavigation, curScreen: TalkingPetsScreen?) {
    val configuration = LocalConfiguration.current
    val width = (2 * configuration.screenWidthDp / 5).dp
    val height = getHeight(TalkingPetsScreen.EDITOR, curScreen)

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
fun DetectorButton(clickNavigation: ClickNavigation, curScreen: TalkingPetsScreen?) {
    val configuration = LocalConfiguration.current
    val width = (3 * configuration.screenWidthDp / 5).dp
    val height = getHeight(TalkingPetsScreen.DETECTOR, curScreen)

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
    clickedScreen: TalkingPetsScreen?
) = if (currentButton == clickedScreen) {
    80.dp
} else {
    72.dp
}
