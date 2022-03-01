package com.issart.talkingpets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.navigation.TalkingPetsScreen.*

class MainViewModel: ViewModel() {

    private var currentScreen = MutableLiveData(GALLERY)
    val screen: LiveData<TalkingPetsScreen> = currentScreen

    fun changeScreen(newScreen: TalkingPetsScreen) {
        currentScreen.value = newScreen
    }

}
