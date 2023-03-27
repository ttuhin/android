package com.tuhin.mymovies.di

import android.content.Context
import com.tuhin.mymovies.presentation.adapter.MovieAdapter
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
    fun provideMovieAdapter(@ActivityContext context: Context): MovieAdapter {
        return MovieAdapter(context, ArrayList())
    }
}