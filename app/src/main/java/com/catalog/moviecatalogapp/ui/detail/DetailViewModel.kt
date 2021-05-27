package com.catalog.moviecatalogapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.catalog.moviecatalogapp.data.source.CatalogDataRepository
import com.catalog.moviecatalogapp.data.source.local.entity.MovieEntity
import com.catalog.moviecatalogapp.data.source.local.entity.TvShowEntity
import com.catalog.moviecatalogapp.vo.Resource

class DetailViewModel (private val catalogDataRepository: CatalogDataRepository) : ViewModel() {

    companion object {
        const val MOVIE ="movie"
        const val TV_SHOW = "tvshow"
    }

    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>
    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>

    fun setFilmDetail (filmId : Int, category : String) {
        when (category) {
            MOVIE -> {
                detailMovie = catalogDataRepository.getDetailMovie(filmId)
            }
            TV_SHOW -> {
                detailTvShow = catalogDataRepository.getDetailTvShow(filmId)
            }
        }
    }

    fun setFavoriteMovie() {
        val resource = detailMovie.value
        if (resource?.data != null) {
            val newState = !resource.data.favorite
            catalogDataRepository.setMovieFavorite(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTvShow.value
        if (resource?.data != null) {
            val newState = !resource.data.favorite
            catalogDataRepository.setTvShowFavorite(resource.data, newState)
        }
    }

    fun getDetailTvShow() = detailTvShow
    fun getDetailMovie() = detailMovie

}