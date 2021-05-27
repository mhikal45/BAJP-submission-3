package com.catalog.moviecatalogapp.catalog.inject

import android.content.Context
import com.catalog.moviecatalogapp.data.source.CatalogDataRepository
import com.catalog.moviecatalogapp.data.source.local.LocalDataSource
import com.catalog.moviecatalogapp.data.source.local.room.CatalogDatabase
import com.catalog.moviecatalogapp.data.source.remote.RemoteDataSource
import com.catalog.moviecatalogapp.utils.AppExecutors

object Injection {
    fun provideRepository (context: Context) : CatalogDataRepository {
        val database = CatalogDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()
        return CatalogDataRepository.getInstance(remoteDataSource,localDataSource, appExecutors)
    }
}