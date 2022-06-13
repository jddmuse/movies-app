package com.example.movies.data

import com.example.movies.data.dao.MovieDAO
import com.example.movies.data.model.MovieModel
import com.example.movies.data.model.MoviesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(
    private val api: MovieApiClient,
    //private val movieDAO: MovieDAO
) {

    suspend fun getAllMoviesFromAPI(): MoviesList? {
        return withContext(Dispatchers.IO) {
            val response = api.getAllMovies()
            response.body()
        }
    }

    suspend fun getMovieByIdFromAPI(id: Long): MovieModel? {
        return withContext(Dispatchers.IO) {
            val response = api.getMovieById(id)
            response.body() ?: MovieModel()
        }
    }

    suspend fun getMoviesListFromAPI(id: Long): MoviesList {
        return withContext(Dispatchers.IO) {
            val response = api.getMoviesList(id)
            response.body() ?: MoviesList()
        }
    }

    suspend fun getMoviesListFromLocal(){

    }


}