package com.example.cinema_app.di

import com.example.cinema_app.data.converter.DateConverter
import com.example.cinema_app.domain.usecase.ValidateCofirmPasswordUseCase
import com.example.cinema_app.domain.usecase.ValidateDateUseCase
import com.example.cinema_app.domain.usecase.ValidateEmailUseCase
import com.example.cinema_app.domain.usecase.ValidateLoginCredentialsUseCase
import com.example.cinema_app.domain.usecase.ValidateLoginUseCase
import com.example.cinema_app.domain.usecase.ValidatePasswordUseCase
import com.example.cinema_app.domain.usecase.ValidateRegistrationCredentialsUseCase
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
    fun provideConfirmPasswordValidator(): ValidateCofirmPasswordUseCase {
        return ValidateCofirmPasswordUseCase()
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
    ): ValidateRegistrationCredentialsUseCase {
        return ValidateRegistrationCredentialsUseCase(emailValidator, validateLoginUseCase, dateValidator)
    }

    @Provides
    fun provideLoginCredentialsValidator(
        validateLoginUseCase: ValidateLoginUseCase,
        passwordValidator: ValidatePasswordUseCase,
    ): ValidateLoginCredentialsUseCase {
        return ValidateLoginCredentialsUseCase(validateLoginUseCase, passwordValidator)
    }

}