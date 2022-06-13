package com.example.movies.domain

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.movies.data.MovieService
import com.example.movies.data.dao.MovieDAO
import com.example.movies.data.entity.toDatabase
import com.example.movies.domain.model.Movie
import com.example.movies.util.CheckNetwork
import javax.inject.Inject

class GetMoviesListByIdUseCase @Inject constructor(
    private val movieService: MovieService
) {

    @RequiresApi(Build.VERSION_CODES.M)
    suspend operator fun invoke(id: Long, context: Context): List<Movie>? =
        if (CheckNetwork.isOnline(context)) {
            var items = movieService.getMoviesListFromAPI(id)
            if (items.isNotEmpty())
                movieService.insertAllMovies(items.map { it.toDatabase() })
            items
        } else {
            movieService.getMoviesListFromLocal()
        }


}