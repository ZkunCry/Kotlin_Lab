package com.example.task3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
class MainActivity : AppCompatActivity() {
    private lateinit var etOperand1: EditText
    private lateinit var etOperand2: EditText
    private lateinit var tvOperationSign: TextView
    private lateinit var tvEqualsSign: TextView
    private lateinit var tvResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etOperand1 = findViewById(R.id.etOperand1)
        etOperand2 = findViewById(R.id.etOperand2)
        tvOperationSign = findViewById(R.id.tvOperationSign)
        tvEqualsSign = findViewById(R.id.tvEqualsSign)
        tvResult = findViewById(R.id.tvResult)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnSub = findViewById<Button>(R.id.btnSub)
        val btnMul = findViewById<Button>(R.id.btnMul)
        val btnDiv = findViewById<Button>(R.id.btnDiv)
        val btnClose = findViewById<Button>(R.id.btnClose)

        btnAdd.setOnClickListener { calculate("+") }
        btnSub.setOnClickListener { calculate("-") }
        btnMul.setOnClickListener { calculate("*") }
        btnDiv.setOnClickListener { calculate("/") }

        btnClose.setOnClickListener { finish() }

        // Дополнительное задание: Очистка при изменении операндов
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                tvOperationSign.text = ""
                tvEqualsSign.text = ""
                tvResult.text = ""
            }
        }

        etOperand1.addTextChangedListener(textWatcher)
        etOperand2.addTextChangedListener(textWatcher)
    }

    private fun calculate(operation: String) {
        val str1 = etOperand1.text.toString()
        val str2 = etOperand2.text.toString()

        if (str1.isEmpty() || str2.isEmpty()) {
            Toast.makeText(this, "Введите оба числа", Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = str1.toDouble()
        val num2 = str2.toDouble()
        var result = 0.0

        when (operation) {
            "+" -> result = num1 + num2
            "-" -> result = num1 - num2
            "*" -> result = num1 * num2
            "/" -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, "Деление на ноль!", Toast.LENGTH_SHORT).show()
                    return
                }
                result = num1 / num2
            }
        }

        tvOperationSign.text = operation
        tvEqualsSign.text = "="

        tvResult.text = if (result % 1.0 == 0.0) {
            result.toInt().toString()
        } else {
            result.toString()
        }
    }
}