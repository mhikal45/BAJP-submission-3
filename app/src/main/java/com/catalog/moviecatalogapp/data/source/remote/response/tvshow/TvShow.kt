package com.catalog.moviecatalogapp.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShow(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("vote_average")
    val voteAverage: Double? = null,
)
