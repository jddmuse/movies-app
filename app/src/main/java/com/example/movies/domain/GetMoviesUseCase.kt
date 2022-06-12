package com.example.movies.domain

import com.example.movies.data.MovieService
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieService: MovieService){
    suspend operator fun invoke() = movieService.getAllMovies()
}