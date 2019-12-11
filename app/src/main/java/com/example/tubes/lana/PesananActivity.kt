package com.example.tubes.lana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tubes.lana.Adapter.PesananObatAdapter
import com.example.tubes.lana.Model.PesananObat
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_pembayaran.*
import kotlinx.android.synthetic.main.activity_pesanan.*
import kotlinx.android.synthetic.main.activity_pesanan.btnsimpan
import kotlinx.android.synthetic.main.activity_pesanan.listbarang
import kotlinx.android.synthetic.main.activity_pesanan.txtnoBCA
import java.text.NumberFormat
import java.util.*

class PesananActivity : AppCompatActivity() {


    lateinit var database: FirebaseDatabase
    lateinit var refPesananObat: DatabaseReference

    private lateinit var listPesananObat: MutableList<PesananObat>
    lateinit var adapter: PesananObatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesanan)

        listPesananObat = mutableListOf()

//        mUser = FirebaseAuth.getInstance().currentUser!!
        val iduser = intent.getStringExtra(USER_ID)
        database = FirebaseDatabase.getInstance()
        refPesananObat = database.getReference(PESANAN_OBAT).child(iduser)
        val refInfoPesanan = database.getReference(PESANAN_OBAT).child(INFOPESANAN).child(iduser)

        var total = 0
        refPesananObat.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {            }

            override fun onDataChange(datas: DataSnapshot) {
                if (datas.exists()) {
                    for (data in datas.children) {
                        val pesanan = data.getValue(PesananObat::class.java)!!
                        if (pesanan.jumlah != 0 ) listPesananObat.add(pesanan)
                    }
                    total = listPesananObat.map { it.totalharga }.sum()
                    val format = NumberFormat.getCurrencyInstance()
                    format.setMaximumFractionDigits(0)
                    format.setCurrency(Currency.getInstance("IDR"))
                    txthargatotal.text = format.format(total)
                    adapter = PesananObatAdapter(this@PesananActivity, listPesananObat)
                    listbarang.adapter = adapter
                }
            }
        })

        btnsimpan.setOnClickListener {
            val inten = Intent(this@PesananActivity, PembayaranActivity::class.java)
            inten.putExtra(USER_ID, iduser)
            inten.putExtra(PEMBAYARAN, total )
            startActivity(inten)
            finish()
        }
    }
}
