package tech.ishe.smatechpos.views.receipts.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import tech.ishe.smatechpos.data.models.OrderModel
import tech.ishe.smatechpos.views.utils.ScreenDimensions
import java.util.Locale

@Composable
fun ReceiptCard(orderModel: OrderModel) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height((ScreenDimensions.screenHeightDp * 0.15).dp)
            .padding(4.dp)
    ) {
        Row {
            //First section
            Column(
                modifier = Modifier
                    .width((ScreenDimensions.screenWidthDp * 0.5).dp)
            ) {
                Text(
                    text = "Order code: ${orderModel.orderCode}",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Text(
                    text = "Order code: ${orderModel.orderedOn.substring(0, 10)}",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            //Second section
            Column(
                modifier = Modifier
                    .width((ScreenDimensions.screenWidthDp * 0.5).dp)
            ) {
                val formattedTotal = String.format(Locale.UK, "%.2f", orderModel.total)
                val formattedSubTotal = String.format(Locale.UK, "%.2f", orderModel.subTotal)
                Text(
                    text = "Order total: $${formattedTotal}",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Text(
                    text = "Order subtotal: $${formattedSubTotal}",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}