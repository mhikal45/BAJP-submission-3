package com.catalog.moviecatalogapp.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.catalog.moviecatalogapp.data.source.local.entity.TvShowEntity
import com.catalog.moviecatalogapp.data.source.CatalogDataRepository
import com.catalog.moviecatalogapp.vo.Resource

class TvShowViewModel  (private val catalogDataRepository: CatalogDataRepository): ViewModel() {

    fun getShow () : LiveData<Resource<PagedList<TvShowEntity>>> = catalogDataRepository.getTvShow()
}