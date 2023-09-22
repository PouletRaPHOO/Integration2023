package com.example.poteauxrace.network

import android.util.Log
import com.example.poteauxrace.common.PotClaim
import com.example.poteauxrace.common.UpdateResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


private const val BASE_URL =
    "http://10.0.2.2:8080/"



private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface PotAPIService {
    @GET("update")
    suspend fun getUpdate() : UpdateResponse
    @POST("claim")
    @Headers("Content-Type: application/json")
    suspend fun postClaim(@Body body : RequestBody) : Response<ResponseBody>
}

object PotApi {
    val retrofitService: PotAPIService by lazy {
        retrofit.create(PotAPIService::class.java)

    }
}



