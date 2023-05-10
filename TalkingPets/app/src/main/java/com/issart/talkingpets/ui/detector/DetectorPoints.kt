package com.issart.talkingpets.ui.detector

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import com.issart.talkingpets.ui.common.dragPoints.TransformablePoint
import com.issart.talkingpets.ui.detector.detectorPoints.EyeParams
import com.issart.talkingpets.ui.detector.detectorPoints.LeftEeyParams
import com.issart.talkingpets.ui.model.Eye

@Composable
fun DetectorBox(viewModel: DetectorViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val boxSize = configuration.screenWidthDp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(boxSize.dp)
    ) {
        DetectorEyeBox(
            eye = viewModel.leftEye,
            params = LeftEeyParams,
            setZoom = viewModel::setLeftEyeZoom,
            setOffset = viewModel::setLeftEyePosition,
            boxSize = boxSize
        )
    }

}

@Composable
fun DetectorFaceBox() {

}

@Composable
fun DetectorEyeBox(
    eye: LiveData<Eye>,
    params: EyeParams,
    setZoom: (Float) -> Unit,
    setOffset: (Float, Float) -> Unit,
    boxSize: Int
) {
    val density = LocalDensity.current

    val eyeState = eye.observeAsState(
        initial = Eye(
            getEyeOffsetX(boxSize, density, params.offsetX).toFloat(),
            getEyeOffsetY(boxSize, density, params.offsetY).toFloat(),
            1f
        )
    )

    EyeImageTransformableState(
        eye = eyeState,
        setZoom = setZoom,
        setOffset = setOffset
    )
}

@Composable
fun EyeImageTransformableState(
    eye: State<Eye>,
    setZoom: (Float) -> Unit,
    setOffset: (Float, Float) -> Unit
) {
    TransformablePoint(
        zoom = eye.value.zoom,
        offsetX = eye.value.x,
        offsetY = eye.value.y,
        setZoom = setZoom,
        setOffset = setOffset,
        description = "left eye"
    )
}

private fun getEyeOffsetX(
    widthCanvas: Int,
    density: Density,
    offsetX: Double
) = density.run {
    widthCanvas * offsetX
}

private fun getEyeOffsetY(
    heightCanvas: Int,
    density: Density,
    offsetY: Double
) = density.run {
    heightCanvas * offsetY
}
