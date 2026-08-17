package com.qoqokoi.librepulse.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.qoqokoi.librepulse.data.local.AppDatabase
import com.qoqokoi.librepulse.data.remote.LibreNmsApi
import com.qoqokoi.librepulse.data.repository.LibrePulseRepository
import com.qoqokoi.librepulse.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val adapter = PortDetailAdapter()
    private lateinit var repository: LibrePulseRepository
    private lateinit var deviceId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getDatabase(this)
        repository = LibrePulseRepository(db.deviceDao(), db.portDao(), LibreNmsApi.create())

        deviceId = intent.getStringExtra("EXTRA_DEVICE_ID") ?: return finish()
        val sysName = intent.getStringExtra("EXTRA_SYS_NAME") ?: ""
        val ip = intent.getStringExtra("EXTRA_IP") ?: ""
        val isUp = intent.getBooleanExtra("EXTRA_IS_UP", true)

        binding.tvDetailSysName.text = sysName
        binding.tvDetailIp.text = "IP: $ip"
        binding.tvDetailHardwareStatus.text = if (isUp) "ONLINE" else "OFFLINE"

        val bg = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 12f
            setColor(if (isUp) Color.parseColor("#2E7D32") else Color.parseColor("#C62828"))
        }
        binding.tvDetailHardwareStatus.background = bg

        binding.rvPorts.layoutManager = LinearLayoutManager(this)
        binding.rvPorts.adapter = adapter

        if (isUp) {
            repository.getCachedPorts(deviceId).observe(this) { ports ->
                val validPorts = ports.filter { port ->
                    val isDeleted = port.deleted?.trim() in listOf("1", "true")
                    val isDisabled = port.disabled?.trim() in listOf("1", "true")
                    val admin = port.ifAdminStatus?.lowercase()?.trim() ?: ""
                    !isDeleted && !isDisabled && admin != "down" && admin != "2"
                }

                val upCount = validPorts.count { (it.ifOperStatus?.lowercase()?.trim() ?: "") in listOf("up", "1", "testing") }
                val downCount = validPorts.size - upCount

                binding.tvDetailPortSummary.text = "Port: Up: $upCount | Down: $downCount"
                adapter.submitList(validPorts)
            }

            binding.swipeRefreshDetail.setOnRefreshListener {
                refreshPorts()
            }

            refreshPorts()
        } else {
            binding.swipeRefreshDetail.isEnabled = false
            binding.tvDetailPortSummary.text = "Hardware Offline (Port inspection disabled)"
        }
    }

    private fun refreshPorts() {
        binding.swipeRefreshDetail.isRefreshing = true
        lifecycleScope.launch {
            try {
                repository.refreshSingleDevicePorts(deviceId)
            } finally {
                binding.swipeRefreshDetail.isRefreshing = false
            }
        }
    }
}
