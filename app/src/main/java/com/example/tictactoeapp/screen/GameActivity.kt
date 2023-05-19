package com.example.tictactoeapp.screen

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoeapp.R
import com.example.tictactoeapp.data.HistoryDao
import com.example.tictactoeapp.data.HistoryDatabase
import com.example.tictactoeapp.model.History
import com.example.tictactoeapp.viewmodel.HistoryViewModel

class GameActivity : AppCompatActivity() {
    private var currentPlayer = "X"
    private var moveCount = 0
    private var boardSize: Int? = 0
    private var moves = Array(boardSize!!) { Array(boardSize!!) { "" } }
    private lateinit var database: HistoryDatabase
    private lateinit var historyDao: HistoryDao
    private lateinit var mHistoryViewModel: HistoryViewModel

    // private var moves: Array<Array> = Array(boardSize) { IntArray(boardSize) }
    private var playerWon = ""
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //init database
        mHistoryViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        database = HistoryDatabase.getDatabase(applicationContext)
        historyDao = database.historyDao()

        boardSize = intent.getIntExtra("value", boardSize!!)
        moves = Array(boardSize!!) { Array(boardSize!!) { "" } }
        val btnReset: Button = findViewById(R.id.btn_Reset)
        btnReset.setOnClickListener {
            resetGame()
        }

        //val linearlayout: LinearLayout = findViewById(R.id.vertical_lin_layout)
        linearLayout = findViewById(R.id.vertical_lin_layout)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER

//        val linearLayoutParams = LinearLayout.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT
//        )
//        linearlayout.layoutParams = linearLayoutParams

