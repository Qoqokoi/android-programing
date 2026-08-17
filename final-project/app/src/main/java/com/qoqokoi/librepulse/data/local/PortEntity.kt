package com.qoqokoi.librepulse.data.local

import androidx.room.*
import androidx.lifecycle.LiveData

@Entity(
    tableName = "ports",
    primaryKeys = ["deviceId", "ifName"]
)
data class PortEntity(
    val deviceId: String,
    val ifName: String,
    val ipAddress: String?,
    val ifDescr: String?,
    val ifAlias: String?,
    val ifOperStatus: String?,
    val ifAdminStatus: String?,
    val ifSpeed: Long?,
    val disabled: String?,
    val deleted: String?
)

@Dao
abstract class PortDao {
    @Query("SELECT * FROM ports WHERE deviceId = :deviceId")
    abstract fun getPortsByDeviceId(deviceId: String): LiveData<List<PortEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPorts(ports: List<PortEntity>)

    @Query("DELETE FROM ports WHERE deviceId = :deviceId")
    abstract suspend fun deletePortsByDeviceId(deviceId: String)

    @Query("DELETE FROM ports")
    abstract suspend fun clearAllPorts()

    @Transaction
    open suspend fun updateDevicePorts(deviceId: String, ports: List<PortEntity>) {
        deletePortsByDeviceId(deviceId)
        if (ports.isNotEmpty()) {
            insertPorts(ports)
        }
    }
}
