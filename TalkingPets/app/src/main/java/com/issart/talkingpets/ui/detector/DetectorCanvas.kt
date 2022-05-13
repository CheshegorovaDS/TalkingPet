package com.issart.talkingpets.ui.detector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetectorCanvas() {
    val configuration = LocalConfiguration.current
    val widthCanvas = configuration.screenWidthDp
    val heightCanvas = configuration.screenHeightDp * 0.56

    val density = LocalDensity.current
    getEyeOffsetX(widthCanvas, density)

    var offsetX by remember { mutableStateOf(getEyeOffsetX(widthCanvas, density)) }
    var offsetY by remember { mutableStateOf(getEyeOffsetY(heightCanvas.toInt(), density)) }
    val paint = Paint().asFrameworkPaint().apply {
        textSize = density.run { 20.sp.toPx() }
        color = android.graphics.Color.WHITE
    }

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(heightCanvas.dp)
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consumeAllChanges()
                offsetX += dragAmount.x
                offsetY += dragAmount.y
            }
        }
    ){

        drawCircle(
            center = Offset(offsetX, offsetY),
            color = Color.White,
            radius = density.run { 5.dp.toPx() }
        )

        drawIntoCanvas {
            it.nativeCanvas.drawText(
                "eye",
                offsetX,
                offsetY - density.run { 5.dp.toPx() },
                paint
            )
        }
    }
}

private fun getEyeOffsetX(widthCanvas: Int, density: Density) = density.run {
    widthCanvas * OFFSET_X_EYE.dp.toPx()
}

private fun getEyeOffsetY(heightCanvas: Int, density: Density) = density.run {
    heightCanvas * OFFSET_Y_EYE.dp.toPx()
}

const val OFFSET_X_EYE = 0.42
const val OFFSET_Y_EYE = 0.4
