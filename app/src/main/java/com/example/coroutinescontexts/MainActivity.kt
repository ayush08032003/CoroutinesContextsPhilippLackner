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
        delay(3000L)
        return "This is the Answer"
    }
}

/*
NOTES/STEPS:-
In General Coroutines is always stared in a particular context. For now we only use GlobalScope.launch{} to start a coroutine, but that didn't give us much
control over it.
But we can get Dispatchers as launch functions
Dispatcher.Main -> A coroutine dispatcher that is confined to the Main thread operating with UI objects. It will start a coroutine in the Main Thread.
Dispatcher.IO -> Useful for all Kind of data operations such as Networking, Data Operations, Databases and Reading and writing a File.
Dispatcher.Default -> If you are planning on Doing complex operations and long-running calculations that will block the main thread
Dispatcher.Unconfined ->

Can ALso use our own new Thread by using newSingleThreadContext("MyThread")
ALso can Switch the Context with the Main Dispatcher using withContext(Dispatcher.Main) function.

 */