package com.example.test.network

import com.example.test.model.UserList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    //repositories?q=newyork
    @GET("users")
    fun getDataFromAPI(@Query("page")query : String):Call<UserList>
}