package com.issart.talkingpets.animation

import android.graphics.Bitmap
import com.issart.talkingpets.animation.eyeRect.getResizedBottomEyeRectangle
import com.issart.talkingpets.animation.eyeRect.getResizedEyeRectangle
import com.issart.talkingpets.animation.eyeRect.getResizedTopEyeRectangle
import com.issart.talkingpets.animation.mask.getMask
import com.issart.talkingpets.animation.mat.copyMatFromTo
import com.issart.talkingpets.animation.model.BlinkingEyeHeights
import com.issart.talkingpets.animation.model.Eye
import com.issart.talkingpets.animation.model.Face
import org.opencv.android.Utils
import org.opencv.core.Mat

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
