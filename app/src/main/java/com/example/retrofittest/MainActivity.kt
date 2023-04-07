package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.retrofit.AuthRequest
import com.example.retrofittest.retrofit.MainAPI
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainAPI = retrofit.create(MainAPI::class.java)

        binding.buttonSignIn.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val user = mainAPI.Auth(
                    AuthRequest(
                        binding.editTextUsername.text.toString(),
                        binding.editTextPassword.text.toString()
                    )
                )
                runOnUiThread{
                    binding.apply {
                        Picasso.get().load(user.image).into(imageView)
                        firstName.text = user.firstName
                        secondName.text = user.lastName
                    }

                }
            }
        }
    }
}