package com.imakeanapp.lifecyclesample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Lifecycle", "onCreate() called")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart() called")
        val database = DatabaseRepository(Executors.newSingleThreadExecutor(), lifecycle)
        database.getUser(object : OnGetUserCallback {
            override fun onGetUser(user: String) {
                Log.d("Lifecycle", "Callback called: $user")

                val userFragment = UserFragment()
                supportFragmentManager.beginTransaction()
                        .add(R.id.fragment_container, userFragment).commit()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy() called")
    }
}
