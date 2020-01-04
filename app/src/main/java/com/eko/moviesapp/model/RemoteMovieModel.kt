package com.eko.moviesapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class RemoteMovieModel(
    @SerializedName("results")
    val results: List<Result>
) {
    @Parcelize
    data class Result(

        @SerializedName("id")
        val id: Long,

        @SerializedName("title")
        val title: String?,

        @SerializedName("release_date")
        val releaseDate: String?,

        @SerializedName("poster_path")
        val posterPath: String?,

        @SerializedName("overview")
        val overview: String?
    ): Parcelable
}