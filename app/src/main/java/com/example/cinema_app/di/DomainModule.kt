package com.example.cinema_app.di

import com.example.cinema_app.data.converter.DateConverter
import com.example.cinema_app.presentation.validator.ConfirmPasswordValidator
import com.example.cinema_app.presentation.validator.DateValidator
import com.example.cinema_app.presentation.validator.EmailValidator
import com.example.cinema_app.presentation.validator.LoginCredentialsValidator
import com.example.cinema_app.presentation.validator.LoginValidator
import com.example.cinema_app.presentation.validator.PasswordValidator
import com.example.cinema_app.presentation.validator.RegistrationCredentialsValidator
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
    fun provideEmailValidator(): EmailValidator {
        return EmailValidator()
    }

    @Provides
    fun provideDateValidator(): DateValidator {
        return DateValidator()
    }

    @Provides
    fun provideConfirmPasswordValidator(): ConfirmPasswordValidator {
        return ConfirmPasswordValidator()
    }

    @Provides
    fun provideLoginValidator(): LoginValidator {
        return LoginValidator()
    }

     @Provides
    fun providePasswordValidator(): PasswordValidator {
        return PasswordValidator()
    }

    @Provides
    fun provideRegistrationCredentialsValidator(
        emailValidator: EmailValidator,
        loginValidator: LoginValidator,
        dateValidator: DateValidator,
    ): RegistrationCredentialsValidator {
        return RegistrationCredentialsValidator(emailValidator, loginValidator, dateValidator)
    }

    @Provides
    fun provideLoginCredentialsValidator(
        loginValidator: LoginValidator,
        passwordValidator: PasswordValidator,
    ): LoginCredentialsValidator {
        return LoginCredentialsValidator(loginValidator, passwordValidator)
    }

}