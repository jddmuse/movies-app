package com.example.movies.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movies.data.model.GenerModel
import com.example.movies.data.model.ProductionCompanyModel

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
    //@ColumnInfo(name = "genres") var genres:MutableList<GenerModel> = mutableListOf<GenerModel>(),
    @ColumnInfo(name = "original_language") val original_language: String,
    @ColumnInfo(name = "popularity") val popularity: Float?,
    @ColumnInfo(name = "poster_path") val poster_path: String,
    //@ColumnInfo(name = "production_companies") val production_companies: MutableList<ProductionCompanyModel> = mutableListOf<ProductionCompanyModel>(),
    @ColumnInfo(name = "release_date") val release_date: String,
    @ColumnInfo(name = "tagline") val tagline: String,
    @ColumnInfo(name = "video") val video: Boolean,
    @ColumnInfo(name = "vote_average") val vote_average: Float?
)