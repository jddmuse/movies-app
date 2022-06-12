package com.example.movies.data

import com.example.movies.data.model.MovieModel
import com.example.movies.data.model.MoviesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(
    private val api: MovieApiClient,
    private val moviesProvider: MoviesProvider
) {

    suspend fun getAllMovies(): MoviesList? {
        return withContext(Dispatchers.IO) {
            val response = api.getAllMovies()
            response.body()
        }
    }

    suspend fun getMovieById(id: Long): MovieModel? {
        return withContext(Dispatchers.IO) {
            val response = api.getMovieById(id)
            response.body() ?: MovieModel()
        }
    }

    suspend fun getMoviesList(id: Long): MoviesList {
        return withContext(Dispatchers.IO) {
            val response = api.getMoviesList(id)
            if(response.body()!=null) moviesProvider.moviesListArray.add(response.body()!!)
            response.body() ?: MoviesList()
        }
    }


}