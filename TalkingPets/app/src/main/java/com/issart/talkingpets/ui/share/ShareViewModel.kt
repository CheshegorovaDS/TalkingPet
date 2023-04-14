package com.issart.talkingpets.ui.share

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.talkingpets.data.GetAnimationEyesPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    fun createVideo() {
        viewModelScope.launch(Dispatchers.IO){
            mutableVideo.value = getAnimationEyesPhotosUseCase.getPhotos()
        }
    }

}
