package com.example.task2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAllow = findViewById<Button>(R.id.btnAllow)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val btnClose = findViewById<Button>(R.id.btnClose)

        btnAllow.setOnClickListener {
            btnShow.isEnabled = true
        }

        btnShow.setOnClickListener {
            btnClose.visibility = View.VISIBLE
        }

        btnClose.setOnClickListener {
            finish()
        }
    }
}