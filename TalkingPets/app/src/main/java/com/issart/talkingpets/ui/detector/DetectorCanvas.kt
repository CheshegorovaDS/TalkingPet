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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetectorCanvas() {
    val configuration = LocalConfiguration.current
    val heightImage = configuration.screenHeightDp * 0.56
    val density = LocalDensity.current

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val paint = Paint().asFrameworkPaint().apply {
        textSize = density.run { 20.sp.toPx() }
        color = android.graphics.Color.WHITE
    }

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
            color = Color.White,
            radius = density.run { 5.dp.toPx() }
        )

        drawIntoCanvas {
            it.nativeCanvas.drawText(
                "eye",
                offsetX + (canvasQuadrantSize.width / 2f),
                offsetY - density.run { 5.dp.toPx() },
                paint
            )
        }
    }
}
