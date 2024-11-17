package tech.ishe.smatechpos.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import tech.ishe.smatechpos.data.models.CartItemModel

@Dao
interface CartItemDao {

    @Query("SELECT * FROM CARTITEMMODEL")
    fun getCartItems() : LiveData<List<CartItemModel>>

    @Insert
    fun addCartItem(cartItemModel: CartItemModel)

    @Query("DELETE FROM CARTITEMMODEL WHERE id = :id")
    fun deleteCartItem(id: Int)

    @Query("DELETE FROM CARTITEMMODEL")
    fun deleteAll()

    @Query("UPDATE CartItemModel SET quantity = :newQuantity WHERE id = :id")
    fun updateCartItemQuantity(id: Int, newQuantity: Int)

    @Query("SELECT * FROM CartItemModel WHERE productSku = :productSku")
    fun getCartItemBySku(productSku: String): CartItemModel?

}