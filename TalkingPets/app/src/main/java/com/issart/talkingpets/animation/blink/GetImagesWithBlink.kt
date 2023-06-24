package com.issart.talkingpets.animation.blink

import android.graphics.Bitmap
import com.issart.talkingpets.animation.model.BlinkingEyeHeights
import com.issart.talkingpets.animation.model.Eye
import com.issart.talkingpets.animation.model.Face
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc

internal fun getBlinkEyesImages(
    eye: Eye,
    face: Face,
    photo: Bitmap
): List<Bitmap> {
    val photos = mutableListOf<Bitmap>()
//    photos.add(photo)

    val heights = BlinkingEyeHeights(eye.radius).heights
//    for (height in heights) {
//        photos.add(getResizeEyeImage(eye, face, photo, height))
//    }
    photos.add(
        paintControlPoints(photo, eye)
    )
    return photos
}

private fun paintControlPoints(photo: Bitmap, point: Eye): Bitmap {
    val src = Mat()
    Utils.bitmapToMat(photo, src)

    val dst = Mat(src.rows(), src.cols(), src.type())
    src.copyTo(dst)

    val newPhoto = paintCircles(
        dst,
        listOf(
            Point(point.x, point.y),
//            Point(0.0, 0.0),
//            Point(1080.0, 1080.0),
//            Point(540.0, 540.0)
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
