package com.qoqokoi.myapp.data.repository

import androidx.lifecycle.LiveData
import com.qoqokoi.myapp.data.local.DeviceDao
import com.qoqokoi.myapp.data.local.DeviceEntity
import com.qoqokoi.myapp.data.remote.NetworkService

class AppRepository(
    private val deviceDao: DeviceDao,
    private val networkService: NetworkService,
) {
    // Room DB sebagai Single Source of Truth
    val allDevices: LiveData<List<DeviceEntity>> = deviceDao.getAllDevices()

    suspend fun refreshData() {
        val devicesToInsert =
            try {
                // Coba ambil dari network
                networkService.getDevices()
            } catch (e: Exception) {
                // Jika network 404/error/offline, gunakan fallback mock data
                listOf(
                    DeviceEntity("1", "Switch-Lab-01", "UP"),
                    DeviceEntity("2", "RB-Core-Router", "UP"),
                    DeviceEntity("3", "Switch-Access-02", "DOWN"),
                )
            }

        // Simpan data ke database Room lokal
        deviceDao.insertAll(devicesToInsert)
    }
}
