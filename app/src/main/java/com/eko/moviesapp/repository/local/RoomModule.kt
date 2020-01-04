package com.eko.moviesapp.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eko.moviesapp.model.LocalMovieModel
import com.eko.moviesapp.model.LocalTvShowModel

@Database(
    entities = [LocalMovieModel::class, LocalTvShowModel::class],
    version = 1,
    exportSchema = false)

abstract class RoomModule : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao

    companion object {
        private var INSTANCE: RoomModule? = null

        fun getInstance(context: Context): RoomModule? {

            if (INSTANCE == null) {
                synchronized(RoomModule::class) {
                    INSTANCE = Room
                        .databaseBuilder(
                            context.applicationContext,
                            RoomModule::class.java,
                            "favorite_data.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}