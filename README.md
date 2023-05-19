# Read me

| Task                                                            | Finished | 
|--------------------------------------------------------------|-----------|
| ให้เขียนเกม XO ด้วย Web หรือ Mobile App โดยให้เกมสามารถกำหนด ขนาดตารางของ XO นอกจาก 3x3 เป็นขนาดใด ๆ ก็ได้    | ✔️ | 
| มีระบบฐานข้อมูลเก็บ history play ของเกมเพื่อดู replay ได้                                                     | ✔️ | 

---
  ## เขียนเกม XO ด้วย Web หรือ Mobile App โดยให้เกมสามารถกำหนด ขนาดตารางของ XO นอกจาก 3x3 เป็นขนาดใด ๆ ก็ได้
```bash
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
                val displayMetrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(displayMetrics)
                val screenWidth = (displayMetrics.widthPixels - 80) / boardSize!!
                button.layoutParams = LinearLayout.LayoutParams(screenWidth, screenWidth)
                button.setOnClickListener {
                    checkWin(i, j, moves, button)
                    onBtnClicked(button)
                    printMove(i, j, moves)
                }
                innerLinearLayout.addView(button)
            }
        }
```
  ## มีระบบฐานข้อมูลเก็บ history play ของเกมเพื่อดู replay ได้
```bash
//init database
mHistoryViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
database = HistoryDatabase.getDatabase(applicationContext)
historyDao = database.historyDao()
private fun insertDataToDatabase() {
        val winnerPlayer = currentPlayer
        val history = History(0, winnerPlayer)
        mHistoryViewModel.addHistory(history)
    }
```
  ## algorithm ที่ใช้ตรวจสอบการชนะ
```bash
private fun checkWin(x: Int, y: Int, moves: Array<Array<String>>, button: Button) {
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
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            //check draw
            if (moveCount == boardSize!! * boardSize!!) {
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
```
  ## วิธีการ setup และ run โปรแกรม
 * การ Run สามารถดาวน์โหลดไปแล้ว Run ได้เลย
 * การติดตั้งไฟล์ apk สำหรับมือถือ android หรือ simulator android ให้ดาวน์โหลดไฟล์ชื่อ app-debug.apk ไปติดตั้งบนมือถือ android หรือ simulator android ได้เลย
