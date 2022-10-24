package com.issart.talkingpets.ui.editor

import android.graphics.Bitmap
import android.graphics.Matrix

fun getRotatedAndCroppedBitmap(bitmap: Bitmap, degrees: Float): Bitmap {
    val croppedBitmap  = if (bitmap.width < bitmap.height) {
        val offset = (bitmap.height - bitmap.width)/2
        Bitmap.createBitmap(bitmap, 0,  offset, bitmap.width, bitmap.width)
    } else {
        val offset = (bitmap.width - bitmap.height)/2
        Bitmap.createBitmap(bitmap, offset, 0 , bitmap.height, bitmap.height)
    }

    val rotationMatrix = Matrix()
    rotationMatrix.postRotate(degrees)
    val rotatedBitmap = Bitmap.createBitmap(
        croppedBitmap,
        0,
        0,
        croppedBitmap.width,
        croppedBitmap.height,
        rotationMatrix,
        true
    )

    return getScaledBitmap(degrees, rotatedBitmap)
}