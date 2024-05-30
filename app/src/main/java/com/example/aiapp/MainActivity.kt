package com.example.aiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text:TextView=findViewById(R.id.splash_text)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, options::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}