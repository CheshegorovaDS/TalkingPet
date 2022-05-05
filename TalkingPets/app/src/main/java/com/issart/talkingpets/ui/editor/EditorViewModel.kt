package com.issart.talkingpets.ui.editor

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(): ViewModel() {

    private var mutableBitmap = MutableLiveData<Bitmap?>(null)
    val bitmap: LiveData<Bitmap?> = mutableBitmap

    fun setEditorBitmap(newBitmap: Bitmap) {
        mutableBitmap.value = newBitmap
    }

}
