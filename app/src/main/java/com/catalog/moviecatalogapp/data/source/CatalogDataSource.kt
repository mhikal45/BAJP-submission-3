package com.catalog.moviecatalogapp.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.catalog.moviecatalogapp.data.source.local.entity.MovieEntity
import com.catalog.moviecatalogapp.data.source.local.entity.TvShowEntity
import com.catalog.moviecatalogapp.vo.Resource

interface CatalogDataSource {
    fun getMovies () : LiveData<Resource<PagedList<MovieEntity>>>
    fun getDetailMovie (movieId: Int) : LiveData<Resource<MovieEntity>>
    fun getTvShow () : LiveData<Resource<PagedList<TvShowEntity>>>
    fun getDetailTvShow (showId: Int) : LiveData<Resource<TvShowEntity>>
    fun getFavoriteMovies () : LiveData<PagedList<MovieEntity>>
    fun getFavoriteTvShow () : LiveData<PagedList<TvShowEntity>>
    fun setMovieFavorite(movie: MovieEntity, state: Boolean)
    fun setTvShowFavorite(movie: TvShowEntity, state: Boolean)
}