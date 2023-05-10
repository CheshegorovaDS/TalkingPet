package com.issart.talkingpets.ui.common.dragPoints

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.issart.talkingpets.R

@Composable
fun TransformablePoint(
    zoom: Float,
    offsetX: Float,
    offsetY: Float,
    setZoom: (Float) -> Unit,
    setOffset: (Float, Float) -> Unit,
    imageId: Int = DEFAULT_IMAGE_POINT_ID,
    description: String = DEFAULT_CONTENT_DESCRIPTION
) {
    val transformableState = rememberTransformableState { zoomChange, offsetChange, _ ->
        setZoom(zoom * zoomChange)
        setOffset(
            offsetX + offsetChange.x,
            offsetY + offsetChange.y
        )
    }

    Box(
        modifier = Modifier
            .size(SCALE_BOX_SIZE.dp)
            .graphicsLayer(
                translationX = offsetX,
                translationY = offsetY,
            )
            .transformable(transformableState),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = DEFAULT_CONTENT_DESCRIPTION,
            modifier = Modifier.scale(zoom)
        )
    }

}

const val SCALE_BOX_SIZE = 100
const val DEFAULT_CONTENT_DESCRIPTION = "Transformable point"
const val DEFAULT_IMAGE_POINT_ID = R.drawable.ic_record
