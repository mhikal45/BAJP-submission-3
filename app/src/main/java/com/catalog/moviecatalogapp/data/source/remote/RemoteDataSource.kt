package com.catalog.moviecatalogapp.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.catalog.moviecatalogapp.BuildConfig
import com.catalog.moviecatalogapp.data.source.remote.response.movie.Movie
import com.catalog.moviecatalogapp.data.source.remote.response.movie.MoviesDetailResponse
import com.catalog.moviecatalogapp.data.source.remote.response.movie.MoviesResponse
import com.catalog.moviecatalogapp.data.source.remote.response.tvshow.DetailTvShowResponse
import com.catalog.moviecatalogapp.data.source.remote.response.tvshow.TvShow
import com.catalog.moviecatalogapp.data.source.remote.response.tvshow.TvShowsResponse
import com.catalog.moviecatalogapp.network.ApiConfig
import com.catalog.moviecatalogapp.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance : RemoteDataSource? = null

        fun getInstance () : RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getMovies () : LiveData<ApiResponse<List<Movie>>>{
        EspressoIdlingResource.increment()
        val movieResult = MutableLiveData<ApiResponse<List<Movie>>>()
        val client = ApiConfig.getApiService().getMovies(BuildConfig.API_KEY)
        client.enqueue( object : Callback<MoviesResponse>{
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                val result = response.body()?.items
                if (result != null ) {
                    movieResult.value = ApiResponse.success(result)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e("Movies Callback :", "${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return movieResult
    }

    fun getDetailMovie (movieId: Int) : LiveData<ApiResponse<MoviesDetailResponse>> {
        EspressoIdlingResource.increment()
        val detailMovie = MutableLiveData<ApiResponse<MoviesDetailResponse>>()
        val client = ApiConfig.getApiService().getDetailMovie(movieId,BuildConfig.API_KEY)
        client.enqueue(object : Callback<MoviesDetailResponse> {
            override fun onResponse(
                call: Call<MoviesDetailResponse>,
                response: Response<MoviesDetailResponse>
            ) {
                val result = response.body()
                if (result != null) {
                    detailMovie.value = ApiResponse.success(result)
                    Log.d("Genre remote:", "ini ${result.genres}")
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesDetailResponse>, t: Throwable) {
                Log.e("Detail Callback :", "${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return detailMovie
    }

    fun getTvShows () : LiveData<ApiResponse<List<TvShow>>> {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getTvShows(BuildConfig.API_KEY)
        val tvShowResult = MutableLiveData<ApiResponse<List<TvShow>>>()
        client.enqueue(object : Callback<TvShowsResponse>{
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                val result = response.body()?.items
                if (result != null ) {
                    tvShowResult.value = ApiResponse.success(result)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e("TV Show Callback :", "${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return tvShowResult
    }

    fun getDetailTvShow (showsId: Int) : LiveData<ApiResponse<DetailTvShowResponse>> {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailTvShow(showsId,BuildConfig.API_KEY)
        val tvshowDetail = MutableLiveData<ApiResponse<DetailTvShowResponse>>()
        client.enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(
                call: Call<DetailTvShowResponse>,
                response: Response<DetailTvShowResponse>
            ) {
                val result = response.body()
                if (result != null) {
                    tvshowDetail.value = ApiResponse.success(result)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                Log.e("Detail Callback :", "${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return tvshowDetail
    }
}