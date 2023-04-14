package com.issart.talkingpets.animation.blink

import com.issart.talkingpets.animation.eyeRect.MatWithNewEyeRectanglesCreator
import com.issart.talkingpets.animation.mat.copyMatFromTo
import org.opencv.core.Mat

internal fun getImageWithNewRectangles(
    topEyeRect: Mat,
    eyeRect: Mat,
    bottomEyeRect: Mat,
    src: Mat,
    x: Double,
    y: Double,
    radiusEye: Double,
    radiusFace: Double
): Mat {
    val creator = MatWithNewEyeRectanglesCreator (
        src,
        listOf(topEyeRect, eyeRect, bottomEyeRect),
        (y - radiusFace).toInt(),
        (y + radiusFace).toInt(),
        (x - radiusEye).toInt(),
        (x + radiusEye).toInt()
    )

    val matrix = creator.copySubmats()
    val mask = creator.getMaskForSubmat()

    copyMatFromTo(matrix, src, mask)

    return src
}