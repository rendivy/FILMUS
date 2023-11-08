package com.example.cinema_app.di

import com.example.cinema_app.data.mappers.FilmMapper
import com.example.cinema_app.pagination.MoviePagingSource
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.repository.ProfileRepositoryImpl
import com.example.cinema_app.domain.usecase.GetAverageFilmRatingsUseCase
import com.example.cinema_app.domain.usecase.GetUserIdUseCase
import com.example.cinema_app.domain.usecase.GetUserProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGetAverageFilmRatingsUseCase(): GetAverageFilmRatingsUseCase {
        return GetAverageFilmRatingsUseCase()
    }

    @Provides
    fun provideFilmMapper(getAverageFilmRatingsUseCase: GetAverageFilmRatingsUseCase): FilmMapper {
        return FilmMapper(getAverageFilmRatingsUseCase = getAverageFilmRatingsUseCase)
    }


    @Provides
    fun provideUserIdUseCase(profileRepositoryImpl: ProfileRepositoryImpl): GetUserIdUseCase {
        return GetUserIdUseCase(profileRepositoryImpl = profileRepositoryImpl)
    }


    @Provides
    @Singleton
    fun provideMoviePagingSource(
        movieApiService: MovieApiService,
        filmMapper: FilmMapper,
        getUserProfileUseCase: GetUserProfileUseCase,
        userIdUseCase: GetUserIdUseCase
    ): MoviePagingSource {
        return MoviePagingSource(movieApiService, filmMapper, userIdUseCase, getUserProfileUseCase )
    }
}
