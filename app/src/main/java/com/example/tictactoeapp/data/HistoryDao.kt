package com.example.tictactoeapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tictactoeapp.model.History

@Dao
//Contains methods used for access database
interface HistoryDao  {
    @Query("SELECT * FROM history ORDER BY id DESC")
    fun getAll(): LiveData<List<History>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertHistory(history: History)

    @Delete
    fun deleteHistory(history: History)

}