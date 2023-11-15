package com.example.cinema_app.data.pagination


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cinema_app.data.database.MovieDataBase
import com.example.cinema_app.data.dbentity.UserRating
import com.example.cinema_app.data.mappers.FilmMapper
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.domain.entity.FilmDTO
import com.example.cinema_app.domain.usecase.GetUserProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviePagingSource @Inject constructor(
    private val movieApiService: MovieApiService,
    private val filmMapper: FilmMapper,
    private val movieDataBase: MovieDataBase,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : PagingSource<Int, FilmDTO>() {


    override fun getRefreshKey(state: PagingState<Int, FilmDTO>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmDTO> {
        return try {
            withContext(Dispatchers.IO) {
                val movies: MutableList<FilmDTO> = mutableListOf()
                val page = params.key ?: 1
                val response = movieApiService.getMovies(page)
                response.movies.forEach {
                    val filmDetails = movieApiService.getMovieDetails(it.id)
                    if (movieDataBase.userDao().getUserRating(it.id) == null) {
                        val userRatings = getUserProfileUseCase.getUserReview(filmDetails.reviews)
                        val userRating = UserRating(
                            filmId = it.id,
                            userRating = userRatings?.rating
                        )
                        movieDataBase.movieDao().insertMovie(
                            filmMapper.mapToCached(it, userRatings?.rating)
                        )
                        movieDataBase.userDao().insertUserRating(userRating)
                        movies += filmMapper.map(it, userRatings?.rating)
                    }
                    else {
                        val userRatings = movieDataBase.userDao().getUserRating(it.id)
                        movies += filmMapper.map(it, userRatings?.userRating)
                    }


                }
                val prevKey = if (page > 0) page - 1 else null
                val nextKey = if (response.movies.isNotEmpty()) page + 1 else null

                LoadResult.Page(
                    data = movies,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}