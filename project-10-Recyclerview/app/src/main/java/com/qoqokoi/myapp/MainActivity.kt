package com.qoqokoi.myapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.qoqokoi.myapp.databinding.ActivityMainBinding
import com.qoqokoi.myapp.model.Barang
import com.qoqokoi.myapp.model.DataItem
import com.qoqokoi.myapp.ui.AdvancedInventoryAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val advancedAdapter = AdvancedInventoryAdapter()
        val manager = GridLayoutManager(this, 3)

        manager.spanSizeLookup =
            object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int =
                    when (advancedAdapter.getItemViewType(position)) {
                        0 -> 3

                        // Header mengambil 3 span penuh
                        1 -> 1

                        // Item biasa mengambil 1 span
                        else -> 1
                    }
            }

        binding.recyclerViewInventory.apply {
            layoutManager = manager
            adapter = advancedAdapter
        }

        val dummyData =
            listOf(
                DataItem.Header,
                DataItem.BarangItem(Barang(1, "TP-Link LS108G Switch", 185000, 14)),
                DataItem.BarangItem(Barang(2, "MikroTek RB5009UG", 2450000, 3)),
                DataItem.BarangItem(Barang(3, "Router RB260GS", 650000, 8)),
                DataItem.BarangItem(Barang(4, "Kabel Fiber Optic 50m", 120000, 25)),
                DataItem.BarangItem(Barang(5, "Optical Power Meter", 350000, 5)),
            )

        advancedAdapter.submitList(dummyData)
    }
}
