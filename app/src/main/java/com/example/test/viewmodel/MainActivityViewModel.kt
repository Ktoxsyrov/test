package com.example.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.model.User
import com.example.test.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RetroRepository)
    : ViewModel(){

    fun getAllRepositoryList(): LiveData<List<User>> {
        return repository.getAllRecords()
    }

    fun makeApiCall() {
        repository.makeApiCall("2")
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
//    fun getUser(id: Int){
//        repository.getRecord(id)
//    }
}