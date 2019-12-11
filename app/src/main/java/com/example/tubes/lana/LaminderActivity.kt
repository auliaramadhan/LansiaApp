package com.example.tubes.lana

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tubes.lana.Adapter.ReminderAdapter
import com.example.tubes.lana.Model.Reminder
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_laminder_acitivty.*


class LaminderActivity : AppCompatActivity() {

    lateinit var database: FirebaseDatabase
    lateinit var refpengingat: DatabaseReference

    lateinit var preferences: SharedPreferences
    private lateinit var listReminder: MutableList<Reminder>
    lateinit var adapter: ReminderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laminder_acitivty)

        database = FirebaseDatabase.getInstance()

        preferences = getSharedPreferences(SHAREDPREFFILE, MODE_PRIVATE)
        val iduser = preferences.getString(USER_ID, "")
        refpengingat = database.getReference(REMINDER).child(iduser)

        listReminder = mutableListOf()

        var time: Long = 0


        refpengingat.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.d("error", p0.toString())
            }

            override fun onDataChange(datas: DataSnapshot) {
                if (datas.exists()) {
                    listReminder.clear()
                    for (data in datas.children) {
                        val pesanan = data.getValue(Reminder::class.java)!!
                        listReminder.add(pesanan)
                    }
                    adapter = ReminderAdapter(this@LaminderActivity, listReminder)
                    list_reminder.adapter = adapter
                }
            }

        })


        fab.setOnClickListener { view ->
            val addFragment = DialogAddLaminder()
            addFragment.show(this@LaminderActivity.supportFragmentManager, "ada")
        }
    }


}
