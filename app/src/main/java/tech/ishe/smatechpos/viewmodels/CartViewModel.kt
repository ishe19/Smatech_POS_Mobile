package tech.ishe.smatechpos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.ishe.smatechpos.data.models.CartItemModel

class CartViewModel : ViewModel() {

    private val _cartList = MutableLiveData<List<CartItemModel>>()
    val cartList: LiveData<List<CartItemModel>> = _cartList

    //Adding to the cart
    fun addToCart(cartItemModel: CartItemModel) {
        val index =
            _cartList.value?.indexOfFirst { it.productModel.productSku == cartItemModel.productModel.productSku }
        if (index != null && index >= 0) {
            val oldItemInList = _cartList.value?.get(index)
            val newQuantity = cartItemModel.quantity + (oldItemInList?.quantity ?: 0)

            updateCartItemQuantity(newQuantity, cartItemModel)
        } else {
            _cartList.value = _cartList.value?.plus(cartItemModel) ?: listOf(cartItemModel)
        }
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

    fun updateCartItemQuantity(newQuantity: Int, cartItemModel: CartItemModel) {
        val updatedCartList = _cartList.value?.toMutableList() ?: return

        val index =
            updatedCartList.indexOfFirst { it.productModel.productSku == cartItemModel.productModel.productSku }

        if (index != -1) {
            val updatedItem = updatedCartList[index].copy(quantity = newQuantity)
            updatedCartList[index] = updatedItem
            _cartList.value = updatedCartList
        }
    }

}