package com.example.travel.api

import com.example.travel.api.data.ATTR001_Rs
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
}