package tech.ishe.smatechpos.views.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import tech.ishe.smatechpos.data.models.BottomNavigationItem
import tech.ishe.smatechpos.data.models.OrderModel
import tech.ishe.smatechpos.views.receipts.ReceiptDetailsScreen
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

fun getGradient(
    startColor: Color,
    endColor: Color
): Brush {
    return Brush.horizontalGradient(
        colors = listOf(startColor, endColor)
    )
}

val barItems = listOf(
    BottomNavigationItem(
        title = "Home",
        icon = Icons.Rounded.Home
    ),

    BottomNavigationItem(
        title = "Cart",
        icon = Icons.Rounded.ShoppingCart
    ),

    BottomNavigationItem(
        title = "Receipts",
        icon = Icons.Rounded.CreditCard
    ),
)

@Composable
fun LoadingOverlay(isLoading: Boolean) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}




