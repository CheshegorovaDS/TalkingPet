package com.issart.talkingpets.ui.recorder.recorder

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioRecord
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private var mutableAudioFile = MutableLiveData<String?>(null)
    val audioFile: LiveData<String?> = mutableAudioFile

    fun setAudioFile(audioPath: String?) {
        mutableAudioFile.value = audioPath
    }

    fun start(context: Context) {
        if (recorder != null) stop()

        initAudioRecorder()
        recorder?.startRecording()
        isRecording = true
        writeAudioDataToStorage(context)
    }

    fun stop() {
        if (!isRecording) return
        try {
            isRecording = false
            recorder?.stop()
            recorder?.release()
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

    private fun writeAudioDataToStorage(context: Context) {
        val file = try {
            createAudioFile(context)
        } catch(ex: IOException) {
//            logger.logExceptionLevel(ex, SAVE_AUDIO_EXCEPTION)
            null
        }

        setAudioFile(file?.absolutePath)

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
    private fun createAudioFile(context: Context): File {
        val storageDir: File = context.cacheDir
        return File.createTempFile(
            "talking_pet_record",
            ".mp3",
            storageDir
        )
    }

//    companion object {
//        const val SAVE_AUDIO_EXCEPTION = "SaveAudioException"
//    }

}
