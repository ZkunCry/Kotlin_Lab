package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etInput = findViewById<EditText>(R.id.etInput)
        val btnCopy = findViewById<Button>(R.id.btnCopy)
        val tvOutput = findViewById<TextView>(R.id.tvOutput)
        val btnClose = findViewById<Button>(R.id.btnClose)

        btnCopy.setOnClickListener {
            tvOutput.text = etInput.text.toString()
        }
        btnClose.setOnClickListener {
            finish()
        }
    }
}