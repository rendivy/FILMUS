package com.example.cinema_app.presentation.mappers

import com.example.cinema_app.data.entity.Film
import com.example.cinema_app.domain.entity.FavouriteDTO

class PresentationFilmMapper {

    fun map(filmData: Film, userReview: Int?): FavouriteDTO {
        return FavouriteDTO(
            id = filmData.id,
            name = filmData.name,
            poster = filmData.poster,
            userReview = userReview
        )
    }
}