package com.issart.talkingpets.ui.recorder.recorder

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioRecord
import android.os.CountDownTimer
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

    private var timer: CountDownTimer? = null

    private var audioFilePath: String? = null

    private var mutableAudioFile = MutableLiveData<String?>(null)
    val audioFile: LiveData<String?> = mutableAudioFile

    private var mutableAudioDuration = MutableLiveData(DEFAULT_TIMER_VALUE)
    val audioDuration: LiveData<String> = mutableAudioDuration

    private fun setAudioFile(audioPath: String?) {
        mutableAudioFile.value = audioPath
    }

    private fun setAudioDuration(duration: Int) {
        mutableAudioDuration.value = String.format(
            DURATION_STRING_FORMAT,
            duration / SECONDS_IN_MINUTE,
            duration % SECONDS_IN_MINUTE
        )
    }

    fun clearRecorder() {
        mutableAudioFile.value = null
        //delete audio from storage
    }

    fun start(context: Context) {
        if (recorder != null) stop()

        initAudioRecorder()
//        recorder?.startRecording()
        if (timer == null) {
            timer = createTimer().apply { start() }
        }
        isRecording = true
//        writeAudioDataToStorage(context)
    }

    fun stop() {
        finishTimer()

        if (!isRecording) return
        try {
            isRecording = false
//            recorder?.stop()
//            recorder?.release()
            setAudioFile("hjhjh")//delete debug
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

    private fun createTimer() = object : CountDownTimer(TIMEOUT_MILLISECONDS, COUNTDOWN_INTERVAL) {

        override fun onTick(millisUntilFinished: Long) {
            val seconds = (TIMEOUT_MILLISECONDS - millisUntilFinished) / MILLISECONDS_IN_SECONDS
            setAudioDuration(seconds.toInt())
        }

        override fun onFinish() {
            setAudioDuration(0)
            timer = null
            stop()
        }

    }

    private fun finishTimer() {
        timer?.cancel()
        timer = null
        setAudioDuration(0)
    }

    companion object {
        const val TIMEOUT_SECONDS = 10
        const val MILLISECONDS_IN_SECONDS = 1_000
        const val SECONDS_IN_MINUTE = 60
        const val COUNTDOWN_INTERVAL = 1_000L
        const val TIMEOUT_MILLISECONDS = 10_000L
        const val DURATION_STRING_FORMAT = "%02d:%02d"
        const val DEFAULT_TIMER_VALUE = "00:00"
    }

//        const val SAVE_AUDIO_EXCEPTION = "SaveAudioException"

}
