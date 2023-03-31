package com.issart.talkingpets.animation

import android.graphics.Bitmap
import com.issart.talkingpets.animation.model.BlinkingEyeHeights
import com.issart.talkingpets.animation.model.Eye
import com.issart.talkingpets.animation.model.Face
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

fun start() {
    val bitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)
    val eye = Eye(CENTER_EYE_X, CENTER_EYE_Y, RADIUS_EYE)
    val face = Face(CENTER_FACE_X, CENTER_FACE_Y, MINOR_AXIS_FACE, MAJOR_AXIS_FACE)
    val photos = getBlinkEyesImages(
        eye = eye,
        face = face,
        photo = bitmap
    )
}

fun getBlinkEyesImages(
    eye: Eye,
    face: Face,
    photo: Bitmap
): List<Bitmap> {
    val photos = mutableListOf<Bitmap>()
    photos.add(photo)

    val heights = BlinkingEyeHeights(eye.radius).heights
    for (height in heights) {
        photos.add(getResizeEyeImage(eye, face, photo, height))
    }
    return photos
}

fun getResizeEyeImage(
    eye: Eye,
    face: Face,
    photo: Bitmap,
    newEyeHeight: Double
): Bitmap {
    val src = Mat()
    Utils.bitmapToMat(photo, src)

    val newTopEyeRect = getResizedTopEyeRectangle(
        src,
        eye.x,
        eye.y,
        eye.radius,
        newEyeHeight,
        face.minorAxis
    )

    val newEyeRect = getResizedEyeRectangle(
        src,
        eye.x,
        eye.y,
        eye.radius,
        newEyeHeight
    )

    val newBottomRect = getResizedBottomEyeRectangle(
        src,
        eye.x,
        eye.y,
        eye.radius,
        newEyeHeight,
        face.minorAxis
    )

    val newPhoto = getImageWithNewRectangles(
        newTopEyeRect,
        newEyeRect,
        newBottomRect,
        src,
        eye.x,
        eye.y,
        eye.radius,
        face.minorAxis
    )

    val bitmap = Bitmap.createBitmap(newPhoto.width(), newPhoto.height(), Bitmap.Config.ARGB_8888)
    Utils.matToBitmap(newPhoto, bitmap)

    return bitmap
}

fun getImageWithNewRectangles(
    topEyeRect: Mat,
    eyeRect: Mat,
    bottomEyeRect: Mat,
    src: Mat,
    x: Double,
    y: Double,
    radiusEye: Double,
    radiusFace: Double
): Mat {
    val matrix = Mat(src.rows(), src.cols(), src.type())
    saveEyeRectangles(
        matrix,
        topEyeRect,
        eyeRect,
        bottomEyeRect,
        (x - radiusEye).toInt(),
        (y - radiusFace).toInt()
    )

    val mask = getMask(
        src,
        (x - radiusEye).toInt(),
        (x + radiusEye).toInt(),
        (y - radiusFace).toInt(),
        (y + radiusFace).toInt()
    )

    copyMatFromTo(matrix, src, mask)

    return src
}

private fun copyMatFromTo(from: Mat, to: Mat, mask: Mat) {
    from.copyTo(to, mask)
}

private fun getMask(dst: Mat, top: Int, bottom: Int, left: Int, right: Int): Mat {
    val mask = Mat(dst.rows(), dst.cols(), dst.type())

    for (i in left until right) {
        for (j in top until bottom) {
            mask.put(i, j, 1.0, 1.0, 1.0)
        }
    }

    return mask
}

private fun saveEyeRectangles(
    dst: Mat,
    topImage: Mat,
    centerImage: Mat,
    bottomImage: Mat,
    firstRow: Int,
    firstCol: Int
) {
    val left = firstRow
    val right = left + topImage.cols()
    var top = firstCol
    var bottom = top + topImage.rows()

    for (i in top..bottom) {
        for (j in left..right) {
            val arr = topImage.get(i - top, j - left)
            if (arr != null) {
                dst.put(i, j, arr[0], arr[1], arr[2])
            }
        }
    }

    top = bottom
    bottom = top + centerImage.rows()

    for (i in top..bottom) {
        for (j in left..right + 1) {
            val arr = centerImage.get(i - top, j - left)
            if (arr != null) {
                dst.put(i, j, arr[0], arr[1], arr[2])
            }
        }
    }

    top = bottom
    bottom = top + bottomImage.rows()

    for (i in top..bottom) {
        for (j in left..right) {
            val arr = bottomImage.get(i - top, j - left)
            if (arr != null) {
                dst.put(i, j, arr[0], arr[1], arr[2])
            }
        }
    }

}


private fun getResizedTopEyeRectangle(
    photo: Mat,
    x: Double,
    y: Double,
    radiusEye: Double,
    newEyeHeight: Double,
    radiusFace: Double
) : Mat {
    val topEyeRect = getTopEyeRectangle(photo, x, y, radiusEye.toInt(), radiusFace.toInt())

    val newRectHeight = ((2 * radiusFace) - newEyeHeight) / 2

    return getResizeMat(topEyeRect, newRectHeight)
}

private fun getTopEyeRectangle(src: Mat, x: Double, y: Double, radiusEye: Int, radiusFace: Int): Mat {
    return src.submat(
        (y - radiusFace).toInt(),
        (y - radiusEye).toInt(),
        (x - radiusEye).toInt(),
        (x + radiusEye).toInt()
    )
}

private fun getResizedEyeRectangle(
    photo: Mat,
    x: Double,
    y: Double,
    radiusEye: Double,
    newEyeHeight: Double
) : Mat {
    val eyeRect = getEyeRectangle(photo, x, y, radiusEye.toInt())

    return getResizeMat(eyeRect, newEyeHeight)
}

private fun getEyeRectangle(src: Mat, x: Double, y: Double, radius: Int): Mat {
    return src.submat(
        (y - radius).toInt(),
        (y + radius).toInt(),
        (x - radius).toInt(),
        (x + radius).toInt()
    )
}

fun getResizedBottomEyeRectangle(
    photo: Mat,
    x: Double,
    y: Double,
    radiusEye: Double,
    newEyeHeight: Double,
    radiusFace: Double
): Mat {
    val bottomEyeRect = getBottomEyeRectangle(photo, x, y, radiusEye.toInt(), radiusFace.toInt())

    val increaseHeightTop = ((2 * radiusFace) - newEyeHeight) / 2

    val newRectHeight = (2 * radiusFace) - newEyeHeight - increaseHeightTop

    return getResizeMat(bottomEyeRect, newRectHeight)
}

private fun getBottomEyeRectangle(src: Mat, x: Double, y: Double, radiusEye: Int, radiusFace: Int): Mat {
    return src.submat(
        (y + radiusEye).toInt(),
        (y + radiusFace).toInt(),
        (x - radiusEye).toInt(),
        (x + radiusEye).toInt()
    )
}

private fun getResizeMat(src: Mat, newHeight: Double): Mat {
    val dst = Mat()
    val newWidth = src.width().toDouble()
    Imgproc.resize(src, dst, Size(newWidth, newHeight))
    return dst
}

const val CENTER_EYE_X = 10.0
const val CENTER_EYE_Y = 10.0
const val RADIUS_EYE = 10.0
const val MAJOR_AXIS_FACE = 10.0
const val MINOR_AXIS_FACE = 10.0
const val CENTER_FACE_X = 10.0
const val CENTER_FACE_Y = 10.0