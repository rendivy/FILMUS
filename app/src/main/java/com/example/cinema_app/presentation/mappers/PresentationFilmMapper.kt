package com.example.cinema_app.presentation.mappers

import com.example.cinema_app.data.entity.FilmShort
import com.example.cinema_app.domain.entity.FavouriteDTO

class PresentationFilmMapper {

    fun map(filmShortData: FilmShort, userReview: Int?): FavouriteDTO {
        return FavouriteDTO(
            id = filmShortData.id,
            name = filmShortData.name,
            poster = filmShortData.poster,
            userReview = userReview
        )
    }
}