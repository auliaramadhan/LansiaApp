package com.example.tubes.lana

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.tasks.Task



class BerandaActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    lateinit var preferences : SharedPreferences

    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        val intent = Intent(this@BerandaActivity, BerandaActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("EXIT", true)
        startActivity(intent)
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        preferences = getSharedPreferences(SHAREDPREFFILE, MODE_PRIVATE)
        mAuth = FirebaseAuth.getInstance()

        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)

    }

    fun lapotikClick(view: View) {
        val nextActivity = Intent(this, LapotikActivity::class.java)
        startActivity(nextActivity)
    }
    fun lamasiClick(view: View) {
        val nextActivity = Intent(this, LamasiActivity::class.java)
        startActivity(nextActivity)
    }

    fun laminderClick(view: View) {
//        val nextActivity = Intent(this, LoginActivity::class.java)
//        startActivity(nextActivity)
    }
    fun lahragaClick(view: View) {
        val nextActivity = Intent(this, LahragaActivity::class.java)
        startActivity(nextActivity)
    }
    fun panduanClick(view: View) {
        val nextActivity = Intent(this, PanduanActivity::class.java)
        startActivity(nextActivity)
    }

    fun signOut(view: View) {
        // Firebase sign out
        mAuth.signOut()

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
            object : OnCompleteListener<Void> {
                override fun onComplete(task: Task<Void>) {

                }
            })
        preferences.edit().clear()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}
