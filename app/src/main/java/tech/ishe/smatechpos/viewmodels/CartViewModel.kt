package tech.ishe.smatechpos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.ishe.smatechpos.data.models.CartItemModel

class CartViewModel : ViewModel() {

    private val _cartList = MutableLiveData<List<CartItemModel>>()
    val cartList : LiveData<List<CartItemModel>> = _cartList

    //Adding to the cart
    fun addToCart(cartItemModel: CartItemModel) {
        val currentList = _cartList.value ?: emptyList()
        _cartList.value = currentList + cartItemModel
    }

    // Removing from the cart
    fun removeFromCart(cartItemModel: CartItemModel) {
        val currentList = _cartList.value ?: emptyList()
        _cartList.value = currentList.filter { it != cartItemModel }
    }

    // Clearing the cart
    fun clearCart() {
        _cartList.value = emptyList()
    }
}