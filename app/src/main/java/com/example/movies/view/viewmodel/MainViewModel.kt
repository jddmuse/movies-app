package com.example.movies.view.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.model.MovieModel
import com.example.movies.data.model.MoviesList
import com.example.movies.data.model.baseUrlImg
import com.example.movies.domain.GetMovieByIdUseCase
import com.example.movies.domain.GetMoviesListByIdUseCase
import com.example.movies.domain.GetMoviesUseCase
import com.example.movies.domain.model.Movie
import com.example.movies.view.fragment.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = ":::MainViewModel -> "

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMoviesListByIdUseCase: GetMoviesListByIdUseCase
    //private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel() {

    val moviesListLiveData = MutableLiveData<MutableList<List<Movie>>>()

    init {
        onCreate()
    }

    private fun onCreate() {

    }

    override fun onCleared() {
        super.onCleared()
        Log.e(TAG, "Info cleaned")
    }

    //esta función es llamada desde los fragments
    fun getMoviesList(context: Context, idCollection: Long, callback: (List<Movie>) -> Unit) {

        val items = moviesListLiveData.value
        if (!items.isNullOrEmpty()) { //se hace esta validación con el fin de evitar que se hagan peticiones innecesarias a la API
            if (items.size > 3) {
                when (idCollection) {
                    BEST_PICTURES_NOMINATIONS -> callback(items[0])
                    GROSSING_FILMS_OF_ALL_TIME -> callback(items[1])
                    MARVEL_UNIVERSE_MOVIES -> callback(items[2])
                    FILMS_DEJA_VUS -> callback(items[3])
                }
            }else {
                callback(items.shuffled().random().shuffled())
            }

        }else { //se envia una peticion a la API o Localmente
            viewModelScope.launch {
                val result: List<Movie>? = getMoviesListByIdUseCase(idCollection, context)
                Log.d(TAG, "getMoviesListByIdUseCase")
                if (result != null) {
                    updateData(result)
                    callback(result)
                }
            }
        }
    }

    fun getRecommendedMoviesList(context: Context) {
        viewModelScope.launch {
            val result: List<Movie>? = getMoviesListByIdUseCase(STREAM_AW, context)
            Log.d(TAG, "getMoviesUseCase")
            if (result != null) {
                updateData(result)
            }
        }
    }

    //se actualizan los datos de nuestro LiveData
    private fun updateData(item: List<Movie>) {
        val currentList = moviesListLiveData.value ?: arrayListOf()
        currentList.add(item)
        moviesListLiveData.postValue(currentList)
    }
}