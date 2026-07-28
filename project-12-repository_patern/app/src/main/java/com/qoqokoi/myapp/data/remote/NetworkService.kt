package com.qoqokoi.myapp.data.remote

import com.qoqokoi.myapp.data.local.DeviceEntity
import retrofit2.http.GET

interface NetworkService {
    @GET("devices")
    suspend fun getDevices(): List<DeviceEntity>
}
