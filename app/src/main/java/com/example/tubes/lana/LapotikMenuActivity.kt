package com.example.tubes.lana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LapotikMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lapotik_menu)

    }

    fun lapotikClick(view: View) {
        val nextActivity = Intent(this, LapotikActivity::class.java)
        startActivity(nextActivity)
    }
    fun resepClick(view: View) {
        val nextActivity = Intent(this, LapotikGambarActivity::class.java)
        nextActivity.putExtra("angka",6)
        startActivity(nextActivity)
    }
    fun prosesClick(view: View) {
        val nextActivity = Intent(this, LapotikGambarActivity::class.java)
        nextActivity.putExtra("angka",1)
        startActivity(nextActivity)
    }

}
