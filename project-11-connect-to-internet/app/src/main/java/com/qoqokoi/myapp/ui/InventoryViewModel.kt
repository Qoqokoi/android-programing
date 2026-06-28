package com.qoqokoi.myapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qoqokoi.myapp.model.DataItem
import com.qoqokoi.myapp.network.NetworkApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InventoryViewModel : ViewModel() {
    private val _postsState = MutableStateFlow<List<DataItem>>(emptyList())
    val postsState: StateFlow<List<DataItem>> = _postsState.asStateFlow()

    init {
        fetchNetworkData()
    }

    private fun fetchNetworkData() {
        viewModelScope.launch {
            try {
                val responseList = NetworkApi.retrofitService.getNetworkPosts()
                val completeList = mutableListOf<DataItem>()
                completeList.add(DataItem.Header)
                completeList.addAll(responseList.map { DataItem.PostItem(it) })
                _postsState.value = completeList

                android.util.Log.d("API_SUCCESS", "Data masuk: ${responseList.size} item")
            } catch (e: Exception) {
                android.util.Log.e("API_ERROR", "Gagal ambil data: ${e.message}")
                e.printStackTrace()
                _postsState.value = listOf(DataItem.Header)
            }
        }
    }
}
