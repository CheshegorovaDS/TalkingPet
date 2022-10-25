package com.issart.talkingpets.ui.recorder.audioList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AudioListViewModel @Inject constructor() : ViewModel() {

    private val mutableIsAudioListVisible = MutableLiveData(false)
    val isAudioListVisible: LiveData<Boolean> = mutableIsAudioListVisible

    private val mutableCheckedAudioId = MutableLiveData<Int?>(null)
    val checkedAudio: LiveData<Int?> = mutableCheckedAudioId

    fun setAudioListVisibility(isVisible: Boolean) {
        mutableIsAudioListVisible.value = isVisible
    }

    fun setCheckedAudio(id: Int, isChecked: Boolean = false) {
        mutableCheckedAudioId.value = if (isChecked) {
            null
        } else {
            id
        }
    }

}
