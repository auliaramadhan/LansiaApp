package com.example.tubes.lana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val text_konten = """
        Selamat Menggunakan LANA
    """.trimIndent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewLayar : RelativeLayout = findViewById(R.id.layout)
        viewLayar.setOnClickListener {
            // pada setiap function parameter otomatis jadi it
            var konten: TextView = findViewById(R.id.textContent)
            when (konten.text){
                text_konten -> {
                    val nextActivity = Intent(this, LoginActiciviy::class.java)
                    startActivity(nextActivity)
                }
                else -> konten.text = text_konten
            }
        }

    }
}
