package tech.ishe.smatechpos.data.models

data class OrderModel(
    val deliverFee: Double,
    val orderCode: String,
    val orderedItems: List<OrderedItem>,
    val orderedOn: String,
    val subTotal: Double,
    val total: Double
)