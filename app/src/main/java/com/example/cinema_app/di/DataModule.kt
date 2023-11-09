package com.example.cinema_app.di

import android.content.Context
import com.example.cinema_app.common.NetworkConstant
import com.example.cinema_app.common.NetworkConstant.BASE_URL
import com.example.cinema_app.data.mappers.ReviewMapper
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.domain.usecase.ReviewMapperUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private val gson: Gson = GsonBuilder().create()

    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(NetworkConstant.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(NetworkConstant.WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(NetworkConstant.READ_TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideReviewMapperUseCase(reviewMapper: ReviewMapper): ReviewMapperUseCase {
        return ReviewMapperUseCase(reviewMapper)
    }

    @Provides
    fun provideReviewMapper() = ReviewMapper()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = okHttpClient


    @Provides
    fun provideTokenLocalStorage(@ApplicationContext context: Context) =
        TokenLocalStorage(context)


    @Provides
    @Singleton
    fun provideService(okHttpClient: OkHttpClient): MovieApiService = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build().create(MovieApiService::class.java)

}