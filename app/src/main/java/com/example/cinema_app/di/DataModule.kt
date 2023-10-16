package com.example.cinema_app.di

import com.example.cinema_app.data.remote.LoggingInterceptor
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.domain.common.Constants
import com.example.cinema_app.domain.common.Constants.BASE_URL
import com.example.cinema_app.domain.repository.AuthRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.math.log


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private val gson: Gson = GsonBuilder().create()


    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = okHttpClient

    @Provides
    @Singleton
    fun provideService(okHttpClient: OkHttpClient): MovieApiService = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build().create(MovieApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(apiService: MovieApiService): AuthRepository {
        return AuthRepository(apiService)
    }

}