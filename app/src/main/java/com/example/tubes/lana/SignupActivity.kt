package com.example.tubes.lana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.useremail
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

       private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.sup).setOnClickListener {
            val email : String = useremail.text.toString()
            val password : String = password.text.toString()
            val phone : Int = phone.text.toString().toInt()
            if (email != null || password != null || phone != null ) signUp(email, password) else Toast.makeText(
                this, "field mohon diisi",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    fun signUp(email:String,password:String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "terdapat kesalahan pada server mohon dicovba lagi", Toast.LENGTH_SHORT).show()
                Log.d("main", it.toString())
            }
    }
}
