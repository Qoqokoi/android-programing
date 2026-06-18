package com.qoqokoi.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.qoqokoi.myapp.ui.InventoryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    InventoryScreen()
                }
            }
        }
    }
}

@Composable
fun InventoryScreen(viewModel: InventoryViewModel = viewModel()) {
    val itemList by viewModel.homeUiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(value = viewModel.itemName, onValueChange = { viewModel.updateUiState(it, viewModel.itemPrice, viewModel.itemQuantity) }, label = { Text("Nama Barang") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = viewModel.itemPrice, onValueChange = { viewModel.updateUiState(viewModel.itemName, it, viewModel.itemQuantity) }, label = { Text("Harga") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = viewModel.itemQuantity, onValueChange = { viewModel.updateUiState(viewModel.itemName, viewModel.itemPrice, it) }, label = { Text("Stok") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
        
        Button(onClick = { viewModel.saveItem() }, modifier = Modifier.fillMaxWidth()) {
            Text("Simpan Data ke Room")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Daftar Barang di Database:", style = MaterialTheme.typography.titleMedium)
        
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(itemList) { item ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = item.name, style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Harga: Rp${item.price} | Stok: ${item.quantity}")
                    }
                }
            }
        }
    }
}
