package com.issart.talkingpets.ui.share

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.talkingpets.data.GetAnimationEyesPhotosUseCase
import com.issart.talkingpets.ui.common.toast.showToast
import com.issart.talkingpets.ui.detector.detectorPoints.model.face.FacePoints
import com.issart.talkingpets.ui.model.Eye
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val getAnimationEyesPhotosUseCase: GetAnimationEyesPhotosUseCase
) : ViewModel() {

    private var mutableVideo = MutableLiveData("")
    val video: LiveData<String> = mutableVideo

    private var mutablePhoto = MutableLiveData("")
    val photo: LiveData<String> = mutableVideo

    private var mutableCadr = MutableLiveData<Bitmap?>(null)
    val cadr: LiveData<Bitmap?> = mutableCadr

    fun createVideo(
        bitmap: LiveData<Bitmap?>,
        context: Context,
        leftEye: Eye?,
        topFace: FacePoints?,
        bottomFace: FacePoints?,
        leftFace: FacePoints?,
        rightFace: FacePoints?,
        density: Float,
        px: Float
    ) {
        showToast(context, "create video")
        val error = when {
            bitmap.value == null -> "bitmap is null"
            leftEye == null -> "eye is null"
//            topFace == null -> "top face is null"
//            bottomFace == null -> "bottom face is null"
            else -> null
        }
        if (error != null) {
            showToast(context, error)
            return
        }

        viewModelScope.launch {
            mutableCadr.value = getAnimationEyesPhotosUseCase.getPhotos(
                bitmap.value!!,
                leftEye!!,
//                topFace!!,
//                bottomFace!!,
//                leftFace!!,
//                rightFace!!,
                density,
                px
            ).first()
        }
    }

}
