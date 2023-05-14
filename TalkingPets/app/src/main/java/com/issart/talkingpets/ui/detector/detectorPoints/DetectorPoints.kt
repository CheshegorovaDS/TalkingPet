package com.issart.talkingpets.ui.detector.detectorPoints

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.ui.common.dragPoints.POINT_SIZE
import com.issart.talkingpets.ui.detector.DetectorViewModel
import com.issart.talkingpets.ui.detector.detectorPoints.model.face.FaceParams
import com.issart.talkingpets.ui.detector.detectorPoints.model.face.FacePoints
import com.issart.talkingpets.ui.theme.Green

@Composable
fun DetectorBox(viewModel: DetectorViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val boxSize = configuration.screenWidthDp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(boxSize.dp)
    ) {
        FaceCanvas(boxSize = boxSize)
        DetectorFaceBox(
            facePoints = viewModel.topFacePoint,
            defaultFacePoints = FaceParams.topPoint,
            boxSize = boxSize,
            setOffset = viewModel::setTopFacePosition
        )

        DetectorFaceBox(
            facePoints = viewModel.leftFacePoint,
            defaultFacePoints = FaceParams.leftPoint,
            boxSize = boxSize,
            setOffset = viewModel::setLeftFacePosition
        )
    }

}

@Composable
fun FaceCanvas(viewModel: DetectorViewModel = hiltViewModel(), boxSize: Int) {
    val start = FacePoints(
        x = getEyeOffsetX(boxSize, FaceParams.topPoint.x.toDouble()).toFloat(),
        y = getEyeOffsetY(boxSize, FaceParams.topPoint.y.toDouble()).toFloat()
    )
    val end = FacePoints(
        x = getEyeOffsetX(boxSize, FaceParams.leftPoint.x.toDouble()).toFloat(),
        y = getEyeOffsetY(boxSize, FaceParams.leftPoint.y.toDouble()).toFloat()
    )

    Canvas(modifier = Modifier.fillMaxSize() ) {

        drawLine(
            color = Green,
            start = Offset(start.x + POINT_SIZE, start.y + POINT_SIZE),
            end = Offset(end.x + POINT_SIZE, end.y + POINT_SIZE),
            strokeWidth = 10f
        )

    }
}

fun getEyeOffsetX(
    widthCanvas: Int,
    offsetX: Double
) = widthCanvas * offsetX

fun getEyeOffsetY(
    heightCanvas: Int,
    offsetY: Double
) = heightCanvas * offsetY
