package com.catalog.moviecatalogapp.data.source.local.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.catalog.moviecatalogapp.data.source.local.entity.MovieEntity
import com.catalog.moviecatalogapp.data.source.local.entity.TvShowEntity

@Dao
interface FilmDao {

    @Query("SELECT * FROM movie")
    fun getMovie(): DataSource.Factory<Int,MovieEntity>

    @Query("SELECT * FROM movie WHERE filmId = :movieId")
    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<MovieEntity>): LongArray

    @Update
    fun updateMovie(movie: MovieEntity): Int

    @Query("SELECT * FROM movie where favorite = 1")
    fun getMoviesFavorite(): DataSource.Factory<Int,MovieEntity>

    @Query("SELECT * FROM movie where favorite = 1")
    fun getMovieAsPaged(): DataSource.Factory<Int, MovieEntity>


    @Query("SELECT * FROM tvshow")
    fun getTvshow(): DataSource.Factory<Int,TvShowEntity>

    @Query("SELECT * FROM tvshow WHERE filmId = :tvShowId")
    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>): LongArray

    @Update
    fun updateTvShow(tvShow: TvShowEntity): Int

    @WorkerThread
    @Query("SELECT * FROM tvshow where favorite = 1")
    fun getTvShowsFavorite(): DataSource.Factory<Int,TvShowEntity>

    @Query("SELECT * FROM tvshow where favorite = 1")
    fun getTvShowAsPaged(): DataSource.Factory<Int, TvShowEntity>
}