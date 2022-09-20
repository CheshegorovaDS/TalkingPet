package com.issart.talkingpets.ui.editor

import android.graphics.Bitmap
import kotlin.math.*

fun getScale(angle: Float, width: Int, height: Int, cropWidth: Float, cropHeight: Float): Float {
    val a = abs(height * sin(angle))
    val b = abs(width * cos(angle))
    val c = abs(width * sin(angle))
    val d = abs(height * cos(angle))

    val newWidth = a + b
    val newHeight = c + d

    val scaleFactor = when (val scale = max( cropWidth / (a + b), cropHeight / (c + d))) {
        1f -> scale
        else -> scale - 0.1f
    }
    return scaleFactor
}

fun getScaledBitmap(degrees: Float, rotatedBitmap: Bitmap): Bitmap {
    val scaleFactor = getScaleByAngle(degrees)

    val widthOffset = ((1 - scaleFactor) / 2 * rotatedBitmap.width).roundToInt()
    val heightOffset = ((1 - scaleFactor) / 2 * rotatedBitmap.height).roundToInt()
    val numWidthPixels: Int = rotatedBitmap.width - 2 * widthOffset
    val numHeightPixels: Int = rotatedBitmap.height - 2 * heightOffset
    val rescaledBitmap = Bitmap.createBitmap(
        rotatedBitmap,
        widthOffset,
        heightOffset,
        numWidthPixels,
        numHeightPixels,
        null,
        true
    )
    return rescaledBitmap
}

fun getScaleByAngle(angle: Float) = when {
    (angle % 90) == 0f -> 1f
    (angle % 45) == 0f -> 0.5f
    (angle % 30) == 0f -> 0.53f
    else -> 0.66f
}