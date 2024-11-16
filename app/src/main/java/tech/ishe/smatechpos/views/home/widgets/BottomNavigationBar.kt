package tech.ishe.smatechpos.views.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import tech.ishe.smatechpos.data.models.BottomNavigationItem

val barItems = listOf(
    BottomNavigationItem(
        title = "Home",
        icon = Icons.Rounded.Home,
        route = "home"
    ),

    BottomNavigationItem(
        title = "Cart",
        icon = Icons.Rounded.ShoppingCart,
        route = "cart"
    ),

    BottomNavigationItem(
        title = "Receipts",
        icon = Icons.Rounded.CreditCard,
        route = "receipts"
    ),
)


@Composable
fun BottomNavigationBar(navController: NavHostController) {


}