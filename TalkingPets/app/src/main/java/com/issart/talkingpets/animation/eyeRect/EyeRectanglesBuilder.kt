package com.issart.talkingpets.animation.eyeRect

import org.opencv.core.Mat

interface EyeRectanglesBuilder {

    fun getRectangleFromMat(
        mat: Mat,
        top: Double,
        bottom: Double,
        left: Double,
        right: Double
    )

    fun getNewHeight(
        newEyeRectHeight: Double,
        heightAllRectangles: Double
    )

    fun getResizedRectangle(): Mat

}
