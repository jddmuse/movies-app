package com.example.movies.domain

import com.example.movies.data.MovieService
import com.example.movies.domain.model.Movie
import javax.inject.Inject

class GetMoviesListByIdUseCase @Inject constructor(private val movieService: MovieService) {
    suspend operator fun invoke(id:Long): List<Movie>? = movieService.getMoviesListFromAPI(id)
}