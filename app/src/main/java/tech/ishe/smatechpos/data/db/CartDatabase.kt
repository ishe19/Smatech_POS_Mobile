package tech.ishe.smatechpos.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.ishe.smatechpos.data.models.CartItemModel

@Database(entities = [CartItemModel::class], version = 1, exportSchema=false)
abstract class CartDatabase: RoomDatabase() {

    companion object{
        const val NAME = "Cart_DB"
    }

    abstract fun getCartItemDao() : CartItemDao
}