package tech.ishe.smatechpos.views.receipts.widgets

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import tech.ishe.smatechpos.data.models.OrderModel
import tech.ishe.smatechpos.views.utils.Routes
import tech.ishe.smatechpos.views.utils.ScreenDimensions
import java.util.Locale

@Composable
fun ReceiptCard(orderModel: OrderModel, index: Int, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height((ScreenDimensions.screenHeightDp * 0.15).dp)
            .padding(4.dp)
            .clickable {
                navController.navigate(Routes.receiptDetails + "/${Uri.encode(Gson().toJson(orderModel))}")
            }
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 2.dp)
        ) {
            Text(text = "Receipt No: ${index + 1}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
                )
            Row(
                modifier = Modifier.padding(4.dp)
            ) {
                //First section
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((ScreenDimensions.screenWidthDp * 0.5).dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = orderModel.orderCode,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    Text(
                        text = "Date: ${orderModel.orderedOn.substring(0, 10)}",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }

                //Second section
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((ScreenDimensions.screenWidthDp * 0.5).dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    val formattedTotal = String.format(Locale.UK, "%.2f", orderModel.total)
                    val formattedSubTotal = String.format(Locale.UK, "%.2f", orderModel.subTotal)
                    Text(
                        text = "Total: $${formattedTotal}",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    Text(
                        text = "Subtotal: $${formattedSubTotal}",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
    }
}