package com.example.dicodingjetpackpro.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dicodingjetpackpro.model.response.tv.TvDetailResponse
import com.example.dicodingjetpackpro.utils.getCurrentDate

@Entity(tableName = "tbl_tv")
data class TvEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String,
    @ColumnInfo(name = "is_bookmark")
    val isBookmark: Boolean = false,
    @ColumnInfo(name = "created_at")
    val createdAt: String
) {
    companion object {
        fun mapToTvEntity(data: TvDetailResponse, isBookmark: Boolean) = TvEntity(
            data.id,
            data.name,
            data.posterPath,
            data.firstAirDate,
            isBookmark,
            getCurrentDate()
        )
    }
}