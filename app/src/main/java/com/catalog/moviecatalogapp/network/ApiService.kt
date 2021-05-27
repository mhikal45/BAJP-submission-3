package com.catalog.moviecatalogapp.network

import com.catalog.moviecatalogapp.data.source.remote.response.movie.MoviesDetailResponse
import com.catalog.moviecatalogapp.data.source.remote.response.movie.MoviesResponse
import com.catalog.moviecatalogapp.data.source.remote.response.tvshow.DetailTvShowResponse
import com.catalog.moviecatalogapp.data.source.remote.response.tvshow.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("list/7094888")
    fun getMovies (
        @Query("api_key") apiKey : String
    ) : Call<MoviesResponse>

    @GET("movie/{id}")
    fun getDetailMovie (
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ) : Call<MoviesDetailResponse>

    @GET("list/7094889")
    fun getTvShows (
        @Query("api_key") apiKey: String
    ) : Call<TvShowsResponse>

    @GET("tv/{id}")
    fun getDetailTvShow (
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ) : Call<DetailTvShowResponse>
}