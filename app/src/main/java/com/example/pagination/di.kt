package com.example.pagination

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.pagination.data.PixabayApi
import com.example.pagination.data.room_db.PixabayDatabase
import com.example.pagination.data.room_db.PixabayEntity
import com.example.pagination.domain.PixabayRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object di {


    @Provides
    @Singleton
    fun provideBeerDatabse(
        @ApplicationContext context: Context
    ): PixabayDatabase {
        return Room.databaseBuilder(
            context,
            PixabayDatabase::class.java,
            "beers.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBeerApi(): PixabayApi {
        return Retrofit.Builder()
            .baseUrl(PixabayApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PixabayApi::class.java)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBeerPager(
        beerDb: PixabayDatabase,
        beerApi: PixabayApi
    ): Pager<Int, PixabayEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = PixabayRemoteMediator(
                beerDb, beerApi
            ),
            pagingSourceFactory = {
                beerDb.dao.pagingSource()
            }
        )
    }
}