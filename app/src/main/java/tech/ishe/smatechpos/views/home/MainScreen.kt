@file:OptIn(ExperimentalMaterial3Api::class)

package tech.ishe.smatechpos.views.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import tech.ishe.smatechpos.viewmodels.CartViewModel
import tech.ishe.smatechpos.viewmodels.ProductsViewModel
import tech.ishe.smatechpos.views.cart.CartTab
import tech.ishe.smatechpos.views.receipts.ReceiptsTab
import tech.ishe.smatechpos.views.utils.barItems

@Composable
fun MainScreen(productsViewModel: ProductsViewModel, navController: NavController) {

    val context = LocalContext.current

    val cartViewModel: CartViewModel =
        ViewModelProvider(context as ComponentActivity)[CartViewModel::class.java]

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                Row(
                    modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
                ) {

                    barItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                            },
                            icon = {

                                BadgedBox(badge = {
                                    val cartCount = cartViewModel.cartList.value?.size
                                    if (index == 1 && cartCount != null) {
                                        Badge() {
                                            Text(text = cartCount.toString())
                                        }
                                    }
                                }) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.title,
                                        tint = MaterialTheme.colorScheme.onBackground
                                    )
                                }
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            },


                            )
                    }
                }
            }
        },
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Smatech POS",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                ) }
            )
        }
    ) { innerPadding ->
        ContentScreen(
            Modifier.padding(innerPadding),
            selectedIndex,
            productsViewModel,
            navController
        )
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier,
    selectedIndex: Int,
    productsViewModel: ProductsViewModel,
    navController: NavController
) {
    when (selectedIndex) {
        0 -> HomeTab(productsViewModel = productsViewModel, navController = navController, modifier)
        1 -> CartTab(modifier)
        2 -> ReceiptsTab(navController, modifier)
    }
}


