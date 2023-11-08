package com.example.cinema_app.ui.screen.profile

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.cinema_app.R
import com.example.cinema_app.presentation.ProfileViewModel
import com.example.cinema_app.presentation.state.ProfileState
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Black300
import com.example.cinema_app.ui.theme.Gray400
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.InternBoldLarge
import com.example.cinema_app.ui.theme.SecondaryAccentStyle
import com.example.cinema_app.ui.theme.SecondarySemiBoldStyle
import com.example.cinema_app.ui.theme.padding128


@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val credentialsState by profileViewModel.credentialsState.collectAsStateWithLifecycle()

    when (credentialsState) {
        is ProfileState.Initial -> profileViewModel.getUserProfile()
        is ProfileState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(visible = true) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(padding128),
                        color = Accent,
                        trackColor = Gray400
                    )
                }
            }
        }

        is ProfileState.Content -> {
            val profileState = remember { profileViewModel.profileState }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray900)
                    .verticalScroll(rememberScrollState())
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
                    SubcomposeAsyncImage(
                        model = profileState.value.userAvatar,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    ) {
                        when (painter.state) {
                            is AsyncImagePainter.State.Loading -> {
                                Box(
                                    modifier = Modifier
                                        .size(88.dp)
                                        .clip(CircleShape)
                                        .shimmerEffect()
                                )
                            }

                            is AsyncImagePainter.State.Error -> {
                                Box(
                                    modifier = Modifier
                                        .size(88.dp)
                                        .clip(
                                            RoundedCornerShape(100.dp)
                                        )
                                        .background(Color.White),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painterResource(id = R.drawable.profile_icon),
                                        contentDescription = null,
                                        modifier = Modifier.size(54.dp)
                                    )
                                }
                            }

                            else -> {
                                SubcomposeAsyncImageContent()
                            }
                        }


                    }

                }
                Text(
                    text = profileState.value.login,
                    style = InternBoldLarge,
                    modifier = Modifier.padding(top = 6.dp, bottom = 20.dp),
                )
                ProfileSection(
                    focusManager = focusManager,
                    userState = profileState.value,
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
                        profileViewModel.updateUserProfile()
                        Toast.makeText(
                            context, "Профиль успешно обновлен!",
                            Toast.LENGTH_SHORT
                        ).show()
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

        else -> {}
    }
}
