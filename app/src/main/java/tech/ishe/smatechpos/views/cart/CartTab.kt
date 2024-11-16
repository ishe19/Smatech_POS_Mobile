package tech.ishe.smatechpos.views.cart

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.material.icons.rounded.RemoveShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import tech.ishe.smatechpos.data.models.NetworkResponse
import tech.ishe.smatechpos.data.models.OrderItemRequest
import tech.ishe.smatechpos.data.models.OrderRequest
import tech.ishe.smatechpos.ui.theme.GreenEnd
import tech.ishe.smatechpos.ui.theme.GreenStart
import tech.ishe.smatechpos.ui.theme.OrangeStart
import tech.ishe.smatechpos.viewmodels.CartViewModel
import tech.ishe.smatechpos.viewmodels.LoadingViewModel
import tech.ishe.smatechpos.viewmodels.OrdersViewModel
import tech.ishe.smatechpos.views.cart.widgets.CartCard
import tech.ishe.smatechpos.views.utils.ScreenDimensions
import tech.ishe.smatechpos.views.utils.getGradient
import java.util.Locale

@Composable
fun CartTab(modifier: Modifier) {

    val context = LocalContext.current
    val cartViewModel: CartViewModel =
        ViewModelProvider(context as ComponentActivity)[CartViewModel::class.java]
    val ordersViewModel: OrdersViewModel = ViewModelProvider(context)[OrdersViewModel::class.java]
    val loadingViewModel: LoadingViewModel =
        ViewModelProvider(context)[LoadingViewModel::class.java]

    val cartList = cartViewModel.cartList.value
    var subtotal = 0.0
    var deliveryFee = 0.0
    var total = 0.0

    var showDialog by remember { mutableStateOf(false) }
    val orderPostResult = ordersViewModel.orderPostResult.observeAsState()


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 2.dp, horizontal = 4.dp),
    ) {

        Box(
            modifier = Modifier
                .height((ScreenDimensions.screenHeightDp / 1.7).dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                if (cartList != null) {
                    if (cartList.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .height((ScreenDimensions.screenHeightDp / 1.7).dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.RemoveShoppingCart,
                                        contentDescription = "Empty shopping cart"
                                    )
                                    Text(text = "Nothing in your cart yet...")
                                }
                            }
                        }
                    } else {
                        items(cartList.size) { index ->
                            CartCard(cartItemModel = cartList[index])
                        }
                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {

            cartList?.forEach { item ->
                subtotal += (item.productModel.price * item.quantity)
            }
            val formattedSubtotal = String.format(Locale.UK, "%.2f", subtotal)
            Text(text = "Subtotal:")
            Text(
                text = "$${formattedSubtotal}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            deliveryFee = 0.05 * subtotal

            val formattedDeliveryFee = String.format(Locale.UK, "%.2f", deliveryFee)
            Text(text = "Delivery Fee:")
            Text(
                text = "$${formattedDeliveryFee}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(OrangeStart)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            total = deliveryFee + subtotal

            val formattedTotal = String.format(Locale.UK, "%.2f", total)
            Text(text = "Total:")
            Text(
                text = "$${formattedTotal}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                    if (cartList != null) {
                        if(cartList.isNotEmpty()){
                            showDialog = true
                        } else{
                            Toast.makeText(context, "Cart is empty, please add then order", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Rounded.CheckCircleOutline,
                        contentDescription = "add to cart"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Place Order",
                        fontSize = 20.sp
                    )
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Confirm Order") },
                text = { Text(text = "Are you sure you want to place this order?") },
                confirmButton = {
                    TextButton(onClick = {
                        var productsList: List<OrderItemRequest> = emptyList()
                        cartList?.forEach { item ->
                            val orderItemRequest =
                                OrderItemRequest(item.productModel.productSku, item.quantity)
                            productsList = productsList + orderItemRequest
                        }

                        val orderRequest = OrderRequest(total, subtotal, deliveryFee, productsList)

                        ordersViewModel.placeOrder(orderRequest)
                        showDialog = false
                    }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(text = "Cancel")
                    }
                }
            )
        }

        when (val result = orderPostResult.value) {
            is NetworkResponse.Error -> {
                loadingViewModel.isLoading(false)
                Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
            }

            NetworkResponse.Loading -> {
                loadingViewModel.isLoading(true)
            }

            is NetworkResponse.Success -> {
                loadingViewModel.isLoading(false)
                Toast.makeText(context, result.data.message, Toast.LENGTH_SHORT).show()
                cartViewModel.clearCart()
                ordersViewModel.clearResult()
            }

            null -> {}
        }
    }
}