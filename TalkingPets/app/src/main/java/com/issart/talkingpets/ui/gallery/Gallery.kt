package com.issart.talkingpets.ui.gallery

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.issart.talkingpets.TitleScreen
import com.issart.talkingpets.navigation.TalkingPetsScreen

@Composable
fun Gallery() {
    Column {
        TitleScreen(title = TalkingPetsScreen.GALLERY.name)
    }
}
