package com.example.cinema_app.data

import android.content.Context
import androidx.room.Room
import com.example.cinema_app.database.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDataBase {
        return Room.databaseBuilder(context, MovieDataBase::class.java, "movie_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(movieDataBase: MovieDataBase) = movieDataBase.movieDao()

    @Provides
    fun provideReviewDao(movieDataBase: MovieDataBase) = movieDataBase.ratingDao()

}