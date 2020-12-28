package com.example.a02_lab

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import org.w3c.dom.Text
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputName = findViewById<EditText>(R.id.inputName)
        val showName = findViewById<TextView>(R.id.textName)
        val rcName = findViewById<TextView>(R.id.rcName)
        val rcWinName = findViewById<TextView>(R.id.rcWinName)
        val rcMeName = findViewById<TextView>(R.id.rcMeInput)
        val rcComputerName = findViewById<TextView>(R.id.rcComInput)
        val rGroup = findViewById<RadioGroup>(R.id.btn_group)
        val btnScossor = findViewById<RadioButton>(R.id.btnScissor)
        val btnStone = findViewById<RadioButton>(R.id.btnStone)
        val btnPaper = findViewById<RadioButton>(R.id.btnPaper)
        val btnStart = findViewById<Button>(R.id.btnMora)

        btnStart.setOnClickListener {
            if (inputName.length() < 1) {
                showName.text = "你什麼都還沒輸入！"
                showName.setTextColor(Color.RED)
            } else {
                showName.text = "Hi " + inputName.text
                showName.setTextColor(Color.BLACK)
                rcName.text = "${rcName.text.toString()}\n${inputName.text}"
//                TODO 印出 使用者出的拳
                var outputS = ""
                if (btnScossor.isChecked) {
                    outputS = btnScossor.text.toString()
                } else if (btnStone.isChecked) {
                    outputS = btnStone.text.toString()
                } else {
                    outputS = btnPaper.text.toString()
                }
                rcMeName.text = "${rcMeName.text.toString()}\n${outputS}"
//                TODO 印出機器人出的拳
                val computer = (Math.random() * 3).toInt()
                var outputC = ""
                if (computer == 0) {
                    outputC = btnScossor.text.toString()
                } else if (computer == 1) {
                    outputC = btnStone.text.toString()
                } else {
                    outputC = btnPaper.text.toString()
                }
                rcComputerName.text = "${rcComputerName.text}\n${outputC}"
//                TODO 判斷誰輸誰贏
                when{
                    btnScossor.isChecked && computer == 2 ||
                            btnStone.isChecked && computer == 0 ||
                            btnPaper.isChecked && computer == 1 ->{
                        rcWinName.text = "${rcWinName.text}\n玩家獲勝"
                        showName.text = "恭喜你，勝利了！"
                    }
                    btnScossor.isChecked && computer == 1 ||
                            btnStone.isChecked && computer == 2 ||
                            btnPaper.isChecked && computer == 0 ->{
                        rcWinName.text = "${rcWinName.text}\n電腦獲勝"
                        showName.text = "很可惜，失敗了！"
                    }
                    else ->{
                        rcWinName.text = "${rcWinName.text}\n平手"
                        showName.text = "和局，再一次。"
                    }
                }
            }
        }
    }
}