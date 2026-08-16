package com.qoqokoi.librepulse.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.qoqokoi.librepulse.data.local.DeviceDao
import com.qoqokoi.librepulse.data.local.DeviceEntity
import com.qoqokoi.librepulse.data.remote.LibreNmsApi

class LibrePulseRepository(
    private val deviceDao: DeviceDao,
    private val api: LibreNmsApi
) {
    val allDevices: LiveData<List<DeviceEntity>> = deviceDao.getAllDevices()

    suspend fun refreshData() {
        try {
            val response = api.getDevices()
            val entities = response.devices.map { dto ->
                val isUp = dto.status == 1
                var calculatedPortsUp: Int? = null
                var calculatedPortsDown: Int? = null

                if (isUp) {
                    val portResponse = runCatching { api.getPorts(dto.deviceId) }.getOrNull()
                    val portList = portResponse?.ports ?: emptyList()

                    if (portList.isNotEmpty()) {
                        var upCount = 0
                        var downCount = 0

                        for (port in portList) {
                            val oper = port.ifOperStatus?.lowercase()?.trim() ?: ""
                            val admin = port.ifAdminStatus?.lowercase()?.trim() ?: ""
                            val disabled = port.disabled?.trim() ?: "0"

                            // Abaikan port yang shutdown admin
                            if (disabled == "1" || admin == "down" || admin == "2") {
                                continue
                            }

                            if (oper == "up" || oper == "1" || oper == "testing") {
                                upCount++
                            } else {
                                downCount++
                            }
                        }

                        calculatedPortsUp = upCount
                        calculatedPortsDown = downCount
                        Log.d("LibrePulse_API", "REAL STATUS -> ${dto.hostname} (${dto.deviceId}): UP=$upCount, DOWN=$downCount")
                    } else {
                        calculatedPortsUp = 0
                        calculatedPortsDown = 0
                    }
                } else {
                    // Dead-Host Safety Handler
                    calculatedPortsUp = null
                    calculatedPortsDown = null
                }

                DeviceEntity(
                    deviceId = dto.deviceId,
                    hostname = dto.hostname,
                    ip = dto.ip ?: dto.hostname,
                    status = if (isUp) "UP" else "DOWN",
                    portsUp = calculatedPortsUp,
                    portsDown = calculatedPortsDown
                )
            }
            deviceDao.insertDevices(entities)
            Log.d("LibrePulse_API", "BERHASIL SYNC ${entities.size} DEVICE KE ROOM DB")
        } catch (e: Exception) {
            Log.e("LibrePulse_API", "GAGAL SYNC: ${e.localizedMessage}", e)
        }
    }
}
