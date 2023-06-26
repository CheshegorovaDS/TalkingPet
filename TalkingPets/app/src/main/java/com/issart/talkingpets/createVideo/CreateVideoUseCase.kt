package com.issart.talkingpets.createVideo

import android.content.Context
import android.graphics.Bitmap
import org.jcodec.api.android.AndroidSequenceEncoder
import java.io.File
import javax.inject.Inject

class CreateVideoUseCase @Inject constructor() {

    operator fun invoke(context: Context, images: List<Bitmap>): File {
        val file = FileUtils.getVideoFile(context, "talking_pet.mp4")
        val encoder = AndroidSequenceEncoder.create25Fps(file)

        for (bitmap in images) {
            encoder.encodeImage(bitmap)
        }

        encoder.finish()

        return file
    }

    companion object {
        const val TAG = "CreateVideoUseCase"
    }

}