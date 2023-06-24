package com.issart.talkingpets.ui.editor

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.issart.talkingpets.services.files.FileService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(
    private val fileService: FileService
): ViewModel() {

    private var mutableBitmap = MutableLiveData<Bitmap?>(null)
    val bitmap: LiveData<Bitmap?> = mutableBitmap

    private var mutableAngle = MutableLiveData(0f)
    val angle: LiveData<Float> = mutableAngle

    private var mutableEditedBitmap = MutableLiveData<Bitmap?>(bitmap.value)
    val editedBitmap: LiveData<Bitmap?> = mutableEditedBitmap

    fun setEditorBitmap(newBitmap: Bitmap) {
        val resizedBitmap = Bitmap.createScaledBitmap(newBitmap, VIDEO_SIZE, VIDEO_SIZE, false)
        mutableBitmap.value = resizedBitmap
        mutableEditedBitmap.value = resizedBitmap
        clear()
    }

    fun setEditorAngle(newAngle: Float) {
        mutableAngle.value = newAngle
        mutableEditedBitmap.value = when {
            bitmap.value != null -> getRotatedAndCroppedBitmap(bitmap.value!!, newAngle)
            else -> null
        }
    }

    fun getUriForImageCrop(): Uri? {
        val bitmap: Bitmap? = mutableBitmap.value
        return when {
            bitmap != null -> fileService.getUrlForBitmap(bitmap)
            else -> null
        }
    }

    fun onCropResult(bitmapUri: Uri?) {
        bitmapUri?.let { notNullUri ->
            val croppedImage = fileService.loadImageFromFile(notNullUri)
            mutableBitmap.value = croppedImage

            val currentAngle: Float? = mutableAngle.value
            currentAngle?.let {  notNullAngle ->  setEditorAngle(notNullAngle) }
        }
    }

    private fun clear() {
        mutableAngle.value = 0f
    }

    companion object {
        const val VIDEO_SIZE = 1080
    }
}
