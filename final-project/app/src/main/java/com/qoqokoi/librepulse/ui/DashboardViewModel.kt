package com.qoqokoi.librepulse.ui

import androidx.lifecycle.*
import com.qoqokoi.librepulse.data.local.DeviceEntity
import com.qoqokoi.librepulse.data.repository.LibrePulseRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: LibrePulseRepository) : ViewModel() {

    val devices: LiveData<List<DeviceEntity>> = repository.allDevices

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun refresh() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                repository.refreshData()
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}

class DashboardViewModelFactory(private val repository: LibrePulseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
