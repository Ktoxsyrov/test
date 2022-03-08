package com.example.test.model

import androidx.room.Dao
import androidx.room.Delete
import retrofit2.http.Query

@Dao
interface UserDao {


   // @Query("SELECT * FROM users_table ORDER BY id")
    fun getAllUsers(): List<User>

    @Delete
    suspend fun deleteUser(user: User)

}