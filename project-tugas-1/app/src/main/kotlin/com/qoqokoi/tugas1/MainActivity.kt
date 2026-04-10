package com.qoqokoi.tugas1

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView =
            TextView(this).apply {
                text = "Hi, my name is Dafi!" // Sesuai tugas lo
                textSize = 32f
                setTextColor(Color.BLACK)
                setBackgroundColor(Color.parseColor("#80DEEA")) // Cyan mbois
                gravity = Gravity.CENTER
                setPadding(40, 40, 40, 40)
            }

        setContentView(textView)
    }
}
