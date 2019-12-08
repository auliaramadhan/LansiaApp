package com.example.tubes.lana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class PengirimanActivity : AppCompatActivity() {

    lateinit var database : FirebaseDatabase
    lateinit var refPesananObat : DatabaseReference
    lateinit var mUser : FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengiriman)


        mUser = FirebaseAuth.getInstance().currentUser!!
        database = FirebaseDatabase.getInstance()
        refPesananObat = database.getReference(PESANAN_OBAT).child(mUser.uid)

        refPesananObat.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(p0: DataSnapshot) {

            }

        })
    }
}
