package com.example.movies.data.network

import com.example.movies.data.dao.MovieDAO
import com.example.movies.data.entity.MovieEntity
import com.example.movies.data.model.MovieModel
import com.example.movies.data.model.MoviesList
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(
    private val api: MovieApiClient,
    private val movieDAO: MovieDAO
) {

    suspend fun getAllMoviesFromAPI(): List<Movie> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllMovies()
            val body = response.body() ?: MoviesList()
            val item = body.items

            item.map { it.toDomain() }
        }
    }

    suspend fun getMovieByIdFromAPI(id: Long): Movie {
        return withContext(Dispatchers.IO) {
            val response = api.getMovieById(id)
            val body = response.body() ?: MovieModel()
            val item = body

            item.toDomain()
        }
    }

    suspend fun getMoviesListFromAPI(id: Long): List<Movie> {
        return withContext(Dispatchers.IO) {
            val response = api.getMoviesList(id)
            val body = response.body() ?: MoviesList()
            val item = body.items

            item.map { it.toDomain() }
        }
    }

    suspend fun getMoviesListFromLocal(): List<Movie> {
        val items:List<MovieEntity> = movieDAO.getAllMovies()
        return items.map { it.toDomain() } ?: emptyList()
    }

    suspend fun insertAllMovies(items:List<MovieEntity>){
        movieDAO.insertAll(items)
    }


}