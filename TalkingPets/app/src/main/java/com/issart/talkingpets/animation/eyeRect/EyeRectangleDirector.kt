package com.issart.talkingpets.animation.eyeRect

import com.issart.talkingpets.animation.model.Eye
import com.issart.talkingpets.animation.model.Face
import org.opencv.core.Mat

internal class EyeRectangleDirector(
    private val mat: Mat,
    private val eye: Eye,
    private val face: Face,
    private val newEyeHeight: Double
    ) {

     fun createTopEyeRectangle(builder: EyeRectanglesBuilder) {
         builder.getRectangleFromMat(
             mat = mat,
             top = (eye.y - face.radius),
             bottom = (eye.y - eye.radius),
             left = (eye.x - eye.radius),
             right = (eye.x + eye.radius)
         )

         builder.getNewHeight(
             newEyeRectHeight = newEyeHeight,
             heightAllRectangles = getRectanglesHeight()
         )
     }

    fun createEyeRectangle(builder: EyeRectanglesBuilder) {
        builder.getRectangleFromMat(
            mat = mat,
            top = (eye.y - eye.radius),
            bottom = (eye.y + eye.radius),
            left = (eye.x - eye.radius),
            right = (eye.x + eye.radius)
        )

        builder.getNewHeight(
            newEyeRectHeight = newEyeHeight,
            heightAllRectangles = getRectanglesHeight()
        )
    }

    fun createBottomEyeRectangle(builder: EyeRectanglesBuilder) {
        builder.getRectangleFromMat(
            mat = mat,
            top = (eye.y + eye.radius),
            bottom = (eye.y + face.radius),
            left = (eye.x - eye.radius),
            right = (eye.x + eye.radius)
        )

        builder.getNewHeight(
            newEyeRectHeight = newEyeHeight,
            heightAllRectangles = getRectanglesHeight()
        )
    }

    private fun getRectanglesHeight() = face.radius * 2

}
