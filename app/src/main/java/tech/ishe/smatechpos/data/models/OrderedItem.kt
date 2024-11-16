package tech.ishe.smatechpos.data.models

data class OrderedItem(
    val description: String,
    val price: Double,
    val productName: String,
    val productSku: String
)