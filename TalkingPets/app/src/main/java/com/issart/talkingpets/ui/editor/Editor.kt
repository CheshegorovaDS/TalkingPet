package com.issart.talkingpets.ui.editor

import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.buttons.ImageButton
import com.issart.talkingpets.ui.common.images.MainImage
import com.issart.talkingpets.ui.common.texts.BodyText
import com.issart.talkingpets.ui.theme.TalkingPetsTheme

@Composable
fun Editor(viewModel: EditorViewModel = hiltViewModel()) {
    val angle = viewModel.angle.observeAsState(initial = 0f)
    val editedBitmap = viewModel.editedBitmap.observeAsState()

    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        EditorImage(editedBitmap.value)
        EditorTitle()
        RotateButtons(angle.value, viewModel::setEditorAngle)
    }
}

@Composable
fun EditorImage(bitmap: Bitmap?) {
    if (bitmap == null) return

    MainImage(bitmap = bitmap)
}

@Composable
fun EditorTitle() = BodyText(
    title = stringResource(id = R.string.change_photo_hilt)
)

@Composable
fun RotateButtons(currentAngle: Float, onClickRotateButton: (Float) -> Unit) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(
            top = 24.dp
        ),
    horizontalArrangement = Arrangement.Center
) {
    val configuration = LocalConfiguration.current
    val sizeRotationButton = configuration.screenWidthDp * PERCENT_SIZE_ROTATION_BUTTON
    val sizeZeroRotationButton = configuration.screenWidthDp * PERCENT_SIZE_ZERO_ROTATION_BUTTON

    ImageButton(
        modifier = Modifier.padding(end = 32.dp),
        size = sizeRotationButton.dp,
        onClick = { onClickRotateButton(currentAngle - 15f) },
        imageId = R.drawable.ic_rotate_left
    )

    ImageButton(
        modifier = Modifier.align(Alignment.CenterVertically),
        size = sizeZeroRotationButton.dp,
        onClick = { onClickRotateButton(0f) },
        imageId = R.drawable.ic_rotate_zero_degrees
    )

    ImageButton(
        Modifier.padding(start = 32.dp),
        size = sizeRotationButton.dp,
        onClick = { onClickRotateButton(currentAngle + 15f) },
        imageId = R.drawable.ic_rotate_right
    )

}

const val PERCENT_SIZE_ROTATION_BUTTON = 0.22
const val PERCENT_SIZE_ZERO_ROTATION_BUTTON = 0.15

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun EditorPreview() {
    TalkingPetsTheme {
        Editor(hiltViewModel())
    }
}

