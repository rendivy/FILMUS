package com.example.cinema_app.di

import com.example.cinema_app.data.converter.DateConverter
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
    fun providePasswordValidator(): PasswordValidator{
        return PasswordValidator()
    }

}