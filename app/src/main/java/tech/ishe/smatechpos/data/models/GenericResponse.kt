package tech.ishe.smatechpos.data.models

data class GenericResponse(
    val `data`: Any,
    val errors: Any,
    val message: String,
    val status: Boolean
)