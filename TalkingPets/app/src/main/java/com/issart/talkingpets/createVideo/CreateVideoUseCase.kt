package com.issart.talkingpets.createVideo

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
//import com.homesoft.encoder.Muxer
//import com.homesoft.encoder.MuxerConfig
//import com.homesoft.encoder.MuxingError
//import com.homesoft.encoder.MuxingSuccess

class CreateVideoUseCase {

//    private var muxer: Muxer? = null

    suspend fun createVideo(context: Context, images: List<Bitmap>) {
//        val file = FileUtils.getVideoFile(context, "video.mp4")
//
//        val muxerConfig = MuxerConfig(
//            file = file,
//            videoWidth = 600,
//            videoHeight = 600,
//            framesPerImage = 1
//        )
//        muxer = Muxer(context, muxerConfig)
//
//        when (val result = muxer?.muxAsync(images)) {
//            is MuxingSuccess -> {
//                Log.i(TAG, "Video muxed - file path: ${result.file.absolutePath}")
////                onMuxerCompleted()
//            }
//            is MuxingError -> {
//                Log.e(TAG, "There was an error muxing the video")
////                bt_make.isEnabled = true
//            }
//        }
    }

    companion object {
        const val TAG = "CreateVideoUseCase"
    }

}