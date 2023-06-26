package com.issart.talkingpets.ui.share

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.buttons.TextButton
import com.issart.talkingpets.ui.common.buttons.TextButtonsWithImage
import com.issart.talkingpets.ui.common.toast.showToast
import com.issart.talkingpets.ui.detector.DetectorViewModel
import com.issart.talkingpets.ui.editor.EditorViewModel
import com.issart.talkingpets.ui.share.video.VideoViewModel
import com.issart.talkingpets.ui.theme.Blue
import com.issart.talkingpets.ui.theme.Green
import com.issart.talkingpets.ui.theme.Purple
import com.issart.talkingpets.ui.common.videoView.VideoView

@Composable
fun Share(
    viewModel: ShareViewModel = hiltViewModel(),
    editorViewModel: EditorViewModel = hiltViewModel(),
    detectorViewModel: DetectorViewModel = hiltViewModel()
) {
    val px = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }
    viewModel.createVideo(
        editorViewModel.editedBitmap,
        LocalContext.current,
        detectorViewModel.leftEye.value,
        detectorViewModel.topFacePoint.value,
        detectorViewModel.bottomFacePoint.value,
        detectorViewModel.leftFacePoint.value,
        detectorViewModel.rightFacePoint.value,
        LocalDensity.current.density,
        px
    )

    Column(modifier = Modifier.padding(bottom = 70.dp)) {
//        ProgressBarBox()
        TalkingPetVideo()
        ShareButtons()
    }
}

@Composable
fun TalkingPetVideo(
    videoViewModel: VideoViewModel = hiltViewModel(),
    viewModel: ShareViewModel = hiltViewModel()
) {
    val videoState = viewModel.video.observeAsState()
    val videoFile = videoState.value ?: return

    videoViewModel.addVideo(LocalContext.current, videoFile)

    VideoView(player = videoViewModel.player)
}

@Composable
fun ProgressBarBox(viewModel: ShareViewModel = hiltViewModel()) {
    val video = viewModel.cadr.observeAsState()

    if (video.value != null) return

    val infiniteTransition = rememberInfiniteTransition()
    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = tween(900))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = progressAnimationValue,
            color = Blue,
            strokeWidth = 10.dp,
            modifier = Modifier.size(120.dp)
        )
    }
}

@Composable
fun ShareButtons() {
    Row(
        modifier = Modifier
            .padding(
                top = 32.dp,
                start = 24.dp,
                end = 24.dp
            )
    ) {
        ShareButton(
            icon = painterResource(id = R.drawable.ic_download),
            text = stringResource(id = R.string.download),
            background = Green
        )

        ShareButton(
            icon = painterResource(id = R.drawable.ic_share),
            text = stringResource(id = R.string.share),
            background = Blue,
            modifier = Modifier.padding(start = 16.dp)
        )
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
       ShareButtonWithoutIcon(
           modifier = Modifier.padding(top = 32.dp),
           text = stringResource(id = R.string.create_new_animation_button),
           background = Purple
       )
    }
}

@Composable
fun ShareButton(icon: Painter, text: String, background: Color, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val widthButton = configuration.screenWidthDp * PERCENT_WIDTH_BUTTON
    val heightButton = configuration.screenHeightDp * PERCENT_HEIGHT_BUTTON
    val sizeIcon = widthButton * PERCENT_SIZE_ICON_BUTTON

    TextButtonsWithImage(
        modifier = modifier.size(
            width = widthButton.dp,
            height = heightButton.dp
        ),
        icon = icon,
        text = text,
        backgroundColor = background,
        sizeIcon = sizeIcon.dp
    ) {
        showToast(context, text)
    }
}

@Composable
fun ShareButtonWithoutIcon(text: String, background: Color, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val heightButton = configuration.screenHeightDp * PERCENT_HEIGHT_BUTTON

    TextButton(
        modifier = modifier.height(heightButton.dp),
        text = text,
        backgroundColor = background
    ) {
        showToast(context, text)
    }

}

const val PERCENT_SIZE_ICON_BUTTON = 0.15
const val PERCENT_WIDTH_BUTTON = 0.45
const val PERCENT_HEIGHT_BUTTON = 0.085
