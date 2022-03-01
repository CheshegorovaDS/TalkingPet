package com.issart.talkingpets.ui.mainNavigation

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.issart.talkingpets.MainViewModel
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.ui.theme.Green

@Composable
fun MainNavigation(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row {
            GalleryButton(viewModel = viewModel)

            Button(
                modifier = Modifier.wrapContentSize(),
                onClick = {
                    Toast.makeText(context, "click editor", Toast.LENGTH_SHORT).show()
                    viewModel.changeScreen(TalkingPetsScreen.EDITOR)
                }
            ) {
                Text(text = TalkingPetsScreen.EDITOR.name)
            }
        }
    }
}

@Composable
fun GalleryButton(viewModel: MainViewModel) {
    val configuration = LocalConfiguration.current
    val width = (configuration.screenWidthDp / 5).dp

    val height = if (viewModel.screen.value == TalkingPetsScreen.GALLERY) {
        80.dp
    } else {
        72.dp
    }

    Button(
        modifier = Modifier
            .width(width)
            .height(height)
            .animateContentSize(),
        onClick = {
            viewModel.changeScreen(TalkingPetsScreen.GALLERY)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Green)
    ) {

    }
}
