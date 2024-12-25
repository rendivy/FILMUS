package com.example.feature_filmus_movie_impl.repository

import android.os.Environment
import com.example.feature_filmus_movie_api.repository.AuthRepository
import com.example.feature_filmus_movie_impl.data.repository.AuthRepositoryImpl
import com.example.feature_filmus_movie_impl.data.storage.TokenLocalStorage
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

/**
 * @author y.yangel
 */
class AuthRepositoryTest {

    @Test
    fun `WHEN storage not empty THEN return true`() = runTest {
        with(createEnvironment()) {
            whenever(tokenLocalStorage.getToken()).thenReturn("token")

            assertEquals(authRepository.isUserLoggedIn(), true)
        }
    }

    @Test
    fun `WHEN storage empty THEN return false`() = runTest {
        with(createEnvironment()) {
            whenever(tokenLocalStorage.getToken()).thenReturn(" ")

            assertEquals(authRepository.isUserLoggedIn(), false)
        }
    }

    private fun createEnvironment(): Environment {
        val tokenLocalStorage = mock<TokenLocalStorage>()
        val authRepository = AuthRepositoryImpl(
            movieApiService = mock(),
            tokenLocalStorage = tokenLocalStorage
        )
        return Environment(
            authRepository = authRepository,
            tokenLocalStorage = tokenLocalStorage
        )
    }

    class Environment(
        val authRepository: AuthRepository,
        val tokenLocalStorage: TokenLocalStorage
    )
}