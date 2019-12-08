package com.example.tubes.lana

import android.content.Intent
import android.content.res.Configuration
import android.nfc.cardemulation.CardEmulation.EXTRA_CATEGORY
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tubes.lana.Adapter.ObatAdapter
import com.example.tubes.lana.Model.Obat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_lapotik.*
import java.util.*
import kotlin.collections.ArrayList


class LapotikActivity : AppCompatActivity() {

    lateinit var database : FirebaseDatabase
    lateinit var refObat : DatabaseReference

    private lateinit var listObat : MutableList<Obat>
    lateinit var adapter : ObatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lapotik)

        val listView : RecyclerView = findViewById(R.id.obatListView)

        listObat = mutableListOf()

        database = FirebaseDatabase.getInstance()
        refObat = database.getReference(OBAT)

        btnpembayaran.setOnClickListener{
            startActivity(Intent(this, PembayaranActivity::class.java))
        }


        refObat.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@LapotikActivity, p0.message, Toast.LENGTH_LONG).show()
            }
            override fun onDataChange(datas: DataSnapshot) {
                if (datas.exists()){
                    for (data in datas.children){
                        val obat = data.getValue(Obat::class.java)!!
                        obat.key = data.key!!
                        listObat.add(obat)
                    }
                }
                adapter = ObatAdapter(this@LapotikActivity, listObat) { category ->
                    val obatIntent = Intent(this@LapotikActivity, ObatDetailActivity::class.java)
                    obatIntent.putExtra(KEY_OBAT, category.key)
//                        startActivityForResult(obatIntent, REQUEST_CODE)
                    startActivity(obatIntent)
                }
                val layoutManager = GridLayoutManager(this@LapotikActivity, 2)
                listView.layoutManager = layoutManager
                listView.adapter = adapter
            }
        })



    }


    override fun onStart() {
        super.onStart()
        val refPesananObat = database.getReference(PESANAN_OBAT).child(INFOPESANAN)
        val user : FirebaseUser? = FirebaseAuth.getInstance().currentUser
        refPesananObat.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                if (user != null) {

                    if (snapshot.child(user.uid).hasChild(PENGIRIMAM)) {
                        val inten = Intent(this@LapotikActivity, PengirimanActivity::class.java)
                        startActivity(inten)
                        finish()
                    }
                }
            }
        })
    }
}
