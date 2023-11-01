package com.example.cinema_app.data.mediator


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cinema_app.data.entity.Film
import com.example.cinema_app.data.remote.MovieApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviePagingSource @Inject constructor(private val movieApiService: MovieApiService) :
    PagingSource<Int, Film>() {


    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {

            val page = params.key ?: 1
            val response = movieApiService.getMovies(page + 1)


            val prevKey = if (page > 0) page - 1 else null
            val nextKey = if (response.movies.isNotEmpty()) page + 1 else null

            LoadResult.Page(
                data = response.movies,
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