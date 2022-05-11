package com.issart.talkingpets.ui.detector

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.images.MainImage
import com.issart.talkingpets.ui.common.texts.Title
import com.issart.talkingpets.ui.editor.EditorViewModel
import com.issart.talkingpets.ui.theme.Blue
import com.issart.talkingpets.ui.theme.SwitchUncheckedTrackColor
import com.issart.talkingpets.ui.theme.White

@Composable
fun Detector(viewModel: EditorViewModel = hiltViewModel()) {
    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        DetectorImage(viewModel.bitmap.value)
        DetectorTitle()
        DetectorEarsSwitch()
    }
}

@Composable
fun DetectorImage(bitmap: Bitmap?) = Box {
    bitmap?.let { MainImage(bitmap = it) }
    DetectorCanvas()
}


@Composable
fun DetectorCanvas() {
    val configuration = LocalConfiguration.current
    val heightImage = configuration.screenHeightDp * 0.56

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val paint = Paint().asFrameworkPaint()

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(heightImage.dp)
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consumeAllChanges()
                offsetX += dragAmount.x
                offsetY += dragAmount.y
            }
        }
    ){
        val canvasQuadrantSize = size / 2F

        drawCircle(
            center = Offset(offsetX + (canvasQuadrantSize.width / 2f), offsetY),
            color = Color.Black,
            radius = 20f
        )

        drawIntoCanvas {
            it.nativeCanvas.drawText(
                "eye",
                 offsetX + (canvasQuadrantSize.width / 2f - 2.5f),
                offsetY - 2.5f,
                paint
            )
        }
    }
}

@Composable
fun DetectorTitle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Title(
            title = stringResource(id = R.string.choose_points)
        )
    }
}

@Composable
fun DetectorEarsSwitch() {
    val checkedState = remember { mutableStateOf(true) }

    Row(modifier = Modifier
        .padding(start = 32.dp, end = 32.dp, top = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    )  {
        Box(modifier = Modifier
            .padding(end = 32.dp)
        ) {
            Title(
                title = stringResource(id = R.string.pointy_ears)
            )
        }

        Switch(
            modifier = Modifier.height(32.dp),
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = White,
                checkedTrackColor = Blue,
                uncheckedThumbColor = Blue,
                uncheckedTrackColor = SwitchUncheckedTrackColor
            )
        )
    }
}