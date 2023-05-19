package com.example.tictactoeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoeapp.screen.GameActivity
import com.example.tictactoeapp.screen.HistoryActivity
import com.example.tictactoeapp.viewmodel.HistoryViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mHistoryViewModel: HistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** init database **/
        mHistoryViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        val intent = Intent(this, GameActivity::class.java)

        val btnThreeByThree = findViewById<Button>(R.id.btn_threebythree)
        btnThreeByThree.setOnClickListener {
            intent.putExtra("value", 3)
            startActivity(intent)
        }
        val btnFiveByFive = findViewById<Button>(R.id.btn_fivebyfive)
        btnFiveByFive.setOnClickListener {
            intent.putExtra("value", 5)
            startActivity(intent)
        }
        val btnSevenBySeven = findViewById<Button>(R.id.btn_sevenbyseven)
        btnSevenBySeven.setOnClickListener {
            intent.putExtra("value", 7)
            startActivity(intent)
        }
        val btnHistory = findViewById<Button>(R.id.btn_history)
        btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

    }
}
