package com.catalog.moviecatalogapp.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.catalog.moviecatalogapp.data.source.CatalogDataRepository

class MovieViewModel (private val catalogDataRepository: CatalogDataRepository): ViewModel() {

    fun getMovie () = catalogDataRepository.getFavoriteMovies()

}