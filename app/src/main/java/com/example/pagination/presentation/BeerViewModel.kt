package com.example.pagination.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pagination.data.room_db.PixabayEntity
import com.example.pagination.data.toPixabayDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.newCoroutineContext
import okhttp3.Dispatcher
import javax.inject.Inject


@HiltViewModel
class BeerViewModel @Inject constructor(
    pager: Pager<Int, PixabayEntity>
) : ViewModel() {

    var beerPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toPixabayDto() }
        }.cachedIn(viewModelScope)


}