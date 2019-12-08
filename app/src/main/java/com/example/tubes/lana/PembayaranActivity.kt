package com.example.tubes.lana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tubes.lana.Model.Obat
import com.example.tubes.lana.Model.PesananObat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_obat_detail.*
import kotlinx.android.synthetic.main.activity_pembayaran.*

class PembayaranActivity : AppCompatActivity() {
    lateinit var database : FirebaseDatabase
    lateinit var refPesananObat : DatabaseReference
    lateinit var mUser : FirebaseUser
    private lateinit var listPesananObat : MutableList<PesananObat>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        mUser = FirebaseAuth.getInstance().currentUser!!
        database = FirebaseDatabase.getInstance()
        refPesananObat = database.getReference(PESANAN_OBAT).child(mUser.uid)
        refPesananObat.child(PEMBAYARAN).setValue("cash")

        refPesananObat.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(datas: DataSnapshot) {
                if (datas.exists()){
                    for (data in datas.children){
                        val pesanan = data.getValue(PesananObat::class.java)!!
                        listPesananObat.add(pesanan)
                    }
                }
            }

        })

//        radioGroup.setOnCheckedChangeListener { radioGroup, id ->
//            if (id == R.id.radiocash)refPesananObat.child(PEMBAYARAN).setValue("cash")
//            else refPesananObat.child(PEMBAYARAN).setValue("bca")
//        }
//
        btnsimpan.setOnClickListener {
            if (radioGroup.checkedRadioButtonId == R.id.radiocash) refPesananObat.child(PEMBAYARAN).setValue("cash")
            else refPesananObat.child(PEMBAYARAN).setValue("bca")
            refPesananObat.child(ALAMAT).setValue(txtalamat.text.toString())
            refPesananObat.child(PENGIRIMAM).setValue(1)
            intn
        }

    }
}
