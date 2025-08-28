package com.example.pagination.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.pagination.domain.PixabayImageDto

@Composable
fun BeerScreen(
    beer: LazyPagingItems<PixabayImageDto>
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = beer.loadState) {
        if (beer.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error " + (beer.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (beer.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = beer.itemCount,
                ) { index ->
                    Log.d("Data",beer[index].toString())
                    val item = beer[index]
                    if (item != null) {
                        BeerItem(
                            beer = item,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                item {
                    if (beer.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }

            }
        }
    }


}