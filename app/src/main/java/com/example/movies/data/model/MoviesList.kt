package com.example.movies.data.model

import java.io.Serializable

class MoviesList(
    val id:Int? = null,
    val created_by:String = "",
    val description:String = "",
    val favorite_count:Int = 0,
    var items:MutableList<MovieModel> = mutableListOf<MovieModel>()
):Serializable {
}