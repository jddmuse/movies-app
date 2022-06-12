package com.example.movies.data

import com.example.movies.data.model.MovieModel
import com.example.movies.data.model.MoviesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiClient {

    @GET("3/list/1")
    suspend fun getAllMovies(): Response<MoviesList>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") id:Long): Response<MovieModel?>

    @GET("3/list/{movie_id}")
    suspend fun getMoviesList(@Path("movie_id") id:Long) : Response<MoviesList?>
}