        /** Create Board **/
        for (i in 0 until boardSize!!) {
            val innerLinearLayout = LinearLayout(this)
            innerLinearLayout.orientation = LinearLayout.HORIZONTAL
            val innerLayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            innerLinearLayout.layoutParams = innerLayoutParams
            linearLayout.addView(innerLinearLayout)
            for (j in 0 until boardSize!!) {
                val button = Button(this)
//                val buttonLayoutParams = LinearLayout.LayoutParams(
//                    0,
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    1f
//                )
                val displayMetrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(displayMetrics)
                val screenWidth = (displayMetrics.widthPixels - 80) / boardSize!!
                button.layoutParams = LinearLayout.LayoutParams(screenWidth, screenWidth)
                button.setOnClickListener {
                    checkWin(i, j, moves, button)
                    onBtnClicked(button)
                    //checkForWin(i, j, currentPlayer)
                    // makeMove(i, j)
                    // checkForWin(currentPlayer)
                    //Log.d("Moves : ", moves[i][j].toString())
                    // Print Button Position
                    // printPosition(i, j)
                    printMove(i, j, moves)
                    //Log.d("Linear Layout Childs : ", linearLayout.childCount.toString())
                }
                innerLinearLayout.addView(button)
            }
            // linearLayout.addView(innerLinearLayout)
        }
    }

    private fun onBtnClicked(button: Button) {
        if (currentPlayer == "X") {
            if (button.text.isEmpty()) {
                button.text = currentPlayer
                button.textSize = 30f
                //button.setBackgroundColor(Color.RED)
                button.isEnabled = false
                currentPlayer = "O"
                // Log.d("Current Player is : ", currentPlayer)
                // Log.d("Index is : ", index.toString())
            }
        } else if (currentPlayer == "O") {
            if (button.text.isEmpty()) {
                button.text = currentPlayer
                button.textSize = 30f
                //button.setBackgroundColor(Color.BLUE)
                button.isEnabled = false
                currentPlayer = "X"
                // Log.d("Current Player is : ", currentPlayer)
                //Log.d("Index is : ", index.toString())
            }
        }
    }

    private  fun checkWin(x: Int, y: Int, moves: Array<Array<String>>, button: Button) {
        if (moves[x][y] == "") {
            moves[x][y] = currentPlayer
        }
        moveCount++

        if (boardSize!! == 3) {
            /** check horizontal **/
            for (i in 0 until boardSize!!) {
                if (moves[x][i] != currentPlayer) {
                    break
                }
                if (i == boardSize!! - 1) {
                    try {
                        insertDataToDatabase()
                        showWinDialog()
//                        Log.d("Game", currentPlayer.toString() + " is Winner!")
//                        Log.d("Insert", "Insert History Success!")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            /** check vertical **/
            for (i in 0 until boardSize!!) {
                if (moves[i][y] != currentPlayer) {
                    break
                }
                if (i == boardSize!! - 1) {
                    try {
                        insertDataToDatabase()
                        showWinDialog()
//                        Log.d("Game", currentPlayer.toString() + " is Winner!")
//                        Log.d("Insert", "Insert History Success!")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            /** check diag **/
            if (x == y) {
                for (i in 0 until boardSize!!) {
                    if (moves[i][i] != currentPlayer) {
                        break
                    }
                    if (i == boardSize!! - 1) {
                        try {
                            insertDataToDatabase()
                            showWinDialog()
//                        Log.d("Game", currentPlayer.toString() + " is Winner!")
//                        Log.d("Insert", "Insert History Success!")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            //check anti diag
            if (x + y == boardSize!! - 1) {
                for (i in 0 until boardSize!!) {
                    if (moves[i][(boardSize!! - 1) - i] != currentPlayer) {
                        break
                    }
                    if (i == boardSize!! - 1) {
                        try {
                            insertDataToDatabase()
                            showWinDialog()
//                        Log.d("Game", currentPlayer.toString() + " is Winner!")
//                        Log.d("Insert", "Insert History Success!")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            //check draw
            if (moveCount == boardSize!! * boardSize!!) {
                Log.d("Game", " It's a Draw!")
                showDrawDialog()
            }
        } else {
            /** check horizontal **/
            for (i in 0 until boardSize!!) {
                if (moves[x][i] != currentPlayer) {
                    break
                }
                if (i == 3) {
                    try {
                        insertDataToDatabase()
                        showWinDialog()
//                        Log.d("Game", currentPlayer.toString() + " is Winner!")
//                        Log.d("Insert", "Insert History Success!")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            /** check vertical **/
            for (i in 0 until boardSize!!) {
                if (moves[i][y] != currentPlayer) {
                    break
                }
                if (i == 3) {
                    try {
                        insertDataToDatabase()
                        showWinDialog()
                        Log.d("Game", currentPlayer.toString() + " is Winner!")
                        Log.d("Insert", "Insert History Success!")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            /** check diag **/
            if (x == y) {
                for (i in 0 until boardSize!!) {
                    if (moves[i][i] != currentPlayer) {
                        break
                    }
                    if (i == 3) {
                        try {
                            insertDataToDatabase()
                            showWinDialog()
//                        Log.d("Game", currentPlayer.toString() + " is Winner!")
//                        Log.d("Insert", "Insert History Success!")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            //check anti diag
            if (x + y == boardSize!! - 1) {
                for (i in 0 until boardSize!!) {
                    if (moves[i][(boardSize!! - 1) - i] != currentPlayer) {
                        break
                    }
                    if (i == 3) {
                        try {
                            insertDataToDatabase()
                            showWinDialog()
//                        Log.d("Game", currentPlayer.toString() + " is Winner!")
//                        Log.d("Insert", "Insert History Success!")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            //check draw
            if (moveCount == boardSize!! * boardSize!!) {
                Log.d("Game", " It's a Draw!")
                showDrawDialog()
            }
        }
    }

    private fun resetGame() {
        currentPlayer = "X"
        moves = Array(boardSize!!) { Array(boardSize!!) { "" } }
        moveCount = 0
        for (i in 0 until linearLayout.childCount) {
            val childView = linearLayout.getChildAt(i)
            if (childView is LinearLayout) {
                for (j in 0 until childView.childCount) {
                    val button = childView.getChildAt(j) as Button
                    val displayMetrics = DisplayMetrics()
                    windowManager.defaultDisplay.getMetrics(displayMetrics)
                    val screenWidth = (displayMetrics.widthPixels - 80) / boardSize!!
                    button.layoutParams = LinearLayout.LayoutParams(screenWidth, screenWidth)
                    button.text = ""
                    button.isEnabled = true
                }
            }
        }
    }


    //Print button position
    private fun printPosition(i: Int, j: Int) {
        Log.d("I : ", i.toString())
        Log.d("J : ", j.toString())
    }

    private fun printMove(i: Int, j: Int, moves: Array<Array<String>>) {
//        Log.d("Moves I : ", i.toString())
//        Log.d("Moves J : ", j.toString())
        for (i in 0 until boardSize!!) {
            println("Moves : " + moves[i][j].toString())
            for (j in 0 until boardSize!!) {
                println("Moves : " + moves[i][j].toString())
            }
        }
    }

    private fun showWinDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Player " + currentPlayer + " won")
        builder.setMessage("Do you want to Restart?")
        builder.setPositiveButton("Restart") { dialog, which ->
            resetGame()

        }
        builder.setNegativeButton("Show Board") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showDrawDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("It's a Draw")
        builder.setMessage("Do you want to restart?")
        builder.setPositiveButton("Reset") { dialog, which ->
            resetGame()
            Log.d("Dialog", "Restart clicked")
        }
        builder.setNegativeButton("Show Board") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun insertDataToDatabase() {
        val winnerPlayer = currentPlayer
        val history = History(0, winnerPlayer)
        mHistoryViewModel.addHistory(history)
    }
}