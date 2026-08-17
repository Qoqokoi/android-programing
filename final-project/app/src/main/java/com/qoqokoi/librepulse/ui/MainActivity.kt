package com.qoqokoi.librepulse.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.qoqokoi.librepulse.data.local.AppDatabase
import com.qoqokoi.librepulse.data.remote.LibreNmsApi
import com.qoqokoi.librepulse.data.repository.LibrePulseRepository
import com.qoqokoi.librepulse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences

    private val viewModel: DashboardViewModel by viewModels {
        val db = AppDatabase.getDatabase(this)
        DashboardViewModelFactory(
            LibrePulseRepository(
                db.deviceDao(),
                db.portDao(),
                LibreNmsApi.create()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = getSharedPreferences("librepulse_settings", Context.MODE_PRIVATE)
        val isDarkMode = prefs.getBoolean("is_dark_mode", isSystemDarkMode())
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnThemeToggle.setOnClickListener {
            val currentlyDark = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
            val nextMode = !currentlyDark
            prefs.edit().putBoolean("is_dark_mode", nextMode).apply()
            
            AppCompatDelegate.setDefaultNightMode(
                if (nextMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        val adapter = DashboardAdapter { device ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_DEVICE_ID", device.deviceId)
                putExtra("EXTRA_SYS_NAME", device.sysName)
                putExtra("EXTRA_HOSTNAME", device.hostname)
                putExtra("EXTRA_IP", device.ip)
                putExtra("EXTRA_IS_UP", device.status.uppercase() == "UP")
            }
            startActivity(intent)
        }

        binding.rvDevices.layoutManager = LinearLayoutManager(this)
        binding.rvDevices.adapter = adapter

        viewModel.devices.observe(this) { devices ->
            adapter.submitList(devices)
            val upCount = devices.count { it.status.uppercase() == "UP" }
            val downCount = devices.size - upCount
            binding.tvDeviceUpCount.text = upCount.toString()
            binding.tvDeviceDownCount.text = downCount.toString()
        }

        viewModel.isLoading.observe(this) { loading ->
            binding.swipeRefresh.isRefreshing = loading
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.refresh()
    }

    private fun isSystemDarkMode(): Boolean {
        return (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    }
}
