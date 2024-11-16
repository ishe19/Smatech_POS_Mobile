package tech.ishe.smatechpos.data.models

data class ProductsResponseModel(
    val `data`: List<ProductModel>,
    val errors: Any,
    val message: Any,
    val status: Boolean
)