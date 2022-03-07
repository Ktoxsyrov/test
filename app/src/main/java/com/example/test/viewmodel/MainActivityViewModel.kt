package com.example.test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.model.RecyclerList
import com.example.test.network.RetroInstance
import com.example.test.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    var recyclerListLiveData : MutableLiveData<RecyclerList> = MutableLiveData()

    fun getRecyclerListObserver(): MutableLiveData<RecyclerList> {
        return recyclerListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response  = retroInstance.getDataFromApi("2")
            recyclerListLiveData.postValue(response)
        }
    }
}