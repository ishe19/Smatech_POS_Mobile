package tech.ishe.smatechpos.views.cart

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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import tech.ishe.smatechpos.ui.theme.OrangeStart
import tech.ishe.smatechpos.viewmodels.CartViewModel
import tech.ishe.smatechpos.views.cart.widgets.CartCard
import tech.ishe.smatechpos.views.utils.ScreenDimensions
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun CartTab() {

    val context = LocalContext.current
    val cartViewModel: CartViewModel =
        ViewModelProvider(context as ComponentActivity)[CartViewModel::class.java]

    val cartList = cartViewModel.cartList.value
    var subtotal = 0.0
    var deliveryFee = 0.0
    var total: Double
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 4.dp),
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .height((ScreenDimensions.screenHeightDp / 1.7).dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                if (cartList != null) {
                    items(cartList.size) { index ->
                        CartCard(cartItemModel = cartList[index])
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
            Text(text= "Subtotal:")
            Text(text= "$${formattedSubtotal}",
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
            Text(text= "Delivery Fee:")
            Text(text= "$${formattedDeliveryFee}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier
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
            Text(text= "Total:")
            Text(text= "$${formattedTotal}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Button(onClick = { /*TODO*/ }) {
            
        }
    }
}