package com.issart.talkingpets.ui.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.issart.talkingpets.TitleScreen
import com.issart.talkingpets.navigation.TalkingPetsScreen

@Composable
fun Editor() {
    Column {
        TitleScreen(title = TalkingPetsScreen.EDITOR.name)
    }
}
