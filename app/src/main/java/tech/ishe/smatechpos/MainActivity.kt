package tech.ishe.smatechpos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson
import tech.ishe.smatechpos.data.models.OrderModel
import tech.ishe.smatechpos.data.models.ProductModel
import tech.ishe.smatechpos.ui.theme.SmatechPOSTheme
import tech.ishe.smatechpos.viewmodels.CartViewModel
import tech.ishe.smatechpos.viewmodels.LoadingViewModel
import tech.ishe.smatechpos.viewmodels.OrdersViewModel
import tech.ishe.smatechpos.viewmodels.ProductsViewModel
import tech.ishe.smatechpos.views.home.MainScreen
import tech.ishe.smatechpos.views.home.ProductDetailsScreen
import tech.ishe.smatechpos.views.receipts.ReceiptDetailsScreen
import tech.ishe.smatechpos.views.utils.LoadingOverlay
import tech.ishe.smatechpos.views.utils.Routes
import tech.ishe.smatechpos.views.utils.ScreenDimensions

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productsViewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        val shoppingCartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        val ordersViewModel = ViewModelProvider(this)[OrdersViewModel::class.java]
        val loadingViewModel = ViewModelProvider(this)[LoadingViewModel::class.java]
        enableEdgeToEdge()
        setContent {

            val configuration = LocalConfiguration.current
            ScreenDimensions.initialize(configuration)
            SmatechPOSTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.mainScreen,
                    builder = {
                        composable(Routes.mainScreen) {
                            MainScreen(
                                productsViewModel,
                                navController
                            )
                        }
                        composable(Routes.productDetails + "/{product}") { backStackEntry ->
                            val productJson = backStackEntry.arguments?.getString("product")
                            val productModel =
                                Gson().fromJson(productJson, ProductModel::class.java)
                            ProductDetailsScreen(productModel, navController)
                        }
                        composable(Routes.receiptDetails + "/{receipt}") { backStackEntry ->
                            val receiptJson = backStackEntry.arguments?.getString("receipt")
                            val orderModel = Gson().fromJson(receiptJson, OrderModel::class.java)
                            ReceiptDetailsScreen(orderModel, navController)
                        }
                    }
                )

                val loadingResult = loadingViewModel.loading.observeAsState(false)
                LoadingOverlay(isLoading = loadingResult.value)
            }
        }
    }
}




