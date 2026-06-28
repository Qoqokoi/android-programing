package com.qoqokoi.myapp.network

import com.qoqokoi.myapp.model.Post
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/google-developer-training/android-kotlin-fundamentals-apps/master/"

interface SimpleService {
    @GET("DevBytesRepository.json")
    suspend fun getNetworkPosts(): List<Post>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object NetworkApi {
    val retrofitService: SimpleService by lazy {
        retrofit.create(SimpleService::class.java)
    }
}
