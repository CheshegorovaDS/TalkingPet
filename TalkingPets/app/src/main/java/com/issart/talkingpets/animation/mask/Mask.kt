package com.issart.talkingpets.animation.mask

import org.opencv.core.Mat

internal fun getMask(dst: Mat, top: Int, bottom: Int, left: Int, right: Int): Mat {
    val mask = Mat(dst.rows(), dst.cols(), dst.type())

    for (i in top until bottom) {
        for (j in left until right) {
            mask.put(i, j, DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE)
        }
    }

    return mask
}

const val DEFAULT_VALUE = 1.0
