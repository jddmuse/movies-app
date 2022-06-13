package com.example.movies.domain

import com.example.movies.data.MovieService
import com.example.movies.data.model.MoviesList
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieService: MovieService){
    suspend operator fun invoke(): MoviesList? = movieService.getAllMoviesFromAPI()
}