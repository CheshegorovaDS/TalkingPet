package com.issart.talkingpets.ui.detector.detectorPoints

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.LiveData
import com.issart.talkingpets.ui.common.dragPoints.DraggablePoint
import com.issart.talkingpets.ui.detector.detectorPoints.model.face.FacePoints

@Composable
fun DetectorFaceBox(
    facePoints: LiveData<FacePoints>,
    defaultFacePoints: FacePoints,
    setOffset: (Float, Float) -> Unit,
    boxSize: Int
) {
    val density = LocalDensity.current

    val pointState = facePoints.observeAsState(
        initial = FacePoints(
            x = getEyeOffsetX(boxSize, density, defaultFacePoints.x.toDouble()).toFloat(),
            y = getEyeOffsetY(boxSize, density, defaultFacePoints.y.toDouble()).toFloat()
        )
    )

    DraggablePoint(
        offsetX = pointState.value.x,
        offsetY = pointState.value.y,
        setOffset = setOffset
    )
}
