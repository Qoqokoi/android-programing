package com.qoqokoi.myapp

import android.os.Bundle
import androidx.activity.viewModels // IMPOR WAJIB UNTUK DELEGASI VIEWMODELS
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.qoqokoi.myapp.databinding.ActivityMainBinding
import com.qoqokoi.myapp.ui.AdvancedInventoryAdapter
import com.qoqokoi.myapp.ui.InventoryViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val advancedAdapter = AdvancedInventoryAdapter()
        val manager = GridLayoutManager(this, 2)

        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (advancedAdapter.getItemViewType(position)) {
                    0 -> 2
                    1 -> 1
                    else -> 1
                }
            }
        }

        binding.recyclerViewInventory.apply {
            layoutManager = manager
            adapter = advancedAdapter
        }

        lifecycleScope.launch {
            viewModel.postsState.collectLatest { dataList ->
                advancedAdapter.submitList(dataList)
            }
        }
    }
}
