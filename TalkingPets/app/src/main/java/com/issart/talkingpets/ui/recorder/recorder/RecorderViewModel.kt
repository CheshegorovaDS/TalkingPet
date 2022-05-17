package com.issart.talkingpets.ui.recorder.recorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecorderViewModel @Inject constructor() : ViewModel() {

    private val mutableCheckedAudioId = MutableLiveData<Int?>(null)
    val checkedAudio: LiveData<Int?> = mutableCheckedAudioId

    fun setCheckedAudio(id: Int, isChecked: Boolean = false) {
        mutableCheckedAudioId.value = if (isChecked) {
            null
        } else {
            id
        }
    }

}
