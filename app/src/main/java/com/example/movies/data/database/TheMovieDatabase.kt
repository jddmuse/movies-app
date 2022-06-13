package com.example.movies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.data.dao.MovieDAO
import com.example.movies.data.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class TheMovieDatabase:RoomDatabase() {

    abstract fun getMovieDAO():MovieDAO
}