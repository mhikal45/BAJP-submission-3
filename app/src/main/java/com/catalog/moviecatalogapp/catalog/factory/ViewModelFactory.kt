package com.catalog.moviecatalogapp.catalog.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.catalog.moviecatalogapp.catalog.inject.Injection
import com.catalog.moviecatalogapp.data.source.CatalogDataRepository
import com.catalog.moviecatalogapp.ui.detail.DetailViewModel
import com.catalog.moviecatalogapp.ui.favorite.movie.MovieViewModel
import com.catalog.moviecatalogapp.ui.favorite.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val catalogDataRepository: CatalogDataRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        var instance : ViewModelFactory? = null

        fun getInstance (context: Context) : ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(catalogDataRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(catalogDataRepository) as T
            }
            modelClass.isAssignableFrom(com.catalog.moviecatalogapp.ui.movie.MovieViewModel::class.java)-> {
                com.catalog.moviecatalogapp.ui.movie.MovieViewModel(catalogDataRepository) as T
            }

            modelClass.isAssignableFrom(com.catalog.moviecatalogapp.ui.tvshow.TvShowViewModel::class.java)-> {
                com.catalog.moviecatalogapp.ui.tvshow.TvShowViewModel(catalogDataRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(catalogDataRepository) as T
            }
            else -> throw Throwable("Class View Model Tidak Diketahui :" + modelClass.name)
        }
    }

}