package com.example.feature_filmus_movie_impl.utils

import com.example.feature_filmus_movie_api.entities.ReviewShort
import com.example.feature_filmus_movie_impl.data.mappers.AverageFilmRatingsProvider
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * @author y.yangel
 */

internal class AverageFilmRatingsProviderTest {

    @Test
    fun `WHEN reviewShort is valid THEN happy path`() {
        with(createEnvironment()) {
            val result = averageFilmRatingsProvider(stubPositiveReviewData())

            assertEquals(result, 4.8)
        }
    }

    @Test
    fun `WHEN reviewShort is empty list THEN average rating is zero`() {
        with(createEnvironment()) {
            val result = averageFilmRatingsProvider(emptyList())

            assertEquals(result, 0.0.toDouble())
        }
    }

    @Test
    fun `WHEN reviewShort is valid and precision is 2 THEN rounded correctly`() {
        with(createEnvironment()) {
            val result = averageFilmRatingsProvider(data = stubPositiveReviewData(), precision = 2)

            assertEquals(result, 4.75)
        }
    }

    @Test
    fun `WHEN reviewShort ratings are negative THEN average rating is zero`() {
        with(createEnvironment()) {
            val result = averageFilmRatingsProvider(stubNegativeReviewData())
            assertEquals(result, 0.0)
        }
    }

    private fun stubPositiveReviewData(): List<ReviewShort> {
        return stubReviewDataList(listOf(3, 10, 2, 4))
    }

    private fun stubNegativeReviewData(): List<ReviewShort> {
        return stubReviewDataList(listOf(-1, -2, -3), startId = 1)
    }

    private fun stubReviewDataList(ratings: List<Int>, startId: Int = 1): List<ReviewShort> {
        return ratings.mapIndexed { index, rating ->
            ReviewShort(id = (startId + index).toString(), rating = rating)
        }
    }

    private fun createEnvironment(): Environment {
        val averageFilmRatingsProvider = AverageFilmRatingsProvider()
        return Environment(averageFilmRatingsProvider = averageFilmRatingsProvider)
    }

    private class Environment(
        val averageFilmRatingsProvider: AverageFilmRatingsProvider,
    )
}
