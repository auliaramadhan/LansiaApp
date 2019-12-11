package com.example.tubes.lana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_driver.*
import kotlinx.android.synthetic.main.activity_driver.btnsimpan
import kotlinx.android.synthetic.main.activity_pembayaran.*
import java.text.NumberFormat
import java.util.*

class DriverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver)

        val format = NumberFormat.getCurrencyInstance()
        format.setMaximumFractionDigits(0)
        format.setCurrency(Currency.getInstance("IDR"))


        var total = intent.getIntExtra(PEMBAYARAN, 0)
        when( intent.getIntExtra(METODE_PEMBAYARAN, 0 ) ){
            1 -> textContent.text = "Silahkan Siapkan Uang Sebesar ${format.format(total)}"
            2 ->  textContent.text = "Silahkan Transfer Sebesar ${format.format(total)}"
        }

        var i =0

        btnsimpan.setOnClickListener {
            when(i){
             0 -> {
                 textContent.visibility = View.GONE
                 textDriver.visibility = View.VISIBLE
                 i++
             }
            1 -> {
                val inten = Intent(this@DriverActivity, PengirimanActivity::class.java)

                inten.putExtra(USER_ID, intent.getStringExtra(USER_ID))
                inten.putExtra(PEMBAYARAN, intent.getIntExtra(PEMBAYARAN, 0))
                startActivity(inten)
                finish()
            }
            }
        }

    }
}
