package com.issart.talkingpets.ui.detector

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.ui.gallery.TitleScreen

@Composable
fun Detector() {
    Column {
        TitleScreen(title = TalkingPetsScreen.DETECTOR.name)
    }
}