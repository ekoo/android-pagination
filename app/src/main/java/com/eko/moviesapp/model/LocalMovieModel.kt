package com.eko.moviesapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = LocalMovieModel.TABLE_NAME)
data class LocalMovieModel(

    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Long?,

    @ColumnInfo(name = TITLE)
    val title: String?,

    @ColumnInfo(name = RELEASE_DATE)
    val releaseDate: String?,

    @ColumnInfo(name = POSTER_PATH)
    val posterPath: String?,

    @ColumnInfo(name = OVERVIEW)
    val overview: String?
): Parcelable {
    companion object{
        const val TABLE_NAME = "saved_movie"
        const val ID = "id"
        const val TITLE = "title"
        const val RELEASE_DATE = "release_date"
        const val POSTER_PATH = "poster_path"
        const val OVERVIEW = "overview"
    }
}