//package com.example.numberlistapp
package com.example.lab2


import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setMargins
//import android.R
import com.example.lab2.R

class MainActivity : AppCompatActivity() {

    private lateinit var edtNumber: EditText
    private lateinit var btnGenerate: Button
    private lateinit var listContainer: LinearLayout
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNumber = findViewById(R.id.edtNumber)
        btnGenerate = findViewById(R.id.btnGenerate)
        listContainer = findViewById(R.id.listContainer)
        tvError = findViewById(R.id.tvError)

        btnGenerate.setOnClickListener {
            val input = edtNumber.text.toString().trim()
            listContainer.removeAllViews() // Xóa danh sách cũ
            tvError.visibility = TextView.GONE

            try {
                val n = input.toInt()

                for (i in 1..n) {
                    val tv = TextView(this).apply {
                        text = i.toString()
                        textSize = 18f
                        setTextColor(Color.WHITE)
                        setBackgroundColor(Color.RED)
                        gravity = Gravity.CENTER
                        setPadding(0, 20, 0, 20)

                        val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        params.setMargins(0, 10, 0, 0)
                        layoutParams = params
                    }
                    listContainer.addView(tv)
                }

            } catch (e: NumberFormatException) {
                tvError.visibility = TextView.VISIBLE
            }
        }
    }
}
