package com.issart.talkingpets.di

import android.content.Context
import com.issart.talkingpets.services.files.FileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ImageEditModule {

    @Provides
    fun provideFileService(
        @ApplicationContext
        appContext: Context
    ) = FileService(context = appContext)
}