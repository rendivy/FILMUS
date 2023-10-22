package com.example.cinema_app.di

import com.example.cinema_app.data.converter.DateConverter
import com.example.cinema_app.presentation.validator.AllCredentialsValidator
import com.example.cinema_app.presentation.validator.ConfirmPasswordValidator
import com.example.cinema_app.presentation.validator.DateValidator
import com.example.cinema_app.presentation.validator.EmailValidator
import com.example.cinema_app.presentation.validator.LoginValidator
import com.example.cinema_app.presentation.validator.PasswordValidator
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
    fun provideNavigationValidator(
        emailValidator: EmailValidator,
        dateValidator: DateValidator,
        loginValidator: LoginValidator
    ): AllCredentialsValidator {
        return AllCredentialsValidator(
            emailValidator = emailValidator,
            dateValidator = dateValidator,
            loginValidator = loginValidator
        )
    }

    @Provides
    fun providePasswordValidator(): PasswordValidator {
        return PasswordValidator()
    }

}