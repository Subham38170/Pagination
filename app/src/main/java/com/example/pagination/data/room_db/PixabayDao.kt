package com.example.pagination.data.room_db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PixabayDao {

    @Upsert
    suspend fun upsertAll(beers: List<PixabayEntity>)

    @Query("SELECT * FROM pixabayentity")
    fun pagingSource(): PagingSource<Int, PixabayEntity>

    @Query("DELETE FROM pixabayentity ")
    suspend fun clearAll()
}