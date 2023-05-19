package com.example.tictactoeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tictactoeapp.data.HistoryDatabase
import com.example.tictactoeapp.model.History
import com.example.tictactoeapp.repository.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    public val readAllData: LiveData<List<History>>
    private val repository: HistoryRepository

    init {
        val historyDao = HistoryDatabase.getDatabase(application).historyDao()
        repository = HistoryRepository(historyDao)
        readAllData = repository.readAllData
    }

    fun addHistory(history: History) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHistory(history)
        }
    }
}