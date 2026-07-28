package com.qoqokoi.myapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "device_table")
data class DeviceEntity(
    @PrimaryKey val id: String,
    val name: String,
    val status: String,
)
