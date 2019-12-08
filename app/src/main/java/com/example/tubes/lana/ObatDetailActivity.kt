package com.example.tubes.lana

import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_obat_detail.*
import com.google.firebase.database.DatabaseError
import androidx.annotation.NonNull
import com.example.tubes.lana.Model.Obat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.auth.FirebaseUser
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.tubes.lana.Model.PesananObat
import com.google.firebase.auth.FirebaseAuth


class ObatDetailActivity : AppCompatActivity() {

    lateinit var database : FirebaseDatabase
    lateinit var refObat : DatabaseReference
    lateinit var refPesananObat : DatabaseReference
    lateinit var pesananObat: PesananObat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_obat_detail)

        database = FirebaseDatabase.getInstance()
        refObat = database.getReference(OBAT)
        refPesananObat = database.getReference(PESANAN_OBAT)

        val key = intent.getStringExtra(KEY_OBAT)
        val mauth : FirebaseAuth= FirebaseAuth.getInstance()
        val user : FirebaseUser? = mauth.currentUser


        refObat.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.child(key).getValue(Obat::class.java)!!
                txtnamaobat.text = data.nama
                imgobat.setImageResource(applicationContext.resources.getIdentifier(data.nama,
                    "drawable", applicationContext.packageName))
                txtdeskripsi.text = "Kategori : ${data.kategori}\n ${data.deskripsi}"
                pesananObat = PesananObat(key,data.nama,data.harga ,0 )

            }
        })
        btnsimpan.setOnClickListener {
            pesananObat.jumlah = txtjumlah.text.toString().toInt()
            pesananObat.totalharga *= pesananObat.jumlah
            refPesananObat.child(user?.uid.toString()).child(key).setValue(pesananObat)
            finish()
        }

    }
}
