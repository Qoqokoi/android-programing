package com.qoqokoi.myapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.qoqokoi.myapp.data.local.DeviceEntity
import com.qoqokoi.myapp.data.repository.AppRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: AppRepository,
) : ViewModel() {
    // ViewModel hanya berinteraksi dengan Repositori
    val devices: LiveData<List<DeviceEntity>> = repository.allDevices

    fun manualRefresh() {
        viewModelScope.launch {
            try {
                repository.refreshData()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class MainViewModelFactory(
    private val repository: AppRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
