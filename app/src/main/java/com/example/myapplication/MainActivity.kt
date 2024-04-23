package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
        getWeather("Bielsko-Biała")

        val search = findViewById<EditText>(R.id.search).text.toString()
        findViewById<Button>(R.id.btn1).setOnClickListener {
            getWeather(search)
        }
    }

    fun getWeather(miasto: String) {
        val url =
            "https://api.openweathermap.org/data/2.5/weather?q=$miasto&lang=pl&appid=29604613abf856e1b8f45210c7ae7562"
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
                    findViewById<TextView>(R.id.miasto).text = data.getString("name")
                    findViewById<TextView>(R.id.stan).text = data.getJSONArray("weather").getJSONObject(0).getString("description")


                    findViewById<TextView>(R.id.temperatura).text = "temperatura: " + data.getJSONObject("main").getString("temp")
                    findViewById<TextView>(R.id.wiatr).text = "wiatr: " + data.getJSONObject("wind").getString("speed")
                    findViewById<TextView>(R.id.wilgotnosc).text = "wilgotnosc: " + data.getJSONObject("main").getString("humidity")

                }
            }
        })
    }
}