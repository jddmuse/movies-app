package com.example.movies.view.viewmodel

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
import com.example.movies.view.fragment.BEST_PICTURES_NOMINATIONS
import com.example.movies.view.fragment.MARVEL_UNIVERSE_MOVIES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = ":::MainViewModel -> "

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMoviesListByIdUseCase: GetMoviesListByIdUseCase
    //private val getMovieByIdUseCase: GetMovieByIdUseCase
):ViewModel() {

    val moviesLiveData = MutableLiveData<List<MovieModel>>()
    val moviesListLiveData = MutableLiveData<ArrayList<MoviesList>>()

    init {
        onCreate()
    }

    private fun onCreate(){

        viewModelScope.launch {
            val result: MoviesList? = getMoviesUseCase()
            Log.d(TAG, "getMoviesUseCase")
            if(result!=null){
                Log.d(TAG, "MoviesList is not null")
                moviesLiveData.postValue(result.items!!)
            }
        }

        getMoviesList()

    }

    override fun onCleared() {
        super.onCleared()
        Log.e(TAG, "Info cleaned")
    }

    fun getMoviesList(){
        viewModelScope.launch {
            val result: MoviesList? = getMoviesListByIdUseCase(BEST_PICTURES_NOMINATIONS)
            Log.d(TAG, "getMoviesListByIdUseCase")
            if(result!=null){
                val moviesListArray = moviesListLiveData.value ?: arrayListOf()
                moviesListArray.add(result)
                Log.d(TAG, "BEST_PICTURES_NOMINATIONS is not null")
                moviesListLiveData.postValue(moviesListArray)
            }
        }
    }
}