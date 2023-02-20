package com.example.coroutinescontexts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var tvDummy : TextView
    private val TAG = "DebugAyush"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvDummy = findViewById(R.id.tvDummy)

        GlobalScope.launch(Dispatchers.IO){
            Log.d(TAG, "Starting Coroutine in thread ${Thread.currentThread().name}")
            val answer = doNetworkCall()
            withContext(Dispatchers.Main){
                Log.d(TAG, "Setting Text in thread ${Thread.currentThread().name}")
                tvDummy.text = answer
            }
        }
    }

    suspend fun doNetworkCall():String{
        delay(5000L)
        return "This is the Answer"
    }
}