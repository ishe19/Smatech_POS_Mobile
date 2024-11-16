package tech.ishe.smatechpos.data.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import tech.ishe.smatechpos.data.models.GenericResponse
import tech.ishe.smatechpos.data.models.OrderRequest
import tech.ishe.smatechpos.data.models.ProductsResponseModel

interface PosApi {

    @GET("products/")
    suspend fun getProducts(): Response<ProductsResponseModel>

    @GET("products/{productSku}/image")
    suspend fun getProductImage(
       @Path("productSku") productSku: String
    ): Response<ResponseBody>


    @POST("orders/")
    suspend fun postOrder(
        @Body orderRequest: OrderRequest
    ): Response<GenericResponse>
}