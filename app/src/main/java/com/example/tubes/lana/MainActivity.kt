package com.example.tubes.lana

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    val text_konten = listOf<String>( "Tidak Ada Waktu", "Selamat Datang ke Aplikasi Lana")
    lateinit var mPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance();
        var i =0
        val viewLayar: RelativeLayout = findViewById(R.id.layout)
        viewLayar.setOnClickListener {
            // pada setiap function parameter otomatis jadi it+
            var konten: TextView = findViewById(R.id.textContent)
            if (konten.text == text_konten[1]) {
                val nextActivity = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(nextActivity)
            } else konten.text = text_konten[i++]
        }
    }


    override fun onStart() {
        mPreferences = getSharedPreferences(SHAREDPREFFILE, MODE_PRIVATE)
        val currentUser = mAuth.currentUser

        if (mPreferences.getString(USER_ID, null) != null) {
            val nextActivity = Intent(this, BerandaActivity::class.java)
            startActivity(nextActivity)
        }

        super.onStart()
    }


}
