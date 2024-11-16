package tech.ishe.smatechpos.views.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import tech.ishe.smatechpos.data.models.NetworkResponse
import tech.ishe.smatechpos.viewmodels.ProductsViewModel

@Composable
fun ProductImage(productSku: String, productsViewModel: ProductsViewModel) {
    val productImageResult by productsViewModel.getProductImageState(productSku).observeAsState()
    LaunchedEffect(productSku) {
        productsViewModel.getProductImage(productSku)
    }

    when (productImageResult) {
        is NetworkResponse.Error -> {
            val errorMessage = (productImageResult as NetworkResponse.Error).message
            Text(
                text = errorMessage,
                textAlign = TextAlign.Center,
                color = Color.Red,
                modifier = Modifier.fillMaxSize()
            )
        }

        NetworkResponse.Loading -> {
            CircularProgressIndicator()
        }

        is NetworkResponse.Success -> {
            val bitmap = (productImageResult as NetworkResponse.Success).data
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Product Image",
                modifier = Modifier.fillMaxHeight()
            )
        }

        null -> {
            Text("No state yet.", textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
        }
    }
}

