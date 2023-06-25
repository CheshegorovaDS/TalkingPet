package com.issart.talkingpets.ui.share.video

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.issart.talkingpets.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    val player: Player
) : ViewModel() {

    private var mutableVideo = MutableLiveData<Uri?>()
    val video: LiveData<Uri?> = mutableVideo

    init {
        player.prepare()
    }

    fun addVideo(context: Context) {
        mutableVideo.value = Uri.parse( "android.resource://" + context.packageName + "/" + R.raw.singing_pet)
        player.addMediaItem(MediaItem.fromUri(mutableVideo.value!!))
    }

    override fun onCleared() {
        mutableVideo.value = null
        player.release()
        super.onCleared()
    }
}
