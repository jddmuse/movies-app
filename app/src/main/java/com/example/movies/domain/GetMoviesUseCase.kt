package com.example.movies.domain

import com.example.movies.data.MovieService
import com.example.movies.data.model.MoviesList
import com.example.movies.domain.model.Movie
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieService: MovieService){
    suspend operator fun invoke(): List<Movie>? = movieService.getAllMoviesFromAPI()
}