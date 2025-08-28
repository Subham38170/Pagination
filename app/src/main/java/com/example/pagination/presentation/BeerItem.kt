package com.example.pagination.presentation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pagination.domain.PixabayImageDto


import com.example.pagination.R
@Composable
fun BeerItem(
    beer: PixabayImageDto,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        AsyncImage(
            model = beer.largeImageURL,
            contentDescription = beer.user,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.baseline_error_outline_24),
            placeholder = painterResource(id = R.drawable.outline_downloading_24),
            onLoading = {
                Log.d("BeerItem", "Loading")
            },
            onError = {
                Log.d("BeerItem","Error")
            },
            onSuccess = {
                Log.d("BeerItem","Success")
            }
        )
    }
}

