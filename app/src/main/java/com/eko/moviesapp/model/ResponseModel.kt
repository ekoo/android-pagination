package com.eko.moviesapp.model


import com.google.gson.annotations.SerializedName

data class ResponseModel(

    @SerializedName("results")
    val movieModels: List<MovieModel>
) {
    data class MovieModel(

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
    )
}