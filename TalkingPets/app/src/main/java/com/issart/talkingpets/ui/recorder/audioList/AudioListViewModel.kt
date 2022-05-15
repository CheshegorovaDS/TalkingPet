package com.issart.talkingpets.ui.recorder.audioList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AudioListViewModel @Inject constructor() : ViewModel() {

    private val mutableCheckedAudioId = MutableLiveData<Int>()
    val checkedAudio: LiveData<Int> = mutableCheckedAudioId

    fun setCheckedAudio(audioId: Int?) {
        mutableCheckedAudioId.value = audioId
    }

}
