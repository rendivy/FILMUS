package com.example.cinema_app.data

import android.content.Context
import com.example.cinema_app.data.NetworkConstant.BASE_URL
import com.example.cinema_app.data.mappers.AverageFilmRatingsProvider
import com.example.cinema_app.data.mappers.MovieShortToMovieWithRatingsMapper
import com.example.cinema_app.data.movie.mappers.MovieShortToDbMapper
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.repository.AuthRepositoryImpl
import com.example.cinema_app.data.repository.FavouriteMovieRepositoryImpl
import com.example.cinema_app.data.movie.repository.MoviesRepositoryImpl
import com.example.cinema_app.data.pagination.MoviePagingRepository
import com.example.cinema_app.data.profile.repository.ProfileRepositoryImpl
import com.example.cinema_app.data.storage.TokenLocalStorage
import com.example.cinema_app.database.dao.MovieDao
import com.example.cinema_app.database.dao.RatingDao
import com.example.cinema_app.domain.repository.AuthRepository
import com.example.cinema_app.domain.repository.FavouriteMovieRepository
import com.example.cinema_app.domain.repository.MoviesRepository
import com.example.cinema_app.domain.repository.ProfileRepository
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

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
    fun provideFavouriteRepository(
        tokenLocalStorage: TokenLocalStorage,
        movieApiService: MovieApiService
    ): FavouriteMovieRepository {
        return FavouriteMovieRepositoryImpl(
            tokenLocalStorage = tokenLocalStorage,
            movieApiService = movieApiService
        )
    }

    @Provides
    fun provideMovieRepository(
        tokenLocalStorage: TokenLocalStorage,
        movieApiService: MovieApiService,
        ratingsDao: RatingDao,
        movieDao: MovieDao,
    ): MoviesRepository {
        return MoviesRepositoryImpl(
            tokenLocalStorage = tokenLocalStorage,
            moviesApiService = movieApiService,
            ratingsDao = ratingsDao,
            movieDao = movieDao,
        )
    }

    @Provides
    fun provideProfileRepository(
        tokenLocalStorage: TokenLocalStorage,
        movieApiService: MovieApiService,
        ratingsDao: RatingDao,
    ): ProfileRepository {
        return ProfileRepositoryImpl(
            tokenLocalStorage = tokenLocalStorage,
            movieApiService = movieApiService,
            ratingDao = ratingsDao,
        )
    }

    @Provides
    fun provideAuthRepository(
        tokenLocalStorage: TokenLocalStorage,
        movieApiService: MovieApiService,
    ): AuthRepository {
        return AuthRepositoryImpl(
            tokenLocalStorage = tokenLocalStorage,
            movieApiService = movieApiService,
        )
    }

    @Provides
    @Singleton
    fun provideService(okHttpClient: OkHttpClient): MovieApiService = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build().create(MovieApiService::class.java)


    @Provides
    @Singleton
    fun provideMoviePagingRepository(
        movieApiService: MovieApiService,
        profileRepository: ProfileRepository,
        ratingsDao: RatingDao,
        movieDao: MovieDao,
    ): MoviePagingRepository {
        return MoviePagingRepository(
            movieApiService = movieApiService,
            profileRepository = profileRepository,
            movieShortToMovieWithRatingsMapper = MovieShortToMovieWithRatingsMapper(),
            ratingsDao = ratingsDao,
            movieDao = movieDao,
            averageFilmRatingsProvider = AverageFilmRatingsProvider(),
            movieShortToDbMapper = MovieShortToDbMapper(),
            )
    }

}