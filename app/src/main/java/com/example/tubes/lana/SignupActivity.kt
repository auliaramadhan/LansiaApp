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

class SignupActivity : AppCompatActivity() {

    var fieldemail: TextView = findViewById(R.id.useremail)
    var fieldpassword: TextView = findViewById(R.id.passwrd)
    var fieldphone: TextView = findViewById(R.id.passwrd)
    private lateinit var mAuth: FirebaseAuth

    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.sup).setOnClickListener {
            val email : String = fieldemail.text.toString()
            val password : String = fieldpassword.text.toString()
            val phone : Int = fieldphone.text.toString().toInt()
            if (email == null || password == null || phone == null ) signUp(email, password) else Toast.makeText(
                this, "field mohon diisi",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    fun signUp(email:String,password:String){
        mAuth.signInWithEmailAndPassword(email, password)
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
