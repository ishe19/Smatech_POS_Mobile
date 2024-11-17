package tech.ishe.smatechpos.views.receipts.widgets


import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import tech.ishe.smatechpos.data.models.OrderedItem
import tech.ishe.smatechpos.viewmodels.ProductsViewModel
import tech.ishe.smatechpos.views.utils.ProductImage
import tech.ishe.smatechpos.views.utils.ScreenDimensions

@Composable
fun OrderedItemCard(orderItemModel: OrderedItem) {

    val context = LocalContext.current
    val productsViewModel: ProductsViewModel =
        ViewModelProvider(context as ComponentActivity)[ProductsViewModel::class.java]

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height((ScreenDimensions.screenHeightDp * 0.13).dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface),

        ) {
        val boxWithConstraintsScope = this
        val width = boxWithConstraintsScope.maxWidth / 3


        Row(horizontalArrangement = Arrangement.SpaceEvenly) {

            Box(modifier = Modifier.width(width - 40.dp)) {
                ProductImage(orderItemModel.productSku, productsViewModel)
            }

            Spacer(
                modifier = Modifier
                    .width(16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                Text(
                    text = orderItemModel.productName,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Price: $${orderItemModel.price}",
                        fontWeight = FontWeight.Medium,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 14.sp,
                    )

                    Text(
                        text = "Quantity: ${orderItemModel.quantity}",
                        fontWeight = FontWeight.Medium,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 14.sp,
                    )

                    Spacer(
                        modifier = Modifier
                            .width(5.dp)
                    )
                }
            }


        }
    }
}
