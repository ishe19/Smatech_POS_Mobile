package tech.ishe.smatechpos.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import tech.ishe.smatechpos.data.models.NetworkResponse
import tech.ishe.smatechpos.viewmodels.ProductsViewModel
import tech.ishe.smatechpos.views.home.widgets.ProductCard


@Composable
fun HomeTab(viewModel: ProductsViewModel, navController: NavController) {
    var searchString by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(

                value = searchString,
                onValueChange = {
                    searchString = it
                },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(45.dp),
                label = { Text(text = "Search") }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .clickable {}
                .padding(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer

                )
            }

        }

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        Text(
            text = "Products",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
        )

        Box(modifier = Modifier
            .fillMaxSize()) {
            ProductsList(viewModel, navController)
        }

    }
}


@Composable
fun ProductsList(productsViewModel: ProductsViewModel, navController: NavController) {
    val productsResult = productsViewModel.productsResult.observeAsState()

    LaunchedEffect(Unit) {
        productsViewModel.getProducts()
    }


    when (val result = productsResult.value) {
        is NetworkResponse.Error -> {
            Text(text = "Failed to load products...")
        }

        NetworkResponse.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,

            ) {
                CircularProgressIndicator()
            }
        }

        is NetworkResponse.Success -> {
            val products = result.data
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(products.size) { index ->
                    ProductCard(products[index], productsViewModel, navController)
                }
            }
        }

        null -> {}
    }
}




