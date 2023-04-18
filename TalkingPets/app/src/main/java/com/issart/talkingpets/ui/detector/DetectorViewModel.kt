package com.issart.talkingpets.ui.detector

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.issart.talkingpets.ui.model.Eye
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetectorViewModel @Inject constructor() : ViewModel() {

    private var mutableLeftEye = MutableLiveData<Eye>()
    val leftEye: LiveData<Eye> = mutableLeftEye

    private var mutableRightEye = MutableLiveData<Eye>()
    val rightEye: LiveData<Eye> = mutableRightEye

    private var mutableHasEars = MutableLiveData(false)
    val hasEars: LiveData<Boolean> = mutableHasEars

    fun setLeftEyePosition(x: Float, y: Float) {
        mutableLeftEye.value = Eye(
            x = x,
            y = y,
            zoom = leftEye.value?.zoom ?: 1f
        )
    }

    fun setLeftEyeZoom(zoom: Float) {
        mutableLeftEye.value = Eye(
            x = leftEye.value?.x ?: 0f,//default position for left eye
            y = leftEye.value?.y ?: 0f,
            zoom = zoom
        )
    }

    fun setRightEye(x: Float, y: Float) {
        mutableRightEye.value = mutableRightEye.value?.copy(
            x = x,
            y = y
        )
    }

}
