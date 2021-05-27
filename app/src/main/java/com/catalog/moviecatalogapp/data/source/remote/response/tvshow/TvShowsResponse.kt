package com.catalog.moviecatalogapp.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(

    @field:SerializedName("items")
	val items: List<TvShow>
)