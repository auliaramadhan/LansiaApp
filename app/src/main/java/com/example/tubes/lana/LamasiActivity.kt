package com.example.tubes.lana

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lamasi.*
import java.net.HttpURLConnection.HTTP_OK
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import android.widget.Toast
import android.content.Intent
import android.widget.AdapterView
import org.json.JSONException
import org.json.JSONObject
import android.os.AsyncTask
import android.provider.MediaStore.Video.VideoColumns.CATEGORY
import android.view.View
import com.example.tubes.lana.Adapter.ListNewsAdapter
import com.example.tubes.lana.Model.News


class LamasiActivity : AppCompatActivity() {

    
//    var listNews: ListView
//    var loader: ProgressBar

    var dataList: ArrayList<News> = ArrayList()
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lamasi)


        listNews.setEmptyView(loader);


        if (Function.isNetworkAvailable(getApplicationContext())) {
            val newsTask =  DownloadNews();
            newsTask.execute();
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }

    internal inner class DownloadNews : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg args: String): String? {
            val apiResponse:String = URL("https://newsapi.org/v2/top-headlines?country=id&category=$CATEGORY_api&apiKey=$API_KEY").readText()
//            return Function.excuteGet("https://newsapi.org/v2/top-headlines?country=id&category=$CATEGORY&apiKey=$API_KEY")
            return apiResponse
        }

        override fun onPostExecute(xml: String) {

            if (xml.length > 10) { // Just checking if not empty

                try {
                    val jsonResponse = JSONObject(xml)
                    val jsonArray = jsonResponse.optJSONArray("articles")

                    for (i in 0 until 20) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val map : News =News(jsonObject.optString(KEY_AUTHOR),
                            jsonObject.optString(KEY_TITLE),
                            jsonObject.optString(KEY_DESCRIPTION),
                            jsonObject.optString(KEY_URL),
                            jsonObject.optString(KEY_URLTOIMAGE),
                            jsonObject.optString(KEY_PUBLISHEDAT))
                        dataList.add(map)
                    }
                } catch (e: JSONException) {
                    Toast.makeText(applicationContext, "Unexpected error", Toast.LENGTH_SHORT)
                        .show()
                }

                val adapter = ListNewsAdapter(context, dataList)
                listNews.adapter = adapter

                listNews.onItemClickListener = object : AdapterView.OnItemClickListener {
                    override fun onItemClick(
                        parent: AdapterView<*>, view: View,
                        position: Int, id: Long
                    ) {
                        val i = Intent(context, LamasiDetailActivity::class.java)
                        i.putExtra("url", dataList[+position].URL)
                        startActivity(i)
                    }
                }

            } else {
                Toast.makeText(applicationContext, "No news found", Toast.LENGTH_SHORT).show()
            }
        }
    }

}


object Function {

    fun isNetworkAvailable(context: Context): Boolean {
        return (context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo != null
    }


    fun excuteGet(targetURL: String): String? {
        var connection: HttpURLConnection? = null
        try {
            val url: URL = URL(targetURL)
            connection = url.openConnection() as HttpURLConnection
            connection!!.setRequestProperty("content-type", "application/json;  charset=utf-8")
            connection!!.setRequestProperty("Content-Language", "en-US")
            connection!!.setUseCaches(false)
            connection!!.setDoInput(true)
            connection!!.setDoOutput(false)

            val `is`: InputStream
            val status = connection!!.getResponseCode()
            if (status != HTTP_OK)
                `is` = connection!!.getErrorStream()
            else
                `is` = connection!!.getInputStream()

            val rd = BufferedReader(InputStreamReader(`is`))
            val response = StringBuffer()
            var line: String = rd.readLine()
            while (line != null) {
                response.append(line)
                response.append('\r')
            }
            rd.close()
            return response.toString()
        } catch (e: Exception) {
            return null
        } finally {
            if (connection != null) {
                connection!!.disconnect()
            }
        }
    }
}
