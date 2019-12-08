package com.example.tubes.lana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tubes.lana.Adapter.PesananObatAdapter
import com.example.tubes.lana.Model.PesananObat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_pembayaran.listbarang
import kotlinx.android.synthetic.main.activity_pengiriman.*

class PengirimanActivity : AppCompatActivity() {

    lateinit var database : FirebaseDatabase
    lateinit var refPesananObat : DatabaseReference
    lateinit var mUser : FirebaseUser

    lateinit var listPesananObat: MutableList<PesananObat>
    lateinit var adapter: PesananObatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengiriman)

        listPesananObat = mutableListOf()

        mUser = FirebaseAuth.getInstance().currentUser!!
        database = FirebaseDatabase.getInstance()
        refPesananObat = database.getReference(PESANAN_OBAT)

        refPesananObat.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(datas: DataSnapshot) {
                if (datas.child(mUser.uid).exists()){
                    for (data in datas.children){
                        val pesanan = data.getValue(PesananObat::class.java)!!
                        listPesananObat.add(pesanan)
                        adapter = PesananObatAdapter(this@PengirimanActivity, listPesananObat )
                        listbarang.adapter = adapter
                    }
                }
                val info = datas.child(INFOPESANAN).child(mUser.uid)
                txtpembayaran.text = info.child(PEMBAYARAN).getValue(String::class.java)
                txtalamat.text = info.child(ALAMAT).getValue(String::class.java)
            }
        })
    }
}
