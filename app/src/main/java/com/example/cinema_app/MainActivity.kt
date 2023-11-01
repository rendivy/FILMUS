package com.example.cinema_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.cinema_app.ui.navigation.CinemaNavHost
import com.example.cinema_app.ui.theme.CinemaappTheme
import com.example.cinema_app.ui.theme.Gray900
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = Gray900) {
                CinemaappTheme {
                    CinemaNavHost()
                }
            }
        }
    }
}


