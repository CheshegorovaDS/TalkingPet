package com.issart.talkingpets.ui.detector.detectorPoints

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import com.issart.talkingpets.ui.common.dragPoints.TransformablePoint
import com.issart.talkingpets.ui.detector.detectorPoints.model.eye.EyeParams
import com.issart.talkingpets.ui.model.Eye

@Composable
fun DetectorEyeBox(
    eye: LiveData<Eye>,
    params: EyeParams,
    setZoom: (Float) -> Unit,
    setOffset: (Float, Float) -> Unit,
    boxSize: Int
) {
    val eyeState = eye.observeAsState(
        initial = Eye(
            getEyeOffsetX(boxSize, params.offsetX).toFloat(),
            getEyeOffsetY(boxSize, params.offsetY).toFloat(),
            1f
        )
    )

    EyeImageTransformableState(
        eye = eyeState,
        description = params.text,
        setZoom = setZoom,
        setOffset = setOffset
    )
}

@Composable
fun EyeImageTransformableState(
    eye: State<Eye>,
    description: String,
    setZoom: (Float) -> Unit,
    setOffset: (Float, Float) -> Unit
) {
    TransformablePoint(
        zoom = eye.value.zoom,
        offsetX = eye.value.x,
        offsetY = eye.value.y,
        setZoom = setZoom,
        setOffset = setOffset,
        description = description
    )
}
