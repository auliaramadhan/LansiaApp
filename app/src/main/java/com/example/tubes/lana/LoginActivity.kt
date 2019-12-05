package com.example.tubes.lana

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.wifi.WifiConfiguration.AuthAlgorithm.SHARED
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.tasks.Task


class LoginActivity : AppCompatActivity() {

    var fieldemail: TextView = findViewById(R.id.useremail)
    var fieldpassword: TextView = findViewById(R.id.passwrd)
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.login).setOnClickListener {
            val email = fieldemail.text.toString()
            val password = fieldemail.text.toString()
            if (email == null || password == null ) signIn(email, password) else Toast.makeText(
                this@LoginActivity, "field mohon diisi",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    fun signIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {

//                    val user = mAuth.currentUser
//                    // tombol signIn di tap, maka akan pindah ke laman aktivitas utama
//                    val i = Intent(this, BerandaActivity::class.java)
//                    //memulai intent
//                    startActivity(i)

                    val mainIntent = Intent(this, BerandaActivity::class.java)
                    val preferences = getSharedPreferences(SHAREDPREFFILE, MODE_PRIVATE)
                    val edit = preferences.edit()
                    edit.putString(
                        "user",
                        mAuth.currentUser!!.email!!.split(("@").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                    )
                    edit.commit()
                    mainIntent.putExtra(
                        "user",
                        mAuth.currentUser!!.email!!.split(("@").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                    )
                    startActivity(mainIntent)
                    finish()
                } else {
                    //jika salah maka akan menampilkan toast
                    Toast.makeText(
                        this@LoginActivity, "Akun Belum Terdaftar",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }

    //Metode bolean untuk memvalidasi
//    fun validateForm(): Boolean {
////        fun valid() : Boolean =
////        // kondisi ketika email dan password kosong
////        if (email == null || password == null ) {
////            valid = false
////        } else {
////            valid = true
////        }
////        return valid
////
//        return
//
//    }

    //metod Untuk memindahkan laman
//    private fun sendToMain() {
//        val mainIntent = Intent(this, BerandaActivity::class.java)
//        val preferences = getSharedPreferences(SHAREDPREFFILE, MODE_PRIVATE)
//        val edit = preferences.edit()
//        edit.putString(
//            "user",
//            mAuth.currentUser!!.email!!.split(("@").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
//        )
//        edit.commit()
//        mainIntent.putExtra(
//            "user",
//            mAuth.currentUser!!.email!!.split(("@").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
//        )
//        startActivity(mainIntent)
//        finish()
//    }

}
