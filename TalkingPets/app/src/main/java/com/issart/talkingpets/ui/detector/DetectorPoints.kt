package com.issart.talkingpets.ui.detector

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.detector.detectorPoints.LeftYeyParams
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
            setZoom = viewModel::setLeftEyeZoom,
            setOffset = viewModel::setLeftEyePosition,
            defaultOffsetX = LeftYeyParams.offsetX,
            defaultOffsetY = LeftYeyParams.offsetY,
            boxSize = boxSize
        )
    }

}

@Composable
fun DetectorEyeBox(
    eye: LiveData<Eye>,
    setZoom: (Float) -> Unit,
    setOffset: (Float, Float) -> Unit,
    defaultOffsetX: Double,
    defaultOffsetY: Double,
    boxSize: Int
) {
    val density = LocalDensity.current

    val eyeState = eye.observeAsState(
        initial = Eye(
            getEyeOffsetX(boxSize, density, defaultOffsetX).toFloat(),
            getEyeOffsetY(boxSize, density, defaultOffsetY).toFloat(),
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
    val transformableState = rememberTransformableState { zoomChange, offsetChange, _ ->
        setZoom(eye.value.zoom * zoomChange)
        setOffset(
            eye.value.x + offsetChange.x,
            eye.value.y + offsetChange.y
        )
    }
    Image(
        painter = painterResource(id = R.drawable.ic_record),
        contentDescription = "left eye",
        modifier = Modifier
            .scale(eye.value.zoom)
            .offset(eye.value.x.dp, eye.value.y.dp)
            .transformable(transformableState)
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

//const val SIZE_CANVAS_TEXT = 20
//const val RADIUS_EYE = 5
