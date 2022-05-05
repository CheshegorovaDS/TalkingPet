package com.issart.talkingpets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.navigation.TalkingPetsScreen.*
import com.issart.talkingpets.ui.detector.Detector
import com.issart.talkingpets.ui.editor.Editor
import com.issart.talkingpets.ui.gallery.Gallery
import com.issart.talkingpets.ui.gallery.GalleryViewModel
import com.issart.talkingpets.ui.mainNavigation.MainNavigation
import com.issart.talkingpets.ui.mainNavigation.NavigationViewModel
import com.issart.talkingpets.ui.recorder.Recorder
import com.issart.talkingpets.ui.share.Share
import com.issart.talkingpets.ui.theme.TalkingPetsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TalkingPetsApp()
        }
    }

}

@Composable
fun TalkingPetsApp() {
    TalkingPetsTheme {
        val viewModel: NavigationViewModel = hiltViewModel()
        val currentScreen: TalkingPetsScreen by viewModel.screen.observeAsState(GALLERY)

        Surface(color = MaterialTheme.colors.background) {
            when (currentScreen) {
                GALLERY -> Gallery(viewModel::changeScreen)
                EDITOR -> Editor()
                DETECTOR -> Detector()
                RECORDER -> Recorder()
                PREVIEW -> Share()
            }
            MainNavigation(
                currentScreen = viewModel.screen.value,
                clickNavigation = viewModel::changeScreen
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun DefaultPreview() {
    TalkingPetsTheme {
        Editor()
    }
}