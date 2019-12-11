package com.example.tubes.lana

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tubes.lana.Adapter.ObatAdapter
import com.example.tubes.lana.Model.Obat
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_lapotik.*


class LapotikActivity : AppCompatActivity() {

    lateinit var database: FirebaseDatabase
    lateinit var refObat: DatabaseReference

    lateinit var preferences: SharedPreferences

    private lateinit var listObat: MutableList<Obat>
    lateinit var adapter: ObatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lapotik)

        val listView: RecyclerView = findViewById(R.id.obatListView)

        listObat = mutableListOf()

        database = FirebaseDatabase.getInstance()
        refObat = database.getReference(OBAT)

        preferences = getSharedPreferences(SHAREDPREFFILE, MODE_PRIVATE)
        val iduser = preferences.getString(USER_ID, "")

        btnpembayaran.setOnClickListener {
            val intent = Intent(this, PembayaranActivity::class.java)
            intent.putExtra(USER_ID, iduser)
            startActivity(intent)
        }


        refObat.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@LapotikActivity, p0.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(datas: DataSnapshot) {
                if (datas.exists()) {
                    for (data in datas.children) {
                        val obat = data.getValue(Obat::class.java)!!
                        obat.key = data.key!!
                        listObat.add(obat)
                    }
                }
                adapter = ObatAdapter(this@LapotikActivity, listObat) { category ->
                    val obatIntent = Intent(this@LapotikActivity, ObatDetailActivity::class.java)
                    obatIntent.putExtra(KEY_OBAT, category.key)
                    obatIntent.putExtra(USER_ID, iduser)
//                        startActivityForResult(obatIntent, REQUEST_CODE)
                    startActivity(obatIntent)
                }
                val layoutManager = GridLayoutManager(this@LapotikActivity, 2)
                listView.layoutManager = layoutManager
                listView.adapter = adapter
            }
        })

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            val layoutManager = GridLayoutManager(this@LapotikActivity, 2)
            listView.layoutManager = layoutManager
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            val layoutManager = GridLayoutManager(this@LapotikActivity, 3)
            listView.layoutManager = layoutManager
            imgLana.visibility = View.GONE
        }

        editsearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(arg0: Editable) {}

            override fun beforeTextChanged(
                arg0: CharSequence, arg1: Int,
                arg2: Int, arg3: Int
            ) {
            }

            override fun onTextChanged(
                arg0: CharSequence, arg1: Int, arg2: Int,
                arg3: Int
            ) {
                val textlength = editsearch.getText().length
                val arrayObat = mutableListOf<Obat>()
                for (obat in listObat) {
                    if (textlength <= obat.nama.length) {
                        Log.d("ertyyy", obat.nama.toLowerCase().trim())
                        val textSearch = editsearch.getText().toString().toLowerCase().trim()
                        // cari di nama dan kategori
                        if (obat.nama.toLowerCase().trim().contains(textSearch) ||
                            obat.kategori.toLowerCase().trim().contains(textSearch)
                        ) {
                            arrayObat.add(obat)
                        }
                    }
                }

                adapter = ObatAdapter(this@LapotikActivity, arrayObat) { category ->
                    val obatIntent = Intent(this@LapotikActivity, ObatDetailActivity::class.java)
                    obatIntent.putExtra(KEY_OBAT, category.key)
                    obatIntent.putExtra(USER_ID, iduser)
//                        startActivityForResult(obatIntent, REQUEST_CODE)
                    startActivity(obatIntent)
                }
                listView.setAdapter(adapter)
            }
        })


    }


    override fun onStart() {
        super.onStart()
        val refPesananObat = database.getReference(PESANAN_OBAT).child(INFOPESANAN)
//        val user : FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val id = preferences.getString(USER_ID, "")
        refPesananObat.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                if (id != "") {

                    if (snapshot.child(id).hasChild(PENGIRIMAM)) {
                        val inten = Intent(this@LapotikActivity, PengirimanActivity::class.java)
                        inten.putExtra(USER_ID, id)
                        startActivity(inten)
                        finish()
                    }
                }
            }
        })
    }
}
