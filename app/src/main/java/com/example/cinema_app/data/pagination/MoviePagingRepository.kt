package com.example.cinema_app.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cinema_app.data.mappers.AverageFilmRatingsProvider
import com.example.cinema_app.data.mappers.MovieShortToMovieWithRatingsMapper
import com.example.cinema_app.data.movie.mappers.MovieShortToDbMapper
import com.example.cinema_app.data.movie.mappers.toDomain
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.data.review.entities.ReviewApi
import com.example.cinema_app.database.dao.MovieDao
import com.example.cinema_app.database.dao.RatingDao
import com.example.cinema_app.database.entities.RatingsDb
import com.example.cinema_app.domain.entities.movie.MovieWithRatings
import com.example.cinema_app.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePagingRepository @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val movieApiService: MovieApiService,
    private val movieShortToMovieWithRatingsMapper: MovieShortToMovieWithRatingsMapper,
    private val movieDao: MovieDao,
    private val ratingsDao: RatingDao,
    private val movieShortToDbMapper: MovieShortToDbMapper,
    private val averageFilmRatingsProvider: AverageFilmRatingsProvider,
) : PagingSource<Int, MovieWithRatings>() {

    override fun getRefreshKey(state: PagingState<Int, MovieWithRatings>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieWithRatings> {
        return withContext(Dispatchers.IO) {
            try {
                val movies: MutableList<MovieWithRatings> = mutableListOf()
                val page = params.key ?: 1
                val moviesList = movieApiService.getMovies(page).movies.map { movie ->
                    movie.toDomain()
                }
                val userId = profileRepository.getProfileData().id
                moviesList.forEach {
                    val filmRatings = averageFilmRatingsProvider(it.reviewShortList)
                    val filmDetails = movieApiService.getMovieDetails(it.id)
                    if (ratingsDao.getUserRating(it.id) == null) {
                        val userRatings = getUserReview(
                            userId = userId,
                            filmReviewApis = filmDetails.reviewApis
                        )
                        val userRatingsDb = RatingsDb(
                            filmId = it.id,
                            userRating = userRatings
                        )
                        movieDao.insertMovie(
                            movieShortToDbMapper(
                                it,
                                userRatings,
                                filmRating = null
                            )
                        )
                        ratingsDao.insertUserRating(userRatingsDb)
                        movies += movieShortToMovieWithRatingsMapper(
                            it,
                            userRatings,
                            filmRating = filmRatings
                        )
                    } else {
                        val userRatings = ratingsDao.getUserRating(it.id)
                        movies += movieShortToMovieWithRatingsMapper(
                            it,
                            userRatings?.userRating,
                            filmRating = filmRatings
                        )
                    }
                }
                val prevKey = if (page > 0) page - 1 else null
                val nextKey = if (moviesList.isNotEmpty()) page + 1 else null

                LoadResult.Page(
                    data = movies,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } catch (e: IOException) {
                LoadResult.Error(e)
            } catch (e: HttpException) {
                LoadResult.Error(e)
            }
        }
    }


    private fun getUserReview(userId: String, filmReviewApis: List<ReviewApi>): Int? {
        return filmReviewApis.find { it.author?.userId == userId }?.rating
    }
}