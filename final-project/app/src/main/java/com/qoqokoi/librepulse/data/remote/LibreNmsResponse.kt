package com.qoqokoi.librepulse.data.remote

import com.google.gson.annotations.SerializedName

data class LibreDeviceResponse(
    @SerializedName("status") val status: String,
    @SerializedName("devices") val devices: List<LibreDeviceDto>
)

data class LibreDeviceDto(
    @SerializedName("device_id") val deviceId: String,
    @SerializedName("hostname") val hostname: String,
    @SerializedName("ip") val ip: String?,
    @SerializedName("status") val status: Int
)

data class LibrePortResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("ports") val ports: List<LibrePortDto>?
)

data class LibrePortDto(
    @SerializedName("ifName") val ifName: String?,
    @SerializedName("ifOperStatus") val ifOperStatus: String?,
    @SerializedName("ifAdminStatus") val ifAdminStatus: String?,
    @SerializedName("disabled") val disabled: String?
)
