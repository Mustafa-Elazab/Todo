package com.example.todo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.todo.R
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed(
            Runnable {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }, 1000
        )

    }
}