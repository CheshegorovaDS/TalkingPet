package com.issart.talkingpets.ui.detector.detectorPoints

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.ui.detector.DetectorViewModel
import com.issart.talkingpets.ui.detector.detectorPoints.model.face.FaceParams

@Composable
fun DetectorBox(viewModel: DetectorViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val boxSize = configuration.screenWidthDp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(boxSize.dp)
    ) {
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

fun getEyeOffsetX(
    widthCanvas: Int,
    density: Density,
    offsetX: Double
) = density.run {
    widthCanvas * offsetX
}

fun getEyeOffsetY(
    heightCanvas: Int,
    density: Density,
    offsetY: Double
) = density.run {
    heightCanvas * offsetY
}
