package com.qoqokoi.librepulse.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.qoqokoi.librepulse.data.local.AppDatabase
import com.qoqokoi.librepulse.data.remote.LibreNmsApi
import com.qoqokoi.librepulse.data.repository.LibrePulseRepository
import com.qoqokoi.librepulse.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DashboardAdapter()
        binding.rvDevices.layoutManager = LinearLayoutManager(this)
        binding.rvDevices.adapter = adapter

        val database = AppDatabase.getDatabase(this)
        val repository = LibrePulseRepository(database.deviceDao(), LibreNmsApi.create())

        repository.allDevices.observe(this) { devices ->
            adapter.submitList(devices)
            
            val upCount = devices.count { it.status.uppercase() == "UP" }
            val downCount = devices.count { it.status.uppercase() == "DOWN" }
            binding.tvGlobalUp.text = upCount.toString()
            binding.tvGlobalDown.text = downCount.toString()
        }

        lifecycleScope.launch {
            repository.refreshData()
        }
    }
}
