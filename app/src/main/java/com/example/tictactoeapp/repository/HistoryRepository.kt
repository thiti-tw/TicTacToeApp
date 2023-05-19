package com.example.tictactoeapp.repository

import androidx.lifecycle.LiveData
import com.example.tictactoeapp.data.HistoryDao
import com.example.tictactoeapp.model.History

class HistoryRepository(private val historyDao: HistoryDao) {

    val readAllData: LiveData<List<History>> = historyDao.getAll()

    suspend fun insertHistory(history: History) {
        historyDao.insertHistory(history)
    }

}