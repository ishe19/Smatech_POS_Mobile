package tech.ishe.smatechpos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson
import tech.ishe.smatechpos.data.models.ProductModel
import tech.ishe.smatechpos.ui.theme.SmatechPOSTheme
import tech.ishe.smatechpos.viewmodels.ProductsViewModel
import tech.ishe.smatechpos.views.home.MainScreen
import tech.ishe.smatechpos.views.home.ProductDetailsScreen
import tech.ishe.smatechpos.views.utils.Routes

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productsViewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            SmatechPOSTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Routes.mainScreen,
                    builder = {
                        composable(Routes.mainScreen) { MainScreen(productsViewModel, navController) }
                        composable(Routes.productDetails + "/{product}") { backStackEntry ->
                            val productJson = backStackEntry.arguments?.getString("product")
                            val productModel = Gson().fromJson(productJson, ProductModel::class.java)
                            ProductDetailsScreen(productModel)
                        }
                    }
                )
            }
        }
    }
}


@Composable
private fun SetBarColour(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = color
        )
    }
}



