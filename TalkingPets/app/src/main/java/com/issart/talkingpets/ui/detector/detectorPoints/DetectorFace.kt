package com.issart.talkingpets.ui.detector.detectorPoints

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import com.issart.talkingpets.ui.common.dragPoints.DraggablePoint
import com.issart.talkingpets.ui.common.figures.Arc
import com.issart.talkingpets.ui.detector.DetectorViewModel
import com.issart.talkingpets.ui.detector.detectorPoints.model.face.FaceParams
import com.issart.talkingpets.ui.detector.detectorPoints.model.face.FacePoints

@Composable
fun DetectorFace(viewModel: DetectorViewModel = hiltViewModel()) {
    FaceCanvas()
    FacePoint(
        facePoints = viewModel.topFacePoint,
        defaultFacePoints = FaceParams.topPoint,
        setOffset = viewModel::setTopFacePosition
    )

    FacePoint(
        facePoints = viewModel.bottomFacePoint,
        defaultFacePoints = FaceParams.bottomPoint,
        setOffset = viewModel::setBottomFacePosition
    )

    FacePoint(
        facePoints = viewModel.leftFacePoint,
        defaultFacePoints = FaceParams.leftPoint,
        setOffset = viewModel::setLeftFacePosition
    )

    FacePoint(
        facePoints = viewModel.rightFacePoint,
        defaultFacePoints = FaceParams.rightPoint,
        setOffset = viewModel::setRightFacePosition
    )
}

@Composable
fun FaceCanvas(viewModel: DetectorViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val boxSize = configuration.screenWidthDp

    val top = viewModel.topFacePoint.observeAsState(
        initial = FacePoints(
            x = getEyeOffsetX(boxSize, FaceParams.topPoint.x.toDouble()).toFloat(),
            y = getEyeOffsetY(boxSize, FaceParams.topPoint.y.toDouble()).toFloat()
        )
    )

    val bottom = viewModel.bottomFacePoint.observeAsState(
        initial = FacePoints(
            x = getEyeOffsetX(boxSize, FaceParams.bottomPoint.x.toDouble()).toFloat(),
            y = getEyeOffsetY(boxSize, FaceParams.bottomPoint.y.toDouble()).toFloat()
        )
    )

    val right = viewModel.rightFacePoint.observeAsState(
        initial = FacePoints(
            x = getEyeOffsetX(boxSize, FaceParams.rightPoint.x.toDouble()).toFloat(),
            y = getEyeOffsetY(boxSize, FaceParams.rightPoint.y.toDouble()).toFloat()
        )
    )

    val left = viewModel.leftFacePoint.observeAsState(
        initial = FacePoints(
            x = getEyeOffsetX(boxSize, FaceParams.leftPoint.x.toDouble()).toFloat(),
            y = getEyeOffsetY(boxSize, FaceParams.leftPoint.y.toDouble()).toFloat()
        )
    )

    Arc(from = top.value, to = left.value)
    Arc(from = top.value, to = right.value)
    Arc(from = bottom.value, to = right.value)
    Arc(from = bottom.value, to = left.value)

}

@Composable
fun FacePoint(
    facePoints: LiveData<FacePoints>,
    defaultFacePoints: FacePoints,
    setOffset: (Float, Float) -> Unit
) {
    val configuration = LocalConfiguration.current
    val boxSize = configuration.screenWidthDp

    val pointState = facePoints.observeAsState(
        initial = FacePoints(
            x = getEyeOffsetX(boxSize, defaultFacePoints.x.toDouble()).toFloat(),
            y = getEyeOffsetY(boxSize, defaultFacePoints.y.toDouble()).toFloat()
        )
    )

    DraggablePoint(
        offsetX = pointState.value.x,
        offsetY = pointState.value.y,
        setOffset = setOffset
    )
}

fun getEyeOffsetX(
    widthCanvas: Int,
    offsetX: Double
) = widthCanvas * offsetX

fun getEyeOffsetY(
    heightCanvas: Int,
    offsetY: Double
) = heightCanvas * offsetY
