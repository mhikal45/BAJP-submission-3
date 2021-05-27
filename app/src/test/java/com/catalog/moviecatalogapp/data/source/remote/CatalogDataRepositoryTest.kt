package com.catalog.moviecatalogapp.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.catalog.moviecatalogapp.data.source.local.LocalDataSource
import com.catalog.moviecatalogapp.data.source.local.entity.MovieEntity
import com.catalog.moviecatalogapp.data.source.local.entity.TvShowEntity
import com.catalog.moviecatalogapp.utils.AppExecutors
import com.catalog.moviecatalogapp.utils.DataDummy
import com.catalog.moviecatalogapp.utils.LiveDataTestUtils
import com.catalog.moviecatalogapp.utils.PagedListUtils
import com.catalog.moviecatalogapp.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.*

class CatalogDataRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val catalogDataRepository = FakeCatalogDataRepository(remote,local,appExecutors)

    private val moviesResponse = DataDummy.generateDummyRemoteMovie()
    private val movieId = moviesResponse[0].id
    private val movieDetail = DataDummy.generateDummyRemoteDetailMovie()

    private val tvShowResponse = DataDummy.generateDummyRemoteTvShow()
    private val tvShowId = tvShowResponse[0].id
    private val tvShowDetail = DataDummy.generateDummyRemoteDetailTvShow()

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovie()).thenReturn(dataSourceFactory)
        catalogDataRepository.getMovies()

        val movieEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getMovie()
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = DataDummy.generateDummyDetailMovie()
        `when`(local.getMovieDetail(movieId)).thenReturn(dummyDetail)

        val movieDetailEntity = LiveDataTestUtils.getValue(catalogDataRepository.getDetailMovie(movieId))
        verify(local).getMovieDetail(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.data?.filmId)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMoviesFavorite()).thenReturn(dataSourceFactory)
        catalogDataRepository.getFavoriteMovies()

        val movieEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getMoviesFavorite()
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.data?.size)
    }

    @Test
    fun setFavoriteMovie() {
        catalogDataRepository.setMovieFavorite(DataDummy.generateDummyDetailMovie(), true)
        verify(local).updateMovie(DataDummy.generateDummyDetailMovie(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvshow()).thenReturn(dataSourceFactory)
        catalogDataRepository.getTvShow()

        val tvShowEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getTvshow()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetail = MutableLiveData<TvShowEntity>()
        dummyDetail.value = DataDummy.generateDummyDetailTvShow()
        `when`(local.getTvShowDetail(tvShowId!!)).thenReturn(dummyDetail)

        val tvShowDetailEntity = LiveDataTestUtils.getValue(catalogDataRepository.getDetailTvShow(tvShowId))
        verify(local).getTvShowDetail(tvShowId)
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id, tvShowDetailEntity.data?.filmId)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShowsFavorite()).thenReturn(dataSourceFactory)
        catalogDataRepository.getFavoriteTvShow()

        val tvShowEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getTvShowsFavorite()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun setFavoriteTvShow() {
        catalogDataRepository.setTvShowFavorite(DataDummy.generateDummyDetailTvShow(), true)
        verify(local).updateTvShow(DataDummy.generateDummyDetailTvShow(), true)
        verifyNoMoreInteractions(local)
    }
}