package com.issart.talkingpets.ui.recorder

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.ui.gallery.TitleScreen

@Composable
fun Recorder() {
    Column {
        TitleScreen(title = TalkingPetsScreen.RECORDER.name)
    }
}
