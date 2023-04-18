package com.issart.talkingpets.ui.detector

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.model.Point

@Composable
fun DetectorBox(viewModel: DetectorViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val boxSize = configuration.screenWidthDp
    val density = LocalDensity.current

    val leftEye = viewModel.leftEye.observeAsState(
        initial = Point(
            getEyeOffsetX(boxSize, density).toFloat(),
            getEyeOffsetY(boxSize, density).toFloat()
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(boxSize.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_record),
            contentDescription = "left eye",
            modifier = Modifier
                .offset(leftEye.value.x.dp, leftEye.value.y.dp)
                .pointerInput(Unit) {
                detectDragGesturesAfterLongPress { change, dragAmount ->
                    change.consume()
                    viewModel.setLeftEye(
                        leftEye.value.x + dragAmount.x,
                        leftEye.value.y + dragAmount.y
                    )
                }
            }
        )

    }

}

private fun getEyeOffsetX(widthCanvas: Int, density: Density) = density.run {
    widthCanvas * OFFSET_X_EYE
}

private fun getEyeOffsetY(heightCanvas: Int, density: Density) = density.run {
    heightCanvas * OFFSET_Y_EYE
}
