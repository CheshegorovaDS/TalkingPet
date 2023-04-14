package com.issart.talkingpets.animation.mat

import org.opencv.core.Mat

internal fun getSubmat(
    src: Mat,
    top: Double,
    bottom: Double,
    left: Double,
    right: Double
) = src.submat(
    top.toInt(),
    bottom.toInt(),
    left.toInt(),
    right.toInt()
)
