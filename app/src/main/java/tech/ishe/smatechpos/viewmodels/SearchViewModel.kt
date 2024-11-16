package tech.ishe.smatechpos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.ishe.smatechpos.data.models.ProductModel

class SearchViewModel: ViewModel() {

    private val _searchedList = MutableLiveData<List<ProductModel>>()
    val searchedList: LiveData<List<ProductModel>> = _searchedList

    private val _isSearching = MutableLiveData<Boolean>()
    val isSearching : LiveData<Boolean> = _isSearching


    fun searchProducts(searchTerm: String, products : List<ProductModel>){
//        val currentSearchList = _searchedList.value ?: emptyList()
        _searchedList.value = products.filter { it.productName.contains(searchTerm) }
    }

    fun toggleSearching(search: Boolean){
        _isSearching.value = search
        _searchedList.value = emptyList()
    }

}