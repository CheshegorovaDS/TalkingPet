package com.issart.talkingpets.ui.share.video

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object VideoPlayerModule {

    @Provides
    fun provideVideoPlayer(
        @ApplicationContext
        context: Context
    ): Player = ExoPlayer
        .Builder(context)
        .build()

}
