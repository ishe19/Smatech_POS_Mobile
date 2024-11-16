package tech.ishe.smatechpos.data.models

data class OrderRequest(
    val total: Double,
    val subTotal: Double,
    val deliveryFee: Double,
    val products: List<OrderItemRequest>
)
