package com.example.tubes.lana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class BerandaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)
    }

    fun lapotikClick(view: View) {
        val nextActivity = Intent(this, LoginActiciviy::class.java)
        startActivity(nextActivity)
    }
    fun lamasiClick(view: View) {
        val nextActivity = Intent(this, LoginActiciviy::class.java)
        startActivity(nextActivity)
    }

    fun laminderClick(view: View) {
        val nextActivity = Intent(this, LoginActiciviy::class.java)
        startActivity(nextActivity)
    }
    fun lahragaClick(view: View) {
        val nextActivity = Intent(this, LoginActiciviy::class.java)
        startActivity(nextActivity)
    }
    fun panduanClick(view: View) {
        val nextActivity = Intent(this, LoginActiciviy::class.java)
        startActivity(nextActivity)
    }


}
