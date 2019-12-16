package com.example.tubes.lana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.youtube.player.internal.i
import kotlinx.android.synthetic.main.activity_lapotik_gambar.*

class LapotikGambarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lapotik_gambar)
        val angka = intent.getIntExtra("angka",0)
        var nama =  if (angka == 6 ) "resep" else "daftar"
        var i = 1

        gambar.setImageResource(
            resources.getIdentifier(
                "${nama}_$i",
                "drawable", packageName
            )
        )
        gambar.setOnClickListener {
            if ( i <= angka){
                val resourceId = resources.getIdentifier(
                    "${nama}_$i",
                    "drawable", packageName
                )
                gambar.setImageResource(resourceId)
                i++
            }
        }
    }
}

