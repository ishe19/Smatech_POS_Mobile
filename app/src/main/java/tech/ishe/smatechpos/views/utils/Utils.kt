package tech.ishe.smatechpos.views.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import tech.ishe.smatechpos.data.models.BottomNavigationItem

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