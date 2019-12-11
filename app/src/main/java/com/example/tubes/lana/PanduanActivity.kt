package com.example.tubes.lana

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class PanduanActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panduan)
    }

    fun callCenteclick(view: View) {
        startActivity(Intent(this, CallCenterActivity::class.java))
    }

    fun helpLahragaclick(view: View) {
        val intent = Intent(this, PanduanDetailActivity::class.java)
        intent.putExtra(ID_TITLE, "Panduan Lahraga")
        intent.putExtra(ID_STRING, R.string.panduanLahraga)
        intent.putExtra(ID_IMAGE, R.drawable.lahraga_img)
        startActivity(intent)
    }

    fun helpLaminderclick(view: View) {
        val intent = Intent(this, PanduanDetailActivity::class.java)
        intent.putExtra(ID_TITLE, "Panduan Laminder")
        intent.putExtra(ID_STRING, R.string.panduanLaminder)
        intent.putExtra(ID_IMAGE, R.drawable.laminder_img)
        startActivity(intent)
    }

    fun helpLamasiclick(view: View) {
        val intent = Intent(this, PanduanDetailActivity::class.java)
        intent.putExtra(ID_TITLE, "Panduan Lamasi")
        intent.putExtra(ID_STRING, R.string.panduanLamasi)
        intent.putExtra(ID_IMAGE, R.drawable.lamasi_img)
        startActivity(intent)
    }

    fun helpLapotikclick(view: View) {
        val intent = Intent(this, PanduanDetailActivity::class.java)
        intent.putExtra(ID_TITLE, "Panduan Lapotik")
        intent.putExtra(ID_STRING, R.string.panduanLapotik)
        intent.putExtra(ID_IMAGE, R.drawable.lapotik_img)
        startActivity(intent)
    }
}
