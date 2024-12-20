package com.example.cinema_app.di

import com.example.cinema_app.domain.repository.AuthRepository
import com.example.cinema_app.domain.usecase.auth.IsUserLoggedUseCase
import com.example.cinema_app.presentation.converter.DateConverter
import com.example.cinema_app.presentation.facade.ValidateLoginFacade
import com.example.cinema_app.presentation.facade.ValidateRegistrationFacade
import com.example.cinema_app.presentation.usecases.ValidateConfirmPasswordUseCase
import com.example.cinema_app.presentation.usecases.ValidateDateUseCase
import com.example.cinema_app.presentation.usecases.ValidateEmailUseCase
import com.example.cinema_app.presentation.usecases.ValidateLoginUseCase
import com.example.cinema_app.presentation.usecases.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

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
