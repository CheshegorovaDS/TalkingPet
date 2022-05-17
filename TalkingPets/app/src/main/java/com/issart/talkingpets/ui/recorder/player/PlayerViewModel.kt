package com.issart.talkingpets.ui.recorder.player

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor() : ViewModel() {

    private var mediaPlayer: MediaPlayer? = null

    private val mutableIsPlay = MutableLiveData(false)
    val isPlay: LiveData<Boolean> = mutableIsPlay

    private val mutablePlayedAudioId = MutableLiveData<Int>()
    val playedAudio: LiveData<Int> = mutablePlayedAudioId

    fun setPlayedAudio(audioId: Int?) {
        mutablePlayedAudioId.value = audioId
    }

    fun clickPlayButton(currentAudioId: Int, context: Context) {
        if (currentAudioId != playedAudio.value) {
            releasePlayer()
            create(currentAudioId, context)
        } else {
            if (isPlay.value == true) {
                pause()
            } else {
                play()
            }
        }
    }

    fun create(currentAudioId: Int, context: Context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, currentAudioId)
        }
        mutableIsPlay.value = false
        play()
    }


    private fun play() {
        try {
            mediaPlayer?.start()
            mutableIsPlay.value = true
        } catch (e: IOException) {
            releasePlayer()
        }
    }

    private fun pause() {
        mediaPlayer?.pause()
        mutableIsPlay.value = false
    }

    private fun releasePlayer() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}
