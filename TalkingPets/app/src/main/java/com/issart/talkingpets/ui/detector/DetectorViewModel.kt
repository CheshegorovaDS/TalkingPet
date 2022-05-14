package com.issart.talkingpets.ui.detector

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.issart.talkingpets.ui.model.Point
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetectorViewModel @Inject constructor() : ViewModel() {

    private var mutableLeftEye = MutableLiveData<Point>()
    val leftEye: LiveData<Point> = mutableLeftEye

    private var mutableRightEye = MutableLiveData<Point>()
    val rightEye: LiveData<Point> = mutableRightEye

    private var mutableHasEars = MutableLiveData(false)
    val hasEars: LiveData<Boolean> = mutableHasEars

    fun setLeftEye(x: Float, y: Float) {
        mutableLeftEye.value = Point(x, y)
    }

    fun setRightEye(x: Float, y: Float) {
        mutableRightEye.value = Point(x, y)
    }
}
