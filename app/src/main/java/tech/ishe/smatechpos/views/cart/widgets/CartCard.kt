package tech.ishe.smatechpos.views.cart.widgets

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import tech.ishe.smatechpos.data.models.CartItemModel
import tech.ishe.smatechpos.ui.theme.GreenStart
import tech.ishe.smatechpos.ui.theme.OrangeStart
import tech.ishe.smatechpos.viewmodels.CartViewModel
import tech.ishe.smatechpos.viewmodels.ProductsViewModel
import tech.ishe.smatechpos.views.utils.ProductImage

@Composable
fun CartCard(cartItemModel: CartItemModel) {

    val context = LocalContext.current
    val productsViewModel: ProductsViewModel =
        ViewModelProvider(context as ComponentActivity)[ProductsViewModel::class.java]

    val cartViewModel: CartViewModel =
        ViewModelProvider(context)[CartViewModel::class.java]


    val product = cartItemModel.productModel



    var productQuantity by remember {
        mutableIntStateOf(cartItemModel.quantity)
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 2.dp, horizontal = 4.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface),

        ) {
        val boxWithConstraintsScope = this
        val width = boxWithConstraintsScope.maxWidth / 3


        Row(

            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Box(modifier = Modifier.width(width - 45.dp)) {
                ProductImage(product.productSku, productsViewModel)
            }

            Spacer(
                modifier = Modifier
                    .width(16.dp)
            )

            Column(
                modifier = Modifier
                    .width(width),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                Text(
                    text = product.productName,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Start
                )
                Spacer(
                    modifier = Modifier
                        .height(5.dp)
                )

                Text(
                    text = "$${product.price}",
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    IconButton(
                        onClick = { 
                             productQuantity--
                            cartViewModel.updateCartItemQuantity(productQuantity, cartItemModel)
                        }) {
                        Icon(
                            imageVector = Icons.Rounded.Remove, contentDescription = "Add Quantity",
                            tint = OrangeStart
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .width(width / 3)
                            .height(60.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$productQuantity",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding()
                        )
                    }

                    IconButton(
                        onClick = {
                            productQuantity++
                            cartViewModel.updateCartItemQuantity(productQuantity, cartItemModel)
                        }) {
                        Icon(
                            imageVector = Icons.Rounded.Add, contentDescription = "Add Quantity",
                            tint = GreenStart
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = {
                        cartViewModel.removeFromCart(cartItemModel)
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.DeleteOutline,
                            contentDescription = "Delete",
                            tint = Color.Red
                        )
                    }
                }
            }

        }
    }
}
