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
import android.widget.EditText
import com.example.tubes.lana.Model.PesananObat
import com.google.firebase.auth.FirebaseAuth
import java.text.NumberFormat
import java.util.*


class ObatDetailActivity : AppCompatActivity() {

    lateinit var database : FirebaseDatabase
    lateinit var refObat : DatabaseReference
    lateinit var refPesananObat : DatabaseReference
    lateinit var pesananObat: PesananObat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_obat_detail)

        val textJumlah = findViewById<EditText>(R.id.txtjumlah)

        database = FirebaseDatabase.getInstance()
        refObat = database.getReference(OBAT)
        refPesananObat = database.getReference(PESANAN_OBAT)

        val key = intent.getStringExtra(KEY_OBAT)
//        val mauth : FirebaseAuth= FirebaseAuth.getInstance()
//        val user : FirebaseUser? = mauth.currentUser
        val iduser = intent.getStringExtra(USER_ID)
        var harga : Int = 1


        refObat.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.child(key).getValue(Obat::class.java)!!
                harga = data.harga.toInt()
                txtnamaobat.text = data.nama
                imgobat.setImageResource(applicationContext.resources.getIdentifier(data.nama,
                    "drawable", applicationContext.packageName))
                txtdeskripsi.text = "Kategori : ${data.kategori}\n ${data.deskripsi}"
                pesananObat = PesananObat(data.nama,data.harga.toInt() ,0 )

            }
        })

        val format = NumberFormat.getCurrencyInstance()
        format.setMaximumFractionDigits(0)
        format.setCurrency(Currency.getInstance("IDR"))
        imgadd.setOnClickListener {
            val jumlah = textJumlah.text.toString().toInt() + 1
            textJumlah.setText(jumlah.toString())
            txttotalharga.text = "${format.format(jumlah*harga)}"
        }
        imgminus.setOnClickListener {
            var jumlah = textJumlah.text.toString().toInt() - 1
            if (jumlah == -1) jumlah = 0
            textJumlah.setText(jumlah.toString())
            txttotalharga.text = "${format.format(jumlah*harga)}"
        }
        btnsimpan.setOnClickListener {
            val jumlah = textJumlah.text.toString().toInt()
            pesananObat.jumlah = jumlah
            pesananObat.totalharga *= pesananObat.jumlah
            refPesananObat.child(iduser).child(key).setValue(pesananObat)
            finish()
        }

    }
}
