package com.example.movies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.data.entity.MovieEntity
import com.example.movies.data.model.MovieModel

@Dao
interface MovieDAO {

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items:List<MovieEntity>)
}