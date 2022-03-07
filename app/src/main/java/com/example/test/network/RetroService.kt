package com.example.test.network

import com.example.test.model.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("users")
    suspend fun getDataFromApi(@Query("page") query : String): RecyclerList
}