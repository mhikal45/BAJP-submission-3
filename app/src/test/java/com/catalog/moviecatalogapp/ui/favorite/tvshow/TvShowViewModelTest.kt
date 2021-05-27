package com.catalog.moviecatalogapp.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.catalog.moviecatalogapp.data.source.CatalogDataRepository
import com.catalog.moviecatalogapp.data.source.local.entity.TvShowEntity
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogDataRepository: CatalogDataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogDataRepository)
    }
    @Test
    fun getShow() {
        val dummyTvShow = pagedList
        `when`(dummyTvShow.size).thenReturn(2)
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShow

        `when`(catalogDataRepository.getFavoriteTvShow()).thenReturn(tvShows)
        val tvShow = viewModel.getShow().value
        verify(catalogDataRepository).getFavoriteTvShow()
        assertNotNull(tvShow)
        assertEquals(2, tvShow?.size)

        viewModel.getShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}