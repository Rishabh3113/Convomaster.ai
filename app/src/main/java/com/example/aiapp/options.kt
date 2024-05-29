package com.example.aiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class options : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)
      val   button:Button=findViewById(R.id.card1)
        button.setOnClickListener(){

        }


        val button2:Button=findViewById(R.id.card2)
        button2.setOnClickListener(){

        }

        val button3:Button=findViewById(R.id.card3)
        button3.setOnClickListener(){

        }

    }
}