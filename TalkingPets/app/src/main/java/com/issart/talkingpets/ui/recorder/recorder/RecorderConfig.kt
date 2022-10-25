package com.issart.talkingpets.ui.recorder.recorder

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder

data class RecorderConfig(
    val audioSource: Int = MediaRecorder.AudioSource.MIC,
    val sampleRateInHz: Int = RATE_IN_HZ,
    val channelConfig: Int = AudioFormat.CHANNEL_IN_MONO,
    val audioFormat: Int = AudioFormat.ENCODING_PCM_16BIT,
    val bufferSizeInBytes: Int = AudioRecord.getMinBufferSize(
        sampleRateInHz,
        channelConfig,
        audioFormat
    )
)

internal fun bitPerSample(audioEncoding: Int) = when (audioEncoding) {
    AudioFormat.ENCODING_PCM_8BIT -> EIGHT_BIT
    else -> SIXTEEN_BIT
}

const val EIGHT_BIT = 8
const val SIXTEEN_BIT = 16
const val RATE_IN_HZ = 16_000
