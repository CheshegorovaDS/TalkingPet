package com.issart.talkingpets.animation

import android.graphics.Bitmap
import com.issart.talkingpets.animation.eyeRect.*
import com.issart.talkingpets.animation.eyeRect.getResizedBottomEyeRectangle
import com.issart.talkingpets.animation.mask.getMask
import com.issart.talkingpets.animation.mat.copyMatFromTo
import com.issart.talkingpets.animation.mat.copySubmatToMat
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

    val director = EyeRectangleDirector(
        mat = src,
        eye = eye,
        face = face,
        newEyeHeight = newEyeHeight
    )

    val topEyeRectangleBuilder = TopEyeRectangleBuilder()
    director.createTopEyeRectangle(topEyeRectangleBuilder)
    val newTopEyeRect = topEyeRectangleBuilder.getResizedRectangle()


    val eyeRectangleBuilder = EyeRectangleBuilder()
    director.createEyeRectangle(eyeRectangleBuilder)
    val newEyeRect = eyeRectangleBuilder.getResizedRectangle()

    val bottomEyeRectangleBuilder = BottomEyeRectangleBuilder()
    director.createBottomEyeRectangle(bottomEyeRectangleBuilder)
    val newBottomEyeRect = bottomEyeRectangleBuilder.getResizedRectangle()

    val newPhoto = getImageWithNewRectangles(
        newTopEyeRect,
        newEyeRect,
        newBottomEyeRect,
        src,
        eye.x,
        eye.y,
        eye.radius,
        face.radius
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

    copySubmatToMat(
        submat = topImage,
        dst = dst,
        top = top,
        bottom = bottom,
        left = left,
        right = right
    )

    top = bottom
    bottom = top + centerImage.rows()

    copySubmatToMat(
        submat = centerImage,
        dst = dst,
        top = top,
        bottom = bottom,
        left = left,
        right = right + 1 // check without +1
    )

    top = bottom
    bottom = top + bottomImage.rows()

    copySubmatToMat(
        submat = bottomImage,
        dst = dst,
        top = top,
        bottom = bottom,
        left = left,
        right = right
    )

}
