package com.example.tictactoeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val winningplayer: String
    )


