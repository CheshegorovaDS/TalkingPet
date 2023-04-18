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
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.model.Eye

@Composable
fun DetectorBox(viewModel: DetectorViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val boxSize = configuration.screenWidthDp
    val density = LocalDensity.current

    viewModel.setLeftEyePosition(
        getEyeOffsetX(boxSize, density).toFloat(),
        getEyeOffsetY(boxSize, density).toFloat()
    )

    val leftEye = viewModel.leftEye.observeAsState(
        initial = Eye(
            getEyeOffsetX(boxSize, density).toFloat(),
            getEyeOffsetY(boxSize, density).toFloat(),
            1f
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(boxSize.dp)
    ) {
        EyeImageTransformableState2(
            eye = leftEye,
            setZoom = viewModel::setLeftEyeZoom,
            setOffset = viewModel::setLeftEyePosition
        )
    }

}

@Composable
fun EyeImageTransformableState2(
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

private fun getEyeOffsetX(widthCanvas: Int, density: Density) = density.run {
    widthCanvas * OFFSET_X_EYE
}

private fun getEyeOffsetY(heightCanvas: Int, density: Density) = density.run {
    heightCanvas * OFFSET_Y_EYE
}

const val OFFSET_X_EYE = 0.42
const val OFFSET_Y_EYE = 0.4

const val EYE_HINT = "eye"
const val SIZE_CANVAS_TEXT = 20
const val RADIUS_EYE = 5
