package com.issart.talkingpets.ui.share

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.ui.gallery.TitleScreen

@Composable
fun Share() {
    Column {
        TitleScreen(title = TalkingPetsScreen.PREVIEW.name)
    }
}
