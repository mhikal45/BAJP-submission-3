package com.catalog.moviecatalogapp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.catalog.moviecatalogapp.data.source.CatalogDataRepository
import com.catalog.moviecatalogapp.data.source.local.entity.MovieEntity
import com.catalog.moviecatalogapp.vo.Resource

class MovieViewModel (private val catalogDataRepository: CatalogDataRepository): ViewModel() {

    fun getMovie () : LiveData<Resource<PagedList<MovieEntity>>> = catalogDataRepository.getMovies()

}