package com.issart.talkingpets.animation.mat

import org.opencv.core.Mat

internal fun copyMatFromTo(from: Mat, to: Mat, mask: Mat) = from.copyTo(to, mask)

internal fun copySubmatToMat(
    submat: Mat,
    dst: Mat,
    top: Int,
    bottom: Int,
    left: Int,
    right: Int
) {
    for (i in top..bottom) {
        for (j in left..right) {
            val arr = submat.get(i - top, j - left)
            if (arr != null) {
                dst.put(i, j, arr[0], arr[1], arr[2])
            }
        }
    }
}
