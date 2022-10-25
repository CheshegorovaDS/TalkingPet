package com.issart.talkingpets.ui.recorder.recorder


import android.annotation.SuppressLint
import android.media.AudioRecord
import android.os.Environment
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RecorderViewModel @Inject constructor() : ViewModel() {

    private var recorder: AudioRecord? = null

    private var isRecording: Boolean = false
    private val recorderConfig = RecorderConfig()
    private var audioFilePath: String? = null

    fun start() {
        if (recorder != null) stop()

        initAudioRecorder()
        recorder?.startRecording()
        isRecording = true
        writeAudioDataToStorage()
    }

    fun stop() {
        if (!isRecording) return
        try {
            isRecording = false
            recorder?.stop()
            recorder?.release()
            audioFilePath?.let {
//                WaveHeaderWriter(it, recorderConfig).writeHeader()
//                requestStorage.audioFile = it
            }
        } catch (ex: IllegalStateException) {
//            logger.logDebugLevel(ex.message.toString())
        } finally {
            recorder = null
        }
    }

    @SuppressLint("MissingPermission")
    private fun initAudioRecorder() {
        recorder = AudioRecord(
            recorderConfig.audioSource,
            recorderConfig.sampleRateInHz,
            recorderConfig.channelConfig,
            recorderConfig.audioFormat,
            recorderConfig.bufferSizeInBytes
        )
    }

    private fun writeAudioDataToStorage() {
        val file = try {
            createAudioFile()
        } catch(ex: IOException) {
//            logger.logExceptionLevel(ex, SAVE_AUDIO_EXCEPTION)
            null
        }

        audioFilePath = file?.absolutePath

        val data = ByteArray(recorderConfig.bufferSizeInBytes)
        val outputStream = file?.outputStream()
        outputStream?.use {
            while (isRecording) {
                val operationStatus = recorder?.read(data, 0, recorderConfig.bufferSizeInBytes)

                if (AudioRecord.ERROR_INVALID_OPERATION != operationStatus) {
                    outputStream.write(data)
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createAudioFile(): File {
        val filepath = Environment.getExternalStorageDirectory().toString() + "/Download/record.mp3"
        val storageDir: File = File(filepath)//context.cacheDir
        return File.createTempFile(
            "record",
            ".wav",
            storageDir
        )
    }

    companion object {
        const val SAVE_AUDIO_EXCEPTION = "SaveAudioException"
    }

}
