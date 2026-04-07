package com.qoqokoi.myapp

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bikin Layout Utama (Wadahnya)
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setBackgroundColor(Color.parseColor("#0b0e14")) // Warna gelap ala terminal Kitty lo
        layout.gravity = Gravity.CENTER

        // Judul Aplikasi
        val title = TextView(this)
        title.text = "QOQOKOI CYBER-SYSTEM"
        title.textSize = 30f
        title.setTextColor(Color.parseColor("#00ff00")) // Ijo Hacker
        title.setPadding(0, 0, 0, 50)

        // Status Info
        val info = TextView(this)
        info.text =
            """
            USER: Qoqokoi
            LOC: Ponorogo, East Java
            OS: Arch Linux x Android
            NET: 94 Mbps Detected
            STATUS: SYSTEM READY!
            """.trimIndent()
        info.textSize = 18f
        info.setTextColor(Color.WHITE)
        info.gravity = Gravity.CENTER

        // Masukin ke layout
        layout.addView(title)
        layout.addView(info)

        setContentView(layout)
    }
}
