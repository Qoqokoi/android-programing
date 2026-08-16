package com.qoqokoi.librepulse.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LibreNmsApi {

    @GET("api/v0/devices")
    suspend fun getDevices(): LibreDeviceResponse

    // Request kolom status operasional secara eksplisit ke LibreNMS
    @GET("api/v0/devices/{id}/ports")
    suspend fun getPorts(
        @Path("id") deviceId: String,
        @Query("columns") columns: String = "ifName,ifOperStatus,ifAdminStatus,disabled"
    ): LibrePortResponse

    companion object {
        private const val BASE_URL = "http://10.10.17.210:8088/"
        private const val API_TOKEN = "07c411b2a1362b7a7a514a374f854fa0"

        fun create(): LibreNmsApi {
            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("X-Auth-Token", API_TOKEN)
                        .build()
                    chain.proceed(request)
                }
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LibreNmsApi::class.java)
        }
    }
}
