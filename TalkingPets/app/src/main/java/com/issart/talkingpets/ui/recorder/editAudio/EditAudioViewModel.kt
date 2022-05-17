package com.issart.talkingpets.ui.recorder.editAudio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditAudioViewModel @Inject constructor() : ViewModel() {

    private val mutableSpeed = MutableLiveData<Float>()
    var speed: LiveData<Float> = mutableSpeed

    private val mutablePitch = MutableLiveData<Float>()
    var pitch: LiveData<Float> = mutablePitch

    fun setSpeed(newSpeed: Float) {
        mutableSpeed.value = newSpeed
    }

    fun setPitch(newPitch: Float) {
        mutablePitch.value = newPitch
    }

}
