package com.catalog.moviecatalogapp.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tvshow")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "filmId")
    var filmId: Int?,

    @ColumnInfo(name = "filmName")
    var filmName: String?,

    @ColumnInfo(name = "filmSynopsis")
    var filmSynopsis: String?,

    @ColumnInfo(name = "filmDate")
    var filmDate: String?,

    @ColumnInfo(name = "filmRating")
    var filmRating: String?,

    @ColumnInfo(name = "poster")
    var poster: String?,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
) : Parcelable
