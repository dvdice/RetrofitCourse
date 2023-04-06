package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.retrofittest.retrofit.ProductAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.textView)
        val btn = findViewById<Button>(R.id.button)
        val et = findViewById<EditText>(R.id.etID)

        val idFromEt = et.text.toString().toInt()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val productAPI = retrofit.create(ProductAPI::class.java)

        btn.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val product = productAPI.getProductByID(idFromEt)
                runOnUiThread{
                    tv.text = product.title
                }
            }
        }
    }
}