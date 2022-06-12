package com.example.movies.util

import com.example.movies.data.MoviesProvider

interface UIBehavior {
    fun initUI()

    interface RecyclerView{
        fun initRecyclerView()
    }

    interface MoviesList{
        fun initMoviesLists(moviesProvider: MoviesProvider)
    }
}