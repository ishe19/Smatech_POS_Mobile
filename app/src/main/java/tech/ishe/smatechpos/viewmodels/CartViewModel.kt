package tech.ishe.smatechpos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.ishe.smatechpos.MainApplication
import tech.ishe.smatechpos.data.models.CartItemModel

class CartViewModel : ViewModel() {


    private val cartDao = MainApplication.cartDatabase.getCartItemDao()
    val cartList: LiveData<List<CartItemModel>> = cartDao.getCartItems()

    // Adding to the cart
    fun addToCart(cartItemModel: CartItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingItem = cartDao.getCartItemBySku(cartItemModel.productSku)
            if (existingItem != null) {
                val newQuantity = cartItemModel.quantity + existingItem.quantity
                cartDao.updateCartItemQuantity(existingItem.id, newQuantity)
            } else {
                cartDao.addCartItem(cartItemModel)
            }
        }
    }

    // Removing from the cart
    fun removeFromCart(cartItemModel: CartItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            cartDao.deleteCartItem(cartItemModel.id)
        }
    }

    // Clearing the cart
    fun clearCart() {
        viewModelScope.launch(Dispatchers.IO) {
            cartDao.deleteAll()
        }
    }

    // Updating the quantity of a cart item
    fun updateCartItemQuantity(newQuantity: Int, cartItemModel: CartItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            cartDao.updateCartItemQuantity(cartItemModel.id, newQuantity)
        }
    }

}