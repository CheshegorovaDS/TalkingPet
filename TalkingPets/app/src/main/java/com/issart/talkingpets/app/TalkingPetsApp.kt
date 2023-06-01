package com.issart.talkingpets.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TalkingPetsApp : Application() {
    init {
        System.loadLibrary("opencv_java4")
    }
}
