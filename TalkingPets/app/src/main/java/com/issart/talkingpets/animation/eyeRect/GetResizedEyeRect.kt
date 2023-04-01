package com.issart.talkingpets.animation.eyeRect

import com.issart.talkingpets.animation.mat.getResizeMat
import org.opencv.core.Mat

//internal fun getResizedTopEyeRectangle(
//    photo: Mat,
//    x: Double,
//    y: Double,
//    radiusEye: Double,
//    newEyeHeight: Double,
//    radiusFace: Double
//) : Mat {
//    val topEyeRect = getTopEyeRectangle(photo, x, y, radiusEye.toInt(), radiusFace.toInt())
//
//    val newRectHeight = ((2 * radiusFace) - newEyeHeight) / 2
//
//    return getResizeMat(topEyeRect, newRectHeight)
//}

//internal fun getResizedEyeRectangle(
//    photo: Mat,
//    x: Double,
//    y: Double,
//    radiusEye: Double,
//    newEyeHeight: Double
//) : Mat {
//    val eyeRect = getEyeRectangle(photo, x, y, radiusEye.toInt())
//
//    return getResizeMat(eyeRect, newEyeHeight)
//}

internal fun getResizedBottomEyeRectangle(
    photo: Mat,
    x: Double,
    y: Double,
    radiusEye: Double,
    newEyeHeight: Double,
    radiusFace: Double//maby need height other rectangles: newTopEye, newEye
): Mat {
    val bottomEyeRect = getBottomEyeRectangle(photo, x, y, radiusEye.toInt(), radiusFace.toInt())

    val increaseHeightTop = ((2 * radiusFace) - newEyeHeight) / 2 // maybe need convert to Int

    val newRectHeight = (2 * radiusFace) - newEyeHeight - increaseHeightTop // maybe wrong, check

    return getResizeMat(bottomEyeRect, newRectHeight)
}
