package com.eko.moviesapp.repository.local

import androidx.paging.DataSource
import androidx.room.*
import com.eko.moviesapp.model.LocalTvShowModel

@Dao
interface TvShowDao {
    @Query("SELECT * FROM saved_tv_show")
    fun getAllData(): DataSource.Factory<Int, LocalTvShowModel>

    @Query("SELECT * FROM saved_tv_show WHERE id =:id")
    suspend fun getById(id: String): LocalTvShowModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(data: LocalTvShowModel): Long

    @Delete
    suspend fun deleteData(data: LocalTvShowModel): Int
}