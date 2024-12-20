package com.example.cinema_app.di

import com.example.cinema_app.domain.repository.ProfileRepository
import com.example.cinema_app.domain.usecase.profile.GetUserIdUseCase
import com.example.cinema_app.presentation.mappers.PresentationFilmMapper
import com.example.cinema_app.presentation.mappers.UserReviewMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providePresentationFilmMapper(): PresentationFilmMapper {
        return PresentationFilmMapper()
    }

    @Provides
    fun provideUserIdUseCase(profileRepository: ProfileRepository): GetUserIdUseCase {
        return GetUserIdUseCase(profileRepository = profileRepository)
    }

    @Provides
    fun provideReviewMapper(): UserReviewMapper {
        return UserReviewMapper()
    }
}
