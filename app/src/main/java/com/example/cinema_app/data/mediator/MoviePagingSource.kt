package com.example.cinema_app.data.mediator


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cinema_app.data.mappers.FilmMapper
import com.example.cinema_app.data.remote.MovieApiService
import com.example.cinema_app.domain.entity.FilmDto
import com.example.cinema_app.domain.usecase.GetUserIdUseCase
import com.example.cinema_app.domain.usecase.GetUserProfileUseCase
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviePagingSource @Inject constructor(
    private val movieApiService: MovieApiService,
    private val filmMapper: FilmMapper,
    private val userIdUseCase: GetUserIdUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : PagingSource<Int, FilmDto>() {


    override fun getRefreshKey(state: PagingState<Int, FilmDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmDto> {
        return try {

            val movies: MutableList<FilmDto> = mutableListOf()
            val page = params.key ?: 1
            val response = movieApiService.getMovies(page)
            response.movies.forEach {
                val filmDetails = movieApiService.getMovieDetails(it.id)
                val userRatings = getUserProfileUseCase.getUserRating(filmDetails.reviews)
                movies += filmMapper.map(it, userRatings)
            }

            val prevKey = if (page > 0) page - 1 else null
            val nextKey = if (response.movies.isNotEmpty()) page + 1 else null

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