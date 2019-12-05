package com.example.tubes.lana

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var  mAuth: FirebaseAuth

    val text_konten = """
        Selamat Menggunakan LANA
    """.trimIndent()

    lateinit var mPreferences : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance();

        val viewLayar : RelativeLayout = findViewById(R.id.layout)
        viewLayar.setOnClickListener {
            // pada setiap function parameter otomatis jadi it
            var konten: TextView = findViewById(R.id.textContent)
            when (konten.text){
                text_konten -> {
                    val nextActivity = Intent(this, LoginActivity::class.java)
                    startActivity(nextActivity)
                }
                else -> konten.text = text_konten
            }
        }
    }



    override fun onStart() {
        mPreferences = getSharedPreferences(SHAREDPREFFILE, MODE_PRIVATE)
        val currentUser = mAuth.currentUser

        if (mPreferences.getString(USER_NAME, null) != null){
            val nextActivity = Intent(this, LoginActivity::class.java)
            startActivity(nextActivity)
        }else {
            val preferencesEditor = mPreferences.edit()
            preferencesEditor.putString(USER_NAME, currentUser?.displayName)
        }

        super.onStart()
    }




}
