package com.example.test.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AppDao {

    @Query("SELECT * FROM users_table")
    fun getAllRecords(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(user: User)

//    @Query("SELECT * FROM users_table WHERE id =:id")
//    fun getUserById(id:Int): User

    @Query("DELETE FROM users_table")
    fun deleteAllRecords()

    @Delete()
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)
}