package com.example.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.test.model.User
import com.example.test.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
}