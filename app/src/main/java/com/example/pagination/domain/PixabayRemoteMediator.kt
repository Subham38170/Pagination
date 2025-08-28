package com.example.pagination.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.pagination.data.PixabayApi
import com.example.pagination.data.room_db.PixabayDatabase
import com.example.pagination.data.room_db.PixabayEntity
import com.example.pagination.data.toPixabayImageEntity
import okio.IOException
import retrofit2.HttpException


@OptIn(ExperimentalPagingApi::class)
class PixabayRemoteMediator(
    private val pixabayDb: PixabayDatabase,
    private val pixabayApi: PixabayApi
) : RemoteMediator<Int, PixabayEntity>() {
    private var currentPage = 1
    @OptIn(ExperimentalPagingApi::class)

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PixabayEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.APPEND -> currentPage+1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.REFRESH -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val images = pixabayApi.getImages(
                page = loadKey.toInt(),
                pageCount = state.config.pageSize
            )
            if (images.hits.isNotEmpty()) {
                currentPage = loadKey.toInt()
            }
            pixabayDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pixabayDb.dao.clearAll()
                }
                val beerEntities = pixabayDb.dao.upsertAll(images.hits.map { it.toPixabayImageEntity() })

            }
            MediatorResult.Success(
                endOfPaginationReached = images.hits.isEmpty()
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}