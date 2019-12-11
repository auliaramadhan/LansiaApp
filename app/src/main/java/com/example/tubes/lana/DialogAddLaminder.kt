package com.example.tubes.lana

import android.annotation.TargetApi
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.tubes.lana.Model.Reminder
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.dialog_add_reminder.*
import java.util.*


class DialogAddLaminder : DialogFragment() {

    private var content: String? = null

    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences =
            activity!!.getSharedPreferences(SHAREDPREFFILE, AppCompatActivity.MODE_PRIVATE)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view: View = inflater.inflate(R.layout.dialog_add_reminder, container, false)

        var time: Long = 0
        val buttonCancel = view.findViewById(R.id.buttonCancel) as Button
        val buttonAccept = view.findViewById(R.id.buttonAccept) as Button
        val editwaktu = view.findViewById(R.id.editwaktu) as EditText
        val setwaktu = view.findViewById(R.id.setwaktu) as Button

        setwaktu.setOnClickListener {
            val dialogView = View.inflate(activity, R.layout.date_time_picker, null)
            val alertDialog = context?.let { it1 -> AlertDialog.Builder(it1).create() }

            dialogView.findViewById<Button>(R.id.date_time_set)
                .setOnClickListener(object : View.OnClickListener {
                    @TargetApi(Build.VERSION_CODES.O)
                    @RequiresApi(Build.VERSION_CODES.M)
                    override fun onClick(view: View) {

                        val datePicker = dialogView.findViewById(R.id.date_picker) as DatePicker
                        val timePicker = dialogView.findViewById(R.id.time_picker) as TimePicker

                        val calendar = GregorianCalendar(
                            datePicker.getYear(),
                            datePicker.getMonth(),
                            datePicker.getDayOfMonth(),
                            timePicker.hour,
                            timePicker.minute
                        )

                        time = calendar.getTimeInMillis()
                        alertDialog?.dismiss()
                        val waktu = Date(time).toString().split(" ")
                        editwaktu.setText("${waktu[1]}  ${waktu[2]}  ${waktu[3]}")
                    }
                })
            alertDialog?.setView(dialogView)
            alertDialog?.show()
        }

        buttonCancel.setOnClickListener { dismiss() }
        buttonAccept.setOnClickListener {

            val iduser = preferences.getString(USER_ID, "")
            val data = FirebaseDatabase.getInstance().getReference(REMINDER).child(iduser)
            val objek = Reminder(
                editnamapengingat.text.toString(),
                editketerangan.text.toString(),
                editwaktu.text.toString()
            )
            data.push().setValue(objek)
            dismiss()
        }

        return view
    }


}

