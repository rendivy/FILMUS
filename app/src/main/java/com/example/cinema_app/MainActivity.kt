package com.example.cinema_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.cinema_app.presentation.UserAuthViewModel
import com.example.cinema_app.ui.navigation.CinemaNavHost
import com.example.cinema_app.ui.theme.CinemaappTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: UserAuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaappTheme {
                CinemaNavHost(
                    userAuthViewModel = authViewModel
                )
            }
        }
    }
}

