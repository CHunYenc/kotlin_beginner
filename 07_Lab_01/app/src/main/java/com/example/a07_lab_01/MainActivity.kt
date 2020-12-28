package com.example.a07_lab_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.graphics.green

class MainActivity : AppCompatActivity() {

    private var balls = arrayOf("Anna", "Bonnie", "Cynthia", "Darcy", "Edison")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val faSpotName = findViewById<TextView>(R.id.textResult)
        val lstPrefer: ListView = findViewById<ListView>(R.id.lstPrefer)
        val adapterBalls = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, balls)
        lstPrefer.adapter = adapterBalls

        lstPrefer.setOnItemClickListener { parent, view, position, id ->
            val sel: String = parent.getItemAtPosition(position).toString()
            faSpotName.setText("最喜歡的球類運動： " + sel)
        }
    }
}