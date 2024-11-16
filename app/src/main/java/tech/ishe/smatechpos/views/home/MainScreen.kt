package tech.ishe.smatechpos.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import tech.ishe.smatechpos.viewmodels.ProductsViewModel
import tech.ishe.smatechpos.views.cart.CartTab
import tech.ishe.smatechpos.views.home.widgets.barItems
import tech.ishe.smatechpos.views.receipts.ReceiptsTab

@Composable
fun MainScreen(productsViewModel: ProductsViewModel, navController: NavController) {

//    val navController = rememberNavController()

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
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
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title,
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
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
        }
    ) { innerPadding ->
        ContentScreen(
            Modifier.padding(innerPadding),
            selectedIndex,
            productsViewModel,
            navController
        )
//        Column(
//        ) {
//
//            NavHost(
//                navController = navController,
//                startDestination = "home",
//                modifier = Modifier.padding(innerPadding),
//                builder = {
//                    composable("home") { HomeTab(viewModel = productsViewModel, navController) }
//                    composable("cart") { CartTab() }
//                    composable("receipts") { ReceiptsTab() }
//
//                }
//            )
//
//        }
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    productsViewModel: ProductsViewModel,
    navController: NavController
) {
    when (selectedIndex) {
        0 -> HomeTab(viewModel = productsViewModel, navController = navController)
        1 -> CartTab()
        2 -> ReceiptsTab()
    }
}


