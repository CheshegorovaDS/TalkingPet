package com.issart.talkingpets.ui.mainNavigation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.issart.talkingpets.R
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.ui.common.buttons.NavigationButton
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

        NavigationButton(
            isFocused = TalkingPetsScreen.PREVIEW == currentScreen,
            background = Orange,
            iconId = R.drawable.ic_preview,
            onClick = { clickNavigation(TalkingPetsScreen.PREVIEW) },
            indexCurrentButton = 5
        )

        NavigationButton(
            isFocused = TalkingPetsScreen.RECORDER == currentScreen,
            background = Red,
            iconId = R.drawable.ic_recorder,
            onClick = { clickNavigation(TalkingPetsScreen.RECORDER) },
            indexCurrentButton = 4
        )

        NavigationButton(
            isFocused = TalkingPetsScreen.DETECTOR == currentScreen,
            background = Purple,
            iconId = R.drawable.ic_detector,
            onClick = { clickNavigation(TalkingPetsScreen.DETECTOR) },
            indexCurrentButton = 3
        )

        NavigationButton(
            isFocused = TalkingPetsScreen.EDITOR == currentScreen,
            background = Blue,
            iconId = R.drawable.ic_editor,
            onClick = { clickNavigation(TalkingPetsScreen.EDITOR) },
            indexCurrentButton = 2
        )

        NavigationButton(
            isFocused = TalkingPetsScreen.GALLERY == currentScreen,
            background = Green,
            iconId = R.drawable.ic_gallery,
            onClick = { clickNavigation(TalkingPetsScreen.GALLERY) },
            indexCurrentButton = 1
        )
    }
}
