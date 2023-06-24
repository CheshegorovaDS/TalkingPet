package com.issart.talkingpets.animation.blink

import android.graphics.Bitmap
import com.issart.talkingpets.animation.model.BlinkingEyeHeights
import com.issart.talkingpets.animation.model.Eye
import com.issart.talkingpets.animation.model.Face

internal fun getBlinkEyesImages(
    eye: Eye,
    face: Face,
    photo: Bitmap
): List<Bitmap> {
    val photos = mutableListOf<Bitmap>()
    photos.add(photo)

    val heights = BlinkingEyeHeights(eye.radius).heights
    for (height in heights) {
        photos.add(getResizeEyeImage(eye, face, photo, height))
    }
    return photos
}
