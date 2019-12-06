package com.example.tubes.lana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CallCenterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_center)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}
