package com.issart.talkingpets.ui.detector

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.issart.talkingpets.ui.detector.detectorPoints.model.face.FacePoints
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

    private var mutableTopFacePoint = MutableLiveData<FacePoints>()
    val topFacePoint: LiveData<FacePoints> = mutableTopFacePoint

    private var mutableBottomFacePoint = MutableLiveData<FacePoints>()
    val bottomFacePoint: LiveData<FacePoints> = mutableBottomFacePoint

    private var mutableLeftFacePoint = MutableLiveData<FacePoints>()
    val leftFacePoint: LiveData<FacePoints> = mutableLeftFacePoint

    private var mutableRightFacePoint = MutableLiveData<FacePoints>()
    val rightFacePoint: LiveData<FacePoints> = mutableRightFacePoint

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

    fun setRightEyePosition(x: Float, y: Float) {
        mutableRightEye.value = mutableRightEye.value?.copy(
            x = x,
            y = y,
            zoom = rightEye.value?.zoom ?: 1f
        )
    }

    fun setRightEyeZoom(zoom: Float) {
        mutableRightEye.value = Eye(
            x = rightEye.value?.x ?: 0f,//default position for left eye
            y = rightEye.value?.y ?: 0f,
            zoom = zoom
        )
    }

    fun setTopFacePosition(x: Float, y: Float) {
        mutableTopFacePoint.value = FacePoints(
            x = x,
            y = y
        )
    }

    fun setBottomFacePosition(x: Float, y: Float) {
        mutableBottomFacePoint.value = FacePoints(
            x = x,
            y = y
        )
    }

    fun setLeftFacePosition(x: Float, y: Float) {
        mutableLeftFacePoint.value = FacePoints(
            x = x,
            y = y
        )
    }

    fun setRightFacePosition(x: Float, y: Float) {
        mutableRightFacePoint.value = FacePoints(
            x = x,
            y = y
        )
    }

}
