package com.example.tubes.lana

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_panduan_detail.*
import kotlinx.android.synthetic.main.content_panduan_detail.*

class PanduanDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panduan_detail)

        titlepanduan.text = intent.getStringExtra(ID_TITLE)
        detailpanduan.text = resources.getString(intent.getIntExtra(ID_STRING, -1))
        imagepanduan.setImageResource(intent.getIntExtra(ID_IMAGE, -1))

        fab.setOnClickListener { view ->
            //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            finish()
//            onBackPressed();
        }
    }

}

