package com.example.test.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class UserList(val data: ArrayList<User>)

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey
    val id: Int,
    val first_name : String,
    val last_name: String,
    val email: String,
    val avatar: String)
