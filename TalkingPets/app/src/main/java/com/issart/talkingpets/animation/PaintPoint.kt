package com.issart.talkingpets.animation

import android.graphics.Bitmap
import com.issart.talkingpets.animation.model.Eye
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc

private fun paintControlPoints(photo: Bitmap, point: Eye): Bitmap {
    val src = Mat()
    Utils.bitmapToMat(photo, src)

    val dst = Mat(src.rows(), src.cols(), src.type())
    src.copyTo(dst)

    val newPhoto = paintCircles(
        dst,
        listOf(
            Point(point.x, point.y)
        ),
        point.radius.toInt()
    )

    val bitmap = Bitmap.createBitmap(newPhoto.width(), newPhoto.height(), Bitmap.Config.ARGB_8888)
    Utils.matToBitmap(newPhoto, bitmap)
    return bitmap
}

fun paintCircles(originalMat: Mat, points: List<Point>, radius: Int): Mat {
    val color = Scalar(255.0, 255.0, 255.0, 0.0)

    for (point in points) {
        Imgproc.circle(
            originalMat,
            point,
            radius,
            color,
            Core.FILLED
        )
    }

    return originalMat
}
