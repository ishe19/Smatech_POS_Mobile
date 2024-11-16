package tech.ishe.smatechpos.viewmodels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.ishe.smatechpos.data.api.RetrofitInstance
import tech.ishe.smatechpos.data.models.ProductModel
import tech.ishe.smatechpos.data.models.NetworkResponse

class ProductsViewModel : ViewModel() {
    private val posApi = RetrofitInstance.posApi
    private val _productsResult = MutableLiveData<NetworkResponse<List<ProductModel>>>()
    val productsResult: LiveData<NetworkResponse<List<ProductModel>>> = _productsResult

    fun getProducts() {
        _productsResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = posApi.getProducts()

                if (response.isSuccessful) {
                    response.body()?.let {
                        _productsResult.value = NetworkResponse.Success(it.data)
                    }
                } else {
                    Log.e("Fetch Products Error", response.message())
                    _productsResult.value = NetworkResponse.Error("Failed to get products...")

                }

            } catch (e: Exception) {
                e.message?.let { Log.e("Fetch Products Error", it) }
                _productsResult.value = NetworkResponse.Error("Failed to get products...")
            }
        }
    }


    private val _productImageResult = MutableLiveData<NetworkResponse<Bitmap>>()
    val productImageResult: LiveData<NetworkResponse<Bitmap>> = _productImageResult

    private val _productImages = mutableMapOf<String, MutableLiveData<NetworkResponse<Bitmap>>>()

    fun getProductImage(productSku: String) {
        if (!_productImages.containsKey(productSku)) {
            _productImages[productSku] = MutableLiveData(NetworkResponse.Loading)
        }

        viewModelScope.launch {
            try {
                val response = posApi.getProductImage(productSku)
                if (response.isSuccessful && response.body() != null) {
                    val inputStream = response.body()!!.byteStream()
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    _productImages[productSku]?.postValue(NetworkResponse.Success(bitmap))
                } else {
                    _productImages[productSku]?.postValue(NetworkResponse.Error("Failed to fetch image"))
                }
            } catch (e: Exception) {
                _productImages[productSku]?.postValue(NetworkResponse.Error("Error: ${e.message}"))
            }
        }
    }

    fun getProductImageState(productSku: String): LiveData<NetworkResponse<Bitmap>> {
        return _productImages.getOrPut(productSku) { MutableLiveData(NetworkResponse.Loading) }
    }



}