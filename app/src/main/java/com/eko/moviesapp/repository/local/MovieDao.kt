package com.eko.moviesapp.repository.local

import androidx.paging.DataSource
import androidx.room.*
import com.eko.moviesapp.model.LocalMovieModel

@Dao
interface MovieDao {
    @Query("SELECT * FROM saved_movie")
    fun getAll(): DataSource.Factory<Int, LocalMovieModel>

    @Query("SELECT * FROM saved_movie WHERE id =:id")
    suspend fun getItem(id: String): LocalMovieModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: LocalMovieModel): Long

    @Delete
    suspend fun delete(data: LocalMovieModel): Int
}