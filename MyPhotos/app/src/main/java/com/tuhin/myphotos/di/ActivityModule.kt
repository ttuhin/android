package com.tuhin.myphotos.di

import android.content.Context
import com.tuhin.myphotos.presentation.adapter.PhotoDetailsAdapter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    @ActivityScoped
    fun providePhotoDetailsAdapter(@ActivityContext context: Context): PhotoDetailsAdapter {
        return PhotoDetailsAdapter(context, ArrayList())
    }
}