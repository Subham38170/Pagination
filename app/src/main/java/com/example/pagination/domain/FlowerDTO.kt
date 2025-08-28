package com.example.pagination.domain
data class PixabayResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<PixabayImageDto>
)

data class PixabayImageDto(
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