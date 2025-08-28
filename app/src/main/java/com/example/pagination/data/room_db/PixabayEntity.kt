package com.example.pagination.data.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PixabayEntity(
    @PrimaryKey
    val id: Long,
    val pageURL: String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val webformatURL: String,
    val largeImageURL: String,
    val user: String,
    val userImageURL: String
)