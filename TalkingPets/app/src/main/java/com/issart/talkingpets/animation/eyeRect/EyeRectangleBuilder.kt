package com.issart.talkingpets.animation.eyeRect

import com.issart.talkingpets.animation.mat.getResizeMat
import com.issart.talkingpets.animation.mat.getSubmat
import org.opencv.core.Mat

internal class EyeRectangleBuilder : EyeRectanglesBuilder {

    private var rectangle: Mat? = null
    private var newHeight: Double? = null

    override fun getRectangleFromMat(
        mat: Mat,
        top: Double,
        bottom: Double,
        left: Double,
        right: Double
    ) {
        rectangle = getSubmat(mat, top, bottom, left, right)
    }

    override fun getNewHeight(newEyeRectHeight: Double, heightAllRectangles: Double) {
        newHeight = newEyeRectHeight.toInt().toDouble()
    }

    override fun getResizedRectangle(): Mat = when {
        rectangle == null -> throw NullPointerException(RECTANGLE_IS_NULL)
        newHeight == null -> throw NullPointerException(NEW_HEIGHT_IS_NULL)
        else -> getResizeMat(rectangle!!, newHeight!!)
    }

    companion object {
        private const val NAME = "EyeRectangleBuilder:"
        const val RECTANGLE_IS_NULL = "$NAME Center rectangle is null."
        const val NEW_HEIGHT_IS_NULL = "$NAME New height of center rectangle is null."
    }

}
