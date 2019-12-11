package com.example.tubes.lana

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    var backButtonCount = 0

    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)



        findViewById<Button>(R.id.login).setOnClickListener {
            val email = useremail.text.toString()
            val password = passwrd.text.toString()
            if (email != null || password != null) signIn(email, password) else Toast.makeText(
                this@LoginActivity, "field mohon diisi",
                Toast.LENGTH_SHORT
            ).show()

        }

        SignUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        }


        google_button.setOnClickListener {
            signInGoogle()
        }
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        } else {
            Toast.makeText(this, "Terdapat kesalahan eksekusi", Toast.LENGTH_LONG).show()
        }
    }

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

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            val mainIntent = Intent(this@LoginActivity, BerandaActivity::class.java)
            val preferences = getSharedPreferences(SHAREDPREFFILE, MODE_PRIVATE)
            val edit = preferences.edit()
            println(account?.id)
            edit.putString(USER_NAME, account?.displayName)
            edit.putString(USER_ID, account?.id)
            edit.apply()
            finish()
            startActivity(mainIntent)
        } catch (e: ApiException) {
            Toast.makeText(this@LoginActivity, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    fun signIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    sendToMain()
                } else {
                    //jika salah maka akan menampilkan toast
                    Toast.makeText(
                        this@LoginActivity, "Email atau password salah",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }

    //metod Untuk memindahkan laman
    private fun sendToMain() {
        val mainIntent = Intent(this, BerandaActivity::class.java)
        val preferences = getSharedPreferences(SHAREDPREFFILE, MODE_PRIVATE)
        val edit = preferences.edit()
        edit.putString(
            USER_NAME,
            mAuth.currentUser!!.email!!.split(("@").toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        )
        edit.putString(USER_ID, mAuth.uid)
        edit.apply()
        finish()
        startActivity(mainIntent)
    }

}
