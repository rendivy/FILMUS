package com.example.cinema_app.ui.screen.profile

import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.cinema_app.R
import com.example.cinema_app.common.Constants
import com.example.cinema_app.common.ErrorConstant
import com.example.cinema_app.presentation.ProfileViewModel
import com.example.cinema_app.presentation.state.ProfileState
import com.example.cinema_app.ui.component.NoRippleInteractionSource
import com.example.cinema_app.ui.navigation.NavigationRoutes
import com.example.cinema_app.ui.screen.errorUiScreen.ErrorUiScreen
import com.example.cinema_app.ui.shimmer.shimmerEffect
import com.example.cinema_app.ui.theme.Accent
import com.example.cinema_app.ui.theme.Black300
import com.example.cinema_app.ui.theme.Gray900
import com.example.cinema_app.ui.theme.InternBoldLarge
import com.example.cinema_app.ui.theme.SecondaryAccentStyle
import com.example.cinema_app.ui.theme.SecondarySemiBoldStyle
import com.example.cinema_app.ui.theme.padding10
import com.example.cinema_app.ui.theme.padding100
import com.example.cinema_app.ui.theme.semiMediumPadding
import com.example.cinema_app.ui.theme.mediumPadding
import com.example.cinema_app.ui.theme.padding54


@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    navHostController: NavController,
    navController: NavController
) {
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect()
                )
            }
        }

        is ProfileState.Content -> {
            val profileState = remember { profileViewModel.profileState }
            val content = (credentialsState as ProfileState.Content).profileCredentials

            var enabled = (profileState.value == content || !(profileState.value.nameError == null && profileState.value.emailError == null))

            val buttonAlpha = if (enabled) {
                0.45f
            }
            else {
                1f
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray900)
                    .verticalScroll(rememberScrollState())
                    .padding(mediumPadding)
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
                                            RoundedCornerShape(padding100)
                                        )
                                        .background(Color.White),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painterResource(id = R.drawable.profile_icon),
                                        contentDescription = null,
                                        modifier = Modifier.size(padding54)
                                    )
                                }
                            }

                            else -> {
                                SubcomposeAsyncImageContent()
                            }
                        }


                    }

                }
                Column(
                    modifier = Modifier
                        .padding(top = 6.dp, bottom = 20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {
                    Text(
                        text = profileState.value.login,
                        style = InternBoldLarge,
                        modifier = Modifier,
                    )
                    TextButton(
                        onClick = {
                            profileViewModel.logoutUser()
                            navHostController.popBackStack()
                            navHostController.navigate(NavigationRoutes.Greetings.route)
                        },
                        interactionSource = NoRippleInteractionSource(),
                        modifier = Modifier
                            .padding(bottom = semiMediumPadding)
                            .fillMaxWidth(),
                    )
                    {
                        Text(
                            text = "Выйти из аккаунта",
                            color = Accent,
                            style = SecondaryAccentStyle,
                        )
                    }
                }
                ProfileSection(
                    focusManager = focusManager,
                    userState = profileState.value,
                    profileViewModel = profileViewModel
                )
                Spacer(modifier = Modifier.height(mediumPadding))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(buttonAlpha),
                    enabled = !enabled,
                    onClick = {
                        profileViewModel.updateUserProfile {
                            if (profileState.value.emailError == null && profileState.value.nameError == null) {
                                Toast.makeText(
                                    context,
                                    Constants.PROFILE_UPDATED,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    },
                    shape = RoundedCornerShape(size = padding10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Accent,
                        disabledContainerColor = Accent
                    ),
                    contentPadding = PaddingValues(semiMediumPadding)
                ) {
                    Text(
                        text = "Cохранить",
                        style = SecondarySemiBoldStyle
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Button(
                    onClick = {
                        navController.popBackStack()
                    },

                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(size = padding10),
                    contentPadding = PaddingValues(semiMediumPadding),
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

        is ProfileState.Error -> {
            val error = credentialsState as ProfileState.Error
            when (error.errorMessage) {
                ErrorConstant.UNAUTHORIZED -> {
                    navHostController.popBackStack()
                    navHostController.navigate(NavigationRoutes.Login.route)
                    Toast.makeText(
                        context, stringResource(id = R.string.confirm),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else ->
                    ErrorUiScreen {
                        profileViewModel.retry()
                    }
            }
        }

        is ProfileState.Success -> {
            Toast.makeText(
                context, stringResource(id = R.string.profileSuccess),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
