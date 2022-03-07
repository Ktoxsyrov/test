package com.example.test.model

data class RecyclerList(val data: ArrayList<RecyclerData>)
data class RecyclerData(
    val first_name : String,
    val last_name: String,
    val email: String,
    val avatar: String)
