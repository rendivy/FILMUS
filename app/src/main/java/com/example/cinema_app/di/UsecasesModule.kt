package com.example.cinema_app.di

import com.example.feature_filmus_movie_api.repository.AuthRepository
import com.example.feature_filmus_movie_api.repository.FavouriteMovieRepository
import com.example.feature_filmus_movie_api.repository.MoviesRepository
import com.example.feature_filmus_movie_api.repository.ProfileRepository
import com.example.feature_filmus_movie_api.usecase.AddFavouriteMovieUseCase
import com.example.feature_filmus_movie_api.usecase.AddUserReviewUseCase
import com.example.feature_filmus_movie_api.usecase.DeleteFavouriteMovieUseCase
import com.example.feature_filmus_movie_api.usecase.DeleteUserReviewUseCase
import com.example.feature_filmus_movie_api.usecase.EditUserReviewUseCase
import com.example.feature_filmus_movie_api.usecase.GetAllCachedFilmsUseCase
import com.example.feature_filmus_movie_api.usecase.auth.LoginUserUseCase
import com.example.feature_filmus_movie_api.usecase.auth.LogoutUserUseCase
import com.example.feature_filmus_movie_api.usecase.auth.RegisterUserUseCase
import com.example.feature_filmus_movie_api.usecase.profile.GetUserProfileUseCase
import com.example.feature_filmus_movie_api.usecase.profile.UpdateUserProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author y.yangel
 */
@Module
@InstallIn(SingletonComponent::class)
object UsecasesModule {

    @Provides
    fun provideLoginUserUseCase(authRepository: AuthRepository): LoginUserUseCase {
        return LoginUserUseCase(repository = authRepository)
    }

    @Provides
    fun provideLogoutUserUseCase(profileRepository: ProfileRepository): LogoutUserUseCase {
        return LogoutUserUseCase(profileRepository = profileRepository)
    }

    @Provides
    fun provideRegisterUserUseCase(authRepository: AuthRepository): RegisterUserUseCase {
        return RegisterUserUseCase(repository = authRepository)
    }

    @Provides
    fun provideGetUserProfileUseCase(profileRepository: ProfileRepository): GetUserProfileUseCase {
        return GetUserProfileUseCase(profileRepository = profileRepository)
    }

    @Provides
    fun provideUpdateUserProfileUseCase(profileRepository: ProfileRepository): UpdateUserProfileUseCase {
        return UpdateUserProfileUseCase(repository = profileRepository)
    }

    @Provides
    fun provideAddFavouriteMovieUseCase(favouriteMovieRepository: FavouriteMovieRepository): AddFavouriteMovieUseCase {
        return AddFavouriteMovieUseCase(favouriteMovieRepository = favouriteMovieRepository)
    }

    @Provides
    fun provideAddUserReviewUseCase(moviesRepository: MoviesRepository): AddUserReviewUseCase {
        return AddUserReviewUseCase(moviesRepository = moviesRepository)
    }

    @Provides
    fun provideDeleteFavouriteMovieUseCase(favouriteMovieRepository: FavouriteMovieRepository): DeleteFavouriteMovieUseCase {
        return DeleteFavouriteMovieUseCase(favouriteMovieRepository = favouriteMovieRepository)
    }

    @Provides
    fun provideDeleteUserReviewUseCase(moviesRepository: MoviesRepository): DeleteUserReviewUseCase {
        return DeleteUserReviewUseCase(moviesRepository = moviesRepository)
    }

    @Provides
    fun provideEditUserReviewUseCase(moviesRepository: MoviesRepository): EditUserReviewUseCase {
        return EditUserReviewUseCase(movieDetailsRepository = moviesRepository)
    }

    @Provides
    fun provideGetAllCachedFilmsUseCase(moviesRepository: MoviesRepository): GetAllCachedFilmsUseCase {
        return GetAllCachedFilmsUseCase(moviesRepository = moviesRepository)
    }
}