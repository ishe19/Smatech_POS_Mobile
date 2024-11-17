package tech.ishe.smatechpos.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CartItemModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val productDescription: String,
    val price: Double,
    val productName: String,
    val productSku: String,
    val quantity: Int
)
