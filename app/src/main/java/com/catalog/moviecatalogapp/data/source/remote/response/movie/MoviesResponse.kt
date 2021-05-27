package com.catalog.moviecatalogapp.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

	@field:SerializedName("items")
	val items : List<Movie>
)