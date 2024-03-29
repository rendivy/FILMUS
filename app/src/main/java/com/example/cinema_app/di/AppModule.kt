package com.example.cinema_app.di

import com.example.cinema_app.data.database.MovieDataBase
import com.example.cinema_app.data.mappers.FilmMapper
import com.example.cinema_app.data.pagination.MoviePagingSource
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.domain.repository.ProfileRepository
import com.example.cinema_app.domain.usecase.GetAverageFilmRatingsUseCase
import com.example.cinema_app.domain.usecase.GetUserIdUseCase
import com.example.cinema_app.domain.usecase.GetUserProfileUseCase
import com.example.cinema_app.presentation.mappers.PresentationFilmMapper
import com.example.cinema_app.presentation.mappers.UserReviewMapper
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
    fun providePresentationFilmMapper(): PresentationFilmMapper {
        return PresentationFilmMapper()
    }

    @Provides
    fun provideFilmMapper(getAverageFilmRatingsUseCase: GetAverageFilmRatingsUseCase): FilmMapper {
        return FilmMapper(getAverageFilmRatingsUseCase = getAverageFilmRatingsUseCase)
    }


    @Provides
    fun provideUserIdUseCase(profileRepository: ProfileRepository): GetUserIdUseCase {
        return GetUserIdUseCase(profileRepository = profileRepository)
    }

    @Provides
    fun provideReviewMapper(): UserReviewMapper {
        return UserReviewMapper()
    }


    @Provides
    @Singleton
    fun provideMoviePagingSource(
        movieApiService: MovieApiService,
        movieDataBase: MovieDataBase,
        filmMapper: FilmMapper,
        getUserProfileUseCase: GetUserProfileUseCase,
    ): MoviePagingSource {
        return MoviePagingSource(
            movieApiService,
            filmMapper,
            movieDataBase = movieDataBase,
            getUserProfileUseCase
        )
    }
}
