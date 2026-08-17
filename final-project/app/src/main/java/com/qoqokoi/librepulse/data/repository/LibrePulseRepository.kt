package com.qoqokoi.librepulse.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.qoqokoi.librepulse.data.local.DeviceDao
import com.qoqokoi.librepulse.data.local.DeviceEntity
import com.qoqokoi.librepulse.data.local.PortDao
import com.qoqokoi.librepulse.data.local.PortEntity
import com.qoqokoi.librepulse.data.remote.LibreIpDto
import com.qoqokoi.librepulse.data.remote.LibreNmsApi
import com.qoqokoi.librepulse.data.remote.LibrePortDto

class LibrePulseRepository(
    private val deviceDao: DeviceDao,
    private val portDao: PortDao,
    private val api: LibreNmsApi
) {
    val allDevices: LiveData<List<DeviceEntity>> = deviceDao.getAllDevices()

    fun getCachedPorts(deviceId: String): LiveData<List<PortEntity>> {
        return portDao.getPortsByDeviceId(deviceId)
    }

    private fun filterAndMapPorts(
        portList: List<LibrePortDto>,
        ipList: List<LibreIpDto>,
        deviceId: String
    ): List<PortEntity> {
        val ipByPortId = mutableMapOf<String, String>()
        val ipByIfName = mutableMapOf<String, String>()

        ipList.forEach { ipDto ->
            val ip = ipDto.ipv4Address?.trim()
            val prefix = ipDto.ipv4Prefixlen?.trim() ?: "24"
            if (!ip.isNullOrBlank()) {
                val fullIp = "$ip/$prefix"
                ipDto.portId?.let { ipByPortId[it] = fullIp }
                ipDto.ifName?.let { ipByIfName[it.lowercase()] = fullIp }
            }
        }

        // Cari apakah hardware ini memiliki interface Bridge aktif
        val detectedBridge = portList.firstOrNull { 
            (it.ifName?.lowercase()?.contains("bridge") == true || it.ifDescr?.lowercase()?.contains("bridge") == true)
        }?.ifName

        return portList.filter { port ->
            val isDeleted = port.deleted?.trim() in listOf("1", "true")
            val isDisabled = port.disabled?.trim() in listOf("1", "true")
            val admin = port.ifAdminStatus?.lowercase()?.trim() ?: ""
            !isDeleted && !isDisabled && admin != "down" && admin != "2"
        }.map { p ->
            val name = p.ifName ?: "port"
            val directIp = ipByPortId[p.portId] ?: ipByIfName[name.lowercase()]

            val finalIp = when {
                !directIp.isNullOrBlank() -> directIp
                detectedBridge != null && !name.lowercase().contains("bridge") -> "Bridged ($detectedBridge)"
                else -> null
            }

            PortEntity(
                deviceId = deviceId,
                ifName = name,
                ipAddress = finalIp,
                ifDescr = p.ifDescr,
                ifAlias = p.ifAlias,
                ifOperStatus = p.ifOperStatus,
                ifAdminStatus = p.ifAdminStatus,
                ifSpeed = p.ifSpeed,
                disabled = p.disabled,
                deleted = p.deleted
            )
        }
    }

    suspend fun refreshData() {
        try {
            val response = api.getDevices()
            val entities = response.devices.map { dto ->
                val isUp = dto.status == 1
                var calculatedPortsUp: Int? = null
                var calculatedPortsDown: Int? = null

                if (isUp) {
                    val portList = runCatching { api.getPorts(dto.deviceId).ports }.getOrNull() ?: emptyList()
                    val ipList = runCatching { api.getDeviceIps(dto.deviceId).addresses }.getOrNull() ?: emptyList()
                    val validPorts = filterAndMapPorts(portList, ipList, dto.deviceId)

                    portDao.updateDevicePorts(dto.deviceId, validPorts)

                    val upCount = validPorts.count { (it.ifOperStatus?.lowercase()?.trim() ?: "") in listOf("up", "1", "testing") }
                    val downCount = validPorts.size - upCount

                    calculatedPortsUp = upCount
                    calculatedPortsDown = downCount
                } else {
                    portDao.deletePortsByDeviceId(dto.deviceId)
                }

                val finalSysName = if (!dto.sysName.isNullOrBlank()) dto.sysName else dto.hostname

                DeviceEntity(
                    deviceId = dto.deviceId,
                    sysName = finalSysName,
                    hostname = dto.hostname,
                    ip = dto.ip ?: dto.hostname,
                    status = if (isUp) "UP" else "DOWN",
                    portsUp = calculatedPortsUp,
                    portsDown = calculatedPortsDown
                )
            }
            deviceDao.insertDevices(entities)
            Log.d("LibrePulse_API", "DATABASE SYNC & SMART IP BRIDGE MAPPER SELESAI")
        } catch (e: Exception) {
            Log.e("LibrePulse_API", "GAGAL SYNC: ${e.localizedMessage}", e)
        }
    }

    suspend fun refreshSingleDevicePorts(deviceId: String) {
        try {
            val portList = api.getPorts(deviceId).ports ?: emptyList()
            val ipList = runCatching { api.getDeviceIps(deviceId).addresses }.getOrNull() ?: emptyList()
            val validPorts = filterAndMapPorts(portList, ipList, deviceId)
            portDao.updateDevicePorts(deviceId, validPorts)
        } catch (e: Exception) {
            Log.w("LibrePulse_API", "Offline mode aktif untuk device $deviceId")
        }
    }
}
