package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var url = "https://api.openweathermap.org/data/2.5/weather?q=Bielsko-Biala&lang=pl&appid=29604613abf856e1b8f45210c7ae7562";
        var data: JSONObject = JSONObject("{}");

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response){
                data = JSONObject(response.body()?.string());
                var temp = data.getJSONObject("main").getString("temp");
                var temp_label = findViewById<TextView>(R.id.textView);
                runOnUiThread {
                    temp_label.text = temp;
                }

            }
        })




    }
}