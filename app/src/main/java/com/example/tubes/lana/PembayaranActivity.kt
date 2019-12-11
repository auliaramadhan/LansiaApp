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

    private lateinit var listPesananObat: MutableList<PesananObat>
    lateinit var adapter: PesananObatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        listPesananObat = mutableListOf()

//        mUser = FirebaseAuth.getInstance().currentUser!!
        val iduser = intent.getStringExtra(USER_ID)
        database = FirebaseDatabase.getInstance()
        refPesananObat = database.getReference(PESANAN_OBAT).child(iduser)
        val refInfoPesanan = database.getReference(PESANAN_OBAT).child(INFOPESANAN).child(iduser)

        refPesananObat.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(datas: DataSnapshot) {
                if (datas.exists()) {
                    for (data in datas.children) {
                        val pesanan = data.getValue(PesananObat::class.java)!!
                        listPesananObat.add(pesanan)
                    }
                    var total = listPesananObat.map { it.totalharga }.sum()
                    listPesananObat.add(PesananObat("Jumlah" ,total, null))
                    adapter = PesananObatAdapter(this@PembayaranActivity, listPesananObat)
                    listbarang.adapter = adapter
                }
            }
        })


        radioGroup.setOnCheckedChangeListener { radioGroup, id ->
            if (radiobca.isChecked) txtnoBCA.visibility = View.VISIBLE
            else txtnoBCA.visibility = View.GONE
        }
//
        btnsimpan.setOnClickListener {
            if (radioGroup.checkedRadioButtonId == R.id.radiocash) {
                refInfoPesanan.child(PEMBAYARAN).setValue("Dibayar dengan Cash")
                refInfoPesanan.child(PENGIRIMAM).setValue(2)
            } else {
                refInfoPesanan.child(PEMBAYARAN).setValue("Dibayar dengan Virtual BCA")
                refInfoPesanan.child(PENGIRIMAM).setValue(1)
            }
            refInfoPesanan.child(ALAMAT).setValue(txtalamat.text.toString())

            val inten = Intent(this@PembayaranActivity, PengirimanActivity::class.java)
            inten.putExtra(USER_ID, iduser)
            startActivity(inten)
            finish()
        }

    }
}
