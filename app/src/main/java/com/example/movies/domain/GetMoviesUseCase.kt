package com.example.movies.domain

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.movies.data.MovieService
import com.example.movies.data.model.MoviesList
import com.example.movies.domain.model.Movie
import com.example.movies.util.CheckNetwork
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieService: MovieService){

    @RequiresApi(Build.VERSION_CODES.M)
    suspend operator fun invoke(context: Context): List<Movie>? {
        var items: List<Movie>? = emptyList<Movie>()
        if(CheckNetwork.isOnline(context)){
            items = movieService.getAllMoviesFromAPI() ?: emptyList()
        }
        return items
    }
}