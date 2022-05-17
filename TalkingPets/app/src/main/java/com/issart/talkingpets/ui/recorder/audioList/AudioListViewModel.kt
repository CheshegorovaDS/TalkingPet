package com.issart.talkingpets.ui.recorder.audioList

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AudioListViewModel @Inject constructor() : ViewModel() {

    private val mutableCheckedAudioId = MutableLiveData<Int>()
    val checkedAudio: LiveData<Int> = mutableCheckedAudioId

    private var mediaPlayer: MediaPlayer? = null
    private val mutableIsPlay = MutableLiveData(false)
    val isPlay: LiveData<Boolean> = mutableIsPlay
    private val mutablePlayedAudioId = MutableLiveData<Int>()
    val playedAudio: LiveData<Int> = mutablePlayedAudioId

    fun setCheckedAudio(audioId: Int?) {
        mutableCheckedAudioId.value = audioId
    }

    fun setPlayedAudio(audioId: Int?) {
        mutablePlayedAudioId.value = audioId
    }

    fun setIsPlay(isPlay: Boolean) {
        mutableIsPlay.value = isPlay
    }

    fun create(context: Context) {
        if (mediaPlayer == null) {
            mediaPlayer = checkedAudio.value?.let { MediaPlayer.create(context, it) }
            mediaPlayer?.prepare()
        }
        mutableIsPlay.value = false
    }


    fun play() {
        try {
            mediaPlayer?.start()
            mutableIsPlay.value = true
        } catch (e: IOException) {
            releasePlayer()
        }
    }

    fun pause() {
        mediaPlayer?.pause()
        mutableIsPlay.value = false
    }

    fun releasePlayer() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}
