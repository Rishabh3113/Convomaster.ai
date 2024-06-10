package com.example.aiapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.google.ai.client.generativeai.GenerativeModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.runBlocking

class text : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)
        val input:TextInputEditText =findViewById(R.id.user_text)
        val gen:TextInputEditText=findViewById(R.id.gen_text)
        val button:TextView=findViewById(R.id.text1)
        val bar:ProgressBar=findViewById(R.id.bar)

        button.setOnClickListener(){
            if(TextUtils.isEmpty(input.text)){
                input.error="please enter text to generate"
                input.requestFocus()

            }
            else{
                bar.progress=View.VISIBLE
                generateinfo(input,gen,bar)

            }
        }







    }

    fun generateinfo(input: TextInputEditText, gen: TextInputEditText,bar: ProgressBar) {
        val generativeModel = GenerativeModel(

            modelName = "gemini-1.5-flash",

            apiKey ="AIzaSyA7n95qfOVrQOFpFlG2qDAwXP5SLH4yqvU"
        )

        val prompt = input.text.toString().trim()
        runBlocking {
            val response = generativeModel.generateContent(prompt)
            bar.visibility=View.GONE

            gen.setText(response.text)

        }
    }
}