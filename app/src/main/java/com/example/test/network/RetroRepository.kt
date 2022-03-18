package com.example.test.network

import androidx.lifecycle.LiveData
import com.example.test.model.AppDao
import com.example.test.model.User
import com.example.test.model.UserList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(private val retroService: RetroService,
                                          private val appDao: AppDao
) {

    fun getAllRecords(): LiveData<List<User>> {
        return appDao.getAllRecords()
    }

    fun insertRecord(user: User) {
        appDao.insertRecords(user)
    }

    //get the data from github api...
    fun makeApiCall(query: String?) {
        val call: Call<UserList> = retroService.getDataFromAPI(query!!)
        call?.enqueue(object : Callback<UserList>{
            override fun onResponse(
                call: Call<UserList>,
                response: Response<UserList>
            ) {
                if(response.isSuccessful) {
                    appDao.deleteAllRecords()
                    response.body()?.data?.forEach {
                        insertRecord(it)
                    }
                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                //
            }
        })
    }
}