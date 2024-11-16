package tech.ishe.smatechpos.data.models

data class OrdersResponseModel(
    val `data`: List<OrderModel>,
    val errors: Any,
    val message: Any,
    val status: Boolean
)