package com.issart.talkingpets.ui.common.figures

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import com.issart.talkingpets.ui.common.dragPoints.POINT_SIZE
import com.issart.talkingpets.ui.detector.detectorPoints.model.face.FacePoints
import com.issart.talkingpets.ui.theme.Green

@Composable
fun Arc(
    from: FacePoints,
    to: FacePoints,
    color: Color = Green,
    pointOffset: Int = POINT_SIZE,
    offset: Offset,
    startAngle: Float,
    sweepAngle: Float
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path()
        path.addArc(
            oval = Rect(
                offset = offset,//getOffset(from, to, pointOffset),
                size = Size(getRectSize(from.x, to.x), getRectSize(from.y, to.y))
            ),
            startAngleDegrees = startAngle,//getStartAngle(from.y, to.y),
            sweepAngleDegrees = sweepAngle//getSweepAngle(from, to)
        )
        drawPath(
            path = path,
            color = color,
            style = Stroke(ARC_STROKE_WIDTH)
        )
//        drawRect(
//            topLeft = offset,
//            size = Size(getRectSize(from.x, to.x), getRectSize(from.y, to.y)),
//            color = color
//        )
    }
}
//top - left start = -90, left = -90
//offset to.x, from.y
//top - right start = -90, left = 90
//offset to.x - from.x, from.y
//bottom - right start = 90, sweep = -90
//offset to.x - from.x, from.y - to.y
//bottom - left start =90, sweep = 90
//offset to.x, from.y - to.y

//private fun getStartAngle(fromY: Float, toY: Float) = if (fromY < toY) {
//    -90f
//} else {
//    90f
//}
//
//private fun getSweepAngle(from: FacePoints, to: FacePoints) = when {
//    from.x > to.x && from.y > to.y -> 90f
//    from.x < to.x && from.y < to.y -> 90f
//    else -> -90f
//}
//
//private fun getOffset(from: FacePoints, to: FacePoints, pointOffset: Int): Offset {
//    val offsetX = if (from.x > to.x) {
//        to.x
//    } else {
//        to.x - from.x
//    }
//    val offsetY = if (from.y > to.y) {
//        from.y - to.y
//    } else {
//        from.y
//    }
//    return Offset(offsetX + pointOffset, offsetY + pointOffset)
//}

private fun getRectSize(fromCoordinate: Float, toCoordinate: Float) = if (fromCoordinate > toCoordinate) {
    2 * (fromCoordinate - toCoordinate)
} else {
    2 * (toCoordinate - fromCoordinate)
}

const val ARC_STROKE_WIDTH = 3f
