package com.catalog.moviecatalogapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.catalog.moviecatalogapp.data.source.CatalogDataRepository
import com.catalog.moviecatalogapp.data.source.local.entity.MovieEntity
import com.catalog.moviecatalogapp.data.source.local.entity.TvShowEntity
import com.catalog.moviecatalogapp.ui.detail.DetailViewModel.Companion.MOVIE
import com.catalog.moviecatalogapp.ui.detail.DetailViewModel.Companion.TV_SHOW
import com.catalog.moviecatalogapp.utils.DataDummy
import com.catalog.moviecatalogapp.vo.Resource
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@Suppress("CAST_NEVER_SUCCEEDS")
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyDetailMovie()
    private val dummyTvShow = DataDummy.generateDummyDetailTvShow()
    private val dummyMovieId = dummyMovie.filmId
    private val dummyTvShowId = dummyTvShow.filmId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogDataRepository: CatalogDataRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUpMovie () {
        viewModel = DetailViewModel(catalogDataRepository)
    }

    @Test
    fun getMovieDetail () {
        val dummyDetailMovie = Resource.success(DataDummy.generateDummyDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(catalogDataRepository.getDetailMovie(dummyMovieId!!)).thenReturn(movie)
        viewModel.setFilmDetail(dummyMovieId, MOVIE)

        viewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Before
    fun setUpTvShow () {
        viewModel = DetailViewModel(catalogDataRepository)
    }

    @Test
    fun getTvShowDetail () {
        val tvShowDetail = Resource.success(DataDummy.generateDummyDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = tvShowDetail

        `when`(catalogDataRepository.getDetailTvShow(dummyTvShowId!!)).thenReturn(tvShow)
        viewModel.setFilmDetail(dummyTvShowId, TV_SHOW)
        viewModel.getDetailTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvShowDetail)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(DataDummy.generateDummyDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(catalogDataRepository.getDetailMovie(dummyMovieId!!)).thenReturn(movie)

        viewModel.setFilmDetail(dummyMovieId, MOVIE)
        viewModel.setFavoriteMovie()
        verify(catalogDataRepository).setMovieFavorite(movie.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(movieObserver)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyDetailTvShow = Resource.success(DataDummy.generateDummyDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(catalogDataRepository.getDetailTvShow(dummyTvShowId!!)).thenReturn(tvShow)

        viewModel.setFilmDetail(dummyTvShowId, TV_SHOW)
        viewModel.setFavoriteTvShow()
        verify(catalogDataRepository).setTvShowFavorite(tvShow.value!!.data as TvShowEntity, true)
        verifyNoMoreInteractions(tvShowObserver)
    }
}