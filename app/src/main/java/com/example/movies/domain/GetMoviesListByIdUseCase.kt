package com.example.movies.domain

import com.example.movies.data.MovieService
import javax.inject.Inject

class GetMoviesListByIdUseCase @Inject constructor(private val movieService: MovieService) {
    suspend operator fun invoke(id:Long) = movieService.getMoviesListFromAPI(id)
}