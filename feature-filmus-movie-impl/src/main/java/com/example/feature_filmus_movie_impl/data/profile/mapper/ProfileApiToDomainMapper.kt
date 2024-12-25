package com.example.feature_filmus_movie_impl.data.profile.mapper

import com.example.feature_filmus_movie_api.entities.profile.Profile
import com.example.feature_filmus_movie_impl.data.profile.entities.ProfileApi

/**
 * @author y.yangel
 */
fun ProfileApi.toDomain(): Profile {
    return Profile(
        id = id,
        avatarLink = avatarLink,
        birthDate = birthDate,
        email = email,
        gender = gender,
        name = name,
        nickName = nickName,
    )
}