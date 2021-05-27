package com.catalog.moviecatalogapp.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.catalog.moviecatalogapp.data.source.local.entity.TvShowEntity
import com.catalog.moviecatalogapp.data.source.CatalogDataRepository
import com.catalog.moviecatalogapp.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogDataRepository: CatalogDataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogDataRepository)
    }

    @Test
    fun getShow() {
        val dummyTvShow = Resource.success(pagedList)
        `when`(dummyTvShow.data?.size).thenReturn(2)
        val shows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        shows.value = dummyTvShow

        `when`(catalogDataRepository.getTvShow()).thenReturn(shows)

        val showEntities = viewModel.getShow().value?.data
        verify(catalogDataRepository).getTvShow()
        assertNotNull(showEntities)
        assertEquals(2,showEntities?.size)

        viewModel.getShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}