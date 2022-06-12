package com.example.movies.data

import com.example.movies.data.model.MoviesList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesProvider @Inject constructor() {

    var moviesListArray = arrayListOf<MoviesList>()
}