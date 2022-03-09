package com.example.test.viewmodel
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.network.RetroService
import com.example.test.adapter.RecyclerViewAdapter
import com.example.test.model.User
import com.example.test.model.UserList
import com.example.test.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel(){
    var userListData: MutableLiveData<UserList> = MutableLiveData()
    var recyclerViewAdapter : RecyclerViewAdapter = RecyclerViewAdapter()

    fun getAdapter():  RecyclerViewAdapter{
        return recyclerViewAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAdapterData(data: ArrayList<User>) {
        recyclerViewAdapter.setDataList(data)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<UserList> {
        return userListData
    }

    fun makeAPICall(input : String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromAPI(input)
        call.enqueue(object : Callback<UserList>{
            override fun onFailure(call: Call<UserList>, t: Throwable) {
                userListData.postValue(null)
            }

            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if(response.isSuccessful){
                    userListData.postValue(response.body())
                } else {
                    userListData.postValue(null)
                }
            }
        })
    }

}