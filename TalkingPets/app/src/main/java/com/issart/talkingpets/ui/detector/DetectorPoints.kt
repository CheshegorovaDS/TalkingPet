package com.issart.talkingpets.ui.detector

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.common.toast.showToast
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
//        EyeImageDetectGesture(leftEye, viewModel)
        EyeImageTransformableState(viewModel = viewModel)
    }

}

@Composable
private fun EyeImageDetectGesture(leftEye: State<Eye>, viewModel: DetectorViewModel) {
    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.drawable.ic_record),
        contentDescription = "left eye",
        modifier = Modifier
            .offset(leftEye.value.x.dp, leftEye.value.y.dp)
            .scale(leftEye.value.zoom)
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress { change, dragAmount ->
                    change.consume()
                    viewModel.setLeftEyePosition(
                        leftEye.value.x + dragAmount.x,
                        leftEye.value.y + dragAmount.y
                    )
                }
                detectTransformGestures(false) { centroid, _, zoom, _ ->
                    viewModel.setLeftEyeZoom(zoom)
                    showToast(context, "change zoom $zoom")
                }
            }
    )
}

@Composable
fun EyeImageTransformableState(viewModel: DetectorViewModel) {
    var zoom  by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val transformableState = rememberTransformableState { zoomChange, offsetChange, _ ->
        zoom *= zoomChange
        offset += offsetChange
    }
    Image(
        painter = painterResource(id = R.drawable.ic_record),
        contentDescription = "left eye",
        modifier = Modifier
            .scale(zoom)
            .offset(offset.x.dp, offset.y.dp)
            .transformable(transformableState)
    )
}

private fun getEyeOffsetX(widthCanvas: Int, density: Density) = density.run {
    widthCanvas * OFFSET_X_EYE
}

private fun getEyeOffsetY(heightCanvas: Int, density: Density) = density.run {
    heightCanvas * OFFSET_Y_EYE
}
