package com.example.tictactoeapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoeapp.R
import com.example.tictactoeapp.adapter.HistoryAdapter
import com.example.tictactoeapp.repository.HistoryRepository
import com.example.tictactoeapp.viewmodel.HistoryViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tictactoeapp.model.History

class HistoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var mHistoryViewModel: HistoryViewModel
    private lateinit var historyList: List<History>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        //init database
        mHistoryViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        //recyclerview
        recyclerView = findViewById(R.id.hist_Recyclerview)
        val adapter = HistoryAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        //get data from database
        mHistoryViewModel.readAllData.observe(this, Observer { response ->
            if(response.isNotEmpty()) {
                historyList = response
                adapter.setData(historyList)
            }
        })
//       historyList = mHistoryViewModel.readAllData
//        adapter.setData(historyList)
    }
}