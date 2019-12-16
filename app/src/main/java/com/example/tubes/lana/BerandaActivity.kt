package com.example.tubes.lana

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_beranda.*


class BerandaActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    lateinit var preferences: SharedPreferences

    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions
    var backButtonCount = 0

    override fun onBackPressed() {
        if (backButtonCount >= 1) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                "Press the back button once again to close the application.",
                Toast.LENGTH_SHORT
            ).show()
            backButtonCount++
            Handler().postDelayed({
                backButtonCount = 0
            }, 5000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        preferences = getSharedPreferences(SHAREDPREFFILE, MODE_PRIVATE)
        mAuth = FirebaseAuth.getInstance()

        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)


        txtwelcome.text = "Selamat Datang " + preferences.getString(USER_NAME, "")
    }

    fun lapotikClick(view: View) {
        val nextActivity = Intent(this, LapotikMenuActivity::class.java)
        startActivity(nextActivity)
    }

    fun lamasiClick(view: View) {
        val nextActivity = Intent(this, LamasiActivity::class.java)
        startActivity(nextActivity)
    }

    fun laminderClick(view: View) {
        val nextActivity = Intent(this, LaminderActivity::class.java)
        startActivity(nextActivity)
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
        preferences.edit().clear().apply()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        if (preferences.getString(USER_ID, null) == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}
