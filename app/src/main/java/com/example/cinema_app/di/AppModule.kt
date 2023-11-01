package com.example.cinema_app.di

import com.example.cinema_app.data.mediator.MoviePagingSource
import com.example.cinema_app.data.remote.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviePagingSource(movieApiService: MovieApiService): MoviePagingSource {
        return MoviePagingSource(movieApiService)
    }
}
