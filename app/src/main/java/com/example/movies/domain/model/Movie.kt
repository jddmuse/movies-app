package com.example.movies.domain.model

import com.example.movies.data.entity.MovieEntity
import com.example.movies.data.model.GenerModel
import com.example.movies.data.model.MovieModel
import com.example.movies.data.model.ProductionCompanyModel

data class Movie(
    val id: Int?,
    val adult: Boolean,
    val backdrop_path: String,
    val budget: Int?,
    val original_title: String,
    val overview: String,
    val title: String,
    //var genres:MutableList<GenerModel> = mutableListOf<GenerModel>(),
    val original_language: String,
    val popularity: Float?,
    val poster_path: String,
    //val production_companies:MutableList<ProductionCompanyModel> = mutableListOf<ProductionCompanyModel>(),
    val release_date: String,
    val tagline: String,
    val video: Boolean,
    val vote_average: Float?
)

fun MovieModel.toDomain() = Movie(
    id,
    adult,
    backdrop_path,
    budget,
    original_title,
    overview,
    title,
    original_language,
    popularity,
    poster_path,
    release_date,
    tagline,
    video,
    vote_average
)

fun MovieEntity.toDomain() = Movie(
    id,
    adult,
    backdrop_path,
    budget,
    original_title,
    overview,
    title,
    original_language,
    popularity,
    poster_path,
    release_date,
    tagline,
    video,
    vote_average
)