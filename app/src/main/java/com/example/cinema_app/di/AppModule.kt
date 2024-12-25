package com.example.cinema_app.di

import com.example.cinema_app.presentation.converter.DateConverter
import com.example.cinema_app.presentation.facade.ValidateLoginFacade
import com.example.cinema_app.presentation.facade.ValidateRegistrationFacade
import com.example.cinema_app.presentation.mappers.PresentationFilmMapper
import com.example.cinema_app.presentation.mappers.UserReviewMapper
import com.example.cinema_app.presentation.usecases.ValidateConfirmPasswordUseCase
import com.example.cinema_app.presentation.usecases.ValidateDateUseCase
import com.example.cinema_app.presentation.usecases.ValidateEmailUseCase
import com.example.cinema_app.presentation.usecases.ValidateLoginUseCase
import com.example.cinema_app.presentation.usecases.ValidatePasswordUseCase
import com.example.feature_filmus_movie_api.repository.AuthRepository
import com.example.feature_filmus_movie_api.repository.ProfileRepository
import com.example.feature_filmus_movie_api.usecase.auth.IsUserLoggedUseCase
import com.example.feature_filmus_movie_api.usecase.profile.GetUserIdUseCase
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

    @Provides
    fun provideDateConverter(): DateConverter {
        return DateConverter()
    }

    @Provides
    fun provideEmailValidator(): ValidateEmailUseCase {
        return ValidateEmailUseCase()
    }

    @Provides
    fun provideDateValidator(): ValidateDateUseCase {
        return ValidateDateUseCase()
    }

    @Provides
    fun provideConfirmPasswordValidator(): ValidateConfirmPasswordUseCase {
        return ValidateConfirmPasswordUseCase()
    }

    @Provides
    fun provideLoginValidator(): ValidateLoginUseCase {
        return ValidateLoginUseCase()
    }

    @Provides
    fun providePasswordValidator(): ValidatePasswordUseCase {
        return ValidatePasswordUseCase()
    }

    @Provides
    fun provideRegistrationCredentialsValidator(
        emailValidator: ValidateEmailUseCase,
        validateLoginUseCase: ValidateLoginUseCase,
        dateValidator: ValidateDateUseCase,
    ): ValidateRegistrationFacade {
        return ValidateRegistrationFacade(
            emailValidatorUseCase = emailValidator,
            validateLoginUseCase = validateLoginUseCase,
            dateValidatorUseCase = dateValidator,
        )
    }

    @Provides
    fun provideIsUserLoggedUseCase(authRepository: AuthRepository): IsUserLoggedUseCase {
        return IsUserLoggedUseCase(repository = authRepository)
    }

    @Provides
    fun provideValidateLoginFacade(
        validateLoginUseCase: ValidateLoginUseCase,
        passwordValidator: ValidatePasswordUseCase,
    ): ValidateLoginFacade {
        return ValidateLoginFacade(
            validateLoginUseCase = validateLoginUseCase,
            passwordValidator = passwordValidator,
        )
    }

}
