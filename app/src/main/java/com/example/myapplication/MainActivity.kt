package com.example.myapplication

import android.annotation.SuppressLint
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
        val url = "https://api.openweathermap.org/data/2.5/weather?q=Bielsko-Biala&lang=pl&appid=29604613abf856e1b8f45210c7ae7562"
        var data: JSONObject = JSONObject("{}")

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call, response: Response){
                data = JSONObject(response.body()?.string())

                runOnUiThread {
                    findViewById<TextView>(R.id.temperatura).text = data.getJSONObject("main").getString("temp")
                    findViewById<TextView>(R.id.stan).text = data.getString("name")


                    findViewById<TextView>(R.id.temperatura).text = "temperatura: " + data.getJSONObject("main").getString("temp")
                    findViewById<TextView>(R.id.wiatr).text = "wiatr: " + data.getJSONObject("wind").getString("speed")
                    findViewById<TextView>(R.id.cisnienie).text = "cisnienie: " + data.getJSONObject("wind").getString("speed")
                    findViewById<TextView>(R.id.wilgotnosc).text = "wilgotnosc: " + data.getJSONObject("main").getString("humidity")
                    findViewById<TextView>(R.id.widocznosc).text = "widocznosc: " + data.getString("visibility")
                }
            }
        })
    }
}