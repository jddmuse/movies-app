package com.example.movies.data.model

import java.io.Serializable

const val baseUrlImg = "http://image.tmdb.org/t/p/w500/"

class MovieModel(
    val id:Int? = null,
    val adult:Boolean = false,
    val backdrop_path:String = "",
    //val belongs_to_collection:Object? = null,
    val budget:Int? = null,
    val original_title:String = "",
    val overview:String ="",
    val title:String ="",
    var genres:MutableList<GenerModel> = mutableListOf<GenerModel>(),
    val original_language:String = "",
    val popularity:Float? = null,
    val poster_path:String ="",
    val production_companies:MutableList<ProductionCompanyModel> = mutableListOf<ProductionCompanyModel>(),
    val release_date:String = "",
    val tagline:String = "",
    val video:Boolean = false,
    val vote_average:Float? = null
):Serializable {

}