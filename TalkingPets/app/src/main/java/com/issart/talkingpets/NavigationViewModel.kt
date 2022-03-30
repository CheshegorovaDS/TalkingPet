package com.issart.talkingpets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.issart.talkingpets.navigation.TalkingPetsScreen
import com.issart.talkingpets.navigation.TalkingPetsScreen.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {

    private var currentScreen = MutableLiveData(GALLERY)
    val screen: LiveData<TalkingPetsScreen> = currentScreen

    fun changeScreen(newScreen: TalkingPetsScreen) {
        currentScreen.value = newScreen
    }

}
