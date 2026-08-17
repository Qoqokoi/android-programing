package com.qoqokoi.librepulse.data.remote

import com.google.gson.annotations.SerializedName

data class LibreDeviceResponse(
    @SerializedName("status") val status: String,
    @SerializedName("devices") val devices: List<LibreDeviceDto>
)

data class LibreDeviceDto(
    @SerializedName("device_id") val deviceId: String,
    @SerializedName("hostname") val hostname: String,
    @SerializedName("sysName", alternate = ["sys_name"]) val sysName: String?,
    @SerializedName("ip") val ip: String?,
    @SerializedName("status") val status: Int
)

data class LibrePortResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("ports") val ports: List<LibrePortDto>?
)

data class LibrePortDto(
    @SerializedName("port_id") val portId: String?,
    @SerializedName("ifIndex") val ifIndex: String?,
    @SerializedName("ifName") val ifName: String?,
    @SerializedName("ifDescr") val ifDescr: String?,
    @SerializedName("ifAlias") val ifAlias: String?,
    @SerializedName("ifOperStatus") val ifOperStatus: String?,
    @SerializedName("ifAdminStatus") val ifAdminStatus: String?,
    @SerializedName("ifSpeed") val ifSpeed: Long?,
    @SerializedName("disabled") val disabled: String?,
    @SerializedName("deleted") val deleted: String?
)

data class LibreIpResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("addresses", alternate = ["ip", "ips", "data"]) val addresses: List<LibreIpDto>?
)

data class LibreIpDto(
    @SerializedName("ipv4_address", alternate = ["ip_address", "address", "ipv4", "ifIP"]) val ipv4Address: String?,
    @SerializedName("ipv4_prefixlen", alternate = ["prefix", "cidr", "mask", "subnet"]) val ipv4Prefixlen: String?,
    @SerializedName("port_id", alternate = ["port", "id", "interface_id"]) val portId: String?,
    @SerializedName("ifIndex") val ifIndex: String?,
    @SerializedName("ifName", alternate = ["interface", "name"]) val ifName: String?
)
