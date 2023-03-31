package com.issart.talkingpets.animation.mat

import org.opencv.core.Mat

internal fun copyMatFromTo(from: Mat, to: Mat, mask: Mat) = from.copyTo(to, mask)
