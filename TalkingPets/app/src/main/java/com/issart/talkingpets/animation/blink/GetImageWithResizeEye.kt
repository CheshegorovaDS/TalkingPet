package com.issart.talkingpets.animation.blink

import android.graphics.Bitmap
import com.issart.talkingpets.animation.eyeRect.BottomEyeRectangleBuilder
import com.issart.talkingpets.animation.eyeRect.EyeRectangleBuilder
import com.issart.talkingpets.animation.eyeRect.EyeRectangleDirector
import com.issart.talkingpets.animation.eyeRect.TopEyeRectangleBuilder
import com.issart.talkingpets.animation.model.Eye
import com.issart.talkingpets.animation.model.Face
import org.opencv.android.Utils
import org.opencv.core.Mat

internal fun getResizeEyeImage(
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

    val eyeRectangleBuilder = EyeRectangleBuilder()
    director.createEyeRectangle(eyeRectangleBuilder)

    val bottomEyeRectangleBuilder = BottomEyeRectangleBuilder()
    director.createBottomEyeRectangle(bottomEyeRectangleBuilder)

    val newPhoto = getImageWithNewRectangles(
        topEyeRectangleBuilder.getResizedRectangle(),
        eyeRectangleBuilder.getResizedRectangle(),
        bottomEyeRectangleBuilder.getResizedRectangle(),
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
