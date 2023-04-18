package com.issart.talkingpets.ui.detector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.ui.model.Eye

@Composable
fun DetectorCanvas(viewModel: DetectorViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val widthCanvas = configuration.screenWidthDp
    val heightCanvas = configuration.screenHeightDp * 0.56
    val density = LocalDensity.current

    val leftEye = viewModel.leftEye.observeAsState(
        initial = Eye(
            getEyeOffsetX(widthCanvas, density),
            getEyeOffsetY(heightCanvas.toInt(), density),
            1f
        )
    )

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(heightCanvas.dp)
        .pointerInput(Unit) {
            detectDragGesturesAfterLongPress { change, dragAmount ->
                change.consume()
                viewModel.setLeftEyePosition(
                    leftEye.value.x + dragAmount.x,
                    leftEye.value.y + dragAmount.y
                )
            }
        }
    ){
        canvasEye(leftEye.value.x, leftEye.value.y)
    }
}

private fun DrawScope.canvasEye(offsetX: Float, offsetY: Float) {
    val paint = Paint().asFrameworkPaint().apply {
        textSize = density.run { SIZE_CANVAS_TEXT.sp.toPx() }
        color = android.graphics.Color.WHITE
    }

    drawCircle(
        center = Offset(offsetX, offsetY),
        color = Color.White,
        radius = density.run { RADIUS_EYE.dp.toPx() }
    )

    drawIntoCanvas {
        it.nativeCanvas.drawText(
            EYE_HINT,
            offsetX,
            offsetY - density.run { RADIUS_EYE.dp.toPx() },
            paint
        )
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

const val EYE_HINT = "eye"
const val SIZE_CANVAS_TEXT = 20
const val RADIUS_EYE = 5
