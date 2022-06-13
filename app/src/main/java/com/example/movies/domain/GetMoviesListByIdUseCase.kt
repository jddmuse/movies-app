package com.example.movies.domain

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.movies.data.network.MovieService
import com.example.movies.data.entity.toDatabase
import com.example.movies.domain.model.Movie
import com.example.movies.util.CheckNetwork
import javax.inject.Inject

class GetMoviesListByIdUseCase @Inject constructor(
    private val movieService: MovieService //se inyecta el servicio
) {

    @RequiresApi(Build.VERSION_CODES.M)
    suspend operator fun invoke(id: Long, context: Context): List<Movie>? =
        if (CheckNetwork.isOnline(context)) { //mediante esta validación nos aseguramos de si el movil tiene conexion a Internet
            var items = movieService.getMoviesListFromAPI(id)
            if (items.isNotEmpty()) {
                movieService.clearAllMovies()
                movieService.insertAllMovies(items.map { it.toDatabase() }) //se hace mapping a los items
            }
            items
        } else { //si no tiene conexion a internet, el servicio consultará los datos alamcenados localmente
            movieService.getMoviesListFromLocal().shuffled()
        }


}