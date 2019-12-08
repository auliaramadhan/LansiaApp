package com.example.tubes.lana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tubes.lana.Adapter.ObatAdapter
import com.example.tubes.lana.Adapter.PesananObatAdapter
import com.example.tubes.lana.Model.Obat
import com.example.tubes.lana.Model.PesananObat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_pembayaran.*

class PembayaranActivity : AppCompatActivity() {
    lateinit var database : FirebaseDatabase
    lateinit var refPesananObat : DatabaseReference
    lateinit var mUser : FirebaseUser
    private lateinit var listPesananObat : MutableList<PesananObat>
    lateinit var adapter: PesananObatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        listPesananObat = mutableListOf()

        mUser = FirebaseAuth.getInstance().currentUser!!
        database = FirebaseDatabase.getInstance()
        refPesananObat = database.getReference(PESANAN_OBAT).child(mUser.uid)
        val refInfoPesanan = database.getReference(PESANAN_OBAT).child(INFOPESANAN).child(mUser.uid)

        refPesananObat.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(datas: DataSnapshot) {
                if (datas.exists()){
                    for (data in datas.children){
                        val pesanan = data.getValue(PesananObat::class.java)!!
                        listPesananObat.add(pesanan)
                        adapter = PesananObatAdapter(this@PembayaranActivity, listPesananObat )
                    }
                }
                listbarang.adapter = adapter
            }
        })


//        radioGroup.setOnCheckedChangeListener { radioGroup, id ->
//            if (id == R.id.radiocash)refPesananObat.child(PEMBAYARAN).setValue("cash")
//            else refPesananObat.child(PEMBAYARAN).setValue("bca")
//        }
//
        btnsimpan.setOnClickListener {
            if (radioGroup.checkedRadioButtonId == R.id.radiocash) refInfoPesanan.child(PEMBAYARAN).setValue("Dibayar dengan Cash")
            else refInfoPesanan.child(PEMBAYARAN).setValue("Dibayar dengan Virtual BCA")
            refInfoPesanan.child(ALAMAT).setValue(txtalamat.text.toString())
            refInfoPesanan.child(PENGIRIMAM).setValue(1)
            val inten = Intent(this@PembayaranActivity, PengirimanActivity::class.java)
            startActivity(inten)
            finish()
        }

    }
}
