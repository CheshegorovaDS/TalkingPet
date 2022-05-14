package com.issart.talkingpets.ui.recorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AudioListViewModel @Inject constructor() : ViewModel() {

    private val mutableIsAudioListVisible = MutableLiveData(false)
    val isAudioListVisible: LiveData<Boolean> = mutableIsAudioListVisible

    fun setAudioListVisibility(isVisible: Boolean) {
        mutableIsAudioListVisible.value = isVisible
    }

}
