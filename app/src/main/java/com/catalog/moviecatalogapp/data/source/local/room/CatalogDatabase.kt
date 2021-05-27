package com.catalog.moviecatalogapp.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.catalog.moviecatalogapp.data.source.local.entity.MovieEntity
import com.catalog.moviecatalogapp.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class,TvShowEntity::class],
            version = 1,
            exportSchema = false)
abstract class CatalogDatabase : RoomDatabase() {

    abstract fun filmDao() : FilmDao

    companion object {
        @Volatile
        private var INSTANCE : CatalogDatabase? = null

        fun getInstance (context: Context) : CatalogDatabase =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    CatalogDatabase::class.java,
                    "catalog.db"
                ).build()
            }
    }
}