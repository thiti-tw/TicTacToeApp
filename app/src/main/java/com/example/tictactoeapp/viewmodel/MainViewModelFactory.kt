package com.example.tictactoeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoeapp.repository.HistoryRepository

class MainViewModelFactory (
    private val repositosy: HistoryRepository
        ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repositosy) as T
    }
        }
