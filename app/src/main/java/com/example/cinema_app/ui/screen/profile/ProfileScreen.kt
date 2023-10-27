package com.example.cinema_app.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.cinema_app.presentation.ProfileViewModel
import com.example.cinema_app.ui.state.ProfileContent
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Black300
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.InternBoldLarge
import com.example.cinema_app.ui.theme.SecondaryAccentStyle
import com.example.cinema_app.ui.theme.SecondarySemiBoldStyle


@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel) {
    val focusManager = LocalFocusManager.current
    val userState: ProfileContent = profileViewModel.profileState.value
    val credentialsState by profileViewModel.credentialsState.collectAsStateWithLifecycle()
    var reloadImage by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        profileViewModel.getUserProfile()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Box(
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
                .background(Color.Red)
        ) {
            AsyncImage(
                model = userState.userAvatar,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = userState.login,
            style = InternBoldLarge,
            modifier = Modifier.padding(top = 6.dp, bottom = 20.dp),
        )
        ProfileSection(
            focusManager = focusManager,
            userState = userState,
            profileViewModel = profileViewModel
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                profileViewModel.updateUserProfile()
            },
            shape = RoundedCornerShape(size = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Accent
            ),
            contentPadding = PaddingValues(12.dp)
        ) {
            Text(
                text = "Cохранить",
                style = SecondarySemiBoldStyle
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                reloadImage = true
            },

            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = 10.dp),
            contentPadding = PaddingValues(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Black300
            )
        ) {
            Text(
                text = "Назад",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Accent,
                style = SecondaryAccentStyle,
                textAlign = TextAlign.Center
            )
        }

    }

}
