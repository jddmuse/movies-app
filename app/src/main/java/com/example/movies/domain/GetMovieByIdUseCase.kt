package com.example.movies.domain

import com.example.movies.data.MovieService
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(private val movieService: MovieService) {
    suspend operator fun invoke(id:Long) = movieService.getMovieById(id)
}