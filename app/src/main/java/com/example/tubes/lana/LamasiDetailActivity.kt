package com.example.tubes.lana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.content.Intent
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.webkit.WebView
import android.widget.ProgressBar


class LamasiDetailActivity : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var loader: ProgressBar
    var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lamasi_detail_activity)

        url = intent.getStringExtra("url")
        loader = findViewById(R.id.loader)
        webView = findViewById(R.id.webView)


        webView.getSettings().setJavaScriptEnabled(true)
        webView.getSettings().setLoadWithOverviewMode(true)
        webView.getSettings().setUseWideViewPort(true)
        webView.setWebViewClient(object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                loader.setVisibility(View.VISIBLE)
                view.loadUrl(url)

                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                loader.setVisibility(View.GONE)
            }
        })

        webView.loadUrl(url)

    }
}
