package com.issart.talkingpets.ui.gallery

import androidx.lifecycle.LiveData

data class GalleryState(
    val photoUri: LiveData<String>
)
