package com.issart.talkingpets.animation.eyeRect

import com.issart.talkingpets.animation.mask.getMask
import com.issart.talkingpets.animation.mat.copySubmatToMat
import org.opencv.core.Mat

internal class MatWithNewEyeRectanglesCreator(
    private val mat: Mat,
    private val submats: List<Mat>,
    private val firstRow: Int,
    private val lastRow: Int,
    private val firstCol: Int,
    private val lastCol: Int
) {

    private val dst = Mat(mat.rows(), mat.cols(), mat.type())
    private var top = firstRow
    private var bottom = top + submats[0].rows()
    private var left = firstCol
    private var right = lastCol

    fun copySubmats(): Mat {
        for ((index, submat) in submats.withIndex()) {
            copySubmat(submat)

            if (submats.size > index.inc()) {
                top = bottom + 1
                bottom = top + submats[index + 1].rows()
            }
        }

        return dst
    }

    private fun copySubmat(submat: Mat) {
        copySubmatToMat(
            submat = submat,
            dst = dst,
            top = top,
            bottom = bottom,
            left = left,
            right = right
        )
    }

    fun getMaskForSubmat() = getMask(
        mat,
        firstRow,
        lastRow,
        firstCol,
        lastCol
    )

}
