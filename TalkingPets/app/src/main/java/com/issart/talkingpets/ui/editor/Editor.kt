package com.issart.talkingpets.ui.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.ui.gallery.TitleScreen

@Composable
fun Editor() {
    Column {
        TitleScreen(title = TalkingPetsScreen.EDITOR.name)
    }
}
