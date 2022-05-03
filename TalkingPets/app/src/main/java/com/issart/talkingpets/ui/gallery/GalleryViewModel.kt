package com.issart.talkingpets.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(): ViewModel() {
    private var photoUri = MutableLiveData<String?>()
    val uri = photoUri

    fun setPhotoUri(uri: String?) {
        photoUri.value = uri
    }
}
