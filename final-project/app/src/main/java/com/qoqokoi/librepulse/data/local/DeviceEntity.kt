package com.qoqokoi.librepulse.data.local

import androidx.room.*
import androidx.lifecycle.LiveData

@Entity(tableName = "devices")
data class DeviceEntity(
    @PrimaryKey val deviceId: String,
    val hostname: String,
    val ip: String,
    val status: String,
    val portsUp: Int?,
    val portsDown: Int?
)

@Dao
interface DeviceDao {
    @Query("SELECT * FROM devices")
    fun getAllDevices(): LiveData<List<DeviceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevices(devices: List<DeviceEntity>)
}
