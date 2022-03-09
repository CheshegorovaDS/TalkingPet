package com.issart.talkingpets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.navigation.TalkingPetsScreen.*
import com.issart.talkingpets.ui.detector.Detector
import com.issart.talkingpets.ui.editor.Editor
import com.issart.talkingpets.ui.gallery.Gallery
import com.issart.talkingpets.ui.mainNavigation.MainNavigation
import com.issart.talkingpets.ui.recorder.Recorder
import com.issart.talkingpets.ui.share.Share
import com.issart.talkingpets.ui.theme.TalkingPetsTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TalkingPetsApp(viewModel)
        }
    }

}

@Composable
fun TalkingPetsApp(viewModel: MainViewModel) {
    TalkingPetsTheme {
        val currentScreen: TalkingPetsScreen by viewModel.screen.observeAsState(GALLERY)
        Surface(color = MaterialTheme.colors.background) {
            when (currentScreen) {
                GALLERY -> Gallery()
                EDITOR -> Editor()
                DETECTOR -> Detector()
                RECORDER -> Recorder()
                PREVIEW -> Share()
            }
            MainNavigation(viewModel::changeScreen, viewModel)
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun DefaultPreview() {
    TalkingPetsApp(MainViewModel())
}