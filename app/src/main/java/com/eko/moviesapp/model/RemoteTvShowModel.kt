package com.eko.moviesapp.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class RemoteTvShowModel(
    @SerializedName("results")
    val results: List<Result>
) {
    @Parcelize
    data class Result(
        @SerializedName("id")
        val id: Long,

        @SerializedName("name")
        val name: String?,

        @SerializedName("first_air_date")
        val firstAirDate: String?,

        @SerializedName("poster_path")
        val posterPath: String?,

        @SerializedName("overview")
        val overview: String?
    ) : Parcelable
}