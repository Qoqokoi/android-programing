package com.qoqokoi.myapp.ui

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.qoqokoi.myapp.data.InventoryDatabase
import com.qoqokoi.myapp.data.InventoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InventoryViewModel(application: Application) : AndroidViewModel(application) {

    private val itemDao = InventoryDatabase.getDatabase(application).itemDao()
    
    val homeUiState: StateFlow<List<InventoryItem>> =
        itemDao.getAllItems().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    var itemName by mutableStateOf("")
    var itemPrice by mutableStateOf("")
    var itemQuantity by mutableStateOf("")

    fun updateUiState(name: String, price: String, qty: String) {
        itemName = name
        itemPrice = price
        itemQuantity = qty
    }

    fun saveItem() {
        val price = itemPrice.toDoubleOrNull() ?: 0.0
        val qty = itemQuantity.toIntOrNull() ?: 0
        if (itemName.isNotBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                itemDao.insert(InventoryItem(name = itemName, price = price, quantity = qty))
            }
            updateUiState("", "", "")
        }
    }
}
