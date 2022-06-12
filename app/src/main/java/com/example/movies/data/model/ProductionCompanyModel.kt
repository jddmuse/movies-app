package com.example.movies.data.model

import java.io.Serializable

class ProductionCompanyModel(
    val id:Int?=null,
    val logo_path:String="",
    val name:String="",
    val origin_Country:String=""
):Serializable {
}
