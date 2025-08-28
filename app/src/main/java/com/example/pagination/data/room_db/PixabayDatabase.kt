package com.example.pagination.data.room_db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [PixabayEntity::class],
    version = 1
)
abstract class PixabayDatabase: RoomDatabase() {

    abstract val dao: PixabayDao
}