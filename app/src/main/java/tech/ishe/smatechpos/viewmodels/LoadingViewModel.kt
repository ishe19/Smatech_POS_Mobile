package tech.ishe.smatechpos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoadingViewModel: ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    fun isLoading(isLoading: Boolean){
        _loading.value = isLoading
    }
}