package com.catalog.moviecatalogapp.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.catalog.moviecatalogapp.data.NetworkBoundResource
import com.catalog.moviecatalogapp.data.source.local.LocalDataSource
import com.catalog.moviecatalogapp.data.source.local.entity.MovieEntity
import com.catalog.moviecatalogapp.data.source.local.entity.TvShowEntity
import com.catalog.moviecatalogapp.data.source.remote.ApiResponse
import com.catalog.moviecatalogapp.data.source.remote.RemoteDataSource
import com.catalog.moviecatalogapp.data.source.remote.response.movie.Movie
import com.catalog.moviecatalogapp.data.source.remote.response.movie.MoviesDetailResponse
import com.catalog.moviecatalogapp.data.source.remote.response.tvshow.DetailTvShowResponse
import com.catalog.moviecatalogapp.data.source.remote.response.tvshow.TvShow
import com.catalog.moviecatalogapp.utils.AppExecutors
import com.catalog.moviecatalogapp.vo.Resource

class CatalogDataRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors):
    CatalogDataSource {

    companion object {
        @Volatile
        private var instance : CatalogDataRepository? = null

        fun getInstance (remoteDataSource: RemoteDataSource,
                         localDataSource: LocalDataSource,
                         appExecutors: AppExecutors) : CatalogDataRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogDataRepository(remoteDataSource,localDataSource, appExecutors)
            }
    }

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> {
                return remoteDataSource.getMovies()
            }

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.overview,
                        response.releaseDate,
                        response.voteAverage.toString(),
                        response.posterPath,
                        favorite = false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MoviesDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getMovieDetail(movieId)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<MoviesDetailResponse>> {
                return remoteDataSource.getDetailMovie(movieId)
            }

            override fun saveCallResult(data: MoviesDetailResponse) {

                Log.d("Genre detail :", "ini ${data.genres}")

                val detailMovie = MovieEntity(
                    data.id,
                    data.title,
                    data.overview,
                    data.releaseDate,
                    data.overview,
                    data.posterPath,
                    favorite = false
                )

                localDataSource.updateMovie(detailMovie,false)
            }
        }.asLiveData()
    }

    override fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvshow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> {
                return remoteDataSource.getTvShows()
            }

            override fun saveCallResult(data: List<TvShow>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val show = TvShowEntity(
                        response.id,
                        response.name,
                        response.overview,
                        response.firstAirDate,
                        response.voteAverage.toString(),
                        response.posterPath,
                        favorite = false
                    )
                    tvShowList.add(show)
                }
                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(showId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, DetailTvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return localDataSource.getTvShowDetail(showId)
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailTvShowResponse>> {
                return remoteDataSource.getDetailTvShow(showId)
            }

            override fun saveCallResult(data: DetailTvShowResponse) {

                val detailShow = TvShowEntity(
                    data.id,
                    data.name,
                    data.overview,
                    data.firstAirDate,
                    data.overview,
                    data.posterPath,
                    favorite = false
                )

                localDataSource.updateTvShow(detailShow,false)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getMoviesFavorite(), config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getTvShowsFavorite(), config).build()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) {
        return appExecutors.diskIO().execute { localDataSource.updateMovie(movie, state) }
    }

    override fun setTvShowFavorite(movie: TvShowEntity, state: Boolean) {
        return appExecutors.diskIO().execute { localDataSource.updateTvShow(movie, state) }
    }

}