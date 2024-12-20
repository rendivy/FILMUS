package com.example.cinema_app.data.profile.mapper

import com.example.cinema_app.data.profile.entities.ProfileApi
import com.example.cinema_app.domain.entities.profile.Profile

/**
 * @author y.yangel
 */
fun Profile.toApi(): ProfileApi {
    return ProfileApi(
        id = id,
        avatarLink = avatarLink,
        birthDate = birthDate,
        email = email,
        gender = gender,
        name = name,
        nickName = nickName,
    )
}