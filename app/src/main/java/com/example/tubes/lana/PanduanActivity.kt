package com.example.tubes.lana

import android.app.LauncherActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PanduanActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panduan)
    }

    fun callCenteclick(view: View) {startActivity(Intent(this,CallCenterActivity::class.java))}

    fun helpLahragaclick(view: View) {
        val intent = Intent(this, PanduanDetailActivity::class.java)
        intent.putExtra(ID_STRING, R.string.panduanLahraga)
        intent.putExtra(ID_IMAGE, R.drawable.Lahraga_img)
        startActivity(intent)
    }
    fun helpLaminderclick(view: View) {
        val intent = Intent(this, PanduanDetailActivity::class.java)
        intent.putExtra(ID_STRING, R.string.panduanLaminder)
        intent.putExtra(ID_IMAGE, R.drawable.Laminder_img)
        startActivity(intent)
    }
    fun helpLamasiclick(view: View) {
        val intent = Intent(this, PanduanDetailActivity::class.java)
        intent.putExtra(ID_STRING, R.string.panduanLamasi)
        intent.putExtra(ID_IMAGE, R.drawable.Lamasi_img)
        startActivity(intent)
    }
    fun helpLapotikclick(view: View) {
        val intent = Intent(this, PanduanDetailActivity::class.java)
        intent.putExtra(ID_STRING, R.string.panduanLapotik)
        intent.putExtra(ID_IMAGE, R.drawable.Lapotik_img)
        startActivity(intent)
    }
}
