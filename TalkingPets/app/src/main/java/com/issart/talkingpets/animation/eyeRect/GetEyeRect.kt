package com.issart.talkingpets.animation.eyeRect

import com.issart.talkingpets.animation.mat.getSubmat
import org.opencv.core.Mat


//internal fun getTopEyeRectangle(src: Mat, x: Double, y: Double, radiusEye: Int, radiusFace: Int): Mat {
//    return getSubmat(
//        src,
//        (y - radiusFace),
//        (y - radiusEye),
//        (x - radiusEye),
//        (x + radiusEye)
//    )
//}

internal fun getEyeRectangle(src: Mat, x: Double, y: Double, radius: Int): Mat {
    return getSubmat(
        src,
        (y - radius),
        (y + radius),
        (x - radius),
        (x + radius)
    )
}

internal fun getBottomEyeRectangle(src: Mat, x: Double, y: Double, radiusEye: Int, radiusFace: Int): Mat {
    return getSubmat(
        src,
        (y + radiusEye),
        (y + radiusFace),
        (x - radiusEye),
        (x + radiusEye)
    )
}