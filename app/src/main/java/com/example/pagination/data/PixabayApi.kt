package com.example.pagination.data

import com.example.pagination.domain.PixabayImageDto
import com.example.pagination.domain.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface PixabayApi {


    @GET(".")
    suspend fun getImages(
        @Query("key") key: String = API_KEY,
        @Query("q") q: String="flowers",
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int,

    ): PixabayResponse

    companion object{
        const val BASE_URL = "https://pixabay.com/api/"
        const val API_KEY = "51045668-31cc1e81ed98af0dd1729f65b"
    }
}

