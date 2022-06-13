package com.example.movies.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movies.data.model.GenerModel
import com.example.movies.data.model.ProductionCompanyModel
import com.example.movies.domain.model.Movie

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id") val id: Int?,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String,
    @ColumnInfo(name = "budget") val budget: Int?,
    @ColumnInfo(name = "original_title") val original_title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "original_language") val original_language: String,
    @ColumnInfo(name = "popularity") val popularity: Float?,
    @ColumnInfo(name = "poster_path") val poster_path: String,
    @ColumnInfo(name = "release_date") val release_date: String,
    @ColumnInfo(name = "tagline") val tagline: String,
    @ColumnInfo(name = "video") val video: Boolean,
    @ColumnInfo(name = "vote_average") val vote_average: Float?
)

fun Movie.toDatabase() = MovieEntity(
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