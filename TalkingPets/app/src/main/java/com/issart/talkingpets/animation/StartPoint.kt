package com.issart.talkingpets.animation

import android.graphics.Bitmap
import com.issart.talkingpets.animation.blink.getBlinkEyesImages
import com.issart.talkingpets.animation.model.Eye
import com.issart.talkingpets.animation.model.Face

//delete

fun start(bitmap: Bitmap): List<Bitmap> {//delete
//    val bitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)
    val eye = Eye(CENTER_EYE_X, CENTER_EYE_Y, RADIUS_EYE)
    val face = Face(CENTER_FACE_X, CENTER_FACE_Y, MINOR_AXIS_FACE)
    val photos = getBlinkEyesImages(
        eye = eye,
        face = face,
        photo = bitmap
    )
    return photos
}

const val CENTER_EYE_X = 630.0
const val CENTER_EYE_Y = 510.0
const val RADIUS_EYE = 35.0
const val MAJOR_AXIS_FACE = 10.0
const val MINOR_AXIS_FACE = 66.0
const val CENTER_FACE_X = 10.0
const val CENTER_FACE_Y = 10.0