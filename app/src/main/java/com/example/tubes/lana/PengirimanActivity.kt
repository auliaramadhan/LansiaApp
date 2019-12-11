package com.example.tubes.lana

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tubes.lana.Adapter.PesananObatAdapter
import com.example.tubes.lana.Model.PesananObat
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_pembayaran.listbarang
import kotlinx.android.synthetic.main.activity_pengiriman.*

class PengirimanActivity : AppCompatActivity() {

    lateinit var database: FirebaseDatabase
    lateinit var refPesananObat: DatabaseReference


    lateinit var listPesananObat: MutableList<PesananObat>
    lateinit var adapter: PesananObatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengiriman)

        listPesananObat = mutableListOf()

//        mUser = FirebaseAuth.getInstance().currentUser!!
        database = FirebaseDatabase.getInstance()
        refPesananObat = database.getReference(PESANAN_OBAT)

        val userid = intent.getStringExtra(USER_ID)

        refPesananObat.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(datas: DataSnapshot) {
                if (datas.child(userid).exists()) {
                    for (data in datas.child(userid).children) {
                        val pesanan = data.getValue(PesananObat::class.java)!!
                        listPesananObat.add(pesanan)
                    }
                    var total = listPesananObat.map { it.totalharga }.sum()
                    listPesananObat.add(PesananObat("Jumlah" ,total, null))
                    adapter = PesananObatAdapter(this@PengirimanActivity, listPesananObat)
                    listbarang.adapter = adapter
                }
                val info = datas.child(INFOPESANAN).child(userid)
                txtpembayaran.text = info.child(PEMBAYARAN).getValue(String::class.java)
                txtalamat.text = info.child(ALAMAT).getValue(String::class.java)
                when (info.child(PENGIRIMAM).getValue(Int::class.java)) {
                    1 -> cb_satu.isChecked = true
                    2 -> cb_dua.isChecked = true
                }
            }
        })
    }
}
