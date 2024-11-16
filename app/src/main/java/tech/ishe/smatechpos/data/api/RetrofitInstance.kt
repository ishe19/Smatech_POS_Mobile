package tech.ishe.smatechpos.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

//    private const val BASE_URL = "http://10.0.2.2:9078/smatech_api/"
    private const val BASE_URL = "https://smatech-pos-api.onrender.com/smatech_api/"

    private fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val posApi: PosApi = getInstance().create(PosApi::class.java)
}