package tech.ishe.smatechpos.data.models

data class OrderItemRequest(
    val productSku: String,
    val quantity: Int
)
