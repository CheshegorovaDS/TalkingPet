package com.issart.talkingpets.animation.mat

import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

internal fun getResizeMat(src: Mat, newHeight: Double): Mat {
    val dst = Mat()
    val newWidth = src.width().toDouble()
    Imgproc.resize(src, dst, Size(newWidth, newHeight))
    return dst
}
