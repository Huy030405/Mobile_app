package com.example.homework

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.homework.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtAge = findViewById<EditText>(R.id.edtAge)
        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        btnCheck.setOnClickListener {
            val name = edtName.text.toString().trim()
            val ageText = edtAge.text.toString().trim()

            if (name.isEmpty() || ageText.isEmpty()) {
                txtResult.text = "Vui lòng nhập đủ họ tên và tuổi!"
                return@setOnClickListener
            }

            val age = ageText.toInt()
            val category = when {
                age > 65 -> "Người già"
                age in 6..65 -> "Người lớn"
                age in 2..6 -> "Trẻ em"
                else -> "Em bé"
            }

            txtResult.text = "$name (${age} tuổi) là: $category"
        }
    }
}

