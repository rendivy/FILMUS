package com.example.cinema_app.di

import android.content.Context
import com.example.cinema_app.common.NetworkConstant
import com.example.cinema_app.common.NetworkConstant.BASE_URL
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.repository.AuthRepositoryImpl
import com.example.cinema_app.data.repository.FavouriteMovieRepositoryImpl
import com.example.cinema_app.data.repository.ProfileRepositoryImpl
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.domain.repository.AuthRepository
import com.example.cinema_app.domain.repository.FavouriteMovieRepository
import com.example.cinema_app.domain.repository.ProfileRepository
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


    @Provides
    @Singleton
    fun provideFavoriteRepository(
        apiService: MovieApiService,
        localStorage: TokenLocalStorage
    ): FavouriteMovieRepository {
        return FavouriteMovieRepositoryImpl(localStorage, apiService)
    }


    @Provides
    @Singleton
    fun provideProfileRepository(
        localStorage: TokenLocalStorage,
        apiService: MovieApiService
    ): ProfileRepository {
        return ProfileRepositoryImpl(localStorage, apiService)
    }

    @Provides
    @Singleton
    fun provideRepository(
        apiService: MovieApiService,
        localStorage: TokenLocalStorage
    ): AuthRepository {
        return AuthRepositoryImpl(apiService, localStorage)
    }

}