package tech.ishe.smatechpos.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.ishe.smatechpos.data.api.RetrofitInstance
import tech.ishe.smatechpos.data.models.GenericResponse
import tech.ishe.smatechpos.data.models.NetworkResponse
import tech.ishe.smatechpos.data.models.OrderModel
import tech.ishe.smatechpos.data.models.OrderRequest

class OrdersViewModel : ViewModel() {

    private val posApi = RetrofitInstance.posApi
    private val _orderPostResult = MutableLiveData<NetworkResponse<GenericResponse>?>()
    val orderPostResult: LiveData<NetworkResponse<GenericResponse>?> = _orderPostResult

    fun placeOrder(orderRequest: OrderRequest) {
        _orderPostResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = posApi.postOrder(orderRequest)

                if (response.isSuccessful) {
                    Log.i("RESPONSE", response.body().toString())
                    response.body()?.let {
                        _orderPostResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    _orderPostResult.value =
                        NetworkResponse.Error("Failed to place order at the moment, please try again later")
                }

            } catch (e: Exception) {
                e.message?.let { Log.e("Post Order Error", it) }
                _orderPostResult.value =
                    NetworkResponse.Error("Failed to place order at the moment, please try again later")
            }
        }
    }


    fun clearResult() {
        _orderPostResult.value = null
    }

    private val _ordersResult = MutableLiveData<NetworkResponse<List<OrderModel>>>()
    val ordersResult: LiveData<NetworkResponse<List<OrderModel>>> = _ordersResult

    fun getOrders() {
        _ordersResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = posApi.getOrders()

                if (response.isSuccessful) {
                    response.body()?.let {
                        _ordersResult.value = NetworkResponse.Success(it.data)
                    }
                } else {
                    Log.e("Failure response", response.toString())
                    _ordersResult.value =
                        NetworkResponse.Error("Failed to load orders at the moment...")
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("Get Orders Error", it) }
                _ordersResult.value =
                    NetworkResponse.Error("Failed to load orders at the moment...")

            }
        }
    }

}