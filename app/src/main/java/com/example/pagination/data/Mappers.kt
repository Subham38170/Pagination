package com.example.pagination.data

import com.example.pagination.data.room_db.PixabayEntity
import com.example.pagination.domain.PixabayImageDto

fun PixabayEntity.toPixabayDto(): PixabayImageDto{
    return PixabayImageDto(
        id= this.id,
        pageURL = this.pageURL,
        type = this.type,
        tags = this.tags,
        previewURL = this.previewURL,
        webformatURL = this.webformatURL,
        largeImageURL = this.largeImageURL,
        user = this.user,
        userImageURL = this.userImageURL
    )
}

fun PixabayImageDto.toPixabayImageEntity(): PixabayEntity{
    return PixabayEntity(
        id= this.id,
        pageURL = this.pageURL,
        type = this.type,
        tags = this.tags,
        previewURL = this.previewURL,
        webformatURL = this.webformatURL,
        largeImageURL = this.largeImageURL,
        user = this.user,
        userImageURL = this.userImageURL
    )}