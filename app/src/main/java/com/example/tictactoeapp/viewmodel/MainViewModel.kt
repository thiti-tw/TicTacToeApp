package com.example.tictactoeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoeapp.model.History
import com.example.tictactoeapp.repository.HistoryRepository
import kotlinx.coroutines.launch

// provide data to the UI
class MainViewModel(private val repository: HistoryRepository): ViewModel() {

     val myResponse: MutableLiveData<List<History>> = MutableLiveData()

//    fun getHistory() {
//        viewModelScope.launch {
//            val response = repository.readAllData
//            myResponse.value = response
//        }
//    }
}