package com.example.movies.data

import com.example.movies.data.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiClient {

    @GET()
    suspend fun getAllMovies(): Response<List<MovieModel>>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") id:Long): Response<MovieModel?>
}