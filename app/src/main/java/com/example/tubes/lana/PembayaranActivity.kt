package com.example.tubes.lana

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.tubes.lana.Adapter.PesananObatAdapter
import com.example.tubes.lana.Model.PesananObat
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_pembayaran.*

class PembayaranActivity : AppCompatActivity() {
    lateinit var database: FirebaseDatabase
    lateinit var refPesananObat: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

//        mUser = FirebaseAuth.getInstance().currentUser!!
        val iduser = intent.getStringExtra(USER_ID)
        database = FirebaseDatabase.getInstance()
        refPesananObat = database.getReference(PESANAN_OBAT).child(iduser)
        val refInfoPesanan = database.getReference(PESANAN_OBAT).child(INFOPESANAN).child(iduser)

        txtnoBCA.isEnabled = false

        radioGroup.setOnCheckedChangeListener { radioGroup, id ->
            if (radiobca.isChecked) txtnoBCA.isEnabled = true
            else txtnoBCA.isEnabled = false
        }
//
        btnsimpan.setOnClickListener {
            val inten = Intent(this@PembayaranActivity, DriverActivity::class.java)
            if (radioGroup.checkedRadioButtonId == R.id.radiocash) {
                refInfoPesanan.child(PEMBAYARAN).setValue("Dibayar dengan Cash")
                refInfoPesanan.child(PENGIRIMAM).setValue(2)
                inten.putExtra(METODE_PEMBAYARAN, 1 )
            } else {
                refInfoPesanan.child(PEMBAYARAN).setValue("Dibayar dengan Virtual BCA")
                refInfoPesanan.child(PENGIRIMAM).setValue(2)
                inten.putExtra(METODE_PEMBAYARAN, 2)
            }
            refInfoPesanan.child(ALAMAT).setValue(txtalamat.text.toString())

            inten.putExtra(USER_ID, iduser)
            inten.putExtra(PEMBAYARAN, intent.getIntExtra(PEMBAYARAN, 0))
            startActivity(inten)
        }

    }
}
