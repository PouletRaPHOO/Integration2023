package com.example.poteauxrace.network

import com.example.poteauxrace.common.UpdateResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.POST


private const val BASE_URL =
    "localhost:8080"



private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface PotAPIService {
    @GET("update")
    suspend fun getUpdate() : UpdateResponse
    @POST("claim")
    suspend fun postClaim()
}

object PotApi {
    val retrofitService : PotAPIService by lazy {
        retrofit.create(PotAPIService::class.java)
    }
}




