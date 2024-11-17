package tech.ishe.smatechpos.views.home

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import tech.ishe.smatechpos.data.models.CartItemModel
import tech.ishe.smatechpos.data.models.ProductModel
import tech.ishe.smatechpos.views.utils.theme.GreenEnd
import tech.ishe.smatechpos.views.utils.theme.GreenStart
import tech.ishe.smatechpos.views.utils.theme.OrangeStart
import tech.ishe.smatechpos.viewmodels.CartViewModel
import tech.ishe.smatechpos.viewmodels.ProductsViewModel
import tech.ishe.smatechpos.views.utils.ProductImage
import tech.ishe.smatechpos.views.utils.ScreenDimensions
import tech.ishe.smatechpos.views.utils.getGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(productModel: ProductModel, navController:NavController) {
    val context = LocalContext.current
    val productsViewModel: ProductsViewModel =
        ViewModelProvider(context as ComponentActivity)[ProductsViewModel::class.java]

    val cartViewModel: CartViewModel =
        ViewModelProvider(context)[CartViewModel::class.java]

    var productCount by remember {
        mutableIntStateOf(1)
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((ScreenDimensions.screenHeightDp * 0.15).dp)
                    .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                    .background(MaterialTheme.colorScheme.onBackground)
            ){

                Box(
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp))
                        .background(
                            brush = getGradient(GreenStart, GreenEnd),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Button(
                        onClick = {
                           if(productCount > 0){

                               val cartItemModel = CartItemModel(
                                   productDescription = productModel.description,
                                   productSku = productModel.productSku,
                                   productName = productModel.productName,
                                   quantity = productCount,
                                   price = productModel.price,
                                   id = 0,
                               )
                               cartViewModel.addToCart(cartItemModel)
                               Toast.makeText(context, "${productModel.productName} added to cart", Toast.LENGTH_SHORT).show()
                           } else {
                               Toast.makeText(context, "Please select a valid quantity", Toast.LENGTH_SHORT).show()

                           }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row {
                            Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = "add to cart")
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Add To Cart")
                        }
                    }
                }
            }
        },
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Product Details",
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                ) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    },
                    ) {
                        Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(4.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((ScreenDimensions.screenHeightDp * 0.3).dp)
            ) {
                ProductImage(productModel.productSku, productsViewModel)
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(text = productModel.productName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start)
            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Price: $${productModel.price}",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
                )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
            ){
                Text(text = "SKU: ${productModel.productSku}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .background(OrangeStart)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(text="Description",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign= TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
                )
            Text(text = productModel.description,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(8.dp)
                )
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OrangeStart
                    ),
                    onClick = {
                    productCount--
                }) {
                    Icon(imageVector = Icons.Rounded.Remove, contentDescription ="Subtract Quantity")
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.inverseOnSurface)
                        .width((ScreenDimensions.screenWidthDp / 3).dp)
                        .height(60.dp),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "$productCount",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding()
                        )
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenStart
                    ),
                    onClick = {
                    productCount++
                }) {
                    Icon(imageVector = Icons.Rounded.Add, contentDescription ="Add Quantity")
                }
            }
        }
    }
}