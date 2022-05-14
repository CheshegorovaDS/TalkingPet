package com.issart.talkingpets.ui.editor

import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.issart.talkingpets.ui.model.Point
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(): ViewModel() {

    private var mutableBitmap = MutableLiveData<Bitmap?>(null)
    val bitmap: LiveData<Bitmap?> = mutableBitmap

    private var mutableAngle = MutableLiveData(0f)
    val angle: LiveData<Float> = mutableAngle

    private var mutableLeftEye = MutableLiveData(Point(0, 0))
    val leftEye: LiveData<Point> = mutableLeftEye

    fun setEditorBitmap(newBitmap: Bitmap) {
        mutableBitmap.value = newBitmap
        clear()
    }

    fun setEditorAngle(newAngle: Float) {
        mutableAngle.value = newAngle
    }

    fun setLeftEye(x: Int, y: Int) {
        mutableLeftEye.value = Point(x, y)
    }

    private fun clear() {
        mutableAngle.value = 0f
    }

    val editedBitmap: Bitmap?
        get() {
            val matrix = Matrix()
            matrix.postRotate(angle.value ?: 0f)
            return when (val source = mutableBitmap.value) {
                null -> null
                else -> Bitmap.createBitmap(
                    source,
                    0,
                    0,
                    source.width, source.height,
                    matrix,
                    true
                )
            }
        }

}
