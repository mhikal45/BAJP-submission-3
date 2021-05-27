package com.catalog.moviecatalogapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.catalog.moviecatalogapp.data.source.local.entity.MovieEntity
import com.catalog.moviecatalogapp.data.source.local.entity.TvShowEntity
import com.catalog.moviecatalogapp.data.source.local.room.FilmDao

class LocalDataSource private constructor(private val mFilmDao: FilmDao) {

    companion object {
        private var instance : LocalDataSource? = null

        fun getInstance (filmDao: FilmDao) : LocalDataSource =
            instance ?: LocalDataSource(filmDao)
    }

    //MOVIE
    fun getMovie(): DataSource.Factory<Int, MovieEntity> = mFilmDao.getMovie()

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity> = mFilmDao.getMovieDetail(movieId)

    fun insertMovies(movie: List<MovieEntity>): LongArray = mFilmDao.insertMovies(movie)

    fun getMoviesFavorite(): DataSource.Factory<Int, MovieEntity>  = mFilmDao.getMoviesFavorite()

    fun updateMovie(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        mFilmDao.updateMovie(movie)
    }

    //TV SHOW
    fun getTvshow(): DataSource.Factory<Int, TvShowEntity> = mFilmDao.getTvshow()

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> = mFilmDao.getTvShowDetail(tvShowId)

    fun insertTvShow(tvShow: List<TvShowEntity>): LongArray = mFilmDao.insertTvShow(tvShow)

    fun getTvShowsFavorite(): DataSource.Factory<Int,TvShowEntity> = mFilmDao.getTvShowsFavorite()

    fun updateTvShow(movie: TvShowEntity, newState: Boolean) {
        movie.favorite = newState
        mFilmDao.updateTvShow(movie)
    }
}