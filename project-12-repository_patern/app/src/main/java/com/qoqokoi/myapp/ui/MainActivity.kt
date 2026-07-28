package com.qoqokoi.myapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.qoqokoi.myapp.data.local.AppDatabase
import com.qoqokoi.myapp.data.remote.ApiClient
import com.qoqokoi.myapp.data.repository.AppRepository
import com.qoqokoi.myapp.databinding.ActivityMainBinding
import com.qoqokoi.myapp.worker.RefreshDataWorker

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = AppDatabase.getDatabase(this)
        val repository = AppRepository(database.deviceDao(), ApiClient.networkService)
        val factory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        viewModel.devices.observe(this) { deviceList ->
            if (deviceList.isEmpty()) {
                binding.tvStatus.text = "Data Kosong (Menunggu Sync/Network)..."
            } else {
                val dataText = deviceList.joinToString("\n") { "${it.name} - ${it.status}" }
                binding.tvStatus.text = "Data dari Room Cache:\n\n$dataText"
            }
        }

        // Eksekusi WorkManager secara instan untuk pengujian Logcat
        binding.btnRefresh.setOnClickListener {
            val testWorkRequest = OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
            WorkManager.getInstance(this).enqueue(testWorkRequest)
        }
    }
}
