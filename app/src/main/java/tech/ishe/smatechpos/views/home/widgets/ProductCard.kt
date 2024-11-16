package tech.ishe.smatechpos.views.home.widgets

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import tech.ishe.smatechpos.data.models.ProductModel
import tech.ishe.smatechpos.viewmodels.ProductsViewModel
import tech.ishe.smatechpos.views.utils.ProductImage
import tech.ishe.smatechpos.views.utils.Routes

@Composable
fun ProductCard(product: ProductModel, productsViewModel: ProductsViewModel, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                navController.navigate(Routes.productDetails + "/${Uri.encode(Gson().toJson(product))}")
            }
            .background(MaterialTheme.colorScheme.inverseOnSurface),

        ) {

        Row(horizontalArrangement = Arrangement.SpaceEvenly) {

            ProductImage(product.productSku, productsViewModel)
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
                    text = product.productName,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
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


        }
    }
}
