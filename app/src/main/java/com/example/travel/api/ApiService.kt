package com.example.travel.api

import com.example.travel.api.model.ATTR001_Rs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*

interface ApiService {

    @Headers("accept: application/json")
    @GET("/open-api/{lang}/Attractions/All")
    suspend fun getAttractions(
        @Path("lang") lang: String = "zh-tw",
        @Query("page") page: Int? = 1,
    ): ATTR001_Rs

    companion object {
        private const val BASE_URL = "https://www.travel.taipei"
        fun create(): ApiService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .retryOnConnectionFailure(true)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}